package org.rentcar.web;

import org.rentcar.clases.Cliente;
import org.rentcar.clases.Reserva;
import org.rentcar.clases.ServicioAdicional;
import org.rentcar.clases.Vehiculo;
import org.rentcar.controlador.Controlador;
import org.rentcar.modalidad.Modalidad;

import java.util.List;
import java.util.stream.Collectors;

public class WebBridge {

    private final Controlador controlador;

    public WebBridge(Controlador controlador) {

        if (controlador == null) {
            throw new IllegalArgumentException(
                    "El controlador no puede ser nulo."
            );
        }

        this.controlador = controlador;
    }


    public String probarConexion() {
        return controlador.probarConexion();
    }


    public String guardarEmpresa(
            String nombre,
            String nit,
            String direccion,
            String telefono,
            String correo,
            String web) {

        return controlador.guardarEmpresa(
                nombre,
                nit,
                direccion,
                telefono,
                correo,
                web
        );
    }


    public String registrarCliente(
            String nombre,
            String documento,
            String telefono,
            String correo,
            int edad,
            String fecha) {

        return controlador.registrarCliente(
                nombre,
                documento,
                telefono,
                correo,
                edad,
                fecha
        );
    }


    public String registrarVehiculo(
            String placa,
            String marca,
            String modelo,
            int ano,
            String tipo,
            double tarifa) {

        return controlador.registrarVehiculo(
                placa,
                marca,
                modelo,
                ano,
                tipo,
                tarifa
        );
    }


    public String registrarModalidad(
            String codigo,
            String tipo,
            String descripcion,
            int duracion,
            double valor,
            String estado,
            String beneficios,
            String cobertura,
            int conductores,
            String caracteristicas) {

        return controlador.registrarModalidad(
                tipo,
                codigo,
                descripcion,
                duracion,
                valor,
                estado,
                beneficios,
                cobertura,
                conductores,
                caracteristicas
        );
    }


    public String registrarServicio(
            String codigo,
            String nombre,
            String descripcion,
            double precio,
            String disponibilidad) {

        return controlador.registrarServicio(
                codigo,
                nombre,
                descripcion,
                precio,
                disponibilidad
        );
    }


    public String registrarReserva(
            String documentoCliente,
            String placaVehiculo,
            String codigoModalidad,
            String fecha,
            int dias,
            double descuento,
            String servicios) {

        return controlador.registrarReserva(
                documentoCliente,
                placaVehiculo,
                codigoModalidad,
                fecha,
                dias,
                descuento,
                servicios
        );
    }


    public String verificarTelefonoPerfecto(
            String telefono) {

        return controlador.verificarTelefonoPerfecto(
                telefono
        );
    }


    public String obtenerResumenIngresos(
            String desde,
            String hasta) {

        try {

            double total =
                    controlador.calcularIngresosPorPeriodo(
                            desde,
                            hasta
                    );

            int cantidad =
                    controlador.contarReservasPorPeriodo(
                            desde,
                            hasta
                    );

            return "{"
                    + "\"ok\":true,"
                    + "\"cantidad\":" + cantidad + ","
                    + "\"total\":" + total
                    + "}";

        } catch (Exception e) {

            return "{"
                    + "\"ok\":false,"
                    + "\"mensaje\":\""
                    + escapar(e.getMessage())
                    + "\""
                    + "}";
        }
    }


    public String obtenerClientes() {

        List<Cliente> clientes =
                controlador.obtenerClientes();

        return clientes.stream()
                .map(cliente ->
                        "{"
                                + "\"nombre\":\""
                                + escapar(cliente.getNombreCompleto())
                                + "\","
                                + "\"documento\":\""
                                + escapar(cliente.getDocumento())
                                + "\","
                                + "\"telefono\":\""
                                + escapar(cliente.getTelefono())
                                + "\","
                                + "\"correo\":\""
                                + escapar(cliente.getCorreoElectronico())
                                + "\","
                                + "\"edad\":"
                                + cliente.getEdad()
                                + ","
                                + "\"fecha\":\""
                                + escapar(cliente.getFechaRegistro())
                                + "\""
                                + "}"
                )
                .collect(
                        Collectors.joining(
                                ",",
                                "[",
                                "]"
                        )
                );
    }


    public String obtenerVehiculos() {

        List<Vehiculo> vehiculos =
                controlador.obtenerVehiculos();

        return vehiculos.stream()
                .map(vehiculo ->
                        "{"
                                + "\"placa\":\""
                                + escapar(vehiculo.getPlaca())
                                + "\","
                                + "\"marca\":\""
                                + escapar(vehiculo.getMarca())
                                + "\","
                                + "\"modelo\":\""
                                + escapar(vehiculo.getModelo())
                                + "\","
                                + "\"ano\":"
                                + vehiculo.getAno()
                                + ","
                                + "\"tipo\":\""
                                + escapar(vehiculo.getTipo())
                                + "\","
                                + "\"tarifa\":"
                                + vehiculo.getTarifaDiaria()
                                + "}"
                )
                .collect(
                        Collectors.joining(
                                ",",
                                "[",
                                "]"
                        )
                );
    }


    public String obtenerModalidades() {

        List<Modalidad> modalidades =
                controlador.obtenerModalidades();

        return modalidades.stream()
                .map(modalidad ->
                        "{"
                                + "\"codigo\":\""
                                + escapar(modalidad.getCodigo())
                                + "\","
                                + "\"nombre\":\""
                                + escapar(modalidad.getNombre())
                                + "\","
                                + "\"descripcion\":\""
                                + escapar(modalidad.getDescripcion())
                                + "\","
                                + "\"duracion\":"
                                + modalidad.getDuracionMinimaDias()
                                + ","
                                + "\"valor\":"
                                + modalidad.getValorDiario()
                                + ","
                                + "\"estado\":\""
                                + escapar(modalidad.getEstado())
                                + "\","
                                + "\"beneficios\":\""
                                + escapar(
                                String.join(
                                        ", ",
                                        modalidad.getBeneficios()
                                )
                        )
                                + "\""
                                + "}"
                )
                .collect(
                        Collectors.joining(
                                ",",
                                "[",
                                "]"
                        )
                );
    }


    public String obtenerServicios() {

        List<ServicioAdicional> servicios =
                controlador.obtenerServicios();

        return servicios.stream()
                .map(servicio ->
                        "{"
                                + "\"codigo\":\""
                                + escapar(servicio.getCodigo())
                                + "\","
                                + "\"nombre\":\""
                                + escapar(servicio.getNombre())
                                + "\","
                                + "\"precio\":"
                                + servicio.getPrecio()
                                + ","
                                + "\"disponible\":"
                                + servicio.isDisponibilidad()
                                + "}"
                )
                .collect(
                        Collectors.joining(
                                ",",
                                "[",
                                "]"
                        )
                );
    }


    public String obtenerReservas() {

        List<Reserva> reservas =
                controlador.obtenerReservas();

        return reservas.stream()
                .map(reserva -> {

                    String servicios =
                            reserva.getServiciosAdicionales()
                                    .stream()
                                    .map(
                                            ServicioAdicional::getNombre
                                    )
                                    .collect(
                                            Collectors.joining(", ")
                                    );

                    return "{"
                            + "\"id\":\""
                            + escapar(reserva.getId())
                            + "\","
                            + "\"cliente\":\""
                            + escapar(
                            reserva.getCliente()
                                    .getNombreCompleto()
                    )
                            + "\","
                            + "\"vehiculo\":\""
                            + escapar(
                            reserva.getVehiculo()
                                    .getPlaca()
                    )
                            + "\","
                            + "\"modalidad\":\""
                            + escapar(
                            reserva.getModalidad()
                                    .getNombre()
                    )
                            + "\","
                            + "\"fecha\":\""
                            + escapar(
                            reserva.getFechaReserva()
                    )
                            + "\","
                            + "\"dias\":"
                            + reserva.getDiasAlquiler()
                            + ","
                            + "\"servicios\":\""
                            + escapar(
                            servicios.isBlank()
                                    ? "—"
                                    : servicios
                    )
                            + "\","
                            + "\"total\":"
                            + reserva.calcularValorFinal()
                            + "}";
                })
                .collect(
                        Collectors.joining(
                                ",",
                                "[",
                                "]"
                        )
                );
    }


    public boolean eliminarCliente(String documento) {
        return controlador.eliminarCliente(documento);
    }

    public boolean eliminarVehiculo(String placa) {
        return controlador.eliminarVehiculo(placa);
    }

    public boolean eliminarModalidad(String codigo) {
        return controlador.eliminarModalidad(codigo);
    }

    public boolean eliminarServicio(String codigo) {
        return controlador.eliminarServicio(codigo);
    }

    public boolean eliminarReserva(String id) {
        return controlador.eliminarReserva(id);
    }


    private String escapar(String valor) {

        if (valor == null) {
            return "";
        }

        return valor
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "");
    }
}