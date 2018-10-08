package com.jason.generala.xml;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XML {

    public static Document newDocument() {
        DocumentBuilderFactory dbf;
        DocumentBuilder db;
        Document doc;
        
        try {
            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
            doc = db.newDocument();
        } catch (ParserConfigurationException ex) {
            System.err.println("No se puede generar un nuevo documento XML.");
            return null;
        }
        
        return doc;
    }
    
    public static Document loadDocument(String xmlFile) {
        DocumentBuilderFactory dbf;
        DocumentBuilder db;
        Document doc;

        try {
            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
            doc = db.parse(new File(xmlFile));
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            return null;
        }

        return doc;
    }

    public static void saveDocument(Document doc, String xmlFile) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            transformer.setParameter(OutputKeys.INDENT, "yes");
            StreamResult result = new StreamResult(new File(xmlFile));
            transformer.transform(source, result);
        } catch (TransformerException ex) {
            ex.printStackTrace();
        }
    }
}
