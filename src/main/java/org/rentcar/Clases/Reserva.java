package org.rentcar.Clases;

import org.rentcar.modalidad.Modalidad;

import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private String fechaReserva;
    private int diasAlquiler;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private Modalidad modalidad;
    private List<ServicioAdicional> serviciosAdicionales;

    public Reserva(String fechaReserva, int diasAlquiler, Cliente cliente, Vehiculo vehiculo, Modalidad modalidad) {
        this.fechaReserva = fechaReserva;
        this.diasAlquiler = diasAlquiler;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.modalidad = modalidad;
        this.serviciosAdicionales = new ArrayList<>();
    }

    public void agregarServicioAdicional(ServicioAdicional servicio) {
        if (servicio.isDisponibilidad()) {
            this.serviciosAdicionales.add(servicio);
        }
    }

    public double calcularValorFinal() {
        double costoBase = modalidad.calcularCostoTotal(diasAlquiler);
        double costoServicios = 0;

        for (ServicioAdicional servicio : serviciosAdicionales) {
            costoServicios += servicio.getPrecio();
        }

        return costoBase + costoServicios;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }
}