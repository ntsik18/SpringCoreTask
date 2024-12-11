package org.social.network.DAO;


import lombok.extern.slf4j.Slf4j;
import org.social.network.models.Trainer;
import org.social.network.storageConfig.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Slf4j
@Repository
public class TrainerDAO {

    private final Map<Integer, Trainer> traineerStorage;

    @Autowired
    public TrainerDAO(Storage storage) {
        this.traineerStorage = storage.getTrainers();
    }


    public void save(Integer id, Trainer trainer) {
        traineerStorage.put(id, trainer);
        log.info("trainer with id " + id + " saved.");
    }

    public void update(Integer id, Trainer trainer) {
        traineerStorage.put(id, trainer);
        log.info("trainer with id " + id + " updated.");
        ;
    }

    public Trainer find(Integer id) {
        return traineerStorage.get(id);
    }

    public void delete(Integer id) {
        traineerStorage.remove(id);
        log.info("trainer with id " + id + " deleted.");
    }

    public Map<Integer, Trainer> getAll() {
        return traineerStorage;
    }

    public boolean findByUsername(String userName) {
        return traineerStorage.values()
                .stream().anyMatch(trainee -> trainee.getUserName().equals(userName));
    }

    public boolean existsById(Integer id) {
        return traineerStorage.containsKey(id);
    }
}



