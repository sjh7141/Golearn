import axios from 'axios';

const URL = {
	DOMAIN: 'https://golearn.co.kr/api',
	FILE: 'file/upload',
	fileBuild() {
		return Array(this.DOMAIN, this.FILE).join('/');
	},
};

export default {
	state: {},
	getters: {},
	mutations: {},
	actions: {
		upload(context, payload) {
			var config = {
				headers: {
					'Content-Type': 'multipart/form-data',
				},
			};
			return axios.post(
				`${URL.fileBuild()}?target=${payload.target}`,
				payload.data,
				config,
			);
		},
	},
};
