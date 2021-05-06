package com.nlelpct.sign.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author nlelpct
 * @since 2020-09-23
 */
@Controller
public class RedirectController {

    @RequestMapping("/")
    public String redirectToQuery() {
        return "redirect:/sign/index.html";
    }


    @RequestMapping("/admin")
    public String redirectToAdmin() {
        return "redirect:/admin/index.html";
    }

}

