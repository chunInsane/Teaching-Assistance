package cn.edu.nuc.acmicpc.web.controller.admin;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/5
 * Admin controller.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequiresAuthentication
    @RequiresRoles(value = {"Administrator"}, logical = Logical.OR)
    @RequestMapping(value = {"/index", "/"})
    public String index() {
        return "admin/index";
    }
}
