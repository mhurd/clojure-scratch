(ns macro)

(defmacro eval-log [expression] (do (let [result (eval expression)]
                             (println (str expression " = " result))
                             result
                         )))

(eval-log (+ 1 2 3 (/ 10 2)))

(defmacro foo [& body]
  (do
    (println body)
    (map #(println %) body)))

(foo (+ 12 3) (* 2 25) (/ 10 2))

(defmacro do-print [& body]
  (map #(let [expression %
              result (eval expression)]
          (println (str expression " = " result))) body))

(do-print (+ 12 3) (* 2 25) (/ 10 2))
