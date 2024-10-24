package com.cctegitc.ai.authority.common.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class DataPage<T> {
    private List<T> items;
    private Long limit;
    private Long page;
    private Long total;

    public DataPage(Page<T> page) {
        this.items = page.getRecords();
        this.limit = page.getSize();
        this.page = page.getCurrent();
        this.total = page.getTotal();

    }
}
