package com.taotao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页访问Controller
 * Created by yvettee on 12/15/18.
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String showIndex() {
        return "index";
    }
}
