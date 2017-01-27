import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Home on 26.01.2017.
 */
public class XMLWorker {

    private static final String LAST_URL = "https://beacon.nist.gov/rest/record/last";
    private static final String PREVIOUS_URL = "https://beacon.nist.gov/rest/record/previous/";
    private static final String OUTPUT_VALUE = "outputValue";
    private static final String PREVIOUS_OUTPUT_VALUE = "previousOutputValue";

    private static DocumentBuilder db;

    public XMLWorker() {
        initBuilder();
    }

    public static String getLastOutputValue() {
        return getOutputValue(buildXML(LAST_URL), OUTPUT_VALUE);
    }

    public static String getPreviousAndDemandedOutputValues(long date) {
        Document document = buildXML(PREVIOUS_URL + date);
        return getOutputValue(document, PREVIOUS_OUTPUT_VALUE) + getOutputValue(document, OUTPUT_VALUE);
    }

    public static String getPreviousOutputValues(long date) {
        return getOutputValue(buildXML(PREVIOUS_URL + date), PREVIOUS_OUTPUT_VALUE);
    }

    private static String getOutputValue(Document document, String outputValue) {
        return document.getElementsByTagName(outputValue).item(0).getTextContent();
    }

    private static Document buildXML(String xmlTarget) {
        initBuilder();
        try {
            return db.parse(new URL(xmlTarget).openStream());
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void initBuilder() {
        if (db == null) {
            try {
                db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
        }
    }
}

