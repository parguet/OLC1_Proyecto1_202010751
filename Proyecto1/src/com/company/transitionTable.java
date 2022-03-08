package com.company;

import java.util.ArrayList;

public class transitionTable {
    public ArrayList<ArrayList> states;
    public int cont;

    public transitionTable(Nodo root, ArrayList tabla, ArrayList<Nodo> leaves) {
        this.states = new ArrayList();

        ArrayList datos = new ArrayList();
        datos.add("S0");
        datos.add(root.first);
        datos.add(new ArrayList());
        datos.add(false);

        this.states.add(datos);
        this.cont = 1;

        for(int i = 0; i < states.size(); i++){
            ArrayList state = states.get(i);
            ArrayList<Integer> elementos = (ArrayList) state.get(1);

            // TODO  Aqui se encuentra el bug
            for(int hoja : elementos){
                followTable sigTabla = new followTable();
                ArrayList lexemeNext = (ArrayList) sigTabla.next(hoja, tabla).clone();


                boolean existe = false;
                String found = "";

                for( ArrayList e : states ){
                    if(e.get(1).equals(lexemeNext.get(1))){
                        existe = true;
                        found = (String)e.get(0);
                        break;
                    }
                }

                if(!existe){
                    leave hojas = new leave();
                    if(hojas.isAccept(hoja, leaves)){
                        state.set(3, true);
                    }
                    if(lexemeNext.get(0)==""){
                        continue;
                    }

                    ArrayList nuevo = new ArrayList();
                    nuevo.add("S"+cont);
                    nuevo.add(lexemeNext.get(1));
                    nuevo.add(new ArrayList());
                    nuevo.add(false);

                    Transicion trans = new Transicion(state.get(0) + "", lexemeNext.get(0) + "", nuevo.get(0) + "");
                    ((ArrayList)state.get(2)).add(trans);

                    cont += 1;
                    states.add(nuevo);

                }
                else{
                    leave hojas = new leave();
                    if(hojas.isAccept(hoja, leaves)){
                        state.set(3, true);
                    }

                    boolean trans_exist = false;

                    for(Object trans : (ArrayList)state.get(2)){
                        Transicion t = (Transicion) trans;
                        if(t.compare(state.get(0) + "", lexemeNext.get(0) + "")){
                            trans_exist = true;
                            break;
                        }
                    }
                    if(!trans_exist){
                        Transicion trans = new Transicion(state.get(0) + "", lexemeNext.get(0) + "", found + "");
                        ((ArrayList)state.get(2)).add(trans);
                    }
                }
            }

        }
    }

    public void impTable(){
        for(ArrayList state : states){
            String tran = "[";
            for(Object tr : (ArrayList)state.get(2)){
                Transicion t = (Transicion) tr;
                tran += t.toString() + ", ";
            }
            tran += "]";

            tran = tran.replace(", ]", "]");

            System.out.println(state.get(0) + " " + state.get(1) + " " + tran + " " + state.get(3));

        }
    }

    public String Automata(){
        String texto = "digraph G {\n rankdir=LR;\n label=\"AFD\"; \nnodex [style=invisible label = \"\"];\n";

        for(ArrayList state : states){
            texto += "node"+state.get(0) + " [shape=circle label=" +state.get(0) + "];\n";
        }
        texto+="Inicio->nodeS0;";

        for(ArrayList state : states){
            for(Object tr : (ArrayList)state.get(2)){
                Transicion t = (Transicion) tr;
                texto += t.graph();
            }

            if (state.get(3).equals(true)){
                texto += "node"+state.get(0) + " [shape=doublecircle label=" +state.get(0) + "];\n";
            }
        }


        return  texto +="}";
    }


    public String graphviz(ArrayList<ArrayList> table){
        StringBuilder dot = new StringBuilder();
        StringBuilder dot2 = new StringBuilder();

        dot.append("digraph G {\n");
        dot.append("bgcolor=\"slategrey\" label=\"Tabla de Transicion\" layout=dot \n" +
                "labelloc = \"t\" edge [weigth=1000  color=darkgreen  arrowtail=\"open\" arrowhead=\"open\"]\n");
        dot.append("node[shape=box, style=\"filled\", color=lightgrey];\n");
        dot.append("a0 [label=<\n" +
                "<TABLE border=\"10\" cellspacing=\"10\" cellpadding=\"10\" style=\"rounded\">");
        dot.append("<TR><TD> </TD>\n" );

        int size = 0;

        for(ArrayList item : table){
            size++;
            dot.append("<TD>"+item.get(1)+"</TD>\n");
        }
        dot.append("  </TR>\n");

        for(ArrayList state : states){

            dot.append("<TR><TD>"+state.get(0)+state.get(1)+"</TD>\n");

            ArrayList itemTemp = new ArrayList();
            for(ArrayList item : table){
                itemTemp.add(item.get(1));
            }
            String[] columnas = new String[itemTemp.size()];
            int c= 0;
            for(Object tr : (ArrayList)state.get(2)){
                Transicion t = (Transicion) tr;
                c= 0;
                for(Object item : itemTemp){
                    if (t.transition.equals(item)){
                        columnas[c] = t.finalState;
                        break;
                        //dot.append( "  <TD>"+t.finalState+"</TD>\n");
                    }//dot.append( "  <TD> </TD>\n");
                   c++;
                }
            }
            for (int i=0; i<columnas.length ; i++) {
                if (columnas[i]!=null){
                    dot.append( "  <TD>"+columnas[i]+"</TD>\n");
                }else{dot.append( "  <TD> </TD>\n");}
            }
            dot.append("</TR>\n");

        }


        dot.append("</TABLE>>];");
        dot.append("}");
        System.out.println( dot.toString());
        return dot.toString();


    }

}
