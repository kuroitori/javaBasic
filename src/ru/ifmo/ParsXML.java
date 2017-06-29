package ru.ifmo;

import java.io.FileNotFoundException;
import java.util.Stack;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXParseException;

public class ParsXML {
    private static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private static DocumentBuilder builder;
    private static NodeList list;


    public static NodeList parseFile(File file){

        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            list = document.getElementsByTagName("Act");
        } catch (FileNotFoundException e) {
            System.out.println("Файл Acts.xml не существует");
        } catch (SAXParseException e) {
            System.out.println("Неправильный xml формат");
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Метод добавляет в коллекцию все данные из файла
     * @param list - NodeList для работы с XML-файлом
     * @return коллекция Stack<Acts>
     */

    public static Stack<Acts> createCollection(NodeList list){
        Stack<Acts> acts = new Stack<>();
        try {
            for (int i = 0; i < list.getLength(); i++){
                NodeList nodeList = list.item(i).getChildNodes();
                String currentCharacter = nodeList.item(1).getTextContent();
                String currentMethod = nodeList.item(3).getTextContent();
                Acts act = new Acts(currentCharacter, currentMethod);
                acts.add(i ,act);
            }
        } catch (NullPointerException e){
            System.out.println("Неправильная структура объекта");
        }
        return acts;
    }

}
