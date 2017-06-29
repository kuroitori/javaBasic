package ru.ifmo;

import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;

import javax.swing.*;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

public class Tale {

    private static Stack<Acts> acts = new Stack<>();

    public static void main(String[] args){
        hello();
        command();
        //SwingUtilities.invokeLater(() -> gui());
    }

    private static void gui(){
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Этот метод содержит 6 команд
     * INSERT - добавить новый элемент в заданную позицию
     * CLEAR - очистить коллекцию
     * IMPORT - добавить в коллекцию все данные из файла
     * REMOVE_LOWER - удалить все элементы меньше заданного по значению
     * INFO - вывести информацию о всех командах
     * EXIT - прервать программу и сохранить данные в файл
     */

    public static void command(){
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        try {
            String str = sc.next().toUpperCase();
            switch (str) {
                case "INSERT":
                case "IN":
                        ParsJSON.setInPosition(sc.nextInt(), sc.next(), acts);
                        System.out.println("Команда выполнена успешно");
                        command();break;
                case "CLEAR":case "C":
                    acts.clear();
                    System.out.println("Команда выполнена успешно");
                    command();break;
                case "IMPORT":case "IMP":
                        acts = ParsXML.createCollection(ParsXML.parseFile(new File(sc.next())));
                        System.out.println("Команда выполнена успешно");
                        command();break;
                case "REMOVE_LOWER":case "RL":
                        ParsJSON.remove_lower_el(sc.next(), acts);
                        System.out.println("Команда выполнена успешно");
                        command();break;
                case "INFO":case "INF":case "I":
                        System.out.printf("%1$-20s %2$-5s %3$-16s - %4$-50s%n",
                                "insert(in)"," ","{index}","добавить новый элемент в заданную позицию");
                        System.out.printf("%1$-20s %2$-5s %3$-16s - %4$-50s%n",
                                "clear(c)"," "," ","очистить коллекцию");
                        System.out.printf("%1$-20s %2$-5s %3$-16s - %4$-50s%n",
                                "import(imp)"," ","{path}","добавить в коллекцию все данные из файла");
                        System.out.printf("%1$-20s %2$-5s %3$-16s - %4$-50s%n",
                                "remove_lower(rl)"," ","{element}","удалить все элементы меньше заданного по значению");
                        System.out.printf("%1$-20s %2$-5s %3$-16s - %4$-50s%n",
                            "exit(e)"," "," ","прервать программу и сохранить данные в файл");
                        System.out.print("ELEMENT - строка, заданная в формате JSON\n");
                        command();break;
                case "EXIT":case "E":
                    FileWriter.write(acts, "Acts.xml");
                    System.exit(0);
            }
        } catch(IllegalStateException e){
            System.out.println("You have entered wrong value in general");
        }catch(NumberFormatException e){
            System.out.println("You have entered too big value of voltage");
        }catch(JsonSyntaxException e){
            Class except = e.getCause().getClass();
            if(except.equals(IllegalStateException.class)){
                System.out.println("You have entered string except JSON");
            }if(except.equals(NumberFormatException.class)){
                System.out.println("You have entered overflowing argument");}
            if (except.equals(MalformedJsonException.class)){
                System.out.println("You have entered wrong JSON-format");}

        }catch(NullPointerException e){

            System.out.println("Don't keep ctrl holden so long");
        }catch (Exception e){
            System.out.println("Успокойся и выбери команду");
        }
        finally{
            command();
        }
    }

    public static void hello(){

        System.out.println("Добро пожаловать. Если хотите ознакомится со списком команд, то введите \"info\" или \"i\"");
        try {
            acts = ParsXML.createCollection(ParsXML.parseFile(new File("Acts.xml")));
        } catch (Exception e){
            System.out.println("Коллекция не была заполнена из-за отсутствия файла Acts.xml");
        }
    }

}
