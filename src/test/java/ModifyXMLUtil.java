import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class ModifyXMLUtil {
	public static void main(String[] args) {
		updateXMLFileNew("test");


	}

	public static void updateXMLFileNew(String ChangeValue) {

		try {
			String filepath ="C:\\Shivraj\\Automation Project\\SoapApi_Automation\\resources\\Request_body.xml";

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

	// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}

	}



	public static void updateXMLFile(String subProgramIdValue,
			String firstNameValue, String middleInitialValue,
			String lastNameValue, String suffixValue, String emailAddressValue,
			String dateOfBirthValue, String streetValue, String cityValue,
			String stateValue, String zipcodeValue, String loanAmountValue,
			String loanPurposeIdValue, String yearlyIncomeValue,
			String yearlyIncomeVerifiableValue, String employmentStatusIdValue,
			String selfReportedCreditScoreValue, String homePhoneAreaCode,
			String homePhoneNumber, String mobilePhoneAreaCode,
			String mobilePhoneNumber, String workPhoneAreaCode,
			String workPhoneNumber, String ssn, String employerName,
			String employerPhoneAreaCode, String employerPhoneNumber,
			String employmentMonth, String employmentYear,
			String occupationalId, String bankName,
			String firstAccountHolderName, String accountNumber) {

		try {
			String filepath = System.getProperty("user.dir")
					+ "\\src\\test\\resources\\getOfferRequest.xml";

			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			// Get the root element
			Node offerRequestDto = doc.getFirstChild();

			NodeList nodes = offerRequestDto.getChildNodes();

			for (int i = 0; i < nodes.getLength(); i++) {

				Node node = nodes.item(i);

				if (node.getNodeName().equals("SubProgramId")
						&& subProgramIdValue != null) {
					node.setTextContent(subProgramIdValue);
				} else if (node.getNodeName().equals("FirstName")
						&& firstNameValue != null) {
					node.setTextContent(firstNameValue);
				} else if (node.getNodeName().equals("MiddleInitial")
						&& middleInitialValue != null) {
					node.setTextContent(middleInitialValue);
				} else if (node.getNodeName().equals("LastName")
						&& lastNameValue != null) {
					node.setTextContent(lastNameValue);
				} else if (node.getNodeName().equals("Suffix")
						&& suffixValue != null) {
					node.setTextContent(suffixValue);
				} else if (node.getNodeName().equals("EmailAddress")
						&& emailAddressValue != null) {
					if (emailAddressValue.equals("Random")) {
						emailAddressValue = "automatedUserTest"
								+ new SimpleDateFormat("ddMMMyyyyhhmmss")
						.format(Calendar.getInstance()
								.getTime()) + "@c1.stg";
						node.setTextContent(emailAddressValue);
					} else {
						node.setTextContent(emailAddressValue);
					}

				} else if (node.getNodeName().equals("DateOfBirth")
						&& dateOfBirthValue != null) {
					node.setTextContent(dateOfBirthValue);
				} else if (node.getNodeName().equals("Street")
						&& streetValue != null) {
					node.setTextContent(streetValue);
				} else if (node.getNodeName().equals("City")
						&& cityValue != null) {
					node.setTextContent(cityValue);
				} else if (node.getNodeName().equals("State")
						&& stateValue != null) {
					node.setTextContent(stateValue);
				}else if (node.getNodeName().equals("Zipcode")
						&& zipcodeValue != null) {
					node.setTextContent(zipcodeValue);
				} else if (node.getNodeName().equals("LoanAmount")
						&& loanAmountValue != null) {
					node.setTextContent(loanAmountValue);
				} else if (node.getNodeName().equals("LoanPurposeId")
						&& loanPurposeIdValue != null) {
					node.setTextContent(loanPurposeIdValue);
				} else if (node.getNodeName().equals("YearlyIncome")
						&& yearlyIncomeValue != null) {
					node.setTextContent(yearlyIncomeValue);
				} else if (node.getNodeName().equals("YearlyIncomeVerifiable")
						&& yearlyIncomeVerifiableValue != null) {
					node.setTextContent(yearlyIncomeVerifiableValue);
				} else if (node.getNodeName().equals("EmploymentStatusId")
						&& employmentStatusIdValue != null) {
					node.setTextContent(employmentStatusIdValue);
				} else if (node.getNodeName().equals("SelfReportedCreditScore")
						&& selfReportedCreditScoreValue != null) {
					node.setTextContent(selfReportedCreditScoreValue);
				} else if (node.getNodeName().equals("HomePhoneAreaCode")
						&& homePhoneAreaCode != null) {
					node.setTextContent(homePhoneAreaCode);
				} else if (node.getNodeName().equals("HomePhoneNumber")
						&& homePhoneNumber != null) {
					node.setTextContent(homePhoneNumber);
				} else if (node.getNodeName().equals("MobilePhoneAreaCode")
						&& mobilePhoneAreaCode != null) {
					node.setTextContent(mobilePhoneAreaCode);
				} else if (node.getNodeName().equals("MobilePhoneNumber")
						&& mobilePhoneNumber != null) {
					node.setTextContent(mobilePhoneNumber);
				} else if (node.getNodeName().equals("WorkPhoneAreaCode")
						&& workPhoneAreaCode != null) {
					node.setTextContent(workPhoneAreaCode);
				} else if (node.getNodeName().equals("WorkPhoneNumber")
						&& workPhoneNumber != null) {
					node.setTextContent(workPhoneNumber);
				} else if (node.getNodeName().equals("SSN") && ssn != null) {
					node.setTextContent(ssn);
				} else if (node.getNodeName().equals("EmployerName")
						&& employerName != null) {
					node.setTextContent(employerName);
				} else if (node.getNodeName().equals("EmployerPhoneAreaCode")
						&& employerPhoneAreaCode != null) {
					node.setTextContent(employerPhoneAreaCode);
				} else if (node.getNodeName().equals("EmployerPhoneNumber")
						&& employerPhoneNumber != null) {
					node.setTextContent(employerPhoneNumber);
				} else if (node.getNodeName().equals("EmploymentMonth")
						&& employmentMonth != null) {
					node.setTextContent(employmentMonth);
				} else if (node.getNodeName().equals("EmploymentYear")
						&& employmentYear != null) {
					node.setTextContent(employmentYear);
				} else if (node.getNodeName().equals("OccupationalId")
						&& occupationalId != null) {
					node.setTextContent(occupationalId);
				} else if (node.getNodeName().equals("BankName")
						&& bankName != null) {
					node.setTextContent(bankName);
				} else if (node.getNodeName().equals("FirstAccountHolderName")
						&& firstAccountHolderName != null) {
					node.setTextContent(firstAccountHolderName);
				} else if (node.getNodeName().equals("AccountNumber")
						&& accountNumber != null) {
					node.setTextContent(accountNumber);
				}
			}
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);


		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}

	}

	public static String updateLTXMLFile(String loanAmountValue,String firstNameValue,String middleInitialValue,String lastNameValue,String nameSuffixValue,String mothersMaidenNameValue,
			String dateOfBirthValue,String ssnValue,
			String emailValue,String workPhoneValue,String homePhoneValue,String workPhoneExtensionValue,
			String streetValue,String cityValue,String stateValue,String countryValue,String postalCodeValue,
			String currentSalaryValue) throws SAXException, IOException,
			ParserConfigurationException, XPathExpressionException, TransformerException {
		String emailValueNew = null;
		try {
			String filepath = System.getProperty("user.dir")
					+ "\\src\\test\\resources\\lendingTreeRequest.xml";
			FileInputStream file = new FileInputStream(new File(filepath));
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document xmlDocument = builder.parse(file);
			XPath xPath = XPathFactory.newInstance().newXPath();


			String expression1 = "/LTXML/Borrower/BorrowerPersonalInformation/Name";
			Node nodeName = (Node) xPath.compile(expression1).evaluate(xmlDocument,XPathConstants.NODE);
			NodeList childNodesName=nodeName.getChildNodes();
			if(null != childNodesName) {
				for (int i = 0;null!=childNodesName && i < childNodesName.getLength(); i++) {
					Node nod = childNodesName.item(i);
					if(nod.getNodeType() == Node.ELEMENT_NODE){
						if (childNodesName.item(i).getNodeName().equals("FirstName")
								&& firstNameValue != null) {
							childNodesName.item(i).setTextContent(firstNameValue);
						} else if (childNodesName.item(i).getNodeName().equals("MiddleName")
								&& middleInitialValue != null) {
							childNodesName.item(i).setTextContent(middleInitialValue);
						} else if (childNodesName.item(i).getNodeName().equals("LastName")
								&& lastNameValue != null) {
							childNodesName.item(i).setTextContent(lastNameValue);
						}else if (childNodesName.item(i).getNodeName().equals("NameSuffix")
								&& nameSuffixValue != null) {
							childNodesName.item(i).setTextContent(nameSuffixValue);
						}else if (childNodesName.item(i).getNodeName().equals("MothersMaidenName")
								&& mothersMaidenNameValue != null) {
							childNodesName.item(i).setTextContent(mothersMaidenNameValue);
						}
					}
				}
			}

			String expression2 = "/LTXML/Borrower/ContactInformation";
			Node nodeContactInfo = (Node) xPath.compile(expression2).evaluate(xmlDocument,XPathConstants.NODE);
			NodeList childNodesContactInfo=nodeContactInfo.getChildNodes();
			if(null != childNodesContactInfo) {
				for (int i = 0;null!=childNodesContactInfo && i < childNodesContactInfo.getLength(); i++) {
					Node nod = childNodesContactInfo.item(i);
					if(nod.getNodeType() == Node.ELEMENT_NODE){
						if (childNodesContactInfo.item(i).getNodeName().equals("Email")	&& emailValue != null) {
							if (emailValue.equals("Random")) {
								 emailValueNew = "automatedUser"+ new SimpleDateFormat("ddMMMyyyyhhmmss")
								.format(Calendar.getInstance().getTime()) + "@c1.stg";
								childNodesContactInfo.item(i).setTextContent(emailValueNew);
								
							} else {
								childNodesContactInfo.item(i).setTextContent(emailValue);
							}
						} else if (childNodesContactInfo.item(i).getNodeName().equals("WorkPhone")
								&& workPhoneValue != null) {
							childNodesContactInfo.item(i).setTextContent(workPhoneValue);
						} else if (childNodesContactInfo.item(i).getNodeName().equals("HomePhone")
								&& homePhoneValue != null) {
							childNodesContactInfo.item(i).setTextContent(homePhoneValue);
						}else if (childNodesContactInfo.item(i).getNodeName().equals("WorkPhoneExtension")
								&& workPhoneExtensionValue != null) {
							childNodesContactInfo.item(i).setTextContent(workPhoneExtensionValue);
						}
					}
				}
			}

			String expression3 = "/LTXML/Borrower/BorrowerResidence/BorrowerResidenceInformation/Address";
			Node nodeAddress = (Node) xPath.compile(expression3).evaluate(xmlDocument,XPathConstants.NODE);
			NodeList childNodesAddress=nodeAddress.getChildNodes();
			if(null != childNodesAddress) {
				for (int i = 0;null!=childNodesAddress && i < childNodesAddress.getLength(); i++) {
					Node nod = childNodesAddress.item(i);
					if(nod.getNodeType() == Node.ELEMENT_NODE){
						if (childNodesAddress.item(i).getNodeName().equals("Street")
								&& streetValue != null) {
							childNodesAddress.item(i).setTextContent(streetValue);
						} else if (childNodesAddress.item(i).getNodeName().equals("City")
								&& cityValue != null) {
							childNodesAddress.item(i).setTextContent(cityValue);
						} else if (childNodesAddress.item(i).getNodeName().equals("State")
								&& stateValue != null) {
							childNodesAddress.item(i).setTextContent(stateValue);
						}else if (childNodesAddress.item(i).getNodeName().equals("Country")
								&& countryValue != null) {
							childNodesAddress.item(i).setTextContent(countryValue);
						}else if (childNodesAddress.item(i).getNodeName().equals("PostalCode")
								&& postalCodeValue != null) {
							childNodesAddress.item(i).setTextContent(postalCodeValue);
						}
					}
				}
			}


			String expression4 = "/LTXML/Borrower/BorrowerEmployment";
			Node nodeBorrowerEmployment = (Node) xPath.compile(expression4).evaluate(xmlDocument,XPathConstants.NODE);
			NodeList childBorrowerEmployment=nodeBorrowerEmployment.getChildNodes();
			if(null != childBorrowerEmployment) {
				for (int i = 0;null!=childBorrowerEmployment && i < childBorrowerEmployment.getLength(); i++) {
					Node nod = childBorrowerEmployment.item(i);
					if(nod.getNodeType() == Node.ELEMENT_NODE){
						if (childBorrowerEmployment.item(i).getNodeName().equals("CurrentSalary")
								&& currentSalaryValue != null) {
							childBorrowerEmployment.item(i).setTextContent(currentSalaryValue);
						} 
					}
				}
			}

			String expression5 = "/LTXML/TransactionType/Personal";
			Node nodePersonal = (Node) xPath.compile(expression5).evaluate(xmlDocument,XPathConstants.NODE);
			NodeList childPersonal=nodePersonal.getChildNodes();
			if(null != childPersonal) {
				for (int i = 0;null!=childPersonal&& i < childPersonal.getLength(); i++) {
					Node nod = childPersonal.item(i);
					if(nod.getNodeType() == Node.ELEMENT_NODE){
						if (childPersonal.item(i).getNodeName().equals("LoanAmount")
								&& loanAmountValue != null) {
							childPersonal.item(i).setTextContent(loanAmountValue);
						} 
					}
				}
			}

			String expression6 = "/LTXML/Borrower/BorrowerPersonalInformation";
			Node nodePersonalInfo = (Node) xPath.compile(expression6).evaluate(xmlDocument,XPathConstants.NODE);
			NodeList childPersonalInfo=nodePersonalInfo.getChildNodes();
			if(null != childPersonalInfo) {
				for (int i = 0;null!=childPersonalInfo&& i < childPersonalInfo.getLength(); i++) {
					Node nod = childPersonalInfo.item(i);
					if(nod.getNodeType() == Node.ELEMENT_NODE){
						if (childPersonalInfo.item(i).getNodeName().equals("DateOfBirth")
								&& dateOfBirthValue != null) {
							childPersonalInfo.item(i).setTextContent(dateOfBirthValue);
						} else if (childPersonalInfo.item(i).getNodeName().equals("SSN")
								&& ssnValue != null) {
							childPersonalInfo.item(i).setTextContent(ssnValue);
						}
					}
				}
			}
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(xmlDocument);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);

		} catch (DOMException e) {
			e.printStackTrace();
		}
		return emailValueNew;

	}

	public static String readTagValue(String tagName) {

		try {
			String filepath = System.getProperty("user.dir")
					+ "\\src\\test\\resources\\getOfferRequest.xml";

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(filepath);
			doc.getDocumentElement().normalize();
			doc.getElementsByTagName(tagName);

			return doc.getElementsByTagName(tagName).item(0).getTextContent();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}
