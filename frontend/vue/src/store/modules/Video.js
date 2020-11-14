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
		getLikeVideo(context) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.get(URL.videoBuild() + '/like', config);
		},
		getSaveVideo(context) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.get(URL.videoBuild() + '/save', config);
		},
	},
};
