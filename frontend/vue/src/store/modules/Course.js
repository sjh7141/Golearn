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

		getLikeCourse(context, id) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.get(URL.courseBuild() + `/like/${id}`, config);
		},

		setLikeCourse(context, id) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.post(URL.courseBuild() + `/like/${id}`, {}, config);
		},

		removeLikeCourse(context, id) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.delete(URL.courseBuild() + `/like/${id}`, config);
		},

		getChapterDetail(contex, id) {
			return axios.get(URL.courseBuild() + `/index/detail/${id}`);
		},

		getCourseIndexs(context, payload) {
			return axios.get(URL.courseBuild() + `/index/${payload}`);
		},

		getChannelCourses(context, payload) {
			return axios.get(URL.courseBuild() + `/member/${payload}`);
		},
		getLikeCourses(context) {
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

		getCompleteChapter(context, id) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.get(URL.courseBuild() + `/index/check/${id}`, config);
		},

		setCompleteChapter(context, payload) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.get(
				URL.courseBuild() + `/index-complete`,
				payload,
				config,
			);
		},

		getVersions(context, id) {
			const config = {
				headers: {
					Authorization: context.rootGetters.token,
				},
			};
			return axios.get(URL.courseBuild() + `/versions/${id}`, config);
		},
	},
};
