import axios from 'axios';

const URL = {
	DOMAIN: 'http://golearn.co.kr:8808',
	FILE: 'upload',
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
