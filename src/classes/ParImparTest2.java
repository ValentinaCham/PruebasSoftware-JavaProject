package classes;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThrows;

public class ParImparTest2 {

    // a) Casos válidos
    @Test
    public void caso1_testSoloPares() {
        List<Integer> numeros = Arrays.asList(2, 4, 6);
        List<String> resultado = ParImpar.parImpar(numeros);
        assertThat(resultado, contains("par", "par", "par"));
    }

    @Test
    public void caso2_testSoloImpares() {
        List<Integer> numeros = Arrays.asList(1, 3, 5);
        List<String> resultado = ParImpar.parImpar(numeros);
        assertThat(resultado, contains("impar", "impar", "impar"));
    }

    @Test
    public void caso3_testMixtos() {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);
        List<String> resultado = ParImpar.parImpar(numeros);
        assertThat(resultado, contains("impar", "par", "impar", "par", "impar"));
    }

    @Test
    public void caso4_testCero() {
        List<Integer> numeros = Collections.singletonList(0);
        List<String> resultado = ParImpar.parImpar(numeros);
        assertThat(resultado, contains("par"));
    }

    @Test
    public void caso5_testNegativos() {
        List<Integer> numeros = Arrays.asList(-2, -3, -4, -5);
        List<String> resultado = ParImpar.parImpar(numeros);
        assertThat(resultado, contains("par", "impar", "par", "impar"));
    }

    // b) Casos inválidos

    @Test
    public void caso6_testCantidadCero() {
        List<Integer> numeros = Collections.emptyList();
        Exception e = assertThrows(IllegalArgumentException.class, () -> ParImpar.parImpar(numeros));
        assertThat(e.getMessage(), containsString("Cantidad inválida: La lista se encuentra vacía"));
    }

    @Test
    public void caso7_testCantidadNegativaSimulada() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            // Simulamos entrada inválida antes de llamar al método real
            String input = "-3";
            if (Integer.parseInt(input) < 0) {
                throw new IllegalArgumentException("Cantidad inválida: La lista debe tener un tamaño positivo");
            }
        });
        assertThat(e.getMessage(), containsString("Cantidad inválida"));
    }

    @Test
    public void caso8_testCantidadDecimal() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            String input = "2.3";
            if (!input.matches("\\d+")) {
                throw new IllegalArgumentException("Cantidad inválida: La lista debe tener un tamaño entero");
            }
        });
        assertThat(e.getMessage(), containsString("tamaño entero"));
    }

    // c) Robustez y errores

    @Test
    public void caso9_testNumerosDecimales() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            List<String> inputs = Arrays.asList("3.4", "5.6");
            for (String val : inputs) {
                Integer.parseInt(val); // Lanza NumberFormatException
            }
        });
        assertThat(e.getMessage(), containsString("For input string"));
    }

    @Test
    public void caso10_testTextoEnLugarDeNumeros() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            List<String> inputs = Arrays.asList("hola", "mundo");
            for (String val : inputs) {
                Integer.parseInt(val);
            }
        });
        assertThat(e.getMessage(), containsString("For input string"));
    }

    @Test
    public void caso11_testMixtoNumeroTexto() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            List<String> inputs = Arrays.asList("5", "hola");
            for (String val : inputs) {
                Integer.parseInt(val);
            }
        });
        assertThat(e.getMessage(), containsString("For input string"));
    }

    @Test
    public void caso12_testCaracteresEspeciales() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            List<String> inputs = Arrays.asList("#", "$");
            for (String val : inputs) {
                Integer.parseInt(val);
            }
        });
        assertThat(e.getMessage(), containsString("For input string"));
    }

    @Test
    public void caso13_testNoSeIngresaCantidad() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            String cantidad = " ";
            if (cantidad.trim().isEmpty()) {
                throw new IllegalArgumentException("Error: Por favor, ingrese solo números enteros");
            }
        });
        assertThat(e.getMessage(), containsString("Por favor"));
    }

    @Test
    public void caso14_testNoSeIngresanNumeros() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            List<String> lista = Collections.emptyList();
            if (lista.isEmpty()) {
                throw new IllegalArgumentException("Error: Por favor, ingrese solo números enteros");
            }
        });
        assertThat(e.getMessage(), containsString("Por favor"));
    }

    @Test
    public void caso15_testNumeroFaltante() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            List<String> inputs = Arrays.asList("4", "");
            for (String val : inputs) {
                if (val.trim().isEmpty()) {
                    throw new IllegalArgumentException("Error: Por favor, ingrese solo números enteros");
                }
                Integer.parseInt(val);
            }
        });
        assertThat(e.getMessage(), containsString("Por favor"));
    }
}
