<template>
	<div id="request">
		<v-layout>
			<div>
				<h3 style="transform: translateY(5px)">강의 요청</h3>
			</div>
			<v-spacer />
			<v-select
				v-model="zz"
				:items="chapters"
				item-text="title"
				item-value="id"
				outlined
				dense
				append-icon="mdi-chevron-down"
				style="max-width:50%;max-height:30px;color:red !important"
				hide-details
			></v-select>
		</v-layout>

		<v-divider class="my-5" />
		<v-list outlined>
			zz
			<v-divider />
			zz
		</v-list>
		<v-card>
			zz
		</v-card>
	</div>
</template>

<script>
export default {
	name: 'Request',
	data() {
		return {
			zz: 1,
			chapters: [
				{
					title: '구름IDE에서 파이썬 코딩 시작하기',
					time: '14 : 27',
					id: 1,
				},
				{ title: '수와 연산', time: '28: 11', id: 2 },
				{ title: '변수: 대입과 비교 연산', time: '30 : 01', id: 3 },
				{ title: '논리 연산과 조건문', time: '31 : 54', id: 4 },
				{ title: '리스트와 반복문', time: '38 : 56', id: 5 },
				{ title: '열린 사물함의 갯수 문제', time: '31 : 44', id: 6 },
				{ title: '함수의 활용과 소수의 판별', time: '43 : 49', id: 7 },
				{ title: '에라토스테네스의 체', time: '27 : 13', id: 8 },
				{ title: '소인수 분해', time: '28 : 20', id: 9 },
				{ title: '최대공약수 구하기', time: '23 : 02', id: 10 },
				{ title: '최소 공배수 구하기', time: '24 : 48', id: 11 },
				{
					title: '구름IDE에서 파이썬 코딩 시작하기',
					time: '14 : 27',
					id: 12,
				},
				{ title: '수와 연산', time: '28: 11', id: 13 },
				{ title: '변수: 대입과 비교 연산', time: '30 : 01', id: 14 },
				{ title: '논리 연산과 조건문', time: '31 : 54', id: 15 },
				{ title: '리스트와 반복문', time: '38 : 56', id: 16 },
			],
		};
	},
};
</script>

<style>
#request .v-input__slot fieldset {
	color: rgba(229, 229, 229) !important;
}
</style>
