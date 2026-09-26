package org.rentcar.modalidad;

import java.util.Collections;
import java.util.List;

public abstract class Modalidad {

    protected String codigo;
    protected String nombre;
    protected String descripcion;
    protected int duracionMinimaDias;
    protected double valorDiario;
    protected String estado;
    protected List<String> beneficios;

    public Modalidad(
            String codigo,
            String nombre,
            String descripcion,
            int duracionMinimaDias,
            double valorDiario,
            List<String> beneficios) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracionMinimaDias = duracionMinimaDias;
        this.valorDiario = valorDiario;
        this.estado = "Disponible";
        this.beneficios = beneficios;
    }

    public abstract double calcularCostoTotal(int dias);

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getDuracionMinimaDias() {
        return duracionMinimaDias;
    }

    public double getValorDiario() {
        return valorDiario;
    }

    public String getEstado() {
        return estado;
    }

    public List<String> getBeneficios() {
        return Collections.unmodifiableList(beneficios);
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}