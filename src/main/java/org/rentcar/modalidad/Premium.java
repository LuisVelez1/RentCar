package org.rentcar.modalidad;

public class Premium extends Modalidad {
    private String tipoCobertura;
    private int cantidadConductoresPermitidos;
    private String caracteristicasEspeciales;

    public Premium(String codigo, double valorDiario, String tipoCobertura, int cantidadConductoresPermitidos, String caracteristicasEspeciales) {
        super(codigo, "Premium", "Vehículo de lujo", 3, valorDiario, "Seguro a todo");
        this.tipoCobertura = tipoCobertura;
        this.cantidadConductoresPermitidos = cantidadConductoresPermitidos;
        this.caracteristicasEspeciales = caracteristicasEspeciales;
    }

    @Override
    public double calcularCostoTotal(int dias) {

        return dias * valorDiario;
    }
}