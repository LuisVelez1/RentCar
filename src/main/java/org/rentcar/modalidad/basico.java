package org.rentcar.modalidad;

import java.util.List;

public class basico extends Modalidad {

    public basico(String codigo, double valorDiario) {

        super(
                codigo,
                "Basico",
                "Vehículo básico",
                1,
                valorDiario,
                List.of(
                        "Kilometraje limitado"
                )
        );
    }

    @Override
    public double calcularCostoTotal(int dias) {
        return dias * valorDiario;
    }
}