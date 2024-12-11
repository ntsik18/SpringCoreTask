package org.social.network.facade;

import lombok.extern.slf4j.Slf4j;
import org.social.network.models.Trainer;
import org.social.network.services.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TrainerFacade {

    private final TrainerService trainerService;

    @Autowired
    public TrainerFacade(TrainerService trainerService) {
        this.trainerService = trainerService;
    }


    public void createTrainer(Integer id, String specialization, String firstName, String lastName, boolean isActive) {
        log.info("Starting to create trainer with ID: {}", id);
        trainerService.createTrainer(id, specialization, firstName, lastName, isActive);
    }

    public Trainer getTrainer(Integer id) {
        log.info("Starting to fetch trainer with ID: {}", id);
        return trainerService.getTrainer(id);
    }

    public void updateTrainer(Integer id, Trainer updatedTrainer) {
        log.info("Starting to update trainer with ID: {}", id);
        trainerService.updateTrainer(id, updatedTrainer);
    }

    public void deleteTrainer(Integer id) {
        log.info("Starting to delete trainer with ID: {}", id);
        trainerService.deleteTrainer(id);
    }

}
