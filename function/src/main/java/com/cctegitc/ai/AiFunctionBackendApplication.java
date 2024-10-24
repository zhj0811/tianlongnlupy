package com.cctegitc.ai;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.cctegitc.ai.function.service.impl.OutCallnotificationsServiceImpl;
import lombok.SneakyThrows;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.InetAddress;

@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class,
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@ComponentScan("com.cctegitc")
@MapperScan(value = "com.cctegitc")
@EnableScheduling  //开启定时任务
public class AiFunctionBackendApplication implements ApplicationListener<WebServerInitializedEvent> {

    @Autowired
    private OutCallnotificationsServiceImpl outCallnotificationsService;

    private static final Logger LOG = LoggerFactory.getLogger(AiFunctionBackendApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AiFunctionBackendApplication.class, args);
    }

    @SneakyThrows
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        WebServer server = event.getWebServer();
        WebServerApplicationContext context = event.getApplicationContext();

        String ip = InetAddress.getLocalHost().getHostAddress();
        int port = server.getPort();
        String contextPath = context.getEnvironment().getProperty("server.servlet.context-pathd");
        if (null == contextPath) {
            contextPath = "";
        }
        outCallnotificationsService.outCallScheduled();
        LOG.info("---------------------------------------------------------");
        LOG.info("\tMAKA Website Service is running! Access address:");
        LOG.info("\tLocal:\t\thttp://localhost:{}", port);
        LOG.info("\tExternal:\thttp://{}:{}{}", ip, port, contextPath);
        LOG.info("---------------------------------------------------------");
    }

}
