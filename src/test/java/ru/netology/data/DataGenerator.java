package ru.netology.data;

import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private static final Faker faker = new Faker(new Locale("ru"));
    private static final Random random = new Random();

    private DataGenerator() {
    }

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            return new UserInfo(
                    generateCity(),
                    generateName(locale),
                    generatePhone(locale)
            );
        }

        public static String generateCity() {
            String[] cities = {"Москва", "Санкт-Петербург", "Казань", "Екатеринбург", "Новосибирск"};
            return cities[random.nextInt(cities.length)];
        }

        public static String generateName(String locale) {
            if ("ru".equals(locale)) {
                return faker.name().lastName() + " " + faker.name().firstName();
            }
            return faker.name().fullName();
        }

        public static String generatePhone(String locale) {
            return "+7" + faker.phoneNumber().subscriberNumber(10);
        }
    }

    public static class UserInfo {
        private final String city;
        private final String name;
        private final String phone;

        public UserInfo(String city, String name, String phone) {
            this.city = city;
            this.name = name;
            this.phone = phone;
        }

        public String getCity() {
            return city;
        }

        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }
    }
}