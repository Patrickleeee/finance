package com.finance.feign.service;

import org.springframework.stereotype.Component;

/**
 * Description 调用指定服务异常时，触发断路处理
 * Create By   Patrick Leee
 * CreateTime  2018/10/23 5:12 PM
 * UpdateTime  2018/10/23 5:12 PM
 */
@Component
public class SchedualServiceImplHystric implements SchedualServiceHystric {

    @Override
    public String hystricFromClient(String name) {
        return "sorry ".concat(name);
    }
}
