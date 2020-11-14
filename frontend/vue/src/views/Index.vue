<template>
	<div>
		<v-app id="inspire">
			<!-- 배너 -->
			<swiper class="swiper" :options="swiperOption" ref="swiper">
				<swiper-slide
					class="slide pb-5 pt-9"
					v-for="(item, index) in banner"
					:key="index"
					:style="{
						'background-image': 'url(' + item.thumbnail + ')',
						'background-size': item.zoom + '%',
					}"
				>
					<v-row class="info-upper" style="width:85%;">
						<v-col cols="6">
							<img
								class="swiper-img"
								height="400px"
								width="100%"
								:src="item.thumbnail"
							/>
						</v-col>
						<v-col cols="6" class="pl-10">
							<div class="title py-5" v-html="item.title"></div>
							<div class="text" v-html="item.text"></div>
							<div>
								<v-row>
									<div class="px-5">
										<span style="font-size: 15px;">
											{{ (index + 1) | setNumLength }} |
											{{ banner.length | setNumLength }}
										</span>
									</div>
									<div style="width:70%">
										<v-progress-linear
											:value="
												((index + 1) / banner.length) *
													100
											"
											rounded
											color="white"
											style="display:inline-block"
										></v-progress-linear>
									</div>
								</v-row>
							</div>
						</v-col>
					</v-row>
				</swiper-slide>
				<div
					class="swiper-button-next swiper-button-white"
					slot="button-next"
				></div>
				<div
					class="swiper-button-prev swiper-button-white"
					slot="button-prev"
				></div>
			</swiper>
			<!-- 강의 -->
			<div class="container">
				<v-container>
					<v-slide-group
						class="my-10"
						show-arrows
						style="height: 50px;"
					>
						<v-slide-item v-for="tag in search" :key="tag">
							<v-btn
								class="mx-2"
								active-class="blue white--text"
								color="white"
								large
								rounded
								@click="goToSearch(tag)"
								style="text-transform:none;"
							>
								<v-icon color="green accent-4" left>
									mdi-search-web
								</v-icon>
								{{ tag }}
							</v-btn>
						</v-slide-item>
					</v-slide-group>
					<v-divider></v-divider>
					<h2 class="mt-15">
						이런 강의 어떠세요?
					</h2>
					<v-row>
						<vcards :items="cards"> </vcards>
					</v-row>
					<v-divider></v-divider>
					<h2 class="mt-15">🚨 금주의 인기강의</h2>
					<v-row>
						<vcards :items="hit"></vcards>
					</v-row>
					<v-divider></v-divider>
					<h2 class="mt-15">🔥 수강생 급상승 강의</h2>
					<v-row>
						<vcards :items="cards"></vcards>
					</v-row>
					<v-row class="py-15">
						<v-img
							src="https://cdn.inflearn.com/public/main_sliders/38744ba0-b12a-491a-9ca2-43375c3905ba/share-you.jpg"
						>
							<div class="py-10 px-15">
								<span
									class="bold"
									style="font-size: 30px; color:#2e2e2e;"
								>
									# 영상참여
								</span>
								<br />
								<span class="bold" style="color:#808080;">
									여러분의 작은참여가 교육의 시작을
									만들어냅니다
								</span>
							</div>
						</v-img>
					</v-row>
					<v-divider></v-divider>
					<h2 class="mt-15">신규 강의</h2>
					<v-row>
						<vcards :items="hit"></vcards>
					</v-row>
				</v-container>
			</div>
			<!-- 하단 설명부 -->
			<div id="description" class="mt-15">
				<v-row class="py-15">
					<v-col cols="12">
						<div class="pb-10 pl-16 bold" style="font-size:45px;">
							Run & Go
						</div>
						<v-row class="pb-15">
							<v-col cols="7">
								<v-img
									src="@/assets/index_1.jpg"
									height="320"
									style="cursor: default;"
								/>
							</v-col>
							<v-col cols="5">
								<div
									class="py-10"
									style="font-size:40px; font-weight:200; padding-right:150px;"
								>
									누구나 참여할 수 있는<br />
									<span class="bold">
										자유로운 플랫폼
									</span>
								</div>
								<div
									style=" font-weight:200; padding-right:150px;"
								>
									Run&Go는 누구에게나 성장의 기회를 균등하게
									부여하고자 하는 온라인 학습,
									<span class="bold">
										지식 공유의 참여형 강의 플랫폼
									</span>
									입니다.
								</div>
							</v-col>
						</v-row>
						<v-row class="pb-15">
							<v-col cols="5">
								<div
									class="py-10"
									style="font-size:40px; font-weight:200; padding-left:150px;"
								>
									<span class="bold">집단지성</span>을 통한
									<br />
									체계적인 강의 제작
								</div>
								<div
									style=" font-weight:200; padding-left:150px;"
								>
									<span class="bold">
										이미 생산된 콘텐츠에 대한 참여를 지원
									</span>
									하기 때문에 꼭 전문적인 지식을 가진 사람이지
									않더라도 집단지성을 통해 강의 제작에 참여할
									수 있습니다.
								</div>
							</v-col>
							<v-col cols="7">
								<v-img
									src="@/assets/index_2.jpg"
									height="320"
									style="cursor: default;"
								/>
							</v-col>
						</v-row>
						<v-row class="pb-10">
							<v-col cols="7">
								<v-img
									src="@/assets/index_3.jpg"
									height="320"
									style="cursor: default;"
								/>
							</v-col>
							<v-col cols="5">
								<div
									class="py-10"
									style="font-size:40px; font-weight:200; padding-right:150px;"
								>
									<span class="bold">무료</span> 온라인
									강의제공과 <br />
									<span class="bold">지식의 선순환</span> 기대
								</div>
								<div
									style=" font-weight:200; padding-right:150px;"
								>
									사용자들의 금전적 부담을 줄여주며 누구에게나
									<span class="bold">성장의 기회</span>를
									부여합니다. 또한, 언제든지 강의의 제작자가
									되어
									<span class="bold">
										다른 사용자들에게 자신의 지식을 전달
									</span>
									할 수 있습니다.
								</div>
							</v-col>
						</v-row>
					</v-col>
				</v-row>
			</div>
		</v-app>
	</div>
</template>

<script>
import vcards from '@/components/component/cards';
import { Swiper, SwiperSlide } from 'vue-awesome-swiper';
import 'swiper/css/swiper.css';
export default {
	components: {
		vcards,
		Swiper,
		SwiperSlide,
	},
	data() {
		return {
			swiperOption: {
				spaceBetween: 30,
				centeredSlides: true,
				loop: true,
				autoplay: {
					delay: 2500,
					disableOnInteraction: false,
				},
				keyboard: {
					enabled: true,
				},
				pagination: {
					el: '.swiper-pagination',
					clickable: true,
				},
				navigation: {
					nextEl: '.swiper-button-next',
					prevEl: '.swiper-button-prev',
				},
				lazy: true,
			},
			cycle: true,
			items: [
				{
					title: 'go learn, go run',
					src:
						'https://www.udacity.com/www-proxy/contentful/assets/2y9b3o528xhq/4QTUWa3i4VL7CMc6MjXJe0/556a951cc5ff56725c4f82d0654fe9fc/HomepageBanner_tablet.jpg',
				},
				{
					title: 'go learn, go run222',
					src: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',
				},
				{
					title: 'go learn, go run333',
					src: 'https://cdn.vuetifyjs.com/images/carousel/bird.jpg',
				},
				{
					title: 'go learn, go run444',
					src:
						'https://www.udacity.com/www-proxy/contentful/assets/2y9b3o528xhq/4QTUWa3i4VL7CMc6MjXJe0/556a951cc5ff56725c4f82d0654fe9fc/HomepageBanner_tablet.jpg',
				},
			],
			cards: [
				{
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
				{
					title: 'Python으로 여자친구만들기',
					viewer: 50,
					like: 1000,
					thumbnail:
						'https://cdn.inflearn.com/wp-content/uploads/python-2.jpg',
					tags: ['Python', '연애'],
					nickname: '귀도 반 로썸',
					id: 'JSmaster',
					profile:
						'https://mblogthumb-phinf.pstatic.net/MjAxODEwMTRfMjMw/MDAxNTM5NTI0MDA4NTU1.lqwYXl0waT8to2HT3ZvKZf1MpOZJvtkDj64qC2cfI6sg.xbisRBnM7Q6Kdz-lwNzYiz_lQGPpnPrELpr82NaMIngg.JPEG.rhksdlr134/GuidoAvatar_400x400.jpg?type=w800',
				},
				{
					title: 'C를 마스터해보자',
					viewer: 50,
					like: 1000,
					thumbnail:
						'https://cdn.inflearn.com/wp-content/uploads/c002.jpg',
					tags: ['C'],
					nickname: '토발즈',
					id: 'windows',
					profile:
						'https://upload.wikimedia.org/wikipedia/commons/0/01/LinuxCon_Europe_Linus_Torvalds_03_%28cropped%29.jpg',
				},
				{
					title: '알고리즘 심화 강의',
					viewer: 50,
					like: 1000,
					thumbnail:
						'https://cdn.inflearn.com/public/course-325806-cover/0e0d07a7-840c-4d01-907c-626e9d6b97d5',
					tags: ['알고리즘', '수학', 'Python'],
					nickname: '안성민',
					id: 'algorithmBoy',
					profile:
						'https://post-phinf.pstatic.net/MjAxOTA5MDVfMTU4/MDAxNTY3Njc2NDkyNTUz.yxnxow6Ff5DXIRAHGcOgNUvkV2J_Nh6WttPIi0aEk_kg.rORnLOgHe8wp3_D_UsEGapDaUtlv3JrZvFA6CalCJPEg.JPEG/%EB%AF%B8%EC%B3%A4%EC%8A%B5%EB%8B%88%EA%B9%8C_%ED%9C%B4%EB%A8%BC.jpg?type=w1200',
				},
			],
			hit: [
				{
					title: 'Python으로 여자친구만들기',
					viewer: 50,
					like: 1000,
					thumbnail:
						'https://cdn.inflearn.com/wp-content/uploads/python-2.jpg',
					tags: ['Python', '연애'],
					nickname: '귀도 반 로썸',
					id: 'JSmaster',
					profile:
						'https://mblogthumb-phinf.pstatic.net/MjAxODEwMTRfMjMw/MDAxNTM5NTI0MDA4NTU1.lqwYXl0waT8to2HT3ZvKZf1MpOZJvtkDj64qC2cfI6sg.xbisRBnM7Q6Kdz-lwNzYiz_lQGPpnPrELpr82NaMIngg.JPEG.rhksdlr134/GuidoAvatar_400x400.jpg?type=w800',
				},
				{
					title: 'Python으로 여자친구만들기',
					viewer: 50,
					like: 1000,
					thumbnail:
						'https://cdn.inflearn.com/wp-content/uploads/python-2.jpg',
					tags: ['Python', '연애'],
					nickname: '귀도 반 로썸',
					id: 'JSmaster',
					profile:
						'https://mblogthumb-phinf.pstatic.net/MjAxODEwMTRfMjMw/MDAxNTM5NTI0MDA4NTU1.lqwYXl0waT8to2HT3ZvKZf1MpOZJvtkDj64qC2cfI6sg.xbisRBnM7Q6Kdz-lwNzYiz_lQGPpnPrELpr82NaMIngg.JPEG.rhksdlr134/GuidoAvatar_400x400.jpg?type=w800',
				},
				{
					title: 'Python으로 여자친구만들기',
					viewer: 50,
					like: 1000,
					thumbnail:
						'https://cdn.inflearn.com/wp-content/uploads/python-2.jpg',
					tags: ['Python', '연애'],
					nickname: '귀도 반 로썸',
					id: 'JSmaster',
					profile:
						'https://mblogthumb-phinf.pstatic.net/MjAxODEwMTRfMjMw/MDAxNTM5NTI0MDA4NTU1.lqwYXl0waT8to2HT3ZvKZf1MpOZJvtkDj64qC2cfI6sg.xbisRBnM7Q6Kdz-lwNzYiz_lQGPpnPrELpr82NaMIngg.JPEG.rhksdlr134/GuidoAvatar_400x400.jpg?type=w800',
				},
				{
					title: 'Python으로 여자친구만들기',
					viewer: 50,
					like: 1000,
					thumbnail:
						'https://cdn.inflearn.com/wp-content/uploads/python-2.jpg',
					tags: ['Python', '연애'],
					nickname: '귀도 반 로썸',
					id: 'JSmaster',
					profile:
						'https://mblogthumb-phinf.pstatic.net/MjAxODEwMTRfMjMw/MDAxNTM5NTI0MDA4NTU1.lqwYXl0waT8to2HT3ZvKZf1MpOZJvtkDj64qC2cfI6sg.xbisRBnM7Q6Kdz-lwNzYiz_lQGPpnPrELpr82NaMIngg.JPEG.rhksdlr134/GuidoAvatar_400x400.jpg?type=w800',
				},
			],
			search: [
				'C',
				'C++',
				'JAVA',
				'Python',
				'Javascript',
				'CSS',
				'HTML',
				'Spring',
				'Django',
				'Algorithm',
				'MySQL',
				'NoSQL',
				'C1',
				'C++1',
				'JAVA1',
				'Python1',
				'Javascript1',
				'CSS1',
				'HTML1',
				'Spring1',
				'Django1',
				'Algorithm1',
				'MySQL1',
				'NoSQL1',
			],
			banner: [
				{
					thumbnail:
						'https://www.educative.io/api/page/5393602882568192/image/download/6038586442907648',
					title: `트리플 교수님의<br />폭풍 C++강의`,
					text: `<p>당신이 지금까지 찾을 수 없었던 Javascript강의, <br />
							15만명의 선택으로 만들어진 최대규모 강의
							프로젝트, <br />
							20년도 Run&Go 최우수 강의 선정, <br />
							지금 바로 여기서 확인하세요!
							</p>`,
					zoom: 3000,
				},
				{
					thumbnail:
						'https://cdn.inflearn.com/public/course-324235-cover/12a6aceb-1c38-4ce1-b50c-ab9d32e43edd',
					title: `트리플 교수님의<br />열혈 Javascript강의`,
					text: `<p>당신이 지금까지 찾을 수 없었던 Javascript강의, <br />
							15만명의 선택으로 만들어진 최대규모 강의
							프로젝트, <br />
							20년도 Run&Go 최우수 강의 선정, <br />
							지금 바로 여기서 확인하세요!
							</p>`,
					zoom: 2000,
				},
				{
					thumbnail:
						'https://cdn.inflearn.com/wp-content/uploads/c002.jpg',
					title: `트리플 교수님의<br />열혈 Javascript강의`,
					text: `<p>당신이 지금까지 찾을 수 없었던 Javascript강의, <br />
							15만명의 선택으로 만들어진 최대규모 강의
							프로젝트, <br />
							20년도 Run&Go 최우수 강의 선정, <br />
							지금 바로 여기서 확인하세요!
							</p>`,
					zoom: 2000,
				},
				{
					thumbnail:
						'https://cdn.class101.net/images/d8641423-a054-4622-af7b-b0582742fb28/1440xauto.webp',
					title: `트리플 교수님의<br />열혈 Javascript강의`,
					text: `<p>당신이 지금까지 찾을 수 없었던 Javascript강의, <br />
							15만명의 선택으로 만들어진 최대규모 강의
							프로젝트, <br />
							20년도 Run&Go 최우수 강의 선정, <br />
							지금 바로 여기서 확인하세요!
							</p>`,
					zoom: 3000,
				},
			],
		};
	},
	methods: {
		// goToSearch(query) {
		// 	//검색페이지 이동
		// 	console.log(query);
		// },
		goToSearch() {},
		changeReview(num) {
			const len = this.review.length;
			this.reviewIdx = (this.reviewIdx + num + len) % len;
		},
	},
	filters: {
		setNumLength(val) {
			if (val.toString().length == 1) {
				return '0' + val;
			} else {
				return val;
			}
		},
	},
	mounted() {},
};
</script>

<style scoped>
@import url(//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSans-kr.css);

.container {
	max-width: 1440px;
}

.card-col {
	padding: 2%;
}

.v-image:hover {
	cursor: pointer;
}

.link:hover {
	color: purple;
	cursor: pointer;
}

.v-btn--contained {
	box-shadow: 0px 1px 5px 1px rgba(0, 0, 0, 0.1);
}

.v-btn--contained:hover {
	font-weight: 700;
}

.v-responsive__content:after {
	transform: scale(3) !important;
}

.swiper {
	height: 500px;
	width: 100%;
	color: white;
}

.swiper-img {
	border-radius: 10px;
	max-width: 700px;
}

.slide {
	background-position: center;

	line-height: 1.32;
}

.slide:after {
	content: '';
	position: absolute;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	background-color: rgba(1, 1, 1, 0.3);
	display: block;
	z-index: 1;
}

.info-upper {
	position: absolute;
	z-index: 2;
	left: 50%;
	transform: translateX(-50%);
}

.title {
	font-size: 35px !important;
	font-weight: bold;
	line-height: 1.32;
}

.subtitle {
	font-size: 20px;
}

.text {
	max-width: 430px;
	min-height: 200px;
	line-height: 1.7;
	color: #ededed;
}

#description {
	background-color: #f5f7f8;
	font-family: 'Lato', 'Spoqa Han Sans';
}
</style>
