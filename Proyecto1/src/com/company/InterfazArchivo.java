package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class InterfazArchivo extends JFrame implements ActionListener {


    JButton abrir, guardar, guardarComo, generarXML;

    public InterfazArchivo(){

        this.setTitle("Archivo");
        this.setBounds(600, 250, 165, 250);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.darkGray);

        abrir = new JButton("Abrir");
        abrir.setBounds(30,15,85,20);
        abrir.addActionListener(this);

        guardar = new JButton("Guardar");
        guardar.setBounds(30,50,85,20);

        guardarComo = new JButton("Guardar como");
        guardarComo.setBounds(15,85,120,20);

        generarXML = new JButton("Generar XML");
        generarXML.setBounds(15,120,120,20);

        this.add(abrir);
        this.add(guardar);
        this.add(guardarComo);
        this.add(generarXML);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==abrir){
            String contenido = LeerArchivo();
            System.out.println(contenido);
            Interfaz.area1.setText(contenido);
            this.dispose();
        }
        if (e.getSource()==guardar){
            String contenido = LeerArchivo();
            Interfaz.area1.append(contenido);
        }
    }








    public String LeerArchivo(){
        //aqui se leera
        File archivo = null;
        FileReader fr = null;
        BufferedReader br;
        String contenido = "";
        try{
            JFileChooser fc = new JFileChooser();
            int op = fc.showOpenDialog(this);
            if (op == JFileChooser.APPROVE_OPTION) {
                archivo = fc.getSelectedFile();
            }
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                 contenido += linea;
                 contenido+= "\n";
            }
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
