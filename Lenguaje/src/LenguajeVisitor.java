// Generated from Lenguaje.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LenguajeParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LenguajeVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LenguajeParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(LenguajeParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link LenguajeParser#vars_opt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVars_opt(LenguajeParser.Vars_optContext ctx);
	/**
	 * Visit a parse tree produced by {@link LenguajeParser#funcs_p}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncs_p(LenguajeParser.Funcs_pContext ctx);
	/**
	 * Visit a parse tree produced by {@link LenguajeParser#vars}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVars(LenguajeParser.VarsContext ctx);
	/**
	 * Visit a parse tree produced by {@link LenguajeParser#id_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId_list(LenguajeParser.Id_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link LenguajeParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(LenguajeParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link LenguajeParser#funcs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncs(LenguajeParser.FuncsContext ctx);
	/**
	 * Visit a parse tree produced by {@link LenguajeParser#atributo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtributo(LenguajeParser.AtributoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LenguajeParser#atr_opt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtr_opt(LenguajeParser.Atr_optContext ctx);
	/**
	 * Visit a parse tree produced by {@link LenguajeParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(LenguajeParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link LenguajeParser#statement_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement_list(LenguajeParser.Statement_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link LenguajeParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(LenguajeParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LenguajeParser#assing}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssing(LenguajeParser.AssingContext ctx);
	/**
	 * Visit a parse tree produced by {@link LenguajeParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(LenguajeParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LenguajeParser#cycle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCycle(LenguajeParser.CycleContext ctx);
	/**
	 * Visit a parse tree produced by {@link LenguajeParser#f_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitF_call(LenguajeParser.F_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link LenguajeParser#expresion_list_opt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresion_list_opt(LenguajeParser.Expresion_list_optContext ctx);
	/**
	 * Visit a parse tree produced by {@link LenguajeParser#expresion_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresion_list(LenguajeParser.Expresion_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link LenguajeParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(LenguajeParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link LenguajeParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresion(LenguajeParser.ExpresionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LenguajeParser#andExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(LenguajeParser.AndExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LenguajeParser#comparacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparacion(LenguajeParser.ComparacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LenguajeParser#aritmetica}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAritmetica(LenguajeParser.AritmeticaContext ctx);
	/**
	 * Visit a parse tree produced by {@link LenguajeParser#termino}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermino(LenguajeParser.TerminoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LenguajeParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(LenguajeParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link LenguajeParser#cte}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCte(LenguajeParser.CteContext ctx);
}