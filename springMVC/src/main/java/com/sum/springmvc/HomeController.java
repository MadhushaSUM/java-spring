package com.sum.springmvc;

import com.sum.springmvc.model.Alien;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("add")
    public ModelAndView add(@RequestParam("num1") int num1, @RequestParam("num2") int num2) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("result");

        int num3 = num1 + num2;
        mv.addObject("num3", num3);

        return mv;
    }

    @RequestMapping("addAlien")
    public String addAlien(@ModelAttribute("alien1") Alien alien) {
        return "result";
    }
}
