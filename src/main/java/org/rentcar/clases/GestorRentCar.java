package org.rentcar.clases;

import org.rentcar.modalidad.Modalidad;
import org.rentcar.servicios.IGestorRentCar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorRentCar implements IGestorRentCar {

    private final List<Cliente> clientes;
    private final List<Vehiculo> vehiculos;
    private final List<Modalidad> modalidades;
    private final List<ServicioAdicional> servicios;
    private final List<Reserva> reservas;

    public GestorRentCar() {
        this.clientes = new ArrayList<>();
        this.vehiculos = new ArrayList<>();
        this.modalidades = new ArrayList<>();
        this.servicios = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    @Override
    public void registrarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }

        if (buscarClientePorDocumento(cliente.getDocumento()) != null) {
            throw new IllegalArgumentException(
                    "Ya existe un cliente con ese documento"
            );
        }

        clientes.add(cliente);
    }

    @Override
    public void registrarVehiculo(Vehiculo vehiculo) {
        if (vehiculo == null) {
            throw new IllegalArgumentException("El vehículo no puede ser nulo");
        }

        if (buscarVehiculoPorPlaca(vehiculo.getPlaca()) != null) {
            throw new IllegalArgumentException(
                    "Ya existe un vehículo con esa placa"
            );
        }

        vehiculos.add(vehiculo);
    }

    @Override
    public void registrarModalidad(Modalidad modalidad) {
        if (modalidad == null) {
            throw new IllegalArgumentException("La modalidad no puede ser nula");
        }

        if (buscarModalidadPorCodigo(modalidad.getCodigo()) != null) {
            throw new IllegalArgumentException(
                    "Ya existe una modalidad con ese código"
            );
        }

        modalidades.add(modalidad);
    }

    @Override
    public void registrarServicio(ServicioAdicional servicio) {
        if (servicio == null) {
            throw new IllegalArgumentException("El servicio no puede ser nulo");
        }

        if (buscarServicioPorCodigo(servicio.getCodigo()) != null) {
            throw new IllegalArgumentException(
                    "Ya existe un servicio con ese código"
            );
        }

        servicios.add(servicio);
    }

    @Override
    public void registrarReserva(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("La reserva no puede ser nula");
        }

        reservas.add(reserva);
    }

    @Override
    public Cliente buscarClientePorDocumento(String documento) {
        for (Cliente cliente : clientes) {
            if (cliente.getDocumento().equals(documento)) {
                return cliente;
            }
        }

        return null;
    }

    @Override
    public Vehiculo buscarVehiculoPorPlaca(String placa) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getPlaca().equalsIgnoreCase(placa)) {
                return vehiculo;
            }
        }

        return null;
    }

    @Override
    public Modalidad buscarModalidadPorCodigo(String codigo) {
        for (Modalidad modalidad : modalidades) {
            if (modalidad.getCodigo().equalsIgnoreCase(codigo)) {
                return modalidad;
            }
        }

        return null;
    }

    @Override
    public ServicioAdicional buscarServicioPorCodigo(String codigo) {
        for (ServicioAdicional servicio : servicios) {
            if (servicio.getCodigo().equalsIgnoreCase(codigo)) {
                return servicio;
            }
        }

        return null;
    }

    @Override
    public Cliente buscarClientePorTelefono(String telefono) {

        for (Cliente cliente : clientes) {

            if (cliente.getTelefono().equals(telefono)) {
                return cliente;
            }
        }

        return null;
    }

    @Override
    public boolean eliminarClientePorDocumento(
            String documento) {

        return clientes.removeIf(
                cliente ->
                        cliente.getDocumento()
                                .equals(documento)
        );
    }

    @Override
    public boolean eliminarVehiculoPorPlaca(
            String placa) {

        return vehiculos.removeIf(
                vehiculo ->
                        vehiculo.getPlaca()
                                .equalsIgnoreCase(placa)
        );
    }

    @Override
    public boolean eliminarModalidadPorCodigo(
            String codigo) {

        return modalidades.removeIf(
                modalidad ->
                        modalidad.getCodigo()
                                .equalsIgnoreCase(codigo)
        );
    }

    @Override
    public boolean eliminarServicioPorCodigo(
            String codigo) {

        return servicios.removeIf(
                servicio ->
                        servicio.getCodigo()
                                .equalsIgnoreCase(codigo)
        );
    }

    @Override
    public boolean eliminarReservaPorId(
            String id) {

        return reservas.removeIf(
                reserva ->
                        reserva.getId().equals(id)
        );
    }

    @Override
    public List<Cliente> getClientes() {
        return Collections.unmodifiableList(clientes);
    }

    @Override
    public List<Vehiculo> getVehiculos() {
        return Collections.unmodifiableList(vehiculos);
    }

    @Override
    public List<Modalidad> getModalidades() {
        return Collections.unmodifiableList(modalidades);
    }

    @Override
    public List<ServicioAdicional> getServicios() {
        return Collections.unmodifiableList(servicios);
    }

    @Override
    public List<Reserva> getReservas() {
        return Collections.unmodifiableList(reservas);
    }
}