package com.flance.saas.tenant.domain.base;

import com.flance.jdbc.mybatis.common.IEntity;
import com.flance.saas.tenant.domain.user.domain.entity.SysUserEntity;
import com.flance.web.utils.web.request.PageRequest;
import com.flance.web.utils.web.response.PageResponse;

import java.io.Serializable;
import java.util.List;

public interface IDomain<ID extends Serializable, T extends IEntity<ID>> {

    ID createId();

    IEntity<ID> create();

    IEntity<ID> deleteById();

    IEntity<ID> updateById();

    IEntity<ID> getById();

    List<T> list(String ... args);

    PageResponse<T> page(PageRequest<T> pageRequest, String ... args);

}
