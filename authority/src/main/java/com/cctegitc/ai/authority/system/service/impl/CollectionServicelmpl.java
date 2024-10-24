package com.cctegitc.ai.authority.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cctegitc.ai.authority.common.core.domain.entity.Collection;
import com.cctegitc.ai.authority.common.core.domain.entity.SysMenu;
import com.cctegitc.ai.authority.system.domain.vo.CollectionVo;
import com.cctegitc.ai.authority.system.mapper.CollectionMapper;
import com.cctegitc.ai.authority.system.mapper.PromptMapper;
import com.cctegitc.ai.authority.system.service.CollectionService;
import com.cctegitc.ai.authority.system.service.ISysMenuService;
import com.cctegitc.ai.authority.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServicelmpl implements CollectionService {
    @Autowired
    private CollectionMapper collectionMapper;

    @Autowired
    private PromptMapper promptMapper;

    @Autowired
    private SysUserServiceImpl iSysUserService;

    @Autowired
    private SysMenuServiceImpl menuService;

    @Override
    public CollectionVo findByPage(CollectionVo collectionListVo) {
        CollectionVo collection = new CollectionVo();
        Page<Collection> page = new Page<>(collectionListVo.getPage(), collectionListVo.getLimit());
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        if ("-id".equals(collectionListVo.getSort())) {
            queryWrapper.orderByDesc("id");
        } else {
            queryWrapper.orderByAsc("id");
        }
        //用户ID
        queryWrapper.eq("uid", iSysUserService.getUserId());
        Page<Collection> result = collectionMapper.selectPage(page, queryWrapper);
        List<Collection> records = result.getRecords();
        for (Collection record : records) {
            if (null == record) {
                continue;
            }
            SysMenu sysMenu = menuService.selectMenuById(record.getMid());
            Long ParentId = sysMenu.getParentId();
            String path1 = sysMenu.getPath();
            String pathUrl = getSysMenu(ParentId);
            pathUrl = pathUrl + "/" + path1;
            record.setMenuName(sysMenu.getMenuName());
            record.setPath(pathUrl);
        }
        collection.setPage(collectionListVo.getPage());
        collection.setLimit(collectionListVo.getLimit());
        collection.setTotal(result.getTotal());
        collection.setItems(result.getRecords());
        return collection;
    }

    /**
     * 根据父级ID查询菜单数据
     *
     * @param parentId 父级ID
     * @return
     */
    private String getSysMenu(Long parentId) {
        SysMenu sysMenu = menuService.selectMenuById(parentId);
        parentId = sysMenu.getParentId();
        String path = sysMenu.getPath();
        if (0 != parentId) {
            String pathUrl = getSysMenuAll(parentId);
            path = pathUrl + "/" + path;
        }
        return path;
    }

    /**
     * 根据父级ID查询菜单数据
     *
     * @param parentId 父级ID
     * @return
     */
    private String getSysMenuAll(Long parentId) {
        SysMenu sysMenu = menuService.selectMenuById(parentId);
        String path = sysMenu.getPath();
        parentId = sysMenu.getParentId();
        if (0 != parentId) {
            String pathUrl = getSysMenu(parentId);
            path = pathUrl + "/" + path;
        }
        return path;
    }
}
