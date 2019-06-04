import { VueRouter } from "vue-router/types/router";
import mainView from '../views/mainView.vue'

Vue.use(VueRouter);

export default new VueRouter({
    mode : 'history',
    base : process.env.BASE_URL,
    routes : [
        {
            path : '/', 
            name : 'main page',
            component : mainView
        },
        // {
        //     path : '/view', 
        //     name : 'map page',
        //     component : () => import(/* webpackChunkName: "map" */ "../views/mapView.vue") 
        // }
    ]
});