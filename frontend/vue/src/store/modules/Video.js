import axios from 'axios';

const URL = {
	DOMAIN: 'https://golearn.co.kr/api',
	PREFIX: 'video',
	videoBuild() {
		return Array(this.DOMAIN, this.PREFIX).join('/');
	},
};

export default {
	state: {
		video: null,
		checked: [],
	},
	getters: {
		tags(state) {
			return state.video;
		},
		checked(state) {
			return state.checked;
		},
	},
	mutations: {
		setVideos(state, payload) {
			state.video = payload;
		},
		setChecked(state, payload) {
			state.checked.push(payload);
		},
		removeChecked(state, payload) {
			const index = state.checked.indexOf(payload);
			state.checked.splice(index, 1);
		},
		clearChecked(state) {
			state.checked = [];
		},
	},
	actions: {
		getVideos(context) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.get(URL.videoBuild(), config);
		},
		getChannelVideos(context, id) {
			return axios.get(URL.videoBuild() + `/member/${id}`);
		},
		removeVideos(context, ids) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
				params: {
					hide_list: ids.join(','),
				},
			};
			return axios.delete(URL.videoBuild(), config);
		},
		getLikeVideo(context) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.get(URL.videoBuild() + '/like', config);
		},
	},
};
