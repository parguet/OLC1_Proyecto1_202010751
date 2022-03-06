package com.company;

import java.util.ArrayList;

public class numLeave {
    public int content;

    public numLeave(ArrayList<String> strList) {
        this.content = clean(strList) + 1;
    }

    public int getNum(){
        content -= 1;
        return content;
    }


    public int clean(ArrayList<String> strList){
        int contador=0;
        for (int i = 0; i <strList.size() ; i++) {
            if(!strList.get(i).equals(".") && !strList.get(i).equals("*") && !strList.get(i).equals("|")  && !strList.get(i).equals("+")  && !strList.get(i).equals("?")){
                contador++;
            }
        }
        return contador;
    }
}
