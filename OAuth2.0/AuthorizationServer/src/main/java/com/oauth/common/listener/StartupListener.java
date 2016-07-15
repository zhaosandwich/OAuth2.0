package com.oauth.common.listener;

import com.oauth.common.DataBase;
import org.springframework.web.context.ContextLoader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 初始化redis连接池
 */
public class StartupListener extends ContextLoader implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DataBase.init();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}