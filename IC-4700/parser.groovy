def fact = '(define (fact n) (if (= n 0) 1 (* n (fact (- n 1)))))'

def parseExpression(String expr) {
    def stack = []
    def current = null
    def tokens = expr.replace('(', '( ').replace(')', ' )').split(' ')
    tokens.each { token ->
        switch(token) {
            case '(':
                stack.push(current)
                current = []
                break
            case ')':
                def top = stack.pop()
                current = top ? top << current : current
                break
            default:
                current << token
        }
    }
    
    current
}

println(parseExpression(fact))
println(parseExpression('(if (not (= 1 2)) (quotient 3 2) 5)'))