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

import static io.restassured.RestAssured.given;

public class SoapTest {

    public static String postRequest(String requestFile, String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(requestFile);
        RestAssured.baseURI = "https://wd5-impl-services1.workday.com";

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

    public static void updateXMLFileNew(String ChangeValue) {
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

    public static void extractMultipleValues() throws ParserConfigurationException, IOException, SAXException {
        String Background_Process_Instance_ID = "";
        String Integration_System_ID = "";
        String Background_Process_Instance_Status_ID = "";
        File inputFile = new File("src/test/resources/Response_third.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
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


    public static void main(String[] args) throws Exception {
        /*String requestFile_1 = "src/test/resources/Request_1.xml";
        String resourcePath = "/ccx/service/levistraussandco_preview/Integrations/v38.0";
        updateXMLFileNew(ExtractValue(postRequest(requestFile_1, resourcePath)));
        Thread.sleep(3000);
        String requestFile_2 = "src/test/resources/Request_2.xml";
        postRequest(requestFile_1, resourcePath);*/
extractMultipleValues();
        //Completed , Processing  , INT118C_Concur_ , INTEGRATION_ESB_INVOCATION-9-960236


    }

}
