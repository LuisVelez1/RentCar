package org.rentcar.modalidad;

import java.util.List;

public class Ejecutiva extends Modalidad {

    public Ejecutiva(
            String codigo,
            String descripcion,
            int duracionMinimaDias,
            double valorDiario,
            List<String> beneficios) {

        super(
                codigo,
                "Ejecutiva",
                descripcion,
                duracionMinimaDias,
                valorDiario,
                beneficios
        );
    }

    @Override
    public double calcularCostoTotal(int dias) {
        return dias * valorDiario;
    }
}