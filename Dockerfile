# استخدم صورة رسمية لـ JDK 17
FROM eclipse-temurin:17-jdk-alpine

# إعداد مجلد العمل داخل الحاوية
WORKDIR /app

# نسخ ملف JAR الناتج من البناء
COPY target/*.jar app.jar

# تشغيل التطبيق
ENTRYPOINT ["java", "-jar", "app.jar"]
