package hu.iit.uni.miskolc.kovacsgergo.distributedsystemsdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClassController {

    @RequestMapping("/class")
    public String listRates() {

        return "index";
    }
}
