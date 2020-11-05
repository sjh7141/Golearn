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
							{{ title == '' ? title_default : title }}
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
		<v-container fluid class="loadmap-wrapper py-0 ">
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
					<edit-info
						@setTitle="setTitle"
						v-show="select == 0"
						@changeActive="changeActive"
					/>
					<edit-cover
						@setBanner="setBanner"
						v-show="select == 1"
						@changeActive="changeActive"
					/>
					<edit-index
						v-show="select == 2"
						@changeActive="changeActive"
					/>
				</v-col>
			</v-row>
		</v-container>
	</div>
</template>

<script>
import EditInfo from '@/components/loadmap/EditInfo.vue';
import EditCover from '@/components/loadmap/EditCover.vue';
import EditIndex from '@/components/loadmap/EditIndex.vue';

export default {
	components: {
		EditInfo,
		EditCover,
		EditIndex,
	},
	data() {
		return {
			id: this.$route.params.id,
			iconList: ['로드맵 정보', '이미지', '코스 순서'],
			select: 0,
			title: '',
			title_default: '[로드맵 이름 예시] Web 단기 완성',
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
};
</script>

<style scoped>
.loadmap-wrapper {
	min-height: 719px;
}
</style>
