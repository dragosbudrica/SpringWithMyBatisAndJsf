package ro.kepler.rominfo.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dragos on 22.06.2017.
 */
public class Authorization {

    public static Map<String, ArrayList<String>> getRights() {
        Map<String, ArrayList<String>> authorizations = new HashMap<>();
        ArrayList<String> adminPages = new ArrayList<String>();
        ArrayList<String> professorPages = new ArrayList<String>();
        ArrayList<String> studentPages = new ArrayList<String>();

        try {
            File inputFile = new File(new Authorization().getClass().getClassLoader().getResource("authorization.xml").getFile());
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList roles = doc.getElementsByTagName("role");
            for (int roleIndex = 0; roleIndex < roles.getLength(); roleIndex++) {
                Node role = roles.item(roleIndex);
                if (role.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) role;
                    String userRole = eElement.getAttribute("id");
                    NodeList pages = eElement.getElementsByTagName("page");
                    for (int pageIndex = 0; pageIndex < pages.getLength(); pageIndex++) {
                        Node page = pages.item(pageIndex);
                        if (page.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement2 = (Element) page;
                            String pageAllowed = eElement2.getTextContent();
                            switch (userRole) {
                                case "Admin":
                                    adminPages.add(pageAllowed);
                                    break;
                                case "Professor":
                                    professorPages.add(pageAllowed);
                                    break;
                                default:
                                    studentPages.add(pageAllowed);
                                    break;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        authorizations.put("Admin", adminPages);
        authorizations.put("Professor", professorPages);
        authorizations.put("Student", studentPages);

        return authorizations;
    }
}
