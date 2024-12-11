package org.social.network.storageConfig;

import lombok.Getter;
import lombok.Setter;
import org.social.network.models.Trainee;
import org.social.network.models.Trainer;
import org.social.network.models.Training;
import org.social.network.models.TrainingType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Component
@PropertySource("classpath:application.properties")
public class Storage {

    private final Map<Integer, Trainee> trainees = new HashMap<>();
    private final Map<Integer, Trainer> trainers = new HashMap<>();
    private final Map<Integer, Training> trainings = new HashMap<>();

    @Value("${storage.init.file.path}")
    private String dataFilePath;

    @PostConstruct
    public void init() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(dataFilePath)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split line by comma and process
                String[] parts = line.split(",");
                String type = parts[0];
                int id = Integer.parseInt(parts[1]);

                switch (type) {
                    case "trainee":
                        String traineeFirstName = parts[2];
                        String traineeLastName = parts[3];
                        LocalDate date = LocalDate.parse(parts[7]);
                        String traineeAddress = parts[8];
                        String traineeUserName = parts[4];
                        String traineePassword = parts[5];
                        Boolean isActive = Boolean.parseBoolean(parts[6]);
                        Trainee trainee = new Trainee(id, traineeFirstName, traineeLastName, traineeUserName, traineePassword, isActive, date, traineeAddress);
                        trainees.put(id, trainee);
                        break;
                    case "trainer":
                        String trainerFirstName = parts[2];
                        String trainerLastName = parts[3];
                        String trainerUserName = parts[4];
                        String trainerPassword = parts[5];
                        Boolean trainerIsActive = Boolean.parseBoolean(parts[6]);
                        String specialization = parts[7];
                        Trainer trainer = new Trainer(id, specialization, trainerFirstName, trainerLastName, trainerUserName, trainerPassword, trainerIsActive);
                        trainers.put(id, trainer);
                        break;
                    case "training":
                        int trainerId = Integer.parseInt(parts[3]);
                        int traineeId = Integer.parseInt(parts[2]);
                        String trainingName = parts[4];
                        String trainingType = parts[5];
                        TrainingType trainingType1 = new TrainingType(trainingType);
                        String trainingDate = parts[6];
                        LocalDate date1 = LocalDate.parse(trainingDate);
                        Double trainingDuration = Double.parseDouble(parts[7]);
                        Training training = new Training(id, traineeId, trainerId, trainingName, trainingType1, date1, trainingDuration);
                        trainings.put(id, training);
                        break;
                }
            }
            System.out.println("Data loaded successfully from " + dataFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
