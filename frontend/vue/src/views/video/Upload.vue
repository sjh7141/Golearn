<template>
	<div>
		<v-row align="center">
			<v-img
				src="@/assets/golearn_banner.jpg"
				height="200"
				style="filter: grayscale(50%);"
				ref="img"
			>
				<v-col style="height:100%;">
					<div class="banner-wrapper">
						<div id="banner-text">
							동영상 업로드
						</div>
					</div>
				</v-col>
			</v-img>
		</v-row>
		<v-progress-linear
			color="#47e0ff"
			height="6"
			:value="(select + 1) * 33.33333"
		></v-progress-linear>
		<v-container fluid class="course-wrapper py-0 ">
			<v-row class="height-100">
				<v-col md="2">
					<v-row class="mx-2">
						<v-col
							v-for="(list, index) in iconList"
							cols="12"
							class="pa-3 my-2 list-wrapper d-flex align-center"
							:class="{ 'list-active': isActive(index) }"
							:key="list"
							@click="select = index"
						>
							<div class="list-icon mr-3">{{ index + 1 }}</div>
							<span>{{ list }}</span>
						</v-col>
					</v-row>
				</v-col>
				<v-col md="10" style="border-left: 1px solid #f2f2f2">
					<edit-cover
						@setBanner="setBanner"
						v-show="select == 0"
						@changeActive="changeActive"
					/>
					<edit-info
						@setTitle="setTitle"
						v-show="select == 1"
						@changeActive="changeActive"
					/>
					<edit-code
						v-show="select == 2"
						@changeActive="changeActive"
					/>
				</v-col>
			</v-row>
		</v-container>
	</div>
</template>

<script>
import EditInfo from '@/components/video/EditInfo.vue';
import EditCover from '@/components/video/EditCover.vue';
import EditCode from '@/components/video/EditCode.vue';
import { mapGetters } from 'vuex';

export default {
	components: {
		EditInfo,
		EditCover,
		EditCode,
	},
	data() {
		return {
			id: this.$route.params.id,
			iconList: ['영상', '기본정보', '예시코드'],
			select: 0,
			title: '',
			title_default: '[코스 이름 예시] CSS의 시작',
		};
	},
	methods: {
		isActive(idx) {
			if (idx == this.select) {
				return true;
			}
			return false;
		},
		setTitle(newTitle) {
			this.title = newTitle;
		},
		setBanner(src) {
			this.$refs.img.src = src;
		},
		changeActive() {
			this.select = (this.select + 1) % this.iconList.length;
			if (this.select == 0) {
				this.$router.push('/');
			}
		},
	},
	computed: {
		...mapGetters(['course']),
	},
};
</script>

<style>
.course-wrapper {
	min-height: 719px;
}

.list-icon {
	display: inline-block;
	border: 1px solid gray;
	width: 25px;
	height: 25px;
	text-align: center;
	font-size: 15px;
	border-radius: 6px;
}

.list-wrapper {
	border-radius: 6px;
	cursor: pointer;
	font-weight: 600;
}

.list-active {
	background-color: #ededed;
}

.list-wrapper:hover {
	background-color: #e3e3e3;
}

.banner-wrapper {
	height: 100%;
	font-size: 40px;
	color: white;
	font-weight: 500;
	font-family: NanumSquareWebFont, NanumSquareWebFont, '나눔고딕', NanumGothic,
		'맑은 고딕', Malgun Gothic, 'Apple SD Gothic Neo', '돋움', dotum,
		Helvetica, arial, sans-serif;
}

#banner-text {
	width: 1000px;
	height: 100%;
	margin: 0 auto;
	padding-top: 60px;
}
</style>
