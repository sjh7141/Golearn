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
		ldmBanner: null,
		loadmapCourse: [],
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
		makeLoadmap(context) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.post(URL.loadmapBuild() + '/', {}, config);
		},
		getLoadmap(context, payload) {
			return axios.get(URL.loadmapBuild() + `/${payload}`);
		},
		setLoadmap(context, payload) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			let data = {
				insert: payload.insert,
				delete: payload.delete,
				update: payload.update,
				loadmap: context.state.loadmap,
			};
			return axios.put(URL.loadmapBuild() + `/`, data, config);
		},
		getChannelLoadmaps(context, payload) {
			return axios.get(URL.loadmapBuild() + `/member/${payload}`);
		},
	},
};
