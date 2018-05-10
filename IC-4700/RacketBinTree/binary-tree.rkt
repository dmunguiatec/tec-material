#lang racket

(define (get-value tree) (car tree))
(define (get-left tree) (cadr tree))
(define (get-right tree) (caddr tree))

;; árbol: '(valor hijo-izq hijo-der)
(define (add-bin-tree value tree)
  (cond [(empty? tree) (cons value '(() ()))]
        [(eq? value (get-value tree)) tree]
        [(< value (get-value tree))
         (list (get-value tree) 
               (add-bin-tree value (get-left tree))
               (get-right tree))]
        [else (list (get-value tree) 
                    (get-left tree)
                    (add-bin-tree value (get-right tree)))]))

(foldl add-bin-tree '() '(5 4 7 2 9 7 3 1 0))
'(5 (4 (2 (1 (0 () ()) ()) (3 () ())) ()) (7 () (9 () ())))
