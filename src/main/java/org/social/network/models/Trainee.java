package org.social.network.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter
@Setter
@NoArgsConstructor

public class Trainee extends User {
    private Integer userId;
    private String address;
    private LocalDate dateOfBirth;

    public Trainee(int id, String firstName, String lastName, String username, String password, boolean isActive, LocalDate dateOfBirth, String address) {
        super(firstName, lastName, username, password, isActive);
        this.userId = id;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Trainee{" + super.toString() +
                "userId=" + userId +
                ", address='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
