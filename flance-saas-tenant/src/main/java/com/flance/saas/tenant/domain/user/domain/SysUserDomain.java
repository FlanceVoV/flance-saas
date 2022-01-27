package com.flance.saas.tenant.domain.user.domain;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.flance.saas.tenant.domain.user.domain.entity.SysUserEntity;
import com.flance.saas.tenant.domain.user.domain.vo.LoginUserSys;
import com.flance.saas.tenant.domain.user.service.SysUserService;
import com.flance.web.utils.AssertException;
import com.flance.web.utils.AssertUtil;
import com.flance.web.utils.SnowFlakeUtil;
import com.flance.web.utils.web.request.PageRequest;
import com.flance.web.utils.web.response.PageResponse;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * sysUser领域模型
 * @author jhf
 */
@Data
@Builder
public class SysUserDomain {

    /**
     * 用户实体
     */
    @NonNull
    private SysUserEntity sysUserEntity;

    @NonNull
    private SysUserService sysUserService;

    /**
     * 新增用户
     */
    public void create() {
        String encode = sysUserService.encodePassword(sysUserEntity.getUserAccount(), sysUserEntity.getUserPassword());
        sysUserEntity.setId(IdUtil.randomUUID());
        sysUserEntity.setUserPassword(encode);
        sysUserService.save(sysUserEntity);
    }

    /**
     * 修改
     */
    public void updateById() {
        AssertUtil.notEmpty(sysUserEntity.getId(), AssertException.getNormal("唯一标识条件[id]不允许为空", "-1"));
        sysUserService.updateById(sysUserEntity);
    }

    /**
     * 删除
     */
    public void deleteById() {
        AssertUtil.notEmpty(sysUserEntity.getId(), AssertException.getNormal("唯一标识条件[id]不允许为空", "-1"));
        SysUserEntity waitDelete = new SysUserEntity();
        waitDelete.setId(sysUserEntity.getId());
        waitDelete.setStatus(1);
        sysUserService.updateById(waitDelete);
    }

    /**
     * 根据查询列表
     * @return  返回集合
     */
    public List<SysUserEntity> list() {
        return sysUserService.list(buildDefault(sysUserEntity));
    }

    /**
     * 分页查询
     * @return  分页封装
     */
    public PageResponse<SysUserEntity> page(PageRequest<SysUserEntity> pageRequest) {
        Page<SysUserEntity> page = new Page<>();
        page.setSize(pageRequest.getPageSize().longValue());
        page.setPages(pageRequest.getPage().longValue());
        this.sysUserEntity = pageRequest.getRequestData();
        if (null == sysUserEntity) {
            sysUserEntity = new SysUserEntity();
        }
        page = sysUserService.page(page, buildDefault(sysUserEntity));
        return new PageResponse<>(page.getTotal(), page.getRecords());
    }

    /**
     * 登录
     * @return  返回登录信息
     */
    public LoginUserSys login() {
        SysUserEntity logon = sysUserService.login(sysUserEntity.getUserAccount(), sysUserEntity.getUserPassword());
        sysUserService.setUserMenu(logon);
        sysUserService.setUserMenu(logon);
        String token = IdUtil.fastSimpleUUID();
        return LoginUserSys.builder()
                .userId(logon.getId())
                .token(token)
                .userName(logon.getUserName())
                .userAccount(logon.getUserAccount())
                .userMenus(logon.getUserMenus())
                .userRoles(logon.getUserRoles())
                .userPhone(logon.getUserPhone())
                .build();
    }

    /**
     * 根据id查询
     * @return  返回
     */
    public SysUserEntity get() {
        SysUserEntity found =sysUserService.getById(sysUserEntity.getId());
        sysUserService.setUserMenu(found);
        sysUserService.setUserMenu(found);
        return found;
    }

    private LambdaQueryWrapper<SysUserEntity> buildDefault(SysUserEntity sysUserEntity) {
        LambdaQueryWrapper<SysUserEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUserEntity::getStatus, 1);
        lambdaQueryWrapper.eq(!StringUtils.isEmpty(sysUserEntity.getUserName()),SysUserEntity::getUserName, sysUserEntity.getUserName());
        lambdaQueryWrapper.eq(!StringUtils.isEmpty(sysUserEntity.getUserAccount()),SysUserEntity::getUserAccount, sysUserEntity.getUserAccount());
        lambdaQueryWrapper.eq(!StringUtils.isEmpty(sysUserEntity.getUserPhone()),SysUserEntity::getUserPhone, sysUserEntity.getUserPhone());

        lambdaQueryWrapper.orderByAsc(SysUserEntity::getCreateDate);
        return lambdaQueryWrapper;
    }


}
