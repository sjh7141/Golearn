<template>
	<div ref="app">
		<v-card class="transparent" flat>
			<v-card-title>최신 동영상</v-card-title>
			<v-divider></v-divider>
			<!-- <v-sheet class="mx-auto"> -->
			<v-slide-group class="pa-4" multiple show-arrows>
				<v-slide-item
					v-for="(video, i) in loading ? 5 : videos.data"
					:key="i"
				>
					<v-skeleton-loader
						type="card-avatar"
						:loading="loading"
						width="250px"
						class="mr-1"
					>
						<video-card
							:card="{
								maxWidth: 250,
								type: 'noAvatar',
							}"
							:video="video"
						></video-card>
					</v-skeleton-loader>
				</v-slide-item>
			</v-slide-group>
		</v-card>
	</div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import VideoCard from './VideoCard';
export default {
	components: {
		VideoCard,
	},
	data: () => ({
		tab: null,
		loading: false,
		errored: false,
		subscribed: false,
		subscribeLoading: false,
		showSubBtn: true,
		videos: {},
		channel: {
			mbr_nickname: 'asm9677',
			_id: 'asdf',
		},
		currentUser: {
			_id: 'asdf',
		},
		signinDialog: false,
		details: {},
	}),
	computed: {
		...mapGetters(['isAuthenticated']),
	},

	methods: {
		...mapActions(['getChannelVideos']),
		async getChannel(id) {
			// console.log(this.$route.params.id)
			this.loading = true;
			this.errored = false;

			this.getChannelVideos(id).then(res => {
				this.videos = res;
			});

			// console.log(channel)
			this.loading = false;
		},
		subscribe() {
			console.log('구독');
		},
	},
	mounted() {
		this.getChannel(this.$route.params.id);
	},
	beforeRouteUpdate(to, from, next) {
		this.getChannel(to.params.id);
		next();
	},
};
</script>

<style></style>
