package edu.ic5701

class Traversal : Visitor<Unit> {

    fun traverse(statement: Statement) {
        statement.accept(this)
    }

    fun traverse(expression: Expression) {
        expression.accept(this)
    }

    override fun visit(varDecl: VarDecl) {
        traverse(varDecl.initExpression)
        println("Estoy en VarDecl")
    }

    override fun visit(sumExpr: SumExpr) {
        traverse(sumExpr.left)
        traverse(sumExpr.right)
        println("Estoy en SumExpr")
    }

    override fun visit(literalInt: LiteralInt) {
        println("Estoy en LiteralInt")
    }
}

fun main() {
    val varDecl = VarDecl(
        Token(TokenType.IDENT, lexeme = "hola", 0, 0),
        initExpression = SumExpr(
            LiteralInt(
                Token(TokenType.LITERAL_INT, lexeme = "1", 0, 0),
                value = 1
            ),
            LiteralInt(
                token = Token(TokenType.LITERAL_INT, lexeme = "5", 0, 0),
                value = 5
            )
        )
    )

    Traversal().traverse(varDecl)
}
