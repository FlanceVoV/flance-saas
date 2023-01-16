package com.flance.saas.tenant.interfaces.tenant;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flance.saas.common.core.SaasConstant;
import com.flance.saas.common.login.LoginInfo;
import com.flance.saas.common.utils.LoginUtil;
import com.flance.saas.tenant.domain.table.domain.entity.SchemaEntity;
import com.flance.saas.tenant.domain.table.service.SchemaService;
import com.flance.saas.tenant.domain.tenant.domain.entity.BusinessId;
import com.flance.saas.tenant.domain.tenant.domain.entity.Tenant;
import com.flance.saas.tenant.domain.tenant.service.BusinessIdService;
import com.flance.saas.tenant.domain.tenant.service.TenantService;
import com.flance.saas.tenant.domain.user.domain.vo.LoginUser;
import com.flance.saas.tenant.domain.vo.TenantRegisterRequest;
import com.flance.saas.tenant.domain.vo.TenantRegisterResponse;
import com.flance.web.utils.AssertException;
import com.flance.web.utils.AssertUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Date;

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
    TenantService tenantService;

    @Resource
    BusinessIdService businessIdService;

    public String createTenantId() {
        String tenantId = businessIdService.getCurrentId(SaasConstant.BUSINESS_ID_TENANT_ID);
        int number = Integer.parseInt(tenantId);
        String result = String.format("%06d", number + 1);
        LambdaQueryWrapper<BusinessId> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BusinessId::getNumberId, SaasConstant.BUSINESS_ID_TENANT_ID);
        BusinessId update = new BusinessId();
        update.setNumberId(SaasConstant.BUSINESS_ID_TENANT_ID);
        update.setBusinessId(result);
        businessIdService.update(update, queryWrapper);
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public TenantRegisterResponse register(@Validated TenantRegisterRequest request) {
        LoginInfo loginUser = LoginUtil.getLoginModel(LoginInfo.class);
        AssertUtil.mastTrue(SaasConstant.SYS_USER_TYPE_MERCHANT.equals(loginUser.getUserType()),
                AssertException.getNormal("非法请求！只有商户用户才能注册租户应用", "-1"));
        Tenant tenant = request.parseTenant();
        tenant.setUserId(loginUser.getUserId());
        tenant.setStatus(SaasConstant.DATA_STATUS_NORMAL);
        tenant.setCreateDate(new Date());
        tenant.setCreateUserId(loginUser.getUserId());
        tenant.setTenantId(createTenantId());
        tenantService.register(tenant);
        return TenantRegisterResponse.builder()
                .tenantId(tenant.getTenantId())
                .build();
    }




}
