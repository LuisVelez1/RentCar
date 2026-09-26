package org.rentcar.clases;

public final class Empresa {

    private static final Empresa INSTANCIA =
            new Empresa();

    private String nombreComercial;
    private String nit;
    private String direccion;
    private String telefono;
    private String correoElectronico;
    private String paginaWeb;

    private Empresa() {

        this.nombreComercial = "RentCar";
        this.nit = "1532";
        this.direccion =
                "Carrera 15 Calle 12 Norte, Armenia";
        this.telefono = "3154003868";
        this.correoElectronico =
                "rentcar23@gmail.com";
        this.paginaWeb =
                "www.rentcar.com";
    }

    public static Empresa getInstance() {
        return INSTANCIA;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public String getNit() {
        return nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setNombreComercial(
            String nombreComercial) {

        this.nombreComercial =
                nombreComercial;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public void setDireccion(
            String direccion) {

        this.direccion =
                direccion;
    }

    public void setTelefono(
            String telefono) {

        this.telefono =
                telefono;
    }

    public void setCorreoElectronico(
            String correoElectronico) {

        this.correoElectronico =
                correoElectronico;
    }

    public void setPaginaWeb(
            String paginaWeb) {

        this.paginaWeb =
                paginaWeb;
    }
}