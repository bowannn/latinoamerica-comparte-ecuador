package dev.gustavoquin.integrador.model.service;

import dev.gustavoquin.integrador.model.dto.ContactRequestDTO;
import dev.gustavoquin.integrador.model.entity.ContactRequest;
import dev.gustavoquin.integrador.model.constant.Purpose;
import dev.gustavoquin.integrador.model.repository.ContactRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactRequestService {

    @Autowired
    private ContactRequestRepository contactRepository;

    public void save(ContactRequestDTO contactDTO) {
        ContactRequest contact = new ContactRequest();
        contact.setName(contactDTO.getName());
        contact.setEmail(contactDTO.getEmail());
        contact.setPhone(contactDTO.getPhone());
        contact.setMessage(contactDTO.getMessage());

        try {
            if (contactDTO.getPurpose() != null && !contactDTO.getPurpose().isEmpty()) {
                contact.setPurpose(Purpose.valueOf(contactDTO.getPurpose().toUpperCase()));
            } else {
                contact.setPurpose(Purpose.SERVICE);
            }
        } catch (IllegalArgumentException e) {
            contact.setPurpose(Purpose.SERVICE);
        }

        contactRepository.save(contact);
    }
}