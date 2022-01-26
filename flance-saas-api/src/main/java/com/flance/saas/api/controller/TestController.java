package com.flance.saas.api.controller;

import com.flance.saas.tenant.interfaces.tenant.TenantInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/au/test")
public class TestController {

    @Resource
    TenantInterface tenantInterface;

    @GetMapping("/test")
    public String test() {
        return "au-test";
    }

//    @GetMapping("/list")
//    public List<Tenant> list() {
//        Tenant tenant = new Tenant();
//        TenantChooseModel baseLogin = TenantChooseUtil.getLoginModel();
//        return tenantInterface.testList(tenant);
//    }

}
