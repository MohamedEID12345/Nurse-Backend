package com.eid.nursing_backend.controller;

import com.eid.nursing_backend.model.PhoneNumbers;
import com.eid.nursing_backend.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phone-numbers")
@CrossOrigin(origins = "*")
public class PhoneNumberController {

    @Autowired
    private PhoneNumberService phoneNumberService;

    @PostMapping
    public PhoneNumbers addPhoneNumber(@RequestBody PhoneNumbers phoneNumber) {
        return phoneNumberService.addPhoneNumber(phoneNumber);
    }

    @GetMapping
    public List<PhoneNumbers> getAllPhoneNumbers() {
        return phoneNumberService.getAllPhoneNumbers();
    }

    @PutMapping("/{id}")
    public PhoneNumbers updatePhoneNumber(@PathVariable Long id, @RequestBody PhoneNumbers phoneNumber) {
        return phoneNumberService.updatePhoneNumber(id, phoneNumber);
    }

    @DeleteMapping("/{id}")
    public void deletePhoneNumber(@PathVariable Long id) {
        phoneNumberService.deletePhoneNumber(id);
    }
}
