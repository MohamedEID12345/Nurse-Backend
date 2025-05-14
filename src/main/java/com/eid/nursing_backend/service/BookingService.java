package com.eid.nursing_backend.service;

import com.eid.nursing_backend.model.Booking;
import com.eid.nursing_backend.model.PhoneNumbers;
import com.eid.nursing_backend.repository.BookingRepository;
import com.eid.nursing_backend.repository.PhoneNumberRepository;
import com.twilio.type.PhoneNumber;
import com.eid.nursing_backend.twilio.TwilioService;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @Autowired
    private TwilioService twilioService;

    public Booking createBooking(Booking booking, Long whatsappNumberId) {
        // البحث عن الرقم من قاعدة البيانات
        PhoneNumbers phoneNumber = phoneNumberRepository.findById(whatsappNumberId)
                .orElseThrow(() -> new RuntimeException("WhatsApp number not found"));

        // تعيين الرقم في الحجز
        booking.setWhatsappNumber(phoneNumber);
        Booking saved = bookingRepository.save(booking);

        // إرسال رسالة WhatsApp بعد الحجز
        sendWhatsAppMessage(saved);

        return saved;
    }

    private void sendWhatsAppMessage(Booking booking) {
        // بناء رسالة الحجز
        String msg = String.format(
                "🩺 تم استلام حجز جديد:\n👤 الاسم: %s\n🎂 السن: %d\n📅 التاريخ: %s\n🕒 الوقت: %s\n📞 رقم العميل: %s\n📍 العنوان: %s\n📝 الوصف: %s",
                booking.getName(), booking.getAge(), booking.getDate(), booking.getTime(),
                booking.getPhoneNumber(), booking.getAddress(), booking.getDescription()
        );

        // تحديد الرقم المرسل إليه (الرقم الموجود في قاعدة البيانات)
        String to = "whatsapp:" + booking.getWhatsappNumber().getNumber();

        try {
            // إرسال الرسالة عبر Twilio
            Message message = Message.creator(
                    new PhoneNumber("whatsapp:" + booking.getWhatsappNumber().getNumber()), // رقم المرسل إليه
                    new PhoneNumber("whatsapp:+14155238886"), // الرقم المرسل (رقم Twilio)
                    msg
            ).create();

            // طباعة SID الرسالة للتأكد من إرسالها
            System.out.println("✅ تم إرسال الرسالة بنجاح إلى: " + to + " | SID: " + message.getSid());
        } catch (Exception e) {
            // طباعة الخطأ في حالة حدوث مشكلة
            System.err.println("❌ فشل في إرسال الرسالة إلى " + to + ": " + e.getMessage());
        }
    }
}
