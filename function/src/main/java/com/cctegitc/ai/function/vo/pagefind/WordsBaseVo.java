package com.cctegitc.ai.function.vo.pagefind;

import com.cctegitc.ai.function.db.pojo.WordsBase;
import lombok.Data;

import java.util.List;

@Data
public class WordsBaseVo {

    private Integer page;
    private Integer limit;
    private String sort;
    private Long total;
    private Long status;
    private List<WordsBase> items;
}
