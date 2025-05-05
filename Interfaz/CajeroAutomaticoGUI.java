package Interfaz;

import Modelo.*;

import javax.swing.JOptionPane;

public class CajeroAutomaticoGUI {

    private Cuentabancaria cuenta;

    public CajeroAutomaticoGUI() {
       
        int seleccion = JOptionPane.showOptionDialog(
                null,
                "Bienvenido al cajero automático. ¿Qué deseas hacer?",
                "Cajero Automático",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[]{"Iniciar sesión", "Registrarse", "Salir"},
                "Iniciar sesión"
        );

        if (seleccion == 1) { // Registrarse
            registrarCuenta();
        } else if (seleccion == 0) { // Iniciar sesión
            iniciarSesion();
        } else {
            JOptionPane.showMessageDialog(null, "Operación cancelada. Hasta luego");
        }
    }

    private void registrarCuenta() {
        JOptionPane.showMessageDialog(null, "Vamos a crear tu cuenta bancaria");

        String numeroCuenta = JOptionPane.showInputDialog("Crea tu número de cuenta:");
        String pin = JOptionPane.showInputDialog("Crea tu PIN:");
        double saldo = Double.parseDouble(JOptionPane.showInputDialog("Ingresa tu saldo inicial:"));

        cuenta = new CuentaCorriente(numeroCuenta, pin, saldo);

        JOptionPane.showMessageDialog(null, "Cuenta creada exitosamente");
        iniciarSesion(); 
    }

    private void iniciarSesion() {
        if (cuenta == null) {
            JOptionPane.showMessageDialog(null, "No hay cuentas registradas. Por favor, registrate primero.");
            new CajeroAutomaticoGUI(); // Volver a mostrar las opciones iniciales
            return;
        }
    
        String numeroIngresado = JOptionPane.showInputDialog("Ingresa tu numero de cuenta para iniciar sesión:");
        String pinIngresado = JOptionPane.showInputDialog("Ingresa tu PIN:");
    
        if (cuenta.autenticar(numeroIngresado, pinIngresado)) {
            mostrarMenu();
        } else {
            JOptionPane.showMessageDialog(null, "Datos incorrectos. Acceso denegado.");
        }
    }

    private void mostrarMenu() {
        int opcion = 0;
        do {
            String input = JOptionPane.showInputDialog(
                    "Elige una opcion:\n" +
                    "1. Consultar saldo\n" +
                    "2. Depositar dinero\n" +
                    "3. Retirar dinero\n" +
                    "4. Pagar servicio\n" +
                    "5. Salir");

            if (input == null) break;

            try {
                opcion = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                mostrarPanel("Por favor, ingresa un número valido.");
                opcion = 0;
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
                        mostrarPanel("Monto invalido.");
                    }
                    break;

                case 5:
                    JOptionPane.showMessageDialog(null, "Gracias por usar el cajero. Hasta luego");
                    break;

                default:
                    mostrarPanel("Opción inválida. Intenta otra vez.");
            }
        } while (opcion != 5);
    }
    private void mostrarPanel(String mensaje) {
        JOptionPane.showMessageDialog(
                null,
                mensaje,
                "Resultado",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

}