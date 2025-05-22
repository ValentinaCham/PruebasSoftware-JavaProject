package classes;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CajeroAutomaticoTest {

    @Before
    public void resetSaldo() throws Exception {
        // Reset el saldo a 1000 antes de cada prueba.
        java.lang.reflect.Field saldoField = CajeroAutomatico.class.getDeclaredField("saldo");
        saldoField.setAccessible(true);
        saldoField.setDouble(null, 1000.0);
    }

    @Test
    public void caso1_testConsultaSaldoInicial() {
        String resultado = CajeroAutomatico.menu("c", new HashMap<>());
        assertEquals("El saldo de la cuenta es 1000", resultado);
    }

    @Test
    public void caso2_testDepositoValido() {
        Map<String, Object> args = new HashMap<>();
        args.put("monto", 500.0);
        String resultado = CajeroAutomatico.menu("d", args);
        assertEquals("Deposito exitoso", resultado);
    }

    @Test
    public void caso3_testRetiroMenorAlSaldo() {
        Map<String, Object> args = new HashMap<>();
        args.put("monto", 300.0);
        String resultado = CajeroAutomatico.menu("r", args);
        assertEquals("Retiro exitoso", resultado);
    }

    @Test
    public void caso4_testRetiroExactoDelSaldo() throws Exception {
        Map<String, Object> args = new HashMap<>();
        args.put("monto", 1000.0);
        String resultado = CajeroAutomatico.menu("r", args);
        assertEquals("Retiro exitoso", resultado);
    }

    @Test
    public void caso5_testSalirDelSistema() {
        String resultado = CajeroAutomatico.menu("s", new HashMap<>());
        assertEquals("Gracias por usar el cajero automático", resultado);
    }

    @Test(expected = IllegalArgumentException.class)
    public void caso6_testFaltaMontoEnDeposito() {
        CajeroAutomatico.menu("d", new HashMap<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void caso7_testFaltaMontoEnRetiro() {
        CajeroAutomatico.menu("r", new HashMap<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void caso8_testRetiroMayorAlSaldo() {
        Map<String, Object> args = new HashMap<>();
        args.put("monto", 1500.0);
        CajeroAutomatico.menu("r", args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void caso9_testDepositoMontoCero() {
        Map<String, Object> args = new HashMap<>();
        args.put("monto", 0.0);
        CajeroAutomatico.menu("d", args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void caso10_testDepositoMontoNegativo() {
        Map<String, Object> args = new HashMap<>();
        args.put("monto", -100.0);
        CajeroAutomatico.menu("d", args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void caso11_testRetiroMontoCero() {
        Map<String, Object> args = new HashMap<>();
        args.put("monto", 0.0);
        CajeroAutomatico.menu("r", args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void caso12_testRetiroMontoNegativo() {
        Map<String, Object> args = new HashMap<>();
        args.put("monto", -200.0);
        CajeroAutomatico.menu("r", args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void caso13_testOpcionInvalida() {
        CajeroAutomatico.menu("x", new HashMap<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void caso14_testOpcionVacia() {
        CajeroAutomatico.menu(" ", new HashMap<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void caso15_testTextoComoMontoDeposito() {
        Map<String, Object> args = new HashMap<>();
        args.put("monto", "abc");
        CajeroAutomatico.menu("d", args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void caso16_testTextoComoMontoRetiro() {
        Map<String, Object> args = new HashMap<>();
        args.put("monto", "hola");
        CajeroAutomatico.menu("r", args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void caso17_testCaracteresEspecialesComoMonto() {
        Map<String, Object> args = new HashMap<>();
        args.put("monto", "/12");
        CajeroAutomatico.menu("d", args);
    }
}