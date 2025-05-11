import java.util.*;

public class GeneradorCuadruplosVisitor extends LenguajeBaseVisitor<String> {

    // Stacks for operators, operands, and types
    private Stack<String> operatorStack = new Stack<>();
    private Stack<String> operandStack = new Stack<>();
    private Stack<String> typeStack = new Stack<>();

    // Queue to store the generated quadruples
    private Queue<Quadruple> quadrupleQueue = new LinkedList<>();

    // Temporary variable counter
    private int tempVarCounter = 0;

    // Symbol table (for variable types)
    private Map<String, SemanticData.Variable> symbolTable;

    // Quadruple class
    public static class Quadruple {
        String operator;
        String arg1;
        String arg2;
        String result;

        public Quadruple(String operator, String arg1, String arg2, String result) {
            this.operator = operator;
            this.arg1 = arg1;
            this.arg2 = arg2;
            this.result = result;
        }

        @Override
        public String toString() {
            return operator + " " + arg1 + " " + arg2 + " " + result;
        }
    }

    // Constructor that accepts the symbol table
    public GeneradorCuadruplosVisitor(Map<String, SemanticData.Variable> symbolTable) {
        this.symbolTable = symbolTable;
    }

    // Helper function to generate a temporary variable
    private String generateTempVar() {
        return "t" + tempVarCounter++;
    }

    // Helper function for type checking (simplified for this example)
    private String getType(String operand) {
        if (operand.matches("\\d+")) {
            return "int";
        } else if (operand.matches("\\d+\\.\\d+")) {
            return "float";
        } else if (symbolTable.containsKey(operand)) {
            return symbolTable.get(operand).tipo; // Access the 'type' field of SemanticData.Variable
        } else {
            return "unknown"; // Or throw an error, depending on your error handling
        }
    }

    //  ---  Visitor Methods  ---

    @Override
    public String visitAssing(LenguajeParser.AssingContext ctx) {
        String varName = ctx.ID().getText();
        visit(ctx.expresion()); // Process the expression
        String result = operandStack.pop();
        quadrupleQueue.add(new Quadruple("=", result, "", varName));
        return null;
    }

    @Override
    public String visitExpresion(LenguajeParser.ExpresionContext ctx) {
        visit(ctx.andExpr(0));

        for (int i = 1; i < ctx.andExpr().size(); i++) {
            operatorStack.push("||");
            visit(ctx.andExpr(i));
            generateQuadruple();
        }
        return null;
    }

    @Override
    public String visitAndExpr(LenguajeParser.AndExprContext ctx) {
        visit(ctx.comparacion(0));

        for (int i = 1; i < ctx.comparacion().size(); i++) {
            operatorStack.push("&&");
            visit(ctx.comparacion(i));
            generateQuadruple();
        }
        return null;
    }

    @Override
    public String visitComparacion(LenguajeParser.ComparacionContext ctx) {
        visit(ctx.aritmetica(0));

        if (ctx.getChildCount() > 1) {
            operatorStack.push(ctx.getChild(1).getText()); // Push the comparison operator
            visit(ctx.aritmetica(1));
            generateQuadruple();
        }
        return null;
    }

    @Override
    public String visitAritmetica(LenguajeParser.AritmeticaContext ctx) {
        visit(ctx.termino(0));

        for (int i = 1; i < ctx.termino().size(); i++) {
            operatorStack.push(ctx.getChild(i * 2 - 1).getText()); // Push + or -
            visit(ctx.termino(i));
            generateQuadruple();
        }
        return null;
    }

    @Override
    public String visitTermino(LenguajeParser.TerminoContext ctx) {
        visit(ctx.factor(0));

        for (int i = 1; i < ctx.factor().size(); i++) {
            operatorStack.push(ctx.getChild(i * 2 - 1).getText()); // Push * or /
            visit(ctx.factor(i));
            generateQuadruple();
        }
        return null;
    }

    @Override
public String visitFactor(LenguajeParser.FactorContext ctx) {
    if (ctx.LPAREN() != null) {
        return visitExpresion(ctx.expresion()); // Visit the expression inside parentheses
    } else if (ctx.ID() != null) {
        operandStack.push(ctx.ID().getText());
        typeStack.push(getType(ctx.ID().getText()));
        return ctx.ID().getText(); // Or return the type, depending on your needs
    } else if (ctx.cte() != null) {
        visitCte(ctx.cte());
        return operandStack.peek(); // Or return the type, depending on your needs
    } else if (ctx.PLUS() != null || ctx.MINUS() != null) {
        // Unary plus/minus:  Process the factor part
        if (ctx.ID() != null) {
            operandStack.push(ctx.ID().getText());
            typeStack.push(getType(ctx.ID().getText()));
            return ctx.ID().getText();
        } else if (ctx.cte() != null) {
            visitCte(ctx.cte());
            return operandStack.peek();
        }
    }
    return null;
}

    @Override
    public String visitCte(LenguajeParser.CteContext ctx) {
        if (ctx.CTE_INT() != null) {
            operandStack.push(ctx.CTE_INT().getText());
            typeStack.push("int");
        } else if (ctx.CTE_FLOAT() != null) {
            operandStack.push(ctx.CTE_FLOAT().getText());
            typeStack.push("float");
        } else if (ctx.CTE_STRING() != null) {
            operandStack.push(ctx.CTE_STRING().getText());
            typeStack.push("str");
        }
        return null;
    }

    // --- Quadruple Generation ---

    private void generateQuadruple() {
        if (!operatorStack.isEmpty()) {
            String operator = operatorStack.pop();
            String operand2 = operandStack.pop();
            String operand1 = operandStack.pop();
            typeStack.pop(); // Pop types (for more robust type checking, you'd use these)
            typeStack.pop();

            String tempVar = generateTempVar();
            quadrupleQueue.add(new Quadruple(operator, operand1, operand2, tempVar));
            operandStack.push(tempVar);
            typeStack.push("unknown"); // Simplified: Type of temp var is 'unknown' here
        }
    }

    // ---  Output Quadruples  ---

    public List<Quadruple> getQuadruples() {
        return new ArrayList<>(quadrupleQueue);
    }

    public void printQuadruples() {
        System.out.println("--- Quadruples ---");
        for (Quadruple quad : quadrupleQueue) {
            System.out.println(quad);
        }
    }
}