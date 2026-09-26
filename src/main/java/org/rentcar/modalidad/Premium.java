package org.rentcar.modalidad;

import java.util.List;

public class Premium extends Modalidad {

    private final String tipoCobertura;
    private final int cantidadConductoresPermitidos;
    private final String caracteristicasEspeciales;

    public Premium(
            String codigo,
            String descripcion,
            int duracionMinimaDias,
            double valorDiario,
            List<String> beneficios,
            String tipoCobertura,
            int cantidadConductoresPermitidos,
            String caracteristicasEspeciales) {

        super(
                codigo,
                "Premium",
                descripcion,
                duracionMinimaDias,
                valorDiario,
                beneficios
        );

        this.tipoCobertura = tipoCobertura;
        this.cantidadConductoresPermitidos =
                cantidadConductoresPermitidos;

        this.caracteristicasEspeciales =
                caracteristicasEspeciales;
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