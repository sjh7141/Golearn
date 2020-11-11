export default {
	state: {
		currentTime: 0,
		duration: 0,
		selectedItem: null,

		mediaList: [],
		isChange: false,
	},
	getters: {
		currentTime(state) {
			return state.currentTime;
		},
		duration(state) {
			return state.duration;
		},
		selectedItem(state) {
			return state.selectedItem;
		},
		mediaList(state) {
			return state.mediaList;
		},
		isChange(state) {
			return state.isChange;
		},
	},
	mutations: {
		setCurrentTime(state, payload) {
			if (payload > state.duration) payload = state.duration;
			if (payload < 0) payload = 0;

			state.currentTime = payload;
		},
		setDuration(state, payload) {
			if (payload < 0.01) payload = 0;
			state.duration = payload;

			if (state.currentTime > payload) state.currentTime = payload;
		},
		setSelectedItem(state, payload) {
			state.selectedItem = payload;
		},
		setMediaList(state, payload) {
			state.mediaList = payload;
		},
		setIsChange(state, payload) {
			state.isChange = payload;
		},
	},
	actions: {},
};
