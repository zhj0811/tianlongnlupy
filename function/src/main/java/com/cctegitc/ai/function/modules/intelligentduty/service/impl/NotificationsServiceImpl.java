package com.cctegitc.ai.function.modules.intelligentduty.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cctegitc.ai.function.db.dao.NotificationsMapper;
import com.cctegitc.ai.function.db.pojo.Notification;
import com.cctegitc.ai.function.modules.intelligentduty.service.INotificationsService;
import com.cctegitc.ai.function.util.FindByPageHelper;
import com.cctegitc.ai.function.vo.pagefind.NotificationPageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NotificationsServiceImpl implements INotificationsService {

    @Resource
    private NotificationsMapper notificationsMapper;

    /**
     * 查看所有语音通知信息
     *
     * @return
     */
    @Override
    public List<Notification> findAll() {
        return notificationsMapper.selectList(new QueryWrapper<>());
    }

    /**
     * 分页查看所有外呼信息
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public NotificationPageVO findByPage(Integer page, Integer limit) {
        return FindByPageHelper.findByPage(NotificationPageVO.class, new QueryWrapper<Notification>(), page, limit, notificationsMapper);
    }

    /**
     * 添加外呼
     *
     * @param notification
     * @return
     */
    @Override
    public int add(Notification notification) {
        return notificationsMapper.insert(notification);
    }

    /**
     * 更新外呼信息
     *
     * @param notification
     * @return
     */
    @Override
    public int updateData(Notification notification) {
        return notificationsMapper.updateById(notification);
    }

    /**
     * 删除当前外呼信息
     *
     * @param id
     * @return
     */
    @Override
    public int delete(Long id) {
        return notificationsMapper.deleteById(id);
    }

    /**
     * 通过id查询外呼信息
     *
     * @param id
     * @return
     */
    @Override
    public Notification findById(Long id) {
        return notificationsMapper.selectById(id);
    }

}
