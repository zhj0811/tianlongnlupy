package com.cctegitc.ai.function.vo.pagefind;

import com.cctegitc.ai.function.scheduleTask.domain.SysJobLog;
import lombok.Data;

import java.util.List;

@Data
public class SysJobLogPageVO implements IPageVo {
    private List<SysJobLog> items;
    private Integer page;
    private Integer limit;
    private String sort;
    private Long total;
    private Long status;

    @Override
    public void setItems(List<?> items) {
        this.items = (List<SysJobLog>) items;
    }
}
