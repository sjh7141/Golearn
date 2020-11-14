import axios from 'axios';

const URL = {
	DOMAIN: 'http://k3a402.p.ssafy.io:8801',
	LOADMAP: 'loadmap',
	loadmapBuild() {
		return Array(this.DOMAIN, this.LOADMAP).join('/');
	},
};

export default {
	state: {
		loadmap: null,
		ldmBanner: null,
		loadmapCourse: null,
	},
	getters: {
		loadmap(state) {
			return state.loadmap;
		},
		ldmBanner(state) {
			return state.ldmBanner;
		},
		loadmapCourse(state) {
			return state.loadmapCourse;
		},
	},
	mutations: {
		setLoadmap(state, payload) {
			state.loadmap = payload;
		},
		setLoadmapTitle(state, payload) {
			state.loadmap.ldm_title = payload;
		},
		setLoadmapContent(state, payload) {
			state.loadmap.ldm_content = payload;
		},
		setLoadmapThumbnail(state, payload) {
			state.loadmap.ldm_thumbnail = payload;
		},
		setLoadmapBanner(state, payload) {
			state.ldmBanner = payload;
		},
		setLoadmapCourse(state, payload) {
			state.loadmapCourse = payload;
		},
	},
	actions: {
		getLoadmap(context, payload) {
			return axios.get(URL.loadmapBuild() + `/${payload}`);
		},
		setLoadmap(context) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			let data = {
				insert: [],
				delete: [],
				update: [],
				loadmap: context.state.loadmap,
			};
			console.log(data);
			return axios.put(URL.loadmapBuild() + `/`, data, config);
		},
		getChannelLoadmaps(context, payload) {
			return axios.get(URL.loadmapBuild() + `/member/${payload}`);
		},
	},
};
