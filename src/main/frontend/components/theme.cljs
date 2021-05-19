(ns frontend.components.theme
  (:require [rum.core :as rum]
            [frontend.util :as util]
            [frontend.handler.route :as route-handler]
            [frontend.handler.plugin :as plugin-handler]
            [frontend.components.svg :as svg]))

(rum/defc container
  [{:keys [route theme on-click nfs-granted? db-restoring? sidebar-open?] :as props} child]
  (rum/use-effect!
   #(let [doc js/document.documentElement
          cls (.-classList doc)]
      (.setAttribute doc "data-theme" (if (= theme "white") "light" theme))
      (if (= theme "dark")                                 ;; for tailwind dark mode
        (.add cls "dark")
        (.remove cls "dark"))
      (plugin-handler/hook-plugin-app :theme-mode-changed {:mode theme} nil))
   [theme])

  (rum/use-effect!
   #(plugin-handler/hook-plugin-app :sidebar-visible-changed {:visible sidebar-open?})
   [sidebar-open?])

  (rum/use-effect!
   #(let [db-restored? (false? db-restoring?)]
      (if db-restoring?
        (util/set-title! "Loading")
        (when (or nfs-granted? db-restored?)
          (route-handler/update-page-title! route))))
   [nfs-granted? db-restoring? route])

  [:div
   {:class    (str theme "-theme")
    :on-click on-click}
   child])
