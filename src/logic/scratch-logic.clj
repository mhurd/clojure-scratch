(ns scratch-logic)
(use 'clojure.core.logic)


;; try and unify (==) the unbound logic variable (q) with true. Answer is 'true'
(run* [q] (== q true))

;; try and unify (==) the unbound logic variable (q) with 1 & 2. This is not possible hence empty set.
(run* [q] (== q 1) (== q 2))

;; find 'q's that are members of the supplied set.
(run* [q] (membero q [1 2 3]))

;; only find 2 results - handy if there are an infinate number of solutions!
(run 2 [q] (membero q [1 2 3]))

;; i.e. the set of 'q's that have [1 2 3] as a member is infinite...
(run 2 [q] (membero [1 2 3] q))
;; the result: (([1 2 3] . _0) (_0 [1 2 3] . _1))
;; . is the list construction operator, _0 is an unbound logic operator, i.e the tail could be anything

(use 'clojure.core.logic.pldb)

;; define two relations 'man' and 'woman'
;; the 'o' suffix indicates a relation (from Lisp)
(db-rel mano x)
(db-rel womano x)

;; create a db of relations using the men and women relations defined earlier
(def facts
  (db
   [mano :alan-turing]
   [womano :grace-hopper]
   [mano :leslie-lamport]
   [mano :alonzo-church]
   [womano :ada-lovelace]
   [womano :barbara-liskov]
   [womano :frances-allen]
   [mano :john-mccarthy]))

;; find all the women
(with-db facts (run* [q] (womano q)))
;; find all the men
(with-db facts (run* [q] (mano q)))

;; define two additional relations 'vital' and 'turing'
(db-rel vitalo x)
(db-rel turingo x)

;; define some more facts
(def facts
 (-> facts
 (db-fact vitalo :alan-turing :dead)
 (db-fact vitalo :grace-hopper :dead)
 (db-fact vitalo :leslie-lamport :alive)
 (db-fact vitalo :alonzo-church :dead)
 (db-fact vitalo :ada-lovelace :dead)
 (db-fact vitalo :barbara-liskov :alive)
 (db-fact vitalo :frances-allen :alive)
 (db-fact vitalo :john-mccarthy :dead)
 (db-fact turingo :leslie-lamport :2013)
 (db-fact turingo :barbara-liskov :2008)
 (db-fact turingo :frances-allen :2006)
 (db-fact turingo :john-mccarthy :1971)))

;; more interesting questions:
;; i.e. find the women who are still alive
(with-db facts
 (run* [q]
 (womano q)
 (vitalo q :alive)))

;; getting more complicated, we can declare additional unbound logic variables using 'fresh'
;; i.e. find the dead people who have a turing award and bind the person and the year to 'q'
(with-db facts
  (run* [q]
        (fresh [p y]
               (vitalo p :dead)
               (turingo p y)
               (== q [p y]))))

;; 'conde' runs the quiries in parallel and returns the union of the results.
(run* [q]
      (conde
       [(== q 1)] ;; returns 1
       [(== q 2) (== q 3)] ;; returns () as there is no match
       [(== q :abc)])) ;; returns :abc

;; conso acts like cons in Lisp
;; (conso head tail list)
(run* [q] (conso :a [:b :c] q))

;; extracts the tail from the list
(run* [q] (conso :a q [:a :b :c]))

;; implement membero using conso
(defn insideo [e l]
  (conde
   [(fresh [h t]
           (conso h t l)
           (== h e))]
   [(fresh [h t]
           (conso h t l)
           (insideo e t))]))

(run* [q] (insideo q [:a :b :c]))

;; only value in both sets is 2
(run* [q] (membero q [1 2 3]) (membero q [5 2 6]))








