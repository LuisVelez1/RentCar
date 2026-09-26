package org.example.modalidad;

    public abstract class Modalidad {
        protected String codigo;
        protected String nombre;
        protected String descripcion;
        protected int duracionMinimaDias;
        protected double valorDiario;
        protected String estado;
        protected String beneficios;

        public Modalidad(String codigo, String nombre, String descripcion, int duracionMinimaDias, double valorDiario, String beneficios) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.duracionMinimaDias = duracionMinimaDias;
            this.valorDiario = valorDiario;
            this.estado = "Disponible"; // Por defecto al crearla
            this.beneficios = beneficios;
        }

        public abstract double calcularCostoTotal(int dias);


        public String getNombre() {
            return nombre;
        }

        public double getValorDiario() {
            return valorDiario;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }
    }
