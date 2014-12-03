(ns udp
  (:refer-clojure :exclude [get]))

(defn beget [this prototype]
  (assoc this ::prototype prototype))

(defn get [map key]
  (when map
    (if-let [[_ value] (find map key)]
      value
      (recur (::prototype map) key))))

(get (beget {:a 1 :b 2} {:c 3 :d 4}) :c)

(def put assoc)

(defmulti compiler :os)

(defmethod compiler ::unix [m] (get m :c-compiler))

(defmethod compiler ::osx [m] (get m :llvm-compiler))

(def clone (partial beget {}))

(def unix {:os ::unix, :c-compiler "cc", :home "/home", :dev "/dev"})

(def osx (-> (clone unix)
             (put :os ::osx)
             (put :llvm-compiler "clang")
                  (put :home "/Users")))

(compiler unix)

(compiler osx)








