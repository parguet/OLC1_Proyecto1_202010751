package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfaz extends JFrame implements ActionListener {

    JButton archivo, generarAutomatas,generarEntradas;
    JTextArea area1, area2;


    public Interfaz (){
        this.setTitle("");
        this.setBounds(600, 250, 800, 610);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.darkGray);

        archivo = new JButton("Archivo");
        archivo.setBounds(15,15,85,20);

        area1 = new JTextArea();
        area1.setBounds(20, 45, 700, 300);

        area2 = new JTextArea();
        area2.setBounds(20, 370, 700, 125);

        generarAutomatas = new JButton("Generar Automatas");
        generarAutomatas.setBounds(20,525,200,25);

        generarEntradas = new JButton("Generar Entradas");
        generarEntradas.setBounds(250,525,200,25);





        this.add(archivo);
        this.add(area1);
        this.add(area2);
        this.add(generarAutomatas);
        this.add(generarEntradas);
    }








    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
