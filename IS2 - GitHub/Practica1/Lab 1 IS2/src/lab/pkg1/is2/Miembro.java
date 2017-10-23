/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.pkg1.is2;

import java.util.ArrayList;

/**
 *
 * @author Angel
 */
public class Miembro {

    private static int incremento_cantidad = 1;
    private int n_socio;
    private String nombre;
    private int n_motos;
    private int importe_motos;
    private ArrayList<Motocicleta> motocicletas;
    

    public Miembro(int n_socio, String nombre) {
        this.motocicletas = new ArrayList<Motocicleta>();
        this.n_socio = n_socio;
        this.nombre = nombre;
        this.importe_motos = 0;
        this.n_motos = 0;
    }
    public void actualizarImporte()
    {
        importe_motos = 0;
        for(int i = 0; i < n_motos; i++)
        {
            importe_motos += motocicletas.get(i).getPrecio();
        }
    }
    public int getN_socio() {
        return n_socio;
    }

    public void setN_socio(int n_socio) {
        this.n_socio = n_socio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getN_motos() {
        return n_motos;
    }

    public void setN_motos(int n_motos) {
        this.n_motos = n_motos;
    }

    public int getImporte_motos() {
        return importe_motos;
    }

    public void setImporte_motos(int importe_motos) {
        this.importe_motos = importe_motos;
    }

    public ArrayList<Motocicleta> getMotocicletas() {
        return motocicletas;
    }

    public void setMotocicletas(ArrayList<Motocicleta> motocicletas) {
        this.motocicletas = motocicletas;
    }
    public void addMotocicletas(Motocicleta motocicleta){
        this.motocicletas.add(motocicleta);
    }

    public ArrayList<Motocicleta> contadorMotocicletas(ArrayList<Motocicleta> motocicletas) {
        int siguiente = 0;
        int contador = 0;
        for (int i = 1; i < motocicletas.size(); i++) {
            if (motocicletas.get(siguiente).getNombre().equals(motocicletas.get(i).getNombre())) {
                motocicletas.remove(siguiente);
                motocicletas.get(i).setCantidad(incremento_cantidad);
            }
            siguiente++;
        }
        return motocicletas;
    }

    public String toStringConMotos() {
        String miembro;
        
        miembro = "Número del socio: " + getN_socio() + "\nNombre: " + getNombre()
                + "\nNúmero de motos: " + getN_motos() + "\nImporte de compra: " + getImporte_motos()+ "\nMotocicletas en posesion: \n";
        
        for (int i = 0; i < motocicletas.size(); i++) {
            motocicletas = contadorMotocicletas(motocicletas);
            miembro = miembro + motocicletas.get(i).toStringMiembro() + "\n";
        }
        return miembro;
    }
    
    public String toStringSinMotos() {
        String miembro;
        
        miembro = "Número del socio: " + getN_socio() + "\nNombre: " + getNombre()
                + "\nNúmero de motos: " + getN_motos() + "\nImporte de compra: " + getImporte_motos();

        return miembro;
    }
}
