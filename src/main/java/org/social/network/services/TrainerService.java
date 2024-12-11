package org.social.network.services;

import lombok.extern.slf4j.Slf4j;
import org.social.network.DAO.TrainerDAO;
import org.social.network.models.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TrainerService {
    @Autowired
    private TrainerDAO trainerDAO;

    public Trainer createTrainer(Integer id, String specialization, String firstName, String lastName, boolean isActive) {
        if (trainerDAO.existsById(id)) {
            log.warn("Trainer with ID {} already exists.", id);
            throw new IllegalArgumentException("Trainer with this ID already exists.");
        }
        String password = ProfileUtils.generateRandomPassword();
        String userName = ProfileUtils.createUsername(firstName, lastName, this::isUserNameTaken);
        log.info("Generated username: {}", userName);
        log.info("Generated password: {}", password);
        Trainer trainee = new Trainer(id, specialization, firstName, lastName, userName, password, isActive);
        trainerDAO.save(id, trainee);
        return trainee;
    }


    public Trainer getTrainer(Integer id) {
        log.info("Fetching Trainer with id: {}", id);
        return trainerDAO.find(id);
    }

    public void updateTrainer(Integer id, Trainer trainer) {
        log.info("Updating Trainer: {} {}", trainer.getFirstName(), trainer.getLastName());
        trainerDAO.update(id, trainer);
    }

    public void deleteTrainer(Integer id) {
        log.info("Deleting Trainer with id: {}", id);
        trainerDAO.delete(id);
    }

    private boolean isUserNameTaken(String userName) {
        return trainerDAO.findByUsername(userName);
    }

}
