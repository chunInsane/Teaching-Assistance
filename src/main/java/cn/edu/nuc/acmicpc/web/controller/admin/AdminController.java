package cn.edu.nuc.acmicpc.web.controller.admin;

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

    @RequestMapping(value = {"/index", "/"})
    public String index() {
        return "admin/index";
    }
}
