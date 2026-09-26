package org.rentcar.modalidad;

import java.util.List;

public class Premium extends Modalidad {

    private String tipoCobertura;
    private int cantidadConductoresPermitidos;
    private String caracteristicasEspeciales;

    public Premium(
            String codigo,
            double valorDiario,
            String tipoCobertura,
            int cantidadConductoresPermitidos,
            String caracteristicasEspeciales) {

        super(
                codigo,
                "Premium",
                "Vehículo de lujo",
                3,
                valorDiario,
                List.of(
                        "Kilometraje ilimitado",
                        "Seguro a todo riesgo",
                        "Asistencia en carretera"
                )
        );

        this.tipoCobertura = tipoCobertura;
        this.cantidadConductoresPermitidos = cantidadConductoresPermitidos;
        this.caracteristicasEspeciales = caracteristicasEspeciales;
    }

    @Override
    public double calcularCostoTotal(int dias) {
        return dias * valorDiario;
    }

    public String getTipoCobertura() {
        return tipoCobertura;
    }

    public int getCantidadConductoresPermitidos() {
        return cantidadConductoresPermitidos;
    }

    public String getCaracteristicasEspeciales() {
        return caracteristicasEspeciales;
    }
}