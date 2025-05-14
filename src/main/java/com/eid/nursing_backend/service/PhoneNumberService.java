package com.eid.nursing_backend.service;

import com.eid.nursing_backend.model.PhoneNumbers;
import com.eid.nursing_backend.repository.PhoneNumberRepository;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneNumberService {

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @Value("${twilio.from_number}")
    private String twilioFromNumber;

    public PhoneNumbers addPhoneNumber(PhoneNumbers phoneNumber) {
        PhoneNumbers saved = phoneNumberRepository.save(phoneNumber);

        // إرسال رسالة ترحيب عبر WhatsApp
        sendWelcomeWhatsAppMessage(saved.getNumber());

        return saved;
    }

    public List<PhoneNumbers> getAllPhoneNumbers() {
        return phoneNumberRepository.findAll();
    }

    public PhoneNumbers updatePhoneNumber(Long id, PhoneNumbers updatedPhoneNumber) {
        return phoneNumberRepository.findById(id)
                .map(existing -> {
                    existing.setNumber(updatedPhoneNumber.getNumber());
                    existing.setDescription(updatedPhoneNumber.getDescription());
                    return phoneNumberRepository.save(existing);
                }).orElseThrow(() -> new RuntimeException("Phone number not found"));
    }

    public void deletePhoneNumber(Long id) {
        phoneNumberRepository.deleteById(id);
    }

    private void sendWelcomeWhatsAppMessage(String number) {
        String to = "whatsapp:" + number;
        String msg = "🤝 تم تسجيل هذا الرقم لتلقي إشعارات حجوزات التمريض. شكرًا لانضمامك!";

        try {
            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber(to),
                    new com.twilio.type.PhoneNumber(twilioFromNumber),
                    msg
            ).create();

            System.out.println("✅ تم إرسال رسالة الترحيب إلى: " + number);
        } catch (Exception e) {
            System.err.println("❌ فشل في إرسال رسالة الترحيب إلى " + number + ": " + e.getMessage());
        }
    }
}
