package org.example.service;

import org.example.consts.TagXmlModelConst;
import org.example.model.Author;
import org.example.model.Line;
import org.example.model.Sonnet;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParserXml {
    public static Sonnet parseXml(String fileName) {
        if (!fileName.endsWith("xml")) {
            return null;
        }

        Document doc = null;
        Sonnet sonnet = new Sonnet();

        try {
            doc = buildDocument(fileName);

        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfigurationException");
        } catch (Exception e) {
            System.out.println("Error");
        }

        Node sonnetNode = doc.getFirstChild();
        NodeList sonnetNodeChild = sonnetNode.getChildNodes();
        Node lines = null;
        Node author = null;

        for (int i = 0; i < sonnetNodeChild.getLength(); i++) {
            if (sonnetNodeChild.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            switch (sonnetNodeChild.item(i).getNodeName()) {
                case TagXmlModelConst.TAG_AUTHOR: {
                    author = sonnetNodeChild.item(i);
                    break;
                }
                case TagXmlModelConst.TAG_TITLE: {
                    sonnet.setTitle(sonnetNodeChild.item(i).getTextContent());
                    break;
                }
                case TagXmlModelConst.TAG_LINES: {
                    lines = sonnetNodeChild.item(i);
                    break;
                }
            }
        }
        sonnet.setLines(parseLine(lines));
        sonnet.setAuthor(parseAuthor(author));

        return sonnet;
    }

    private static Document buildDocument(String fileName) throws ParserConfigurationException, IOException, SAXException {
        File file = new File(fileName);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        return dbf.newDocumentBuilder().parse(file);
    }

    private static List<Line> parseLine(Node linesNode) {
        List<Line> listLines = new ArrayList<>();
        NodeList linesChild = linesNode.getChildNodes();

        if (linesChild != null) {
            for (int i = 0; i < linesChild.getLength(); i++) {

                if (linesChild.item(i).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                Line line = new Line(linesChild.item(i).getTextContent());
                listLines.add(line);
            }
        }
        return listLines;
    }

    private static Author parseAuthor(Node authorNode) {
        Author author = new Author();
        if (authorNode != null) {
            NodeList authorList = authorNode.getChildNodes();
            for (int i = 0; i < authorList.getLength(); i++) {
                if (authorList.item(i).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                switch (authorList.item(i).getNodeName()) {
                    case TagXmlModelConst.TAG_LASTNAME: {
                        author.setLastName(authorList.item(i).getTextContent());
                        break;
                    }
                    case TagXmlModelConst.TAG_FIRSTNAME: {
                        author.setFirstName(authorList.item(i).getTextContent());
                        break;
                    }
                    case TagXmlModelConst.TAG_NATIONALITY: {
                        author.setNationality(authorList.item(i).getTextContent());
                        break;
                    }
                    case TagXmlModelConst.TAG_YEAR_OF_BIRTH: {
                        author.setYearOfBirth(authorList.item(i).getTextContent());
                        break;
                    }
                    case TagXmlModelConst.TAG_YEAR_OF_DEATH: {
                        author.setYearOfDeath(authorList.item(i).getTextContent());
                        break;
                    }
                }
            }
        }
        return author;
    }
}
