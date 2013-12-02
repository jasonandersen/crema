package crema.view.pivot;

import java.io.IOException;

import org.apache.commons.lang.Validate;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.wtk.Window;
import org.springframework.stereotype.Service;

/**
 * Basic implementation of BxmlService using Pivot's BXMLSerializer class.
 * 
 * @author Jason Andersen (andersen.jason@gmail.com)
 */
@Service
public class BxmlServiceImpl implements BxmlService {

    private static final String BXML_PATH_PREFIX = "/bxml/";

    /**
     * Loads a Window from a BXML file.
     * @param baseType
     * @param fileName
     * @return a populated Window object
     * @throws SerializationException 
     * @throws IOException 
     */
    public Window readWindowFromBxml(Class<?> baseType, String fileName) throws IOException, SerializationException {
        Validate.notNull(baseType);
        Validate.notNull(fileName);
        String bxmlPath = cleanFileName(fileName);
        BXMLSerializer bxmlSerializer = new BXMLSerializer();
        return (Window) bxmlSerializer.readObject(baseType, bxmlPath);
    }

    /**
     * @param fileName
     * @return a cleansed file name
     */
    private String cleanFileName(String fileName) {
        String bxmlPath = fileName;
        if (!fileName.startsWith(BXML_PATH_PREFIX)) {
            bxmlPath = BXML_PATH_PREFIX + fileName;
        }
        return bxmlPath;
    }

}
