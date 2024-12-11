package org.social.network.services;

import lombok.extern.slf4j.Slf4j;
import org.social.network.DAO.TrainingDAO;
import org.social.network.models.Training;
import org.social.network.models.TrainingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
public class TrainingService {

    @Autowired
    private TrainingDAO trainingDao;

    public void createTraining(Integer id, Integer traineeId, Integer trainerId, String trainingName,
                               TrainingType trainingType, LocalDate trainingDate, Double trainingDuration) {
        if (trainingDao.existsById(id)) {
            log.warn("Training with ID {} already exists.", id);
            throw new IllegalArgumentException("Training with this ID already exists.");
        }

        Training training = new Training(id, traineeId, trainerId, trainingName, trainingType, trainingDate, trainingDuration);
        trainingDao.save(id, training);
        log.info("Training with ID {} created successfully.", id);
    }

    public Training getTraining(Integer id) {
        Training training = trainingDao.find(id);
        if (training == null) {
            log.warn("Training with ID {} not found.", id);
            throw new IllegalArgumentException("Training not found.");
        }
        log.info("Training with ID {} retrieved successfully.", id);
        return training;
    }
}
