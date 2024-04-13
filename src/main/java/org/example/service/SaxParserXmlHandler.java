package org.example.service;

import org.example.consts.TagXmlModelConst;
import org.example.model.Author;
import org.example.model.Line;
import org.example.model.Sonnet;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;


public class SaxParserXmlHandler extends DefaultHandler {
    private String currentTagName;
    private Sonnet sonnet = new Sonnet();
    private Author author = new Author();
    private List<Line> lineList = new ArrayList<>();
    private boolean isAuthor = false;
    private boolean isLines = false;
    public Sonnet getSonnet() {
        return sonnet;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentTagName = qName;
        if (currentTagName.equals(TagXmlModelConst.TAG_AUTHOR)) {
            isAuthor = true;
        }
        if(currentTagName.equals(TagXmlModelConst.TAG_LINES)){
            isLines = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (currentTagName == null) return;
        if (currentTagName.equals(TagXmlModelConst.TAG_AUTHOR)) {
            isAuthor = false;
        }
        if(currentTagName.equals(TagXmlModelConst.TAG_LINES)){
            isLines = false;
        }
        currentTagName = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentTagName == null) return;

        String textContent = new String(ch, start, length);
        if (currentTagName.equals(TagXmlModelConst.TAG_TITLE)) {
            sonnet.setTitle(textContent);
        }
        if (isAuthor) {
            if (currentTagName.equals(TagXmlModelConst.TAG_FIRSTNAME)) {
                author.setFirstName(textContent);
            } else if (currentTagName.equals(TagXmlModelConst.TAG_LASTNAME)) {
                author.setLastName(textContent);
            } else if (currentTagName.equals(TagXmlModelConst.TAG_NATIONALITY)) {
                author.setNationality(textContent);
            } else if (currentTagName.equals(TagXmlModelConst.TAG_YEAR_OF_BIRTH)) {
                author.setYearOfBirth(textContent);
            } else if (currentTagName.equals(TagXmlModelConst.TAG_YEAR_OF_DEATH)) {
                author.setYearOfDeath(textContent);
            }
            sonnet.setAuthor(author);
        }
        if(isLines &&  currentTagName.equals("line")){
            lineList.add(new Line(textContent));
        }
        sonnet.setLines(lineList);
    }

}
