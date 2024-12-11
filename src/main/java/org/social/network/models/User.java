package org.social.network.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public abstract class User {

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Boolean isActive;

    @Override
    public String toString() {
        return
                "firstName='" + firstName + '\'' +
                        ", lastName='" + lastName + '\'' +
                        ", userName='" + userName + '\'' +
                        ", password='" + password + '\'' +
                        ", isActive=" + isActive + '\'' + ',';
    }
}
