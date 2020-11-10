import axios from 'axios';

const URL = {
	DOMAIN: 'https://golearn.co.kr/api',
	LOADMAP: 'loadmap',
	loadmapBuild() {
		return Array(this.DOMAIN, this.LOADMAP).join('/');
	},
};

export default {
	state: {
		loadmap: null,
	},
	getters: {
		loadmap(state) {
			return state.loadmap;
		},
	},
	mutations: {
		setLoadmap(state, payload) {
			state.loadmap = payload;
		},
	},
	actions: {
		getLoadmap(context, payload) {
			return axios.get(URL.loadmapBuild() + `/${payload}`);
		},
		setLoadmap() {
			return axios.put(URL.loadmapBuild() + `/`);
		},
		getChannelLoadmaps(context, payload) {
			return axios.get(URL.loadmapBuild() + `/member/${payload}`);
		},
	},
};
