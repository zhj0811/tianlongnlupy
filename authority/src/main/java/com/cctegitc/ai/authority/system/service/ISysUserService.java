package com.cctegitc.ai.authority.system.service;

import com.cctegitc.ai.authority.common.core.domain.UserTreeSelect;
import com.cctegitc.ai.authority.common.core.domain.entity.SysUser;
import com.cctegitc.ai.authority.system.dto.UserDto;

import java.util.List;
import java.util.Map;

/**
 * 用户 业务层
 *
 * 
 */
public interface ISysUserService {
    List<SysUser> selectUserPhoneTreeSelect(String parent);

    List<UserTreeSelect> userPhoneTreeSelect(List<SysUser> users);

    public List<SysUser> selectUserByDeptId(long deptId);

    /**
     * 根据条件分页查询用户列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<SysUser> selectUserList(SysUser user);

    /**
     * 根据条件分页查询已分配用户角色列表
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
     * @param nickName 用户名
     * @return 用户对象信息
     */
    public List<SysUser> selectUserByNickName(String nickName);

    public SysUser selectUserByDB2UserName(String userName);

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    public SysUser selectUserById(Long userId);

    /**
     * 根据用户ID查询用户所属角色组
     *
     * @param userName 用户名
     * @return 结果
     */
    public String selectUserRoleGroup(String userName);

    /**
     * 根据用户ID查询用户所属岗位组
     *
     * @param userName 用户名
     * @return 结果
     */
    public String selectUserPostGroup(String userName);

    /**
     * 校验用户名称是否唯一
     *
     * @param userName 用户名称
     * @return 结果
     */
    public String checkUserNameUnique(String userName);

    /**
     * 校验手机号码是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    public String checkPhoneUnique(SysUser user);

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    public String checkEmailUnique(SysUser user);

    /**
     * 校验用户是否允许操作
     *
     * @param user 用户信息
     */
    public void checkUserAllowed(SysUser user);

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
     * 用户授权角色
     *
     * @param userId  用户ID
     * @param roleIds 角色组
     */
    public void insertUserAuth(Long userId, Long[] roleIds);

    /**
     * 修改用户状态
     *
     * @param user 用户信息
     * @return 结果
     */
    public int updateUserStatus(SysUser user);

    /**
     * 修改用户基本信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public int updateUserProfile(SysUser user);

    /**
     * 修改用户头像
     *
     * @param userName 用户名
     * @param avatar   头像地址
     * @return 结果
     */
    public boolean updateUserAvatar(String userName, String avatar);

    /**
     * 重置用户密码
     *
     * @param user 用户信息
     * @return 结果
     */
    public int resetPwd(SysUser user);

    /**
     * 重置用户密码
     *
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    public int resetUserPwd(String userName, String password);

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
     * 导入用户数据
     *
     * @param userList        用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName        操作用户
     * @return 结果
     */
    public String importUser(List<SysUser> userList, Boolean isUpdateSupport, String operName);


    public List<String> findUsernamesByRole(String role);

    public List<String> selectRolesByName(String username);

    public Map<String, Object> selectDeptByUserName(String username);

    /**
     * 获取当前用户ID
     *
     * @return
     */
    Long getUserId();

    /**
     * 根据岗位id获取该岗位下用户列表
     *
     * @param postId 岗位id
     * @return List<SysUser>
     * @author jiangyang
     * @date 2021/12/31 18:33
     */
    List<SysUser> getUserListByPostId(Long postId);

    /**
     * username和nickName映射关系表
     *
     * @return Map<String, String>
     * @author jiangyang
     * @date 2021/12/31 18:33
     */
    Map<String, String> getUserNameMapping();


    //-----------------以下是远程调用----------------------------------------------

    /**
     * @Description:
     * @Author: renyi
     * @Date: 2022/3/11 9:30
     * @Param: [list]
     * @Return: java.util.List<java.lang.String>
     * @Throws:
     */
//    List<String> selectUserListByEmpNumber(List<String> list);

    Long selectUserIdByUserName(String username);


    Long selectUserIdByEmpNumber(String empNumber);


    /**
     * @Description:根据岗位ID查询用户
     * @Author: yrc
     * @Date: 2022/1/25 13:45
     * @Param: [byPostId]
     * @Return: java.util.List<com.cctegitc.authority.common.core.domain.entity.SysUser>
     * @Throws:
     */
    List<SysUser> selectByPostId(Long postId);

    /**
     * @Description:根据用户ID查询用户
     * @Author: yrc
     * @Date: 2022/1/26 9:42
     * @Param: [userId]
     * @Return: com.cctegitc.authority.common.core.domain.entity.SysUser
     * @Throws:
     */
    SysUser selectByUserId(Long userId);

    /**
     * @Description:根据用户EmpNumber查询用户
     * @Author: yrc
     * @Date: 2022/3/11 8:57
     * @Param: [empNumber]
     * @Return: com.cctegitc.authority.common.core.domain.entity.SysUser
     * @Throws:
     */
    SysUser selectByEmpNumber(String empNumber);

    /**
     * @Description:查询所有用户信息
     * @Author: yrc
     * @Date: 2022/3/11 8:58
     * @Param: []
     * @Return: java.util.List<com.cctegitc.authority.system.dto.UserDto>
     * @Throws:
     */
    List<UserDto> selectAllUser();

    /**
     * 根据用户名关键字模糊查询用户列表
     *
     * @param keyword
     * @return List<SysPost>
     * @author jiangyang
     * @date 2022-02-22
     */
    List<SysUser> searchUsers(String keyword);

    /**
     * 根据roleKey获取传入角色下的用户列表
     *
     * @param roleKey
     * @return List<SysUser>
     * @author jiangyang
     * @date 2022-02-22
     */
    List<SysUser> getUserListByRoleKey(String roleKey);

    SysUser selectUserByNickNames(String leader, Long deptId);

    int insertOpenId(String phoneNumber, String openId);

    SysUser selectUserByOpenId(String openId);

    SysUser MiniDataLogin(SysUser user);

    int updateOpenId(SysUser user);

    int miniLoginOut(String userName);

    int getAllUserCount();
//    Long selectUserIdByUserName(String username);
//
//    String selectEmpNumberByUserName(String username);
//
//    Long selectUserIdByEmpNumber(String empNumber);
}
