package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GetTheWeather {
    //Creating constructor to get everything into document, one connection with as many calls as you want
    private Document document;
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    };

    public GetTheWeather(WeatherBean weatherBean) throws IOException {

        String API_KEY = "31e9eb2e5fa10148c011a4aef6be6752";
        // Build the API call URL by adding city+country into a URL
        String URLtoSend = "http://api.openweathermap.org/data/2.5/weather?q=" + weatherBean.getCityStr() + ","
                + weatherBean.getCountryStr() + "&APPID=" + API_KEY + "&mode=xml";

        // print and test in a browser
        System.out.println(URLtoSend);

        // Set the URL that will be sent
        URL line_api_url = new URL(URLtoSend);

        // Create a HTTP connection to sent the GET request over
        HttpURLConnection linec = (HttpURLConnection) line_api_url.openConnection();
        linec.setDoInput(true);
        linec.setDoOutput(true);
        linec.setRequestMethod("GET");

        // Make a Buffer to read the response from the API
        BufferedReader in = new BufferedReader(new InputStreamReader(linec.getInputStream()));

        // a String to temp save each line in the response
        String inputLine;

        // a String to save the full response to use later
        String ApiResponse = "";

        // loop through the whole response
        while ((inputLine = in.readLine()) != null) {

            // System.out.println(inputLine);
            // Save the temp line into the full response
            ApiResponse += inputLine;
        }
        in.close();

        // print the response
        System.out.println(ApiResponse);

        // Call a method to make a XMLdoc out of the full response
        Document doc = convertStringToXMLDocument(ApiResponse);

        this.document = doc;

    }

    public String getValueFromTag(String tag, String att) {
        // Create a Node list that gets everything in and under the "tag"
        NodeList nList = getDocument().getElementsByTagName(tag);

        String XMLclouds = "";
        // loop through the content of the tag
        for (int temp = 0; temp < nList.getLength(); temp++) {
            // Save a node of the current list id
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                // set the current node as an Element
                org.w3c.dom.Element eElement = (Element) node;
                // get the content of an attribute in element
                XMLclouds = eElement.getAttribute(att);

            }
        }
        return XMLclouds;
    }


    // Method the makes a XML doc out of a string, if it is in a XML format.
    private static org.w3c.dom.Document convertStringToXMLDocument(String xmlString) {
        // Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try {
            // Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();

            // Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

