package edu.cexpr.codegen;

import edu.cexpr.parser.ExprBaseVisitor;
import edu.cexpr.parser.ExprParser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AsmCodeGenerator extends ExprBaseVisitor<String> {

    public static final String TOKEN_LPAREN = "(";
    public static final String TOKEN_ADD = "+";
    public static final String TOKEN_MUL = "*";

    @Override
    public String visitStat(ExprParser.StatContext ctx) {

        try {
            String prologue = new String(
                    AsmCodeGenerator.class.getResourceAsStream("/prologue.asm").readAllBytes(),
                    StandardCharsets.UTF_8);
            String epilogue = new String(
                    AsmCodeGenerator.class.getResourceAsStream("/epilogue.asm").readAllBytes(),
                    StandardCharsets.UTF_8);

            return prologue +
                    visit(ctx.expr()) + "\n" +
                    epilogue;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String visitExpr(ExprParser.ExprContext ctx) {
        if (ctx.INT() != null) {
            return String.format("    mov eax, %s\n", ctx.INT().getSymbol().getText());
        }

        if (ctx.getChildCount() == 3) {
            if (ctx.getChild(0).getText().equals(TOKEN_LPAREN)) {
                return visit(ctx.expr(0));
            }

            StringBuilder builder = new StringBuilder();
            builder.append(visit(ctx.expr(0))); // izquierdo
            builder.append("    push eax\n");
            builder.append(visit(ctx.expr(1)));
            builder.append("    pop ebx\n");

            String operator = ctx.getChild(1).getText();
            if (operator.equals(TOKEN_ADD)) {
                builder.append("    add eax, ebx\n");
            } else if (operator.equals(TOKEN_MUL)) {
                builder.append("    imul eax, ebx\n");
            }

            return builder.toString();
        }

        return null;
    }
}
