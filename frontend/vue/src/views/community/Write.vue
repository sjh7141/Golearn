<template>
	<div id="wrapper">
		<v-img height="200" src="@/assets/community_banner.jpg"> </v-img>
		<v-row class="height-100 pt-15 mb-15" style="padding: 0 4%">
			<v-col cols="3" md="3" lg="2">
				<v-row class="pl-15">
					<v-col cols="12" v-for="(item, idx) in tap" :key="idx">
						<span
							class="tap-font"
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
							게시판 글{{ upperTitle }}
						</div>
						<div style="width:100%;">
							<v-card class="mb-6 pa-6">
								<v-combobox
									v-model="selectBoard"
									:items="outerBoard"
									:disabled="isEdit"
									placeholder="게시판을 선택해주세요"
									outlined
								></v-combobox>
								<div class="item">
									<v-text-field
										v-model="title"
										:rules="rules"
										outlined
										ref="title"
										placeholder="제목을 입력해주세요"
										maxlength="30"
									></v-text-field>
								</div>
								<editor
									v-if="content != null"
									ref="editor"
									:preContent="content"
								></editor>
								<v-divider />
								<div class="pt-8" style="text-align:end">
									<v-btn
										depressed
										outlined
										tile
										color="#9382D7"
										class="bold"
										@click="save"
									>
										{{ isEdit ? '수정' : '등록' }}
									</v-btn>
								</div>
							</v-card>
						</div>
					</v-col>
				</v-row>
			</v-col>
		</v-row>
	</div>
</template>

<script>
import Editor from '@/components/component/editor.vue';

export default {
	components: {
		Editor,
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
			selectBoard: 'FAQ',
			outerBoard: ['FAQ', '질문게시판', '자유게시판'],
			boardCode: {
				FAQ: 2,
				질문게시판: 3,
				자유게시판: 4,
			},
			title: '',
			rules: [v => v.length > 4 || '5자이상 입력이 필요합니다.'],
			content: null,
			isEdit: false,
			upperTitle: '',
		};
	},
	methods: {
		changeTap(idx) {
			this.$router.push(`/community/${this.tapEn[idx]}/1`);
		},
		save() {
			if (!this.title.length || this.title.length < 5) {
				this.$refs.title.focus();
				return;
			}
			if (!this.isEdit) {
				this.$store
					.dispatch('postPost', {
						content: this.$refs.editor.getHTML(),
						title: this.title,
						type: this.boardCode[this.selectBoard],
					})
					.then(() => {
						this.$router.push(`/community/notice/1`);
					});
			} else {
				this.$store
					.dispatch('setPost', {
						content: this.$refs.editor.getHTML(),
						title: this.title,
						brd_no: Number(this.$route.params.id),
					})
					.then(() => {
						this.$router.push(`/community/notice/1`);
					});
			}
		},
	},
	mounted() {
		if (this.$route.name == 'CommunityModify') {
			this.upperTitle = '수정';
			let index = this.tapCode[this.$route.params.type];
			this.selectBoard = this.tap[index - 1];
			this.$store
				.dispatch('getPost', this.$route.params.id)
				.then(({ data }) => {
					this.content = data.content;
					this.title = data.title;
					this.isEdit = true;
				});
		} else {
			this.content = ' ';
			this.upperTitle = '쓰기';
		}
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
