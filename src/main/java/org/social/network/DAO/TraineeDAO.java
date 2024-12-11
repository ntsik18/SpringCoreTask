package org.social.network.DAO;


import lombok.extern.slf4j.Slf4j;
import org.social.network.models.Trainee;
import org.social.network.storageConfig.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class TraineeDAO {

    private final Map<Integer, Trainee> traineeStorage;

    @Autowired
    public TraineeDAO(Storage storage) {
        this.traineeStorage = storage.getTrainees();
    }


    public void save(Integer id, Trainee trainee) {
        traineeStorage.put(id, trainee);
        log.info("Trainee with id " + id + " saved.");
    }

    public void update(Integer id, Trainee trainee) {
        traineeStorage.put(id, trainee);
        log.info("Trainee with id " + id + " updated.");

    }

    public Trainee find(Integer id) {
        return traineeStorage.get(id);
    }

    public void delete(Integer id) {
        traineeStorage.remove(id);
        log.info("Trainee with id " + id + " deleted.");
    }

    public Map<Integer, Trainee> getAll() {
        return traineeStorage;
    }

    public boolean findByUsername(String userName) {
        return traineeStorage.values()
                .stream().anyMatch(trainee -> trainee.getUserName().equals(userName));
    }

    public boolean existsById(Integer id) {
        return traineeStorage.containsKey(id);
    }
}



