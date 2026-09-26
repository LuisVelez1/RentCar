package org.example;

import org.example.modalidad.Modalidad;

import java.util.ArrayList;
import java.util.List;

public class reserva {
    private String fechaReserva;
    private int diasAlquiler;
    private cliente cliente;
    private vehiculo vehiculo;
    private Modalidad modalidad;
    private List<servicioAdicional> serviciosAdicionales;

    public reserva(String fechaReserva, int diasAlquiler, cliente cliente, vehiculo vehiculo, Modalidad modalidad) {
        this.fechaReserva = fechaReserva;
        this.diasAlquiler = diasAlquiler;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.modalidad = modalidad;
        this.serviciosAdicionales = new ArrayList<>();
    }

    public void agregarServicioAdicional(servicioAdicional servicio) {
        if (servicio.isDisponibilidad()) {
            this.serviciosAdicionales.add(servicio);
        }
    }

    public double calcularValorFinal() {
        double costoBase = modalidad.calcularCostoTotal(diasAlquiler);
        double costoServicios = 0;

        for (servicioAdicional servicio : serviciosAdicionales) {
            costoServicios += servicio.getPrecio();
        }

        return costoBase + costoServicios;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public cliente getCliente() {
        return cliente;
    }

    public vehiculo getVehiculo() {
        return vehiculo;
    }
}