package crema.view.pivot;

import java.io.IOException;

import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.wtk.Window;

/**
 * Service to provide functionality surrounding accessing BXML files.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
public interface BxmlService {

    /**
     * Loads and deserializes a BXML file into a Window object.
     * @param baseType
     * @param fileName
     * @return a populatated Window object
     * @throws IOException
     * @throws SerializationException
     */
    Window readWindowFromBxml(final Class<?> baseType, String fileName) throws IOException, SerializationException;
}
