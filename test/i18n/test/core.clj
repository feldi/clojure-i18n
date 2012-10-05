;;  Copyright (c) Peter Feldtmann All rights reserved.  The use and
;;  distribution terms for this software are covered by the Eclipse Public
;;  License 1.0 (http://opensource.org/licenses/eclipse-1.0.php), the same
;;  as clojure, which can be found in the file epl-v10.html at the root of 
;;  this distribution.  By using this software in any fashion, you are 
;;  agreeing to be bound by the terms of this license. You must not remove
;;  this notice, or any other, from this software.
;;
;;  i18n
;;
;;  Internationalization for Clojure
;;
;;  Peter Feldtmann
;;  Started September 1 2012
;;
;;  based on the projects:
;;   j18n     :author "Meikel Brandmeyer <m@kotka.de>"
;;   clji18n: :author "Sebastián Galkin"
;;
;; to run this tests on Windows: see and use "runlazytest.bat"

(ns 
  ^{:author "Peter Feldtmann"
    :doc "Lazytest test cases of project: Iternationalization for Clojure"}
  i18n.test.core
  (:import (java.util Calendar GregorianCalendar Date ResourceBundle))
  (:use i18n.core)
  (:use [lazytest.describe :only (describe it testing given)]))

(describe i18n
   (given [en-us (locale "en" "US")
           es-ar (locale "es" "AR")
          ]
      (testing "simple translation" 
          (it "gets english translation"
              (with-locale en-us
                (= "hi Rich" (i18n :i18n.test.example/hi "Rich")))) 
          (it "gets spanish translation"
              (with-locale es-ar
                (= "hola Rich" (i18n :i18n.test.example/hi "Rich")))))
     
      (testing "messages with parameters"
       (it "formats messages with 2 arguments in en"
          (with-locale en-us
            (= "Maria was born on 1/5/00" (i18n :i18n.test.example/the-girl-phrase
                                                  "Maria"
                                                  (.getTime (GregorianCalendar. 2000 Calendar/JANUARY 5))))))
       (it "formats messages with 2 arguments in es"
        (with-locale es-ar  
            (= "María nació el 05/01/00" (i18n :i18n.test.example/the-girl-phrase
                                                  "María"
                                                  (.getTime (GregorianCalendar. 2000 Calendar/JANUARY 5)))))))
      
      (testing "choice formatters"
       (testing "in english"
        (it "formats 0"
            (with-locale en-us 
              (= "no Clojure programmers" (i18n :i18n.test.example/plural 0))))
        (it "formats singular"
             (with-locale en-us 
               (= "a single Clojure programmer" (i18n :i18n.test.example/plural 1))))
        (it "formats plurals"
            (with-locale en-us 
              (= "2 Clojure programmers" (i18n :i18n.test.example/plural 2)))))
       (testing "in spanish"
         (it "formats 0"
             (with-locale es-ar
               (= "ningún programador Clojure" (i18n :i18n.test.example/plural 0))))
         (it "formats singular"
             (with-locale es-ar
               (= "solo un programador Clojure" (i18n :i18n.test.example/plural 1))))
         (it "formats plurals"
             (with-locale es-ar
               (= "2 programadores Clojure" (i18n :i18n.test.example/plural 2)))))
        )
      
      (testing "naming bundles" 
         (it "works with explicit named bundle and keyword as key"
             (with-locale en-us
               (= "hello Rich" (i18n (ResourceBundle/getBundle "i18n.test.example") ::hi "Rich"))))
         (it "works with explicit named bundle and string as key"
             (with-locale en-us
               (= "hello Rich" (i18n (ResourceBundle/getBundle "i18n.test.example") "hi" "Rich")))))
      ))

 

