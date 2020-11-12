import axios from 'axios';

const URL = {
	DOMAIN: 'http://k3a402.p.ssafy.io:8801',
	COURSE: 'course',
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
			state.course.cos_title = payload;
		},
		setContent(state, payload) {
			state.course.cos_content = payload;
		},
		setTags(state, payload) {
			state.tags = payload;
		},
		setThumbnail(state, payload) {
			state.course.cos_thumbnail = payload;
		},
		setBanner(state, payload) {
			state.course.cos_banner = payload;
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
			return axios.put(
				URL.courseBuild() + `/`,
				context.state.course,
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
		getIndex(context, payload) {
			return axios.get(URL.courseBuild() + `/index/${payload}`);
		},
		getCourseTag(context, payload) {
			return axios.get(URL.courseBuild() + `/tag/${payload}`);
		},
		setCourseTag(context, payload) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			console.log(payload);
			return axios.post(
				URL.courseBuild() + '-service/tag/',
				payload,
				config,
			);
		},
		deleteCourseTag(context, payload) {
			console.log(payload);
			return axios.delete(URL.courseBuild() + '-service/tag/', {
				data: payload,
				headers: { Authorization: context.rootGetters.token },
			});
		},
	},
};
