<template>
	<div id="wrapper">
		<v-img height="200" src="@/assets/community_banner.jpg">
			<!-- <v-row align="center" id="banner-wrapper" class="mx-0 px-5">
				<v-col cols="12" align="center">
					고런고런의 방대한 네트워크에서
				</v-col>
				<v-col cols="12" align="center">
					모르는 것이 있다면 바로 질문하고 성장하세요
				</v-col>
			</v-row> -->
		</v-img>
		<v-row class="height-100 pt-15 mb-15" style="padding: 0 4%">
			<v-col cols="3" md="3" lg="2">
				<v-row class="pl-15">
					<v-col cols="12" v-for="(item, idx) in tap" :key="idx">
						<span
							class="tap-font"
							:class="{ select: idx == selectNo }"
							@click="changeTap(idx)"
						>
							{{ item }}
						</span>
					</v-col>
				</v-row>
			</v-col>
			<v-col cols="9" md="9" lg="10">
				<v-row>
					<v-col cols="12" class="pr-15">
						<div style="width:100%; border:1px solid black;">
							sample
						</div>
					</v-col>
				</v-row>
			</v-col>
		</v-row>
	</div>
</template>

<script>
export default {
	data() {
		return {
			tap: ['공지사항', 'FAQ', '질문게시판', '자유게시판'],
			selectNo: 0,
			boardList: [],
			pageNo: 1,
		};
	},
	methods: {
		changeTap(idx) {
			this.selectNo = idx;
		},
	},
	mounted() {
		this.$store
			.dispatch('getBoard', {
				type: this.selectNo + 1,
				page: this.pageNo,
			})
			.then(({ data }) => {
				this.boardList = data;
				console.log(data);
			});
	},
};
</script>

<style scoped>
#banner-wrapper {
	width: 100%;
	height: 200px;
	color: #f9f9f9;
	font-size: 1.5rem;
	font-weight: 600;
	font-family: 'Lato', 'Spoqa Han Sans';
}
#wrapper {
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
