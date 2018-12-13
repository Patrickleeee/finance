package com.finance.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description 调用指定服务异常时，触发断路处理
 * Create By   Patrick Leee
 * CreateTime  2018/10/23 5:03 PM
 * UpdateTime  2018/10/23 5:03 PM
 */
@FeignClient(value = "service-finance", fallback = SchedualServiceImplHystric.class)
public interface SchedualServiceHystric {

    @RequestMapping(value = "/finance", method = RequestMethod.GET)
    String hystricFromClient(@RequestParam(value = "name") String name);
}
