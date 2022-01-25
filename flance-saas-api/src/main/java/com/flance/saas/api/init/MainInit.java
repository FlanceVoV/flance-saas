package com.flance.saas.api.init;

import com.flance.saas.api.init.handlers.IHandler;
import com.flance.saas.api.utils.ThreadUtils;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * 初始化程序
 * @author jhf
 */
@Slf4j
@Component
public class MainInit {

    private static final List<String> START_HANDLERS = Lists.newArrayList();

    static {
//        START_HANDLERS.add("testHandler");
    }

    @Resource
    ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        ThreadUtils.execSupplierNow(this::exec);
    }

    private void exec() {
        START_HANDLERS.forEach(handler -> {
            try {
                IHandler handlerBean = applicationContext.getBean(handler, IHandler.class);
                handlerBean.handler();
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[加载程序]{}出错跳过加载", handler);
            }
        });
    }
}
