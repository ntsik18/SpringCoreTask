import org.junit.Before;
import org.junit.Test;
import org.social.network.storageConfig.Storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StorageTest {

    private Storage storage;

    @Before
    public void setUp() {
        storage = new Storage(); //initializing without spring context
        storage.setDataFilePath("data.txt");
    }

    @Test
    public void testInitFromFile() {
        storage.init();

        assertNotNull(storage.getTrainees());
        assertNotNull(storage.getTrainers());
        assertNotNull(storage.getTrainings());

        assertEquals(2, storage.getTrainees().size());
        assertEquals(2, storage.getTrainers().size());
        assertEquals(3, storage.getTrainings().size());

        assertNotNull(storage.getTrainees().get(1));
        assertNotNull(storage.getTrainers().get(2));
    }
}
