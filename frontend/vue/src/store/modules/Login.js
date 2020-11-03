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
		isLogin: parseInt(sessionStorage.getItem('isLogin')),
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
			return state.user;
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
			state.user = payload;
			sessionStorage.setItem('user', payload);
		},
	},
	actions: {
		login(context, payload) {
			console.log(URL.loginBuild(), payload);
			return axios.post(URL.loginBuild(), payload);
		},
		getUser(context) {
			console.log(URL.userBuild(), context.state.token);
			const config = {
				headers: {
					Authorization: context.state.token,
				},
			};
			return axios.get(URL.userBuild(), config);
		},
	},
};
