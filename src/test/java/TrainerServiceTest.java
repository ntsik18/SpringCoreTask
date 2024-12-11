import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.social.network.DAO.TrainerDAO;
import org.social.network.models.Trainer;
import org.social.network.services.TrainerService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrainerServiceTest {

    @Mock
    private TrainerDAO trainerDAO;

    @InjectMocks
    private TrainerService trainerService;

    private Trainer trainer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        trainer = new Trainer(1, "Specialization", "John", "Doe", "john.doe", "password", true);
    }

    @Test
    public void testCreateTrainer_whenTrainerExists_shouldThrowException() {
        Integer trainerId = 1;
        when(trainerDAO.existsById(trainerId)).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            trainerService.createTrainer(trainerId, "Specialization", "John", "Doe", true);
        });

        assertEquals("Trainer with this ID already exists.", exception.getMessage());
    }

    @Test
    public void testCreateTrainer_whenTrainerDoesNotExist_shouldCreateTrainer() {
        Integer trainerId = 1;
        when(trainerDAO.existsById(trainerId)).thenReturn(false);
        when(trainerDAO.findByUsername("john.doe")).thenReturn(false);

        Trainer createdTrainer = trainerService.createTrainer(trainerId, "Specialization", "John", "Doe", true);

        assertNotNull(createdTrainer);
        assertEquals(trainerId, createdTrainer.getUserId());
        assertEquals("John.Doe", createdTrainer.getUserName());
    }

    @Test
    public void testGetTrainer_shouldReturnTrainer() {
        // Arrange
        Integer trainerId = 1;
        when(trainerDAO.find(trainerId)).thenReturn(trainer);

        // Act
        Trainer fetchedTrainer = trainerService.getTrainer(trainerId);

        // Assert
        assertNotNull(fetchedTrainer);
        assertEquals(trainerId, fetchedTrainer.getUserId());
        assertEquals("John", fetchedTrainer.getFirstName());
    }

    @Test
    public void testUpdateTrainer_shouldUpdateTrainer() {
        Integer trainerId = 1;
        Trainer updatedTrainer = new Trainer(trainerId, "Specialization", "Jane", "Doe", "jane.doe", "newPassword", true);
        when(trainerDAO.find(trainerId)).thenReturn(trainer);

        trainerService.updateTrainer(trainerId, updatedTrainer);

        verify(trainerDAO, times(1)).update(trainerId, updatedTrainer); // Ensure update was called
    }

    @Test
    public void testDeleteTrainer_shouldDeleteTrainer() {
        Integer trainerId = 1;
        when(trainerDAO.existsById(trainerId)).thenReturn(true);

        trainerService.deleteTrainer(trainerId);

        verify(trainerDAO, times(1)).delete(trainerId); // Ensure delete was called
    }
}
