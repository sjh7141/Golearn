<template>
	<div ref="app">
		<v-hover v-slot="{ hover }">
			<v-img height="230" :src="channel.banner">
				<v-fade-transition>
					<v-btn
						v-if="hover && user.no === channel.no"
						class="d-flex transition-fast-in-fast-out darken-2 v-card--reveal display-3 white--text"
						style="height: 100%; background-color:rgba(0,0,0,0.5); width:100%"
						@click="onButtonClick"
					>
						<v-icon class="mx-auto" color="#F6F6F6" x-large
							>mdi-camera</v-icon
						>
						<input
							ref="uploader"
							class="d-none"
							type="file"
							accept="image/*"
							@change="onFileChanged"
						/>
					</v-btn>
				</v-fade-transition>
			</v-img>
		</v-hover>

		<div class="nav-bgcolor">
			<div id="channel-header" class="container">
				<v-row class="justify-space-between ">
					<v-col cols="12" sm="5" md="5" lg="5">
						<v-skeleton-loader
							type="list-item-avatar-two-line"
							:loading="loading"
							class="mr-1"
						>
							<v-card class="transparent" flat>
								<v-list-item three-line class="px-0">
									<v-list-item-avatar size="80">
										<v-img
											v-if="
												channel.profile !==
													'no-photo.jpg'
											"
											:src="channel.profile"
										></v-img>

										<v-avatar v-else color="red" size="60">
											<span class="white--text headline ">
												{{
													channel.nickname
														.split('')[0]
														.toUpperCase()
												}}</span
											>
										</v-avatar>
									</v-list-item-avatar>
									<v-list-item-content
										class="align-self-auto"
									>
										<v-list-item-title
											class="headline mb-1"
											>{{
												channel.nickname
											}}</v-list-item-title
										>
										<v-list-item-subtitle
											>구독자
											{{
												channel.subscribe_count
													| viewFormatter
											}}
											명
										</v-list-item-subtitle>
									</v-list-item-content>
								</v-list-item>
							</v-card>
						</v-skeleton-loader>
					</v-col>
					<v-col cols="12" sm="5" md="2" lg="2" v-if="!loading">
						<v-btn
							v-if="isLogin == 1 && channel.no !== user.no"
							:class="[
								{ 'red white--text': !subscribed },
								{
									'grey grey--text lighten-3 text--darken-3': subscribed,
								},
								'mt-6',
							]"
							style="float:right"
							tile
							large
							depressed
							:loading="subscribeLoading"
							@click="subscribe"
							><v-icon v-if="!subscribed"
								>mdi-thumb-up-outline</v-icon
							><v-icon v-else>mdi-thumb-up</v-icon>
							{{ !subscribed ? '구독' : '구독중' }}</v-btn
						>
						<v-btn
							v-else-if="isLogin != 1"
							:class="[
								{ 'red white--text': !subscribed },
								{
									'grey grey--text lighten-3 text--darken-3': subscribed,
								},
								'mt-6',
							]"
							tile
							large
							style="float:right"
							depressed
							:loading="subscribeLoading"
							@click="subscribe"
							><v-icon v-if="!subscribed"
								>mdi-thumb-up-outline</v-icon
							><v-icon v-else>mdi-thumb-up</v-icon>
							{{ !subscribed ? '구독' : '구독중' }}</v-btn
						>
					</v-col>
				</v-row>
			</div>
			<v-card flat class="transparent mx-auto">
				<v-tabs
					v-model="tab"
					background-color="transparent"
					show-arrows
					center-active
					class="container"
				>
					<v-tab v-for="(item, i) in items" :key="i">
						{{ item }}
					</v-tab>
				</v-tabs>

				<v-container class="mx-auto px-0">
					<v-tabs-items v-model="tab" class="transparent ">
						<v-tab-item>
							<video-slider> </video-slider>
							<course-slider></course-slider>
							<loadmap-slider></loadmap-slider>
						</v-tab-item>
						<v-tab-item>
							<video-list :channel="channel"> </video-list>
						</v-tab-item>
						<v-tab-item>
							<course-list></course-list>
						</v-tab-item>
						<v-tab-item>
							<loadmap-list></loadmap-list>
						</v-tab-item>
						<v-tab-item>
							<v-container class="information ma-1">
								<v-row>
									<v-col cols="9">
										<v-card-title>
											설명
										</v-card-title>

										<v-card-text>
											고런의 모두가 훌륭한 개발자가
											될때까지 저의 업로드는 멈추지
											않습니다.
										</v-card-text>
										<v-divider></v-divider>
									</v-col>

									<v-col cols="3" class="rigth-container">
										<v-row>
											<v-col>
												통계
											</v-col>
										</v-row>
										<v-divider></v-divider>
										<v-row>
											<v-col>
												가입일 :
												{{
													channel.reg_date
														| dateFormatter
												}}
											</v-col>
										</v-row>
										<v-divider></v-divider>
										<v-row>
											<v-col>
												조회수 :
												{{ 12351235 | viewFormater }}
												회
											</v-col>
										</v-row>
									</v-col>
								</v-row>
							</v-container>
						</v-tab-item>
					</v-tabs-items>
				</v-container>
			</v-card>
		</div>
	</div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
import VideoSlider from '@/components/component/VideoSlider';
import VideoList from '@/components/component/VideoList';
import CourseSlider from '@/components/component/CourseSlider';
import CourseList from '@/components/component/CourseList';
import LoadmapSlider from '@/components/component/LoadmapSlider';
import LoadmapList from '@/components/component/LoadmapList';
import moment from 'moment';
// import VideoCard from '@/components/component/VideoCard';
export default {
	data: () => ({
		tab: null,
		loading: false,
		errored: false,
		subscribed: false,
		subscribeLoading: false,
		showSubBtn: true,
		items: ['홈', '동영상', '코스', '로드맵', '정보'],
		channel: {},
		details: {},
	}),
	computed: {
		...mapGetters(['isLogin', 'user']),
	},
	components: {
		// VideoCard,

		VideoSlider,
		VideoList,
		CourseSlider,
		CourseList,
		LoadmapSlider,
		LoadmapList,
	},
	methods: {
		...mapActions(['getMember', 'isLike', 'upload', 'setBanner']),
		subscribe() {
			if (!this.isLogin) {
				this.$router.push('/login');
			}
		},
		modify() {},
		async getChannel(id) {
			this.getMember(id).then(res => {
				this.channel = res.data;
			});
			this.isLike(id).then(res => {
				this.subscribed = res.data;
			});
		},
		onButtonClick() {
			this.isSelecting = true;
			window.addEventListener(
				'focus',
				() => {
					this.isSelecting = false;
				},
				{ once: true },
			);

			this.$refs.uploader.click();
		},
		onFileChanged(e) {
			const self = this;
			let payload = {};
			let imageFile = e.target.files[0];
			let formData = new FormData();
			formData.append('file', imageFile);
			payload.target = 'member/banner';
			payload.data = formData;
			this.upload(payload).then(res => {
				self.setBanner({ banner: res.data }).then(function() {
					self.channel.banner = res.data;
				});
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
	filters: {
		subscriberFormatter(value) {
			if (value >= 1000000) {
				return '' + parseInt(value / 1000000) + '백만';
			} else if (value >= 10000) {
				return '' + parseInt(value / 10000) + '만';
			} else if (value >= 1000) {
				return '' + parseInt(value / 1000) + '천';
			} else return value;
		},
		dateFormatter(date) {
			return moment(date).format('YYYY.MM.DD');
		},
		viewFormater(value) {
			return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
		},
	},
};
</script>

<style scoped>
@import url(//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSans-kr.css);
.information {
	font-family: 'Lato', 'Spoqa Han Sans';
}
.nav-bgcolor {
	background: #f9f9f9;
}

.card {
	background: #f9f9f9 !important;
}

.v-tab {
	margin-right: 1em;
}

#channel-home .v-list-item--link:before {
	background-color: transparent;
}
.side-margin {
	width: 90%;
}
.rigth-container {
	font-size: 0.87rem;
}
</style>
