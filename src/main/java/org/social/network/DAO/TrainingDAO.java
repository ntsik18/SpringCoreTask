package org.social.network.DAO;


import lombok.extern.slf4j.Slf4j;
import org.social.network.models.Training;
import org.social.network.storageConfig.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Slf4j
@Repository
public class TrainingDAO {

    private final Map<Integer, Training> trainingStorage;

    @Autowired
    public TrainingDAO(Storage storage) {
        this.trainingStorage = storage.getTrainings();
    }


    public void save(Integer id, Training training) {
        trainingStorage.put(id, training);
        log.info("training with id " + id + " saved.");
    }

    public void update(Integer id, Training training) {
        trainingStorage.put(id, training);
        log.info("training with id " + id + " updated.");
    }

    public Training find(Integer id) {
        return trainingStorage.get(id);
    }

    public void delete(Integer id) {
        trainingStorage.remove(id);
        log.info("training with id " + id + " deleted.");
    }

    public Map<Integer, Training> getAll() {
        return trainingStorage;
    }

    public boolean existsById(Integer id) {
        return trainingStorage.containsKey(id);
    }
}



