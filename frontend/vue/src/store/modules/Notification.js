import axios from 'axios';

const URL = {
	DOMAIN: 'https://golearn.co.kr/api',
	// DOMAIN: 'http://localhost:8801',
	LOADMAP: 'notification',
	notificationBuild() {
		return Array(this.DOMAIN, this.LOADMAP).join('/');
	},
};

export default {
	state: {},
	getters: {},
	mutations: {},
	actions: {
		getNotiCount({ rootGetters }) {
			const config = {
				headers: {
					Authorization: rootGetters.token,
				},
			};
			return axios.get(URL.notificationBuild() + '/count', config);
		},
		getNotification({ rootGetters }) {
			const config = {
				headers: {
					Authorization: rootGetters.token,
				},
			};
			return axios.get(URL.notificationBuild(), config);
		},
		/*
            payload:{
                noti_msg : "content",
                noti_type : "(1,2,3,4)",
                noti_path : "video/3",
                dest : "member number"
            }
        */
		sendNotification({ rootGetters }, payload) {
			const config = {
				headers: {
					Authorization: rootGetters.token,
				},
			};
			return axios.post(URL.notificationBuild(), payload, config);
		},
	},
};
