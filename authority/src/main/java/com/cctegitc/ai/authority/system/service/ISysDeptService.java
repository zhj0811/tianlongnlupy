package com.cctegitc.ai.authority.system.service;

import com.cctegitc.ai.authority.common.core.domain.DeptTreeSelect;
import com.cctegitc.ai.authority.common.core.domain.TreeSelect;
import com.cctegitc.ai.authority.common.core.domain.entity.SysDept;

import java.util.List;

/**
 * 部门管理 服务层
 *
 *
 */
public interface ISysDeptService {
    /**
     * 构建前端所需要下拉树结构
     *
     * @param depts 部门列表
     * @return 下拉树结构列表
     */
    public List<DeptTreeSelect> wBuildDeptTreeSelect(List<SysDept> depts);

    /**
     * 查询部门管理数据
     *
     * @param parent 部门信息
     * @return 部门信息集合
     */
    public List<SysDept> selectDeptListByParent(String parent);

    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息
     * @return 部门信息集合
     */
    public List<SysDept> selectDeptList(SysDept dept);

    /**
     * 构建前端所需要树结构
     *
     * @param depts 部门列表
     * @return 树结构列表
     */
    public List<SysDept> buildDeptTree(List<SysDept> depts);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param depts 部门列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildDeptTreeSelect(List<SysDept> depts);

    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     * @return 选中部门列表
     */
    public List<Integer> selectDeptListByRoleId(Long roleId);

    /**
     * 根据部门ID查询信息
     *
     * @param deptId 部门ID
     * @return 部门信息
     */
    public SysDept selectDeptById(Long deptId);

    /**
     * 根据ID查询所有子部门（正常状态）
     *
     * @param deptId 部门ID
     * @return 子部门数
     */
    public int selectNormalChildrenDeptById(Long deptId);

    /**
     * 是否存在部门子节点
     *
     * @param deptId 部门ID
     * @return 结果
     */
    public boolean hasChildByDeptId(Long deptId);

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkDeptExistUser(Long deptId);

    /**
     * 校验部门名称是否唯一
     *
     * @param dept 部门信息
     * @return 结果
     */
    public String checkDeptNameUnique(SysDept dept);

    /**
     * 新增保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    public int insertDept(SysDept dept);

    /**
     * 修改保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    public int updateDept(SysDept dept);

    /**
     * 删除部门管理信息
     *
     * @param deptId 部门ID
     * @return 结果
     */
    public int deleteDeptById(Long deptId);

    List<SysDept> selectDeptList1(SysDept dept);

//-----------------以下是远程调用----------------------------------------------

    /**
     * @Description:查询所有部门信息
     * @Author: yrc
     * @Date: 2022/2/24 14:03
     * @Param: []
     * @Return: java.util.List<com.cctegitc.authority.common.core.domain.entity.SysDept>
     * @Throws:
     */
    List<SysDept> selectAllDept();

    /**
     * @Description:根据部门ID查询部门信息
     * @Author: yrc
     * @Date: 2022/2/25 16:33
     * @Param: [deptId]
     * @Return: com.cctegitc.authority.common.core.domain.entity.SysDept
     * @Throws:
     */
    SysDept selectByDeptId(Long deptId);
}
