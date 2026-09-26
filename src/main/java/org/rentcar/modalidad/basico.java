package org.rentcar.modalidad;
public class basico extends Modalidad{
    public basico(String codigo, double valorDiario) {
        super(codigo, "Basico", "Vehículo básico", 1, valorDiario, "Kilometraje limitado");
    }

    @Override
    public double calcularCostoTotal(int dias) {
        return dias * valorDiario;
    }
}