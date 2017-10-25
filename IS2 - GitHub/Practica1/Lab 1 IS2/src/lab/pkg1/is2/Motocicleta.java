/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.pkg1.is2;

/**
 *
 * @author Angel
 */
public class Motocicleta {
    
    private int id_motocicleta;
    private String matricula;
    private String nombre;
    private int cilindrada;
    private int precio;
    private int cantidad;
    private int otros_gastos;


    public Motocicleta()
    {}
            
    public Motocicleta(int id_motocicleta,String matricula,String nombre,int cilindrada,int precio, int otros_gastos)
    {
        this.cilindrada = cilindrada;
        this.id_motocicleta = id_motocicleta;
        this.matricula = matricula;
        this.precio = precio;
        this.nombre = nombre;
        this.cantidad = 1;
        this.otros_gastos = otros_gastos;
    }

    public int getCantidad() {
        return cantidad;
    }
    public void addOtrosGastos(int otros_gastos)
    {
        this.otros_gastos += otros_gastos; 
    }
    public void setCantidad(int incremento) {
        this.cantidad = cantidad + incremento;
    }

    public int getId_motocicleta() {
        return id_motocicleta;
    }

    public void setId_motocicleta(int id_motocicleta) {
        this.id_motocicleta = id_motocicleta;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getOtros_gastos() {
        return otros_gastos;
    }
    
    public String toStringMiembro(){
        return "    " + getCantidad() + " " + getNombre() + ", de " + getCilindrada() + "CC, coste de compra de "
                    + getPrecio() + " + otros gastos = "+getOtros_gastos() + " y matricula " + getMatricula() ;
    }
    
    public String toString(){
        return "    " + getCantidad() + " " +getNombre() + ", de " + getCilindrada() + "CC, coste de compra de "
                    + getPrecio()+ ", " + " otros gastos = "+ getOtros_gastos();
    }
}
