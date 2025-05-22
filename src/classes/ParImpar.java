package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParImpar {

    /**
     * Determina la paridad (par o impar) de cada número de una lista.
     *
     * @param numeros Lista de números enteros.
     * @return Lista de strings con "par" o "impar" por cada número.
     * @throws IllegalArgumentException Si la lista está vacía.
     */
    public static List<String> parImpar(List<Integer> numeros) {
        if (numeros == null || numeros.isEmpty()) {
            throw new IllegalArgumentException("Cantidad inválida: La lista se encuentra vacía.");
        }

        List<String> resultado = new ArrayList<>();
        for (int n : numeros) {
            resultado.add(n % 2 == 0 ? "par" : "impar");
        }
        return resultado;
    }

    public static void main(String[] args) {
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Ingrese la cantidad de números a analizar: ");
            String cantidadInput = scanner.nextLine().trim();

            if (cantidadInput.isEmpty()) {
                throw new IllegalArgumentException("Error: Por favor, ingrese solo números enteros");
            }

            // Verifica si es número entero válido (descarta negativos, decimales, texto)
            if (!cantidadInput.matches("\\d+")) {
                throw new IllegalArgumentException("Cantidad inválida: La lista debe tener un tamaño entero");
            }

            int cantidad = Integer.parseInt(cantidadInput);

            if (cantidad < 0) {
                throw new IllegalArgumentException("Cantidad inválida: La lista debe tener un tamaño positivo");
            }
            if (cantidad == 0) {
                throw new IllegalArgumentException("Cantidad inválida: La lista se encuentra vacía");
            }

            List<Integer> numeros = new ArrayList<>();
            for (int i = 0; i < cantidad; i++) {
                System.out.print("Ingrese el número " + (i + 1) + ": ");
                String valor = scanner.nextLine().trim();

                if (valor.isEmpty()) {
                    throw new IllegalArgumentException("Error: Por favor, ingrese solo números enteros");
                }

                try {
                    int num = Integer.parseInt(valor);
                    numeros.add(num);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Error: Por favor, ingrese solo números enteros");
                }
            }

            List<String> resultados = parImpar(numeros);
            for (int i = 0; i < numeros.size(); i++) {
                System.out.println("El número " + numeros.get(i) + " es " + resultados.get(i));
            }

        } catch (IllegalArgumentException ve) {
            System.out.println(ve.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado.");
        }

        scanner.close();
    }
}
