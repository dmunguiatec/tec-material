package edu.cexpr;


import edu.cexpr.codegen.AsmCodeGenerator;
import edu.cexpr.parser.ExprLexer;
import edu.cexpr.parser.ExprParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileWriter;
import java.io.IOException;

public class Compiler {

    public static final String EXT_EXPR = ".expr";
    public static final String EXT_ASM = ".asm";

    public static void main(String[] args) {

        if (!validate(args)) {
            return;
        }

        String sourceFilename = args[0];

        try {
            // 1. Análisis léxico
            CharStream charStream = CharStreams.fromFileName(sourceFilename);
            ExprLexer lexer = new ExprLexer(charStream);

            // 2. Análisis sintáctico
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            ExprParser parser = new ExprParser(tokenStream);

            // 3. Generar código
            ParseTree ast = parser.stat();
            AsmCodeGenerator generator = new AsmCodeGenerator();
            String output = generator.visit(ast);

            saveOutput(sourceFilename, output);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void saveOutput(String sourceFilename, String output) {
        String outputFilename = sourceFilename.replace(EXT_EXPR, EXT_ASM);
        try (FileWriter writer = new FileWriter(outputFilename)) {
            writer.write(output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean validate(String[] args) {
        if (args.length != 1) {
            usage();
            return false;
        }

        if (!args[0].endsWith(EXT_EXPR)) {
            System.out.println("Source filename must have .expr extension");
            return false;
        }

        return true;
    }

    private static void usage() {
        System.out.println("Usage: cexpr <source filename>");
    }
}
