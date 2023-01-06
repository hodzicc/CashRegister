package ba.unsa.etf.rpr.exceptions;

public class CashRegisterException extends Exception{
    public CashRegisterException(String msg, Exception reason){
        super(msg, reason);
    }

    public CashRegisterException(String msg){
        super(msg);
    }

}
