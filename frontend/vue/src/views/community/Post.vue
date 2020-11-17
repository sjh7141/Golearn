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
							class="cm-title pb-6"
							style="font-family: 'BMJUA';"
						>
							{{ tap[selectNo] }}
						</div>
						<div style="width:100%;">
							<v-card class="mb-6 pa-6">
								<div
									align="end"
									v-if="isLogin && post.mbr_no == user.no"
								>
									<v-btn
										class="mr-3 bold"
										depressed
										rounded
										dark
										color="#7640e3"
										@click="modify"
										style="	font-family: 'BMJUA';"
									>
										수정
									</v-btn>
									<v-btn
										depressed
										rounded
										dark
										color="#7640e3"
										class="bold"
										@click="confirmDelete"
										style="	font-family: 'BMJUA';"
									>
										삭제
									</v-btn>
								</div>
								<v-card-title class="card-title bold">
									{{ post.title }}
								</v-card-title>
								<v-card-text
									class="text--primary"
									style="font-family: 'BMJUA';"
								>
									<v-row align="center">
										<v-avatar size="32" class="mr-2">
											<v-img
												:src="post.mbr_profile"
												style="cursor:pointer;"
												@click="
													$router.push(
														`/channel/${post.mbr_no}`,
													)
												"
											/>
										</v-avatar>
										<span
											@click="
												$router.push(
													`/channel/${post.mbr_no}`,
												)
											"
											class=" mr-5"
											style="cursor: pointer; font-size:14px;"
										>
											{{ post.mbr_nickname }}
										</span>
										<span
											class="mr-5"
											style="font-size:14px; color:#a3a3a3"
										>
											{{
												post.reg_date
													? post.reg_date
															.substring(0, 10)
															.replaceAll(
																'-',
																'.',
															)
													: ''
											}}
										</span>
										<span
											class="mr-5"
											style="font-size:14px; color:#a3a3a3"
										>
											조회 {{ post.view_count }}
										</span>
										<v-spacer />
										<v-icon small class="mr-2">
											mdi-message-processing-outline
										</v-icon>

										<span style="font-size:14px;">
											댓글 {{ post.comment_count }}
										</span>
									</v-row>
								</v-card-text>
								<v-divider />
								<v-card-text
									v-html="post.content"
									class="post-content"
								>
								</v-card-text>
								<v-divider />
								<v-card-text>
									<Comment
										:no="postNo"
										:numCmt="post.comment_count"
										v-if="postNo"
									/>
								</v-card-text>
								<v-card-text>
									<v-btn
										absolute
										right
										bottom
										depressed
										outlined
										tile
										color="#9382D7"
										style="font-size:14px;"
										@click="scrollUp"
									>
										TOP
									</v-btn>
								</v-card-text>
							</v-card>
						</div>
					</v-col>
				</v-row>
			</v-col>
		</v-row>
	</div>
</template>

<script>
import Comment from '@/components/board/Comment.vue';
import { mapGetters } from 'vuex';

export default {
	components: {
		Comment,
	},
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
			post: {},
			postNo: 1,
		};
	},
	watch: {
		pageNo() {
			this.$router.push(
				`/community/${this.tapEn[this.selectNo]}/${this.pageNo}`,
			);
		},
	},
	methods: {
		changeTap(idx) {
			this.$router.push(`/community/${this.tapEn[idx]}/1`);
		},
		scrollUp() {
			window.scrollTo(0, 0);
		},
		confirmDelete() {
			if (confirm('정말 삭제하시겠습니까?')) {
				this.$store
					.dispatch('deletePost', {
						brd_no: this.post.brd_no,
					})
					.then(() => {
						this.$store.commit(
							'setSBMessage',
							'정상적으로 삭제되었습니다.',
						);
						this.$store.commit('setSnackbar', true);
						this.$router.push('/community/notice/1');
					});
			}
		},
		modify() {
			this.$router.push(
				`/community/modify/${this.tapEn[this.selectNo]}/${
					this.post.brd_no
				}`,
			);
		},
	},
	computed: {
		...mapGetters(['isLogin', 'user']),
	},
	mounted() {
		this.selectNo = Number(this.tapCode[this.$route.params.type] - 1);
		this.postNo = Number(this.$route.params.id);
		this.$store.dispatch('getPost', this.postNo).then(({ data }) => {
			let user = data;
			this.$store
				.dispatch('getUserByNo', user.mbr_no)
				.then(({ data }) => {
					user.mbr_nickname = data.nickname;
					user.mbr_profile = data.profile;
					this.post = user;
				});
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
	font-size: 35px;
}
.post-content {
	min-height: 400px;
}
</style>
