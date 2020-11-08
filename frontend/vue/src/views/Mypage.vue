<template>
	<v-container fluid class="wrapper py-0">
		<v-row>
			<v-img
				src="@/assets/golearn_banner.jpg"
				height="300"
				style="filter: grayscale(50%);"
				ref="img"
			>
				<!-- <v-col style="height:100%;">
					<div class="banner-wrapper">
						<div id="banner-text">
							{{ title == '' ? title_default : title }}
						</div>
					</div>
				</v-col> -->
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
					<v-col cols="12" class="px-10">
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
							:courses="likeCourses"
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
				{ name: '좋아요한 영상', child: true },
				{ name: '좋아요한 코스', child: true },
				{ name: '수강중인 코스', child: true },
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
			likeCourses: [
				{
					cos_no: 1,
					mbr_no: 1,
					cos_title: '안드로이드 완전 끝내기',
					cos_content:
						'asdfasdfasdfasdasdfasdafsdfasfasfsdfasfasdfasdfasdfasdfasdfasfasf',
					cos_thumbnail:
						'https://i.ytimg.com/vi/mvzZMjIQEyo/hq720.jpg?sqp=-oaymwEZCOgCEMoBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLCpu2iRzJXHFa7nCQP4HpGrt-P_1Q',
					cos_banner:
						'https://cdn.pixabay.com/photo/2017/08/30/01/05/milky-way-2695569_960_720.jpg',
					reg_dt: '2020-11-02T21:15:16.000+00:00',
					chg_dt: '2020-10-28T02:15:44.000+00:00',
				},
				{
					cos_no: 25,
					mbr_no: 1,
					cos_title: '자바 완전 끝내기',
					cos_content: 'asdf',
					cos_thumbnail:
						'https://cdn.inflearn.com/public/courses/324509/course_cover/85a80c0a-b297-458a-a4f2-0666e4de13a5/thejava2.png',
					cos_banner: 'course_banner_default.png',
					reg_dt: '2020-11-02T21:15:16.000+00:00',
					chg_dt: '2020-11-02T21:15:16.000+00:00',
				},
				{
					cos_no: 24,
					mbr_no: 1,
					cos_title: 'C언어 마스터하기',
					cos_content: 'asdf',
					cos_thumbnail:
						'https://cdn.inflearn.com/wp-content/uploads/c002.jpg',
					cos_banner: 'course_banner_default.png',
					reg_dt: '2020-11-02T21:14:47.000+00:00',
					chg_dt: '2020-11-02T21:14:47.000+00:00',
				},
				{
					cos_no: 23,
					mbr_no: 1,
					cos_title: '스프링 마스터하기',
					cos_content: 'asdf',
					cos_thumbnail:
						'https://cdn.inflearn.com/wp-content/uploads/springboot-1-2-1.jpg',
					cos_banner: 'course_banner_default.png',
					reg_dt: '2020-11-02T21:14:12.000+00:00',
					chg_dt: '2020-11-02T21:14:12.000+00:00',
				},
				{
					cos_no: 22,
					mbr_no: 1,
					cos_title: 'asdf',
					cos_content: 'asdf',
					cos_thumbnail:
						'https://cdn.pixabay.com/photo/2017/08/30/01/05/milky-way-2695569_960_720.jpg',
					cos_banner: 'course_banner_default.png',
					reg_dt: '2020-11-02T21:13:29.000+00:00',
					chg_dt: '2020-11-02T21:13:29.000+00:00',
				},
				{
					cos_no: 21,
					mbr_no: 1,
					cos_title: 'asdf',
					cos_content: 'asdf',
					cos_thumbnail:
						'https://cdn.pixabay.com/photo/2017/08/30/01/05/milky-way-2695569_960_720.jpg',
					cos_banner: 'course_banner_default.png',
					reg_dt: '2020-11-02T21:12:52.000+00:00',
					chg_dt: '2020-11-02T21:12:52.000+00:00',
				},
				{
					cos_no: 20,
					mbr_no: 1,
					cos_title: 'asdf',
					cos_content: 'asdf',
					cos_thumbnail:
						'https://cdn.pixabay.com/photo/2017/08/30/01/05/milky-way-2695569_960_720.jpg',
					cos_banner: 'course_banner_default.png',
					reg_dt: '2020-11-02T21:11:57.000+00:00',
					chg_dt: '2020-11-02T21:11:57.000+00:00',
				},
				{
					cos_no: 19,
					mbr_no: 1,
					cos_title: 'asdf',
					cos_content: 'asdf',
					cos_thumbnail:
						'https://cdn.pixabay.com/photo/2017/08/30/01/05/milky-way-2695569_960_720.jpg',
					cos_banner: 'course_banner_default.png',
					reg_dt: '2020-11-02T21:08:13.000+00:00',
					chg_dt: '2020-11-02T21:08:13.000+00:00',
				},
				{
					cos_no: 18,
					mbr_no: 1,
					cos_title: 'asdf',
					cos_content: 'asdf',
					cos_thumbnail:
						'https://cdn.pixabay.com/photo/2017/08/30/01/05/milky-way-2695569_960_720.jpg',
					cos_banner: 'course_banner_default.png',
					reg_dt: '2020-11-02T20:55:53.000+00:00',
					chg_dt: '2020-11-02T20:55:53.000+00:00',
				},
				{
					cos_no: 4,
					mbr_no: 1,
					cos_title: 'co4',
					cos_content: 'vcds',
					cos_thumbnail:
						'https://img.freepik.com/free-vector/watercolor-background_87374-69.jpg?size=626&ext=jpg',
					cos_banner: 'course_banner_default.png',
					reg_dt: '2020-11-02T20:16:22.000+00:00',
					chg_dt: '2020-10-27T17:38:43.000+00:00',
				},
				{
					cos_no: 17,
					mbr_no: 1,
					cos_title: 'asdf',
					cos_content: 'asdf',
					cos_thumbnail:
						'https://cdn.pixabay.com/photo/2017/08/30/01/05/milky-way-2695569_960_720.jpg',
					cos_banner: 'course_banner_default.png',
					reg_dt: '2020-10-30T11:07:35.000+00:00',
					chg_dt: '2020-10-30T11:07:35.000+00:00',
				},
				{
					cos_no: 16,
					mbr_no: 1,
					cos_title: 'zzzz',
					cos_content: null,
					cos_thumbnail:
						'https://cdn.pixabay.com/photo/2017/08/30/01/05/milky-way-2695569_960_720.jpg',
					cos_banner: 'course_banner_default.png',
					reg_dt: '2020-10-28T01:41:31.000+00:00',
					chg_dt: '2020-10-28T01:41:31.000+00:00',
				},
				{
					cos_no: 15,
					mbr_no: 1,
					cos_title: 'ccc',
					cos_content: null,
					cos_thumbnail:
						'https://cdn.pixabay.com/photo/2017/08/30/01/05/milky-way-2695569_960_720.jpg',
					cos_banner: 'course_banner_default.png',
					reg_dt: '2020-10-28T01:19:29.000+00:00',
					chg_dt: '2020-10-28T01:19:29.000+00:00',
				},
				{
					cos_no: 14,
					mbr_no: 1,
					cos_title: 'hgjk',
					cos_content: null,
					cos_thumbnail:
						'https://cdn.pixabay.com/photo/2017/08/30/01/05/milky-way-2695569_960_720.jpg',
					cos_banner: 'course_banner_default.png',
					reg_dt: '2020-10-28T01:17:12.000+00:00',
					chg_dt: '2020-10-28T01:17:12.000+00:00',
				},
				{
					cos_no: 3,
					mbr_no: 1,
					cos_title: 'asdf',
					cos_content: 'dfg',
					cos_thumbnail:
						'https://images.unsplash.com/photo-1579546929518-9e396f3cc809?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjExMzk2fQ&w=1000&q=80',
					cos_banner: 'course_banner_default.png',
					reg_dt: '2020-10-27T17:38:43.000+00:00',
					chg_dt: '2020-10-27T17:38:43.000+00:00',
				},
				{
					cos_no: 5,
					mbr_no: 1,
					cos_title: 'co5',
					cos_content: 'cvb',
					cos_thumbnail:
						'https://cdn.cjr.org/wp-content/uploads/2019/07/AdobeStock_100000042-e1563305717660-686x371.jpeg',
					cos_banner: 'course_banner_default.png',
					reg_dt: '2020-10-27T17:38:43.000+00:00',
					chg_dt: '2020-10-27T17:38:43.000+00:00',
				},
				{
					cos_no: 6,
					mbr_no: 1,
					cos_title: 'co6',
					cos_content: 'trh',
					cos_thumbnail:
						'https://cdn.pixabay.com/photo/2017/08/30/01/05/milky-way-2695569_960_720.jpg',
					cos_banner: 'course_banner_default.png',
					reg_dt: '2020-10-27T17:38:43.000+00:00',
					chg_dt: '2020-10-27T17:38:43.000+00:00',
				},
				{
					cos_no: 7,
					mbr_no: 1,
					cos_title: 'co7',
					cos_content: 'bvc',
					cos_thumbnail:
						'https://cdn.pixabay.com/photo/2017/08/30/01/05/milky-way-2695569_960_720.jpg',
					cos_banner: 'course_banner_default.png',
					reg_dt: '2020-10-27T17:38:43.000+00:00',
					chg_dt: '2020-10-27T17:38:43.000+00:00',
				},
				{
					cos_no: 8,
					mbr_no: 1,
					cos_title: 'co8',
					cos_content: 'cbv',
					cos_thumbnail:
						'https://cdn.pixabay.com/photo/2017/08/30/01/05/milky-way-2695569_960_720.jpg',
					cos_banner: 'course_banner_default.png',
					reg_dt: '2020-10-27T17:38:43.000+00:00',
					chg_dt: '2020-10-27T17:38:43.000+00:00',
				},
				{
					cos_no: 2,
					mbr_no: 1,
					cos_title: 'co2',
					cos_content: 'asdf',
					cos_thumbnail:
						'https://media.istockphoto.com/photos/blue-abstract-background-or-texture-picture-id1138395421?k=6&m=1138395421&s=612x612&w=0&h=bJ1SRWujCgg3QWzkGPgaRiArNYohPl7-Wc4p_Fa_cyA=',
					cos_banner: 'course_banner_default.png',
					reg_dt: '2020-10-27T16:52:13.000+00:00',
					chg_dt: '2020-10-27T16:52:13.000+00:00',
				},
				{
					cos_no: 13,
					mbr_no: 1,
					cos_title: 'rtyu',
					cos_content: null,
					cos_thumbnail:
						'https://cdn.pixabay.com/photo/2017/08/30/01/05/milky-way-2695569_960_720.jpg',
					cos_banner: 'course_banner_default.png',
					reg_dt: '2020-10-27T14:25:15.000+00:00',
					chg_dt: '2020-10-27T14:25:15.000+00:00',
				},
				{
					cos_no: 12,
					mbr_no: 1,
					cos_title: 'qwer',
					cos_content: null,
					cos_thumbnail:
						'https://cdn.pixabay.com/photo/2017/08/30/01/05/milky-way-2695569_960_720.jpg',
					cos_banner: 'course_banner_default.png',
					reg_dt: '2020-10-27T14:24:26.000+00:00',
					chg_dt: '2020-10-27T14:24:26.000+00:00',
				},
				{
					cos_no: 11,
					mbr_no: 1,
					cos_title: 'asdf',
					cos_content: null,
					cos_thumbnail:
						'https://cdn.pixabay.com/photo/2017/08/30/01/05/milky-way-2695569_960_720.jpg',
					cos_banner: 'course_banner_default.png',
					reg_dt: '2020-10-27T14:24:19.000+00:00',
					chg_dt: '2020-10-27T14:24:19.000+00:00',
				},
				{
					cos_no: 10,
					mbr_no: 1,
					cos_title: 'zxcv',
					cos_content: null,
					cos_thumbnail:
						'https://cdn.pixabay.com/photo/2017/08/30/01/05/milky-way-2695569_960_720.jpg',
					cos_banner: 'course_banner_default.png',
					reg_dt: '2020-10-27T14:23:25.000+00:00',
					chg_dt: '2020-10-27T14:23:25.000+00:00',
				},
			],
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
