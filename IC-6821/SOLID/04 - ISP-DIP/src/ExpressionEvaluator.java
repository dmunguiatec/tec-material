import expr.creator.ExpressionCreator;
import expr.creator.FactoryMethodExpressionCreator;
import expr.creator.fpfactories.FPAddExprFactoryMethod;
import expr.creator.fpfactories.FPDivExprFactoryMethod;
import expr.creator.fpfactories.FPMulExprFactoryMethod;
import expr.creator.fpfactories.FPSubExprFactoryMethod;
import expr.creator.intfactories.AddExprFactoryMethod;
import expr.creator.intfactories.MulExprFactoryMethod;
import expr.creator.intfactories.QuotExprFactoryMethod;
import expr.creator.intfactories.SubExprFactoryMethod;
import ui.CLI;
import ui.ConsoleCLI;

import java.util.*;

public class ExpressionEvaluator {
    public static void main(String[] args) {
        ExpressionEvaluatorConfig config = new ExpressionEvaluatorConfig();

        CLI cli = config.cli();
        cli.repl();
    }
}
