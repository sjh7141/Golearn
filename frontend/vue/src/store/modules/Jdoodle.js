import axios from 'axios';

const URL = {
	DOMAIN:
		'https://cors-anywhere.herokuapp.com/https://api.jdoodle.com/v1/execute',
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
				clientId: '4cd1af623a8965c83bb833b2a2c01024',
				clientSecret:
					'e5f7bc09f39277e0c238c831fb8dd49ef0082dbee8d1f586be3228822f8361fb',
				script: payload.script,
				language: payload.language,
				versionIndex: payload.versionIndex,
			});
		},
	},
};
