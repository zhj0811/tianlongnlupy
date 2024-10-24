package com.cctegitc.ai.authority.common.core.domain;

import com.cctegitc.ai.authority.common.core.domain.entity.SysDept;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class DeptTreeSelect implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 节点ID
     */
    private Long value;

    /**
     * 节点名称
     */
    private String label;

    /**
     * 子节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<DeptTreeSelect> children;

    public DeptTreeSelect() {

    }

    public DeptTreeSelect(SysDept dept) {
        this.value = dept.getDeptId();
        this.label = dept.getDeptName();
        this.children = dept.getChildren().stream().map(DeptTreeSelect::new).collect(Collectors.toList());
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<DeptTreeSelect> getChildren() {
        return children;
    }

    public void setChildren(List<DeptTreeSelect> children) {
        this.children = children;
    }
}
