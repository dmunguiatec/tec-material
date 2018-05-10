fact n = if n == 0 then 1 else n * fact (n - 1)

fact' :: Int -> Int
fact' n = product [1..n]

fact'' :: Integer -> Integer
fact'' n = product [1..n]

largo :: (Eq a) => [a] -> Int
largo l = if l == [] then 0 else 1 + largo (tail l)

suma :: Int -> Int -> Int
suma x y = x + y

suma' :: (Num a) => a -> a -> a
suma' x y = x + y

ref :: [a] -> Int -> a
ref lista i = if i == 0 then head lista else ref (tail lista) (i - 1)

pendiente :: (Double, Double) -> (Double, Double) -> Double
pendiente (x1, y1) (x2, y2) = (y2 - y1) / (x2 - x1)

max' :: (Ord a) => [a] -> a
max' [x] = x
max' (x:xs) = max x (max' xs)

foo :: (Num a) => [a] -> a
foo [] = 0
foo [x] = x
foo (x1:x2:xs) = (x1 - x2) + foo xs


largo' :: [a] -> Int
largo' [] = 0
largo' (_:xs) = 1 + largo' xs

sufijos :: [a] -> [[a]]
sufijos xs@(_:ys) = xs : sufijos ys
sufijos _ = []

max'' :: (Ord a) => [a] -> a
max'' [x] = x
max'' lst
   | x < maxxs = maxxs
   | x >= maxxs = x
   where (x:xs) = lst
         maxxs = max'' xs

max''' :: (Ord a) => [a] -> a
max''' [x] = x
max''' (x:xs) = 
   let maxxs = max''' xs
   in if x >= maxxs then x else maxxs

count1s :: [Int] -> Int
count1s [] = 0
count1s (1:xs) = 1 + count1s xs
count1s (_:xs) = count1s xs
