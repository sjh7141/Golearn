import axios from 'axios';

const URL = {
	DOMAIN: 'https://golearn.co.kr/api',
	PREFIX: 'video',
	COMMENT: 'comment',
	videoBuild() {
		return Array(this.DOMAIN, this.PREFIX).join('/');
	},
	commentBuild() {
		return Array(this.DOMAIN, this.PREFIX, this.COMMENT).join('/');
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
		_getVideoComments(context, id) {
			return axios.get(URL.commentBuild() + `/${id}`);
		},
		_writeComment(context, payload) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.post(
				URL.commentBuild() + `/${payload.vid_no}`,
				payload,
				config,
			);
		},

		_getVideoSubComments(context, payload) {
			return axios.get(
				URL.commentBuild() +
					`/${payload.vid_no}/${payload.vid_cmt_pno}`,
			);
		},
		_writeSubComment(context, payload) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.post(
				URL.commentBuild() +
					`/${payload.vid_no}/${payload.vid_cmt_pno}`,
				payload,
				config,
			);
		},

		getVideoDetail(context, id) {
			return axios.get(URL.videoBuild() + `/${id}`);
		},
		getVideos() {
			return axios.get(URL.videoBuild());
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
		getLikeVideos(context) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.get(URL.videoBuild() + '/like', config);
		},

		getLikeVideo(context, id) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.get(URL.videoBuild() + `/like/${id}`, config);
		},

		likeVideo(context, id) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.post(URL.videoBuild() + `/like/${id}`, {}, config);
		},

		cancelVideo(context, id) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.delete(URL.videoBuild() + `/like/${id}`, config);
		},

		getSaveVideo(context, id) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.get(URL.videoBuild() + `/save/${id}`, config);
		},
		setSaveVideo(context, payload) {
			const config = {
				headers: {
					Authorization: context.getters.token,
				},
			};
			return axios.post(URL.videoBuild() + '/save', payload, config);
		},
		removeSaveVideo(context, payload) {
			const config = {
				headers: {
					Authorization: context.getters.token,
				},
			};
			return axios.delete(URL.videoBuild() + `/save/${payload}`, config);
		},
	},
};
