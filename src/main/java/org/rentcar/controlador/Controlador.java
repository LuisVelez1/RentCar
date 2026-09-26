package org.rentcar.controlador;

import org.rentcar.clases.Cliente;
import org.rentcar.clases.Empresa;
import org.rentcar.clases.Reserva;
import org.rentcar.clases.ServicioAdicional;
import org.rentcar.clases.Vehiculo;
import org.rentcar.modalidad.Modalidad;
import org.rentcar.modalidad.ModalidadFactory;
import org.rentcar.servicios.IGestorRentCar;
import org.rentcar.servicios.IValidadorNumeroPerfecto;

import java.time.LocalDate;

public class Controlador {

    private final IGestorRentCar gestorRentCar;
    private final IValidadorNumeroPerfecto validadorNumeroPerfecto;

    public Controlador(
            IGestorRentCar gestorRentCar,
            IValidadorNumeroPerfecto validadorNumeroPerfecto) {

        if (gestorRentCar == null) {
            throw new IllegalArgumentException(
                    "El gestor de RentCar no puede ser nulo."
            );
        }

        if (validadorNumeroPerfecto == null) {
            throw new IllegalArgumentException(
                    "El validador de número perfecto no puede ser nulo."
            );
        }

        this.gestorRentCar = gestorRentCar;
        this.validadorNumeroPerfecto = validadorNumeroPerfecto;
    }

    public String probarConexion() {
        return "Conexión JavaScript - Java funcionando correctamente";
    }

    public String guardarEmpresa(
            String nombre,
            String nit,
            String direccion,
            String telefono,
            String correo,
            String web) {

        try {

            validarTexto(nombre, "El nombre comercial es obligatorio.");
            validarTexto(nit, "El NIT es obligatorio.");
            validarTexto(direccion, "La dirección es obligatoria.");
            validarTexto(telefono, "El teléfono es obligatorio.");
            validarTexto(correo, "El correo electrónico es obligatorio.");

            Empresa empresa = Empresa.getInstance();

            empresa.setNombreComercial(nombre.trim());
            empresa.setNit(nit.trim());
            empresa.setDireccion(direccion.trim());
            empresa.setTelefono(telefono.trim());
            empresa.setCorreoElectronico(correo.trim());
            empresa.setPaginaWeb(
                    web == null ? "" : web.trim()
            );

            return "Datos de la empresa guardados correctamente.";

        } catch (IllegalArgumentException e) {

            return "ERROR: " + e.getMessage();
        }
    }

    public String registrarCliente(
            String nombreCompleto,
            String documento,
            String telefono,
            String correo,
            int edad,
            String fechaRegistro) {

        try {

            validarTexto(
                    nombreCompleto,
                    "El nombre completo es obligatorio."
            );

            validarTexto(
                    documento,
                    "El documento es obligatorio."
            );

            validarTexto(
                    telefono,
                    "El teléfono es obligatorio."
            );

            validarTexto(
                    correo,
                    "El correo electrónico es obligatorio."
            );

            validarTexto(
                    fechaRegistro,
                    "La fecha de registro es obligatoria."
            );

            if (edad < 18) {
                throw new IllegalArgumentException(
                        "El cliente debe ser mayor de edad."
                );
            }

            Cliente cliente =
                    new Cliente.ClienteBuilder(
                            documento.trim(),
                            nombreCompleto.trim()
                    )
                            .conTelefono(telefono.trim())
                            .conCorreo(correo.trim())
                            .conEdad(edad)
                            .conFechaRegistro(fechaRegistro.trim())
                            .build();

            gestorRentCar.registrarCliente(cliente);

            return "Cliente registrado correctamente.";

        } catch (IllegalArgumentException e) {

            return "ERROR: " + e.getMessage();
        }
    }

    public String registrarVehiculo(
            String placa,
            String marca,
            String modelo,
            int ano,
            String tipo,
            double tarifaDiaria) {

        try {

            validarTexto(
                    placa,
                    "La placa es obligatoria."
            );

            validarTexto(
                    marca,
                    "La marca es obligatoria."
            );

            validarTexto(
                    modelo,
                    "El modelo es obligatorio."
            );

            validarTexto(
                    tipo,
                    "El tipo de vehículo es obligatorio."
            );

            if (ano <= 0) {
                throw new IllegalArgumentException(
                        "El año del vehículo no es válido."
                );
            }

            if (tarifaDiaria < 0) {
                throw new IllegalArgumentException(
                        "La tarifa diaria no puede ser negativa."
                );
            }

            Vehiculo vehiculo =
                    new Vehiculo(
                            placa.trim().toUpperCase(),
                            marca.trim(),
                            modelo.trim(),
                            ano,
                            tipo.trim(),
                            tarifaDiaria
                    );

            gestorRentCar.registrarVehiculo(vehiculo);

            return "Vehículo registrado correctamente.";

        } catch (IllegalArgumentException e) {

            return "ERROR: " + e.getMessage();
        }
    }

    public String registrarModalidad(
            String tipo,
            String codigo,
            String descripcion,
            int duracionMinimaDias,
            double valorDiario,
            String estado,
            String beneficiosTexto,
            String cobertura,
            int conductoresPermitidos,
            String caracteristicasEspeciales) {

        try {

            validarTexto(
                    tipo,
                    "El tipo de modalidad es obligatorio."
            );

            validarTexto(
                    codigo,
                    "El código de modalidad es obligatorio."
            );

            if (duracionMinimaDias <= 0) {
                throw new IllegalArgumentException(
                        "La duración mínima debe ser mayor que cero."
                );
            }

            if (valorDiario < 0) {
                throw new IllegalArgumentException(
                        "El valor diario no puede ser negativo."
                );
            }

            java.util.List<String> beneficios =
                    new java.util.ArrayList<>();

            if (beneficiosTexto != null
                    && !beneficiosTexto.isBlank()) {

                for (String beneficio
                        : beneficiosTexto.split(",")) {

                    if (!beneficio.isBlank()) {
                        beneficios.add(
                                beneficio.trim()
                        );
                    }
                }
            }

            Modalidad modalidad =
                    ModalidadFactory.crearModalidad(
                            tipo.trim(),
                            codigo.trim(),
                            descripcion == null
                                    ? ""
                                    : descripcion.trim(),
                            duracionMinimaDias,
                            valorDiario,
                            beneficios,
                            cobertura == null
                                    ? ""
                                    : cobertura.trim(),
                            conductoresPermitidos,
                            caracteristicasEspeciales == null
                                    ? ""
                                    : caracteristicasEspeciales.trim()
                    );

            if (estado != null
                    && !estado.isBlank()) {

                modalidad.setEstado(
                        estado.trim()
                );
            }

            gestorRentCar.registrarModalidad(
                    modalidad
            );

            return "Modalidad registrada correctamente.";

        } catch (IllegalArgumentException e) {

            return "ERROR: " + e.getMessage();
        }
    }

    public String registrarServicio(
            String codigo,
            String nombre,
            String descripcion,
            double precio,
            String disponibilidad) {

        try {

            validarTexto(
                    codigo,
                    "El código del servicio es obligatorio."
            );

            validarTexto(
                    nombre,
                    "El nombre del servicio es obligatorio."
            );

            if (precio < 0) {
                throw new IllegalArgumentException(
                        "El precio del servicio no puede ser negativo."
                );
            }

            boolean disponible =
                    disponibilidad != null
                            && disponibilidad.equalsIgnoreCase(
                            "Disponible"
                    );

            ServicioAdicional servicio =
                    new ServicioAdicional(
                            codigo.trim(),
                            nombre.trim(),
                            descripcion == null
                                    ? ""
                                    : descripcion.trim(),
                            precio,
                            disponible
                    );

            gestorRentCar.registrarServicio(servicio);

            return "Servicio adicional registrado correctamente.";

        } catch (IllegalArgumentException e) {

            return "ERROR: " + e.getMessage();
        }
    }


    public String registrarReserva(
            String documentoCliente,
            String placaVehiculo,
            String codigoModalidad,
            String fechaReserva,
            int dias,
            double porcentajeDescuento,
            String codigosServicios) {

        try {

            validarTexto(
                    documentoCliente,
                    "Debe seleccionar un cliente."
            );

            validarTexto(
                    placaVehiculo,
                    "Debe seleccionar un vehículo."
            );

            validarTexto(
                    codigoModalidad,
                    "Debe seleccionar una modalidad."
            );

            validarTexto(
                    fechaReserva,
                    "La fecha de reserva es obligatoria."
            );

            Cliente cliente =
                    gestorRentCar.buscarClientePorDocumento(
                            documentoCliente
                    );

            if (cliente == null) {
                throw new IllegalArgumentException(
                        "El cliente seleccionado no existe."
                );
            }

            Vehiculo vehiculo =
                    gestorRentCar.buscarVehiculoPorPlaca(
                            placaVehiculo
                    );

            if (vehiculo == null) {
                throw new IllegalArgumentException(
                        "El vehículo seleccionado no existe."
                );
            }

            Modalidad modalidad =
                    gestorRentCar.buscarModalidadPorCodigo(
                            codigoModalidad
                    );

            if (modalidad == null) {
                throw new IllegalArgumentException(
                        "La modalidad seleccionada no existe."
                );
            }

            if (!modalidad.getEstado()
                    .equalsIgnoreCase("Disponible")) {

                throw new IllegalArgumentException(
                        "La modalidad seleccionada no está disponible."
                );
            }

            if (dias < modalidad.getDuracionMinimaDias()) {

                throw new IllegalArgumentException(
                        "La modalidad "
                                + modalidad.getNombre()
                                + " requiere mínimo "
                                + modalidad.getDuracionMinimaDias()
                                + " día(s) de alquiler."
                );
            }

            Reserva reserva =
                    new Reserva(
                            fechaReserva,
                            dias,
                            cliente,
                            vehiculo,
                            modalidad,
                            porcentajeDescuento
                    );

            if (codigosServicios != null
                    && !codigosServicios.isBlank()) {

                String[] codigos =
                        codigosServicios.split(",");

                for (String codigo : codigos) {

                    String codigoLimpio =
                            codigo.trim();

                    if (codigoLimpio.isEmpty()) {
                        continue;
                    }

                    ServicioAdicional servicio =
                            gestorRentCar
                                    .buscarServicioPorCodigo(
                                            codigoLimpio
                                    );

                    if (servicio != null) {
                        reserva.agregarServicioAdicional(
                                servicio
                        );
                    }
                }
            }

            gestorRentCar.registrarReserva(reserva);

            return "Reserva registrada correctamente. Total: $"
                    + String.format(
                    "%,.0f",
                    reserva.calcularValorFinal()
            );

        } catch (IllegalArgumentException e) {

            return "ERROR: " + e.getMessage();
        }
    }

    public String verificarTelefonoPerfecto(
            String numeroTelefono) {

        try {

            validarTexto(
                    numeroTelefono,
                    "Debe ingresar un número de teléfono."
            );

            Cliente clienteEncontrado =
                    gestorRentCar
                            .buscarClientePorTelefono(
                                    numeroTelefono.trim()
                            );

            if (clienteEncontrado == null) {

                return "Cliente no encontrado con ese teléfono.";
            }

            long telefonoNumerico;

            try {

                telefonoNumerico =
                        Long.parseLong(
                                numeroTelefono.trim()
                        );

            } catch (NumberFormatException e) {

                return "El teléfono del cliente no contiene un número válido.";
            }

            boolean perfecto =
                    validadorNumeroPerfecto
                            .esNumeroPerfecto(
                                    telefonoNumerico
                            );

            if (perfecto) {

                return "El teléfono del cliente "
                        + clienteEncontrado.getNombreCompleto()
                        + " ES un número perfecto.";
            }

            return "El teléfono del cliente "
                    + clienteEncontrado.getNombreCompleto()
                    + " NO es un número perfecto.";

        } catch (IllegalArgumentException e) {

            return "ERROR: " + e.getMessage();
        }
    }

    public double calcularIngresosPorPeriodo(
            String fechaInicio,
            String fechaFin) {

        validarTexto(
                fechaInicio,
                "La fecha inicial es obligatoria."
        );

        validarTexto(
                fechaFin,
                "La fecha final es obligatoria."
        );

        LocalDate inicio =
                LocalDate.parse(fechaInicio);

        LocalDate fin =
                LocalDate.parse(fechaFin);

        if (fin.isBefore(inicio)) {

            throw new IllegalArgumentException(
                    "La fecha final no puede ser anterior a la fecha inicial."
            );
        }

        double total = 0;

        for (Reserva reserva
                : gestorRentCar.getReservas()) {

            LocalDate fechaReserva =
                    LocalDate.parse(
                            reserva.getFechaReserva()
                    );

            boolean dentroDelPeriodo =
                    !fechaReserva.isBefore(inicio)
                            && !fechaReserva.isAfter(fin);

            if (dentroDelPeriodo) {

                total += reserva.calcularValorFinal();
            }
        }

        return total;
    }


    public int contarReservasPorPeriodo(
            String fechaInicio,
            String fechaFin) {

        LocalDate inicio =
                LocalDate.parse(fechaInicio);

        LocalDate fin =
                LocalDate.parse(fechaFin);

        int cantidad = 0;

        for (Reserva reserva
                : gestorRentCar.getReservas()) {

            LocalDate fechaReserva =
                    LocalDate.parse(
                            reserva.getFechaReserva()
                    );

            boolean dentroDelPeriodo =
                    !fechaReserva.isBefore(inicio)
                            && !fechaReserva.isAfter(fin);

            if (dentroDelPeriodo) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public java.util.List<Cliente> obtenerClientes() {
        return gestorRentCar.getClientes();
    }

    public java.util.List<Vehiculo> obtenerVehiculos() {
        return gestorRentCar.getVehiculos();
    }

    public java.util.List<Modalidad> obtenerModalidades() {
        return gestorRentCar.getModalidades();
    }

    public java.util.List<ServicioAdicional> obtenerServicios() {
        return gestorRentCar.getServicios();
    }

    public java.util.List<Reserva> obtenerReservas() {
        return gestorRentCar.getReservas();
    }


    public boolean eliminarCliente(String documento) {
        return gestorRentCar.eliminarClientePorDocumento(
                documento
        );
    }

    public boolean eliminarVehiculo(String placa) {
        return gestorRentCar.eliminarVehiculoPorPlaca(
                placa
        );
    }

    public boolean eliminarModalidad(String codigo) {
        return gestorRentCar.eliminarModalidadPorCodigo(
                codigo
        );
    }

    public boolean eliminarServicio(String codigo) {
        return gestorRentCar.eliminarServicioPorCodigo(
                codigo
        );
    }

    public boolean eliminarReserva(String id) {
        return gestorRentCar.eliminarReservaPorId(
                id
        );
    }

    public IGestorRentCar getGestorRentCar() {
        return gestorRentCar;
    }


    private void validarTexto(
            String valor,
            String mensajeError) {

        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(
                    mensajeError
            );
        }
    }
}