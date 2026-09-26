package org.example.modalidad;
public class Ejecutiva extends Modalidad {
    public Ejecutiva(String codigo, double valorDiario) {
        super(codigo, "Ejecutiva", "Vehículo cómodo para negocios", 2, valorDiario, "Kilometraje ilimitado y seguro básico");
    }

    @Override
    public double calcularCostoTotal(int dias) {
        return dias * valorDiario;
    }
}