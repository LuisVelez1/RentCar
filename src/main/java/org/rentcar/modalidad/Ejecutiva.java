package org.rentcar.modalidad;

import java.util.List;

public class Ejecutiva extends Modalidad {

    public Ejecutiva(String codigo, double valorDiario) {

        super(
                codigo,
                "Ejecutiva",
                "Vehículo cómodo para negocios",
                2,
                valorDiario,
                List.of(
                        "Kilometraje ilimitado",
                        "Seguro básico"
                )
        );
    }

    @Override
    public double calcularCostoTotal(int dias) {
        return dias * valorDiario;
    }
}