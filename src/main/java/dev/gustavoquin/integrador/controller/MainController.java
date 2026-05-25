package dev.gustavoquin.integrador.controller;

import dev.gustavoquin.integrador.model.service.NewsService;
import dev.gustavoquin.integrador.model.service.TestimonyService;
import dev.gustavoquin.integrador.model.dto.ContactRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private TestimonyService testimonyService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newsList", newsService.findAll());
        model.addAttribute("testimonyList", testimonyService.findApproved());
        model.addAttribute("contactoDTO", new ContactRequestDTO());
        return "principalview/principal-view";
    }

    @GetMapping("/noticias")
    public String showNews(Model model) {
        model.addAttribute("newsList", newsService.findAll());
        return "news/news";
    }

    //@GetMapping("/testimonios")
    //public String showTestimonials(Model model) {
    //    model.addAttribute("testimonyList", testimonyService.findApproved());
    //    return "testimony/testimony";
    //}

    @GetMapping("/contacto")
    public String showContact(Model model) {
        model.addAttribute("contactoDTO", new ContactRequestDTO());
        return "contact/contact";
    }
}