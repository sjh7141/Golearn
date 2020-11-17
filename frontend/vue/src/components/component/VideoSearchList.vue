<template>
	<div id="csl" ref="app">
		<v-container class="asd content-component">
			<v-row>
				<v-slide-group
					class="my-5 px-5"
					show-arrows
					style="height: 50px;"
				>
					<v-slide-item
						v-for="(tag, i) in categories"
						:key="`tag_${i}`"
					>
						<v-btn
							class="px-1 mx-2"
							active-class="white--text"
							text
							@click="goToSearch(tag)"
							elevation="0"
							style="text-transform:none; color:#8675a9;font-size:13px"
							:class="{
								selected: tags == tag.tag_no,
							}"
						>
							{{ tag.tag_name }}
						</v-btn>
					</v-slide-item>
				</v-slide-group>
			</v-row>

			<v-row>
				<v-col cols="12">
					<swiper class="swiper-container" :options="swiperOption">
						<div
							class="swiper-button-prev  swiper-button"
							slot="button-prev"
						></div>
						<div
							class="swiper-button-next  swiper-button"
							slot="button-next"
						></div>
						<swiper-slide v-for="(channel, i) in channels" :key="i">
							<v-skeleton-loader
								type="card-avatar"
								width="250px"
								class="mr-1"
							>
								<channel-card
									:card="{
										maxWidth: 250,
										type: 'noAvatar',
									}"
									:channel="channel"
								></channel-card>
							</v-skeleton-loader>
						</swiper-slide>
					</swiper>
				</v-col>
			</v-row>
			<v-row>
				<v-col>
					<v-row>
						<v-col
							cols="12"
							sm="6"
							md="4"
							lg="3"
							v-for="(video, i) in videos"
							:key="i"
							class="mx-xs-auto pt-0 pb-6"
						>
							<v-skeleton-loader type="card-avatar">
								<video-card
									:card="{ maxWidth: 400 }"
									:video="video"
								></video-card>
							</v-skeleton-loader>
						</v-col>
					</v-row>
				</v-col>
			</v-row>
		</v-container>
		<infinite-loading
			ref="InfiniteLoading"
			@infinite="infiniteHandler"
		></infinite-loading>
	</div>
</template>

<script>
import { mapActions } from 'vuex';
import VideoCard from './VideoCard2';
import ChannelCard from './ChannelCard';
import InfiniteLoading from 'vue-infinite-loading';
export default {
	data: () => ({
		loading: false,
		tags: '',
		search: '',
		pageNo: 1,
		videos: [],
		channels: [],
		categories: [],
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
	computed: {},
	components: {
		InfiniteLoading,
		VideoCard,
		ChannelCard,
	},
	methods: {
		...mapActions(['getTagList', 'getSearchResult']),
		setTags() {
			this.getTagList().then(res => {
				this.categories = res.data;
			});
		},
		infiniteHandler($state) {
			const payload = {
				search: this.search ? this.search : '',
				page_no: this.pageNo,
				type: 'video',
				tag_no: this.tags,
			};
			this.getSearchResult(payload).then(res => {
				if (res.data.video.length > 0) {
					this.videos.push(...res.data.video);
					$state.loaded();
					this.pageNo += 1;
				} else {
					$state.complete();
				}
			});
		},

		searchChannel() {
			this.channels = [];
			const payload = {
				search: this.search ? this.search : '',
				page_no: 1,
				tag_no: this.tags,
				type: 'channel',
			};

			this.getSearchResult(payload).then(res => {
				this.channels = res.data.channel;
			});
		},
		goToSearch(value) {
			if (this.tags == value.tag_no) {
				this.tags = '';
				this.$router.replace({
					path: '/video',
					query: { tag: '', search: this.search },
				});
			} else {
				this.tags = value.tag_no;
				this.$router.replace({
					path: '/video',
					query: { tag: value.tag_no, search: this.search },
				});
			}
		},
	},

	mounted() {
		this.search = this.$route.query.search;
		this.tags = this.$route.query.tag;
		this.searchChannel();
		this.setTags();
	},

	watch: {
		'$route.query.search'() {
			this.search = this.$route.query.search;
			this.tags = this.$route.query.tag;
			this.searchChannel();
			this.videos = [];
			this.pageNo = 1;
			// this.$refs.InfiniteLoading.stateChanger.reset();
		},
		'$route.query.tag'() {
			this.search = this.$route.query.search;
			this.tags = this.$route.query.tag;
			this.videos = [];
			this.pageNo = 1;
			// this.$refs.InfiniteLoading.stateChanger.reset();
		},
	},
};
</script>

<style scoped>
@import url(//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSans-kr.css);
.content-component {
}

.asd {
	max-width: 1220px;
}
.v-btn-toggle {
	flex-direction: column;
	display: block !important;
}
.side-bar {
	position: fixed;
	width: 10.7%;
}
.selected {
	background-color: rgba(31, 179, 215, 0.12);
}

.v-btn-toggle {
	flex-direction: column;
	display: block !important;
}
.side-bar {
	position: fixed;
	width: 10.7%;
}
.selected {
	background-color: #c3aed633;
}
.swiper-button {
	color: #8675a9;
	margin: 0px;
	top: 30%;
}
</style>

<style>
#csl .v-navigation-drawer__border {
	display: none !important;
	background-color: rgba(0, 0, 0, 0) !important;
}
#csl .v-btn__content {
	justify-content: center;
}
</style>
