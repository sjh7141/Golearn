<template>
	<div ref="app">
		<v-card class="transparent" flat v-if="courses.length !== 0">
			<v-divider></v-divider>

			<v-card-title>코스</v-card-title>

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
					v-for="(course, i) in loading ? 5 : courses"
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
				</swiper-slide>
			</swiper>
		</v-card>
	</div>
</template>

<script>
import { Swiper, SwiperSlide } from 'vue-awesome-swiper';
import CourseCard from './CourseCard';
export default {
	props: ['courses'],
	components: {
		CourseCard,
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
	computed: {},
};
</script>

<style scoped>
.swiper-button {
	color: gray;
	margin: 0px;
	top: 30%;
}
.card {
	/* background: #f9f9f9 !important; */
}
</style>
