package org.social.network.facade;

import lombok.extern.slf4j.Slf4j;
import org.social.network.models.Training;
import org.social.network.models.TrainingType;
import org.social.network.services.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
public class TrainingFacade {

    private final TrainingService trainingService;

    @Autowired
    public TrainingFacade(TrainingService trainingService) {
        this.trainingService = trainingService;
    }


    public void createTraining(Integer id, Integer traineeId, Integer trainerId, String trainingName,
                               TrainingType trainingType, LocalDate trainingDate, Double trainingDuration) {
        log.info("Starting to create training with ID: {}", id);
        trainingService.createTraining(id, traineeId, trainerId, trainingName, trainingType, trainingDate, trainingDuration);
    }

    public Training getTraining(Integer id) {
        log.info("Starting to fetch training with ID: {}", id);
        return trainingService.getTraining(id);
    }
}
