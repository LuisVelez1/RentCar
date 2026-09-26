package org.rentcar.modalidad;

public class modalidadFactory {

    public static Modalidad crearModalidad(String tipo, String codigo, double valorDiario) {
        if (tipo.equalsIgnoreCase("Basico")) {
            return new basico(codigo, valorDiario);
        } else if (tipo.equalsIgnoreCase("Ejecutiva")) {
            return new Ejecutiva(codigo, valorDiario);
        } else if (tipo.equalsIgnoreCase("Premium")) {
            return new Premium(codigo, valorDiario, "Cobertura Total", 2, "Asientos de excelente calidad y GPS");
        }
        throw new IllegalArgumentException("Tipo de modalidad no válida");
    }
}