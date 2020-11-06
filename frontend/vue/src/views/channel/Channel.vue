<template>
	<div ref="app">
		<v-parallax height="230" :src="channel.banner"></v-parallax>
		<div class="nav-bgcolor">
			<div id="channel-header">
				<v-container fluid class="py-0">
					<v-row class="justify-space-between">
						<v-col cols="12" sm="5" md="5" lg="5" offset-md="1">
							<v-skeleton-loader
								type="list-item-avatar-two-line"
								:loading="loading"
								class="mr-1"
							>
								<v-card class="transparent" flat>
									<v-list-item three-line>
										<v-list-item-avatar size="80">
											<v-img
												v-if="
													channel.profile !==
														'no-photo.jpg'
												"
												:src="channel.profile"
											></v-img>

											<v-avatar
												v-else
												color="red"
												size="60"
											>
												<span
													class="white--text headline "
												>
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
							<!-- <template v-else-if="!currentUser" -->
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
								depressed
								:loading="subscribeLoading"
								@click="subscribe"
								><v-icon v-if="!subscribed"
									>mdi-thumb-up-outline</v-icon
								><v-icon v-else>mdi-thumb-up</v-icon>
								{{ !subscribed ? '구독' : '구독중' }}</v-btn
							>
							<v-btn
								v-else-if="
									isLogin == 1 && channel.no == user.no
								"
								class="blue white--text mt-6"
								tile
								large
								depressed
								@click="modify"
								><v-icon>mdi-wrench</v-icon>설정</v-btn
							>
							<!-- <v-btn icon class="ml-5 mt-6"><v-icon>mdi-bell</v-icon></v-btn> -->
						</v-col>
					</v-row>
				</v-container>
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

				<v-container class="mx-auto">
					<v-tabs-items v-model="tab" class="transparent">
						<v-tab-item>
							<video-slider> </video-slider>
							<course-slider></course-slider>
							<loadmap-slider></loadmap-slider>
						</v-tab-item>
						<v-tab-item>
							<video-list> </video-list>
						</v-tab-item>
						<v-tab-item>
							<course-list></course-list>
						</v-tab-item>
						<v-tab-item>
							<loadmap-list></loadmap-list>
						</v-tab-item>
						<v-tab-item>
							<v-container>
								<v-row>
									<v-col cols="8">
										설명
										<v-divider></v-divider>
									</v-col>

									<v-col cols="4">
										<v-row>
											통계
										</v-row>
										<v-divider></v-divider>
										<v-row>
											가입일 :
											{{
												channel.reg_date | dateFormatter
											}}
										</v-row>
										<v-divider></v-divider>
										<v-row>
											조회수 :
											{{ 12351235 | viewFormmater }}
											회
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
		...mapActions(['getMember', 'isLike']),
		subscribe() {
			if (!this.isLogin) {
				this.$router.push('/login');
			}
			console.log('구독');
		},
		modify() {},
		async getChannel(id) {
			this.getMember(id).then(res => {
				this.channel = res.data;
				console.log(this.channel);
				console.log(this.user);
			});
			this.isLike(id).then(res => {
				this.subscribed = res.data;
			});
			console.log(this.isLogin == 0);
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
		viewFormmater(value) {
			return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
		},
	},
};
</script>

<style scoped>
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
</style>
