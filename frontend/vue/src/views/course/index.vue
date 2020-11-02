<template>
	<div>
		<v-img width="100%" src="@/assets/bg_skin1.jpg" height="230">
			<v-layout
				style="background-color:rgba(0,0,0,0.4); width:100%;height:100%;"
			>
			</v-layout>
		</v-img>

		<v-layout wrap style="width:1080px; margin:0 auto;" ref="contain">
			<v-flex md3 lg3 xl3>
				<v-card flat tile class="mt-10">
					<v-list-item>
						<v-list-item-subtitle
							style="font-size:14px; color:black; font-weight:600;"
							@click="
								$router.push('/course/intro');
								index = 0;
							"
						>
							소개
						</v-list-item-subtitle>
					</v-list-item>
					<v-list-item>
						<v-list-item-subtitle
							style="font-size:14px; color:black; font-weight:600;"
							@click="
								$router.push('/course/requestlist');
								index = 1;
							"
						>
							요청 목록
						</v-list-item-subtitle>
					</v-list-item>
					<v-list-item>
						<v-list-item-subtitle
							style="font-size:14px; color:black; font-weight:600;"
						>
							목차
						</v-list-item-subtitle>
					</v-list-item>

					<v-list-item
						class="pl-6"
						v-for="(item, i) in chapters"
						:key="`chapters_${i}`"
					>
						<v-list-item-subtitle
							style="font-size:12px;"
							:style="{
								color: i == 0 ? 'rgb(60,60,223)' : '',
							}"
						>
							{{ item.title }}
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
				:style="{ height: height + 'px' }"
			>
				<Introduce v-if="index == 0" />
				<RequestList
					v-else-if="index == 1"
					@move="
						index = 2;
						move();
					"
				/>
				<Request v-else-if="index == 2" />
			</v-flex>
		</v-layout>
	</div>
</template>

<script>
import Introduce from '@/components/course/Introduce.vue';
import RequestList from '@/components/course/RequestList.vue';
import Request from '@/components/course/Request.vue';
export default {
	name: 'Index',
	components: {
		Introduce,
		RequestList,
		Request,
	},
	data() {
		return {
			height: 0,
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
			index: 0,
		};
	},
	mounted() {
		this.height = this.$refs.contain.clientHeight;
		if (this.$route.params.id == 'intro') this.index = 0;
		if (this.$route.params.id == 'requestlist') this.index = 1;
		if (this.$route.params.id == 'request') this.index = 2;
		console.log(this.index, this.$route.params);
	},
	methods: {
		move() {
			this.$router.push('/course/request');
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
