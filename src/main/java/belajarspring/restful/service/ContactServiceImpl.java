package belajarspring.restful.service;

import belajarspring.restful.entity.Contact;
import belajarspring.restful.entity.User;
import belajarspring.restful.model.ContactResponse;
import belajarspring.restful.model.CreateContactRequest;
import belajarspring.restful.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ValidationService validationService;

    @Override
    @Transactional
    public ContactResponse create(User user, CreateContactRequest createContactRequest) {
        validationService.validate(createContactRequest);

        Contact contact = new Contact();
        contact.setId(UUID.randomUUID().toString());
        contact.setFirstName(createContactRequest.getFirstName());
        contact.setLastName(createContactRequest.getLastName());
        contact.setEmail(createContactRequest.getEmail());
        contact.setPhone(createContactRequest.getPhone());
        contact.setUser(user);

        contactRepository.save(contact);

        return ContactResponse.builder()
                .id(contact.getId())
                .firstname(contact.getFirstName())
                .lastname(contact.getLastName())
                .email(contact.getEmail())
                .phone(contact.getPhone())
                .build();
    }
}
