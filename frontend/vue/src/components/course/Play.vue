<template>
	<div>
		<v-layout class="ma-0 pa-0">
			<h3>구름IDE에서 파이썬 코딩 시작하기</h3>
			<v-spacer />
			<span>
				<v-avatar size="28" style="" class="mr-1">
					<v-img src="@/assets/default_profile.png" />
				</v-avatar>
				<a href="#" style="font-size:14px; text-decoration:none">
					미용쓰기
				</a>
			</span>
		</v-layout>
		<v-divider class="mb-5" />
		<div>
			<MultiView :width="729" :height="410" />
			<p style="text-align: center;">
				<span style="font-size: 14pt;"
					><strong>모두를 위한 파이썬</strong></span
				>
			</p>
			<p style="text-align: center;">
				<strong>- 1. 모두를 위한 프로그래밍 : 파이썬 -</strong>
			</p>
			<p>&nbsp;<br /></p>
			<p>
				전 세계 백만명이 넘게 수강하고 있는 Michigan 대학 Charles
				Severance 교수님의 인기 MOOC 강좌인 “Python for Everybody”
				동영상과 강의 노트를 한국어로 번역하여 제공합니다. 파이썬은 웹
				서비스, 데이터 분석, 머신러닝에서 강세를 보이며 널리 쓰이는
				언어로, 쉽지만 강력한 언어입니다.<br />
			</p>
			<p>
				총 5개의 연속된 작은 강의들을 통해 파이썬 언어의 문법에서 시작해
				자료구조, 웹 데이터 추출 및 데이터베이스를 포함한 기본적인
				프로그래밍 개념을 차근차근 소개합니다.&nbsp;마지막 캡스톤
				프로젝트에서는 앞 코스에서 학습한 전반적인 내용을 이용해 데이터
				검색, 처리 및 시각화를 위한 응용 프로그램을 설계하고 제작합니다.
			</p>
		</div>
		<v-layout class="mt-5 mb-1">
			<v-spacer />
			<v-tooltip top>
				<template v-slot:activator="{ on }">
					<v-btn icon v-on="on">
						<v-icon size="25">mdi-heart-outline</v-icon>
					</v-btn>
				</template>
				<span>
					좋아요
				</span>
			</v-tooltip>
			<v-tooltip top>
				<template v-slot:activator="{ on }">
					<v-btn icon v-on="on">
						<v-icon size="20">fas fa-external-link-alt</v-icon>
					</v-btn>
				</template>
				<span>
					보관함에 추가
				</span>
			</v-tooltip>
			<v-tooltip top>
				<template v-slot:activator="{ on }">
					<v-btn icon v-on="on">
						<v-icon size="25">mdi-source-merge</v-icon>
					</v-btn>
				</template>
				<span>
					기여하기
				</span>
			</v-tooltip>
		</v-layout>
		<v-divider class="mb-5" />

		<v-layout>
			<v-menu offset-y>
				<template v-slot:activator="{ on }">
					<v-btn
						tile
						depressed
						outlined
						width="200"
						color="rgba(0,0,0,0.12)"
						v-on="on"
					>
						<span
							style="position:absolute; left:0; font-size:14px; color:#444; text-overflow: ellipsis; overflow: hidden; white-space: nowrap; width:150px;"
						>
							구름IDE에서 파이썬 코딩 시작하기
						</span>
						<v-icon
							size="24"
							right
							style="position:absolute; right:0"
						>
							mdi-chevron-down
						</v-icon>
					</v-btn>
				</template>
				<v-list dense class="pa-0" style="width:200px;">
					<v-list-item link>
						<span
							style="font-size:14px;text-overflow: ellipsis; overflow: hidden; white-space: nowrap; width:200px; "
						>
							구름IDE에서 파이썬 코딩 시작하기1
						</span>
					</v-list-item>
					<v-list-item style="font-size:14px;" link>
						<span
							style="font-size:14px;text-overflow: ellipsis; overflow: hidden; white-space: nowrap; width:200px; "
						>
							구름IDE에서 파이썬 코딩 시작하기2
						</span>
					</v-list-item>
				</v-list>
			</v-menu>

			<v-spacer />
			<v-btn depressed color="#9382D7" dark tile style="font-size:14px;">
				수강 완료
			</v-btn>
		</v-layout>
		<v-divider class="my-5" />

		<Comment />
	</div>
</template>

<script>
import MultiView from '@/components/video/MultiView.vue';
import Comment from '@/components/course/Comment.vue';
export default {
	components: {
		MultiView,
		Comment,
	},
};
</script>

<style></style>
