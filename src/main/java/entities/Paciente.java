package entities;

import *;
import java.time.LocalDate;

public class Paciente {
    private int idPaciente;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaNacimiento;
    private boolean eliminado;
    private HistoriaClinica historia; // Relación unidireccional

    // Constructor vacío
    public Paciente() {
    }

    // Constructor completo
    public Paciente(int idPaciente, String nombre, String apellido, String dni,
            LocalDate fechaNacimiento, boolean eliminado, HistoriaClinica historia) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.eliminado = eliminado;
        this.historia = historia;
    }

    // Método toString para mostrar el paciente completo
    @Override
    public String toString() {
        return "Paciente{" +
               "idPaciente=" + idPaciente +
               ", nombre='" + nombre + '\'' +
               ", apellido='" + apellido + '\'' +
               ", dni='" + dni + '\'' +
               ", fechaNacimiento=" + fechaNacimiento +
               ", eliminado=" + eliminado +
               ", historia=" + (historia != null ? historia.getIdHistoria() : "sin historia") +
               '}';
    }

    // Getters y setters
    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public HistoriaClinica getHistoria() {
        return historia;
    }

    public void setHistoria(HistoriaClinica historia) {
        this.historia = historia;
    }
}