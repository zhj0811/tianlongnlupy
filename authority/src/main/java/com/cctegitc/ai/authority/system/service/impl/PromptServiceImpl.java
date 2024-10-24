package com.cctegitc.ai.authority.system.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cctegitc.ai.authority.common.core.domain.entity.Attachment;
import com.cctegitc.ai.authority.common.core.domain.entity.Collection;
import com.cctegitc.ai.authority.common.core.domain.entity.Prompt;
import com.cctegitc.ai.authority.common.utils.StringUtils;
import com.cctegitc.ai.authority.system.mapper.AttachmentMapper;
import com.cctegitc.ai.authority.system.mapper.CollectionMapper;
import com.cctegitc.ai.authority.system.mapper.PromptMapper;
import com.cctegitc.ai.authority.system.service.ISysUserService;
import com.cctegitc.ai.authority.system.service.PromptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@DS("db1")
public class PromptServiceImpl implements PromptService {

    @Autowired
    private PromptMapper promptMapper;

    @Autowired
    private CollectionMapper collectionMapper;

    @Autowired
    private SysUserServiceImpl iSysUserService;

    @Autowired
    private AttachmentMapper attachmentMapper;

    /**
     * 根据页面名称查询页面提示信息
     *
     * @param path 路由地址
     * @return
     */
    public Prompt findByPrompt(String path) {
        return promptMapper.getPrompt(path);
    }

    @Override
    public void savePrompt(Prompt prompt) {
        Collection collection = new Collection();
        collection.setMid(prompt.getMid());
        collection.setUid(iSysUserService.getUserId());
        int insert = collectionMapper.insert(collection);
        if (insert != 0) {
            prompt.setCid(collection.getId());
            promptMapper.updateById(prompt);
        }
    }

    @Override
    public void updatePrompt(Prompt prompt) {
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mid", prompt.getMid());
        queryWrapper.eq("uid", iSysUserService.getUserId());
        int insert = collectionMapper.delete(queryWrapper);
        if (insert != 0) {
            promptMapper.updateById(prompt);
        }
    }

    @Override
    public void update(Prompt prompt) {
        int count = promptMapper.updateById(prompt);
        if (count != 0) {
            if (StringUtils.isNotEmpty(prompt.getFid())) {
                Attachment attachment = new Attachment();
                attachment.setBusinessNo(prompt.getId() + "");
                QueryWrapper<Attachment> wrapper = new QueryWrapper<>();
                wrapper.eq("id", prompt.getFid());
                attachmentMapper.update(attachment, wrapper);
            }
        }
    }

    @Override
    public void save(Prompt prompt) {
        int count = promptMapper.insert(prompt);
        if (count != 0) {
            if (StringUtils.isNotEmpty(prompt.getFid())) {
                Attachment attachment = new Attachment();
                attachment.setBusinessNo(prompt.getId() + "");
                QueryWrapper<Attachment> wrapper = new QueryWrapper<>();
                wrapper.eq("id", prompt.getFid());
                attachmentMapper.update(attachment, wrapper);
            }
        }
    }
}
