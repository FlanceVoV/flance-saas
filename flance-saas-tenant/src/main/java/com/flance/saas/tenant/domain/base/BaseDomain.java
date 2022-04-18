package com.flance.saas.tenant.domain.base;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.flance.jdbc.mybatis.common.IEntity;
import com.flance.saas.db.utils.FieldUtils;
import com.flance.web.utils.AssertException;
import com.flance.web.utils.AssertUtil;
import com.flance.web.utils.web.request.PageRequest;
import com.flance.web.utils.web.response.PageResponse;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;

public abstract class BaseDomain<ID extends Serializable, T extends IEntity<ID>> implements IDomain<ID, T> {

    private T t;

    private IService<T> service;

    protected void setInfo(T t, IService<T> service) {
        this.t = t;
        this.service = service;
    }

    protected String createSimpleUUID() {
        return IdUtil.fastSimpleUUID();
    }

    @Override
    public T create() {
        t.setId(createId());
        t.setCreateDate(new Date());
        t.setStatus(1);
        service.save(t);
        return t;
    }

    @Override
    public T deleteById() {
        AssertUtil.notNull(t.getId(), AssertException.getNormal("唯一标识条件[id]不允许为空", "-1"));
        t.setStatus(0);
        t.setLastUpdateDate(new Date());
        service.updateById(t);
        return t;
    }

    @Override
    public T updateById() {
        AssertUtil.notNull(t.getId(), AssertException.getNormal("唯一标识条件[id]不允许为空", "-1"));
        t.setLastUpdateDate(new Date());
        service.updateById(t);
        return t;
    }

    @Override
    public T getById() {
        AssertUtil.notNull(t.getId(), AssertException.getNormal("唯一标识条件[id]不允许为空", "-1"));
        return service.getById(t.getId());
    }

    @Override
    public List<T> list(String ... args) {
        return service.list(getWrapper(args));
    }

    @Override
    public PageResponse<T> page(PageRequest<T> pageRequest, String ... args) {
        Page<T> page = new Page<>();
        page.setSize(pageRequest.getPageSize().longValue());
        page.setPages(pageRequest.getPage().longValue());
        this.t = pageRequest.getRequestData();
        page = service.page(page, getWrapper(args));
        return new PageResponse<>(page.getTotal(), page.getRecords());
    }

    /**
     * args 格式
     * OPERATOR_QUERYFIELD_VALUEFIELD
     * EQ_
     * NEQ_
     * IN_
     * NIN_
     * LT_
     * GT_
     * LIKE_
     * NLIKE_
     * LLIKE_
     * RLIKE_
     * IDIN_
     *
     *
     */
    public QueryWrapper<T> getWrapper(String ... args) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        if (null == args || args.length == 0 || null == this.t) {
            return queryWrapper;
        }

        Arrays.stream(args).forEach(arg -> {
            String[] parses = arg.split("_");
            if (!arg.contains("_") || parses.length != 3) {
                return;
            }
            String op = parses[0];
            String fieldName = getFieldName(parses[1]);
            String valueField = parses[2];

            switch (OP.valueOf(op)) {
                case EQ:
                    queryWrapper.eq(getCondition(valueField), fieldName, getValues(valueField));
                    break;
                case NEQ:
                    queryWrapper.ne(getCondition(valueField), fieldName, getValues(valueField));
                    break;
                case IN:
                    Object inValues = getValues(valueField);
                    if (inValues instanceof Collection) {
                        Collection<?> collection = (Collection<?>) inValues;
                        queryWrapper.in(getCondition(valueField), fieldName, collection);
                    }
                    break;
                case NIN:
                    Object notInValues = getValues(valueField);
                    if (notInValues instanceof Collection) {
                        Collection<?> collection = (Collection<?>) notInValues;
                        queryWrapper.notIn(getCondition(valueField), fieldName, collection);
                    }
                    break;
                case LT:
                    queryWrapper.lt(getCondition(valueField), fieldName, getValues(valueField));
                    break;
                case GT:
                    queryWrapper.gt(getCondition(valueField), fieldName, getValues(valueField));
                    break;
                case LIKE:
                    queryWrapper.like(getCondition(valueField), fieldName, getValues(valueField));
                    break;
                default:
                    break;

            }
        });
        return queryWrapper;
    }

    public String getFieldName(String fieldName) {
        return FieldUtils.humpToLine(fieldName);
    }

    private Object getValues(String fieldName) {
        try {
            Method method = t.getClass().getMethod("get" + FieldUtils.captureName(fieldName));
            return method.invoke(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean getCondition(String fieldName) {
        Object value = getValues(fieldName);
        if (null == value) {
            return false;
        }
        if (value instanceof Collection) {
            return ((Collection<?>)value).size() > 0;
        }
        return value.toString().length() > 0;
    }

    private void writeReplace(ObjectOutputStream out) {

    }

    enum OP {

        EQ("EQ"),
        NEQ("NEQ"),
        IN("IN"),
        NIN("NIN"),
        LT("LT"),
        GT("GT"),
        LIKE("LIKE"),

        ;

        private final String op;

        OP(String op) {
            this.op = op;
        }

        public String getOp() {
            return this.op;
        }

    }


}
