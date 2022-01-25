package com.flance.saas.tenant.domain.user.domain;

import com.flance.saas.tenant.domain.user.domain.entity.User;
import lombok.Builder;
import lombok.Data;

/**
 * user领域模型
 * @author jhf
 */
@Data
@Builder
public class UserDomain {

    /**
     * 用户实体
     */
    private User user;

}
