import axios from 'axios';

const URL = {
	DOMAIN: 'https://golearn.co.kr/api',
	PREFIX: 'search-service/v1',
	searchBuild() {
		return Array(this.DOMAIN, this.PREFIX).join('/');
	},
};

export default {
	state: {
		tag: null,
	},
	getters: {
		tag(state) {
			return state.tag;
		},
	},
	mutations: {
		setTags(state, payload) {
			state.tag = payload;
		},
	},
	actions: {
		getTags() {
			return axios.get(URL.searchBuild() + '/tag');
		},
		getSearchResult(context, payload) {
			const config = {
				params: payload,
			};
			return axios.get(URL.searchBuild() + '/search', config);
		},
	},
};
