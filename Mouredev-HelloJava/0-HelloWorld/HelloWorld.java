public class HelloWorld {

    //ESTO ES PARTE DE SCOPE EXPLICACION
    public class Persona {
        String nombre; //Varaible de instancia
        static int total = 0; //Variable estatica

        public void mostrar() {
            int edad = 30; // Variable local
            System.out.println(nombre + " tiene " + edad + " años.");
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Hola me lllamo Claudio y estoy inciando en Java");

        //COMENTARIOS
        // Aqui tenemos un comentario de una sola linea
        /*
        Aqui tenemos un comentario
        de varias lineas
        de informacion
        */
        //----------------------------------------------
        //VARIABLES
        int edad; //Declarando la variable
        edad = 26; //Asignando la variable
        System.out.println("La edad es de: " + edad);

        //Tipo de variable primitiva
        int numero = 10;
        double precio = 29.99;
        boolean activo = true;
        char letra = 'A';
        System.err.println("Numero: " + numero);
        System.out.println("Precio: " + precio);
        System.err.println("Activo: " + activo);
        System.err.println("Letra: " + letra);

        //Referencia (objetos)
        String nombre = "Claudio";
        HelloWorld p = new HelloWorld();
        System.err.println(nombre);

        //Scope 
        /*
        Donde se puede usar una variabler depende de donde se declara
        - Dentro de un metodo: solo en ese metodo (variable local)
        - Dentro de una clase, fuera de métodos: En cualquier método (Variable de instancia)
        - Con 'static' dentro de clase: Pertenece a la clase, no al objeto (variable estatica) 
        */
        
        //INICIALIZACION POR DEFECTO
        /*Si no inicializas una variable primitiva a nivel de clase:
            * int, byte, short, long -< 0
            * float, double -> 0.0
            * boolean -> false
            * char -> '\u0000' (Caracter nulo) 
        Pero las variables 
        */
        
        //CONSTANTE
        //Si no quieres que el valor cambie 
        final int DIAS_SEMANA = 7;
        System.out.println(DIAS_SEMANA);

        //TIPOS DE DATOS
            //PRIMITIVOS
        byte tipoByte = 10; //Números pequeños (-128 a 127)
        short tipoShort = 5000; //Mas grande que byte
        int tipoInt = 100; //Enteros más grandes
        long tipoLong = 1000000L; //Enteros grandes
        float tipoFloat = 5.75f; //Decimales, menos precisos
        double tipoDouble = 19.99; //Decimasles más precisos
        char tipoChar = 'A'; //Solo un caracter
        boolean tipoBoolean = false; //Verdadero/falso logico
        System.out.println(tipoByte + tipoShort + tipoInt + tipoLong);
        System.out.println(tipoFloat + tipoDouble + tipoChar);
        System.out.println(tipoBoolean);

            //POR REFERENCIA
        


    }
}