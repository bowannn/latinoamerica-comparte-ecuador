package dev.gustavoquin.integrador.controller;

import dev.gustavoquin.integrador.model.entity.Testimony;
import dev.gustavoquin.integrador.model.service.TestimonyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/testimonios")
public class TestimonyController {

    @Autowired
    private TestimonyService testimonyService;

    @GetMapping
    public String verTestimonios(Model model) {
        model.addAttribute("testimonyList", testimonyService.findAll());
        return "testimony/testimony";
    }

    @PostMapping("/nuevo")
    public String guardarTestimonio(@ModelAttribute Testimony testimonio) {
        testimonio.setApproved(false);
        testimonyService.save(testimonio);
        return "redirect:/testimonios?enviado";
    }
}