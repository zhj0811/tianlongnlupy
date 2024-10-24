package com.cctegitc.ai.function.modules.knowledgemanagement.service.impl;

import com.alibaba.druid.support.ibatis.SpringIbatisBeanNameAutoProxyCreator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cctegitc.ai.authority.common.utils.DateUtils;
import com.cctegitc.ai.function.db.dao.AnswerInfoMapper;
import com.cctegitc.ai.function.db.dao.KnowledgeLibraryMapper;
import com.cctegitc.ai.function.db.dao.RelevanceQuestionMapper;
import com.cctegitc.ai.function.db.pojo.AnswerInfo;
import com.cctegitc.ai.function.db.pojo.KnowledgeLibrary;
import com.cctegitc.ai.function.db.pojo.R;
import com.cctegitc.ai.function.db.pojo.RelevanceQuestion;
import com.cctegitc.ai.function.exception.CustomException;
import com.cctegitc.ai.function.modules.knowledgemanagement.service.QuestionlibService;
import com.cctegitc.ai.function.util.Constants;
import com.cctegitc.ai.function.util.HttpURLConnectionUtil;
import com.cctegitc.ai.function.util.StringUtils;
import com.cctegitc.ai.function.util.common.res.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class QuestionlibServiceImpl implements QuestionlibService {

    @Resource
    private KnowledgeLibraryMapper knowledgeLibraryMapper;

    @Resource
    private RelevanceQuestionMapper relevanceQuestionMapper;

    @Resource
    private AnswerInfoMapper answerInfoMapper;

    @Autowired
    private ResultResponse resultResponse;

    @Autowired
    private RelevanceQuestionService relevanceQuestionService;

    @Autowired
    private HttpURLConnectionUtil httpURLConnectionUtil;

    @Value("${dispatch.nwpu-http-url}")
    private String httpUrl;



    /**
     * 分页查询所有问答知识
     *
     * @return
     */

    @Override
    public R findByPage(String question, int page, int limit) {
        //标准问题
        QueryWrapper<KnowledgeLibrary> knowledgeLibraryQueryWrapper = new QueryWrapper<>();
        Page<KnowledgeLibrary> infoPage = new Page<>(page, limit);
        if (!"".equals(question)) {
            knowledgeLibraryQueryWrapper.like("question", question);
        }
        Page<KnowledgeLibrary> page1 = knowledgeLibraryMapper.selectPage(infoPage, knowledgeLibraryQueryWrapper);
        List<KnowledgeLibrary> knowledgeLibrary = page1.getRecords();
        QueryWrapper<RelevanceQuestion> relevanceQuestionQueryWrapper = new QueryWrapper<>();
        for (int i = 0; i < knowledgeLibrary.size(); i++) {
            relevanceQuestionQueryWrapper.clear();
            relevanceQuestionQueryWrapper.eq("question_id", knowledgeLibrary.get(i).getQuestionId());
            knowledgeLibrary.get(i).setRelevanceQuestion(relevanceQuestionMapper.selectList(relevanceQuestionQueryWrapper));
        }
        page1.setRecords(knowledgeLibrary);
        return R.success(page1);
    }

    @Override
    @Transactional
    public ResultResponse save(@RequestBody KnowledgeLibrary KnowledgeLibrary) {
        // 创建并插入答案信息
        AnswerInfo ai = new AnswerInfo();
        ai.setAnswer(KnowledgeLibrary.getAnswer());
        int insertAns = answerInfoMapper.insert(ai);
        if (insertAns != 1) {
            throw new RuntimeException("Failed to insert answer info");
        }

        // 创建并插入知识库信息
        KnowledgeLibrary knowledgeLibrary = buildKnowledgeLibrary(KnowledgeLibrary);
        int insertKnow = knowledgeLibraryMapper.insert(knowledgeLibrary);
        if (insertKnow != 1) {
            throw new RuntimeException("Failed to insert knowledge library info");
        }

        // 通过插入后获取的ID，避免额外的查询
        int questionId = knowledgeLibrary.getQuestionId();
        // 添加关联问题库，批量插入提高效率
        List<RelevanceQuestion> relevanceQuestions = KnowledgeLibrary.getRelevanceQuestion();
        for (RelevanceQuestion relevanceQuestion : relevanceQuestions) {
            if (StringUtils.isNotEmpty(relevanceQuestion.getRelevanceQuestion())) {
                relevanceQuestion.setQuestionId(questionId);
            }
        }
        boolean saveBatch = relevanceQuestionService.saveBatch(relevanceQuestions);
        // 日志记录成功插入的关联问题
        log.debug("Inserted relevance questions for question ID: " + questionId);
        if (saveBatch) {
            resultResponse.setCode(Constants.STATUS_OK);
            resultResponse.setMessage(Constants.MESSAGE_OK);
            resultResponse.setData(Constants.UPDATE_SUCESS);
            httpURLConnectionUtil.doGetNoParam(httpUrl, "/kbqa/updatebyself");
            return resultResponse;
        }
        resultResponse.setCode(Constants.STATUS_FAIL);
        resultResponse.setMessage(Constants.MESSAGE_FAIL);
        resultResponse.setData(Constants.SAVE_SUCESS);
        return resultResponse;
    }

    private static KnowledgeLibrary buildKnowledgeLibrary(KnowledgeLibrary KnowledgeLibrary) {
        KnowledgeLibrary knowledgeLibrary = new KnowledgeLibrary();
        knowledgeLibrary.setQuestion(KnowledgeLibrary.getQuestion());
        knowledgeLibrary.setAnswer(KnowledgeLibrary.getAnswer());
        knowledgeLibrary.setQuestionType(KnowledgeLibrary.getQuestionType());
        knowledgeLibrary.setRemark(KnowledgeLibrary.getRemark());
        log.debug("KnowledgeLibrary EffectTime is {}", KnowledgeLibrary.getEffectTime());
        knowledgeLibrary.setEffectTime(DateUtils.formatTimeToNormal(KnowledgeLibrary.getEffectTime()));
        log.debug("knowledgeLibrary EffectTime is {}", knowledgeLibrary.getEffectTime());
        knowledgeLibrary.setEfficacyTime(DateUtils.formatTimeToNormal(KnowledgeLibrary.getEfficacyTime()));
        return knowledgeLibrary;
    }

    /**
     * 修改当前该问答知识
     */
    @Override
    @Transactional
    public ResultResponse update(@RequestBody KnowledgeLibrary KnowledgeLibrary) {
        try {
            // 更新标准问题
            KnowledgeLibrary knowledgeLibrary = buildKnowledgeLibrary(KnowledgeLibrary);
            UpdateWrapper<KnowledgeLibrary> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("question_id", KnowledgeLibrary.getQuestionId());
            int updateCount = knowledgeLibraryMapper.update(knowledgeLibrary, updateWrapper);
            if (updateCount != 1) {
                throw new RuntimeException("Update failed for KnowledgeLibrary with question ID: " + KnowledgeLibrary.getQuestionId());
            }

            // 更新关联问题
            List<RelevanceQuestion> relevanceQuestions = KnowledgeLibrary.getRelevanceQuestion();
            for (RelevanceQuestion relevanceQuestion : relevanceQuestions) {
                if (relevanceQuestion.getRelevanceId() != null) {
                    UpdateWrapper<RelevanceQuestion> relevanceQuestionUpdateWrapper = new UpdateWrapper<>();
                    relevanceQuestionUpdateWrapper.eq("relevance_id", relevanceQuestion.getRelevanceId());
                    int relevanceUpdateCount = relevanceQuestionMapper.update(relevanceQuestion, relevanceQuestionUpdateWrapper);
                    if (relevanceUpdateCount != 1) {
                        throw new RuntimeException("Update failed for RelevanceQuestion with ID: " + relevanceQuestion.getRelevanceId());
                    }
                } else {
                    relevanceQuestionMapper.insert(relevanceQuestion);
                }
            }
            resultResponse.setCode(Constants.STATUS_OK);
            resultResponse.setMessage(Constants.MESSAGE_OK);
            resultResponse.setData(Constants.UPDATE_SUCESS);
            try  {
                httpURLConnectionUtil.doGetNoParam(httpUrl, "/kbqa/updatebyself");
            } catch (Exception e) {
                log.error("Error /kbqa/updatebyself : " + e.getMessage(), e);
            }
            return resultResponse;
        } catch (RuntimeException  e) {
            log.error("Error updating problem base information: " + e.getMessage(), e);
            resultResponse.setCode(Constants.STATUS_FAIL);
            resultResponse.setMessage(e.getMessage());
            resultResponse.setData(Constants.UPDATE_FAILED);
            return resultResponse;
        }
    }

    /**
     * 删除当前该问答知识
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultResponse delete(Integer questionId) {
        // 先删除关联的问题库数据
        QueryWrapper<RelevanceQuestion> relQueryWrapper = new QueryWrapper<>();
        relQueryWrapper.eq("question_id", questionId);
        if (relevanceQuestionMapper.delete(relQueryWrapper) != 1) {
            resultResponse.setCode(Constants.STATUS_FAIL);
            resultResponse.setMessage("删除关联问题失败");
            return resultResponse;
        }

        // 再删除主问题库数据
        if (knowledgeLibraryMapper.deleteById(questionId) == 1) {
            resultResponse.setCode(Constants.STATUS_OK);
            resultResponse.setMessage("删除成功");
            resultResponse.setData("问题及其关联问题已成功删除");
            try  {
                httpURLConnectionUtil.doGetNoParam(httpUrl, "/kbqa/updatebyself");
            } catch (Exception e) {
                log.error("Error /kbqa/updatebyself : " + e.getMessage(), e);
            }
            return resultResponse;
        } else {
            resultResponse.setCode(Constants.STATUS_FAIL);
            resultResponse.setMessage("删除失败，未找到指定问题");
            resultResponse.setData(null);
            return resultResponse;
        }
    }

    @Override
    public Integer selectIdMax() {
        return relevanceQuestionMapper.selectIdMax();
    }


    @Transactional
    public String importData(List<KnowledgeLibrary> knowledgeLibraries) {
        if (knowledgeLibraries == null || knowledgeLibraries.isEmpty()) {
            throw new CustomException("导入用户数据不能为空！");
        }

        int successNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        int questionId = knowledgeLibraryMapper.selectIdMax() + 1;

        try {
            for (KnowledgeLibrary knowledgeLibrary : knowledgeLibraries) {
                knowledgeLibrary.setQuestionId(questionId++);
                knowledgeLibraryMapper.insert(knowledgeLibrary);

                List<RelevanceQuestion> relevanceQuestions = new ArrayList<>();
                for (String relevanceStr : knowledgeLibrary.getRelevanceQuestionFlag().split("\\?")) {
                    RelevanceQuestion relevanceQuestion = new RelevanceQuestion();
                    relevanceQuestion.setQuestionId(knowledgeLibrary.getQuestionId());
                    relevanceQuestion.setRelevanceQuestion(relevanceStr);
                    relevanceQuestions.add(relevanceQuestion);
                }
                relevanceQuestionService.saveBatch(relevanceQuestions);
                successNum++;
                successMsg.append(String.format("<br/>%d、问题 %s 导入成功", successNum, knowledgeLibrary.getQuestion()));
            }
        } catch (Exception e) {
            failureMsg.append(String.format("导入过程中出现错误，已终止。错误：%s", e.getMessage()));
            log.error("导入数据失败", e);
            throw new CustomException(failureMsg.toString());
        }

        if (successNum > 0) {
            try  {
                httpURLConnectionUtil.doGetNoParam(httpUrl, "/kbqa/updatebyself");
            } catch (Exception e) {
                log.error("Error /kbqa/updatebyself : " + e.getMessage(), e);
            }

            successMsg.insert(0, String.format("恭喜您，数据已全部导入成功！共 %d 条，数据如下：", successNum));
            return successMsg.toString();
        } else {
            return "未导入任何数据。";
        }
    }

    @Override
    public List<KnowledgeLibrary> findAll() {
        return knowledgeLibraryMapper.findAll();
    }
}
