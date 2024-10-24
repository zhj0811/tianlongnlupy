package com.cctegitc.ai.function.vo.pagefind;

import com.cctegitc.ai.function.db.pojo.VideoLibrary;
import lombok.Data;

import java.util.List;

@Data
public class VideoLibraryVo {

    private Integer page;
    private Integer limit;
    private String sort;
    private Long total;
    private Long status;
    private List<VideoLibrary> items;
}
