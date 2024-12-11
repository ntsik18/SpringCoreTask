import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.social.network.DAO.TrainingDAO;
import org.social.network.models.Training;
import org.social.network.models.TrainingType;
import org.social.network.services.TrainingService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
public class TrainingServiceTest {

    @Mock
    private TrainingDAO trainingDAO;

    @InjectMocks
    private TrainingService trainingService;

    private Training training;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        training = new Training(1, 100, 200, "Java Basics", new TrainingType("Technical"), LocalDate.now(), 10.0);
    }

    @Test
    public void testCreateTraining_WhenTrainingExists_ThrowsIllegalArgumentException() {
        Integer trainingId = 1;
        when(trainingDAO.existsById(trainingId)).thenReturn(true);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            trainingService.createTraining(trainingId, 100, 200, "Java Basics", new TrainingType("Technical"), LocalDate.now(), 10.0);
        });
        assertEquals("Training with this ID already exists.", thrown.getMessage());
    }

    @Test
    public void testCreateTraining_WhenTrainingDoesNotExist_Success() {
        Integer trainingId = 1;
        when(trainingDAO.existsById(trainingId)).thenReturn(false);

        trainingService.createTraining(trainingId, 100, 200, "Java Basics", new TrainingType("Technical"), LocalDate.now(), 10.0);

        verify(trainingDAO, times(1)).save(eq(trainingId), any(Training.class));
    }

    @Test
    public void testGetTraining_WhenTrainingExists_ReturnsTraining() {
        Integer trainingId = 1;
        when(trainingDAO.find(trainingId)).thenReturn(training);

        Training result = trainingService.getTraining(trainingId);

        assertNotNull(result);
        assertEquals(trainingId, result.getId());
        verify(trainingDAO, times(1)).find(trainingId);
    }

    @Test
    public void testGetTraining_WhenTrainingDoesNotExist_ThrowsIllegalArgumentException() {
        Integer trainingId = 1;
        when(trainingDAO.find(trainingId)).thenReturn(null);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            trainingService.getTraining(trainingId);
        });
        assertEquals("Training not found.", thrown.getMessage());
    }
}
