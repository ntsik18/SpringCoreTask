package org.social.network.services;


import lombok.extern.slf4j.Slf4j;
import org.social.network.DAO.TraineeDAO;
import org.social.network.models.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
public class TraineeService {

    @Autowired
    private TraineeDAO traineeDAO;


    public Trainee createTrainee(Integer id, String firstName, String lastName, boolean isActive, LocalDate dateOfBirth, String address) {
        if (traineeDAO.existsById(id)) {
            log.warn("Trainee with ID {} already exists.", id);
            throw new IllegalArgumentException("Trainee with this ID already exists.");
        }
        String password = ProfileUtils.generateRandomPassword();
        String userName = ProfileUtils.createUsername(firstName, lastName, this::isUserNameTaken);
        log.info("Generated username: {}", userName);
        log.info("Generated password: {}", password);
        Trainee trainee = new Trainee(id, firstName, lastName, userName, password, isActive, dateOfBirth, address);
        traineeDAO.save(id, trainee);
        return trainee;
    }


    public Trainee getTrainee(Integer id) {
        log.info("Fetching Trainee with id: {}", id);
        return traineeDAO.find(id);
    }

    public void updateTrainee(Integer id, Trainee trainee) {
        log.info("Updating Trainee: {} {}", trainee.getFirstName(), trainee.getLastName());
        traineeDAO.update(id, trainee);
    }

    public void deleteTrainee(Integer id) {
        log.info("Deleting Trainee with id: {}", id);
        traineeDAO.delete(id);
    }

    private boolean isUserNameTaken(String userName) {
        return traineeDAO.findByUsername(userName);
    }


}
