import axios from 'axios';

const URL = {
	DOMAIN: 'http://k3a402.p.ssafy.io:8801',
	PREFIX: 'search-service/v1',
	searchBuild() {
		return Array(this.DOMAIN, this.PREFIX).join('/');
	},
};

export default {
	state: {
		tags: null,
	},
	getters: {
		tags(state) {
			return state.tags;
		},
	},
	mutations: {
		setTags(state, payload) {
			state.tags = payload;
		},
	},
	actions: {
		getTags() {
			return axios.get(URL.searchBuild() + '/tag');
		},
	},
};
