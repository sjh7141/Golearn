<template>
	<div ref="app">
		<div id="left-panel" class="px-6" :style="{ width: width + 'px' }">
			<multi-view
				:width="width"
				:height="height"
				:source="source"
				:type="type"
				:poster="video.vid_thumbnail"
				:src="video.vid_url"
				v-if="!loading"
			/>

			<v-list-item class="pa-0 ma-0 mb-1">
				<v-list-item-avatar
					size="48"
					@click="$router.push(`/channel/${author.mbr_no}`)"
					style="cursor:pointer;"
				>
					<v-img :src="author.mbr_profile" v-if="!loading" />
				</v-list-item-avatar>
				<v-list-item-content>
					<v-list-item-title class="mt-1" style="font-size:18px;">
						{{ video.vid_title }}
					</v-list-item-title>
					<v-list-item-subtitle class="mt-0">
						<span
							style="font-size:14px;cursor:pointer;"
							@click="$router.push(`/channel/${author.mbr_no}`)"
						>
							{{ author.mbr_nick_name }}
						</span>
					</v-list-item-subtitle>
					<v-list-item-subtitle class="mt-1" style="font-size:13px;">
						<span v-for="(tag, i) in video.tags" :key="`tag_${i}`">
							#{tag}
						</span>
					</v-list-item-subtitle>
				</v-list-item-content>
				<v-list-item-action>
					<v-layout class="mb-1">
						<v-spacer />
						<!-- <span style="font-size:15px; font-weight:500">
							<div>
								<v-icon> mdi-play-outline </v-icon>
								<span
									style="margin: auto; color:#8e8e8e"
									class="mr-2"
									>4,091</span
								>
								<v-icon small class="mr-1">
									mdi-heart-outline
								</v-icon>
								<span style="margin: auto; color:#8e8e8e"
									>25</span
								>
							</div>
						</span> -->

						<v-tooltip top>
							<template v-slot:activator="{ on }">
								<v-btn icon v-on="on" @click="toggleSubscribe">
									<v-icon
										size="25"
										:color="
											subscribed || isMine
												? '#9382D7'
												: 'rgba(0, 0, 0, 0.54)'
										"
										>{{
											subscribed || isMine
												? 'mdi-bell'
												: 'mdi-bell-outline'
										}}</v-icon
									>
								</v-btn>
							</template>
							<span>
								구독하기
							</span>
						</v-tooltip>
						<v-tooltip top>
							<template v-slot:activator="{ on }">
								<v-btn icon v-on="on" @click="toggleLike">
									<v-icon
										size="25"
										:color="
											liked
												? '#9382D7'
												: 'rgba(0, 0, 0, 0.54)'
										"
										>{{
											liked
												? 'mdi-heart'
												: 'mdi-heart-outline'
										}}</v-icon
									>
								</v-btn>
							</template>
							<span>
								좋아요
							</span>
						</v-tooltip>
						<v-tooltip top>
							<template v-slot:activator="{ on }">
								<v-btn
									icon
									v-on="on"
									@click="toggleSaveVideo(video.vid_no)"
								>
									<v-icon
										size="20"
										:color="
											saved
												? '#9382D7'
												: 'rgba(0, 0, 0, 0.54)'
										"
										>fas fa-external-link-alt</v-icon
									>
								</v-btn>
							</template>
							<span>
								보관함에 추가
							</span>
						</v-tooltip>
					</v-layout>
					<v-layout>
						<span
							style="font-size:14px; font-weight:400; color:#606060"
						>
							조회수 {{ `${video.vid_view}` | addComma }}회 •
							{{ video.reg_dt | moment('YYYY. M. D.') }}
						</span>
					</v-layout>
				</v-list-item-action>
			</v-list-item>
			<v-divider />
			<div
				class="my-3"
				style="padding:0px 65px;"
				v-html="video.vid_content"
			/>

			<v-divider class="mb-5" />

			<Comment :no="no" v-if="no" />
		</div>
		<div id="right-panel">
			<Chapter :no="mbr_no" />
		</div>
	</div>
</template>

<script>
import { mapActions } from 'vuex';
import Chapter from '@/components/video/Chapter.vue';
import MultiView from '@/components/video/MultiView.vue';
import Comment from '@/components/video/Comment.vue';

export default {
	name: 'VideoPlay',
	components: {
		MultiView,
		Chapter,
		Comment,
	},
	data() {
		return {
			totalHeight: 0,
			width: 0,
			height: 0,

			source: '',
			type: -1,

			no: 0,
			mbr_no: 0,

			author: [],
			editor: [],
			video: [],

			loading: true,
			subscribed: false,
			isMine: false,
			liked: false,
			saved: false,
		};
	},
	filters: {
		addComma(number) {
			return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
		},
	},
	mounted() {
		this.no = this.$route.query.no;
		if (!this.no) {
			alert('존재하지 않는 영상입니다');
			this.$router.push('/');
		}
		window.addEventListener('resize', this.resizePlay);
		this.resizePlay();

		this.source = '#include <stdio.h>';
		this.type = 0;

		this.getVideoDetail(this.no).then(({ data }) => {
			// console.dir(data);
			this.loading = false;
			this.author = data.author;
			this.editor = data.editor;
			this.video = data.video;
			this.mbr_no = data.author.mbr_no;
			this.getSubscribe(this.mbr_no);
			this.checkLikeVideo(this.video.vid_no);
			this.isMine = this.mbr_no == this.$store.getters.user.no;
			this.checkSaveVideo(this.video.vid_no);
		});
	},
	beforeDestroy() {
		window.removeEventListener('resize', this.resizePlay);
	},
	methods: {
		...mapActions([
			'getVideoDetail',
			'isLike',
			'getLikeVideo',
			'likeVideo',
			'cancelVideo',
			'setSubscribed',
			'removeSubscribed',
			'setSaveVideo',
			'removeSaveVideo',
			'getSaveVideo',
		]),

		checkLikeVideo(id) {
			this.getLikeVideo(id).then(({ data }) => {
				this.liked = data.isLike;
			});
		},

		getSubscribe(id) {
			this.isLike(id).then(({ data }) => {
				this.subscribed = data;
			});
		},

		toggleSubscribe() {
			if (this.isMine) return;
			if (this.subscribed) {
				this.removeSubscribed(this.mbr_no).then(() => {
					this.subscribed = !this.subscribed;
				});
			} else {
				this.setSubscribed(this.mbr_no).then(() => {
					this.subscribed = !this.subscribed;
				});
			}
		},

		toggleLike() {
			if (this.liked) {
				this.cancelVideo(this.video.vid_no).then(() => {
					this.liked = !this.liked;
				});
			} else {
				this.likeVideo(this.video.vid_no).then(() => {
					this.liked = !this.liked;
				});
			}
		},

		checkSaveVideo(id) {
			this.getSaveVideo(id).then(({ data }) => {
				this.saved = data;
			});
		},

		toggleSaveVideo(id) {
			if (this.saved) {
				this.removeSaveVideo(id).then(() => {
					this.saved = false;
				});
			} else {
				this.setSaveVideo({
					vid_no: id,
				}).then(() => {
					this.saved = true;
				});
			}
		},

		resizePlay() {
			if (window.innerWidth < 960) {
				this.width = window.innerWidth;
			} else {
				this.width = window.innerWidth - 450;
			}
			this.height = (this.width * 9) / 16;
			this.totalHeight = window.innerHeight - this.$refs.app.offsetTop;
		},
	},
};
</script>

<style scoped>
#left-panel {
	float: left;
}
#right-panel {
	float: right;
}
</style>
