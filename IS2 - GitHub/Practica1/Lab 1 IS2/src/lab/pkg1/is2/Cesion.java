/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.pkg1.is2;

import java.util.Date;

/**
 *
 * @author Angel
 */
public class Cesion {

    Miembro miembro1, miembro2;
    Motocicleta motocicleta;
    Date fecha;
    
    public Cesion(Miembro miembro1, Miembro miembro2, Motocicleta motocicleta, Date fecha) {
        this.miembro1 = miembro1;
        this.miembro2 = miembro2;
        this.motocicleta = motocicleta;     
        this.fecha = fecha;
    }

    public String toString() {
        String cesion;

        cesion = "El socio: \n " + miembro1.toStringSinMotos()+ " \n \nHa cedido la moto: \n    " + motocicleta.toString() + "\n \nAl socio: \n   " + miembro2.toStringSinMotos() + "\n \nEn la fecha: \n   "+ fecha.toString() + "\n";
        return cesion;
    }
}
