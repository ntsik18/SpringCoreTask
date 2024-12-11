package org.social.network.services;


import org.apache.commons.text.RandomStringGenerator;

public class ProfileUtils {
    private static final int PASSWORD_LENGTH = 10;

    public static String generateRandomPassword() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('!', '~') // Characters between ASCII 33 and 126
                .build();
        return generator.generate(PASSWORD_LENGTH);
    }


    public static String createUsername(String firstName, String lastName, UsernameChecker usernameChecker) {
        String baseUserName = firstName + "." + lastName;
        int suffix = 0;
        while (usernameChecker.usernameExists(baseUserName)) {
            suffix++;
            baseUserName = baseUserName + suffix;
        }
        return baseUserName;

    }

    @FunctionalInterface
    public interface UsernameChecker {
        boolean usernameExists(String userName);

    }


}
