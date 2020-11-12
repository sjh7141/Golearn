import axios from 'axios';

const URL = {
	DOMAIN: 'https://golearn.co.kr/api',
	PREFIX: 'account',
	accountBuild() {
		return Array(this.DOMAIN, this.PREFIX).join('/');
	},
};

export default {
	state: {},
	getters: {},
	mutations: {},
	actions: {
		getMember(context, id) {
			return axios.get(URL.accountBuild() + `/users/no/${id}`);
		},
		isLike({ rootGetters }, id) {
			const config = {
				headers: {
					Authorization: rootGetters.token,
				},
			};
			return axios.get(URL.accountBuild() + `/like/${id}`, config);
		},
		like({ rootGetters }, id) {
			const config = {
				headers: {
					Authorization: rootGetters.token,
				},
			};
			return axios.post(
				URL.accountBuild() + `/like`,
				{ mbr_no: id },
				config,
			);
		},
		unlike({ rootGetters }, id) {
			const config = {
				headers: {
					Authorization: rootGetters.token,
					'Content-Type': 'application/json',
				},
				params: { mbr_no: id },
			};
			console.log(config);
			return axios.delete(URL.accountBuild() + `/like`, config);
		},
		setBanner(context, payload) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.patch(
				URL.accountBuild() + `/users/banner`,
				payload,
				config,
			);
		},
		sendEmail(context, payload) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.post(
				URL.accountBuild() + '/email-authentication',
				payload,
				config,
			);
		},
		checkCode(context, payload) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.get(
				URL.accountBuild() + `/email-authentication/${payload}`,
				config,
			);
		},
	},
};
