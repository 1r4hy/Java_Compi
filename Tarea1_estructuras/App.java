import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;
import java.util.Map;

/*
 * Tarea 1: Estructuras de Datos
 * Nombre: Ira-hy Barrueta de la Mora
 * Matricula: A00835435
 * Fecha: 25/09/2023
 * 
 * Git: https://github.com/1r4hy/Java_Compi
 * 
 * ---------------------------------------------------
 * TEST CASES:
 * 
 * 1. STACK (LIFO):
 *    - Insertar elementos y verificar el orden (último en entrar, primero en salir).
 *    - Probar peek(), pop() y size().
 *    - Intentar pop() en pila vacía (manejo de excepciones).
 *    - Insertar null y verificar comportamiento.
 * 
 * 2. QUEUE (FIFO):
 *    - Insertar elementos y verificar el orden (primero en entrar, primero en salir).
 *    - Probar peek(), poll() y size().
 *    - Intentar poll() en cola vacía.
 *    - Insertar null y verificar comportamiento.
 * 
 * 3. DICTIONARY (HASHMAP):
 *    - Insertar pares clave-valor y verificar recuperación.
 *    - Probar containsKey(), remove() y clear().
 *    - Insertar null como clave y valor.
 *    - Verificar tamaño después de operaciones.
 */

public class App {
    public static void main(String[] args) {
        String[] invitados = {"ira-hy", "Juancho", "Pedrito", "Valeria", "Fany", "Marce"};
        Invitado[] datosInvitados = {
            new Invitado("ira-hy", 20),
            new Invitado("Juancho", 21),
            new Invitado(null, 22),       // Caso con null
            new Invitado("Valeria", 23)
        };

        System.out.println("============= STACK TEST =============");
        testStack(invitados);

        System.out.println("\n============= QUEUE TEST =============");
        testQueue(invitados);

        System.out.println("\n============= DICTIONARY TEST =============");
        testDictionary(datosInvitados);
    }

    // ================== STACK (LIFO) ==================
    public static void testStack(String[] elements) {
        Stack<String> stack = new Stack<>();

        System.out.println("1. Llenando la pila:");
        for (String elem : elements) {
            stack.push(elem);
        }
        System.out.println("Pila actual: " + stack);

        System.out.println("\n2. Probando peek(): " + stack.peek());
        System.out.println("3. Probando pop(): " + stack.pop());
        System.out.println("Pila después de pop: " + stack);

        System.out.println("\n4. Vaciando la pila:");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }

        System.out.println("\n\n5. Intentando pop() en pila vacía:");
        try {
            stack.pop();
        } catch (Exception e) {
            System.out.println("Excepción atrapada: " + e.getClass().getSimpleName());
        }

        System.out.println("\n6. Insertando null:");
        stack.push(null);
        System.out.println("Peek con null: " + stack.peek());
    }

    // ================== QUEUE (FIFO) ==================
    public static void testQueue(String[] elements) {
        Queue<String> queue = new LinkedList<>();

        System.out.println("1. Llenando la cola:");
        for (String elem : elements) {
            queue.add(elem);
        }
        System.out.println("Cola actual: " + queue);

        System.out.println("\n2. Probando peek(): " + queue.peek());
        System.out.println("3. Probando poll(): " + queue.poll());
        System.out.println("Cola después de poll: " + queue);

        System.out.println("\n4. Vaciando la cola:");
        while (!queue.isEmpty()) {
            System.out.print(queue.poll() + " ");
        }

        System.out.println("\n\n5. Intentando poll() en cola vacía:");
        System.out.println("Resultado: " + queue.poll()); // Debería retornar null

        System.out.println("\n6. Insertando null:");
        queue.add(null);
        System.out.println("Peek con null: " + queue.peek());
    }

    // ================== DICTIONARY (HASHMAP) ==================
    static class Invitado {
        String nombre;
        int edad;

        public Invitado(String nombre, int edad) {
            this.nombre = nombre;
            this.edad = edad;
        }

        @Override
        public String toString() {
            return nombre + " (" + edad + ")";
        }
    }

    public static void testDictionary(Invitado[] invitados) {
        Map<String, Invitado> diccionario = new HashMap<>();

        System.out.println("1. Llenando el diccionario:");
        for (Invitado inv : invitados) {
            diccionario.put(inv.nombre, inv);
        }
        System.out.println("Diccionario: " + diccionario);

        System.out.println("\n2. Probando get() y containsKey():");
        System.out.println("¿Existe 'Juancho'? " + diccionario.containsKey("Juancho"));
        System.out.println("Valor para 'Juancho': " + diccionario.get("Juancho"));

        System.out.println("\n3. Eliminando 'Valeria':");
        diccionario.remove("Valeria");
        System.out.println("Diccionario actualizado: " + diccionario);

        System.out.println("\n4. Caso con null:");
        System.out.println("Valor para null: " + diccionario.get(null));

        System.out.println("\n5. Limpiando el diccionario:");
        diccionario.clear();
        System.out.println("Tamaño después de clear(): " + diccionario.size());
    }
}