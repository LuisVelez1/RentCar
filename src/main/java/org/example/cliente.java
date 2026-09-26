package org.example;

import java.util.Date;

public class cliente {
    private String nombreCompleto;
    private String documento;
    private String telefono;
    private String correoElectronico;
    private String edad;
    private Date fechaRegistro;

    public cliente (String nombreCompleto, String documento, String telefono, String correoElectronico, String edad, Date fechaRegistro, String nombreCompleto1){
        this.nombreCompleto=nombreCompleto;
        this.documento=documento;
        this.telefono=telefono;
        this.correoElectronico=correoElectronico;
        this.edad=edad;
        this.fechaRegistro=fechaRegistro;
    }
    public cliente() {}

    public cliente(ClienteBuilder clienteBuilder) {
    }

    public static class ClienteBuilder {
        private String nombreCompleto;
        private String documento;
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
        public cliente build() {

            return new cliente (this);
        }
    }
 public String getNombreCompleto (){
        return nombreCompleto;
    }
public String getDocumento (){
        return documento;
    }
public String getTelefono (){
        return telefono;
}
public String getCorreoElectronico (){
        return correoElectronico;
}
public String getEdad (){
        return edad;
}
public Date getFechaRegistro(){
        return fechaRegistro;
}

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setNombreCompleto(String nombreCompleto) {

    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

}
