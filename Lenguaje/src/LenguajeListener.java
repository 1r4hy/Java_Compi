// Generated from Lenguaje.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LenguajeParser}.
 */
public interface LenguajeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LenguajeParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(LenguajeParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link LenguajeParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(LenguajeParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link LenguajeParser#vars_opt}.
	 * @param ctx the parse tree
	 */
	void enterVars_opt(LenguajeParser.Vars_optContext ctx);
	/**
	 * Exit a parse tree produced by {@link LenguajeParser#vars_opt}.
	 * @param ctx the parse tree
	 */
	void exitVars_opt(LenguajeParser.Vars_optContext ctx);
	/**
	 * Enter a parse tree produced by {@link LenguajeParser#funcs_p}.
	 * @param ctx the parse tree
	 */
	void enterFuncs_p(LenguajeParser.Funcs_pContext ctx);
	/**
	 * Exit a parse tree produced by {@link LenguajeParser#funcs_p}.
	 * @param ctx the parse tree
	 */
	void exitFuncs_p(LenguajeParser.Funcs_pContext ctx);
	/**
	 * Enter a parse tree produced by {@link LenguajeParser#vars}.
	 * @param ctx the parse tree
	 */
	void enterVars(LenguajeParser.VarsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LenguajeParser#vars}.
	 * @param ctx the parse tree
	 */
	void exitVars(LenguajeParser.VarsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LenguajeParser#id_list}.
	 * @param ctx the parse tree
	 */
	void enterId_list(LenguajeParser.Id_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link LenguajeParser#id_list}.
	 * @param ctx the parse tree
	 */
	void exitId_list(LenguajeParser.Id_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link LenguajeParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(LenguajeParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link LenguajeParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(LenguajeParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link LenguajeParser#funcs}.
	 * @param ctx the parse tree
	 */
	void enterFuncs(LenguajeParser.FuncsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LenguajeParser#funcs}.
	 * @param ctx the parse tree
	 */
	void exitFuncs(LenguajeParser.FuncsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LenguajeParser#atributo}.
	 * @param ctx the parse tree
	 */
	void enterAtributo(LenguajeParser.AtributoContext ctx);
	/**
	 * Exit a parse tree produced by {@link LenguajeParser#atributo}.
	 * @param ctx the parse tree
	 */
	void exitAtributo(LenguajeParser.AtributoContext ctx);
	/**
	 * Enter a parse tree produced by {@link LenguajeParser#atr_opt}.
	 * @param ctx the parse tree
	 */
	void enterAtr_opt(LenguajeParser.Atr_optContext ctx);
	/**
	 * Exit a parse tree produced by {@link LenguajeParser#atr_opt}.
	 * @param ctx the parse tree
	 */
	void exitAtr_opt(LenguajeParser.Atr_optContext ctx);
	/**
	 * Enter a parse tree produced by {@link LenguajeParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(LenguajeParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link LenguajeParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(LenguajeParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link LenguajeParser#statement_list}.
	 * @param ctx the parse tree
	 */
	void enterStatement_list(LenguajeParser.Statement_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link LenguajeParser#statement_list}.
	 * @param ctx the parse tree
	 */
	void exitStatement_list(LenguajeParser.Statement_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link LenguajeParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(LenguajeParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LenguajeParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(LenguajeParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LenguajeParser#assing}.
	 * @param ctx the parse tree
	 */
	void enterAssing(LenguajeParser.AssingContext ctx);
	/**
	 * Exit a parse tree produced by {@link LenguajeParser#assing}.
	 * @param ctx the parse tree
	 */
	void exitAssing(LenguajeParser.AssingContext ctx);
	/**
	 * Enter a parse tree produced by {@link LenguajeParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(LenguajeParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LenguajeParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(LenguajeParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LenguajeParser#cycle}.
	 * @param ctx the parse tree
	 */
	void enterCycle(LenguajeParser.CycleContext ctx);
	/**
	 * Exit a parse tree produced by {@link LenguajeParser#cycle}.
	 * @param ctx the parse tree
	 */
	void exitCycle(LenguajeParser.CycleContext ctx);
	/**
	 * Enter a parse tree produced by {@link LenguajeParser#f_call}.
	 * @param ctx the parse tree
	 */
	void enterF_call(LenguajeParser.F_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link LenguajeParser#f_call}.
	 * @param ctx the parse tree
	 */
	void exitF_call(LenguajeParser.F_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link LenguajeParser#expresion_list_opt}.
	 * @param ctx the parse tree
	 */
	void enterExpresion_list_opt(LenguajeParser.Expresion_list_optContext ctx);
	/**
	 * Exit a parse tree produced by {@link LenguajeParser#expresion_list_opt}.
	 * @param ctx the parse tree
	 */
	void exitExpresion_list_opt(LenguajeParser.Expresion_list_optContext ctx);
	/**
	 * Enter a parse tree produced by {@link LenguajeParser#expresion_list}.
	 * @param ctx the parse tree
	 */
	void enterExpresion_list(LenguajeParser.Expresion_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link LenguajeParser#expresion_list}.
	 * @param ctx the parse tree
	 */
	void exitExpresion_list(LenguajeParser.Expresion_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link LenguajeParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(LenguajeParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link LenguajeParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(LenguajeParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link LenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresion(LenguajeParser.ExpresionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresion(LenguajeParser.ExpresionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LenguajeParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(LenguajeParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LenguajeParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(LenguajeParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LenguajeParser#comparacion}.
	 * @param ctx the parse tree
	 */
	void enterComparacion(LenguajeParser.ComparacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LenguajeParser#comparacion}.
	 * @param ctx the parse tree
	 */
	void exitComparacion(LenguajeParser.ComparacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LenguajeParser#aritmetica}.
	 * @param ctx the parse tree
	 */
	void enterAritmetica(LenguajeParser.AritmeticaContext ctx);
	/**
	 * Exit a parse tree produced by {@link LenguajeParser#aritmetica}.
	 * @param ctx the parse tree
	 */
	void exitAritmetica(LenguajeParser.AritmeticaContext ctx);
	/**
	 * Enter a parse tree produced by {@link LenguajeParser#termino}.
	 * @param ctx the parse tree
	 */
	void enterTermino(LenguajeParser.TerminoContext ctx);
	/**
	 * Exit a parse tree produced by {@link LenguajeParser#termino}.
	 * @param ctx the parse tree
	 */
	void exitTermino(LenguajeParser.TerminoContext ctx);
	/**
	 * Enter a parse tree produced by {@link LenguajeParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(LenguajeParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link LenguajeParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(LenguajeParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link LenguajeParser#cte}.
	 * @param ctx the parse tree
	 */
	void enterCte(LenguajeParser.CteContext ctx);
	/**
	 * Exit a parse tree produced by {@link LenguajeParser#cte}.
	 * @param ctx the parse tree
	 */
	void exitCte(LenguajeParser.CteContext ctx);
}