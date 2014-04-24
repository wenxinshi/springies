package parser;

import entity.Model;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


/**
 * Factory class that handles parsing of XML files to build assemblies.
 * @author Team15
 */

public class ParserFactory {

    /**
     * Constructor call, no parameters necessary.
     */
    public ParserFactory() {
    }

    /**
     * Main parser method, parses environment variables
     * first before asking for user input.
     * @param model Collection of entities
     * @throws SAXException Exception thrown by the SAX parser library
     * @throws IOException Exception thrown by the file chooser
     */
    public void parser(Model model) throws SAXException, IOException {
        parserPath(model);
        // ParserOnePath(model);
    }

    /**
     * 
     * @param model Collection of entities
     * @throws SAXException Exception thrown by SAX parser
     * @throws IOException Exception thrown by IO process 
     */
    private void parserPath(Model model) throws SAXException, IOException {
        File path = filePath();

        if (path != null) {
            XMLReader p = XMLReaderFactory.createXMLReader();
            p.setContentHandler(new ParserHandler(model));
            p.parse(path.toURI().toURL().toString());
        }

    }

    /**
     * Opens a dialog box and allows the
     * user to specify a file.
     * @return
     */
    private File filePath() {
        JFileChooser input = new 
                JFileChooser(System.getProperties().getProperty("user.dir"));
        if (input.showOpenDialog(null)
                == JFileChooser.APPROVE_OPTION) {
            return input.getSelectedFile();
        }
        return null;
    }

}
