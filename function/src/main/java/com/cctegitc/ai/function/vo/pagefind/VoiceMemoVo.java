package com.cctegitc.ai.function.vo.pagefind;

import com.cctegitc.ai.function.db.pojo.Memo;

import com.cctegitc.ai.function.scheduleTask.domain.SysJob;
import lombok.Data;

import java.util.List;


@Data
public class VoiceMemoVo implements IPageVo {
    private List<Memo> items;
    private Integer page;
    private Integer limit;
    private String sort;
    private Long total;
    private Long status;

    @Override
    public void setItems(List<?> items) {
        this.items = (List<Memo>) items;
    }
}
