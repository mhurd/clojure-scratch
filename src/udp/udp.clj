(ns udp)

(defn rget [map key]
  (when map
    (if-let [[_ value] (find map key)]
      value
      (recur (::prototype map) key))))

(rget {:a 1 :b 2 ::prototype {:c 3 :d 4}} :a)

