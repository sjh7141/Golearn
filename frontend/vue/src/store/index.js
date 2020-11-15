import Vue from 'vue';
import Vuex from 'vuex';
import Login from './modules/Login';
import Course from './modules/Course';
import Search from './modules/Search';
import Loadmap from './modules/Loadmap';
import EditVideo from './modules/EditVideo';
import Jdoodle from './modules/Jdoodle';
import Video from './modules/Video';
import Account from './modules/Acoount';
import File from './modules/File';
import Notification from './modules/Notification';
import Common from './modules/Common';

Vue.use(Vuex);

export default new Vuex.Store({
	state: {},
	mutations: {},
	actions: {},
	modules: {
		Login: Login,
		Course: Course,
		Search: Search,
		Loadmap: Loadmap,
		EditVideo: EditVideo,
		Video: Video,
		Account: Account,
		File: File,
		Jdoodle: Jdoodle,
		Notification: Notification,
		Common: Common,
	},
});
