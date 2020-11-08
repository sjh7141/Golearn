<template>
	<div ref="app">
		<v-card class="transparent" flat>
			<v-card-title class="px-0">
				<v-row align="center" class="pb-5">
					<div class="modify-text">
						좋아요한 영상
					</div>
					<v-spacer />
					<div
						v-if="flag"
						@click="goToLikeVideo"
						style="cursor: pointer;"
					>
						<span class="bold" style="font-size: 20px;">
							더보기
						</span>
						<v-icon large color="blue-grey">
							mdi-chevron-right
						</v-icon>
					</div>
				</v-row>
			</v-card-title>
			<v-row v-if="videos.length !== 0">
				<v-col
					cols="12"
					sm="6"
					md="3"
					lg="2"
					v-for="(video, i) in videos"
					:key="i"
					class="mx-xs-auto"
				>
					<v-skeleton-loader type="card-avatar">
						<video-card
							:card="{ maxWidth: 250 }"
							:video="video"
						></video-card>
					</v-skeleton-loader>
				</v-col>
			</v-row>
			<v-row v-else justify="center" align="center">
				<v-col cols="12" class="empty-box">
					<div style="margin-top:70px">
						등록된 영상이 없습니다. 새롭게 추가해보세요!🙆‍♂️
					</div>
				</v-col>
			</v-row>
		</v-card>
	</div>
</template>

<script>
import VideoCard from '@/components/component/VideoCard';
export default {
	props: ['videos', 'flag'],
	data() {
		return {};
	},
	computed: {},
	components: {
		VideoCard,
	},
	methods: {
		goToLikeVideo() {
			this.$emit('setNo', 2);
		},
	},
	mounted() {},
};
</script>

<style scoped>
.modify-text {
	font-size: 30px;
	font-weight: 600;
}

.empty-box {
	font-size: 30px;
	font-weight: 600;
	color: #b5b5b5;
	text-align: center;
	min-height: 200px;
}
</style>
