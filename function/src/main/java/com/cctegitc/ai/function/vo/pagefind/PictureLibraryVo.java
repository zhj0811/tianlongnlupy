package com.cctegitc.ai.function.vo.pagefind;

import com.cctegitc.ai.function.db.pojo.PictureLibrary;
import lombok.Data;

import java.util.List;

@Data
public class PictureLibraryVo {

    private Integer page;
    private Integer limit;
    private String sort;
    private Long total;
    private Long status;
    private List<PictureLibrary> items;
}
