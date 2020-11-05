<template>
	<div ref="app">
		<v-parallax
			height="230"
			src="https://cdn.vuetifyjs.com/images/parallax/material.jpg"
		></v-parallax>
		<div class="nav-bgcolor">
			<div id="channel-header">
				<v-container class="py-0">
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
													channel.photoUrl !==
														'no-photo.jpg'
												"
												src="
													https://avatars1.githubusercontent.com/u/25308679?s=460&u=d3601dc80551df28f6c0c1f2da6d9e64e24fae89&v=4
												"
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
														channel.mbr_nickname
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
													channel.mbr_nickname
												}}</v-list-item-title
											>
											<v-list-item-subtitle
												>10k subscribers
											</v-list-item-subtitle>
										</v-list-item-content>
									</v-list-item>
								</v-card>
							</v-skeleton-loader>
						</v-col>
						<v-col cols="12" sm="5" md="2" lg="2" v-if="!loading">
							<v-btn
								v-if="isLogin && channel.mbr_no !== user.mbr_no"
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
								v-else-if="!isLogin"
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
									isLogin && channel.mbr_no === user.mbr_no
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
			<v-card flat class="transparent">
				<v-tabs
					v-model="tab"
					background-color="transparent"
					show-arrows
					centered
					center-active
				>
					<v-tab v-for="(item, i) in items" :key="i">
						{{ item }}
					</v-tab>
				</v-tabs>

				<v-container class="mx-auto">
					<v-tabs-items v-model="tab" class="transparent">
						<v-tab-item>
							<video-slider> </video-slider>
						</v-tab-item>
						<v-tab-item>
							<video-list> </video-list>
						</v-tab-item>
					</v-tabs-items>
				</v-container>
			</v-card>
		</div>
	</div>
</template>

<script>
// import { mapGetters } from 'vuex';
import VideoSlider from '@/components/component/VideoSlider';
import VideoList from '@/components/component/VideoList';
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
		channel: {
			mbr_nickname: 'asm9677',
			_id: 'asdf',
			mbr_no: 3,
		},
		user: {
			mbr_no: 3,
		},
		isLogin: true,
		// currentUser: {
		// 	_id: 'asdf',
		// },
		signinDialog: false,
		details: {},
	}),
	computed: {
		// ...mapGetters(['isLogin']),
	},
	components: {
		// VideoCard,
		VideoSlider,
		VideoList,
	},
	methods: {
		subscribe() {
			if (!this.isLogin) {
				this.$router.push('/login');
			}
			console.log('구독');
		},
		modify() {},
	},
};
</script>

<style>
.nav-bgcolor {
	background: #f9f9f9;
}

.card {
	background: #f9f9f9 !important;
}

.v-tab {
	margin-right: 4em;
}

#channel-home .v-list-item--link:before {
	background-color: transparent;
}
.side-margin {
	width: 90%;
}
</style>
