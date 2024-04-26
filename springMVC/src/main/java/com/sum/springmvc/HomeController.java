package com.sum.springmvc;

import com.sum.springmvc.data.AlienRepo;
import com.sum.springmvc.model.Alien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    AlienRepo repo;

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
        repo.save(alien);
        return "result";
    }

    @GetMapping("getAliens")
    public String getAliens(Model m) {
        m.addAttribute("result", repo.findAll());
        return "showAliens";
    }

    @GetMapping("getAlien")
    public String getAlien(@RequestParam int aid, Model m) {
        m.addAttribute("result", repo.getReferenceById(aid));
        return "showAliens";
    }

    @GetMapping("getAlienByName")
    public String getAlienByName(@RequestParam String aname, Model m) {
        m.addAttribute("result", repo.find(aname));
        return "showAliens";
    }
}
