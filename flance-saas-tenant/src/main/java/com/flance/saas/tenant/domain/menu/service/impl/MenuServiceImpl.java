package com.flance.saas.tenant.domain.menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.flance.jdbc.mybatis.service.BaseService;
import com.flance.saas.tenant.domain.menu.domain.entity.MenuEntity;
import com.flance.saas.tenant.domain.menu.mapper.MenuMapper;
import com.flance.saas.tenant.domain.menu.service.MenuService;
import com.flance.web.utils.TreeModel;
import com.flance.web.utils.TreeUtil;
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
        return TreeUtil.getRoot(list, "0");
    }


}
