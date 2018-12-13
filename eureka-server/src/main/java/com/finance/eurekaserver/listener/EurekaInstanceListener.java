package com.finance.eurekaserver.listener;

import com.netflix.discovery.shared.Applications;
import com.netflix.eureka.EurekaServerContextHolder;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

/**
 * Description 注册中心监控
 * Create By   Patrick Leee
 * CreateTime  2018/10/26 2:12 PM
 * UpdateTime  2018/10/26 2:12 PM
 */
@Configuration
public class EurekaInstanceListener implements ApplicationListener {

    private Logger log = LoggerFactory.getLogger(EurekaInstanceListener.class);

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        // 服务挂掉
        if (event instanceof EurekaInstanceCanceledEvent) {
            EurekaInstanceCanceledEvent canceledEvent = (EurekaInstanceCanceledEvent) event;
            // 获取当前Eureka实例中的节点信息
            PeerAwareInstanceRegistry registry = EurekaServerContextHolder
                    .getInstance()
                    .getServerContext()
                    .getRegistry();
            Applications applications = registry.getApplications();
            // 遍历已注册节点与当前失效节点id一致的节点信息
            applications.getRegisteredApplications().forEach(registeredApplication -> {
                registeredApplication.getInstances().forEach(registeredInstance -> {
                    if (registeredInstance.getInstanceId().equals(canceledEvent.getServerId())) {
                        log.debug("服务： " + registeredInstance.getAppName() + "挂掉啦。。。");
                        // 可以扩展消息提醒、邮件、手机短信、微信等
                    }
                });
            });

        }

        // 服务注册成功
        if (event instanceof EurekaInstanceRegisteredEvent) {
            EurekaInstanceRegisteredEvent registeredEvent = (EurekaInstanceRegisteredEvent) event;
            log.debug("服务： " + registeredEvent.getInstanceInfo().getAppName() + "注册成功啦。。。");
        }

        // 心跳检测服务
        if (event instanceof EurekaInstanceRenewedEvent) {
            EurekaInstanceRenewedEvent renewedEvent = (EurekaInstanceRenewedEvent) event;
            log.debug("服务： " + renewedEvent.getInstanceInfo().getAppName() + "心跳检测啦。。。");
        }

        // 服务空闲
        if (event instanceof EurekaRegistryAvailableEvent) {
            log.debug("服务 Aualiable。。。");
        }
    }
}
