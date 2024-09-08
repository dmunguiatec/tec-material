package org.expreval.parser

import org.expreval.expression.Expression

interface ExpressionParser {
    fun parse(input: String): Expression
}
