package classes;

import java.util.Scanner;

public class AreaRectangulo {

    /**
     * Calcula el área de un rectángulo.
     *
     * @param base   Longitud de la base.
     * @param altura Longitud de la altura.
     * @return Área calculada.
     * @throws IllegalArgumentException Si base o altura no son mayores que cero.
     */
    public static double areaRectangulo(double base, double altura) {
        if (base <= 0 || altura <= 0) {
            throw new IllegalArgumentException("Rectángulo inválido: Los valores deben ser positivos.");
        }
        return base * altura;
    }

    @SuppressWarnings("resource")
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            try {
                System.out.print("Ingrese la longitud del primer lado: ");
                double baseInput = Double.parseDouble(scanner.nextLine().trim());

                System.out.print("Ingrese la longitud del segundo lado: ");
                double alturaInput = Double.parseDouble(scanner.nextLine().trim());

                // Calcular área
                double area = areaRectangulo(baseInput, alturaInput);
                System.out.printf("El rectángulo con base %.2f y altura %.2f tiene como área %.2f.%n",
                                  baseInput, alturaInput, area);

            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Error: Por favor, ingrese solo números enteros o decimales para la base y altura.");
            }

        } catch (IllegalArgumentException ve) {
            System.out.println(ve.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Por favor, ingrese solo números enteros o decimales para la base y altura.");
        }

        scanner.close();
    }
}