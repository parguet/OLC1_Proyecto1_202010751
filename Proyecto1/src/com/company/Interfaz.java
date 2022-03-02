package com.company;

import analizadores.Lexico;
import analizadores.Sintactico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

public class Interfaz extends JFrame implements ActionListener {

    public static JButton archivo, generarAutomatas,generarEntradas;
    public static JTextArea area1, area2;



    public Interfaz (){
        this.setTitle("");
        this.setBounds(600, 250, 800, 610);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.darkGray);

        archivo = new JButton("Archivo");
        archivo.setBounds(15,15,85,20);
        archivo.addActionListener(this);

        area1 = new JTextArea();

        JScrollPane scroll = new JScrollPane(area1);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(20, 45, 700, 300);

        area2 = new JTextArea();
        area2.setBounds(20, 370, 700, 125);

        generarAutomatas = new JButton("Generar Automatas");
        generarAutomatas.setBounds(20,525,200,25);
        generarAutomatas.addActionListener(this);

        generarEntradas = new JButton("Analizar Entradas");
        generarEntradas.setBounds(250,525,200,25);
        generarEntradas.addActionListener(this);





        this.add(archivo);
        this.add(scroll);
        this.add(area2);
        this.add(generarAutomatas);
        this.add(generarEntradas);

    }








    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==archivo){
            InterfazArchivo interfazArchivo = new InterfazArchivo();
            interfazArchivo.setVisible(true);

        }
        if (e.getSource()==generarEntradas){
            try {

                Lexico lexico = new Lexico(
                        new BufferedReader(new FileReader("C:\\Users\\usuario\\Desktop\\PROGRA\\Compi1\\OLC1_Proyecto1_202010751\\entrada_seccionC\\entrada_seccionC\\a ver si esto es tu talla xD\\entrada.txt"))
                );
                Sintactico sintactico = new Sintactico(lexico);
                sintactico.parse();

            } catch (Exception s) {
            }

        }

    }
}
