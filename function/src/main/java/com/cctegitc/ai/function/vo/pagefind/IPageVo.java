package com.cctegitc.ai.function.vo.pagefind;



import java.util.List;


public interface IPageVo {
    // 设置页码
    void setPage(Integer page);
    // 设置每页显示的数量
    void setLimit(Integer limit);
    // 设置总记录数
    void setTotal(Long total);
    // 设置items
    // 设置项
    void setItems(List<?> items);

    // 设置排序
    void setSort(String sort);

    // 设置状态
    void setStatus(Long status);
}
