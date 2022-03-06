package analizadores;

public class Conjunto {
    private String nombre;
    private String combinacion;


    public Conjunto(String nombre, String combinacion){
        this.nombre=nombre;
        this.combinacion=combinacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCombinacion() {
        return combinacion;
    }

    public void setCombinacion(String combinacion) {
        this.combinacion = combinacion;
    }
}
