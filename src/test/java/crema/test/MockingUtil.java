package crema.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import crema.service.OSDirectoryService;

/**
 * Aids with building mocks.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public class MockingUtil {

    private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");

    /**
     * Creates a mock OSDirectoryService that is setup to return a specific user home directory. Will
     * create the .crema directory within the Java specified temp directory.
     * @param userHomeDir 
     * @return a mocked OS directory service that will return a user home directory
     *      in a temp directory that we can later clean up without affecting a user's
     *      actual crema directory
     */
    public static OSDirectoryService mockOSDirectoryService() {
        OSDirectoryService mock = mock(OSDirectoryService.class);
        when(mock.getUserHomeDirectoryPath()).thenReturn(TEMP_DIR);
        return mock;
    }
}
