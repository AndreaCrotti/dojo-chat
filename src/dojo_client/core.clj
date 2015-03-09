(ns dojo-client.core
  (:require [org.httpkit.client :as client]
            [clojure.core.async :as async]))

(def response (client/get "http://localhost:3000"))

(def c (async/chan))

(defn poll-server []
  (async/go
    (async/>! c (:body @response))))


(defn print-channel []
  (async/go
    (while true
     (println "Result = "
              (async/<! c)))))
