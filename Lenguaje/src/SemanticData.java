import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class SemanticData {
    
    public Stack<String> operands = new Stack<>();
    public Stack<String> operators = new Stack<>();
    public Stack<String> types = new Stack<>();
    public List<GeneradorCuadruplosVisitor> quadruples = new ArrayList<>();
    public int tempCounter = 0;

    public String getNewTemp() {
        return "t" + (tempCounter++);
    }
    
    public static class Variable {
        public String nombre;
        public String tipo;
        // Puedes agregar más atributos como scope, dirección, etc.

        public Variable(String nombre, String tipo) {
            this.nombre = nombre;
            this.tipo = tipo;
        }
    }

    public static class Funcion {
        public String nombre;
        public String tipoRetorno;
        public List<Variable> parametros;
        public Map<String, Variable> tablaVariablesLocal;
        // Puedes agregar más atributos como dirección de inicio, etc.

        public Funcion(String nombre, String tipoRetorno) {
            this.nombre = nombre;
            this.tipoRetorno = tipoRetorno;
            this.parametros = new ArrayList<>();
            this.tablaVariablesLocal = new HashMap<>();
        }
    }

    public static Map<String, Funcion> directorioFunciones = new HashMap<>();
    public static Map<String, Variable> tablaVariablesGlobal = new HashMap<>();
    public static Stack<Map<String, Variable>> pilaTablasVariables = new Stack<>();

    static {
        pilaTablasVariables.push(tablaVariablesGlobal);
    }

    public static Map<String, Map<String, Map<String, String>>> cuboSemantico = new HashMap<>();

    static {
        // Inicialización del cubo semántico
        Map<String, Map<String, String>> intOps = new HashMap<>();
        intOps.put("+", Map.of("int", "int", "float", "float"));
        intOps.put("-", Map.of("int", "int", "float", "float"));
        intOps.put("*", Map.of("int", "int", "float", "float"));
        intOps.put("/", Map.of("int", "float", "float", "float"));
        intOps.put("==", Map.of("int", "bool", "float", "bool"));
        intOps.put("!=", Map.of("int", "bool", "float", "bool"));
        intOps.put(">", Map.of("int", "bool", "float", "bool"));
        intOps.put("<", Map.of("int", "bool", "float", "bool"));
        intOps.put("=", Map.of("int", "int", "float", "float"));
        cuboSemantico.put("int", intOps);

        Map<String, Map<String, String>> floatOps = new HashMap<>();
        floatOps.put("+", Map.of("int", "float", "float", "float"));
        floatOps.put("-", Map.of("int", "float", "float", "float"));
        floatOps.put("*", Map.of("int", "float", "float", "float"));
        floatOps.put("/", Map.of("int", "float", "float", "float"));
        floatOps.put("==", Map.of("int", "bool", "float", "bool"));
        floatOps.put("!=", Map.of("int", "bool", "float", "bool"));
        floatOps.put(">", Map.of("int", "bool", "float", "bool"));
        floatOps.put("<", Map.of("int", "bool", "float", "bool"));
        floatOps.put("=", Map.of("float", "float"));
        cuboSemantico.put("float", floatOps);

        // Agregar operaciones para el tipo str
        Map<String, Map<String, String>> strOps = new HashMap<>();
        strOps.put("+", Map.of("str", "str"));
        strOps.put("==", Map.of("str", "bool"));
        strOps.put("!=", Map.of("str", "bool"));
        strOps.put("=", Map.of("str", "str"));
        cuboSemantico.put("str", strOps);
    }

    public static final List<String> tiposValidos = List.of("int", "float", "str");
}