export default {
	state: {
		message: '',
		snackbar: false,
	},
	getters: {
		SBMessage(state) {
			return state.message;
		},
		snackbar(state) {
			return state.snackbar;
		},
	},
	mutations: {
		setSBMessage(state, payload) {
			state.message = payload;
		},
		setSnackbar(state, payload) {
			state.snackbar = payload;
		},
	},
	actions: {},
};
