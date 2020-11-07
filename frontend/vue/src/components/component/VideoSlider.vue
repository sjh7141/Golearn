<template>
	<div ref="app">
		<v-card class="transparent" flat v-if="videos.data.length !== 0">
			<v-card-title>최신 동영상</v-card-title>
			<!-- <v-sheet class="mx-auto"> -->
			<swiper class="swiper-container" :options="swiperOption">
				<div
					class="swiper-button-prev  swiper-button"
					slot="button-prev"
				></div>
				<div
					class="swiper-button-next  swiper-button"
					slot="button-next"
				></div>
				<swiper-slide
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
				</swiper-slide>
			</swiper>
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
		swiperOption: {
			navigation: {
				nextEl: '.swiper-button-next',
				prevEl: '.swiper-button-prev',
			},
			breakpoints: {
				1690: {
					slidesPerView: 6,
					spaceBetween: 10,
				},
				1440: {
					slidesPerView: 5,
					spaceBetween: 10,
				},
				1024: {
					slidesPerView: 4,
					spaceBetween: 10,
				},
				768: {
					slidesPerView: 3,
					spaceBetween: 10,
				},
				640: {
					slidesPerView: 2,
					spaceBetween: 10,
				},
				320: {
					slidesPerView: 1,
					spaceBetween: 10,
				},
			},
		},
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

<style scoped>
.swiper-button {
	color: gray;
	margin: 0px;
	top: 30%;
}
.card {
	background: #f9f9f9 !important;
}
</style>
