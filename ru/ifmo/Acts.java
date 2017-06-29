package ru.ifmo;

public class Acts implements Comparable<Acts>{
    private String currentMethod;
    private String currentCharacter;

    public void setCurrentMethod(String currentMethod) {
        this.currentMethod = currentMethod.toUpperCase();

    }

    public void setCurrentCharacter(String currentCharacter) {
        this.currentCharacter = currentCharacter.toUpperCase();
    }

    public String getCurrentCharacter(){
        return this.currentCharacter;
    }

    public String getCurrentMethod(){
        return this.currentMethod;
    }

    public int compareTo (Acts act){
        return this.currentCharacter.compareTo(act.currentCharacter) == 0 ?
                this.currentMethod.compareTo(act.currentMethod) : this.currentCharacter.compareTo(act.currentCharacter);
    }

    Acts (String currentCharacter, String currentMethod){
        setCurrentCharacter(currentCharacter);
        setCurrentMethod(currentMethod);
    }
}
