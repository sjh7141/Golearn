import axios from 'axios';

const URL = {
	DOMAIN: 'http://k3a402.p.ssafy.io:8801',
	AUTH: 'auth/login',
	USER: 'account/users',
	loginBuild() {
		return Array(this.DOMAIN, this.AUTH).join('/');
	},
	userBuild() {
		return Array(this.DOMAIN, this.USER).join('/');
	},
};

export default {
	state: {
		token: sessionStorage.getItem('token'),
		isLogin: Number(sessionStorage.getItem('isLogin')),
		user: sessionStorage.getItem('user'),
	},
	getters: {
		// token(state, getters, rootState, rootGetters) {
		token(state) {
			return state.token;
		},
		isLogin(state) {
			return state.isLogin;
		},
		user(state) {
			if (state.user) {
				return JSON.parse(state.user);
			} else {
				return null;
			}
		},
	},
	mutations: {
		setToken(state, payload) {
			state.token = payload;
			sessionStorage.setItem('token', payload);
		},
		setIsLogin(state, payload) {
			state.isLogin = payload;
			sessionStorage.setItem('isLogin', payload);
		},
		setUser(state, payload) {
			state.user = JSON.stringify(payload);
			sessionStorage.setItem('user', state.user);
		},
	},
	actions: {
		login(context, payload) {
			return axios.post(URL.loginBuild(), payload);
		},
		getUser(context) {
			const config = {
				headers: {
					Authorization: context.state.token,
				},
			};
			return axios.get(URL.userBuild(), config);
		},
	},
};
