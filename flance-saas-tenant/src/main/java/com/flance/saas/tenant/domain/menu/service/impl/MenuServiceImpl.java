package com.flance.saas.tenant.domain.menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.tenant.domain.menu.domain.entity.MenuEntity;
import com.flance.saas.tenant.domain.menu.mapper.MenuMapper;
import com.flance.saas.tenant.domain.menu.service.MenuService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl extends BaseService<String, MenuMapper, MenuEntity> implements MenuService {

    @Override
    public List<MenuEntity> list(Wrapper<MenuEntity> queryWrapper) {
        List<MenuEntity> list = super.list(queryWrapper);
        return getTree(list);
    }

    private List<MenuEntity> getTree(List<MenuEntity> menus) {
        List<MenuEntity> root = Lists.newArrayList();
        menus.forEach(menu -> {
            if ("0".equals(menu.getParentId())) {
                root.add(menu);
            }
        });
        root.forEach(menu -> menu.setChildren(setChildren(menu, menus)));
        return root;
    }

    private List<MenuEntity> setChildren(MenuEntity menu, List<MenuEntity> menus) {
        return menus.stream()
                .filter((category) -> category.getParentId().equals(menu.getId()))
                .peek((category) -> category.setChildren(setChildren(category, menus)))
                .sorted(Comparator.comparingInt(menu2 -> (menu2.getSort() == null ? 0 : menu2.getSort())))
                .collect(Collectors.toList());
    }


}
