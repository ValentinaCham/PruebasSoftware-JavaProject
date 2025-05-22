package classes;

import org.junit.Test;
import static org.junit.Assert.*;

public class AreaRectanguloTest {

    // a) Condiciones válidas
	@Test
    public void caso1_valoresEnteros() {
        assertEquals(6.0, AreaRectangulo.areaRectangulo(2, 3), 0.001);
    }

    @Test
    public void caso2_valoresDecimales() {
        assertEquals(15.75, AreaRectangulo.areaRectangulo(3.5, 4.5), 0.001);
    }

    @Test
    public void caso3_enteroYDecimal() {
        assertEquals(11.0, AreaRectangulo.areaRectangulo(2.2, 5), 0.001);
    }

    @Test
    public void caso4a_baseDecimal() {
        assertEquals(11.0, AreaRectangulo.areaRectangulo(2.2, 5), 0.001);
    }

    @Test
    public void caso4b_alturaDecimal() {
        assertEquals(11.0, AreaRectangulo.areaRectangulo(2, 5.5), 0.001);
    }

    @Test
    public void caso5_baseMayorQueAltura() {
        assertEquals(80.0, AreaRectangulo.areaRectangulo(10, 8), 0.001);
    }

    @Test
    public void caso6_alturaMayorQueBase() {
        assertEquals(80.0, AreaRectangulo.areaRectangulo(8, 10), 0.001);
    }

    @Test
    public void caso7_baseIgualAltura() {
        assertEquals(100.0, AreaRectangulo.areaRectangulo(10, 10), 0.001);
    }

    // b) Condiciones inválidas
    @Test
    public void caso8_valoresCero() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> 
            AreaRectangulo.areaRectangulo(0, 0));
        assertEquals("Rectángulo inválido: Los valores deben ser positivos.", e.getMessage());
    }

    @Test
    public void caso9_valorNegativo() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> 
        	AreaRectangulo.areaRectangulo(-3, 5));
        assertEquals("Rectángulo inválido: Los valores deben ser positivos.", e.getMessage());
    }

    @Test
    public void caso10a_valorNegativo() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> 
            AreaRectangulo.areaRectangulo(-3, 5));
        assertEquals("Rectángulo inválido: Los valores deben ser positivos.", e.getMessage());
    }

    @Test
    public void caso10b_valorNegativo() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> 
            AreaRectangulo.areaRectangulo(3, -5));
        assertEquals("Rectángulo inválido: Los valores deben ser positivos.", e.getMessage());
    }

    // c) Manejo de errores en parseo - simulados con parseDouble
    @Test
    public void caso11_alfabeticos() {
        assertThrows(NumberFormatException.class, () -> Double.parseDouble("hola"));
        assertThrows(NumberFormatException.class, () -> Double.parseDouble("adiós"));
    }

    @Test
    public void caso12_caracteresEspeciales() {
        assertThrows(NumberFormatException.class, () -> Double.parseDouble("*/12/*"));
        assertThrows(NumberFormatException.class, () -> Double.parseDouble("-9-"));
    }

    // d) Robustez de código
    @Test
    public void caso13_sinValores() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> 
            AreaRectangulo.areaRectangulo(0, 0));
        assertEquals("Rectángulo inválido: Los valores deben ser positivos.", e.getMessage());
    }

    @Test
    public void caso14_valorUnico() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> 
            AreaRectangulo.areaRectangulo(5, 0));
        assertEquals("Rectángulo inválido: Los valores deben ser positivos.", e.getMessage());
    }
}