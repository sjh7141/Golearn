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
							style="font-family: 'BMJUA';"
						>
							{{ item }}
						</span>
					</v-col>
				</v-row>
			</v-col>
			<v-col cols="9" md="9" lg="10">
				<v-row justify="center">
					<v-col cols="12" class="pr-15">
						<div
							class="cm-title pb-3"
							style="font-family: 'BMJUA';"
						>
							{{ tap[selectNo] }}
						</div>
						<div class="pb-3" style="text-align:end;">
							<v-btn
								v-if="isLogin == 1"
								dark
								depressed
								color="#7640e3"
								class="mb-6"
								@click="write"
								rounded
								style="	font-family: 'BMJUA';"
							>
								글쓰기
							</v-btn>
						</div>
						<div
							v-for="(item, index) in boardList"
							:key="index"
							style="width:100%;"
						>
							<v-card class="mb-6">
								<v-img
									class="white--text align-end"
									height="68px"
									src="https://cdn.pixabay.com/photo/2017/03/28/22/55/night-photograph-2183637_960_720.jpg"
									@click="goToPost(item.brd_no)"
								>
									<v-card-title class="card-title bold">
										{{ item.title }}
									</v-card-title>
								</v-img>

								<v-card-text
									class="text--primary"
									@click="goToPost(item.brd_no)"
								>
									<div
										class="text-content"
										v-html="item.content"
									></div>
								</v-card-text>
								<v-card-text
									class="text--primary"
									style="font-family: 'BMJUA';"
								>
									<v-avatar class="ml-3" size="24">
										<v-img
											:src="item.mbr_profile"
											style="cursor:pointer;"
											@click="
												$router.push(
													`/channel/${item.mbr_no}`,
												)
											"
										/>
									</v-avatar>
									<span
										@click="
											$router.push(
												`/channel/${item.mbr_no}`,
											)
										"
										class="mr-5"
										style="cursor: pointer;"
									>
										{{ item.mbr_nickname }}
									</span>
									<span
										class="mr-5"
										style="font-size:14px; color:#a3a3a3"
									>
										{{
											item.reg_date
												.substring(0, 10)
												.replaceAll('-', '.')
										}}
									</span>
									<span
										class="mr-5"
										style="font-size:14px; color:#a3a3a3"
									>
										조회 {{ item.view_count }}
									</span>
									<v-icon small>
										mdi-message-processing-outline
									</v-icon>
									<span style="font-size:14px; color:#a3a3a3">
										{{ item.comment_count }}
									</span>
								</v-card-text>
							</v-card>
						</div>
					</v-col>
					<v-pagination
						v-model="pageNo"
						color="#7640e3"
						:length="pageInfo.end_page"
						style="text-align:center !important;"
					></v-pagination>
				</v-row>
			</v-col>
		</v-row>
	</div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
	data() {
		return {
			tap: ['공지사항', 'FAQ', '질문게시판', '자유게시판'],
			tapEn: ['notice', 'faq', 'question', 'general'],
			tapCode: {
				notice: 1,
				faq: 2,
				question: 3,
				general: 4,
			},
			selectNo: 0,
			boardList: [],
			pageNo: 1,
			pageInfo: {},
		};
	},
	watch: {
		pageNo() {
			if (this.pageNo != Number(this.$route.params.page)) {
				this.$router.push(
					`/community/${this.tapEn[this.selectNo]}/${this.pageNo}`,
				);
			}
		},
	},
	methods: {
		changeTap(idx) {
			if (idx == Number(this.tapCode[this.$route.params.type] - 1)) {
				return;
			}
			this.$router.push(`/community/${this.tapEn[idx]}/1`);
		},
		changeBoard() {
			this.$store
				.dispatch('getBoard', {
					type: this.selectNo + 1,
					page: this.pageNo,
				})
				.then(({ data }) => {
					this.pageInfo = data.page;
					this.boardList = [];
					for (let post of data.board) {
						post.mbr_nickname = '';
						post.mbr_profile = '';
						this.$store
							.dispatch('getUserByNo', post.mbr_no)
							.then(({ data }) => {
								post.mbr_nickname = data.nickname;
								post.mbr_profile = data.profile;
							});
					}
					this.boardList = data.board;
				});
		},
		goToPost(no) {
			this.$router.push(`/post/${this.tapEn[this.selectNo]}/${no}`);
		},
		write() {
			this.$router.push(`/community/write`);
		},
	},
	computed: {
		...mapGetters(['isLogin']),
	},
	mounted() {
		this.selectNo = Number(this.tapCode[this.$route.params.type] - 1);
		this.pageNo = Number(this.$route.params.page);
		this.changeBoard();
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
.cm-title {
	font-weight: 600;
	font-size: 2.2rem;
}
.tap-font {
	font-weight: 600;
	cursor: pointer;
}
.select {
	color: #8059d4;
}
.card-title {
	background-color: rgba(30, 30, 30, 0.8);
	cursor: pointer;
}
.text-content {
	display: block;
	max-height: 44px;
	display: block;
	display: -webkit-box;
	overflow: hidden;
	text-overflow: ellipsis;
	-webkit-line-clamp: 2;
	-webkit-box-orient: vertical;
	word-break: break-all;
	word-wrap: break-word;
	word-break: break-word;
	color: #666;
	cursor: pointer;
}
.text-content:hover {
	text-decoration: underline;
}
</style>
<style>
.v-pagination__item {
	text-align: center !important;
}
</style>
