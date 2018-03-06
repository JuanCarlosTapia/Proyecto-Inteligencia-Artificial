// Generated from RedBayesiana.txt by ANTLR 4.6
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RedBayesianaParser}.
 */
public interface RedBayesianaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RedBayesianaParser#descripcion}.
	 * @param ctx the parse tree
	 */
	void enterDescripcion(RedBayesianaParser.DescripcionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RedBayesianaParser#descripcion}.
	 * @param ctx the parse tree
	 */
	void exitDescripcion(RedBayesianaParser.DescripcionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RedBayesianaParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresion(RedBayesianaParser.ExpresionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RedBayesianaParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresion(RedBayesianaParser.ExpresionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RedBayesianaParser#logica}.
	 * @param ctx the parse tree
	 */
	void enterLogica(RedBayesianaParser.LogicaContext ctx);
	/**
	 * Exit a parse tree produced by {@link RedBayesianaParser#logica}.
	 * @param ctx the parse tree
	 */
	void exitLogica(RedBayesianaParser.LogicaContext ctx);
	/**
	 * Enter a parse tree produced by {@link RedBayesianaParser#evento}.
	 * @param ctx the parse tree
	 */
	void enterEvento(RedBayesianaParser.EventoContext ctx);
	/**
	 * Exit a parse tree produced by {@link RedBayesianaParser#evento}.
	 * @param ctx the parse tree
	 */
	void exitEvento(RedBayesianaParser.EventoContext ctx);
	/**
	 * Enter a parse tree produced by {@link RedBayesianaParser#parametro}.
	 * @param ctx the parse tree
	 */
	void enterParametro(RedBayesianaParser.ParametroContext ctx);
	/**
	 * Exit a parse tree produced by {@link RedBayesianaParser#parametro}.
	 * @param ctx the parse tree
	 */
	void exitParametro(RedBayesianaParser.ParametroContext ctx);
}