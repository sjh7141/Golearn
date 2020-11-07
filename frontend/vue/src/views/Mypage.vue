<template>
	<v-container fluid class="wrapper py-0 ">
		<v-row class="height-100 pt-15" style="width:1500px; margin:0 auto;">
			<v-col md="3">
				<v-row>
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
			<v-col md="9">
				<v-row>
					<v-col cols="12">
						<profile v-if="selectNo == 0"></profile>
						<!-- <like v-if="selectNo == 1"></like>
						<like-video v-if="selectNo == 2"></like-video>
						<like-course v-if="selectNo == 3"></like-course> -->
					</v-col>
				</v-row>
			</v-col>
		</v-row>
	</v-container>
</template>

<script>
import Profile from '@/components/mypage/ProfileForm.vue';
// import Like from '@/components/mypage/Like.vue';
// import LikeVideo from '@/components/mypage/LikeVideo.vue';
// import LikeCourse from '@/components/mypage/LikeCourse.vue';

export default {
	components: {
		Profile,
		// Like,
		// LikeVideo,
		// LikeCourse,
	},
	data() {
		return {
			tap: [
				{ name: '프로필 수정', child: false },
				{ name: '내 학습', child: false },
				{ name: '좋아요한 영상', child: true },
				{ name: '좋아요한 코스', child: true },
			],
			selectNo: 0,
		};
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
	color: #34abeb;
}
</style>
