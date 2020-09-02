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

public class ExpressionEvaluator {
    public static void main(String[] args) {
        ExpressionCreator expressionCreator = FactoryMethodExpressionCreator.getInstance();

        expressionCreator.registerIntOperator("+", new AddExprFactoryMethod());
        expressionCreator.registerIntOperator("-", new SubExprFactoryMethod());
        expressionCreator.registerIntOperator("*", new MulExprFactoryMethod());
        expressionCreator.registerIntOperator("/", new QuotExprFactoryMethod());

        expressionCreator.registerFPOperator("+", new FPAddExprFactoryMethod());
        expressionCreator.registerFPOperator("-", new FPSubExprFactoryMethod());
        expressionCreator.registerFPOperator("*", new FPMulExprFactoryMethod());
        expressionCreator.registerFPOperator("/", new FPDivExprFactoryMethod());

        CLI cli = new ConsoleCLI();
        cli.repl();
    }
}
