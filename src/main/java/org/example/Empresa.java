package org.example;

public class Empresa {

    private static Empresa instancia;
       private String nombreComercial;
       private String nit;
       private String telefono;
       private String correoElectronico;
       private String paginaWeb;

    private Empresa(){
        this.nombreComercial="RentCar";
        this.nit="1532";
        this.telefono= "3154003868";
        this.correoElectronico="rentcar23@gmail.com";
        this.paginaWeb="www.rentcar.com";
    }

    public static Empresa getInstance() {
        if (instancia==null){
         instancia = new Empresa();
        }
         return instancia;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public String getnit() {
        return nit;
    }

    public String getGettelfono() {
        return telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }
}

