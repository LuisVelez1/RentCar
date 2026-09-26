package org.rentcar.controlador;

import org.rentcar.clases.Cliente;
import org.rentcar.servicios.IGestorRentCar;
import org.rentcar.servicios.IValidadorNumeroPerfecto;

public class Controlador {

    private final IGestorRentCar gestorRentCar;
    private final IValidadorNumeroPerfecto validadorNumeroPerfecto;

    public Controlador(
            IGestorRentCar gestorRentCar,
            IValidadorNumeroPerfecto validadorNumeroPerfecto) {

        if (gestorRentCar == null) {
            throw new IllegalArgumentException(
                    "El gestor de RentCar no puede ser nulo"
            );
        }

        if (validadorNumeroPerfecto == null) {
            throw new IllegalArgumentException(
                    "El validador de número perfecto no puede ser nulo"
            );
        }

        this.gestorRentCar = gestorRentCar;
        this.validadorNumeroPerfecto = validadorNumeroPerfecto;
    }

    public void registrarCliente(Cliente cliente) {
        gestorRentCar.registrarCliente(cliente);
    }

    public String verificarTelefonoPerfecto(String numeroTelefono) {

        Cliente clienteEncontrado =
                gestorRentCar.buscarClientePorTelefono(numeroTelefono);

        if (clienteEncontrado == null) {
            return "Cliente no encontrado con ese teléfono.";
        }

        long telefonoNumerico;

        try {
            telefonoNumerico = Long.parseLong(numeroTelefono);

        } catch (NumberFormatException e) {

            return "El teléfono del cliente no contiene un número válido.";
        }

        boolean esPerfecto =
                validadorNumeroPerfecto.esNumeroPerfecto(
                        telefonoNumerico
                );

        if (esPerfecto) {
            return "El teléfono del cliente "
                    + clienteEncontrado.getNombreCompleto()
                    + " ES un número perfecto.";
        }

        return "El teléfono del cliente "
                + clienteEncontrado.getNombreCompleto()
                + " NO es un número perfecto.";
    }

    public IGestorRentCar getGestorRentCar() {
        return gestorRentCar;
    }
}