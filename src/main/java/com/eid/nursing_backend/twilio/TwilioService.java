package com.eid.nursing_backend.twilio;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    @Value("${twilio.from_number}")
    private String fromNumber;

    public void sendWhatsAppMessage(String toNumber, String message) {
        try {
            // إرسال رسالة واتساب
            Message msg = Message.creator(
                    new PhoneNumber("whatsapp:" + toNumber), // الرقم المستلم
                    new PhoneNumber(fromNumber), // الرقم المرسل (من Twilio)
                    message // المحتوى
            ).create();

            System.out.println("✅ رسالة واتساب أُرسلت بنجاح: " + msg.getSid());
        } catch (Exception e) {
            System.err.println("❌ فشل في إرسال رسالة واتساب: " + e.getMessage());
        }
    }
}