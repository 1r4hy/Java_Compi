import org.antlr.v4.runtime.tree.TerminalNode;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.ArrayList;

public class SemanticVisitor extends LenguajeBaseVisitor<String> {

    public static class SemanticError extends RuntimeException {
        public SemanticError(String message) {
            super(message);
        }
    }

    private Stack<Map<String, SemanticData.Variable>> pilaTablasVariables = new Stack<>();
    private Map<String, SemanticData.Funcion> directorioFunciones = SemanticData.directorioFunciones;
    private Map<String, Map<String, Map<String, String>>> cuboSemantico = SemanticData.cuboSemantico;
    private List<String> tiposValidos = SemanticData.tiposValidos;

    private String obtenerTipoOperacion(String tipoIzq, String operador, String tipoDer) {
        if (cuboSemantico.containsKey(tipoIzq) && cuboSemantico.get(tipoIzq).containsKey(operador) && cuboSemantico.get(tipoIzq).get(operador).containsKey(tipoDer)) {
            return cuboSemantico.get(tipoIzq).get(operador).get(tipoDer);
        } else {
            return null; // La operación no está definida en el cubo semántico
        }
    }

    private boolean esTipoCompatible(String tipoEsperado, String tipoRecibido) {
        return tipoEsperado.equals(tipoRecibido) || (tipoEsperado.equals("float") && tipoRecibido.equals("int"));
    }

    private SemanticData.Variable buscarVariable(String nombre) {
        for (int i = pilaTablasVariables.size() - 1; i >= 0; i--) {
            Map<String, SemanticData.Variable> tabla = pilaTablasVariables.get(i);
            if (tabla.containsKey(nombre)) {
                return tabla.get(nombre);
            }
        }
        return null; // La variable no se encontró en ningún ámbito
    }

    private String obtenerUltimaFuncionDefinida() {
        List<String> nombresFunciones = new ArrayList<>(directorioFunciones.keySet());
        if (!nombresFunciones.isEmpty()) {
            return nombresFunciones.get(nombresFunciones.size() - 1);
        }
        return null; // No se ha definido ninguna función aún
    }

    private List<String> obtenerTiposExpresionListOpt(LenguajeParser.Expresion_list_optContext ctx) {
        if (ctx.expresion_list() != null) {
            return obtenerTiposExpresionList(ctx.expresion_list());
        }
        return new ArrayList<>();
    }

    private List<String> obtenerTiposExpresionList(LenguajeParser.Expresion_listContext ctx) {
        List<String> tipos = new ArrayList<>();
        for (LenguajeParser.ExpresionContext exp : ctx.expresion()) {
            String tipoExp = visit(exp);
            if (tipoExp != null) {
                tipos.add(tipoExp);
            }
        }
        return tipos;
    }

    @Override
    public String visitProgram(LenguajeParser.ProgramContext ctx) {
        pilaTablasVariables.push(new java.util.HashMap<>()); // Tabla global
        visit(ctx.vars_opt());
        visit(ctx.funcs_p());
        visit(ctx.MAIN());
        visit(ctx.body());
        pilaTablasVariables.pop(); // Salir del ámbito global
        return null;
    }

    @Override
    public String visitVars_opt(LenguajeParser.Vars_optContext ctx) {
        if (ctx.vars() != null) {
            for (LenguajeParser.VarsContext varsContext : ctx.vars()) {
                visit(varsContext); // Visitar cada nodo 'vars' encontrado
            }
        }
        return null;
    }

    @Override
    public String visitFuncs_p(LenguajeParser.Funcs_pContext ctx) {
        if (ctx.funcs() != null) {
            visit(ctx.funcs());
            visit(ctx.funcs_p()); // Procesar más declaraciones de funciones
        }
        return null;
    }

    @Override
    public String visitVars(LenguajeParser.VarsContext ctx) {
        String tipo = ctx.type().getText();
        if (!tiposValidos.contains(tipo)) {
            throw new SemanticError("Tipo de variable inválido: " + tipo + " en la línea " + ctx.getStart().getLine());
        }
        for (TerminalNode idNode : ctx.id_list().ID()) {
            String nombreVariable = idNode.getText();
            Map<String, SemanticData.Variable> tablaActual = pilaTablasVariables.peek();
            if (tablaActual.containsKey(nombreVariable)) {
                throw new SemanticError("Variable doblemente declarada: " + nombreVariable + " en la línea " + ctx.getStart().getLine());
            }
            tablaActual.put(nombreVariable, new SemanticData.Variable(nombreVariable, tipo));
        }
        return null;
    }

    @Override
    public String visitType(LenguajeParser.TypeContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitFuncs(LenguajeParser.FuncsContext ctx) {
        String tipoRetorno = ctx.VOID() != null ? ctx.VOID().getText() : null;
        String nombreFuncion = ctx.ID().getText();

        if (directorioFunciones.containsKey(nombreFuncion)) {
            throw new SemanticError("Función doblemente declarada: " + nombreFuncion + " en la línea " + ctx.getStart().getLine());
        }

        SemanticData.Funcion funcion = new SemanticData.Funcion(nombreFuncion, tipoRetorno);
        directorioFunciones.put(nombreFuncion, funcion);
        pilaTablasVariables.push(new java.util.HashMap<>()); // Nuevo ámbito para la función
        visit(ctx.atributo());
        visit(ctx.vars_opt()); // Variables locales de la función
        visit(ctx.body());
        pilaTablasVariables.pop(); // Salir del ámbito de la función
        return null;
    }

    @Override
    public String visitAtributo(LenguajeParser.AtributoContext ctx) {
        if (ctx.ID() != null) {
            String nombreParametro = ctx.ID().getText();
            String tipoParametro = ctx.type().getText();
            if (!tiposValidos.contains(tipoParametro)) {
                throw new SemanticError("Tipo de parámetro inválido: " + tipoParametro + " en la línea " + ctx.getStart().getLine());
            }
            Map<String, SemanticData.Variable> tablaLocal = pilaTablasVariables.peek();
            if (tablaLocal.containsKey(nombreParametro)) {
                throw new SemanticError("Parámetro doblemente declarado: " + nombreParametro + " en la línea " + ctx.getStart().getLine());
            }
            tablaLocal.put(nombreParametro, new SemanticData.Variable(nombreParametro, tipoParametro));
            SemanticData.Funcion funcionActual = directorioFunciones.get(obtenerUltimaFuncionDefinida());
            if (funcionActual != null) {
                funcionActual.parametros.add(new SemanticData.Variable(nombreParametro, tipoParametro));
            }
            visit(ctx.atr_opt()); // Procesar más atributos
        }
        return null;
    }

    @Override
    public String visitAtr_opt(LenguajeParser.Atr_optContext ctx) {
        if (ctx.COMMA() != null) {
            visit(ctx.atributo());
        }
        return null;
    }

    @Override
    public String visitBody(LenguajeParser.BodyContext ctx) {
        return visit(ctx.statement_list());
    }

    @Override
    public String visitStatement_list(LenguajeParser.Statement_listContext ctx) {
        if (ctx.statement() != null) {
            for (LenguajeParser.StatementContext statementContext : ctx.statement()) {
                visit(statementContext); // Visitar cada nodo 'statement' encontrado
            }
        }
        return null;
    }

    @Override
    public String visitStatement(LenguajeParser.StatementContext ctx) {
        if (ctx.assing() != null) return visit(ctx.assing());
        if (ctx.condition() != null) return visit(ctx.condition());
        if (ctx.cycle() != null) return visit(ctx.cycle());
        if (ctx.f_call() != null) return visit(ctx.f_call());
        if (ctx.print() != null) return visit(ctx.print());
        return null;
    }

    @Override
    public String visitAssing(LenguajeParser.AssingContext ctx) {
        String nombreVariable = ctx.ID().getText();
        SemanticData.Variable variable = buscarVariable(nombreVariable);
        if (variable == null) {
            throw new SemanticError("Variable no declarada: " + nombreVariable + " en la línea " + ctx.getStart().getLine());
        }
        String tipoExpr = visit(ctx.expresion());
        if (tipoExpr != null && !esTipoCompatible(variable.tipo, tipoExpr)) {
            throw new SemanticError("Tipos incompatibles en la asignación: " + variable.tipo + " y " + tipoExpr + " en la línea " + ctx.getStart().getLine());
        }
        return null;
    }

    @Override
    public String visitCondition(LenguajeParser.ConditionContext ctx) {
        String tipoCondicion = visit(ctx.expresion());
        if (tipoCondicion != null && !tipoCondicion.equals("bool")) {
            throw new SemanticError("La condición debe ser de tipo booleano, pero se encontró: " + tipoCondicion + " en la línea " + ctx.getStart().getLine());
        }
        visit(ctx.body(0));
        if (ctx.ELSE() != null) {
            visit(ctx.body(1));
        }
        return null;
    }

    @Override
    public String visitCycle(LenguajeParser.CycleContext ctx) {
        String tipoCondicion = visit(ctx.expresion());
        if (tipoCondicion != null && !tipoCondicion.equals("bool")) {
            throw new SemanticError("La condición del ciclo debe ser de tipo booleano, pero se encontró: " + tipoCondicion + " en la línea " + ctx.getStart().getLine());
        }
        visit(ctx.body());
        return null;
    }

    @Override
    public String visitF_call(LenguajeParser.F_callContext ctx) {
        String nombreFuncion = ctx.ID().getText();
        SemanticData.Funcion funcion = directorioFunciones.get(nombreFuncion);
        if (funcion == null) {
            throw new SemanticError("Función no declarada: " + nombreFuncion + " en la línea " + ctx.getStart().getLine());
        }
        List<String> tiposArgumentos = obtenerTiposExpresionListOpt(ctx.expresion_list_opt());
        if (funcion.parametros.size() != tiposArgumentos.size()) {
            throw new SemanticError("Número incorrecto de argumentos en la llamada a " + nombreFuncion + " en la línea " + ctx.getStart().getLine());
        }
        for (int i = 0; i < funcion.parametros.size(); i++) {
            String tipoArg = tiposArgumentos.get(i);
            if (tipoArg != null && !esTipoCompatible(funcion.parametros.get(i).tipo, tipoArg)) {
                throw new SemanticError("Tipo de argumento incorrecto en la llamada a " + nombreFuncion + " en la línea " + ctx.getStart().getLine());
            }
        }
        return funcion.tipoRetorno;
    }

    @Override
    public String visitExpresion_list_opt(LenguajeParser.Expresion_list_optContext ctx) {
        if (ctx.expresion_list() != null) {
            return visit(ctx.expresion_list());
        }
        return null;
    }

    @Override
    public String visitExpresion_list(LenguajeParser.Expresion_listContext ctx) {
        List<String> tipos = new ArrayList<>();
        for (LenguajeParser.ExpresionContext exp : ctx.expresion()) {
            String tipoExp = visit(exp);
            if (tipoExp != null) {
                tipos.add(tipoExp);
            }
        }
        return null; // Aún no estamos devolviendo la lista de tipos aquí
    }

    @Override
    public String visitPrint(LenguajeParser.PrintContext ctx) {
        visit(ctx.expresion_list());
        return null;
    }

     @Override
    public String visitExpresion(LenguajeParser.ExpresionContext ctx) {
        return visit(ctx.andExpr(0));
    }

    @Override
    public String visitAndExpr(LenguajeParser.AndExprContext ctx) {
        String resultType = visit(ctx.comparacion(0)); // Type of the first comparacion

        for (int i = 1; i < ctx.comparacion().size(); i++) {
            String nextType = visit(ctx.comparacion(i));
            if (!resultType.equals("int") && !resultType.equals("float") && !resultType.equals("bool")) {
                throw new SemanticError("Invalid type for AND operation.");
            }
            if (!nextType.equals("int") && !nextType.equals("float") && !nextType.equals("bool")) {
                throw new SemanticError("Invalid type for AND operation.");
            }
            if (!resultType.equals(nextType)) {
                throw new SemanticError("Type mismatch in AND operation.");
            }
            resultType = "bool"; // Result of AND is always boolean
        }
        return resultType;
    }

    @Override
    public String visitComparacion(LenguajeParser.ComparacionContext ctx) {
        String tipoIzq = visit(ctx.aritmetica(0));
        if (ctx.getChildCount() > 1) {
            String operador = ctx.getChild(1).getText();
            String tipoDer = visit(ctx.aritmetica(1));
            String tipoResultado = obtenerTipoOperacion(tipoIzq, operador, tipoDer);
            if (tipoResultado == null || !tipoResultado.equals("bool")) {
                throw new SemanticError("Operación de comparación inválida entre tipos: " + tipoIzq + " " + operador + " " + tipoDer + " en la línea " + ctx.getStart().getLine());
            }
            return "bool";
        }
        return tipoIzq;
    }

    @Override
    public String visitAritmetica(LenguajeParser.AritmeticaContext ctx) {
        String tipo = visit(ctx.termino(0));
        for (int i = 1; i < ctx.getChildCount(); i += 2) {
            String operador = ctx.getChild(i).getText();
            // El siguiente operando (termino) estará en el siguiente índice par
            if (i + 1 < ctx.getChildCount()) {
                String tipoOp = visit(ctx.getChild(i + 1)); // Acceder al hijo directamente
                tipo = obtenerTipoOperacion(tipo, operador, tipoOp);
                if (tipo == null) {
                    throw new SemanticError("Operación aritmética inválida entre tipos: " + tipo + " " + operador + " " + tipoOp + " en la línea " + ctx.getStart().getLine());
                }
            } else {
                System.out.println("Advertencia: Operador aritmético sin operando derecho.");
            }
        }
        return tipo;
    }

    @Override
    public String visitTermino(LenguajeParser.TerminoContext ctx) {
        String tipo = visit(ctx.factor(0));
        for (int i = 1; i < ctx.getChildCount(); i += 2) {
            String operador = ctx.getChild(i).getText();
            // El siguiente operando (factor) estará en el siguiente índice par
            if (i + 1 < ctx.getChildCount()) {
                String tipoOp = visit(ctx.getChild(i + 1)); // Acceder al hijo directamente
                tipo = obtenerTipoOperacion(tipo, operador, tipoOp);
                if (tipo == null) {
                    throw new SemanticError("Operación de término inválida entre tipos: " + tipo + " " + operador + " " + tipoOp + " en la línea " + ctx.getStart().getLine());
                }
            } else {
                System.out.println("Advertencia: Operador de término sin operando derecho.");
            }
        }
        return tipo;
    }

    @Override
    public String visitFactor(LenguajeParser.FactorContext ctx) {
        if (ctx.LPAREN() != null) {
            return visit(ctx.expresion());
        } else if (ctx.ID() != null) {
            SemanticData.Variable variable = buscarVariable(ctx.ID().getText());
            if (variable == null) {
                throw new SemanticError("Variable no declarada: " + ctx.ID().getText() + " en la línea " + ctx.getStart().getLine());
            }
            return variable.tipo;
        } else if (ctx.cte() != null) {
            return visit(ctx.cte());
        } else if (ctx.getChildCount() == 2 && (ctx.getChild(0).getText().equals("+") || ctx.getChild(0).getText().equals("-"))) {
            // Operador unario
            return visit(ctx.getChild(1)); // Visitamos el ID o la cte directamente
        }
        return null;
    }

    @Override
    public String visitCte(LenguajeParser.CteContext ctx) {
        if (ctx.CTE_INT() != null) return "int";
        if (ctx.CTE_FLOAT() != null) return "float";
        if (ctx.CTE_STRING() != null) return "str";
        return null;
    }
}