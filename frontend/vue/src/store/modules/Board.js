import axios from 'axios';

const URL = {
	DOMAIN: 'https://golearn.co.kr/api',
	BOARD: 'board',
	COMMENT: 'comment',
	boardBuild() {
		return Array(this.DOMAIN, this.BOARD).join('/');
	},
	commentBuild() {
		return Array(this.DOMAIN, this.BOARD, this.COMMENT).join('/');
	},
};
export default {
	state: {},
	getters: {},
	mutations: {},
	actions: {
		getBoard(context, payload) {
			return axios.get(URL.boardBuild() + `/${payload.type}`, {
				params: {
					page_no: payload.page,
				},
			});
		},
		getPost(context, payload) {
			return axios.get(URL.boardBuild() + `/details/${payload}`);
		},
		postPost(context, payload) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.post(URL.boardBuild(), payload, config);
		},
		setPost(context, payload) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			console.log(payload);
			return axios.put(URL.boardBuild(), payload, config);
		},
		deletePost(context, payload) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.delete(
				URL.boardBuild() + `/${payload.brd_no}`,
				config,
			);
		},
		_getBoardComments(context, payload) {
			return axios.get(URL.commentBuild() + `/${payload.brd_no}`, {
				params: {
					page_no: payload.page_no,
				},
			});
		},
		_writeBoardComment(context, payload) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.post(URL.commentBuild() + '/', payload, config);
		},
		_setBoardComment(context, payload) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.put(URL.commentBuild() + '/', payload, config);
		},
		_deleteBoardComment(context, payload) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.delete(
				URL.commentBuild() + `/${payload.cmt_no}`,
				config,
			);
		},
		_getBoardSubComments(context, payload) {
			return axios.get(
				URL.commentBuild() +
					`/${payload.brd_no}/${payload.brd_cmt_pno}`,
			);
		},
	},
};
