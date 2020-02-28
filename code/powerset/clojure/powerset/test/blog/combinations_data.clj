(ns blog.combinations-data
  (:require [clojure.test :refer :all]))

(def COMBINATION-DATA [
                       {:input    [:a :b]
                        :expected [[:a :a]
                                   [:a :b]
                                   [:b :a]
                                   [:b :b]]}

                       {:input    [:a :b :c]
                        :expected [[:a :a :a]
                                   [:a :a :b]
                                   [:a :a :c]
                                   [:a :b :a]
                                   [:a :b :b]
                                   [:a :b :c]
                                   [:a :c :a]
                                   [:a :c :b]
                                   [:a :c :c]
                                   [:b :a :a]
                                   [:b :a :b]
                                   [:b :a :c]
                                   [:b :b :a]
                                   [:b :b :b]
                                   [:b :b :c]
                                   [:b :c :a]
                                   [:b :c :b]
                                   [:b :c :c]
                                   [:c :a :a]
                                   [:c :a :b]
                                   [:c :a :c]
                                   [:c :b :a]
                                   [:c :b :b]
                                   [:c :b :c]
                                   [:c :c :a]
                                   [:c :c :b]
                                   [:c :c :c]]}])

(def POWERSET-DATA [
                       {:input    [:a :b]
                        :expected [#{:a} #{:b :a} #{:b}]}

                       {:input    [:a :b :c]
                        :expected [#{:a} #{:b :a} #{:c :a} #{:c :b :a} #{:b} #{:c :b} #{:c}]}])

