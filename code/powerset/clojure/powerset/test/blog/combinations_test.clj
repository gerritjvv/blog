(ns blog.combinations-test
  (:require [clojure.test :refer :all]
            [blog.combinations-data :as data]
            [blog.combinations :as c]))


(defn test-runner [test-cases test-fn]
  (doseq [{:keys [expected input]} test-cases]
    (is
      (= (test-fn input)
         expected))))

(deftest test-combinations
  (test-runner data/COMBINATION-DATA c/combinations))

(deftest test-powerset
  (test-runner data/POWERSET-DATA c/powerset))
