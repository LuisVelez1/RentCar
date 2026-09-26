package org.rentcar.servicios;

import org.rentcar.clases.Cliente;
import org.rentcar.clases.Reserva;
import org.rentcar.clases.ServicioAdicional;
import org.rentcar.clases.Vehiculo;
import org.rentcar.modalidad.Modalidad;

import java.util.List;

public interface IGestorRentCar {

    void registrarCliente(Cliente cliente);

    void registrarVehiculo(Vehiculo vehiculo);

    void registrarModalidad(Modalidad modalidad);

    void registrarServicio(ServicioAdicional servicio);

    void registrarReserva(Reserva reserva);


    Cliente buscarClientePorDocumento(
            String documento
    );

    Cliente buscarClientePorTelefono(
            String telefono
    );

    Vehiculo buscarVehiculoPorPlaca(
            String placa
    );

    Modalidad buscarModalidadPorCodigo(
            String codigo
    );

    ServicioAdicional buscarServicioPorCodigo(
            String codigo
    );


    boolean eliminarClientePorDocumento(
            String documento
    );

    boolean eliminarVehiculoPorPlaca(
            String placa
    );

    boolean eliminarModalidadPorCodigo(
            String codigo
    );

    boolean eliminarServicioPorCodigo(
            String codigo
    );

    boolean eliminarReservaPorId(
            String id
    );


    List<Cliente> getClientes();

    List<Vehiculo> getVehiculos();

    List<Modalidad> getModalidades();

    List<ServicioAdicional> getServicios();

    List<Reserva> getReservas();
}