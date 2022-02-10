package emulador;
import java.util.Scanner;

public class Main {
    static String subrutina[] = new String [1024];
    static Registro[] registro1 = new Registro[43];
    static Scanner captura = new Scanner(System.in);

    public static void main(String[] args) {

        for (int i = 0; i < 42; i++) {
             registro1[i] = new Registro();
        }
        System.out.print("Indicar el número de instrucciones a ingresar: ");
        int instrucciones = Integer.parseInt(captura.nextLine());

        for(int i=0; i<instrucciones; i++) {
            System.out.println("Indique la instrucción " + (i+1) + ": ");
            subrutina[i] = captura.nextLine();
        }
        Nombre();
    }

    public static void Nombre(){
        char caracter;
        int i = 0;
        int pos = 0;
        int pos2 = 0;
        int dato = 0;
        String posicion = "";
        String posicion2 = "";
        System.out.println("Entré al método Nombre");

        while(subrutina[i]!=null){
            System.out.println("Ingresé al While, " + i);
            System.out.println("Posicion siguiente: "+ subrutina[i++]);
            caracter = subrutina[i].charAt(0);
            switch (caracter){
                case 'M':
                    caracter = subrutina[i].charAt(4);
                    if(caracter=='R'){
                        posicion = String.valueOf(subrutina[i].charAt(5));
                        posicion = posicion + subrutina[i].charAt(6);
                        pos = Integer.parseInt(posicion);
                        posicion2 = String.valueOf(subrutina[i].charAt(9));
                        posicion2 = posicion2 + subrutina[i].charAt(10);
                        pos2 = Integer.parseInt(posicion2);
                        registro1[pos2].valor = registro1[pos].valor;
                    }
                    else {
                        int posEsp=0;
                        int posCom=0;
                        for (int j = 0; j < subrutina[i].length(); j++) {
                            if (subrutina[i].charAt(j) == ' ') {
                                posEsp = j;
                            } else {
                                if (subrutina[i].charAt(j) == ',') {
                                    posCom = j;
                                }
                            }
                        }
                        dato = Integer.parseInt(String.valueOf(subrutina[i]).substring((posEsp+1), (posCom)));
                        posicion = String.valueOf(subrutina[i].charAt(posCom+2));
                        posicion = posicion + subrutina[i].charAt(posCom+3);
                        pos = Integer.parseInt(posicion);
                        registro1[pos].valor = dato;
                    }
                    break;

                case 'A':
                    posicion = String.valueOf(subrutina[i].charAt(5));
                    posicion = posicion + subrutina[i].charAt(6);
                    pos = Integer.parseInt(posicion);
                    posicion2 = String.valueOf(subrutina[i].charAt(9));
                    posicion2 = posicion2 + subrutina[i].charAt(10);
                    pos2 = Integer.parseInt(posicion2);
                    registro1[pos].valor = (int)((registro1[pos].valor + registro1[pos2].valor) % Math.pow(2,32));
                    break;

                case 'D':
                    posicion = String.valueOf(subrutina[i].charAt(5));
                    posicion = posicion + subrutina[i].charAt(6);
                    pos = Integer.parseInt(posicion);
                    if(registro1[pos].valor==0){
                        registro1[pos].valor = (int)(Math.pow(2,32)-1);
                    }
                    else{
                        registro1[pos].valor--;
                    }
                    break;

                case 'I':
                    caracter = subrutina[i].charAt(2);
                    if(caracter=='C'){
                        registro1[6].valor=2147483647;
                        posicion = String.valueOf(subrutina[i].charAt(5));
                        posicion = posicion + subrutina[i].charAt(6);
                        pos = Integer.parseInt(posicion);
                        if(registro1[pos].valor == (int)(Math.pow(2,32)-1)){
                            registro1[pos].valor = 0;
                        }
                        else{
                            registro1[pos].valor++;
                        }
                        System.out.println(registro1[6].valor);
                    }
                    else{
                        String cadena = "";
                        posicion = String.valueOf(subrutina[i].charAt(5));
                        posicion = posicion + subrutina[i].charAt(6);
                        pos = Integer.parseInt(posicion);
                        cadena = Registro.inv(String.valueOf(registro1[pos].valor));
                        registro1[pos].valor = Integer.parseInt(cadena);
                    }
                    break;

                case 'J':
                    caracter = subrutina[i].charAt(1);
                    if(caracter=='M'){
                        dato = Integer.parseInt(String.valueOf(subrutina[i]).substring(4, (subrutina[i].length())));
                        i = dato;
                        System.out.println(i);
                    }
                    else{
                        //registro1[0].valor=0;
                        dato = Integer.parseInt(String.valueOf(subrutina[i]).substring(3, (subrutina[i].length())));
                        if(registro1[0].valor==0){
                            i = dato;
                        }
                        System.out.println(i);
                    }
                    break;

                case 'N':
                    break;
            }
            i++;
        }
        System.out.println("El resultado final es: "+ registro1[42].valor);
    }
}
