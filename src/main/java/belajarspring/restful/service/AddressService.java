package belajarspring.restful.service;

import belajarspring.restful.entity.User;
import belajarspring.restful.model.AddressResponse;
import belajarspring.restful.model.CreateAddressRequest;
import belajarspring.restful.model.UpdateAddressRequest;

public interface AddressService {
    AddressResponse create(User user, CreateAddressRequest createAddressRequest);

    AddressResponse get(User user, String contactId, String addressId);

    AddressResponse update(User user, UpdateAddressRequest updateAddressRequest);

    void remove(User user, String contactId, String addressId);
}
