package com.cctegitc.ai.function.vo.pagefind;

import com.cctegitc.ai.function.db.pojo.StatisticalChart;
import lombok.Data;

import java.util.List;

@Data
public class StatisticalChartVo {

    private Integer page;  //页码
    private Integer limit;  //分页大小
    private String sort;
    private Long total;  //查询条数
    private Long status;
    private List<StatisticalChart> items;  //需要分页的对象
}
