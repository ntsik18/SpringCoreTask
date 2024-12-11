package org.social.network.facade;

import lombok.extern.slf4j.Slf4j;
import org.social.network.models.Trainee;
import org.social.network.services.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
public class TraineeFacade {

    private final TraineeService traineeService;

    @Autowired
    public TraineeFacade(TraineeService traineeService) {
        this.traineeService = traineeService;
    }


    public void createTrainee(Integer id, String firstName, String lastName, boolean isActive, LocalDate dateOfBirth, String address) {
        log.info("Starting to create trainee with ID: {}", id);
        traineeService.createTrainee(id, firstName, lastName, isActive, dateOfBirth, address);
    }

    public Trainee getTrainee(Integer id) {
        log.info("Starting to fetch trainee with ID: {}", id);
        return traineeService.getTrainee(id);
    }

    public void updateTrainee(Integer id, Trainee updatedTrainee) {
        log.info("Staring to update trainee with ID: {}", id);
        traineeService.updateTrainee(id, updatedTrainee);
    }

    public void deleteTrainee(Integer id) {
        log.info("Starting to delete trainee with ID: {}", id);
        traineeService.deleteTrainee(id);
    }

}
