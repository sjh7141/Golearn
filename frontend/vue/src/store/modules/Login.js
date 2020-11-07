import axios from 'axios';

const URL = {
	DOMAIN: 'http://k3a402.p.ssafy.io:8801',
	AUTH: 'auth/login',
	USER: 'account/users',
	ACCOUNT: 'account-service',
	PASSWORD: 'account/password',
	loginBuild() {
		return Array(this.DOMAIN, this.AUTH).join('/');
	},
	userBuild() {
		return Array(this.DOMAIN, this.USER).join('/');
	},
	accountBuild() {
		return Array(this.DOMAIN, this.ACCOUNT).join('/');
	},
	passwordBuild() {
		return Array(this.DOMAIN, this.PASSWORD).join('/');
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
		signup(context, payload) {
			return axios.post(URL.userBuild(), payload);
		},
		emailCheck(context, payload) {
			return axios.get(URL.accountBuild() + `/email-check/${payload}`);
		},
		idCheck(context, payload) {
			return axios.get(URL.accountBuild() + `/id-check/${payload}`);
		},
		nicknameCheck(context, payload) {
			return axios.get(URL.accountBuild() + `/nickname-check/${payload}`);
		},
		pwdCheck(context, payload) {
			const config = {
				headers: {
					Authorization: context.state.token,
				},
			};
			return axios.post(
				URL.accountBuild() + '/password-check',
				payload,
				config,
			);
		},
		modifyUser(context, payload) {
			const config = {
				headers: {
					Authorization: context.state.token,
				},
			};
			return axios.patch(URL.userBuild(), payload, config);
		},
		modifyPassword(context, payload) {
			const config = {
				headers: {
					Authorization: context.state.token,
				},
			};
			return axios.patch(URL.passwordBuild(), payload, config);
		},
		deleteUser(context) {
			const config = {
				headers: {
					Authorization: context.state.token,
				},
			};
			return axios.delete(URL.userBuild(), config);
		},
	},
};
