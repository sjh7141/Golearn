import axios from 'axios';

const URL = {
	DOMAIN: 'https://golearn.co.kr/api',
	COURSE: 'course',
	courseBuild() {
		return Array(this.DOMAIN, this.COURSE).join('/');
	},
};

export default {
	state: {
		course: null,
	},
	getters: {
		course(state) {
			return state.course;
		},
	},
	mutations: {
		setCourse(state, payload) {
			state.course = payload;
		},
	},
	actions: {
		getCourse(context, payload) {
			return axios.get(URL.courseBuild() + `/${payload}`);
		},
		setCourse() {
			return axios.put(URL.courseBuild() + `/`);
		},
		getChannelCourses(context, payload) {
			return axios.get(URL.courseBuild() + `/member/${payload}`);
		},
	},
};
