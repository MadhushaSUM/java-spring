package com.sum.springmvc.model;

import com.sum.springmvc.data.AlienRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class APIController {
    @Autowired
    AlienRepo repo;

    @GetMapping(path="AliensAPI", produces = {"application/xml"})
    public List<Alien> getAliens() {
        return repo.findAll();
    }

    @GetMapping("getAlienAPI/{aid}")
    public Optional<Alien> getAlien(@PathVariable("aid") int aid) {
        return repo.findById(aid);
    }

    @PostMapping("AliensAPI")
    public boolean saveAlien(@RequestBody Alien alien) {
        try {
            repo.save(alien);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
