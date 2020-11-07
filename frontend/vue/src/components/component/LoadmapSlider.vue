<template>
	<div ref="app">
		<v-card class="transparent" flat v-if="loadmaps.data.length !== 0">
			<v-divider></v-divider>

			<v-card-title>로드맵</v-card-title>
			<!-- <v-sheet class="mx-auto"> -->
			<!-- <v-slide-group class="pa-4" multiple show-arrows>
				<v-slide-item
					v-for="(course, i) in loading ? 5 : courses.data"
					:key="i"
				>
					<v-skeleton-loader
						type="card-avatar"
						:loading="loading"
						width="250px"
						class="mr-1"
					>
						<course-card
							:card="{
								maxWidth: 250,
								type: 'noAvatar',
							}"
							:course="course"
						></course-card>
					</v-skeleton-loader>
				</v-slide-item>
			</v-slide-group> -->
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
					v-for="(loadmap, i) in loading ? 5 : loadmaps.data"
					:key="i"
				>
					<v-skeleton-loader
						type="card-avatar"
						:loading="loading"
						width="250px"
						class="mr-1"
					>
						<loadmap-card
							:card="{
								maxWidth: 250,
								type: 'noAvatar',
							}"
							:loadmap="loadmap"
						></loadmap-card>
					</v-skeleton-loader>
				</swiper-slide>
			</swiper>
		</v-card>
	</div>
</template>

<script>
import { Swiper, SwiperSlide } from 'vue-awesome-swiper';
import { mapGetters, mapActions } from 'vuex';
import LoadmapCard from './LoadmapCard';
export default {
	components: {
		LoadmapCard,
		Swiper,
		SwiperSlide,
	},
	data: () => ({
		tab: null,
		loading: false,
		errored: false,
		subscribed: false,
		subscribeLoading: false,
		showSubBtn: true,
		loadmaps: {},
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
				1440: {
					slidesPerView: 4,
					spaceBetween: 10,
				},

				1024: {
					slidesPerView: 3,
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
		...mapActions(['getChannelLoadmaps']),
		async getChannel(id) {
			// console.log(this.$route.params.id)
			this.loading = true;
			this.errored = false;

			this.getChannelLoadmaps(id).then(res => {
				this.loadmaps = res;
			});

			// console.log(channel)
			this.loading = false;
		},
		subscribe() {},
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
