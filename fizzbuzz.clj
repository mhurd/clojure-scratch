(ns fizzbuzz)

(defn fizzbuzz [n]
  (let [s (take n (iterate inc 1))
        fizz [1 0 0]
        buzz [2 0 0 0 0]]
        (map #(let [labels [% "fizz" "buzz" "fizzbuzz"]]
                 (str (labels (+ (fizz (mod % 3)) (buzz (mod % 5)))))) s))
  )

(fizzbuzz 55)
