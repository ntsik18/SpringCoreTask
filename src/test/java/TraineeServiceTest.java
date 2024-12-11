import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.social.network.DAO.TraineeDAO;
import org.social.network.models.Trainee;
import org.social.network.services.TraineeService;
import org.social.network.storageConfig.Storage;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TraineeServiceTest {

    @Mock
    private TraineeDAO traineeDAO;
    @Mock
    private Storage storage;

    @InjectMocks
    private TraineeService traineeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTrainee_whenTraineeExists_shouldThrowException() {
        Integer traineeId = 1;
        String firstName = "John";
        String lastName = "Doe";
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        String address = "123 Main St";
        when(traineeDAO.existsById(traineeId)).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            traineeService.createTrainee(traineeId, firstName, lastName, true, dateOfBirth, address);
        });

        assertEquals("Trainee with this ID already exists.", exception.getMessage());
    }

    @Test
    public void testCreateTrainee_whenTraineeDoesNotExist_shouldCreateTrainee() {
        Integer traineeId = 1;
        String firstName = "John";
        String lastName = "Doe";
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        String address = "123 Main St";
        when(traineeDAO.existsById(traineeId)).thenReturn(false);
        when(traineeDAO.findByUsername(anyString())).thenReturn(false); // Mock username check

        Trainee createdTrainee = traineeService.createTrainee(traineeId, firstName, lastName, true, dateOfBirth, address);

        assertNotNull(createdTrainee);
        assertEquals(traineeId, createdTrainee.getUserId());
        assertEquals(firstName, createdTrainee.getFirstName());
        assertEquals(lastName, createdTrainee.getLastName());
    }

    @Test
    public void testGetTrainee_shouldReturnTrainee() {
        Integer traineeId = 1;
        Trainee trainee = new Trainee(traineeId, "John", "Doe", "john.doe", "password", true, LocalDate.of(1990, 1, 1), "123 Main St");
        when(traineeDAO.find(traineeId)).thenReturn(trainee);

        Trainee fetchedTrainee = traineeService.getTrainee(traineeId);

        assertNotNull(fetchedTrainee);
        assertEquals(traineeId, fetchedTrainee.getUserId());
        assertEquals("John", fetchedTrainee.getFirstName());
    }

    @Test
    public void testUpdateTrainee_shouldUpdateTrainee() {
        Integer traineeId = 1;
        Trainee trainee = new Trainee(traineeId, "John", "Doe", "john.doe", "password", true, LocalDate.of(1990, 1, 1), "123 Main St");
        when(traineeDAO.find(traineeId)).thenReturn(trainee);

        Trainee updatedTrainee = new Trainee(traineeId, "Jane", "Doe", "jane.doe", "newPassword", true, LocalDate.of(1990, 1, 1), "123 Main St");
        traineeService.updateTrainee(traineeId, updatedTrainee);

        verify(traineeDAO, times(1)).update(traineeId, updatedTrainee); // Ensure update was called
    }

    @Test
    public void testDeleteTrainee_shouldDeleteTrainee() {
        Integer traineeId = 1;
        when(traineeDAO.existsById(traineeId)).thenReturn(true);

        traineeService.deleteTrainee(traineeId);

        verify(traineeDAO, times(1)).delete(traineeId); // Ensure delete was called
    }
}
