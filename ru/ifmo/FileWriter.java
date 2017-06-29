package ru.ifmo;

import java.io.*;
import java.util.Stack;

public class FileWriter {
    public static void write(Stack<Acts> acts, String s) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(new File(s))));
        try {

            writer.write("<Acts>" + "\r\n");
            for (Acts act : acts) {

                writer.write("<Act>" + "\r\n");
                writer.write("<currentCharacter>" + act.getCurrentCharacter() + "</currentCharacter>" + "\r\n");
                writer.write("<currentMethod>" + act.getCurrentMethod() + "</currentMethod>" + "\r\n");
                writer.write("</Act>" + "\r\n");
            }
            writer.write("</Acts>");
        } finally {
            writer.close();
        }
    }
}
