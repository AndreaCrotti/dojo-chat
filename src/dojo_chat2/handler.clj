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
      {:status 201 :body (format "Created user %s" user) :headers {"Content-Type" "text/plain"}})
    {:status 400 :body "User already existing"}))

(defn get-messages
  [user]
  (str (@users user)))

(defn send-message
  [from to]
  )

(defroutes app-routes
  (GET "/" [] "Hello World")
  (POST "/register" kwargs (register (:user (:params kwargs))))
  (GET "/messages" [user] (get-messages user))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes api-defaults))
