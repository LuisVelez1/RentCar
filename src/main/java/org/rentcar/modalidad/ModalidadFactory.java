package org.rentcar.modalidad;

import java.util.List;

public final class ModalidadFactory {

    private ModalidadFactory() {
    }

    public static Modalidad crearModalidad(
            String tipo,
            String codigo,
            String descripcion,
            int duracionMinimaDias,
            double valorDiario,
            List<String> beneficios,
            String tipoCobertura,
            int conductoresPermitidos,
            String caracteristicasEspeciales) {

        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException(
                    "El tipo de modalidad es obligatorio."
            );
        }

        if (tipo.equalsIgnoreCase("Basico")
                || tipo.equalsIgnoreCase("Básico")
                || tipo.equalsIgnoreCase("Económica")) {

            return new Basico(
                    codigo,
                    descripcion,
                    duracionMinimaDias,
                    valorDiario,
                    beneficios
            );
        }

        if (tipo.equalsIgnoreCase("Ejecutiva")) {

            return new Ejecutiva(
                    codigo,
                    descripcion,
                    duracionMinimaDias,
                    valorDiario,
                    beneficios
            );
        }

        if (tipo.equalsIgnoreCase("Premium")) {

            return new Premium(
                    codigo,
                    descripcion,
                    duracionMinimaDias,
                    valorDiario,
                    beneficios,
                    tipoCobertura,
                    conductoresPermitidos,
                    caracteristicasEspeciales
            );
        }

        throw new IllegalArgumentException(
                "Tipo de modalidad no válida."
        );
    }
}