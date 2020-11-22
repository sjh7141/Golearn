import axios from 'axios';

const URL = {
	DOMAIN: 'https://golearn.co.kr/api',
	VIDEO: 'video-service',
	videoBuild() {
		return Array(this.DOMAIN, this.VIDEO).join('/');
	},
};

export default {
	state: {
		currentTime: 0,
		duration: 0,
		selectedItem: null,

		mediaList: [],
		isChange: false,
	},
	getters: {
		currentTime(state) {
			return state.currentTime;
		},
		duration(state) {
			return state.duration;
		},
		selectedItem(state) {
			return state.selectedItem;
		},
		mediaList(state) {
			return state.mediaList;
		},
		isChange(state) {
			return state.isChange;
		},
	},
	mutations: {
		setCurrentTime(state, payload) {
			if (payload > state.duration) payload = state.duration;
			if (payload < 0) payload = 0;

			state.currentTime = payload;
		},
		setDuration(state, payload) {
			if (payload < 0.01) payload = 0;
			state.duration = payload;

			if (state.currentTime > payload) state.currentTime = payload;
		},
		setSelectedItem(state, payload) {
			state.selectedItem = payload;
		},
		setMediaList(state, payload) {
			state.mediaList = payload;
		},
		setIsChange(state, payload) {
			state.isChange = payload;
		},
	},
	actions: {
		initEditVideo(context) {
			context.commit('setCurrentTime', 0);
			context.commit('setDuration', 0);
			context.commit('setSelectedItem', null);
			context.commit('setMediaList', []);
			context.commit('setIsChange', false);
		},
		uploadVideo(context, payload) {
			const config = {
				headers: {
					Authorization: context.getters.token,
				},
			};
			return axios.post(URL.videoBuild(), payload, config);
		},
		saveVideo(context, payload) {
			const config = {
				headers: {
					Authorization: context.getters.token,
				},
			};
			return axios.post(URL.videoBuild() + '/save', payload, config);
		},
		getStorageList(context) {
			const config = {
				headers: {
					Authorization: context.getters.token,
				},
			};
			return axios.get(URL.videoBuild() + '/save', config);
		},
	},
};
