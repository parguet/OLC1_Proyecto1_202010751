package com.company;

public class Transicion {
    public String initialState;
    public String transition;
    public String finalState;

    public Transicion( String initialState, String transition, String finalState ) {
        this.initialState = initialState;
        this.transition = transition;
        this.finalState = finalState;
    }

    public boolean compare(String initialState, String transition){

        return this.initialState.equals(initialState) && this.transition.equals(transition);
    }

    @Override
    public String toString(){
        return this.initialState + " - " + this.transition + " - " + this.finalState;
    }
    public String graph(){
        return "\n node"+this.initialState+ " -> node"+this.finalState + " [label=\""+this.transition.replace("\"","")+"\"];\n";
    }
}
