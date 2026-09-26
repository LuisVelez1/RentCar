package org.rentcar.modalidad;

import java.util.List;

public class Basico extends Modalidad {

    public Basico(
            String codigo,
            String descripcion,
            int duracionMinimaDias,
            double valorDiario,
            List<String> beneficios) {

        super(
                codigo,
                "Económica",
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