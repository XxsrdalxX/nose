package Modelo;

public class Cuentabancaria {
    private String numeroCuenta;
    private String pin;
    private double saldo;

    public Cuentabancaria(String numeroCuenta, String pin, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.pin = pin;
        this.saldo = saldoInicial;
    }

    public boolean autenticar(String numeroCuenta, String pin) {
        return this.numeroCuenta.equals(numeroCuenta) && this.pin.equals(pin);
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double monto) {
        saldo += monto;
    }

    public boolean retirar(double monto) {
        if (monto <= saldo) {
            saldo -= monto;
            return true;
        }
        return false;
    }

    public boolean pagarServicio(double monto) {
        return retirar(monto);
    }
}
