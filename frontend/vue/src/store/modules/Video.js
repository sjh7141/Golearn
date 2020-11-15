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
		thumbnailURL: null,
		uploadVideo: {
			tags: [],
			vid_content: '',
			vid_title: '',
			vid_url: '',
			vid_length: 0,
			vid_code: '',
			vid_code_type: 'java',
		},
		editURL: '',
	},
	getters: {
		tags(state) {
			return state.video;
		},
		checked(state) {
			return state.checked;
		},
		uploadVideo(state) {
			return state.uploadVideo;
		},
		editURL(state) {
			return state.editURL;
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
		setThumbnailURL(state, payload) {
			state.uploadVideo.vid_thumbnail = payload;
		},
		setVideoURL(state, payload) {
			state.uploadVideo.vid_url = payload;
		},
		setVideoTitle(state, payload) {
			state.uploadVideo.vid_title = payload;
		},
		setVideoContent(state, payload) {
			state.uploadVideo.vid_content = payload;
		},
		setVideoTags(state, payload) {
			state.uploadVideo.tags = payload;
		},
		setVideoCode(state, payload) {
			state.uploadVideo.vid_code = payload;
		},
		setVideoCodeType(state, payload) {
			state.uploadVideo.vid_code_type = payload;
		},
		setVideoLength(state, payload) {
			state.uploadVideo.vid_length = payload;
		},
		setEditURL(state, payload) {
			state.editURL = payload;
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
		getVideos(context) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.get(URL.videoBuild(), config);
		},
		setVideo(context) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.post(
				URL.videoBuild(),
				context.state.uploadVideo,
				config,
			);
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
		getSaveVideos(context) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.get(URL.videoBuild() + '/save', config);
		},
		getVideoDetail(context, id) {
			return axios.get(URL.videoBuild() + `/${id}`);
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
