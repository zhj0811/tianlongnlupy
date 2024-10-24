package com.cctegitc.ai.function.modules.intelligentduty.service;

import com.cctegitc.ai.function.db.pojo.Notification;
import com.cctegitc.ai.function.db.pojo.OutCallnotifications;
import com.cctegitc.ai.function.vo.pagefind.NotificationPageVO;

import java.util.List;

public interface INotificationsService {
    List<Notification> findAll();

    NotificationPageVO findByPage(Integer page, Integer limit);

    int add(Notification notification);

    int updateData(Notification notification);

    int delete(Long id);

    Notification findById(Long id);
}
