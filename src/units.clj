(ns units)

(def simple-metric {:m 1,
                    :km 1000,
                    :cm 1/100,
                    :mm [1/10 :cm]})

(->
 (* 3 (:km simple-metric))
 (+ (* 10 (:m simple-metric)))
 (+ (* 80 (:cm simple-metric)))
 (+ (* (:cm simple-metric)
       (* 10 (first (:mm simple-metric)))))
 float)

(defn convert [context descriptor]
  (reduce (fn [result [mag unit]]
            (+ result
               (let [val (get context unit)]
                 (if (vector? val)
                   (* mag (convert context val))
                   (* mag val)))))
          0
          (partition 2 descriptor)))

(convert simple-metric [1 :km 10 :m 5 :cm])

(partition 2 [1 :km 10 :m 5 :cm])

(defn gcd [x y]
  (cond
   (> x y) (recur (- x y) y) ; replacing calls direct to gcd which would StackOverflowError
   (< x y) (recur x (- y x))
   :else x))

(gcd 57875435345345345 4324375435354)
