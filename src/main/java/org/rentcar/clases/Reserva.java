package org.rentcar.clases;

import org.rentcar.modalidad.Modalidad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Reserva {

    private final String id;

    private String fechaReserva;
    private int diasAlquiler;

    private Cliente cliente;
    private Vehiculo vehiculo;
    private Modalidad modalidad;

    private final List<ServicioAdicional> serviciosAdicionales;

    private double porcentajeDescuento;

    public Reserva(
            String fechaReserva,
            int diasAlquiler,
            Cliente cliente,
            Vehiculo vehiculo,
            Modalidad modalidad,
            double porcentajeDescuento) {

        if (diasAlquiler <= 0) {
            throw new IllegalArgumentException(
                    "Los días de alquiler deben ser mayores que cero."
            );
        }

        if (porcentajeDescuento < 0 || porcentajeDescuento > 100) {
            throw new IllegalArgumentException(
                    "El descuento debe estar entre 0 y 100."
            );
        }

        if (cliente == null) {
            throw new IllegalArgumentException(
                    "El cliente es obligatorio."
            );
        }

        if (vehiculo == null) {
            throw new IllegalArgumentException(
                    "El vehículo es obligatorio."
            );
        }

        if (modalidad == null) {
            throw new IllegalArgumentException(
                    "La modalidad es obligatoria."
            );
        }

        this.id = UUID.randomUUID().toString();

        this.fechaReserva = fechaReserva;
        this.diasAlquiler = diasAlquiler;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.modalidad = modalidad;
        this.porcentajeDescuento = porcentajeDescuento;

        this.serviciosAdicionales = new ArrayList<>();
    }

    public void agregarServicioAdicional(
            ServicioAdicional servicio) {

        if (servicio == null) {
            throw new IllegalArgumentException(
                    "El servicio no puede ser nulo."
            );
        }

        if (servicio.isDisponibilidad()) {
            serviciosAdicionales.add(servicio);
        }
    }

    public double calcularSubtotal() {

        double costoBase =
                modalidad.calcularCostoTotal(
                        diasAlquiler
                );

        double costoServicios = 0;

        for (ServicioAdicional servicio
                : serviciosAdicionales) {

            costoServicios += servicio.getPrecio();
        }

        return costoBase + costoServicios;
    }

    public double calcularValorDescuento() {

        return calcularSubtotal()
                * porcentajeDescuento
                / 100;
    }

    public double calcularValorFinal() {

        return calcularSubtotal()
                - calcularValorDescuento();
    }

    public String getId() {
        return id;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public int getDiasAlquiler() {
        return diasAlquiler;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Modalidad getModalidad() {
        return modalidad;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public List<ServicioAdicional> getServiciosAdicionales() {

        return Collections.unmodifiableList(
                serviciosAdicionales
        );
    }
}