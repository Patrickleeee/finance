package com.finance.feign.controller;

import com.finance.feign.service.SchedualServiceHystric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description 暴露对外接口
 * Create By   Patrick Leee
 * CreateTime  2018/10/25 11:49 AM
 * UpdateTime  2018/10/25 11:49 AM
 */
@RestController
public class SchedualController {

    @Autowired
    SchedualServiceHystric hystric;

    @RequestMapping(value = "/hystric", method = RequestMethod.GET)
    public String schedualHystric(@RequestParam String name) {
        return hystric.hystricFromClient(name);
    }
}
