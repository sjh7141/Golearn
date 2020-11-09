export default {
	state: {
		currentTime: 0,
		duration: 0,
	},
	getters: {
		currentTime(state) {
			return state.currentTime;
		},
		duration(state) {
			return state.duration;
		},
	},
	mutations: {
		setCurrentTime(state, payload) {
			state.currentTime = payload;
			if (state.currentTime > state.duration)
				state.currentTime = state.duration;
		},
		setDuration(state, payload) {
			state.duration = payload;
			if (state.currentTime > state.duration)
				state.currentTime = state.duration;
		},
	},
	actions: {},
};
