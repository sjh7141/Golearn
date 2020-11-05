<template>
	<div ref="app">
		<v-card class="transparent" flat>
			<v-card-title>동영상</v-card-title>
			<v-divider></v-divider>
			<v-row>
				<v-col
					cols="12"
					sm="6"
					md="3"
					lg="3"
					v-for="(video, i) in loading ? 10 : videos.data"
					:key="i"
					class="mx-xs-auto"
				>
					<v-skeleton-loader type="card-avatar" :loading="loading">
						<video-card
							:card="{ maxWidth: 250 }"
							:video="video"
						></video-card>
					</v-skeleton-loader>
				</v-col>
			</v-row>
		</v-card>
	</div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import VideoCard from './VideoCard';
export default {
	data: () => ({
		loading: false,
		errored: false,
		showSubBtn: true,
		items: [
			{ name: '홈', link: '/home' },
			{ name: '동영상', link: '/videos' },
		],
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
	components: {
		VideoCard,
	},
	methods: {
		...mapActions(['getChannelVideos']),
		async getChannel(id) {
			this.loading = true;
			this.errored = false;

			this.getChannelVideos(id).then(res => {
				this.videos = res;
			});

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
