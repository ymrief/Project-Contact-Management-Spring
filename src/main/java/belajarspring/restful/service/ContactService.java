package belajarspring.restful.service;

import belajarspring.restful.entity.User;
import belajarspring.restful.model.ContactResponse;
import belajarspring.restful.model.CreateContactRequest;

public interface ContactService {

    ContactResponse create(User user, CreateContactRequest createContactRequest);
}
