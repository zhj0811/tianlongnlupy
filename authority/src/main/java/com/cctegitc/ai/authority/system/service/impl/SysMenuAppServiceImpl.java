package com.cctegitc.ai.authority.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cctegitc.ai.authority.common.constant.Constants;
import com.cctegitc.ai.authority.common.constant.UserConstants;
import com.cctegitc.ai.authority.common.core.domain.TreeSelectApp;
import com.cctegitc.ai.authority.common.core.domain.entity.Prompt;
import com.cctegitc.ai.authority.common.core.domain.entity.SysMenuApp;
import com.cctegitc.ai.authority.common.core.domain.entity.SysRole;
import com.cctegitc.ai.authority.common.core.domain.entity.SysUser;
import com.cctegitc.ai.authority.common.utils.StringUtils;
import com.cctegitc.ai.authority.system.domain.vo.MetaVo;
import com.cctegitc.ai.authority.system.domain.vo.RouterVo;
import com.cctegitc.ai.authority.system.mapper.PromptMapper;
import com.cctegitc.ai.authority.system.mapper.SysMenuAppMapper;
import com.cctegitc.ai.authority.system.mapper.SysRoleMapper;
import com.cctegitc.ai.authority.system.mapper.SysRoleMenuAppMapper;
import com.cctegitc.ai.authority.system.service.ISysMenuAppService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 菜单 业务层处理
 *
 *
 */
@Service
public class SysMenuAppServiceImpl implements ISysMenuAppService {
    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    @Autowired
    private SysMenuAppMapper _menuMapper;

    @Autowired
    private SysRoleMapper _roleMapper;

    @Autowired
    private SysRoleMenuAppMapper _roleMenuMapper;

    @Autowired
    private PromptMapper promptMapper;

    /**
     * 根据用户查询系统菜单列表
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public List<SysMenuApp> selectMenuList(Long userId) {
        return selectMenuList(new SysMenuApp(), userId);
    }

    /**
     * 查询系统菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    @Override
    public List<SysMenuApp> selectMenuList(SysMenuApp menu, Long userId) {
        List<SysMenuApp> menuList = null;
        // 管理员显示所有菜单信息
        if (SysUser.isAdmin(userId)) {
            menuList = _menuMapper.selectMenuList(menu);
        } else {
            menu.getParams().put("userId", userId);
            menuList = _menuMapper.selectMenuListByUserId(menu);
        }
        if (CollectionUtils.isNotEmpty(menuList)) {
            for (SysMenuApp SysMenuApp : menuList) {
                if (null == SysMenuApp) {
                    continue;
                }
                if (null == SysMenuApp.getPerms()) {
                    SysMenuApp.setPerms("");
                }
            }
        }
        return menuList;
    }

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectMenuPerms(Long userId, String role) {
        List<String> perms = null;
        if (userId != null) {
            perms = _menuMapper.selectMenuPermsByUserId(userId);
        } else {
            perms = _menuMapper.selectMenuPermsByRole(role);
        }
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotEmpty(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户名称
     * @return 菜单列表
     */
    @Override
    public List<SysMenuApp> selectMenuTreeByUserId(Long userId) {
        Boolean isAdmin = false;
        List<SysRole> sysRoles = _roleMapper.roleListByUserId(userId);
        if (sysRoles == null) {
            return null;
        }
        for (int i = 0; i < sysRoles.size(); i++) {
            if ("sysadmin".equals(sysRoles.get(i).getRoleKey())) {
                isAdmin = true;
            }
        }
        List<SysMenuApp> menus = null;
        if (isAdmin) {
            menus = _menuMapper.selectMenuTreeAll();
        } else {
            menus = _menuMapper.selectMenuTreeByUserId(userId);
        }
        return getChildPerms(menus, 0);
    }

    @Override
    public List<SysMenuApp> selectMenuTreeByRole(String role) {
        List<SysMenuApp> menus = _menuMapper.selectMenuTreeByRole(role);
        return getChildPerms(menus, 0);
    }

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    @Override
    public List<Integer> selectMenuListByRoleId(Long roleId) {
        SysRole role = _roleMapper.selectRoleById(roleId);
        return _menuMapper.selectMenuListByRoleId(roleId, role.isMenuCheckStrictly());
    }

    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    @Override
    public List<RouterVo> buildMenus(List<SysMenuApp> menus) {
        List<RouterVo> routers = new LinkedList<RouterVo>();
        for (SysMenuApp menu : menus) {
            RouterVo router = new RouterVo();
            router.setHidden("1".equals(menu.getVisible()));
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setMeta(new MetaVo(menu.getMenuName(), menu.getFile(), StringUtils.equals("1", menu.getIsCache()), menu.getPath()));
            List<SysMenuApp> cMenus = menu.getChildren();
            if (!cMenus.isEmpty() && cMenus.size() > 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            } else if (isMenuFrame(menu)) {
                router.setMeta(null);
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(new MetaVo(menu.getMenuName(), menu.getFile(), StringUtils.equals("1", menu.getIsCache()), menu.getPath()));
                childrenList.add(children);
                router.setChildren(childrenList);
            } else if (menu.getParentId().intValue() == 0 && isInnerLink(menu)) {
                router.setMeta(null);
                router.setPath("/inner");
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                String routerPath = StringUtils.replaceEach(menu.getPath(), new String[]{Constants.HTTP, Constants.HTTPS}, new String[]{"", ""});
                children.setPath(routerPath);
                router.setFile(menu.getFile());
                children.setComponent(UserConstants.INNER_LINK);
                children.setName(StringUtils.capitalize(routerPath));
                children.setMeta(new MetaVo(menu.getMenuName(), menu.getFile(), menu.getPath()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */
    @Override
    public List<SysMenuApp> buildMenuTree(List<SysMenuApp> menus) {
        List<SysMenuApp> returnList = new ArrayList<SysMenuApp>();
        List<Long> tempList = new ArrayList<Long>();
        for (SysMenuApp dept : menus) {
            tempList.add(dept.getMenuId());
        }
        for (Iterator<SysMenuApp> iterator = menus.iterator(); iterator.hasNext(); ) {
            SysMenuApp menu = (SysMenuApp) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(menu.getParentId())) {
                recursionFn(menus, menu);
                returnList.add(menu);
            }
        }
        if (returnList.isEmpty()) {
            returnList = menus;
        }
        return returnList;
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param menus 菜单列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelectApp> buildMenuTreeSelect(List<SysMenuApp> menus) {
        List<SysMenuApp> menuTrees = buildMenuTree(menus);
        return menuTrees.stream().map(TreeSelectApp::new).collect(Collectors.toList());
    }

    /**
     * 根据菜单ID查询信息
     *
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    @Override
    public SysMenuApp selectMenuById(Long menuId) {
        return _menuMapper.selectMenuById(menuId);
    }

    /**
     * 是否存在菜单子节点
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public boolean hasChildByMenuId(Long menuId) {
        int result = _menuMapper.hasChildByMenuId(menuId);
        return result > 0 ? true : false;
    }

    /**
     * 查询菜单使用数量
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public boolean checkMenuExistRole(Long menuId) {
        int result = _roleMenuMapper.checkMenuExistRole(menuId);
        return result > 0 ? true : false;
    }

    /**
     * 新增保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public int insertMenu(SysMenuApp menu) {
        menu.setCreateTime(new Date());
        return _menuMapper.insertMenu(menu);
    }

    /**
     * 修改保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public int updateMenu(SysMenuApp menu) {
        menu.setUpdateTime(new Date());
        return _menuMapper.updateMenu(menu);
    }

    /**
     * 删除菜单管理信息
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public int deleteMenuById(Long menuId) {
        return _menuMapper.deleteMenuById(menuId);
    }

    /**
     * 校验菜单名称是否唯一
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public String checkMenuNameUnique(SysMenuApp menu) {
        Long menuId = StringUtils.isNull(menu.getMenuId()) ? -1L : menu.getMenuId();
        SysMenuApp info = _menuMapper.checkMenuNameUnique(menu.getMenuName(), menu.getParentId());
        if (StringUtils.isNotNull(info) && info.getMenuId().longValue() != menuId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public List<SysMenuApp> selectList(SysMenuApp menu, Long userId) {
        List<SysMenuApp> menuList = null;
        // 管理员显示所有菜单信息
        if (SysUser.isAdmin(userId)) {
            menuList = _menuMapper.selectList(menu);
        } else {
            menu.getParams().put("userId", userId);
            menuList = _menuMapper.selectList(menu);
        }
        for (SysMenuApp SysMenuApp : menuList) {
            if (null == SysMenuApp) {
                continue;
            }
            Prompt prompt = new Prompt();
            prompt.setPath(SysMenuApp.getPath());
            if (null == SysMenuApp.getPerms()) {
                SysMenuApp.setPerms("");
            }
            int count = promptMapper.selectCount(new QueryWrapper<Prompt>().eq("path", SysMenuApp.getPath()));
            if (count > 0) {
                continue;
            } else {
                promptMapper.insert(prompt);
            }
        }
        return menuList;
    }


    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    public String getRouteName(SysMenuApp menu) {
        String routerName = StringUtils.capitalize(menu.getPath());
        // 非外链并且是一级目录（类型为目录）
        if (isMenuFrame(menu)) {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(SysMenuApp menu) {
        String routerPath = menu.getPath();
        // 内链打开外网方式
        if (menu.getParentId().intValue() != 0 && isInnerLink(menu)) {
            routerPath = StringUtils.replaceEach(routerPath, new String[]{Constants.HTTP, Constants.HTTPS}, new String[]{"", ""});
        }
        // 非外链并且是一级目录（类型为目录）
        if (0 == menu.getParentId().intValue() && UserConstants.TYPE_DIR.equals(menu.getMenuType())
                && UserConstants.NO_FRAME.equals(menu.getIsFrame())) {
            routerPath = "/" + menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMenuFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public String getComponent(SysMenuApp menu) {
        String component = UserConstants.LAYOUT;
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMenuFrame(menu)) {
            component = menu.getComponent();
        } else if (StringUtils.isEmpty(menu.getComponent()) && menu.getParentId().intValue() != 0 && isInnerLink(menu)) {
            component = UserConstants.INNER_LINK;
        } else if (StringUtils.isEmpty(menu.getComponent()) && isParentView(menu)) {
            component = UserConstants.PARENT_VIEW;
        }
        return component;
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isMenuFrame(SysMenuApp menu) {
        return menu.getParentId().intValue() == 0 && UserConstants.TYPE_MENU.equals(menu.getMenuType())
                && menu.getIsFrame().equals(UserConstants.NO_FRAME);
    }

    /**
     * 是否为内链组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isInnerLink(SysMenuApp menu) {
        return menu.getIsFrame().equals(UserConstants.NO_FRAME) && StringUtils.ishttp(menu.getPath());
    }

    /**
     * 是否为parent_view组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isParentView(SysMenuApp menu) {
        return menu.getParentId().intValue() != 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType());
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<SysMenuApp> getChildPerms(List<SysMenuApp> list, int parentId) {
        List<SysMenuApp> returnList = new ArrayList<SysMenuApp>();
        for (Iterator<SysMenuApp> iterator = list.iterator(); iterator.hasNext(); ) {
            SysMenuApp t = (SysMenuApp) iterator.next();
            if (null == t.getPerms()) {
                t.setPerms("");
            }
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<SysMenuApp> list, SysMenuApp t) {
        // 得到子节点列表
        List<SysMenuApp> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenuApp tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysMenuApp> getChildList(List<SysMenuApp> list, SysMenuApp t) {
        List<SysMenuApp> tlist = new ArrayList<SysMenuApp>();
        Iterator<SysMenuApp> it = list.iterator();
        while (it.hasNext()) {
            SysMenuApp n = (SysMenuApp) it.next();
            if (n.getParentId().longValue() == t.getMenuId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenuApp> list, SysMenuApp t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}
