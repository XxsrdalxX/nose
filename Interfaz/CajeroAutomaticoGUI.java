package Interfaz;

import Modelo.*;

import javax.swing.JOptionPane;

public class CajeroAutomaticoGUI {

    private Cuentabancaria cuenta;

    public CajeroAutomaticoGUI() {
        // Crear cuenta nueva
        JOptionPane.showMessageDialog(null, "¡Bienvenido al cajero! Vamos a crear tu cuenta bancaria ");

        String numeroCuenta = JOptionPane.showInputDialog("Crea tu número de cuenta:");
        String pin = JOptionPane.showInputDialog("Crea tu PIN:");
        double saldo = Double.parseDouble(JOptionPane.showInputDialog("Ingresa tu saldo inicial:"));

        cuenta = new CuentaCorriente(numeroCuenta, pin, saldo);

        JOptionPane.showMessageDialog(null, "¡Cuenta creada exitosamente! ");

        // Ahora pedir login
        String numeroIngresado = JOptionPane.showInputDialog("Ingresa tu número de cuenta para iniciar sesión:");
        String pinIngresado = JOptionPane.showInputDialog("Ingresa tu PIN:");

        if (cuenta.autenticar(numeroIngresado, pinIngresado)) {
            mostrarMenu();
        } else {
            JOptionPane.showMessageDialog(null, "Datos incorrectos. Acceso denegado ");
        }
    }
    private void mostrarMenu() {
        int opcion = 0; // ¡Inicializamos aquí para evitar errores!
        do {
            String input = JOptionPane.showInputDialog(
                    "Elige una opción:\n" +
                    "1. Consultar saldo\n" +
                    "2. Depositar dinero\n" +
                    "3. Retirar dinero\n" +
                    "4. Pagar servicio\n" +
                    "5. Salir");
    
            if (input == null) break; // Si le da "Cancelar" en el menú, salimos
    
            try {
                opcion = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                mostrarPanel("Por favor, ingresa un número válido.");
                opcion = 0; // Volvemos al menú sin explotar
                continue;
            }
    
            switch (opcion) {
                case 1:
                    mostrarPanel("Tu saldo actual es: $" + cuenta.getSaldo());
                    break;
    
                case 2:
                    String depositoInput = JOptionPane.showInputDialog("Monto a depositar:");
                    if (depositoInput == null) break;
                    try {
                        double deposito = Double.parseDouble(depositoInput);
                        cuenta.depositar(deposito);
                        mostrarPanel("Depósito exitoso. Saldo actual: $" + cuenta.getSaldo());
                    } catch (NumberFormatException e) {
                        mostrarPanel("Monto inválido.");
                    }
                    break;
    
                case 3:
                    String retiroInput = JOptionPane.showInputDialog("Monto a retirar:");
                    if (retiroInput == null) break;
                    try {
                        double retiro = Double.parseDouble(retiroInput);
                        cuenta.retirar(retiro);
                        mostrarPanel("Retiro realizado. Saldo actual: $" + cuenta.getSaldo());
                    } catch (NumberFormatException e) {
                        mostrarPanel("Monto inválido.");
                    }
                    break;
    
                case 4:
                    String pagoInput = JOptionPane.showInputDialog("Monto del servicio a pagar:");
                    if (pagoInput == null) break;
                    try {
                        double pago = Double.parseDouble(pagoInput);
                        cuenta.pagarServicio(pago);
                        mostrarPanel("Servicio pagado. Saldo actual: $" + cuenta.getSaldo());
                    } catch (NumberFormatException e) {
                        mostrarPanel("Monto inválido.");
                    }
                    break;
    
                case 5:
                    JOptionPane.showMessageDialog(null, "Gracias por usar el cajero. ¡Hasta luego!");
                    break;
    
                default:
                    mostrarPanel("Opción inválida. Intenta otra vez.");
            }
        } while (opcion != 5);
    }
    
    private void mostrarPanel(String mensaje) {
        JOptionPane.showOptionDialog(
                null,
                mensaje,
                "Resultado",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[]{"Atrás"},
                "Atrás"
        );
    }
    
    
    
    }

