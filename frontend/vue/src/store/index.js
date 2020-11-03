import Vue from 'vue';
import Vuex from 'vuex';
import Login from './modules/Login';
import Course from './modules/Course';
import Search from './modules/Search';
Vue.use(Vuex);

export default new Vuex.Store({
	state: {},
	mutations: {},
	actions: {},
	modules: {
		Login: Login,
		Course: Course,
		Search: Search,
	},
});
