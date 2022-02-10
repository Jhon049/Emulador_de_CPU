package emulador;

public class Registro {

    int valor = 0;

    Registro() {
    }

    Registro(int valor){
        this.valor = valor;
    }

    public void mov(String Rx, String Ry) {
        //Ry.valor = Rx.valor;
    }

    public void movRXaRY(Registro Rx, int valor) {
        Rx.valor = valor;
    }

    public void add(Registro Rx, Registro Ry) {
        //Rx.valor = (int) ((Rx.valor + Ry.valor) % Math.pow(2, 32));
    }

    public void dec(Registro Rx) {
        if (Rx.valor == 0) {
            Rx.valor = (int) (Math.pow(2, 32) - 1);
        } else {
            Rx.valor = Rx.valor - 1;
        }
    }

    public void inc(Registro Rx) {
        if (Rx.valor == (int) (Math.pow(2, 32) - 1)) {
            Rx.valor = 0;
        } else {
            Rx.valor = Rx.valor - 1;
        }
    }

    public static String inv (String reg){
        int valor = Integer.parseInt(reg);
        String val = Integer.toBinaryString(valor);
        String inv = "";
        for(int i=0; i<val.length(); i++) {
            if (val.charAt(i) == '0') {
                inv = inv + "1";
            } else {
                inv = inv + "0";
            }
        }
        int decimal = Integer.parseInt(inv,2);
        String inv2 = String.valueOf(decimal);
        return inv2;
    }

}
