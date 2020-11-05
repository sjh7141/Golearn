import axios from 'axios';

const URL = {
	DOMAIN: 'http://k3a402.p.ssafy.io:8801',
	PREFIX: 'account',
	searchBuild() {
		return Array(this.DOMAIN, this.PREFIX).join('/');
	},
};

export default {
	state: {},
	getters: {},
	mutations: {},
	actions: {
		getMember(context, id) {
			return axios.get(URL.searchBuild() + `/member/${id}`);
		},
	},
};
