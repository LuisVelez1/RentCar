package org.rentcar.controlador;
import org.rentcar.cliente;

import java.util.ArrayList;
import java.util.List;

public class controlador {

    private List<cliente> listaClientes;

    public controlador() {
        this.listaClientes = new ArrayList<>();
    }

    public void registrarCliente(cliente cliente) {
        listaClientes.add(cliente);
    }

    public String verificarTelefonoPerfecto(String numeroTelefono) {
        cliente clienteEncontrado = null;
        for (cliente c : listaClientes) {
            if (numeroTelefono.equals(c.getTelefono())) {
                clienteEncontrado = c;
                break;
            }
        }

        if (clienteEncontrado == null) {
            return "Cliente no encontrado con ese teléfono.";
        }

        long telefonoNumerico = Long.parseLong(numeroTelefono);

        if (esNumeroPerfecto(telefonoNumerico)) {
            return "El teléfono del cliente " + clienteEncontrado.getNombreCompleto() + " ES un número perfecto.";
        } else {
            return "El teléfono del cliente " + clienteEncontrado.getNombreCompleto() + " NO es un número perfecto.";
        }
    }

    private boolean esNumeroPerfecto(long numero) {
        if (numero <= 1) return false;

        long sumaDivisores = 0;
        for (long i = 1; i <= numero / 2; i++) {
            if (numero % i == 0) {
                sumaDivisores += i;
            }
        }

        return sumaDivisores == numero;
    }
}