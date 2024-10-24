package com.cctegitc.ai.function.modules.knowledgemanagement.service;

import com.cctegitc.ai.function.db.pojo.KnowledgeLibrary;
import com.cctegitc.ai.function.db.pojo.R;
import com.cctegitc.ai.function.util.common.res.ResultResponse;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Description:
 * @Author: wgd
 * @Date: 2023-07-10
 * @Time: 16:24
 */
public interface QuestionlibService {
    R findByPage(String question, int page, int limit);

    ResultResponse save(@RequestBody KnowledgeLibrary knowledgeLibrary);

    ResultResponse update(@RequestBody KnowledgeLibrary knowledgeLibrary);

    ResultResponse delete(Integer questionId);

    Integer selectIdMax();

    String importData(List<KnowledgeLibrary> knowledgeLibraries);

    List<KnowledgeLibrary> findAll();
}
