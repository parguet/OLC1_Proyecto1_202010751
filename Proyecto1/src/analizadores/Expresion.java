package analizadores;

import java.util.ArrayList;

public class Expresion {
    private String nombre;
    private String expresion;
    private ArrayList<String> expresionArray;

    public Expresion(String nombre, String expresion){
        this.nombre = nombre;
        this.expresion = expresion;
        this.expresionArray = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getExpresion() {
        return expresion;
    }

    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }

    public void insertarTerminalAlArray(String terminal){
        this.expresionArray.add(terminal);
    }

    public ArrayList<String> getExpresionArray() {
        return expresionArray;
    }

    public void setExpresionArray(ArrayList<String> expresionArray) {
        this.expresionArray = expresionArray;
    }
}
