package classes;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class ParImparTest {

	// a) Condiciones válidas

    @Test
    public void caso1_listaSoloPares() {
        List<Integer> entrada = Arrays.asList(2, 4, 6);
        List<String> esperado = Arrays.asList("par", "par", "par");
        assertEquals(esperado, ParImpar.parImpar(entrada));
    }

    @Test
    public void caso2_listaSoloImpares() {
        List<Integer> entrada = Arrays.asList(1, 3, 5);
        List<String> esperado = Arrays.asList("impar", "impar", "impar");
        assertEquals(esperado, ParImpar.parImpar(entrada));
    }

    @Test
    public void caso3_listaMixta() {
        List<Integer> entrada = Arrays.asList(1, 2, 3, 4, 5);
        List<String> esperado = Arrays.asList("impar", "par", "impar", "par", "impar");
        assertEquals(esperado, ParImpar.parImpar(entrada));
    }

    @Test
    public void caso4_listaConCero() {
        List<Integer> entrada = Collections.singletonList(0);
        List<String> esperado = Collections.singletonList("par");
        assertEquals(esperado, ParImpar.parImpar(entrada));
    }

    @Test
    public void caso5_listaConNegativos() {
        List<Integer> entrada = Arrays.asList(-2, -3, -4, -5);
        List<String> esperado = Arrays.asList("par", "impar", "par", "impar");
        assertEquals(esperado, ParImpar.parImpar(entrada));
    }

    // b) Condiciones inválidas

    @Test(expected = IllegalArgumentException.class)
    public void caso6_cantidadCero() {
        ParImpar.parImpar(new ArrayList<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void caso7_listaNull() {
        ParImpar.parImpar(null);
    }
}
