import axios from 'axios';

const URL = {
	DOMAIN: 'https://golearn.co.kr/api',
	PREFIX: 'board',
	boardBuild() {
		return Array(this.DOMAIN, this.PREFIX).join('/');
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
	},
};
