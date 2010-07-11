(ns net.intensivesystems.arrows
  (:use [clojure.contrib.macro-utils :only (with-symbol-macros)]))

(defmacro arrow
  [operations]
  `(let [~'a-select ::undefined
         ~'a-plus ::undefined
         ~'a-zero ::undefined
         ~'a-loop ::undefined
         ~'a-lift ::undefined
         ~'a-par ::undefined
         ~'a-all ::undefined
         ~@operations]
     {:a-arr ~'a-arr
      :a-seq ~'a-seq 
      :a-nth ~'a-nth

      :a-all ~'a-all
      :a-par ~'a-par

      :a-select ~'a-select

      :a-plus ~'a-plus
      :a-zero ~'a-zero
      :a-loop ~'a-loop

      :a-lift ~'a-lift
      }))

(defmacro defarrow
   ([arrow-name doc-string operations]
    (let [doc-name (with-meta arrow-name {:doc doc-string})]
      `(defarrow ~doc-name ~operations)))

   ([arrow-name operations]
    `(def ~arrow-name (arrow ~operations))))

(defmacro with-arrow
   [arrow & exprs]
   `(let [~'a-arr (:a-arr ~arrow)
		  ~'a-seq (:a-seq ~arrow)
		  ~'a-nth (:a-nth ~arrow)

		  ~'a-select (:a-select ~arrow)
		  ~'a-plus (:a-plus ~arrow)
		  ~'a-zero (:a-zero ~arrow)
		  ~'a-loop (:a-loop ~arrow)
		  ~'a-all (:a-all ~arrow)
		  ~'a-par (:a-par ~arrow)
		  ~'a-lift (:a-lift ~arrow)
		 ]
      (with-symbol-macros ~@exprs)))

