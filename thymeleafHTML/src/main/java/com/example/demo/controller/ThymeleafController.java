package com.example.demo.controller;

import com.example.demo.entity.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {
    String[] flowers = new String[]{"Rose", "Lily", "Tuylip"
    };
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("age",18);
        model.addAttribute("dow", 5);
        model.addAttribute("flowers",flowers);
        model.addAttribute("isAdmin",true);
        return "index";
    }
    @GetMapping("/date")
    public String date(Model model){
//        model.addAttribute("date",new Date());
//        return "date";
        Profile profile = new Profile("Nguyễn Huy Hoàng","huyhoang.nguyen1@gmail.com");
        System.out.println(profile);
        model.addAttribute("profile",profile);
        return "date";
    }
}
