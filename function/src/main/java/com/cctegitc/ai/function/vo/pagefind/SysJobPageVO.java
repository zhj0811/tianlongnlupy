package com.cctegitc.ai.function.vo.pagefind;

import com.cctegitc.ai.function.scheduleTask.domain.SysJob;
import lombok.Data;

import java.util.List;

@Data
public class SysJobPageVO implements IPageVo {
    private List<SysJob> items;
    private Integer page;
    private Integer limit;
    private String sort;
    private Long total;
    private Long status;

    @Override
    public void setItems(List<?> items) {
        this.items = (List<SysJob>) items;
    }
}
