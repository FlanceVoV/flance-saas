package com.flance.saas.common.core;

public interface AuthService {

    boolean checkTenantAuth(String tenantId);

    boolean checkUserAuth(String requestId);

    boolean checkFeign(String headerFeignName, String headerFeignPassword);

}
