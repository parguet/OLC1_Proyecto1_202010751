package com.company;

import java.util.ArrayList;

public class leave {
    public void addLeave(Nodo nodo, ArrayList<Nodo> leaves){
        leaves.add(nodo);
    }

    public Nodo getLeave(int numLeave, ArrayList<Nodo> leaves){
        for (Nodo item : leaves) {
            if(item.number == numLeave) return item;
        }
        return null;
    }

    public boolean isAccept(int numLeave, ArrayList<Nodo> leaves){
        for (Nodo item : leaves) {
            if(item.number == numLeave) return item.accept;
        }
        return false;
    }
}
