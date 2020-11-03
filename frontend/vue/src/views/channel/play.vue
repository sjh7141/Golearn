<template>
	<div ref="app">
		<div
			id="left-panel"
			class="px-6"
			:style="{ width: width + 'px', height: totalHeight + 'px' }"
		>
			<multi-view :width="width" :height="height" />

			<v-list-item class="pa-0 ma-0 mb-1">
				<v-list-item-avatar size="48">
					<v-img :src="`https://picsum.photos/500/300?image=20`" />
				</v-list-item-avatar>
				<v-list-item-content>
					<v-list-item-title class="mt-1" style="font-size:18px;">
						구름IDE에서 파이썬 코딩 시작하기
					</v-list-item-title>
					<v-list-item-subtitle class="mt-0" style="font-size:14px;">
						미용쓰기
					</v-list-item-subtitle>
					<v-list-item-subtitle class="mt-1" style="font-size:13px;">
						#파이썬 #알고리즘 #입문
					</v-list-item-subtitle>
				</v-list-item-content>
				<v-list-item-action>
					<span style="font-size:15px; font-weight:500">
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
							<span style="margin: auto; color:#8e8e8e">25</span>
						</div>
					</span>
				</v-list-item-action>
				<v-list-item-action>
					<v-btn
						depressed
						color="rgb(60, 65, 223)"
						dark
						style="font-size:13px; height:36px;"
					>
						<v-icon class="mr-2" small>
							mdi-heart-outline
						</v-icon>
						구독하기
					</v-btn>
				</v-list-item-action>
			</v-list-item>
			<v-divider class="mb-5" />

			<Comment />
		</div>
		<div id="right-panel">
			<Chapter />
		</div>
	</div>
</template>

<script>
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
		};
	},
	mounted() {
		window.addEventListener('resize', this.resizePlay);
		this.resizePlay();
	},
	beforeDestroy() {
		window.removeEventListener('resize', this.resizePlay);
	},
	methods: {
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
