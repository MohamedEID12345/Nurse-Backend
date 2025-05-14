package com.eid.nursing_backend.repository;


import com.eid.nursing_backend.model.PhoneNumbers;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumbers, Long> {
    Optional<PhoneNumbers> findByNumber(String number);
}
