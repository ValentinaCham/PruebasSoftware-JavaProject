package classes;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CajeroAutomatico {

    // Saldo global de la cuenta
    private static double saldo = 1000.0;

    /**
     * Consulta el saldo actual.
     *
     * @param args No utilizado en esta función.
     * @return String con el saldo formateado.
     */
    public static String consulta(Map<String, Object> args) {
        return String.format("El saldo de la cuenta es %.0f", saldo);
    }

    /**
     * Realiza un depósito a la cuenta.
     *
     * @param args Debe contener la clave "monto" con un número positivo.
     * @return Mensaje de éxito.
     * @throws IllegalArgumentException si el monto es inválido.
     */
    public static String deposito(Map<String, Object> args) {
        if (args == null || !args.containsKey("monto")) {
            throw new IllegalArgumentException("Error: Falta ingreso del monto");
        }

        Object valor = args.get("monto");
        if (!(valor instanceof Number)) {
            throw new IllegalArgumentException("Error: El monto debe ser número positivo mayor que cero");
        }

        double monto = ((Number) valor).doubleValue();
        if (monto <= 0) {
            throw new IllegalArgumentException("Error: El monto debe ser número positivo mayor que cero");
        }

        saldo += monto;
        return "Deposito exitoso";
    }

    /**
     * Realiza un retiro de la cuenta.
     *
     * @param args Debe contener la clave "monto" con un número positivo menor o igual al saldo.
     * @return Mensaje de éxito.
     * @throws IllegalArgumentException si el monto es inválido o si el saldo es insuficiente.
     */
    public static String retiro(Map<String, Object> args) {
        if (args == null || !args.containsKey("monto")) {
            throw new IllegalArgumentException("Error: Falta ingreso del monto");
        }

        Object valor = args.get("monto");
        if (!(valor instanceof Number)) {
            throw new IllegalArgumentException("Error: El monto debe ser número positivo mayor que cero");
        }

        double monto = ((Number) valor).doubleValue();
        if (monto <= 0) {
            throw new IllegalArgumentException("Error: El monto debe ser número positivo mayor que cero");
        }

        if (monto > saldo) {
            throw new IllegalArgumentException("Error: Saldo insuficiente");
        }

        saldo -= monto;
        return "Retiro exitoso";
    }

    /**
     * Cierra la sesión del cajero automático.
     *
     * @param args No utilizado.
     * @return Mensaje de despedida.
     */
    public static String salir(Map<String, Object> args) {
        return "Gracias por usar el cajero automático";
    }

    /**
     * Lógica del menú de operaciones.
     *
     * @param operacion Opción elegida: 'c', 'd', 'r', 's'.
     * @param args Argumentos para las operaciones (como "monto").
     * @return Resultado de la operación.
     * @throws IllegalArgumentException si la opción es inválida.
     */
    public static String menu(String operacion, Map<String, Object> args) {
        operacion = operacion.trim().toLowerCase();

        switch (operacion) {
            case "c":
                return consulta(args);
            case "d":
                return deposito(args);
            case "r":
                return retiro(args);
            case "s":
                return salir(args);
            default:
                throw new IllegalArgumentException("Error: Opción inválida");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String opcion = "";

        while (!opcion.equals("s")) {
            System.out.println("""
              Ingresa la opción:
              c: Consultar el saldo actual
              d: Depositar a la cuenta
              r: Retirar de la cuenta
              s: Salir de la cuenta
            """);

            System.out.print("Opción: ");
            opcion = scanner.nextLine().trim().toLowerCase();
            Map<String, Object> argumentos = new HashMap<>();

            if (opcion.equals("d") || opcion.equals("r")) {
                System.out.print("Ingrese el monto: ");
                String montoInput = scanner.nextLine().trim();

                if (montoInput.isEmpty()) {
                    argumentos.put("monto", null);
                } else {
                    try {
                        double monto = Double.parseDouble(montoInput);
                        argumentos.put("monto", monto);
                    } catch (NumberFormatException e) {
                        argumentos.put("monto", "invalido");
                    }
                }
            }

            try {
                // Validar monto inválido explícitamente antes del menú
                if ((opcion.equals("d") || opcion.equals("r")) &&
                        "invalido".equals(argumentos.get("monto"))) {
                    throw new IllegalArgumentException("Error: El monto debe ser número positivo mayor que cero");
                }

                String resultado = menu(opcion, argumentos);
                System.out.println(resultado);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }
}
