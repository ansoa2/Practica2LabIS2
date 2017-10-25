/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.pkg1.is2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Angel
 */
public class Menu {

    private static final int error_maxcompra = -1;
    private static final int sig_id_socio = 1;
    private static final int sig_id_moto = 1;
    private static final int incremento_num_motos = 1;
    private int maxcompra;
    private int cantidad_socios = 0;
    private int cantidad_motos = 0;
    private ArrayList<Miembro> miembros;
    private ArrayList<Cesion> cesiones;

    public Menu() throws IOException {
        this.miembros = new ArrayList<>();
        this.cesiones = new ArrayList<>();

        int menu, max_compra;

        System.out.println("Introduce importe máximo de compra de cada miembro: ");
        max_compra = leerInt();
        this.maxcompra = max_compra;

        objetosPrueba();

        do {
            System.out.println("\nMENU: \n1. Registrar un nuevo miembro");
            System.out.println("2. Registrar una nueva motocicleta");
            System.out.println("3. Registrar una cesión");
            System.out.println("4. Listar en pantalla los miembros con motos en posesión");
            System.out.println("5. Listar todas las motos");
            System.out.println("6. Mostrar las cesiones realizadas");
            System.out.println("7. Modificar otros gastos de moto");
            System.out.println("8. Eliminar miembro");
            System.out.println("9. Mostrar miembros con mas motos");
            System.out.println("10. Salir del programa");

            menu = leerInt();

            switch (menu) {
                case 1:
                    registrarMiembro();
                    break;
                case 2:
                    registrarMotocicleta();
                    break;
                case 3:
                    registrarCesion();
                    break;
                case 4:
                    mostrarMiembrosConMoto();
                    break;
                case 5:
                    mostrarMotos();
                    break;
                case 6:
                    mostrarCesiones();
                    break;
                case 7:
                    modificarOtrosGastos();
                    break;
                case 8:
                    eliminarMiembro();
                    break;
                case 9:
                    miembrosConMasMotos();
                    break;
                case 10:
                    guardarEnFichero();
                    break;
            }
            limpiarConsola();
        } while (menu != 10);
    }

    public void registrarMiembro() {
        Miembro miembro;
        String nombre;
        boolean nombre_valido;
        int i = 0;
        do {
            i = 0;
            System.out.println("Introduce el nombre: ");
            nombre = leerString();

            do {
                if (Character.isAlphabetic(nombre.charAt(i)) || nombre.charAt(i) == ' ') {
                    nombre_valido = true;
                } else {
                    nombre_valido = false;
                }

                if (nombre_valido == false) {
                    System.out.println("Se espera un nombre válido: ");
                }
                i++;
            } while (nombre_valido == true && i < nombre.length());
        } while (nombre_valido == false);

        miembro = new Miembro(sig_id_socio + cantidad_socios, nombre);
        miembros.add(miembro);
        cantidad_socios++;
        System.out.println("Se ha registrado al miembro con éxito");
    }

    public void registrarMotocicleta() {
        if (miembros.size() > 0) {

            Motocicleta motocicleta, motocicleta_aux;
            int precio, otros_gastos, i;

            motocicleta_aux = solicitarMotocicleta();
            do {

                System.out.println("Introduce el precio: ");
                precio = leerInt();
                if (precio == 0) {
                    System.out.println("Se espera un numero entero mayor que 0: ");
                }
            } while (precio == 0);

            System.out.println("Introduce el importe de otros gastos: ");
            otros_gastos = leerInt();

            motocicleta = new Motocicleta(sig_id_moto + cantidad_motos, motocicleta_aux.getMatricula(), motocicleta_aux.getNombre(), motocicleta_aux.getCilindrada(), precio, otros_gastos);

            i = buscarMiembroParaMotocicleta(motocicleta);
            if (i != error_maxcompra) {
                cantidad_motos++;
                miembros.get(i).addMotocicletas(motocicleta);
                miembros.get(i).setN_motos(incremento_num_motos);
                miembros.get(i).setImporte_motos(motocicleta.getPrecio() + miembros.get(i).getImporte_motos());
                System.out.println("Se ha registrado la motocicleta con éxito");

            } else if (i == error_maxcompra) {
                System.out.println("El importe de la compra supera los 6000 euros");
            }
        } else {
            System.out.println("\n No hay miembros registrados, introduzca al menos un miembro para poder registrar una nueva motocicleta \n");
        }

    }

    public String leerString() {
        String s;
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();
        return s;
    }

    public int leerInt() {
        int x = 0;
        boolean valido = false;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                x = sc.nextInt();
                valido = true;
            } catch (Exception e) {
                System.out.println("Se espera un numero entero ");
            }
        } while (valido == false);

        return x;
    }

    public void mostrarMiembrosConMoto() {

        System.out.println("Miembros: \n");
        if (miembros.size() > 0) {
            for (int i = 0; i < miembros.size(); i++) {
                System.out.println(miembros.get(i).toStringConMotos() + "\n");
            }
        } else {
            System.out.println("No hay miembros registrados \n");
        }

    }

    private void mostrarMotos() {

        ArrayList<Motocicleta> aux = new ArrayList<>();

        for (int i = 0; i < miembros.size(); i++) {
            for (int j = 0; j < miembros.get(i).getMotocicletas().size(); j++) {
                aux.add(miembros.get(i).getMotocicletas().get(j));
            }
        }

        if (aux.size() > 0) {
            System.out.println("Motocicletas: \n");

            for (int i = 0; i < aux.size(); i++) {
                System.out.println(aux.get(i).toString() + "\n");
            }
        } else {
            System.out.println("No se ha registrado ninguna motocicleta\n ");
        }
    }

    private void registrarCesion() {

        int miembro_1, motocicleta_1;
        mostrarMiembrosConMoto();
        do {
            System.out.println("Miembro que cede la motocicleta: ");
            miembro_1 = buscarMiembro();
            if (miembros.get(miembro_1).getMotocicletas().size() <= 0) {
                System.out.println("El miembro no dispone de motocicletas para ceder. ");
            }
        } while (miembros.get(miembro_1).getMotocicletas().size() <= 0);

        System.out.println("Motocicleta que quiere ceder: ");
        motocicleta_1 = buscarMotocicleta(miembros.get(miembro_1));

        Cesion(miembro_1, motocicleta_1);
    }

    private void Cesion(int miembro_1, int motocicleta_1) {
        Cesion cesion;
        Date fecha;
        int miembro_2;
        Miembro miembro1, miembro2;
        Motocicleta motocicleta;

        System.out.println("Miembro al que ceder la motocicleta: ");
        miembro_2 = buscarMiembroParaMotocicleta(miembros.get(miembro_1).getMotocicletas().get(motocicleta_1));

        if (miembro_2 != error_maxcompra) {
            miembro1 = miembros.get(miembro_1);
            miembros.get(miembro_1).setN_motos(miembros.get(miembro_1).getN_motos() - incremento_num_motos);
            miembros.get(miembro_1).setImporte_motos(miembros.get(miembro_1).getImporte_motos() - miembros.get(miembro_1).getMotocicletas().get(motocicleta_1).getPrecio());

            motocicleta = miembros.get(miembro_1).getMotocicletas().get(motocicleta_1);
            miembros.get(miembro_1).getMotocicletas().remove(motocicleta_1);

            miembro2 = miembros.get(miembro_2);
            miembros.get(miembro_2).addMotocicletas(motocicleta);
            miembros.get(miembro_2).setN_motos(miembros.get(miembro_2).getN_motos() + incremento_num_motos);
            miembros.get(miembro_2).actualizarImporte();
            fecha = new Date();
            cesion = new Cesion(miembro1, miembro2, motocicleta, fecha);
            cesiones.add(cesion);
        }
    }

    public int buscarMiembro() {
        String nombre_miembro;
        int i = 0;

        boolean encontrado = false, miembro_encontrado = false;

        do {
            i = 0;
            System.out.println("Nombre del miembro");
            nombre_miembro = leerString();
            do {

                if (nombre_miembro.equals(miembros.get(i).getNombre())) {
                    miembro_encontrado = true;
                } else {
                    i++;
                }

            } while (miembro_encontrado == false && i < miembros.size());
            if (miembro_encontrado == false) {
                System.out.println("El miembro introducido no existe introduzca otro nombre. ");
            }
        } while (miembro_encontrado == false);

        return i;
    }

    public int buscarMiembroParaMotocicleta(Motocicleta motocicleta) {
        String nombre_miembro;
        int i = 0;
        boolean miembro_encontrado = false, max_limite = false;

        do {
            i = 0;
            System.out.println("Nombre del miembro");
            nombre_miembro = leerString();

            do {

                if (nombre_miembro.equals(miembros.get(i).getNombre()) && ((motocicleta.getPrecio() + miembros.get(i).getImporte_motos()) <= maxcompra)) {
                    miembro_encontrado = true;
                } else if (nombre_miembro.equals(miembros.get(i).getNombre()) && ((motocicleta.getPrecio() + miembros.get(i).getImporte_motos()) > maxcompra)) {
                    i++;
                    System.out.println("El importe de compra supera los 6000 Euros ");
                    max_limite = true;
                    miembro_encontrado = true;
                } else {
                    i++;
                }
            } while (i < miembros.size() && miembro_encontrado == false);

            if (miembro_encontrado == false && max_limite == false) {
                System.out.println("El miembro introducido no existe, vuelva a seleccionar el nombre");
            }
        } while (miembro_encontrado == false && max_limite == false);

        if (max_limite == true) {
            i = error_maxcompra;
        }
        return i;
    }

    public Motocicleta solicitarMotocicleta() {
        String matricula;
        int cilindrada;
        int i = 0;
        Motocicleta motocicleta = new Motocicleta();

        do {
            System.out.println("Introduce la matricula: ");
            matricula = leerString();
        } while (false == compruebaMatricula(matricula));

        do {
            System.out.println("Introduce la cilindrada: ");
            cilindrada = leerInt();
            if (cilindrada == 0) {
                System.out.println("Se espera un numero entero superior a 0: ");
            }

        } while (cilindrada == 0);

        motocicleta.setMatricula(matricula);
        motocicleta.setCilindrada(cilindrada);

        return motocicleta;
    }

    public int buscarMotocicleta(Miembro miembro) {
        Motocicleta motocicleta_aux;
        boolean motocicleta_encontrada = false;
        int i = 0;

        do {
            i = 0;
            motocicleta_aux = solicitarMotocicleta();

            do {

                if (motocicleta_aux.getMatricula().equals(miembro.getMotocicletas().get(i).getMatricula()) && motocicleta_aux.getCilindrada() == miembro.getMotocicletas().get(i).getCilindrada()) {
                    motocicleta_encontrada = true;
                } else {
                    i++;
                }
            } while (motocicleta_encontrada == false && i < miembro.getMotocicletas().size());

            if (motocicleta_encontrada == false) {
                System.out.println("La motocicleta introducida no pertenece a dicho miembro. ");
            }
        } while (motocicleta_encontrada == false);

        return i;
    }

    public void mostrarCesiones() {

        if (cesiones.size() > 0) {
            System.out.println("Cesiones : \n");
            for (int i = 0; i < cesiones.size(); i++) {
                System.out.println("" + cesiones.get(i).toString());
            }
        } else {
            System.out.println("No se ha producido ninguna cesión");
        }

    }

    public void limpiarConsola() {
        for (int i = 0; i < 2; i++) {
            System.out.println("\n");
        }
    }

    public boolean compruebaMatricula(String matricula) {
        boolean valido = false;
        String numeros = "", letras = "";
        int numero;

        if (matricula.length() == 7) {

            try {
                for (int i = 0; i < matricula.length(); i++) {
                    numeros = matricula.substring(0, 4);
                    letras = matricula.substring(4, matricula.length());
                }
                numero = parseInt(numeros);
                valido = true;

                for (int i = 0; i < letras.length(); i++) {
                    if (!Character.isAlphabetic(letras.charAt(i))) {
                        System.out.println("Se espera una matricula válida, formato: NNNN LLL. (N = número, L = letra ");
                        valido = false;
                    }
                }
            } catch (Exception e) {
            }
        } else {
            valido = false;
            System.out.println("Introduzca una matrícula válida, formato: NNNN LLL. (N = número, L = letra)");
        }
        return valido;
    }

    public void objetosPrueba() {
        String[] nombres = {"Angel", "Raul", "Pau", "Salva", "Eric", "Ruben"};
        Miembro miembro;
        Motocicleta motocicleta;

        String[] nombres_moto = {"Vespa Primavera", "Vespa Primavera", "Motobenae Poney AG2", "Bultaco", "Guzzi Cardelino", "Ducati mini"};
        String[] matricula = {"1111AAA", "1111AAB", "1111AAC", "1111AAD", "1111AAE", "1111AAF"};
        int[] cilindrada = {125, 125, 70, 200, 75, 49};
        int[] precio = {2500, 2500, 2300, 3800, 1200, 4000};
        int[] otros_gastos = {100, 120, 200, 100, 80, 30};

        for (int i = 0; i < matricula.length; i++) {
            motocicleta = new Motocicleta(sig_id_moto + i, matricula[i], nombres_moto[i], cilindrada[i], precio[i], otros_gastos[i]);
            miembro = new Miembro(sig_id_socio + i, nombres[i]);
            miembros.add(miembro);
            if (motocicleta.getPrecio() <= maxcompra) {
                miembro.addMotocicletas(motocicleta);
                miembro.setN_motos(incremento_num_motos);
                miembro.setImporte_motos(motocicleta.getPrecio());
            }
        }
    }

    private void guardarEnFichero() throws IOException {
        String nombre;
        System.out.println("Introduce el nombre del fichero: ");
        nombre = leerString();
        nombre = nombre + ".txt";
        FileWriter fichero = new FileWriter(nombre);
        PrintWriter pw = null;

        try {

            pw = new PrintWriter(fichero);
            String s;
            s = "Miembros: \n";

            if (miembros.size() > 0) {
                for (int i = 0; i < miembros.size(); i++) {
                    if (miembros.get(i).getN_motos() > 0) {
                        s += miembros.get(i).toStringConMotos() + "\n";
                    }
                }
            }

            s += "\nCesiones: \n";
            if (cesiones.size() > 0) {
                for (int i = 0; i < cesiones.size(); i++) {
                    s += cesiones.get(i).toString() + "\n";
                }
            }

            pw.println(s);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        System.out.println("Saliendo del programa");
        System.exit(0);
    }

    public void modificarOtrosGastos() {
        int i, j, miembro = 0, motocicleta = 0, otros_gastos = 0;
        boolean matricula_valida = false, nombre_encontrado = false, motocicleta_encontrada = false;
        String matricula;

        mostrarMiembrosConMoto();
        do {
            System.out.println("Introduce la matricula: ");
            matricula = leerString();
            matricula_valida = compruebaMatricula(matricula);
            for (i = 0; i < miembros.size(); i++) {
                for (j = 0; j < miembros.get(i).getMotocicletas().size(); j++) {
                    if (miembros.get(i).getMotocicletas().get(j).getMatricula().equals(matricula)) {
                        motocicleta_encontrada = true;
                        miembro = i;
                        motocicleta = j;
                    }
                }
            }

        } while (motocicleta_encontrada == false || false == matricula_valida);

        do {
            try {
                System.out.println("Introduce el importe de otros gastos: ");
                otros_gastos = leerInt();
                Integer.parseInt("" + otros_gastos);
                if (otros_gastos < 0) {
                    System.out.println("No se admiten valores negativos");
                } else {
                    miembros.get(miembro).getMotocicletas().get(motocicleta).addOtrosGastos(otros_gastos);
                }
            } catch (Exception e) {
                System.out.println("Se esperaba un numero entero.");
            }
        } while (otros_gastos < 0);

    }

    private void eliminarMiembro() {
        int i, j;
        String nombre_miembro;
        boolean miembro_encontrado = false;

        i = 0;
        System.out.println("Nombre del miembro");
        nombre_miembro = leerString();

        do {

            if (nombre_miembro.equals(miembros.get(i).getNombre())) {
                miembro_encontrado = true;
            } else {
                i++;
            }

        } while (i < miembros.size() && miembro_encontrado == false);

        if (miembro_encontrado == true) {
            do {
                System.out.println(miembros.get(i).getMotocicletas().get(0).toString());
                Cesion(i, 0);
            } while (miembros.get(i).getMotocicletas().size() != 0);
            miembros.remove(i);
        }
    }

    private void miembrosConMasMotos() {
        ArrayList<Miembro> mas_motos = new ArrayList<>();
        ArrayList<Miembro> mas_motos_aux = new ArrayList<>();
        Miembro miembro_max;
        

        for (int i = 0; i < cesiones.size(); i++) {
            mas_motos.add(cesiones.get(i).getMiembro2());
        }
        
        miembro_max = mas_motos.get(0);
        
        for (int j = 1; j < mas_motos.size(); j++) {
            if (miembro_max.getMotocicletas().size() < mas_motos.get(j).getMotocicletas().size()) {
                miembro_max = mas_motos.get(j);
            }
        }
        for (int k = 0; k < mas_motos.size(); k++) {
            if (miembro_max.getMotocicletas().size() == mas_motos.get(k).getMotocicletas().size()) {
                System.out.println("Añado miembro");
                mas_motos_aux.add(mas_motos.get(k));
            }
        }

        System.out.println("Miembros con mas motos: ");
        for (int i = 0; i < mas_motos_aux.size(); i++) {
            System.out.println(mas_motos_aux.get(i).toStringSinMotos() + "\n");
        }
    }
}
