package com.company;

import analizadores.Lexico;
import analizadores.Sintactico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Interfaz extends JFrame implements ActionListener {

    public static JButton archivo, generarAutomatas,generarEntradas,verImagen;
    public static JTextArea area1, area2;
    public static JTextPane area3;
    public static JFileChooser escogerImagen;


    public Interfaz (){
        this.setTitle("");
        this.setBounds(600, 250, 1180, 610);
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

        area3 = new JTextPane();
        JScrollPane scroll3 = new JScrollPane(area3);
        scroll3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll3.setBounds(740, 45, 400, 400);


        generarAutomatas = new JButton("Generar Automatas");
        generarAutomatas.setBounds(20,525,200,25);
        generarAutomatas.addActionListener(this);

        generarEntradas = new JButton("Analizar Entradas");
        generarEntradas.setBounds(250,525,200,25);
        generarEntradas.addActionListener(this);

        verImagen = new JButton("Ver Imagen");
        verImagen.setBounds(880,470,100,20);
        verImagen.addActionListener(this);





        this.add(archivo);
        this.add(scroll);
        this.add(area2);
        this.add(scroll3);
        this.add(verImagen);
        this.add(generarAutomatas);
        this.add(generarEntradas);

    }








    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==archivo){
            InterfazArchivo interfazArchivo = new InterfazArchivo();
            interfazArchivo.setVisible(true);

            String d = "\""+"\\"+"\"";
        }
        if (e.getSource()==generarEntradas){
            Main.analizar();
        }
        if (e.getSource()==verImagen) {
            escogerImagen = new JFileChooser();
            int returnVal = escogerImagen.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                area3.setText("");
                File imagen = escogerImagen.getSelectedFile();
                area3.insertIcon(new ImageIcon(String.valueOf(imagen)));
            }
        }
    }

}
