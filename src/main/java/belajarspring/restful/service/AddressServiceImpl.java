package belajarspring.restful.service;

import belajarspring.restful.entity.Address;
import belajarspring.restful.entity.Contact;
import belajarspring.restful.entity.User;
import belajarspring.restful.model.AddressResponse;
import belajarspring.restful.model.ContactResponse;
import belajarspring.restful.model.CreateAddressRequest;
import belajarspring.restful.repository.AddressRepository;
import belajarspring.restful.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ValidationService validationService;

    private AddressResponse toAddressResponse(Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .province(address.getProvince())
                .country(address.getCountry())
                .postalCode(address.getPostalCode())
                .build();
    }

    @Override
    @Transactional
    public AddressResponse create(User user, CreateAddressRequest createAddressRequest) {
        validationService.validate(createAddressRequest);

        Contact contact = contactRepository.findFirstByUserAndId(user, createAddressRequest.getContactId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

        Address address = new Address();
        address.setId(UUID.randomUUID().toString());
        address.setContact(contact);
        address.setStreet(createAddressRequest.getStreet());
        address.setCity(createAddressRequest.getCity());
        address.setProvince(createAddressRequest.getProvince());
        address.setCountry(createAddressRequest.getCountry());
        address.setPostalCode(createAddressRequest.getPostalCode());
        addressRepository.save(address);

        return toAddressResponse(address);
    }
}
