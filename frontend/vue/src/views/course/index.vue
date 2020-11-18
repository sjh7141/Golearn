<template>
	<div>
		<course-banner :course="info" :src="info.cos_banner" :ldm_no="ldm_no" />
		<v-layout
			wrap
			style="width:1080px; margin:0 auto; min-height:800px;"
			ref="contain"
		>
			<v-flex md3 lg3 xl3>
				<v-card flat tile class="mt-10" style="font-family: 'BMJUA';">
					<v-list-item>
						<v-list-item-subtitle
							style="font-size:16px; color:black; font-weight:400;"
							@click="
								move(
									`/course/${no}/intro${
										ldm_no ? '?ldm_no=' + ldm_no : ''
									}`,
									0,
									-1,
								)
							"
						>
							소개
						</v-list-item-subtitle>
					</v-list-item>
					<v-list-item>
						<v-list-item-subtitle
							style="font-size:16px; color:black; font-weight:400;"
							@click="
								move(
									`/course/${no}/requestlist${
										ldm_no ? '?ldm_no=' + ldm_no : ''
									}`,
									1,
									-1,
								)
							"
						>
							요청 목록
						</v-list-item-subtitle>
					</v-list-item>
					<v-list-item>
						<v-list-item-subtitle
							style="font-size:16px; color:black; font-weight:400;"
						>
							목차
						</v-list-item-subtitle>
					</v-list-item>

					<v-list-item
						class="pl-6"
						v-for="(item, i) in chapters"
						:key="`chapters_${i}`"
						@click="
							move(
								`/course/${no}/play?chapter=${item.idx_no}${
									ldm_no ? '&ldm_no=' + ldm_no : ''
								}`,
								3,
								item.idx_no,
							)
						"
					>
						<v-list-item-subtitle
							style="font-size:14px;"
							:style="{
								color:
									chapter == item.idx_no
										? 'rgb(60,60,223)'
										: '',
							}"
						>
							{{ item.idx_title }}
						</v-list-item-subtitle>
					</v-list-item>
				</v-card>
			</v-flex>
			<v-flex
				class="pa-10"
				md9
				lg9
				xl9
				style="border-left:1px solid rgba(0,0,0,0.13)"
			>
				<Introduce :info="info" v-if="index == 0" />
				<RequestList v-else-if="index == 1" @move="move" />
				<Play v-else-if="index == 3" :idx_no="chapter" @move="move" />
			</v-flex>
		</v-layout>
	</div>
</template>

<script>
import { mapActions } from 'vuex';
import Introduce from '@/components/course/Introduce.vue';
import RequestList from '@/components/course/RequestList.vue';
import Play from '@/components/course/Play.vue';
import CourseBanner from '@/components/course/CourseBanner.vue';

export default {
	name: 'Index',
	components: {
		Introduce,
		RequestList,
		Play,
		CourseBanner,
	},
	data() {
		return {
			height: 0,
			chapters: [],
			info: {},
			index: 0,
			no: -1,
			chapter: -1,
			video: -1,
			ldm_no: 0,
		};
	},
	mounted() {
		this.height = this.$refs.contain.clientHeight;
		this.ldm_no = this.$route.query.ldm_no;

		if (this.$route.params.id == 'intro') this.index = 0;
		if (this.$route.params.id == 'requestlist') this.index = 1;
		if (this.$route.params.id == 'request') this.index = 2;
		if (this.$route.params.id == 'play') this.index = 3;
		this.no = this.$route.params.no;
		this.chapter = this.$route.query.chapter;
		this.getCourseInfo(this.no);
		this.getCourseIndexList(this.no);
	},
	methods: {
		...mapActions(['getCourse', 'getCourseIndexs']),

		move(url, index, no) {
			history.pushState(null, null, url);
			this.index = index;
			this.chapter = no;
		},

		getCourseInfo(no) {
			this.getCourse(no).then(({ data }) => {
				this.info = data;
			});
		},
		getCourseIndexList(no) {
			this.getCourseIndexs(no).then(({ data }) => {
				this.chapters = data;
			});
		},
	},
};
</script>

<style scoped>
.v-list-item {
	min-height: 36px;
	cursor: pointer;
}
</style>
