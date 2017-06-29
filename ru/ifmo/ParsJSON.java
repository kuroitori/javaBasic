package ru.ifmo;

import com.google.gson.*;

import java.util.Stack;

public class ParsJSON {

    /**
     * Метод ставит объект, заданный в формате JSON, на указанную позцицию в коллекции, если это возможно
     * @param index - позиция
     * @param json - строчка заданная в JSON-формате
     * @param acts - рабочая коллекция
     */

    public static void setInPosition(int index, String json, Stack<Acts> acts){
        Acts act = parse(json);
        try {
            if(!act.equals(null)) {
                acts.add(index,act);
            }else{
                System.out.println("Вы ничего не ввели или ввели недопустимое поле");
            }
        } catch (Exception e){
            System.out.println("Туда, куда вы хотите поместить объект пусто. Нельзя " +
                    "вот так просто взять и поместить объект в пустоту");
        }

    }

    public static Acts parse(String json){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Acts act = gson.fromJson(json, Acts.class);
        return act;
    }

    /**
     * Метод удаляет все элементы меньше заданного в формате JSON по значению
     * @param json - строчка заданная в JSON-формате
     * @param acts - рабочая коллекция
     */

    public static void remove_lower_el(String json, Stack<Acts> acts){
        try{
            Acts act = parse(json);
            for (Acts a:acts){
                if (act.compareTo(a) > 0)
                    acts.remove(a);
            }
        }catch(IllegalArgumentException e){System.out.println("Не JSON-формат");}
        catch(Exception e){System.out.println("Неверно задан элемент");}
    }

}
