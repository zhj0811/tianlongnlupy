package com.cctegitc.ai.authority.system.service.impl;

import com.cctegitc.ai.authority.common.annotation.DataScope;
import com.cctegitc.ai.authority.common.constant.Constants;
import com.cctegitc.ai.authority.common.constant.UserConstants;
import com.cctegitc.ai.authority.common.core.domain.UserTreeSelect;
import com.cctegitc.ai.authority.common.core.domain.entity.SysPost;
import com.cctegitc.ai.authority.common.core.domain.entity.SysRole;
import com.cctegitc.ai.authority.common.core.domain.entity.SysUser;
import com.cctegitc.ai.authority.common.core.domain.entity.SysUserPost;
import com.cctegitc.ai.authority.common.core.domain.entity.SysUserRole;
import com.cctegitc.ai.authority.common.exception.CustomException;
import com.cctegitc.ai.authority.common.matcher.RetryLimitHashedCredentialsMatcher;
import com.cctegitc.ai.authority.common.utils.StringUtils;
import com.cctegitc.ai.authority.common.utils.sign.JWTUtils;
import com.cctegitc.ai.authority.system.dto.UserDto;
import com.cctegitc.ai.authority.system.mapper.SysPostMapper;
import com.cctegitc.ai.authority.system.mapper.SysRoleMapper;
import com.cctegitc.ai.authority.system.mapper.SysUserMapper;
import com.cctegitc.ai.authority.system.mapper.SysUserPostMapper;
import com.cctegitc.ai.authority.system.mapper.SysUserRoleMapper;
import com.cctegitc.ai.authority.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * 用户 业务层处理
 *
 *
 */
@Service
public class SysUserServiceImpl implements ISysUserService {
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper _userMapper;

    @Autowired
    private SysRoleMapper _roleMapper;

    @Autowired
    private SysPostMapper _postMapper;

    @Autowired
    private SysUserRoleMapper _userRoleMapper;

    @Autowired
    private SysUserPostMapper _userPostMapper;

    @Autowired
    private RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher;

    @Override
    public List<SysUser> selectUserPhoneTreeSelect(String parent) {
        SysUser sysUser = new SysUser();
        sysUser.setDeptId(Long.parseLong(parent));
        return _userMapper.selectUserPhoneList(sysUser);
    }

    @Override
    public List<UserTreeSelect> userPhoneTreeSelect(List<SysUser> users) {
        List<UserTreeSelect> list = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            List<UserTreeSelect> list1 = new ArrayList<>();
            UserTreeSelect user = new UserTreeSelect();
            user.setLabel(users.get(i).getNickName());
            user.setValue(users.get(i).getUserId().toString());
            if (!"".equals(users.get(i).getPhoneNumber())) {
                UserTreeSelect user1 = new UserTreeSelect();
                user1.setLabel(users.get(i).getPhoneNumber());
                user1.setValue(users.get(i).getPhoneNumber());
                list1.add(user1);
            }
            if (!"".equals(users.get(i).getTel())) {
                UserTreeSelect user2 = new UserTreeSelect();
                user2.setLabel(users.get(i).getTel());
                user2.setValue(users.get(i).getTel());
                list1.add(user2);
            }
            user.setChildren(list1);
            list.add(user);
        }
        return list;
    }

    @Override
    public List<SysUser> selectUserByDeptId(long deptId) {
        return _userMapper.selectUserByDeptId(deptId);
    }

    /**
     * 根据条件分页查询用户列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<SysUser> selectUserList(SysUser user) {
        return _userMapper.selectUserList(user);
    }

    /**
     * 根据条件分页查询已分配用户角色列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<SysUser> selectAllocatedList(SysUser user) {
        return _userMapper.selectAllocatedList(user);
    }

    /**
     * 根据条件分页查询未分配用户角色列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<SysUser> selectUnallocatedList(SysUser user) {
        return _userMapper.selectUnallocatedList(user);
    }

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserByUserName(String userName) {
        return _userMapper.selectUserByUserName(userName);
    }

    /**
     * 通过用户名查询用户
     *
     * @param nickName 用户名
     * @return 用户对象信息
     */
    @Override
    public List<SysUser> selectUserByNickName(String nickName) {
        return _userMapper.selectUserByNickName(nickName);
    }

    @Override
    public SysUser selectUserByDB2UserName(String userName) {
        return _userMapper.selectUserByDB2UserName(userName);
    }

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserById(Long userId) {
        return _userMapper.selectUserById(userId);
    }

    /**
     * 查询用户所属角色组
     *
     * @param userName 用户名
     * @return 结果
     */
    @Override
    public String selectUserRoleGroup(String userName) {
        List<SysRole> list = _roleMapper.selectRolesByUserName(userName);
        StringBuffer idsStr = new StringBuffer();
        for (SysRole role : list) {
            idsStr.append(role.getRoleName()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString())) {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

    /**
     * 查询用户所属岗位组
     *
     * @param userName 用户名
     * @return 结果
     */
    @Override
    public String selectUserPostGroup(String userName) {
        List<SysPost> list = _postMapper.selectPostsByUserName(userName);
        StringBuffer idsStr = new StringBuffer();
        for (SysPost post : list) {
            idsStr.append(post.getPostName()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString())) {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param userName 用户名称
     * @return 结果
     */
    @Override
    public String checkUserNameUnique(String userName) {
        int count = _userMapper.checkUserNameUnique(userName);
        if (count > 0) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public String checkPhoneUnique(SysUser user) {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        SysUser info = _userMapper.checkPhoneUnique(user.getPhoneNumber());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public String checkEmailUnique(SysUser user) {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        SysUser info = _userMapper.checkEmailUnique(user.getEmail());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验用户是否允许操作
     *
     * @param user 用户信息
     */
    @Override
    public void checkUserAllowed(SysUser user) {
        if (StringUtils.isNotNull(user.getUserId()) && user.isAdmin()) {
            throw new CustomException("不允许操作超级管理员用户");
        }
    }

    /**
     * 新增保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertUser(SysUser user) {
        // 新增用户信息
        int rows = _userMapper.insertUser(user);
        // 新增用户岗位关联
        insertUserPost(user);
        // 新增用户与角色管理
        insertUserRole(user);
        return rows;
    }

    /**
     * 修改保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateUser(SysUser user) {
//        Long userId = user.getUserId();
//        // 删除用户与角色关联
//        _userRoleMapper.deleteUserRoleByUserId(userId);
//        // 新增用户与角色管理
//        insertUserRole(user);
//        // 删除用户与岗位关联
//        _userPostMapper.deleteUserPostByUserId(userId);
//        // 新增用户与岗位管理
//        insertUserPost(user);
        return _userMapper.updateUser(user);
    }

    /**
     * 用户授权角色
     *
     * @param userId  用户ID
     * @param roleIds 角色组
     */
    @Override
    @Transactional
    public void insertUserAuth(Long userId, Long[] roleIds) {
        _userRoleMapper.deleteUserRoleByUserId(userId);
        insertUserRole(userId, roleIds);
    }

    /**
     * 修改用户状态
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserStatus(SysUser user) {
        retryLimitHashedCredentialsMatcher.unlockAccount(user.getUserName());
        return _userMapper.updateUser(user);
    }

    /**
     * 修改用户基本信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserProfile(SysUser user) {
        return _userMapper.updateUser(user);
    }

    /**
     * 修改用户头像
     *
     * @param userName 用户名
     * @param avatar   头像地址
     * @return 结果
     */
    @Override
    public boolean updateUserAvatar(String userName, String avatar) {
        return _userMapper.updateUserAvatar(userName, avatar) > 0;
    }

    /**
     * 重置用户密码
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int resetPwd(SysUser user) {
        return _userMapper.updateUser(user);
    }

    /**
     * 重置用户密码
     *
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    @Override
    public int resetUserPwd(String userName, String password) {
        return _userMapper.resetUserPwd(userName, password);
    }

    /**
     * 新增用户角色信息
     *
     * @param user 用户对象
     */
    public void insertUserRole(SysUser user) {
        Long[] roles = user.getRoleIds();
        if (StringUtils.isNotNull(roles)) {
            // 新增用户与角色管理
            List<SysUserRole> list = new ArrayList<SysUserRole>();
            for (Long roleId : roles) {
                SysUserRole ur = new SysUserRole();
                ur.setUserId(user.getUserId());
                ur.setRoleId(roleId);
                list.add(ur);
            }
            if (list.size() > 0) {
                _userRoleMapper.batchUserRole(list);
            }
        }
    }

    /**
     * 新增用户岗位信息
     *
     * @param user 用户对象
     */
    public void insertUserPost(SysUser user) {
        Long[] posts = user.getPostIds();
        if (StringUtils.isNotNull(posts)) {
            // 新增用户与岗位管理
            List<SysUserPost> list = new ArrayList<SysUserPost>();
            for (Long postId : posts) {
                SysUserPost up = new SysUserPost();
                up.setUserId(user.getUserId());
                up.setPostId(postId);
                list.add(up);
            }
            if (list.size() > 0) {
                _userPostMapper.batchUserPost(list);
            }
        }
    }

    /**
     * 新增用户角色信息
     *
     * @param userId  用户ID
     * @param roleIds 角色组
     */
    public void insertUserRole(Long userId, Long[] roleIds) {
        if (StringUtils.isNotNull(roleIds)) {
            // 新增用户与角色管理
            List<SysUserRole> list = new ArrayList<SysUserRole>();
            for (Long roleId : roleIds) {
                SysUserRole ur = new SysUserRole();
                ur.setUserId(userId);
                ur.setRoleId(roleId);
                list.add(ur);
            }
            if (list.size() > 0) {
                _userRoleMapper.batchUserRole(list);
            }
        }
    }

    /**
     * 通过用户ID删除用户
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteUserById(Long userId) {
        // 删除用户与角色关联
        _userRoleMapper.deleteUserRoleByUserId(userId);
        // 删除用户与岗位表
        _userPostMapper.deleteUserPostByUserId(userId);
        return _userMapper.deleteUserById(userId);
    }

    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的用户ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteUserByIds(Long[] userIds) {
        for (Long userId : userIds) {
            checkUserAllowed(new SysUser(userId));
        }
        // 删除用户与角色关联
        _userRoleMapper.deleteUserRole(userIds);
        // 删除用户与岗位关联
        _userPostMapper.deleteUserPost(userIds);
        return _userMapper.deleteUserByIds(userIds);
    }

    @Override
    public String importUser(List<SysUser> userList, Boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(userList) || userList.size() == 0) {
            throw new CustomException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String password = DigestUtils.md5DigestAsHex(Constants.password.getBytes());
        for (SysUser user : userList) {
            try {
                // 验证是否存在这个用户
                SysUser u = _userMapper.selectUserByUserName(user.getUserName());
                if (StringUtils.isNull(u)) {
                    user.setPassword(password);
                    user.setCreateBy(operName);
                    this.insertUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getUserName() + " 导入成功");
                } else if (isUpdateSupport) {
                    user.setUpdateBy(operName);
                    this.updateUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getUserName() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + user.getUserName() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + user.getUserName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Override
    public List<String> findUsernamesByRole(String roleId) {

        return _userRoleMapper.findUsernamesByRole(roleId);
    }

    @Override
    public List<String> selectRolesByName(String username) {

        return _userRoleMapper.selectRolesByName(username);
    }

    @Override
    public Map<String, Object> selectDeptByUserName(String username) {

        return _userMapper.selectDeptByUserName(username);
    }

    @Override
    public Long getUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        String username = JWTUtils.getUsername(token);
        SysUser sysUser = _userMapper.selectUserByUserName(username);
        return sysUser.getUserId();
    }

    @Override
    public List<SysUser> getUserListByPostId(Long postId) {
        List<SysUser> sysUsers = _userMapper.selectUserByPostId(postId);
        return sysUsers;
    }

    @Override
    public Map<String, String> getUserNameMapping() {
        List<SysUser> sysUsers = _userMapper.getUserNameMapping();
        Map<String, String> result = new HashMap<>();
        sysUsers.forEach(sysUser -> result.put(sysUser.getUserName(), sysUser.getNickName()));
        return result;
    }

//    @Override
//    public List<String> selectUserListByEmpNumber(List<String> list) {
//        return null;
//    }

//-----------------以下是远程调用----------------------------------------------

    /**
     * @Description:
     * @Author: renyi
     * @Date: 2022/3/11 9:41
     * @Param: [list]
     * @Return: java.util.List<java.lang.String>
     * @Throws:
     */
//    @Override
//    public List<String> selectUserListByEmpNumber(List<String> list) {
//        List<String> list1 = new ArrayList<>();
//        for (String s : list) {
//            SysUser sysUser =_userMapper.selectUserListByEmpNumber(s);
//            list1.add(sysUser.getUserName());
//        }
//        return list1;
//    }
    @Override
    public Long selectUserIdByUserName(String username) {
        SysUser sysUser = _userMapper.selectEmpNumberByUserName(username);
        return sysUser.getUserId();
    }


    @Override
    public Long selectUserIdByEmpNumber(String empNumber) {
        SysUser sysUser = _userMapper.selectUserIdByEmpNumber(empNumber);
        return sysUser.getUserId();
    }

//-----------------以下是远程调用----------------------------------------------

    /**
     * @Description:根据岗位ID查询用户
     * @Author: yrc
     * @Date: 2022/1/25 14:59
     * @Param: [postId]
     * @Return: java.util.List<com.cctegitc.authority.common.core.domain.entity.SysUser>
     * @Throws:
     */
    @Override
    public List<SysUser> selectByPostId(Long postId) {
        List<SysUser> sysUserList = _userMapper.selectByPostId(postId);
        return sysUserList;
    }

    /**
     * @Description:根据用户ID查询用户
     * @Author: yrc
     * @Date: 2022/1/26 9:44
     * @Param: [userId]
     * @Return: com.cctegitc.authority.common.core.domain.entity.SysUser
     * @Throws:
     */
    @Override
    public SysUser selectByUserId(Long userId) {
        SysUser sysUser = _userMapper.selectByUserId(userId);
        return sysUser;
    }

    @Override
    public SysUser selectByEmpNumber(String empNumber) {
        SysUser sysUser = _userMapper.selectByEmpNumber(empNumber);
        return sysUser;
    }

    @Override
    public List<UserDto> selectAllUser() {
        List<UserDto> userDtoList = _userMapper.selectAllUser();
        return userDtoList;
    }

    /**
     * @Description:
     * @Author: jiangyang
     * @Date: 2022/3/11 9:35
     * @Param: [keyword]
     * @Return: java.util.List<com.cctegitc.authority.common.core.domain.entity.SysUser>
     * @Throws:
     */
    @Override
    public List<SysUser> searchUsers(String keyword) {
        return _userMapper.searchUser(keyword);
    }

    @Override
    public List<SysUser> getUserListByRoleKey(String roleKey) {
        return _userMapper.getUserListByRoleKey(roleKey);
    }

    @Override
    public SysUser selectUserByNickNames(String leader, Long deptId) {
        return _userMapper.selectUserByNickNames(leader, deptId);
    }

    @Override
    @Transactional
    public int insertOpenId(String phoneNumber, String openId) {
        return _userMapper.insertOpenId(phoneNumber, openId);
    }

    @Override
    public SysUser selectUserByOpenId(String openId) {
        return _userMapper.selectUserByOpenId(openId);
    }

    @Override
    public SysUser MiniDataLogin(SysUser user) {
        return _userMapper.MiniDataLogin(user);
    }

    @Override
    public int updateOpenId(SysUser user) {
        return _userMapper.updateOpenId(user);
    }

    @Override
    public int miniLoginOut(String userName) {
        return _userMapper.miniLoginOut(userName);
    }

    @Override
    public int getAllUserCount() {
        return _userMapper.getAllUserCount();
    }

}
