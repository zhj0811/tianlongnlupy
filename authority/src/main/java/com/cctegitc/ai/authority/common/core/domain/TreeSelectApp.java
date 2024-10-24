package com.cctegitc.ai.authority.common.core.domain;

import com.cctegitc.ai.authority.common.core.domain.entity.SysDept;
import com.cctegitc.ai.authority.common.core.domain.entity.SysMenuApp;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Treeselect树结构实体类
 *
 *
 */
public class TreeSelectApp implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 节点ID
     */
    private Long id;

    /**
     * 节点名称
     */
    private String label;

    /**
     * 子节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeSelectApp> children;

    public TreeSelectApp() {

    }

    public TreeSelectApp(SysDept dept) {
        this.id = dept.getDeptId();
        this.label = dept.getDeptName();
        this.children = dept.getChildren().stream().map(TreeSelectApp::new).collect(Collectors.toList());
    }

    public TreeSelectApp(SysMenuApp menu) {

        this.id = menu.getMenuId();
        this.label = menu.getMenuName();
        this.children = menu.getChildren().stream().map(TreeSelectApp::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<TreeSelectApp> getChildren() {
        return children;
    }

    public void setChildren(List<TreeSelectApp> children) {
        this.children = children;
    }
}