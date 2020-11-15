<template>
	<div>
		<v-app id="inspire">
			<!-- 배너 -->
			<swiper class="swiper" :options="swiperOption" ref="swiper">
				<swiper-slide
					class="slide pb-5 pt-9"
					v-for="(item, index) in banner"
					:key="`banner_${index}`"
					:style="{
						'background-image': 'url(' + item.cos_thumbnail + ')',
						'background-size': '10000%',
					}"
				>
					<v-row
						@click="$router.push(`/course/${item.cos_no}/intro`)"
						class="info-upper"
						style="width:85%;"
					>
						<v-col cols="6">
							<img
								class="swiper-img"
								height="400px"
								width="100%"
								:src="item.cos_thumbnail"
							/>
						</v-col>
						<v-col cols="6" class="pl-10">
							<div
								class="title py-5"
								v-html="item.cos_title"
							></div>
							<div
								class="text content"
								v-html="item.cos_content"
							></div>
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
						<v-slide-item
							v-for="(tag, index) in tags"
							:key="`tag_${index}`"
						>
							<v-btn
								class="mx-2 tag"
								active-class="blue white--text"
								color="white"
								large
								rounded
								@click="goToSearch(tag.tag_no)"
								style="text-transform:none;"
							>
								<v-icon color="#c3aed6" left>
									mdi-magnify
								</v-icon>
								{{ tag.tag_name }}
							</v-btn>
						</v-slide-item>
					</v-slide-group>
					<v-divider class="mt-15"></v-divider>
					<h2 class="mt-15">이런 강의 어떠세요?</h2>

					<v-row>
						<v-col
							cols="12"
							sm="6"
							md="6"
							lg="3"
							v-for="(course, i) in courses.slice(0, 4)"
							:key="`course_${i}`"
						>
							<course-card :course="course"> </course-card>
						</v-col>
					</v-row>
					<v-divider class="mt-15"></v-divider>
					<h2 class="mt-15">금주의 인기강의</h2>
					<v-row>
						<v-col
							cols="12"
							sm="6"
							md="6"
							lg="3"
							v-for="(course, i) in courses.slice(4, 8)"
							:key="`course_${i}`"
						>
							<course-card :course="course"> </course-card>
						</v-col>
					</v-row>
					<v-divider class="mt-15"></v-divider>
					<h2 class="mt-15">수강생 급상승 강의</h2>
					<v-row>
						<v-col
							cols="12"
							sm="6"
							md="6"
							lg="3"
							v-for="(course, i) in courses.slice(8, 12)"
							:key="`course_${i}`"
						>
							<course-card :course="course"> </course-card>
						</v-col>
					</v-row>
					<v-row class="mt-15" style="height:220px;">
						<v-img
							:src="require('@/assets/nature.jpg')"
							style="position: absolute; left:0px;"
							height="220"
							width="100%"
							position="50% 75%"
						>
							<div class="py-10 px-15">
								<span
									class="bold"
									style="font-size: 40px; color:#2e2e2e; font-family: 'BMJUA';"
								>
									# 영상참여
								</span>
								<br />
								<span
									class="bold"
									style="color:#2e2e2e; font-family: 'BMJUA';"
								>
									여러분의 작은참여가 교육의 시작을
									만들어냅니다
								</span>
							</div>
						</v-img>
					</v-row>
					<v-divider></v-divider>
					<h2 class="mt-15">신규 강의</h2>
					<v-row>
						<v-col
							cols="12"
							sm="6"
							md="6"
							lg="3"
							v-for="(course, i) in courses.slice(3, 7)"
							:key="`course_${i}`"
						>
							<course-card :course="course"> </course-card>
						</v-col>
					</v-row>
				</v-container>
			</div>
			<!-- 하단 설명부 -->
			<div id="description" class="mt-15">
				<v-row class="py-15">
					<v-col cols="12">
						<div class="pb-10 pl-16 bold" style="font-size:45px;">
							고런고런
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
									고런고런은 누구에게나 성장의 기회를 균등하게
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
import { Swiper, SwiperSlide } from 'vue-awesome-swiper';
import CourseCard from '@/components/component/CourseCard2';
import 'swiper/css/swiper.css';
import { mapActions } from 'vuex';
export default {
	components: {
		Swiper,
		SwiperSlide,
		CourseCard,
	},
	data() {
		return {
			swiperOption: {
				spaceBetween: 30,
				centeredSlides: true,
				loop: true,
				courses: [],
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
			tags: [],
			banner: [],
			courses: [],
		};
	},
	methods: {
		...mapActions(['getSearchResult', 'getTagList']),

		goToSearch(value) {
			this.$router.push(`/video?tag=${value}`);
		},
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
	created() {
		const payload = {
			search: '',
			page_no: 1,
			type: 'course',
		};
		this.getSearchResult(payload).then(res => {
			this.banner = res.data.course;
			this.courses = res.data.course;
		});
		this.getTagList().then(res => {
			this.tags = res.data;
		});
	},
};
</script>

<style scoped>
@import url(//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSans-kr.css);
.v-card {
	font-family: 'BMJUA';
}
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
.slide:hover {
	cursor: pointer;
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
.content {
	color: black;
	font-weight: bold;
	font-size: 0.85rem;
	word-break: break-all;
	display: -webkit-box;
	margin-bottom: 0.5rem;
	line-height: 1.2em;
	text-overflow: ellipsis;
	overflow: hidden;
	-webkit-line-clamp: 7;
	-webkit-box-orient: vertical;
}
.tag {
	font-family: 'BMJUA';
}
</style>
