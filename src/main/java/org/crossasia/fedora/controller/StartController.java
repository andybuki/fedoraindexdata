package org.crossasia.fedora.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StartController {

    @RequestMapping("/")
    public String startFedora () {
        return "fedora-configuration";
    }
}
