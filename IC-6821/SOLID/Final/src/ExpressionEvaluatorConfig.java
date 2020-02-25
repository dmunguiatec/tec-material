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
import expr.parser.ExpressionParser;
import expr.parser.RegexExpressionParser;
import ui.CLI;
import ui.ConsoleCLI;

public class ExpressionEvaluatorConfig {
    private ExpressionCreator expressionCreator() {
        ExpressionCreator creator = FactoryMethodExpressionCreator.getInstance();

        creator.registerIntOperator("+", new AddExprFactoryMethod());
        creator.registerIntOperator("-", new SubExprFactoryMethod());
        creator.registerIntOperator("*", new MulExprFactoryMethod());
        creator.registerIntOperator("/", new QuotExprFactoryMethod());

        creator.registerFPOperator("+", new FPAddExprFactoryMethod());
        creator.registerFPOperator("-", new FPSubExprFactoryMethod());
        creator.registerFPOperator("*", new FPMulExprFactoryMethod());
        creator.registerFPOperator("/", new FPDivExprFactoryMethod());

        return creator;
    }

    private ExpressionParser expressionParser() {
        return new RegexExpressionParser(expressionCreator());
    }

    public CLI cli() {
        return new ConsoleCLI(expressionParser());
    }
}
