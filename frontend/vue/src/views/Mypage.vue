<template>
	<v-container fluid class="wrapper py-0">
		<v-row>
			<v-img
				src="@/assets/golearn_banner.jpg"
				height="200"
				style="filter: grayscale(50%);"
				ref="img"
			>
				<v-col style="height:100%;">
					<div class="banner-wrapper">
						<div id="banner-text">
							회원 정보
						</div>
					</div>
				</v-col>
			</v-img>
		</v-row>
		<v-row class="height-100 pt-15 mb-15 media-margin">
			<v-col cols="3" md="3" lg="2">
				<v-row class="pl-15">
					<v-col
						cols="12"
						v-for="(item, idx) in tap"
						:key="idx"
						:class="{ 'ml-5': item.child }"
					>
						<span
							class="tap-font"
							:class="{ select: idx == selectNo }"
							@click="changeTap(idx)"
						>
							{{ item.name }}
						</span>
					</v-col>
				</v-row>
			</v-col>
			<v-col cols="9" md="9" lg="10">
				<v-row>
					<v-col cols="12" class="pr-15">
						<profile v-if="selectNo == 0"></profile>
						<like
							v-if="selectNo == 1"
							@setNo="changeTap"
							:videos="likeVideos"
							:courses="likeCourses"
						></like>
						<like-video
							v-if="selectNo == 2"
							:videos="likeVideos"
						></like-video>
						<like-course
							v-if="selectNo == 3"
							:courses="likeCourses"
						></like-course>
						<learn-course
							v-if="selectNo == 4"
							:courses="learnCourse"
						></learn-course>
					</v-col>
				</v-row>
			</v-col>
		</v-row>
	</v-container>
</template>

<script>
import Profile from '@/components/mypage/ProfileForm.vue';
import Like from '@/components/mypage/Like.vue';
import LikeVideo from '@/components/mypage/LikeVideo.vue';
import LikeCourse from '@/components/mypage/LikeCourse.vue';
import LearnCourse from '@/components/mypage/LearnCourse.vue';

import { mapGetters } from 'vuex';

export default {
	components: {
		Profile,
		Like,
		LikeVideo,
		LikeCourse,
		LearnCourse,
	},
	data() {
		return {
			tap: [
				{ name: '프로필', child: false },
				{ name: '내 학습', child: false },
				{ name: '좋아요한 영상', child: false },
				{ name: '좋아요한 코스', child: false },
				{ name: '수강중인 코스', child: false },
			],
			selectNo: 0,
			likeVideos: [
				{
					vidNo: 1,
					vid_no: 12,
					mbr_no: 1,
					vid_pno: 0,
					vid_title: '안드로이드 심화',
					vid_content: '123',
					vid_url: null,
					vid_view: 123123,
					reg_dt: '2020-11-03T17:29:48.000+00:00',
					vid_hide: false,
					vid_thumbnail:
						'https://i.ytimg.com/vi/oj6DI3PvAr0/hq720.jpg?sqp=-oaymwEZCOgCEMoBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLA5V2z7wEebOH_aOcah6Lu9-VuL2A',
					vid_length: 0,
					tags: [],
					vid_likes: 0,
				},
				{
					vidNo: 2,
					vid_no: 13,
					mbr_no: 1,
					vid_pno: 0,
					vid_title: '코틀린 빠르게 끝내기',
					vid_content: '1',
					vid_url: null,
					vid_view: 1,
					reg_dt: '2020-11-03T17:29:48.000+00:00',
					vid_hide: false,
					vid_thumbnail:
						'https://i.ytimg.com/vi/8gseVzeMOzk/hq720.jpg?sqp=-oaymwEZCOgCEMoBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLCcpqL5kSejJMbNt2cwyChQvjF4LQ',
					vid_length: 0,
					tags: [],
					vid_likes: 0,
				},
				{
					vidNo: 3,
					vid_no: 9,
					mbr_no: 1,
					vid_pno: 0,
					vid_title: '나도 인스타 만들수 있다',
					vid_content: '7',
					vid_url:
						'https://storage.googleapis.com/exoplayer-test-media-0/play.mp3',
					vid_view: 1234458,
					reg_dt: '2020-10-29T16:50:17.000+00:00',
					vid_hide: false,
					vid_thumbnail:
						'https://i.ytimg.com/vi/ORI4-EXhgC4/hq720.jpg?sqp=-oaymwEZCOgCEMoBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLD2qF7VI19bs2iyhW36Do2GNURM9w',
					vid_length: 0,
					tags: [],
					vid_likes: 0,
				},
				{
					vidNo: 4,
					vid_no: 10,
					mbr_no: 1,
					vid_pno: 0,
					vid_title: '코딩 뭐부터 배우지?',
					vid_content: '8',
					vid_url: null,
					vid_view: 2341,
					reg_dt: '2020-10-29T16:50:17.000+00:00',
					vid_hide: false,
					vid_thumbnail:
						'https://i.ytimg.com/vi/wpUiN5hBnyc/hq720.jpg?sqp=-oaymwEZCOgCEMoBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLBOID4q2UyrRNPrhF-ks8lnzqqztQ',
					vid_length: 0,
					tags: [],
					vid_likes: 0,
				},
				{
					vidNo: 5,
					vid_no: 11,
					mbr_no: 1,
					vid_pno: 0,
					vid_title: '안드로이드 강좌',
					vid_content: '9',
					vid_url: null,
					vid_view: 211112,
					reg_dt: '2020-10-29T16:50:17.000+00:00',
					vid_hide: false,
					vid_thumbnail:
						'https://i.ytimg.com/vi/oj6DI3PvAr0/hq720.jpg?sqp=-oaymwEZCOgCEMoBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLA5V2z7wEebOH_aOcah6Lu9-VuL2A',
					vid_length: 0,
					tags: [],
					vid_likes: 0,
				},
				{
					vidNo: 6,
					vid_no: 4,
					mbr_no: 1,
					vid_pno: 0,
					vid_title: 'C언어 기초',
					vid_content: '2',
					vid_url:
						'https://storage.googleapis.com/exoplayer-test-media-0/play.mp3',
					vid_view: 931310,
					reg_dt: '2020-10-27T16:06:54.000+00:00',
					vid_hide: false,
					vid_thumbnail:
						'https://i.ytimg.com/vi/q6fPjQAzll8/hq720.jpg?sqp=-oaymwEZCOgCEMoBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLA612qZjfkXe2D10MLtMdnEDy9img',
					vid_length: 600,
					tags: [
						{
							tag_no: 2,
							tag_name: 'JAVA',
						},
						{
							tag_no: 3,
							tag_name: 'C++',
						},
					],
					vid_likes: 0,
				},
				{
					vidNo: 1,
					vid_no: 12,
					mbr_no: 1,
					vid_pno: 0,
					vid_title: '안드로이드 심화',
					vid_content: '123',
					vid_url: null,
					vid_view: 123123,
					reg_dt: '2020-11-03T17:29:48.000+00:00',
					vid_hide: false,
					vid_thumbnail:
						'https://i.ytimg.com/vi/oj6DI3PvAr0/hq720.jpg?sqp=-oaymwEZCOgCEMoBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLA5V2z7wEebOH_aOcah6Lu9-VuL2A',
					vid_length: 0,
					tags: [],
					vid_likes: 0,
				},
				{
					vidNo: 2,
					vid_no: 13,
					mbr_no: 1,
					vid_pno: 0,
					vid_title: '코틀린 빠르게 끝내기',
					vid_content: '1',
					vid_url: null,
					vid_view: 1,
					reg_dt: '2020-11-03T17:29:48.000+00:00',
					vid_hide: false,
					vid_thumbnail:
						'https://i.ytimg.com/vi/8gseVzeMOzk/hq720.jpg?sqp=-oaymwEZCOgCEMoBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLCcpqL5kSejJMbNt2cwyChQvjF4LQ',
					vid_length: 0,
					tags: [],
					vid_likes: 0,
				},
				{
					vidNo: 5,
					vid_no: 11,
					mbr_no: 1,
					vid_pno: 0,
					vid_title: '안드로이드 강좌',
					vid_content: '9',
					vid_url: null,
					vid_view: 211112,
					reg_dt: '2020-10-29T16:50:17.000+00:00',
					vid_hide: false,
					vid_thumbnail:
						'https://i.ytimg.com/vi/oj6DI3PvAr0/hq720.jpg?sqp=-oaymwEZCOgCEMoBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLA5V2z7wEebOH_aOcah6Lu9-VuL2A',
					vid_length: 0,
					tags: [],
					vid_likes: 0,
				},
				{
					vidNo: 6,
					vid_no: 4,
					mbr_no: 1,
					vid_pno: 0,
					vid_title: 'C언어 기초',
					vid_content: '2',
					vid_url:
						'https://storage.googleapis.com/exoplayer-test-media-0/play.mp3',
					vid_view: 931310,
					reg_dt: '2020-10-27T16:06:54.000+00:00',
					vid_hide: false,
					vid_thumbnail:
						'https://i.ytimg.com/vi/q6fPjQAzll8/hq720.jpg?sqp=-oaymwEZCOgCEMoBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLA612qZjfkXe2D10MLtMdnEDy9img',
					vid_length: 600,
					tags: [
						{
							tag_no: 2,
							tag_name: 'JAVA',
						},
						{
							tag_no: 3,
							tag_name: 'C++',
						},
					],
					vid_likes: 0,
				},
			],
			likeCourses: [],
			learnCourse: [],
		};
	},
	computed: {
		...mapGetters(['user', 'isLogin']),
	},
	methods: {
		changeTap(idx) {
			this.selectNo = idx;
		},
	},
	mounted() {
		this.$store.dispatch('getLikeVideos').then(({ data }) => {
			this.likeVideos = data;
		});
		this.$store.dispatch('getLikeCourses').then(({ data }) => {
			this.likeCourses = data;
		});
		this.$store.dispatch('getLearnCourse').then(({ data }) => {
			this.learnCourse = data;
		});
	},
};
</script>

<style scoped>
.wrapper {
	min-height: 800px;
}
.tap-font {
	font-weight: 700;
	cursor: pointer;
}
.select {
	color: #8059d4;
}
</style>
