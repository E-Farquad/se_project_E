package co.edu.unal.unacompaamiento.model;

public class Estudiante {
    private int id;
    private float PAPA;
    private float PA;
    private float PAPI;
    private String Carrera;
    private int avance;
    private String Tutor;
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPAPA() {
        return PAPA;
    }

    public void setPAPA(float PAPA) {
        this.PAPA = PAPA;
    }

    public float getPA() {
        return PA;
    }

    public void setPA(float PA) {
        this.PA = PA;
    }

    public float getPAPI() {
        return PAPI;
    }

    public void setPAPI(float PAPI) {
        this.PAPI = PAPI;
    }

    public String getCarrera() {
        return Carrera;
    }

    public void setCarrera(String carrera) {
        Carrera = carrera;
    }

    public int getAvance() {
        return avance;
    }

    public void setAvance(int avance) {
        this.avance = avance;
    }

    public String getTutor() {
        return Tutor;
    }

    public void setTutor(String tutor) {
        Tutor = tutor;
    }
}
