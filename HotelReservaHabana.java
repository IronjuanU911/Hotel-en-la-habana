//HotelReservaHabana

import java.util.Scanner;

public class HotelReservaHabana {

    // Definimos la matriz para gestionar las habitaciones (2 pisos y 5 habitaciones por piso)
    static boolean[][] habitaciones = new boolean[2][5];
    // Arrays para almacenar la información de las reservas
    static String[] clientes = new String[10]; // Máximo 10 reservas
    static int[] nochesReservadas = new int[10];
    static double[] totalPagar = new double[10];

    // Precio constante por noche
    static final double PRECIO_POR_NOCHE = 100.0;

    static int contadorReservas = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n[Sistema de Reservas del Hotel La Habana]");
            System.out.println("1. Registrar reserva");
            System.out.println("2. Cancelar reserva");
            System.out.println("3. Mostrar reporte de habitaciones");
            System.out.println("4. Salir");
            System.out.print("Elija una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    registrarReserva(scanner);
                    break;
                case 2:
                    cancelarReserva(scanner);
                    break;
                case 3:
                    mostrarReporte();
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }

        scanner.close();
    }

    public static void registrarReserva(Scanner scanner) {
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = scanner.next();
        System.out.print("Ingrese el número del piso a reservar" + '\n' +  "(Piso 1 = 1 y Piso 2 = 2)");
        int piso = scanner.nextInt();
        System.out.print("Ingrese el número de habitación" +'\n'+ "(1 a 5)");
        int numHabitacion = scanner.nextInt();
        numHabitacion = numHabitacion-1;
        System.out.print("Ingrese el número de noches: ");
        int noches = scanner.nextInt();

        if (habitaciones[piso][numHabitacion]) {
            System.out.println("La habitación ya está ocupada.");
        } else {
            // Registrar la reserva
            habitaciones[piso][numHabitacion] = true;  // Marcar la habitación como ocupada
            clientes[contadorReservas] = nombre;
            nochesReservadas[contadorReservas] = noches;
            totalPagar[contadorReservas] = noches * PRECIO_POR_NOCHE;
            contadorReservas++;
            System.out.println("Reserva registrada con éxito.");
        }
    }

    public static void cancelarReserva(Scanner scanner) {
        System.out.print("Ingrese el número del piso de la habitación a cancelar" + '\n' +  "(Piso 1 = 1 y Piso 2 = 2)");
        int piso = scanner.nextInt();
        piso = piso - 1;
        System.out.print("Ingrese el número de habitación a cancelar" +'\n'+ "(1 a 5)");
        int numHabitacion = scanner.nextInt();
        numHabitacion = numHabitacion-1;

        if (!habitaciones[piso][numHabitacion]) {
            System.out.println("La habitación ya está disponible.");
        } else {
            // Cancelar la reserva
            habitaciones[piso][numHabitacion] = false; // Liberar la habitación
            System.out.println("Reserva cancelada. Habitación liberada.");
        }
    }

    public static void mostrarReporte() {
        int ocupadas = 0;
        int disponibles = 0;

        System.out.println("\n [Estado de las Habitaciones]");
        for (int i = 0; i < habitaciones.length; i++) {
            for (int j = 0; j < habitaciones[i].length; j++) {
                // Generar el número de la habitación basado en el piso y número
                int numeroHabitacion = (i + 1) * 100 + (j + 1);
                
                if (habitaciones[i][j]) {
                    ocupadas++;
                    System.out.println("Habitación " + numeroHabitacion + " está ocupada.");
                } else {
                    disponibles++;
                    System.out.println("Habitación " + numeroHabitacion + " está disponible.");
                }
            }
        }

        System.out.println("\nTotal habitaciones ocupadas: " + ocupadas);
        System.out.println("Total habitaciones disponibles: " + disponibles);
        System.out.println("\n--- Detalles de Reservas ---");

        for (int i = 0; i < contadorReservas; i++) {
            System.out.println("Cliente: " + clientes[i] + ", Noches: " + nochesReservadas[i] + ", Total a pagar: $" + totalPagar[i]);
        }
    }
}
