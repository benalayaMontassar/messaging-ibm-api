package com.example.demo.controller;

import com.example.demo.model.Partner;
import com.example.demo.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partners")
@CrossOrigin
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    @GetMapping
    public List<Partner> getPartners() {
        return partnerService.getAllPartners();
    }

    @PostMapping
    public ResponseEntity<Void> addPartner(@RequestBody Partner partner) {
        partnerService.addPartner(partner);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartner(@PathVariable Long id) {
        partnerService.deletePartner(id);
        return ResponseEntity.noContent().build();
    }
}
