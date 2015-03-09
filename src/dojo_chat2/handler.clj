(ns dojo-chat2.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]))

(def users (atom {}))

(defn register
  [user]
  (if-not (contains? @users user)
    (reset! users (assoc @users user []))))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (POST "/register" [user] (register user))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes api-defaults))
