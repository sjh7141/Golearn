import axios from 'axios';

const URL = {
	DOMAIN: 'http://k3a402.p.ssafy.io:8801',
	COURSE: 'course-service',
	courseBuild() {
		return Array(this.DOMAIN, this.COURSE).join('/');
	},
};

export default {
	state: {
		course: null,
		tags: [],
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
		setTitle(state, payload) {
			state.course.title = payload;
		},
		setContent(state, payload) {
			state.course.content = payload;
		},
		setTags(state, payload) {
			state.tags = payload;
		},
	},
	actions: {
		getCourse(context, payload) {
			return axios.get(URL.courseBuild() + `/${payload}`);
		},
		setCourse(context) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			console.log(context.state.course);
			return axios.put(
				URL.courseBuild() + `/`,
				{ course: context.state.course },
				config,
			);
		},
		getChannelCourses(context, payload) {
			return axios.get(URL.courseBuild() + `/member/${payload}`);
		},
		getLikeCourse(context) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.get(URL.courseBuild() + '/like', config);
		},
		getLearnCourse(context) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.get(URL.courseBuild() + '/view', config);
		},
	},
};
