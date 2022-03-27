import com.opencsv.CSVWriter;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

import static io.restassured.RestAssured.given;

public class SoapTest {
    static String Background_Process_Instance_ID = "";
    static String Integration_System_ID = "";
    static String Background_Process_Instance_Status_ID = "";
    static final String requestFile_1 = "src/test/resources/Request_1.xml";
    static final String resourcePath = "/ccx/service/levistraussandco_preview/Integrations/v38.0";
    static final String requestFile_2 = "src/test/resources/Request_2.xml";
    static final String requestFile_3 = "src/test/resources/Request_3.xml";
    static final String CSVFile = "src/test/resources/INT_STU_FTP_INTEGRATION_OUTPUT.csv";


    public static String postRequest(String requestFile, String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(requestFile);
        RestAssured.baseURI = "https://wd5-impl-services1.workday.com";
        System.out.println("requestFile : " + requestFile);
        System.out.println("path : " + path);
        Response response = given()
                .header("Content-Type", "text/xml")
                .and()
                .body(IOUtils.toString(fileInputStream, "UTF-8"))
                .when()
                .post(path)
                .then()
                .statusCode(200)
                .and()
                .extract().response();
        //.log().all().extract().response();*/
        System.out.println(response.asString());
        return response.asString();
    }


    public static String ExtractValue(String Response) throws IOException, SAXException, ParserConfigurationException {

        String firstResponseValue = "";
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(Response));

        Document doc = db.parse(is);


        NodeList nList = doc.getElementsByTagName("wd:Integration_Event_Reference");
        System.out.println("----------------------------");
        Node nNode = nList.item(0);
        //System.out.println("\nCurrent Element :" + nNode.getNodeName());
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;
            for (int temp = 0; temp < eElement.getElementsByTagName("wd:ID").getLength(); temp++) {
                if (eElement.getElementsByTagName("wd:ID").item(temp).getAttributes().getNamedItem("wd:type").getTextContent().equalsIgnoreCase("Background_Process_Instance_ID")) {
                    firstResponseValue = eElement
                            .getElementsByTagName("wd:ID")
                            .item(temp)
                            .getTextContent();
                    System.out.println(" Extracted Value : " + firstResponseValue);
                }
            }
        }
        return firstResponseValue;
    }

    public static void updateXMLFile(String ChangeValue) {
        try {
            String filepath = "src/test/resources/Request_2.xml";

            DocumentBuilderFactory docFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);

            NodeList nList = doc.getElementsByTagName("wd:Integration_Event_Reference");
            System.out.println("----------------------------");
            Node nNode = nList.item(0);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                for (int temp = 0; temp < eElement.getElementsByTagName("wd:ID").getLength(); temp++) {
                    if (eElement.getElementsByTagName("wd:ID").item(temp).getAttributes().getNamedItem("wd:type").getTextContent().equalsIgnoreCase("Background_Process_Instance_ID")) {
                        System.out.println(" Current Values: "
                                + eElement
                                .getElementsByTagName("wd:ID")
                                .item(temp)
                                .getTextContent());

                        eElement
                                .getElementsByTagName("wd:ID")
                                .item(temp)
                                .setTextContent(ChangeValue);
                        System.out.println(" Updated Values: "
                                + eElement
                                .getElementsByTagName("wd:ID")
                                .item(temp)
                                .getTextContent());
                    }
                }
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath));
            transformer.transform(source, result);

        } catch (
                ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (
                TransformerException tfe) {
            tfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (
                SAXException sae) {
            sae.printStackTrace();
        }
    }

    public static void extractMultipleValues(String Response) throws ParserConfigurationException, IOException, SAXException {

        String firstResponseValue = "";
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(Response));

        Document doc = db.parse(is);

       /* File inputFile = new File("src/test/resources/Response_third.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);*/
        doc.getDocumentElement().normalize();
        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
        NodeList nList = doc.getElementsByTagName("wd:Integration_Event_Reference");
        System.out.println("----------------------------");
        Node nNode = nList.item(0);
        //System.out.println("\nCurrent Element :" + nNode.getNodeName());
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;
            for (int temp = 0; temp < eElement.getElementsByTagName("wd:ID").getLength(); temp++) {
                if (eElement.getElementsByTagName("wd:ID").item(temp).getAttributes().getNamedItem("wd:type").getTextContent().equalsIgnoreCase("Background_Process_Instance_ID")) {
                    Background_Process_Instance_ID = eElement
                            .getElementsByTagName("wd:ID")
                            .item(temp)
                            .getTextContent();
                    System.out.println(" Background_Process_Instance_ID : " + Background_Process_Instance_ID);
                }
            }
        }
        //----------------------------------------------------------------------------
        NodeList nList2 = doc.getElementsByTagName("wd:Integration_System_Reference");
        System.out.println("----------------------------");
        Node nNode2 = nList2.item(0);
        //System.out.println("\nCurrent Element :" + nNode.getNodeName());
        if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode2;
            for (int temp = 0; temp < eElement.getElementsByTagName("wd:ID").getLength(); temp++) {
                if (eElement.getElementsByTagName("wd:ID").item(temp).getAttributes().getNamedItem("wd:type").getTextContent().equalsIgnoreCase("Integration_System_ID")) {
                    Integration_System_ID = eElement
                            .getElementsByTagName("wd:ID")
                            .item(temp)
                            .getTextContent();
                    System.out.println(" Integration_System_ID : " + Integration_System_ID);
                }
            }
        }
//----------------------------------------------------------------------------
        NodeList nList3 = doc.getElementsByTagName("wd:Background_Process_Instance_Status_Reference");
        System.out.println("----------------------------");
        Node nNode3 = nList3.item(0);
        //System.out.println("\nCurrent Element :" + nNode.getNodeName());
        if (nNode3.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode3;
            for (int temp = 0; temp < eElement.getElementsByTagName("wd:ID").getLength(); temp++) {
                if (eElement.getElementsByTagName("wd:ID").item(temp).getAttributes().getNamedItem("wd:type").getTextContent().equalsIgnoreCase("Background_Process_Instance_Status_ID")) {
                    Background_Process_Instance_Status_ID = eElement
                            .getElementsByTagName("wd:ID")
                            .item(temp)
                            .getTextContent();
                    System.out.println(" Background_Process_Instance_Status_ID : " + Background_Process_Instance_Status_ID);
                }
            }
        }


    }


    public static void makeRequestWithInterval(int IntervalInSecond, int Counter) {
        Boolean flag = false;
        Timer timer = new Timer();
        int begin = 1;
        //timeInterval values are in millisecond
        int timeInterval = IntervalInSecond;
        timer.schedule(new TimerTask() {
            int counter = 0;

            @Override
            public void run() {

                if (Background_Process_Instance_Status_ID.equalsIgnoreCase("Completed")) {
                    System.out.println("Counter" + counter);
                    timer.cancel();
                }
                try {
                    extractMultipleValues(postRequest(requestFile_2, resourcePath));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("Hit request");
                counter++;
                if (counter >= Counter) {
                    timer.cancel();
                    throw new IllegalArgumentException("Expected status value : Completed not observed after " + (IntervalInSecond) * Counter + " Seconds");
                }


            }
        }, begin, timeInterval * 1000);
    }

    public static void createCSV(String filePath) {
        // first create file object for file placed at location
        // specified by filepath
        File file = new File(filePath);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            // create CSVWriter with '|' as separator
            CSVWriter writer = new CSVWriter(outputfile, ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            // add data to csv
            //String[] data1 = {"Aman", "10", "620"};

            String[] data1 = {Background_Process_Instance_ID,Background_Process_Instance_Status_ID , Integration_System_ID};
            writer.writeNext(data1);
            // closing writer connection
            writer.close();
            System.out.println("CSV file generated Successfully");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public static void main(String[] args) throws Exception {
         updateXMLFile(ExtractValue(postRequest(requestFile_1, resourcePath)));
        Thread.sleep(3000);
        extractMultipleValues(postRequest(requestFile_2, resourcePath));
        //Completed , Processing  , INT118C_Concur_ , INTEGRATION_ESB_INVOCATION-9-960236
        //makeRequestWithInterval(300, 5);
        makeRequestWithInterval(10, 5);
        createCSV(CSVFile);
//----Upload CSV file
        //postRequest(requestFile_3, resourcePath);

    }

}
