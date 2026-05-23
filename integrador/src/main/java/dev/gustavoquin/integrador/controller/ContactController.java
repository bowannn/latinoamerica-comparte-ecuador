package dev.gustavoquin.integrador.controller;

import dev.gustavoquin.integrador.model.dto.ContactRequestDTO;
import dev.gustavoquin.integrador.model.service.ContactRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ContactController {

    @Autowired
    private ContactRequestService contactService;

    @PostMapping("/contact/enviar")
    public String procesarContacto(@ModelAttribute("contactoDTO") ContactRequestDTO contactDTO) {
        contactService.save(contactDTO);
        return "redirect:/contacto?enviado=true";
    }
}