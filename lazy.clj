(ns lazy)

(defn triangle [n]
  (/ (* n (+ n 1)) 2))

(triangle 10)

(def tri-nums (map triangle (iterate inc 1)))

(take 10 (filter even? tri-nums))

(nth tri-nums 99)

(double (reduce + (take 100000 (map / tri-nums))))

(take 2 (drop-while #(< % 100000000000000000) tri-nums))

(defn inf-triangles [n]
  {:head (triangle n)
   :tail (delay (inf-triangles (inc n)))})

(defn head [l] (:head l))

(defn tail [l] (force (:tail l)))

(def tri-nums (inf-triangles 1))

(head (tail (tail (tail (tail (tail tri-nums))))))

(defn taker [n l]
  (loop [t n, src l, ret []]
    (if (zero? t)
      ret
      (recur (dec t) (tail src) (conj ret (head src))))))

(taker 100 (inf-triangles 1))
