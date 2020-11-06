<template>
	<div ref="app">
		<v-card class="transparent" flat>
			<v-card-title
				><div>동영상</div>
				<v-spacer></v-spacer>
				<v-btn v-if="hide" class="mx-5" @click="remove"
					>선택 완료</v-btn
				>
				<v-switch
					color="purple"
					inset
					label="영상 삭제"
					v-model="hide"
				></v-switch>
			</v-card-title>
			<v-row>
				<v-col
					cols="12"
					sm="6"
					md="3"
					lg="2"
					v-for="(video, i) in loading ? 10 : videos.data"
					:key="i"
					class="mx-xs-auto"
				>
					<v-checkbox
						v-if="hide"
						v-model="checked"
						:value="video.vid_no"
					></v-checkbox>
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
		hide: false,
		checked: [],
	}),
	computed: {
		...mapGetters(['isAuthenticated']),
	},
	components: {
		VideoCard,
	},
	methods: {
		...mapActions(['getChannelVideos', 'removeVideos']),
		async getChannel(id) {
			this.loading = true;
			this.errored = false;

			this.getChannelVideos(id).then(res => {
				this.videos = res;
				console.log(res);
			});

			this.loading = false;
		},
		subscribe() {
			console.log('구독');
		},
		async remove() {
			const self = this;
			this.removeVideos(this.checked).then(function() {
				self.getChannel(self.$route.params.id);
				self.checked = [];
				self.hide = false;
			});
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

<style scoped>
.card {
	background: #f9f9f9 !important;
}
</style>
