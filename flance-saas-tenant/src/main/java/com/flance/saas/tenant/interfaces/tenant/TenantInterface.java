package com.flance.saas.tenant.interfaces.tenant;

import com.flance.saas.tenant.domain.table.service.SchemaService;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import com.flance.saas.tenant.domain.tenant.service.TenantService;
import com.flance.saas.tenant.domain.vo.TenantRegisterRequest;
import com.flance.saas.tenant.domain.vo.TenantRegisterResponse;
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

    public TenantRegisterResponse register(TenantRegisterRequest request) {
        return null;
    }




}
