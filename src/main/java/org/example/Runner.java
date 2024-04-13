package org.example;

import org.example.model.Sonnet;
import org.example.service.DomParserXml;
import org.example.service.SaxParserXml;
import org.example.utils.FileWriterXml;

import java.io.IOException;
import java.util.Scanner;


public class Runner {
    public static void main(String[] args) throws IOException {
        Sonnet sonnet = null;
        String path;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ввыберите 1 (DOM парсер) или 2 (SAX парсер): ");
        String userChoice = scanner.nextLine();
        if (userChoice.equals("1")) {
            sonnet = DomParserXml.parseXml("document.xml");
            path = sonnet.getAuthor().getFirstName() + "_" + sonnet.getAuthor().getLastName() + "_" + sonnet.getTitle() + ".txt";
            FileWriterXml.writeSonnetFile(path, sonnet);
        } else if (userChoice.equals("2")) {
            SaxParserXml parserXml = new SaxParserXml();
            sonnet = parserXml.parseXml();
            path = sonnet.getAuthor().getFirstName() + "_" + sonnet.getAuthor().getLastName() + "_" + sonnet.getTitle() + ".txt";
            FileWriterXml.writeSonnetFile(path, sonnet);

        } else {
            System.out.println("Не верно введён номер");
        }

        System.out.println("SONNET " + sonnet);
    }
}