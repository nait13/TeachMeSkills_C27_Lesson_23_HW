package org.example.service;

import org.example.model.Sonnet;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SaxParserXml {

    public Sonnet parseXml() {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SaxParserXmlHandler handler = new SaxParserXmlHandler();
        SAXParser parser = null;

        try {
            parser = factory.newSAXParser();
        } catch (Exception e) {
            System.out.println("Sax parser error " + e);
        }
        File file = new File("document.xml");

        try {
            parser.parse(file, handler);
        } catch (SAXException e) {
            System.out.println("");
        } catch (IOException e) {
            System.out.println("");
        }

        return handler.getSonnet();
    }
}
