package edu.ic5701

interface Visitor<R> {
    fun visit(varDecl: VarDecl): R
    fun visit(sumExpr: SumExpr): R
    fun visit(literalInt: LiteralInt): R
}

interface Statement {
    fun <R> accept(visitor: Visitor<R>): R
}

interface Expression {
    fun <R> accept(visitor: Visitor<R>): R
}

data class VarDecl(
    val identifier: Token,
    val initExpression: Expression
) : Statement {
    override fun <R> accept(visitor: Visitor<R>): R {
        return visitor.visit(this)
    }
}

data class SumExpr(
    val left: Expression,
    val right: Expression
) : Expression {
    override fun <R> accept(visitor: Visitor<R>): R {
        return visitor.visit(this)
    }
}

data class LiteralInt(
    val token: Token,
    val value: Int
) : Expression {
    override fun <R> accept(visitor: Visitor<R>): R {
        return visitor.visit(this)
    }
}