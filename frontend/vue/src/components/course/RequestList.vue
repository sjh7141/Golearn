<template>
	<div>
		<v-layout wrap>
			<h3 class="my-auto">요청 목록</h3>
			<v-spacer />
			<v-btn
				dark
				depressed
				color="#9382D7"
				@click="
					$router.push(
						`/request?cos_no=${no}${
							ldm_no ? '&ldm_no=' + ldm_no : ''
						}`,
					)
				"
			>
				<span style="font-size:14px; font-weight:400">
					기여하기
				</span>
			</v-btn>
		</v-layout>
		<v-divider class="mt-2 mb-5" />
		<v-row
			v-for="(item, i) in requestList"
			:key="`requestlist_${i}`"
			class="pa-0 ma-0 mb-3"
			style="cursor:pointer"
			@click="
				$router.push(
					`/request/${item.vid_req_no}${
						ldm_no ? '?ldm_no=' + ldm_no : ''
					}`,
				)
			"
			v-show="item.vid_req_accept_yn == 0"
		>
			<v-col :cols="4" class="py-0">
				<v-img
					:src="item.info.video.vid_thumbnail"
					v-if="item.info.video"
					max-width="100%"
				/>
			</v-col>
			<v-col :cols="8" class="py-0 px-1" style="position: relative;">
				<v-list-item-title style="font-weight:700; font-size:16px;">
					{{ item.chapter.idx_title }}
				</v-list-item-title>
				<v-list-item-subtitle style="font-size:14px;">
					{{ item.info.video ? item.info.video.vid_title : '' }}
				</v-list-item-subtitle>
				<v-list-item-subtitle style="font-size:14px;">
					{{ item.vid_req_comment }}
				</v-list-item-subtitle>
				<v-list-item-subtitle
					style="font-size:14px;position: absolute; bottom:0"
				>
					<v-avatar size="20">
						<v-img
							:src="item.info.author.mbr_profile"
							v-if="item.info.author"
						/>
					</v-avatar>
					<span style="font-size:12px;">
						{{
							item.info.author
								? item.info.author.mbr_nick_name
								: ''
						}}
					</span>
				</v-list-item-subtitle>
			</v-col>
		</v-row>
	</div>
</template>

<script>
import { mapActions } from 'vuex';
export default {
	name: 'RequestList',
	data() {
		return {
			tabs: 0,
			requestList: [],
			no: -1,
			ldm_no: 0,
		};
	},
	mounted() {
		this.no = this.$route.params.no;
		this.ldm_no = this.$route.query.ldm_no;
		this.initRequestList();
	},
	methods: {
		...mapActions(['getRequestList', 'getVideoDetail', 'getChapterDetail']),
		initRequestList() {
			this.getRequestList({
				cos_no: this.no,
				page: 1,
			}).then(({ data }) => {
				for (let i in data) {
					data[i].info = {};
					data[i].chapter = {};
				}
				this.requestList = data;

				for (let i in this.requestList) {
					this.getVideoInfo(this.requestList[i]);
					this.getChapterInfo(this.requestList[i]);
				}
			});
		},
		getVideoInfo(item) {
			this.getVideoDetail(item.vid_no).then(({ data }) => {
				item.info = data;
			});
		},
		getChapterInfo(item) {
			this.getChapterDetail(item.idx_no).then(({ data }) => {
				item.chapter = data;
			});
		},
	},
};
</script>

<style scoped>
.v-tab,
.v-btn {
	text-transform: none;
}
</style>
