package org.rentcar.servicios;

public class ValidadorNumeroPerfecto
        implements IValidadorNumeroPerfecto {

    @Override
    public boolean esNumeroPerfecto(long numero) {

        if (numero <= 1) {
            return false;
        }

        long sumaDivisores = 0;

        for (long i = 1; i <= numero / 2; i++) {

            if (numero % i == 0) {
                sumaDivisores += i;
            }
        }

        return sumaDivisores == numero;
    }
}