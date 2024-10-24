package com.cctegitc.ai.authority.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cctegitc.ai.authority.common.core.domain.entity.SysUser;
import com.cctegitc.ai.authority.system.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户表 数据层
 *
 *
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUser> selectUserPhoneList(SysUser sysUser);

    /**
     * 根据条件分页查询用户列表
     *
     * @param sysUser 用户信息
     * @return 用户信息集合信息
     */
    public List<SysUser> selectUserList(SysUser sysUser);

    /**
     * 根据条件分页查询未已配用户角色列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<SysUser> selectAllocatedList(SysUser user);

    /**
     * 根据条件分页查询未分配用户角色列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<SysUser> selectUnallocatedList(SysUser user);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    public SysUser selectUserByUserName(String userName);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    public List<SysUser> selectUserByNickName(String userName);

    public SysUser selectUserByDB2UserName(String userName);

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    public SysUser selectUserById(Long userId);

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public int insertUser(SysUser user);

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public int updateUser(SysUser user);

    /**
     * 修改用户头像
     *
     * @param userName 用户名
     * @param avatar   头像地址
     * @return 结果
     */
    public int updateUserAvatar(@Param("userName") String userName, @Param("avatar") String avatar);

    /**
     * 重置用户密码
     *
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    public int resetUserPwd(@Param("userName") String userName, @Param("password") String password);

    /**
     * 通过用户ID删除用户
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteUserById(Long userId);

    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的用户ID
     * @return 结果
     */
    public int deleteUserByIds(Long[] userIds);

    /**
     * 校验用户名称是否唯一
     *
     * @param userName 用户名称
     * @return 结果
     */
    public int checkUserNameUnique(String userName);

    /**
     * 校验手机号码是否唯一
     *
     * @param phonenumber 手机号码
     * @return 结果
     */
    public SysUser checkPhoneUnique(String phonenumber);

    /**
     * 校验email是否唯一
     *
     * @param email 用户邮箱
     * @return 结果
     */
    public SysUser checkEmailUnique(String email);


    Map<String, Object> selectDeptByUserName(String username);

    /**
     * 根据postId查询该岗位下的人员列表
     *
     * @param postId 岗位id
     * @return List<SysUser> usr列表
     * @author jiangyang
     * @date 2021/12/30 14:59
     */
    public List<SysUser> selectUserByPostId(@Param("postId") Long postId);

    /**
     * 查询username和nickName映射关系
     *
     * @return List<SysUser> usr列表
     * @author jiangyang
     */
    public List<SysUser> getUserNameMapping();

//-----------------以下是远程调用----------------------------------------------

    /**
     * @Description:
     * @Author: renyi
     * @Date: 2022/3/11 9:42
     * @Param: [emp_number]
     * @Return: com.cctegitc.authority.common.core.domain.entity.SysUser
     * @Throws:
     */
//    SysUser selectUserListByEmpNumber(String emp_number);

    SysUser selectEmpNumberByUserName(String username);

    SysUser selectUserIdByEmpNumber(String empNumber);


    /**
     * @Description:根据岗位ID查询用户
     * @Author: yrc
     * @Date: 2022/1/25 13:53
     * @Param: [byPostId]
     * @Return: java.util.List<com.cctegitc.authority.common.core.domain.entity.SysUser>
     * @Throws:
     */
    List<SysUser> selectByPostId(@Param("postId") Long postId);

    /**
     * @Description:根据用户ID查询用户
     * @Author: yrc
     * @Date: 2022/1/26 9:44
     * @Param: [userId]
     * @Return: com.cctegitc.authority.common.core.domain.entity.SysUser
     * @Throws:
     */
    SysUser selectByUserId(@Param("userId") Long userId);

    SysUser selectByEmpNumber(@Param("empNumber") String empNumber);

    /**
     * 根据用户名或用户中文名模糊搜索用户
     *
     * @param keyword
     * @return List<SysUser>
     * @author jiangyang
     * @date 2022-02-22
     */
    List<SysUser> searchUser(@Param("keyword") String keyword);

    /**
     * 根据roleKey查询所属角色下用户列表
     *
     * @param roleKey
     * @return List<SysUser>
     * @author jiangyang
     * @date 2022-02-22
     */
    List<SysUser> getUserListByRoleKey(@Param("roleKey") String roleKey);

    List<UserDto> selectAllUser();

    List<SysUser> selectUserByDeptId(long deptId);

    SysUser selectUserByNickNames(String leader, Long deptId);

    int insertOpenId(String phoneNumber, String openId);

    SysUser selectUserByOpenId(String openId);

    SysUser MiniDataLogin(SysUser user);

    int updateOpenId(SysUser user);

    int miniLoginOut(String userName);

    int getAllUserCount();
}
