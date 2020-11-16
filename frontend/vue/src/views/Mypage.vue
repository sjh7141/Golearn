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
		<v-row
			class="height-100 pt-15 mb-15 media-margin"
			style="padding: 0 4%"
		>
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
			likeVideos: [],
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
