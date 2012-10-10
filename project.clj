(defproject i18n/i18n "1.0.3"
  :min-lein-version "2.0.0"
  :profiles {:dev
             {:dependencies [[com.stuartsierra/lazytest "1.1.2"]]}}
  :dependencies [[org.clojure/clojure "1.4.0"]]
  :repositories {"stuartsierra-releases"
                 "http://stuartsierra.com/maven2"}
  :description "Internationalization for Clojure")
