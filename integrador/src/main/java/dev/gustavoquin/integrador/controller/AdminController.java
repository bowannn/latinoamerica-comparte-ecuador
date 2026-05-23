package dev.gustavoquin.integrador.controller;

import dev.gustavoquin.integrador.model.entity.News;
import dev.gustavoquin.integrador.model.entity.Testimony;
import dev.gustavoquin.integrador.model.service.NewsService;
import dev.gustavoquin.integrador.model.service.TestimonyService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final NewsService news;
    private final TestimonyService testimonies;

    public AdminController(NewsService news, TestimonyService testimonies) {
        this.news = news;
        this.testimonies = testimonies;
    }

    @GetMapping
    public String showDashboard(Model model) {
        model.addAttribute("noticias", news.findAll());
        model.addAttribute("testimonios", testimonies.findAll());
        return "admin/admin";
    }

    @GetMapping("/noticias/editar/{id}")
    public String editNewsForm(@PathVariable Long id, Model model) {
        model.addAttribute("noticia", news.findById(id));
        return "admin/edit-new";
    }

    @PostMapping("/noticias/guardar")
    public String saveNews(@ModelAttribute("noticia") News noticia) {
        news.save(noticia);
        return "redirect:/admin";
    }

    @GetMapping("/noticias/eliminar/{id}")
    public String deleteNews(@PathVariable Long id) {
        news.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/testimonios/aprobar/{id}")
    public String approve(@PathVariable Long id) {
        Testimony t = testimonies.findById(id);
        t.setApproved(true);
        testimonies.save(t);
        return "redirect:/admin";
    }

    @GetMapping("/testimonios/eliminar/{id}")
    public String deleteTestimony(@PathVariable Long id) {
        testimonies.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/testimonios/editar/{id}")
    public String editTestimonyForm(@PathVariable Long id, Model model) {
        model.addAttribute("testimonio", testimonies.findById(id));
        return "admin/edit-testimony";
    }

    @PostMapping("/testimonios/guardar")
    public String saveTestimony(@ModelAttribute("testimonio") Testimony testimonio) {
        testimonies.save(testimonio);
        return "redirect:/admin";
    }
}
