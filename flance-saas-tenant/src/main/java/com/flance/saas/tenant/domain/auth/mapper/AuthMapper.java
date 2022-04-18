package com.flance.saas.tenant.domain.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flance.saas.tenant.domain.auth.domain.entity.AuthEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthMapper extends BaseMapper<AuthEntity> {
}
