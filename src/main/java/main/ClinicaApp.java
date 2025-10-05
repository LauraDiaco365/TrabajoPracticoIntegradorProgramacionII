package main;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import config.DatabaseConnection;


public class ClinicaApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PacienteDAO daoPaciente = new PacienteDAO();
        HistoriaClinicaDAO daoHistoria = new HistoriaClinicaDAO();

          try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("✅ Conexión exitosa a la base de datos.");
            int opcion;
            do {
                System.out.println("\n --- MENÚ CLÍNICA ---");
                System.out.println("1. Crear paciente");
                System.out.println("2. Crear historia clínica");
                System.out.println("3. Consultar paciente por ID");
                System.out.println("4. Consultar historia clínica por ID");
                System.out.println("5. Listar pacientes");
                System.out.println("6. Listar historias clínicas");
                System.out.println("7. Modificar paciente");
                System.out.println("8. Modificar historia clínica");
                System.out.println("9. Eliminar paciente (lógica)");
                System.out.println("10. Eliminar historia clínica (lógica)");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.println("\n--- CREAR PACIENTE ---");
                        System.out.print("ID: ");
                        int idP = scanner.nextInt(); scanner.nextLine();
                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Apellido: ");
                        String apellido = scanner.nextLine();
                        System.out.print("DNI: ");
                        String dni = scanner.nextLine();
                        System.out.print("Fecha nacimiento (AAAA-MM-DD): ");
                        LocalDate fecha = LocalDate.parse(scanner.nextLine());
                        System.out.print("ID historia asociada: ");
                        int idHist = scanner.nextInt(); scanner.nextLine();

                        HistoriaClinica historia = daoHistoria.leer(idHist, conn);
                        if (historia != null) {
                            Paciente nuevo = new Paciente(idP, nombre, apellido, dni, fecha, false, historia);
                            daoPaciente.crear(nuevo, conn);
                            System.out.println("Paciente creado.");
                        } else {
                            System.out.println("Historia no encontrada.");
                        }
                        break;

                    case 2:
                        System.out.println("\n--- CREAR HISTORIA CLÍNICA ---");
                        System.out.print("ID: ");
                        int idH = scanner.nextInt(); scanner.nextLine();
                        System.out.print("Número: ");
                        String nro = scanner.nextLine();
                        System.out.print("Grupo sanguíneo: ");
                        String grupo = scanner.nextLine();
                        System.out.print("Antecedentes: ");
                        String ant = scanner.nextLine();
                        System.out.print("Medicación actual: ");
                        String med = scanner.nextLine();
                        System.out.print("Observaciones: ");
                        String obs = scanner.nextLine();

                        HistoriaClinica nueva = new HistoriaClinica(idH, false, nro, grupo, ant, med, obs);
                        daoHistoria.crear(nueva, conn);
                        System.out.println("Historia clínica creada.");
                        break;

                    case 3:
                        System.out.print("\nID paciente a consultar: ");
                        int idConsultaP = scanner.nextInt(); scanner.nextLine();
                        Paciente p = daoPaciente.leer(idConsultaP, conn);
                        System.out.println(p != null ? p : "Paciente no encontrado.");
                        break;

                    case 4:
                        System.out.print("\nID historia a consultar: ");
                        int idConsultaH = scanner.nextInt(); scanner.nextLine();
                        HistoriaClinica h = daoHistoria.leer(idConsultaH, conn);
                        System.out.println(h != null ? h : "Historia no encontrada.");
                        break;

                    case 5:
                        System.out.println("\n--- LISTA DE PACIENTES ---");
                        List<Paciente> pacientes = daoPaciente.leerTodos(conn);
                        for (Paciente pac : pacientes) {
                            if (!pac.isEliminado()) System.out.println(pac);
                        }
                        break;

                    case 6:
                        System.out.println("\n--- LISTA DE HISTORIAS CLÍNICAS ---");
                        List<HistoriaClinica> historias = daoHistoria.leerTodos(conn);
                        for (HistoriaClinica hc : historias) {
                            if (!hc.isEliminado()) System.out.println(hc);
                        }
                        break;

                    case 7:
                        System.out.print("\nID paciente a modificar: ");
                        int idModP = scanner.nextInt(); scanner.nextLine();
                        Paciente modP = daoPaciente.leer(idModP, conn);
                        if (modP != null) {
                            System.out.print("Nuevo nombre: ");
                            modP.setNombre(scanner.nextLine());
                            System.out.print("Nuevo apellido: ");
                            modP.setApellido(scanner.nextLine());
                            daoPaciente.actualizar(modP, conn);
                            System.out.println("Paciente modificado.");
                        } else {
                            System.out.println("Paciente no encontrado.");
                        }
                        break;

                    case 8:
                        System.out.print("\nID historia a modificar: ");
                        int idModH = scanner.nextInt(); scanner.nextLine();
                        HistoriaClinica modH = daoHistoria.leer(idModH, conn);
                        if (modH != null) {
                            System.out.print("Nueva medicación actual: ");
                            modH.setMedicacionActual(scanner.nextLine());
                            System.out.print("Nuevas observaciones: ");
                            modH.setObservaciones(scanner.nextLine());
                            daoHistoria.actualizar(modH, conn);
                            System.out.println("Historia modificada.");
                        } else {
                            System.out.println("Historia no encontrada.");
                        }
                        break;

                    case 9:
                        System.out.print("\nID paciente a eliminar: ");
                        int idDelP = scanner.nextInt(); scanner.nextLine();
                        daoPaciente.eliminarLogico(idDelP, conn);
                        System.out.println("Paciente eliminado lógicamente.");
                        break;

                    case 10:
                        System.out.print("\nID historia a eliminar: ");
                        int idDelH = scanner.nextInt(); scanner.nextLine();
                        daoHistoria.eliminarLogico(idDelH, conn);
                        System.out.println("Historia eliminada lógicamente.");
                        break;

                    case 0:
                        System.out.println("Saliendo del sistema...");
                        break;

                    default:
                        System.out.println("Opción inválida.");
                }
            } while (opcion != 0);
        } catch (Exception e) {
            System.out.println("Error en la aplicación: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}