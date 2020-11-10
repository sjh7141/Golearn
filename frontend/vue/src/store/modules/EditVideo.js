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
			if (payload < 0.01) payload = 0;
			state.duration = payload;

			if (state.currentTime > state.duration)
				state.currentTime = state.duration;
		},
	},
	actions: {},
};
