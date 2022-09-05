package by.issoft.store.helpers.XMLparsers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class XMLReaderToMap {
    public static final String PATH = new File("store/src/main/resources/config.xml").getAbsolutePath();

    public static Map<SortKey, SortCommand> getPropertiesToSort() {
        String sortTag = "sort";

        Map<SortKey, SortCommand> propertiesMap = new LinkedHashMap<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document document = null;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Node node = document.getElementsByTagName(sortTag).item(0);
        NodeList sortProperties = node.getChildNodes();

        Element elementary;
        for (int i = 0; i < sortProperties.getLength(); i++)
            if (sortProperties.item(i).getNodeType() == Node.ELEMENT_NODE) {
                elementary = (Element) sortProperties.item(i);

                SortKey key = SortKey.valueOf(elementary.getTagName());
                SortCommand value = SortCommand.valueOf(elementary.getTextContent());
                propertiesMap.put(key, value);
            }
        return propertiesMap;
    }
}
