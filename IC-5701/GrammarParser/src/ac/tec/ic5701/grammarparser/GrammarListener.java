// Generated from /home/diegomunguia/Dropbox/TEC/II-2014/IC-5701 Compiladores e Intérpretes/GeneradoresParser/Grammar.g4 by ANTLR 4.4.1-dev
package ac.tec.ic5701.grammarparser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarParser}.
 */
public interface GrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarParser#rightHand}.
	 * @param ctx the parse tree
	 */
	void enterRightHand(@NotNull GrammarParser.RightHandContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#rightHand}.
	 * @param ctx the parse tree
	 */
	void exitRightHand(@NotNull GrammarParser.RightHandContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#leftHand}.
	 * @param ctx the parse tree
	 */
	void enterLeftHand(@NotNull GrammarParser.LeftHandContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#leftHand}.
	 * @param ctx the parse tree
	 */
	void exitLeftHand(@NotNull GrammarParser.LeftHandContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#gram}.
	 * @param ctx the parse tree
	 */
	void enterGram(@NotNull GrammarParser.GramContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#gram}.
	 * @param ctx the parse tree
	 */
	void exitGram(@NotNull GrammarParser.GramContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#grule}.
	 * @param ctx the parse tree
	 */
	void enterGrule(@NotNull GrammarParser.GruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#grule}.
	 * @param ctx the parse tree
	 */
	void exitGrule(@NotNull GrammarParser.GruleContext ctx);
}