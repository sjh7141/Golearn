import axios from 'axios';

const URL = {
	DOMAIN: 'http://k3a402.p.ssafy.io:8801',
	PREFIX: 'video',
	searchBuild() {
		return Array(this.DOMAIN, this.PREFIX).join('/');
	},
};

export default {
	state: {
		video: null,
	},
	getters: {
		tags(state) {
			return state.video;
		},
	},
	mutations: {
		setVideos(state, payload) {
			state.video = payload;
		},
	},
	actions: {
		getVideos() {
			return axios.get(URL.searchBuild() + '/3');
		},
	},
};
