<template>
	<v-container>
		<swiper class="swiper" :options="swiperOption">
			<swiper-slide v-for="(i, idx) in 5" :key="idx">
				<card-component :item="card"></card-component>
			</swiper-slide>

			<div class="swiper-pagination" slot="pagination"></div>
		</swiper>
	</v-container>
</template>

<script>
import CardComponent from '@/components/component/CardComponent';
import { Swiper, SwiperSlide } from 'vue-awesome-swiper';
import 'swiper/css/swiper.css';

export default {
	name: 'swiper-example-multiple-slides-per-biew',
	title: 'Multiple slides per view',
	components: {
		Swiper,
		SwiperSlide,
		CardComponent,
	},
	data() {
		return {
			swiperOption: {
				slidesPerView: 4,
				spaceBetween: 30,
				pagination: {
					el: '.swiper-pagination',
					clickable: true,
				},
			},
			card: {
				title: 'C++ 기초 강의',
				viewer: 50,
				like: 1000,
				thumbnail:
					'https://cdn.inflearn.com/wp-content/uploads/ktw_algorithm3.jpg',
				tags: ['C', 'C++'],
				nickname: '고런',
				id: 'golearn',
				profile:
					'https://www.creativefabrica.com/wp-content/uploads/2019/02/Monogram-GR-Logo-Design-by-Greenlines-Studios.jpg',
			},
		};
	},
};
</script>
<style scoped>
.swiper-slide {
	height: 300px;
}
.container {
	width: 80%;
}
</style>
