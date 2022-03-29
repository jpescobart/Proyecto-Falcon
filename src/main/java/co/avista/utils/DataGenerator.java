package co.avista.utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
        throw new IllegalStateException("Utility class");
    }
    private static final Faker faker = Faker.instance(new Locale("es","co"));

    public static String generateRandomString(int length){
        String randomString= new String(new char[length]).replace("\0", "#");
        return faker.bothify(randomString);
    }

    public static String generateRandomInt(int length){
        String randomString= new String(new char[length]).replace("\0", "#");
        return faker.numerify(randomString);
    }

    public static String generateFirstName(){
        return faker.name().firstName();
    }

    public static String generateLastName(){
        return faker.name().lastName();
    }

    public static String generateAddress(){
        return faker.address().fullAddress();
    }

    public static String generateAddressa(){
        return faker.internet().safeEmailAddress();
    }


}
