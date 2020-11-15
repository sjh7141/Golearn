<template>
	<div>
		<v-layout class="ma-0 pa-0">
			<h3>
				{{ video.vid_title }}
			</h3>
			<v-spacer />
		</v-layout>
		<v-divider class="mb-5" />
		<div>
			<MultiView
				:width="729"
				:height="410"
				:poster="video.vid_thumbnail"
				:src="video.vid_url"
				:title="video.vid_title"
				:source="video.vid_code"
				:type="video.vid_code_type"
			/>
			<div class="mt-5" v-html="video.vid_content" />
		</div>
		<v-layout class="mt-8 mb-1">
			<span>
				<v-avatar size="28" style="" class="mr-1">
					<v-img :src="author.mbr_profile" />
				</v-avatar>
				<a
					href="#"
					style="font-size:14px; text-decoration:none"
					@click="$router.push(`/channel/${author.mbr_no}`)"
				>
					{{ author.mbr_nick_name }}
				</a>
			</span>
			<v-spacer />
			<v-tooltip top>
				<template v-slot:activator="{ on }">
					<v-btn icon v-on="on" @click="toggleLike">
						<v-icon
							size="25"
							:color="liked ? '#9382D7' : 'rgba(0, 0, 0, 0.54)'"
							>{{
								liked ? 'mdi-heart' : 'mdi-heart-outline'
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
							:color="saved ? '#9382D7' : 'rgba(0, 0, 0, 0.54)'"
							>fas fa-external-link-alt</v-icon
						>
					</v-btn>
				</template>
				<span>
					보관함에 추가
				</span>
			</v-tooltip>
			<v-tooltip top>
				<template v-slot:activator="{ on }">
					<v-btn icon v-on="on">
						<v-icon size="25">mdi-source-merge</v-icon>
					</v-btn>
				</template>
				<span>
					기여하기
				</span>
			</v-tooltip>
		</v-layout>
		<v-divider class="mb-5" />

		<v-layout>
			<v-menu offset-y>
				<template v-slot:activator="{ on }">
					<v-btn
						tile
						depressed
						outlined
						width="200"
						color="rgba(0,0,0,0.12)"
						v-on="on"
					>
						<span
							style="position:absolute; left:0; font-size:14px; color:#444; text-overflow: ellipsis; overflow: hidden; white-space: nowrap; width:150px;"
						>
							{{ video.vid_title }}
						</span>
						<v-icon
							size="24"
							right
							style="position:absolute; right:0"
						>
							mdi-chevron-down
						</v-icon>
					</v-btn>
				</template>
				<v-list dense class="pa-0" style="width:200px;">
					<v-list-item
						link
						v-for="(item, i) in versions"
						:key="`version_${i}`"
						@click="vid_no = item.vid_no"
					>
						<span
							style="font-size:14px;text-overflow: ellipsis; overflow: hidden; white-space: nowrap; width:200px; "
						>
							{{ item.vid_title }}
						</span>
					</v-list-item>
				</v-list>
			</v-menu>

			<v-spacer />
			<v-btn
				depressed
				:color="!completed ? '#9382D7' : 'rgba(0,0,0,0.54)'"
				dark
				tile
				style="font-size:14px;"
				@click="
					!completed ? toggleCompleteChapter(cos_no, idx_no) : null
				"
			>
				수강 완료
			</v-btn>
		</v-layout>
		<v-divider class="my-5" />

		<Comment :no="vid_no" />
	</div>
</template>

<script>
import { mapActions } from 'vuex';
import MultiView from '@/components/video/MultiView.vue';
import Comment from '@/components/video/Comment.vue';
export default {
	components: {
		MultiView,
		Comment,
	},
	props: ['idx_no'],
	watch: {
		idx_no() {
			this.getChapterDetail(this.idx_no).then(({ data }) => {
				this.vid_no = data.vid_no;
				this.cos_no = data.cos_no;
				this.checkCompleteChapter(this.idx_no);
				this.getVersionList(this.idx_no);
			});
		},
		vid_no() {
			// console.dir(this.vid_no);
			this.getVideoDetail(this.vid_no).then(({ data }) => {
				// console.dir(data);
				data.video.vid_code_type = ['c_cpp', 'java', 'python'].indexOf(
					data.video.vid_code_type,
				);
				this.author = data.author;
				this.editor = data.editor;
				this.video = data.video;

				this.checkLikeVideo(this.video.vid_no);
				this.checkSaveVideo(this.video.vid_no);
			});
		},
	},
	data() {
		return {
			vid_no: -1,
			cos_no: -1,
			author: {},
			editor: {},
			video: {},
			versions: {},

			liked: false,
			saved: false,
			completed: false,
		};
	},
	mounted() {
		this.getChapterDetail(this.idx_no).then(({ data }) => {
			this.vid_no = data.vid_no;
			this.cos_no = data.cos_no;
			this.checkCompleteChapter(this.idx_no);
			this.getVersionList(this.idx_no);
		});
	},
	methods: {
		...mapActions([
			'getChapterDetail',
			'getVideoDetail',
			'getLikeVideo',
			'likeVideo',
			'cancelVideo',
			'setSaveVideo',
			'removeSaveVideo',
			'getSaveVideo',
			'getCompleteChapter',
			'setCompleteChapter',
			'getVersions',
		]),

		checkLikeVideo(id) {
			this.getLikeVideo(id).then(({ data }) => {
				this.liked = data.isLike;
			});
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

		checkCompleteChapter(id) {
			this.getCompleteChapter(id).then(({ data }) => {
				this.completed = data;
			});
		},

		toggleCompleteChapter(cos_no, idx_no) {
			this.setCompleteChapter({
				cos_no,
				idx_no,
			}).then(() => {
				this.completed = true;
			});
		},

		getVersionList(id) {
			this.getVersions(id).then(({ data }) => {
				this.versions = data;
			});
		},
	},
};
</script>

<style></style>
