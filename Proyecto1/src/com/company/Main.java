package com.company;

import analizadores.Lexico;
import analizadores.Sintactico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) {

        try {

            Lexico lexico = new Lexico(
                    new BufferedReader(new FileReader("C:\\Users\\usuario\\Desktop\\PROGRA\\Compi1\\OLC1_Proyecto1_202010751\\entrada_seccionC\\entrada_seccionC\\a ver si esto es tu talla xD\\entrada.txt"))
            );
            Sintactico sintactico = new Sintactico(lexico);
            sintactico.parse();

        } catch (Exception e) {
        }

        //Interfaz interfaz = new Interfaz();
        //interfaz.setVisible(true);

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
