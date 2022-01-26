package com.flance.saas.tenant.domain.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flance.saas.tenant.domain.user.domain.entity.AppUserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserMapper extends BaseMapper<AppUserEntity> {
}
