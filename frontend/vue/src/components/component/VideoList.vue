<template>
	<div ref="app">
		<v-card class="transparent" flat v-if="videos.data.length !== 0">
			<v-card-title
				><div>동영상</div>
				<v-spacer></v-spacer>
				<v-slide-x-transition mode="out-in">
					<v-btn
						v-if="hide"
						class="mx-1"
						@click="remove"
						outlined
						color="purple"
						>삭제</v-btn
					>
				</v-slide-x-transition>
				<v-slide-x-transition mode="out-in">
					<v-btn
						v-if="hide"
						class="mx-1"
						@click="hide = false"
						outlined
						color="purple"
						>취소</v-btn
					>
				</v-slide-x-transition>
				<v-slide-x-reverse-transition mode="out-in">
					<v-btn
						color="gray"
						icon
						large
						v-if="!hide && channel.no == user.no"
						class="mx-1"
						@click="hide = true"
					>
						<v-icon>mdi-cog</v-icon>
					</v-btn>
				</v-slide-x-reverse-transition>
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
	props: ['channel'],
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
		...mapGetters(['isAuthenticated', 'user']),
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
			});

			this.loading = false;
		},
		subscribe() {},
		async remove() {
			const self = this;
			this.removeVideos(this.checked).then(function() {
				self.getChannel(self.$route.params.id);
				self.checked = [];
			});
			self.hide = false;
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
