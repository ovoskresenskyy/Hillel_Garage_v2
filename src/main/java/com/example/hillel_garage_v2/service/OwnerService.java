package com.example.hillel_garage_v2.service;

import com.example.hillel_garage_v2.model.Owner;
import com.example.hillel_garage_v2.repository.Dao.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public List<Owner> getAll() {
        return ownerRepository.getAll();
    }

    public Owner findById(int id) {
        return ownerRepository.findById(id);
    }

    public void addNew(Owner owner) {
        ownerRepository.save(owner);
    }

    public void update(Owner incomingData) {
        ownerRepository.update(incomingData);
    }

    public void delete(int id) {
        ownerRepository.delete(id);
    }

}
