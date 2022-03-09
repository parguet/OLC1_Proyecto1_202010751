package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class Tree {
    Nodo root;
    public static String conexiones = "";
    public static String contenido = "";
    public static String contenido2 = "";
    public static String conexiones2 = "";

    public Tree( ArrayList<String> strList, ArrayList<Nodo> leaves, ArrayList<ArrayList> table ) {

        numLeave numHoja = new numLeave(strList);
        Stack pila = new Stack();


        Collections.reverse(strList);
        StringBuilder dot = new StringBuilder();
        StringBuilder dot2 = new StringBuilder();
        dot.append("digraph G {\n");
        dot.append(" layout=dot \n" +
                "labelloc = \"t\"edge [weigth=1000  color=darkgreen  arrowtail=\"open\" arrowhead=\"open\"]\n");
        dot2.append(dot.toString());
        dot2.append("\nrankdir=\"LR\"");



        conexiones = "";
        conexiones2 = "";
        strList.forEach((character) -> {
            switch (character) {
                case "|":
                    Nodo lefto = (Nodo) pila.pop();
                    Nodo righto = (Nodo) pila.pop();


                    Nodo no = new Nodo(character, Type.Types.OR, 0, lefto, righto, leaves, table);

                    // unir los dos nodos al nodo padre    poner sus first     poner sus last      y si es anulable
                    conexiones+=String.format("Nodo%d -> Nodo%d;\n", no.hashCode(), lefto.hashCode());
                    conexiones+=String.format("Nodo%d -> Nodo%d;\n", no.hashCode(), righto.hashCode());


                    conexiones2+="Nodo"+no.hashCode()+ " -> Nodo"+ lefto.hashCode()+"[label=\"ε\"]\n";
                    conexiones2+="Nodo"+no.hashCode()+ " -> Nodo"+ righto.hashCode()+"[label=\"ε\"]\n";

                    if(lefto.lexeme.equals("\"" + "\\" + "n" + "\"")){ lefto.lexeme="\\"+"\\"+"n"; }if(lefto.lexeme.equals("\""+"\\"+"\"")){ lefto.lexeme="''"; }
                    if(righto.lexeme.equals("\"" + "\\" + "n" + "\"")){ righto.lexeme="\\"+"\\"+"n"; }if(righto.lexeme.equals("\""+"\\"+"\"")){ righto.lexeme="''"; }
                    conexiones2+="Nodo"+lefto.hashCode()+ " -> Nodo"+ lefto.hashCode()+"D[label=\""+lefto.lexeme.replace("\"","")+"\"]\n";
                    conexiones2+="Nodo"+righto.hashCode()+ " -> Nodo"+ righto.hashCode()+"D[label=\""+righto.lexeme.replace("\"","")+"\"]\n";
                    conexiones2+="Nodo"+lefto.hashCode()+ "D -> Nodo"+ no.hashCode()+"D[label=\"ε\"]\n";
                    conexiones2+="Nodo"+righto.hashCode()+ "D -> Nodo"+ no.hashCode()+"D[label=\"ε\"]\n";


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

                    if(lefta.lexeme.equals("\"" + "\\" + "n" + "\"")){ lefta.lexeme="\\"+"\\"+"n"; }if(lefta.lexeme.equals("\""+"\\"+"\"")){ lefta.lexeme="''"; }
                    if(righta.lexeme.equals("\"" + "\\" + "n" + "\"")){ righta.lexeme="\\"+"\\"+"n"; }if(righta.lexeme.equals("\""+"\\"+"\"")){ righta.lexeme="''"; }
                    conexiones2+="Nodo"+na.hashCode()+ " -> Nodo"+ lefta.hashCode()+"[label=\""+lefta.lexeme.replace("\"","")+"\"]\n";
                    conexiones2+="Nodo"+lefta.hashCode()+ " -> Nodo"+ righta.hashCode()+"[label=\""+righta.lexeme.replace("\"","")+"\"]\n";


                    break;
                case "*":
                    Nodo unario = (Nodo) pila.pop();

                    Nodo nk = new Nodo(character, Type.Types.KLEENE, 0, unario, null, leaves, table);
                    pila.push(nk);
                    // unor los dos nodos al nodo padre    poner sus first     poner sus last      y si es anulable
                    conexiones+=String.format("Nodo%d -> Nodo%d;\n", nk.hashCode(), unario.hashCode());


                    conexiones2+="Nodo"+nk.hashCode()+ " -> Nodo"+ unario.hashCode()+"[label=\"ε\"] \n";
                    conexiones2+="Nodo"+nk.hashCode()+ " -> Nodo"+ nk.hashCode()+"D[label=\"ε\"] \n";
                    if(unario.lexeme.equals("\"" + "\\" + "n" + "\"")){ unario.lexeme="\\"+"\\"+"n"; }if(unario.lexeme.equals("\""+"\\"+"\"")){ unario.lexeme="''"; }
                    conexiones2+="Nodo"+unario.hashCode()+ " -> Nodo"+ unario.hashCode()+"D[label=\""+unario.lexeme.replace("\"","")+"\"] \n";
                    conexiones2+="Nodo"+unario.hashCode()+ "D -> Nodo"+ unario.hashCode()+"[label=\"ε\"]\n";
                    conexiones2+="Nodo"+unario.hashCode()+ "D -> Nodo"+ nk.hashCode()+"D[label=\"ε\"] \n";


                    break;
                case "+":
                    Nodo unario1 = (Nodo) pila.pop();

                    Nodo nk1 = new Nodo(character, Type.Types.MAS, 0, unario1, null, leaves, table);
                    pila.push(nk1);
                    // unor los dos nodos al nodo padre    poner sus first     poner sus last      y si es anulable
                    conexiones+=String.format("Nodo%d -> Nodo%d;\n", nk1.hashCode(), unario1.hashCode());


                    conexiones2+="Nodo"+nk1.hashCode()+ " -> Nodo"+ unario1.hashCode()+"D[label=\"ε\"]\n";
                    if(unario1.lexeme.equals("\"" + "\\" + "n" + "\"")){ unario1.lexeme="\\"+"\\"+"n"; }if(unario1.lexeme.equals("\""+"\\"+"\"")){ unario1.lexeme="''"; }
                    conexiones2+="Nodo"+unario1.hashCode()+ " -> Nodo"+ unario1.hashCode()+"D[label=\""+unario1.lexeme.replace("\"","")+"\"] \n";
                    conexiones2+="Nodo"+unario1.hashCode()+ "D -> Nodo"+ unario1.hashCode()+"[label=\"ε\"]\n";
                    conexiones2+="Nodo"+unario1.hashCode()+ "D -> Nodo"+ nk1.hashCode()+"D[label=\"ε\"] \n";

                    break;
                case "?":
                    Nodo unario2 = (Nodo) pila.pop();

                    Nodo nk2 = new Nodo(character, Type.Types.INTERROGACION, 0, unario2, null, leaves, table);
                    pila.push(nk2);
                    // unor los dos nodos al nodo padre    poner sus first     poner sus last      y si es anulable
                    conexiones+=String.format("Nodo%d -> Nodo%d;\n", nk2.hashCode(), unario2.hashCode());


                    conexiones2+="Nodo"+nk2.hashCode()+ " -> Nodo"+ unario2.hashCode()+"[label=\"ε\"] \n";
                    conexiones2+="Nodo"+nk2.hashCode()+ " -> Nodo"+ nk2.hashCode()+"D[label=\"ε\"] \n";
                    if(unario2.lexeme.equals("\"" + "\\" + "n" + "\"")){ unario2.lexeme="\\"+"\\"+"n"; }if(unario2.lexeme.equals("\""+"\\"+"\"")){ unario2.lexeme="''"; }
                    conexiones2+="Nodo"+unario2.hashCode()+ " -> Nodo"+ unario2.hashCode()+"D[label=\""+unario2.lexeme.replace("\"","")+"\"] \n";
                    conexiones2+="Nodo"+unario2.hashCode()+ "D -> Nodo"+ nk2.hashCode()+"D[label=\"ε\"] \n";
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
        dot.append(conexiones);
        dot2.append(conexiones2);
        contenido = dot.toString();
        contenido2 = dot2.toString();
    }

    public Nodo getRoot(){
        return this.root;
    }
}
