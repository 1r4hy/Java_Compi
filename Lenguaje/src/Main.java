import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String inputFile = "Lenguaje/src/ejemplos/testcase4.Lenguaje"; // Reemplaza con tu archivo de entrada

        CharStream input = CharStreams.fromFileName(inputFile);
        LenguajeLexer lexer = new LenguajeLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LenguajeParser parser = new LenguajeParser(tokens);
        ParseTree tree = parser.program(); // Suponiendo que 'program' es tu regla raíz

        SemanticVisitor semanticVisitor = new SemanticVisitor();
        try {
            semanticVisitor.visit(tree);
            System.out.println("Análisis semántico completado sin errores.");

            // Generación de cuádruplos
            GeneradorCuadruplosVisitor generador = new GeneradorCuadruplosVisitor(SemanticData.tablaVariablesGlobal);
            generador.visit(tree);

            System.out.println("Cuádruplos generados:");
            generador.printQuadruples(); // Usa el método del visitor para imprimir

        } catch (SemanticVisitor.SemanticError e) {
            System.err.println("Error semántico: " + e.getMessage());
        }
    }
}