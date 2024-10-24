package com.cctegitc.ai.function.vo.pagefind;

import com.cctegitc.ai.function.db.pojo.IntelligenceRecord;
import lombok.Data;

import java.util.List;


@Data
public class IntelligenceRecordVo {

    private Integer page;
    private Integer limit;
    private String sort;
    private Long total;
    private Long status;
    private List<IntelligenceRecord> items;
}