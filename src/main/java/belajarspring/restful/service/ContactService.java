package belajarspring.restful.service;

import belajarspring.restful.entity.User;
import belajarspring.restful.model.ContactResponse;
import belajarspring.restful.model.CreateContactRequest;
import belajarspring.restful.model.UpdateContactRequest;

public interface ContactService {

    ContactResponse create(User user, CreateContactRequest createContactRequest);

    ContactResponse get(User user, String id);

    ContactResponse update(User user, UpdateContactRequest updateContactRequest);

    void delete(User user, String contactId);
}
