public class RaizCuadrada {
    
    public native double raizc(double n);

    static {
        System.loadLibrary("raizc");
    }

    public static void main(String[] args) {
        RaizCuadrada rc = new RaizCuadrada();
        double resultado = rc.raizc(2);
        System.out.println("Raiz cuadrada de 2: " + resultado);
    }
}
