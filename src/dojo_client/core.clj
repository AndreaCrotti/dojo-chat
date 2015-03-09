(ns dojo-client.core
  (:require [org.httpkit.client :as client]
            [clojure.core.async :as async]))


(defn response-messages [user] (client/get (str "http://localhost:3000/messages?user=" user) ))

(def c (async/chan))

(defn poll-server [user]
  (async/go
    (while true
      (async/>! c (:body @(response-messages user)))
      (Thread/sleep 2000))))


(defn print-channel []
  (async/go-loop []
    (println "Result = "
             (async/<! c))))


(defn register-user [name]
  (let [options {:form-params {:user name}}
        {:keys [status error]} @(client/post "http://localhost:3000/register" options)]
    (if error
      (println "Failed, exception is " error)
      (println "Async HTTP POST: " status))))

(defn send-message [name message]
  (let [options {:form-params {:to name :message message}}
        {:keys [status error]} @(client/post "http://localhost:3000/send" options)]
    (if error
      (println "Failed, exception is " error)
      (println "Async HTTP POST: " status))))
