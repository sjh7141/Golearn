import axios from 'axios';

const URL = {
	DOMAIN:'https://api.jdoodle.com/v1/execute',
	ApiBuild() {
		return this.DOMAIN;
	},
};

export default {
	state: {},
	getters: {},
	mutations: {},
	actions: {
		compile(context, payload) {
			return axios.post(URL.DOMAIN, {
				clientId: 'bef508a76e6980f3c3e9157b07ce4d5e',
				clientSecret:
					'a4fe5d60d5904f039e0a5e4c42b13fafadf131169838e703eefa2bb8a62f9c1f',
				script: payload.script,
				language: payload.language,
				versionIndex: payload.versionIndex,
			});
		},
	},
};
