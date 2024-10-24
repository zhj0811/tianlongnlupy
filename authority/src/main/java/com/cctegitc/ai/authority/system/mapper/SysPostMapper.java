package com.cctegitc.ai.authority.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cctegitc.ai.authority.common.core.domain.entity.SysPost;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 岗位信息 数据层
 *
 * 
 */
@Mapper
public interface SysPostMapper extends BaseMapper<SysPost> {
    /**
     * 查询岗位数据集合
     *
     * @param post 岗位信息
     * @return 岗位数据集合
     */
    public List<SysPost> selectPostList(SysPost post);

    /**
     * 查询所有岗位
     *
     * @return 岗位列表
     */
    public List<SysPost> selectPostAll();

    /**
     * 通过岗位ID查询岗位信息
     *
     * @param postId 岗位ID
     * @return 角色对象信息
     */
    public SysPost selectPostById(Long postId);

    /**
     * 根据用户ID获取岗位选择框列表
     *
     * @param userId 用户ID
     * @return 选中岗位ID列表
     */
    public List<Integer> selectPostListByUserId(Long userId);

    /**
     * 查询用户所属岗位组
     *
     * @param userName 用户名
     * @return 结果
     */
    public List<SysPost> selectPostsByUserName(String userName);

    /**
     * 删除岗位信息
     *
     * @param postId 岗位ID
     * @return 结果
     */
    public int deletePostById(Long postId);

    /**
     * 批量删除岗位信息
     *
     * @param postIds 需要删除的岗位ID
     * @return 结果
     */
    public int deletePostByIds(Long[] postIds);

    /**
     * 修改岗位信息
     *
     * @param post 岗位信息
     * @return 结果
     */
    public int updatePost(SysPost post);

    /**
     * 新增岗位信息
     *
     * @param post 岗位信息
     * @return 结果
     */
    public int insertPost(SysPost post);

    /**
     * 校验岗位名称
     *
     * @param postName 岗位名称
     * @return 结果
     */
    public SysPost checkPostNameUnique(String postName);

    /**
     * 校验岗位编码
     *
     * @param postCode 岗位编码
     * @return 结果
     */
    public SysPost checkPostCodeUnique(String postCode);
//-----------------以下是远程调用----------------------------------------------

    /**
     * @Description:查询所有岗位信息
     * @Author: yrc
     * @Date: 2022/1/25 11:07
     * @Param: []
     * @Return: java.util.List<com.cctegitc.authority.common.core.domain.entity.SysPost>
     * @Throws:
     */
    public List<SysPost> selectAllPost();

    /**
     * @Description:根据岗位ID查询岗位信息
     * @Author: yrc
     * @Date: 2022/1/26 9:59
     * @Param: [postId]
     * @Return: com.cctegitc.authority.common.core.domain.entity.SysPost
     * @Throws:
     */
    SysPost selectByPostId(Long postId);
}
