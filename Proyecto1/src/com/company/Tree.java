package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class Tree {
    Nodo root;
    public static String nombreNodos = "";
    public static String conexiones = "";
    public static String contenido = "";

    public Tree( ArrayList<String> strList, ArrayList<Nodo> leaves, ArrayList<ArrayList> table ) {

        numLeave numHoja = new numLeave(strList);
        Stack pila = new Stack();


        Collections.reverse(strList);
        StringBuilder dot = new StringBuilder();
        dot.append("digraph G {\n");
        dot.append(" layout=dot \n" +
                "labelloc = \"t\"edge [weigth=1000  color=darkgreen  arrowtail=\"open\" arrowhead=\"open\"]\n");

        nombreNodos = "";
        conexiones = "";
        strList.forEach((character) -> {
            switch (character) {
                case "|":
                    Nodo lefto = (Nodo) pila.pop();
                    Nodo righto = (Nodo) pila.pop();


                    Nodo no = new Nodo(character, Type.Types.OR, 0, lefto, righto, leaves, table);

                    // unir los dos nodos al nodo padre    poner sus first     poner sus last      y si es anulable
                    conexiones+=String.format("Nodo%d -> Nodo%d;\n", no.hashCode(), lefto.hashCode());
                    conexiones+=String.format("Nodo%d -> Nodo%d;\n", no.hashCode(), righto.hashCode());


                    pila.push(no);

                    break;
                case ".":
                    Nodo lefta = (Nodo) pila.pop();
                    Nodo righta = (Nodo) pila.pop();

                    Nodo na = new Nodo(character, Type.Types.AND, 0, lefta, righta, leaves, table);
                    pila.push(na);
                    // unir los dos nodos al nodo padre    poner sus first     poner sus last      y si es anulable
                    conexiones+=String.format("Nodo%d -> Nodo%d;\n", na.hashCode(), lefta.hashCode());
                    conexiones+=String.format("Nodo%d -> Nodo%d;\n", na.hashCode(), righta.hashCode());

                    break;
                case "*":
                    Nodo unario = (Nodo) pila.pop();

                    Nodo nk = new Nodo(character, Type.Types.KLEENE, 0, unario, null, leaves, table);
                    pila.push(nk);
                    // unor los dos nodos al nodo padre    poner sus first     poner sus last      y si es anulable
                    conexiones+=String.format("Nodo%d -> Nodo%d;\n", nk.hashCode(), unario.hashCode());

                    break;
                case "+":
                    Nodo unario1 = (Nodo) pila.pop();

                    Nodo nk1 = new Nodo(character, Type.Types.MAS, 0, unario1, null, leaves, table);
                    pila.push(nk1);
                    // unor los dos nodos al nodo padre    poner sus first     poner sus last      y si es anulable
                    conexiones+=String.format("Nodo%d -> Nodo%d;\n", nk1.hashCode(), unario1.hashCode());

                    break;
                case "?":
                    Nodo unario2 = (Nodo) pila.pop();

                    Nodo nk2 = new Nodo(character, Type.Types.INTERROGACION, 0, unario2, null, leaves, table);
                    pila.push(nk2);
                    // unor los dos nodos al nodo padre    poner sus first     poner sus last      y si es anulable
                    conexiones+=String.format("Nodo%d -> Nodo%d;\n", nk2.hashCode(), unario2.hashCode());
                    break;
                default:
                    Nodo nd = new Nodo(character, Type.Types.HOJA, numHoja.getNum(), null, null, leaves, table);
                    pila.push(nd);
                    leave hoja = new leave();
                    hoja.addLeave(nd, leaves);
                    break;
            }
        });
        this.root = (Nodo) pila.pop();
        dot.append(nombreNodos);
        dot.append(conexiones);
        contenido = dot.toString();
    }

    public Nodo getRoot(){
        return this.root;
    }
}
