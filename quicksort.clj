(ns quicksort)

(defn rand-ints [n]
  (take n (repeatedly #(rand-int n))))

(rand-ints 100)

(defn sort-parts [work]
  (lazy-seq
   (loop [[part & parts] work]
     (if-let [[pivot & xs] (seq part)]
       (let [smaller? #(< % pivot)]
         (recur (list*
                 (filter smaller? xs)
                 pivot
                 (remove smaller? xs)
                 parts)))
      (when-let [[x & parts] parts]
       (cons x (sort-parts parts)))))))

(defn qsort [xs]
  (sort-parts (list xs)))

(qsort [1 2 3 5 4 5 3 8 5 3 2 5 23 3 2])

(if-let [a false]
  (println (str "in if-let, a is " a)) ;; if test is true, bind 'a' and execute this
  (println "Not in if-let")) ;; else execute this

(when-let [a false]
  (println (str "in when-let, a is " a))) ;; if test is true then bind 'a' and execute this
