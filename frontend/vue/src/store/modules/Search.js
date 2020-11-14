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
		getTagList() {
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
