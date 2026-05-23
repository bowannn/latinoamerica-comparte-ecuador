package dev.gustavoquin.integrador.controller;

import dev.gustavoquin.integrador.model.constant.NewsStatus;
import dev.gustavoquin.integrador.model.constant.Purpose;
import dev.gustavoquin.integrador.model.entity.ContactRequest;
import dev.gustavoquin.integrador.model.entity.News;
import dev.gustavoquin.integrador.model.entity.Testimony;
import dev.gustavoquin.integrador.model.service.ContactRequestService;
import dev.gustavoquin.integrador.model.service.NewsService;
import dev.gustavoquin.integrador.model.service.TestimonyService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final NewsService news;
    private final TestimonyService testimonies;
    private final ContactRequestService contacts;

    public AdminController(NewsService news, TestimonyService testimonies, ContactRequestService contacts) {
        this.news = news;
        this.testimonies = testimonies;
        this.contacts = contacts;
    }

    @GetMapping
    public String showDashboard(Model model) {
        model.addAttribute("noticias", news.findAll());
        model.addAttribute("testimonios", testimonies.findAll());
        model.addAttribute("contactos", contacts.findAll());
        return "admin/admin";
    }

    @GetMapping("/noticias/editar/{id}")
    public String editNewsForm(@PathVariable Long id, Model model) {
        model.addAttribute("noticia", news.findById(id));
        return "admin/edit-new";
    }

    @PostMapping("/noticias/guardar")
    public String saveNews(
            @RequestParam("id") Long id,
            @RequestParam("title") String title,
            @RequestParam("summary") String summary,
            @RequestParam("content") String content,
            @RequestParam(value = "status", required = false) String statusStr,
            RedirectAttributes ra) {

        News existing = news.findById(id);
        existing.setTitle(title);
        existing.setSummary(summary);
        existing.setContent(content);
        if (statusStr != null && !statusStr.isBlank()) {
            try { existing.setStatus(NewsStatus.valueOf(statusStr)); }
            catch (IllegalArgumentException ignored) {}
        }
        news.save(existing);
        ra.addFlashAttribute("successMsg", "Noticia actualizada correctamente.");
        return "redirect:/admin";
    }

    @GetMapping("/noticias/eliminar/{id}")
    public String deleteNews(@PathVariable Long id, RedirectAttributes ra) {
        news.deleteById(id);
        ra.addFlashAttribute("successMsg", "Noticia eliminada.");
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
    public String deleteTestimony(@PathVariable Long id, RedirectAttributes ra) {
        testimonies.deleteById(id);
        ra.addFlashAttribute("successMsg", "Testimonio eliminado.");
        return "redirect:/admin";
    }

    @GetMapping("/testimonios/editar/{id}")
    public String editTestimonyForm(@PathVariable Long id, Model model) {
        model.addAttribute("testimonio", testimonies.findById(id));
        return "admin/edit-testimony";
    }

    @PostMapping("/testimonios/guardar")
    public String saveTestimony(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("role") String role,
            @RequestParam("content") String content,
            @RequestParam(value = "instagramUrl", required = false) String instagramUrl,
            RedirectAttributes ra) {

        Testimony existing = testimonies.findById(id);
        existing.setName(name);
        existing.setRole(role);
        existing.setContent(content);
        existing.setInstagramUrl(instagramUrl);
        testimonies.save(existing);
        ra.addFlashAttribute("successMsg", "Testimonio actualizado correctamente.");
        return "redirect:/admin";
    }

    @GetMapping("/contactos/editar/{id}")
    public String editContactForm(@PathVariable Long id, Model model) {
        model.addAttribute("contacto", contacts.findById(id));
        model.addAttribute("purposes", Purpose.values());
        return "admin/edit-contact";
    }

    @PostMapping("/contactos/guardar")
    public String saveContact(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("message") String message,
            @RequestParam(value = "purpose", required = false) String purposeStr,
            RedirectAttributes ra) {

        ContactRequest existing = contacts.findById(id);
        existing.setName(name);
        existing.setEmail(email);
        existing.setPhone(phone);
        existing.setMessage(message);
        if (purposeStr != null && !purposeStr.isBlank()) {
            try { existing.setPurpose(Purpose.valueOf(purposeStr)); }
            catch (IllegalArgumentException ignored) {}
        }
        contacts.save(existing);
        ra.addFlashAttribute("successMsg", "Contacto actualizado correctamente.");
        return "redirect:/admin";
    }

    @GetMapping("/contactos/eliminar/{id}")
    public String deleteContact(@PathVariable Long id, RedirectAttributes ra) {
        contacts.deleteById(id);
        ra.addFlashAttribute("successMsg", "Solicitud de contacto eliminada.");
        return "redirect:/admin";
    }
}