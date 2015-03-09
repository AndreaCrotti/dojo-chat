(ns dojo-chat2.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (POST "/" [user] (register user))
  (route/not-found "Not Found"))

(def users (atom {}))

(defn register
  [user]
  (if-not (contains? @users user)
    (reset! users (assoc @users user []))))

(def app
  (wrap-defaults app-routes site-defaults))
