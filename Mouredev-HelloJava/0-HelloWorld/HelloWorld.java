public class HelloWorld {
    
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


    }
}