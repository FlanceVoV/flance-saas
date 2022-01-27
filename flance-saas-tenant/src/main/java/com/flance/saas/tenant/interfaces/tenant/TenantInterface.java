package com.flance.saas.tenant.interfaces.tenant;

import com.flance.saas.tenant.domain.table.domain.SchemaDomain;
import com.flance.saas.tenant.domain.table.domain.entity.SchemaEntity;
import com.flance.saas.tenant.domain.table.service.SchemaService;
import com.flance.saas.tenant.domain.tenant.domain.TenantDomain;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import com.flance.saas.tenant.domain.tenant.service.TenantService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 业务流程
 * 商户流程（网页端）：
 * 1. 创建商户账号
 * 2. 创建商户应用（租户应用）[填写基本信息 -> 选择套餐方案 或 自行勾选/开通功能 -> 配置菜单 或 使用系统提供的默认菜单 -> 完成创建 -> 审核 -> 开通]
 * 3. 登录商户应用
 * 4. 生成推广码、二维码（带商户参数） 推广应用
 * 5. 生成web端访问地址
 *
 * 用户流程（手机端-微信小程序）：
 * 1. 登录微信
 * 2. 使用二维码 关注应用
 * 3. 填写基本信息进行注册
 * 4. 绑定应用
 *
 * 用户流程（web端）
 * 1. 访问系统注册地址，使用微信扫码登录
 * 2. 填写基本信息，注册
 * 3. 访问系统应用市场，输入推广码选择应用
 * 4. 绑定应用
 *
 */
@Service
public class TenantInterface {

    @Resource
    SchemaService schemaService;

    @Resource
    TenantService tenantService;

    /**
     * 创建表空间
     * @param schemaEntity    实例
     */
    public void createTenantSchema(SchemaEntity schemaEntity) {
        SchemaDomain schemaDomain = SchemaDomain.builder()
                .schemaEntity(schemaEntity)
                .schemaService(schemaService)
                .build();
        schemaDomain.createSchema();
    }

    /**
     * 创建租户应用
     * @param tenant    租户对象
     */
    public void createTenant(Tenant tenant) {
        TenantDomain tenantDomain = TenantDomain.builder().tenantService(tenantService).tenant(tenant).build();
        tenantDomain.create();
    }

    /**
     * 根据主键获取租户
     * @param id        主键
     * @return          返回
     */
    public Tenant getById(String id) {
        Tenant tenant = new Tenant();
        tenant.setId(id);
        TenantDomain tenantDomain = TenantDomain.builder().tenantService(tenantService).tenant(tenant).build();
        return tenantDomain.get();
    }




}
