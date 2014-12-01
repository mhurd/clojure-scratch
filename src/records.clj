(ns records)

;; operating system record
(defrecord OS [name])

;; dispatch method
(defn dispatch [os] (:name os))

;; the multi-method
(defmulti cool dispatch)

(dispatch (OS. ::linux))

;; implementation for unix
(defmethod cool ::unix [m]
  (do
    (println "UNIX is cool!")
    true))

;; implementation for bsd
(defmethod cool ::bsd [m]
  (do
    (println "BSD is coolish!")
    true))

;; implementation for windows
(defmethod cool ::windows [m]
  (do
    (println "Windows is not cool!")
    false))

(def windows (OS. ::windows))
(def unix (OS. ::unix))
(def linux (OS. ::linux))
(def bsd (OS. ::bsd))
(def osx (OS. ::osx))

(cool windows)

;; linux extends unix
(derive ::linux ::unix)

;; osx extends unix
(derive ::osx ::unix)

;; osx also extends bsd
(derive ::osx ::bsd)

(isa? ::linux ::unix)

;; resolve multiple inheritance issue
(prefer-method cool ::unix ::bsd)
