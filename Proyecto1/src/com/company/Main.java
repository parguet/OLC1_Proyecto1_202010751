package com.company;

import analizadores.*;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static ArrayList<Conjunto> conjuntos = new ArrayList<Conjunto>();
    public static ArrayList<Expresion> expresiones = new ArrayList<Expresion>();
    public static ArrayList<Expresion> entradas = new ArrayList<Expresion>();

    public static void main(String[] args) {
        Expresion expresion = new Expresion("n", "C");

        Interfaz interfaz = new Interfaz();
        interfaz.setVisible(true);




    }


    public static void analizar(){
        try {
            Lexico lexico = new Lexico(new BufferedReader(new StringReader(Interfaz.area1.getText())));
            Sintactico sintactico = new Sintactico(lexico);
            sintactico.parse();
        } catch (Exception e) {
        }


        for (int i = 0; i < conjuntos.size(); i++) {
            System.out.println(conjuntos.get(i).getNombre() + " " + conjuntos.get(i).getCombinacion());
        }
        for (int i = 0; i < expresiones.size(); i++) {
            int estado = 0;
            String buffer = "";
            String centinela = "¬";
            String codigoFuente = expresiones.get(i).getExpresion() + centinela;
            char[] chars = codigoFuente.toCharArray();
            ArrayList<Character> cadaCaracter = new ArrayList<>();
            for (char ch : chars) {
                cadaCaracter.add(ch);
            }

            int j = 0;
            expresiones.get(i).insertarTerminalAlArray(".");
                while (j<cadaCaracter.size()){
                if (estado == 0){
                    if(cadaCaracter.get(j)==46){
                        expresiones.get(i).insertarTerminalAlArray(".");
                        j++;
                    }else if(cadaCaracter.get(j)==42){
                        expresiones.get(i).insertarTerminalAlArray("*");
                        j++;
                    } else if(cadaCaracter.get(j)==124){
                        expresiones.get(i).insertarTerminalAlArray("|");
                        j++;
                    }else if(cadaCaracter.get(j)==63){
                        expresiones.get(i).insertarTerminalAlArray("?");
                        j++;
                    }else if(cadaCaracter.get(j)==43){
                        expresiones.get(i).insertarTerminalAlArray("+");
                        j++;
                    }else if(cadaCaracter.get(j)==172){
                        System.out.println("se completo el casteo");
                        break;
                    }
                    else if (cadaCaracter.get(j)==123){
                        buffer += cadaCaracter.get(j);
                        j++;
                        estado = 1;
                    }
                    else if (cadaCaracter.get(j)==34){
                        buffer += cadaCaracter.get(j);
                        j++;
                        estado = 2;
                    }
                }else if (estado==1){
                    if(cadaCaracter.get(j)==125){
                        buffer += "}";
                        expresiones.get(i).insertarTerminalAlArray(buffer);
                        buffer = "";
                        j++;
                        estado = 0;
                    }else{
                        buffer+= cadaCaracter.get(j);
                        j++;
                    }
                }else if (estado==2){
                    if(cadaCaracter.get(j)==34){
                        buffer += "\"";
                        expresiones.get(i).insertarTerminalAlArray(buffer);
                        buffer = "";
                        j++;
                        estado = 0;
                    }else{
                        buffer+= cadaCaracter.get(j);
                        j++;
                    }

                }


            }
            expresiones.get(i).insertarTerminalAlArray("#");
            System.out.println(expresiones.get(i).getNombre() + " " + expresiones.get(i).getExpresion());
        }


        for (int i = 0; i < expresiones.size(); i++) {

            String er = "..a*|{c}{x}b";

            ArrayList<Nodo> leaves = new ArrayList();
            ArrayList<ArrayList> table = new ArrayList();

            er = "." + er + "#";


            Tree arbol = new Tree(expresiones.get(i).getExpresionArray(), leaves, table); // CREA EL ARBOL
            Nodo raiz = arbol.getRoot();

            raiz.getNode(); // DETERMINA SI LOS NODOS SON ANULABLES, SUS PRIMEROS Y ULTIMOS
            raiz.follow();


            System.out.println("==============================TABLA SIGUIENTES==============================");
            followTable ft = new followTable();
            ft.printTable(table);
            String graphviz = ft.graphviz(table);
            EscribirArchivo(graphviz,"./reportes/siguientes_202010751/Tabla"+(i+1)+".dot");
            ProcessBuilder proceso;
            proceso = new ProcessBuilder("dot", "-Tpng", "-o","./reportes/siguientes_202010751/Tabla"+(i+1)+".png","./reportes/siguientes_202010751/Tabla"+(i+1)+".dot");
            proceso.redirectErrorStream(true);
            try {
                proceso.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

            transitionTable tran = new transitionTable(raiz, table, leaves); // bug
            System.out.println("=============================TABLA TRANSICIONES=============================");
            tran.impTable();
            //tran.graphviz();



        }




    }

    public static void EscribirArchivo(String contenido, String ruta){
        try {

            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static String LeerArchivo(String ruta){
        //aqui se leera
        File archivo = null;
        FileReader fr = null;
        BufferedReader br;
        String contenido = "";
        try{
            archivo = new File(ruta);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido += linea;
            }
            return contenido;
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        //termina de hacer la lectura
        return contenido;


    }

}
