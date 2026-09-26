package org.rentcar.clases;

public class Cliente {
    private String nombreCompleto;
    private String documento;
    private String telefono;
    private String correoElectronico;
    private int edad;
    private String fechaRegistro;

    private Cliente(ClienteBuilder builder) {
        this.nombreCompleto = builder.nombreCompleto;
        this.documento = builder.documento;
        this.telefono = builder.telefono;
        this.correoElectronico = builder.correoElectronico;
        this.edad = builder.edad;
        this.fechaRegistro = builder.fechaRegistro;
    }

    public static class ClienteBuilder {
        private final String documento;
        private final String nombreCompleto;
        private String telefono;
        private String correoElectronico;
        private int edad;
        private String fechaRegistro;

        public ClienteBuilder(String documento, String nombreCompleto) {
            this.documento = documento;
            this.nombreCompleto = nombreCompleto;
        }

        public ClienteBuilder conTelefono(String telefono) {
            this.telefono = telefono;
            return this;
        }

        public ClienteBuilder conCorreo(String correoElectronico) {
            this.correoElectronico = correoElectronico;
            return this;
        }

        public ClienteBuilder conEdad(int edad) {
            this.edad = edad;
            return this;
        }

        public ClienteBuilder conFechaRegistro(String fechaRegistro) {
            this.fechaRegistro = fechaRegistro;
            return this;
        }

        public Cliente build() {
            if (documento == null || documento.isBlank()) {
                throw new IllegalArgumentException("El documento es obligatorio");
            }
            if (nombreCompleto == null || nombreCompleto.isBlank()) {
                throw new IllegalArgumentException("El nombre completo es obligatorio");
            }
            return new Cliente(this);
        }
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getDocumento() {
        return documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public int getEdad() {
        return edad;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}