package com.cctegitc.ai.function.vo.pagefind;

import com.cctegitc.ai.function.db.pojo.Notification;
import lombok.Data;

import java.util.List;

@Data
public class NotificationPageVO implements IPageVo {
    private List<Notification> items;
    private Integer page;
    private Integer limit;
    private String sort;
    private Long total;
    private Long status;

    @Override
    public void setItems(List<?> items) {
        this.items = (List<Notification>) items;
    }
}
