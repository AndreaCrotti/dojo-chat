(ns dojo-chat2.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]))

(def users (atom {}))

(defn register
  [user]
  (println (format "user = %s ,users = %s" user @users))
  (if-not (contains? @users user)
    (do
      (reset! users (assoc @users user []))
      {:status 201 :body "Created user" :headers {"Content-Type" "text/plain"}})))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (POST "/register" [kwargs] (register (:user kwargs)))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes api-defaults))
