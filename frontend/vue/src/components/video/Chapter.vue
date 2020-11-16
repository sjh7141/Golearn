<template>
	<v-card
		ref="app"
		:width="width"
		outlined
		flat
		tile
		class="mr-6"
		style="overflow-y: auto; "
		:style="{ top: top }"
	>
		<v-row
			class="pl-5 my-4"
			v-for="(item, i) in chapters"
			:key="`chapter_${i}`"
			@click="$router.push(`/channel/play/${item.vid_no}`)"
			style="cursor:pointer;"
		>
			<v-col class="pa-0" :cols="3">
				<v-img :src="item.vid_thumbnail" style="border-radius:3px;">
					<v-layout
						style="position: absolute; bottom:3px; right:3px; background-color:rgba(0,0,0,0.8); color:white; font-size: 12px;line-height: 18px;border-radius: 2px;padding: 0 4px;"
						>{{ item.vid_length | convertM }}:{{
							item.vid_length | convertS
						}}</v-layout
					>
				</v-img>
			</v-col>
			<v-col class="pa-0 pl-2" :cols="9" style="position:relative;">
				<div style="height:100%; position:relative;">
					<span style="font-size:14px; font-weight:500;">
						{{ i + 1 }}.
						{{ item.vid_title ? item.vid_title : 'Untitled' }}</span
					><br />
				</div>
			</v-col>
		</v-row>
	</v-card>
</template>

<script>
import { mapActions } from 'vuex';

export default {
	name: 'Chapter',
	props: ['no'],
	watch: {
		no() {
			this.getPlayList(this.no);
		},
	},
	filters: {
		convertM(t) {
			let ret = parseInt(t / 60);
			return ret;
		},
		convertS(t) {
			let ret = parseInt(t) % 60;
			if (ret < 10) ret = '0' + ret;
			return ret;
		},
	},
	data() {
		return {
			width: 0,
			height: 0,
			top: 64,

			chapters: [
				{ title: '구름IDE에서 파이썬 코딩 시작하기', time: '14 : 27' },
				{ title: '수와 연산', time: '28: 11' },
				{ title: '변수: 대입과 비교 연산', time: '30 : 01' },
				{ title: '논리 연산과 조건문', time: '31 : 54' },
				{ title: '리스트와 반복문', time: '38 : 56' },
				{ title: '열린 사물함의 갯수 문제', time: '31 : 44' },
				{ title: '함수의 활용과 소수의 판별', time: '43 : 49' },
				{ title: '에라토스테네스의 체', time: '27 : 13' },
				{ title: '소인수 분해', time: '28 : 20' },
				{ title: '최대공약수 구하기', time: '23 : 02' },
				{ title: '최소 공배수 구하기', time: '24 : 48' },
				{ title: '구름IDE에서 파이썬 코딩 시작하기', time: '14 : 27' },
				{ title: '수와 연산', time: '28: 11' },
				{ title: '변수: 대입과 비교 연산', time: '30 : 01' },
				{ title: '논리 연산과 조건문', time: '31 : 54' },
				{ title: '리스트와 반복문', time: '38 : 56' },
			],
		};
	},
	mounted() {
		this.resizeChapter();
		window.addEventListener('resize', this.resizeChapter);
	},
	beforeDestroy() {
		window.removeEventListener('resize', this.resizeChapter);
	},
	methods: {
		...mapActions(['getChannelVideos']),
		getPlayList(no) {
			this.getChannelVideos(no).then(({ data }) => {
				this.chapters = data;
			});
		},
		resizeChapter() {
			if (window.innerWidth < 960) {
				this.width = '100%';
				this.height = 500;
			} else {
				this.width = 418;
				this.height =
					window.innerHeight - this.$refs.app.$el.offsetTop - 10;
			}
		},
	},
};
</script>

<style scoped></style>
