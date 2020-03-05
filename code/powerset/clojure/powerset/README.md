# powerset


## Installation

Download from https://github.com/blog/powerset.

## Usage

```clojure
(require '[blog.combinations :as c] :reload)
 
(c/combinations [:a :b])
;; [:a :a]
;; [:a :b]
;; [:b :a]
;; [:b :b]

(c/powerset [:a :b :c])
;; #{:a}
;; #{:b :a}
;; #{:c :a}
;; #{:c :b :a}
;; #{:b}
;; #{:c :b}
;;#{:c}
```

Run the project's tests (they'll fail until you edit them):

    $ clojure -A:test:runner


## License

Copyright © 2020 Gvanvuuren

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
