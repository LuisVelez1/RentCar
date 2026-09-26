package org.rentcar.Clases;

public class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private Integer ano;
    private String tipo;
    private Double tarifaDiaria;

    public Vehiculo (String placa, String marca, String modelo, Integer ano, String tipo, Double tarifaDiaria){
        this.placa=placa;
        this.marca=marca;
        this.modelo=modelo;
        this.ano=ano;
        this.tipo=tipo;
        this.tarifaDiaria=tarifaDiaria;
    }

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public String getTipo() {
        return tipo;
    }

    public Double getTarifaDiaria() {
        return tarifaDiaria;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTarifaDiaria(Double tarifaDiaria) {
        this.tarifaDiaria = tarifaDiaria;
    }
}
