package belajarspring.restful.service;

import belajarspring.restful.entity.User;
import belajarspring.restful.model.AddressResponse;
import belajarspring.restful.model.CreateAddressRequest;
import belajarspring.restful.model.UpdateAddressRequest;

import java.util.List;

public interface AddressService {
    AddressResponse create(User user, CreateAddressRequest createAddressRequest);

    AddressResponse get(User user, String contactId, String addressId);

    AddressResponse update(User user, UpdateAddressRequest updateAddressRequest);

    void delete(User user, String contactId, String addressId);

    List<AddressResponse> list(User user, String contactId);
}
