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
												:src="
													`${url}/uploads/avatars/${channel.photoUrl}`
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
						<v-col cols="12" sm="5" md="3" lg="3" v-if="!loading">
							<v-btn
								v-if="
									currentUser &&
										channel._id !== currentUser._id
								"
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
								>{{
									!subscribed ? 'subscribe' : 'subscribed'
								}}</v-btn
							>
							<!-- <template v-else-if="!currentUser" -->
							<v-btn
								v-else-if="showSubBtn"
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
								>{{
									!subscribed ? 'subscribe' : 'subscribed'
								}}</v-btn
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

				<v-container fluid>
					<v-tabs-items v-model="tab" class="transparent">
						<v-tab-item>
							<v-card class="transparent" flat>
								<v-card-title>Uploads</v-card-title>
								<!-- <v-sheet class="mx-auto"> -->
								<v-slide-group
									class="pa-4"
									multiple
									show-arrows
								>
									<v-slide-item
										v-for="(video, i) in loading ? 5 : 1"
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
												:video="videos.data"
												:channel="channel"
											></video-card>
										</v-skeleton-loader>
									</v-slide-item>
								</v-slide-group>
							</v-card>
						</v-tab-item>
						<v-tab-item>
							<v-card class="transparent" flat>
								<v-card-title>Uploads</v-card-title>
								<v-row>
									<v-col
										cols="12"
										sm="6"
										md="4"
										lg="3"
										v-for="(video, i) in loading ? 10 : 1"
										:key="i"
										class="mx-xs-auto"
									>
										<v-skeleton-loader
											type="card-avatar"
											:loading="loading"
										>
											<video-card
												:card="{ maxWidth: 350 }"
												:video="videos.data"
												:channel="videos.data.mbr_no"
											></video-card>
										</v-skeleton-loader>
									</v-col>
								</v-row>
							</v-card>
						</v-tab-item>
					</v-tabs-items>
				</v-container>
			</v-card>
		</div>
	</div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';

import VideoCard from '@/components/component/VideoCard';

export default {
	data: () => ({
		tab: null,
		loading: false,
		errored: false,
		subscribed: false,
		subscribeLoading: false,
		showSubBtn: true,
		url: 'k3a402.p.ssafy.io',
		items: [
			'Home',
			'Videos',
			'Playlists',
			'Community',
			'Channels',
			'about',
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
	}),
	computed: {
		...mapGetters(['isAuthenticated']),
	},
	components: {
		VideoCard,
	},
	methods: {
		...mapActions(['getVideos']),
		async getChannel() {
			// console.log(this.$route.params.id)
			this.loading = true;
			this.errored = false;

			this.getVideos().then(res => {
				this.videos = res;
				console.log(this.videos);
			});

			// console.log(channel)
			this.loading = false;
		},
		subscribe() {
			console.log('구독');
		},
	},
	mounted() {
		// this.getChannel(this.$route.params.id);
		this.getChannel();
	},
	beforeRouteUpdate(to, from, next) {
		this.getChannel();
		next();
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
</style>
