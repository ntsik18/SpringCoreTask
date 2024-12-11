package org.social.network;

import org.social.network.facade.TraineeFacade;
import org.social.network.models.Trainee;
import org.social.network.storageConfig.Storage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
@ComponentScan(basePackages = "org.social.network")
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);
        Storage storage = context.getBean(Storage.class);
        TraineeFacade traineeFacade = context.getBean(TraineeFacade.class);
        traineeFacade.createTrainee(5, "nini", "tsiklauri", true,
                LocalDate.of(2000, 5, 11),"smth");
        storage.getTrainees().entrySet().stream().forEach(System.out::println);
        storage.getTrainers().entrySet().stream().forEach(System.out::println);
        storage.getTrainings().entrySet().stream().forEach(System.out::println);

    }


}