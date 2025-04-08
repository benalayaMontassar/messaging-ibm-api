package com.example.demo.service;


import com.example.demo.model.Partner;
import com.example.demo.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnerService {

    @Autowired
    private PartnerRepository partnerRepository;

    public void addPartner(Partner partner) {
        partnerRepository.save(partner);
    }

    public void deletePartner(Long id) {
        partnerRepository.deleteById(id);
    }

    public List<Partner> getAllPartners() {
        return partnerRepository.findAll();
    }
}

