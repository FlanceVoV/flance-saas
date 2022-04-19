package com.flance.saas.tenant.domain.tenant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flance.saas.tenant.domain.tenant.domain.entity.BusinessId;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessIdMapper extends BaseMapper<BusinessId> {

    @Select("select business_id from sys_flance_saas_business_id where number_id = #{numberId} for update")
    String getCurrentId(@Param("numberId") Integer numberId);

}
