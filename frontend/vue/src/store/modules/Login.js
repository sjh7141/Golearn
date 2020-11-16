import axios from 'axios';

const URL = {
	DOMAIN: 'http://localhost:8080',
	AUTH: 'auth/login',
	loginBuild() {
		return Array(this.DOMAIN, this.AUTH).join('/');
	},
};

export default {
	state: {
		token: sessionStorage.getItem('token'),
	},
	getters: {
		// token(state, getters, rootState, rootGetters) {
		token(state) {
			return state.token;
		},
	},
	mutations: {
		setToken(state, payload) {
			state.token = payload;
			sessionStorage.setItem('token', payload);
		},
	},
	actions: {
		login(context) {
			// console.log(URL.loginBuild());
			axios.get(URL.loginBuild()).then(({ data }) => {
				context.commit('setToken', data.token);
			});
			// .catch(error => {
			// 	console.log(error);
			// });
		},
	},
};
