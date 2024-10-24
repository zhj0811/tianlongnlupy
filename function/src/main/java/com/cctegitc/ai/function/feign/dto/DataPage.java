package com.cctegitc.ai.function.feign.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 名称
 *
 * @FileName SystemIntroductionVo
 * @Author TianCheng
 * @Date 2021/7/6 16:39
 * @Modifier
 * @ModifiedDate
 * @Description 功能描述
 * @See
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DataPage<T> implements Serializable {
    private List<T> items;
    private long limit;
    private long page;
    private Long total;

    public DataPage(Page<T> page) {
        this.items = page.getRecords();
        this.limit = page.getSize();
        this.page = page.getCurrent();
        this.total = page.getTotal();

    }
}
