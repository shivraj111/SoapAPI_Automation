import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DOMParser {


    public static void parser1() {
        try {
            File inputFile = new File("C:\\Shivraj\\Automation Project\\SoapApi_Automation\\XML\\Student.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("student");
            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Student roll no : "
                            + eElement.getAttribute("rollno"));
                    System.out.println("First Name : "
                            + eElement
                            .getElementsByTagName("firstname")
                            .item(0)
                            .getTextContent());
                    System.out.println("Last Name : "
                            + eElement
                            .getElementsByTagName("lastname")
                            .item(0)
                            .getTextContent());
                    System.out.println("Nick Name : "
                            + eElement
                            .getElementsByTagName("nickname")
                            .item(0)
                            .getTextContent());
                    System.out.println("Marks : "
                            + eElement
                            .getElementsByTagName("marks")
                            .item(0)
                            .getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void parser2() {
        try {
            File inputFile = new File("C:\\Shivraj\\Automation Project\\SoapApi_Automation\\XML\\ResponseXML.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("wd:Integration_Event_Reference");
            System.out.println("----------------------------");

            Node nNode = nList.item(0);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                for (int temp = 0; temp < eElement.getElementsByTagName("wd:ID").getLength(); temp++) {
                    if (eElement.getElementsByTagName("wd:ID").item(temp).getAttributes().getNamedItem("wd:type").getTextContent().equalsIgnoreCase("Background_Process_Instance_ID")) {
                        System.out.println(" Values: "
                                + eElement
                                .getElementsByTagName("wd:ID")
                                .item(temp)
                                .getTextContent());

                        eElement
                                .getElementsByTagName("wd:ID")
                                .item(temp)
                                .setTextContent("Shivraj");
                        System.out.println(" After updation Values: "
                                + eElement
                                .getElementsByTagName("wd:ID")
                                .item(temp)
                                .getTextContent());
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void parser3() {
        try {
            File inputFile = new File("C:\\Shivraj\\Automation Project\\SoapApi_Automation\\resources\\Request_body.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("wd:Integration_Event_Reference");
            System.out.println("----------------------------");

            Node nNode = nList.item(0);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                for (int temp = 0; temp < eElement.getElementsByTagName("wd:ID").getLength(); temp++) {
                    if (eElement.getElementsByTagName("wd:ID").item(temp).getAttributes().getNamedItem("wd:type").getTextContent().equalsIgnoreCase("Background_Process_Instance_ID")) {
                        System.out.println(" Values: "
                                + eElement
                                .getElementsByTagName("wd:ID")
                                .item(temp)
                                .getTextContent());

                        eElement
                                .getElementsByTagName("wd:ID")
                                .item(temp)
                                .setTextContent("Shivraj");
                        System.out.println(" After updation Values: "
                                + eElement
                                .getElementsByTagName("wd:ID")
                                .item(temp)
                                .getTextContent());
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        parser3();

    }
}
