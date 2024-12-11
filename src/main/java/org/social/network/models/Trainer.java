package org.social.network.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor


public class Trainer extends User {
    private Integer userId;
    private String specialization;

    public Trainer(Integer userId, String specialization, String firstName, String lastName, String username, String password, boolean isActive) {
        super(firstName, lastName, username, password, isActive);
        this.userId = userId;
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Trainer{" + super.toString() +
                "userId=" + userId +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
