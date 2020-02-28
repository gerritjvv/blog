(ns blog.combinations)

(set! *unchecked-math* true)
(set! *warn-on-reflection* true)

(defn -inc-index! [^longs indices ^long i]
  (aset indices i (unchecked-inc-int (aget indices i))))

(defn -copy-from-indices [s ^longs indices ^long l]
  (loop [i 0 record []]
    (if (= i l)
      record
      (recur (inc i) (conj record (nth s (aget indices i)))
        ))))

(defn -update-indices! [^longs indices ^long maxI]
  (loop [i maxI]
    (when (and (pos? i) (> (aget indices i) maxI))
      (-inc-index! indices (dec i))
      (aset indices i 0)
      (recur (dec i)))))

(defn -combinations [s ^longs indices ^long l ^long maxI]
  (when (not= (aget indices 0) l)
    (if (> (aget indices maxI) maxI)
      (do
        (-update-indices! indices maxI)
        (recur s indices l maxI))
      (let [record (-copy-from-indices s indices l)]
        (-inc-index! indices maxI)

        (lazy-seq
          (cons record
                (-combinations s indices l maxI)))))))

(defn combinations [s]
  (let [l (count s)
        indices (long-array l)
        maxI (dec l)]

    (-combinations s indices l maxI)))