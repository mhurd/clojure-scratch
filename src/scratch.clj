(ns scratch)

(defn f-values [f max-x max-y]
  (for
    [x (range max-x) y (range max-y)]
    [x y (rem (f x y) 256)]))

(def frame (java.awt.Frame.))
(.setVisible frame true)

(def gfx (.getGraphics frame))
(defn clear [g] (.clearRect g 0 0 500 500))

(defn draw-values [f xs ys]
  (clear gfx)
  (.setSize frame (java.awt.Dimension. xs ys))
  (doseq [[x y v] (f-values f xs ys)]
    (.setColor gfx (java.awt.Color. v v v))
    (.fillRect gfx x y 1 1)))

(draw-values bit-and 512 512)
(draw-values bit-xor 512 512)
(draw-values bit-or 512 512)
(draw-values + 512 512)
(draw-values * 512 512)

(def a-to-z (vec (map char (range 65 91))))

(seq a-to-z)

(defn neighbors
  ([size yx] (neighbors [[-1 0] [1 0] [0 -1] [0 1]]
                        size
                        yx))
  ([deltas size yx]
   (filter (fn [new-yx]
             (every? #(< -1 % size) new-yx))
           (map #(vec (map + yx %))
                deltas))))

(neighbors 3 [1 1])

(defmethod print-method clojure.lang.PersistentQueue
  [q, w]
  (print-method '<- w)
  (print-method (seq q) w)
  (print-method '-< w))



(println clojure.lang.PersistentQueue/EMPTY)

(def schedule (conj clojure.lang.PersistentQueue/EMPTY :wake-up :shower :brush-teeth))

(println schedule)
(def new-schedule (pop schedule))
(println new-schedule)
