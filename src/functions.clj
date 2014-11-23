(ns functions)

(defn join
  {:test (fn []
           (assert (= (join "," [1 2 3]) "1,2,3")))}
  [sep s]
  (apply str (interpose sep s)))

(use '[clojure.test :as t])
(t/run-tests)

(def plays [{:band "Burial", :plays 10, :loved 1}
            {:band "Eno", :plays 10, :loved 5}
            {:band "Bill Evans", :plays 10, :loved 2}])

(def sort-by-loved-ratio (partial sort-by #(/(:loved %) (:plays %) ) >))

(sort-by-loved-ratio plays)
