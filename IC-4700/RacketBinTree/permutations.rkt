#lang racket
(define (! n)
  (letrec ([faux (lambda (n acum) 
                   (if (zero? n) acum
                       (faux (- n 1) (* acum n))))])
    (faux n 1)))

(define (perm n k)
  (/ (! n) (* (! (- n k)) (! k))))

