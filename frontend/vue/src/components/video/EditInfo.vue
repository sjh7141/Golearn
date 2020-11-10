<template>
	<v-row class="ml-3 height-100">
		<v-col cols="9">
			<h2>강의 정보</h2>
			<h4 class="pb-5 pl-3" style="color:gray;">
				"강의에 대한 정보가 잘 드러나는 제목과 내용을 작성해주세요."
			</h4>
			<div class="item">
				<div class="label">
					<h4>강의제목</h4>
				</div>
				<v-text-field
					v-model="title"
					:rules="rules"
					ref="title"
					filled
					placeholder="[코스 이름 예시] CSS의 시작"
					maxlength="30"
				></v-text-field>
			</div>
			<div id="editor-wrapper" class="item">
				<div class="label">
					<h4>강의내용</h4>
				</div>
				<editor
					v-if="content != null"
					ref="editor"
					:preContent="content"
				></editor>
			</div>
			<div class="pt-5">
				<div class="label">
					<h4>태그</h4>
				</div>
				<v-combobox
					v-model="selectTags"
					:items="tags"
					multiple
					filled
					chips
					deletable-chips
				></v-combobox>
			</div>
			<div style="text-align:end;">
				<v-btn
					outlined
					class="mr-3"
					style="border: 1px solid #c9c9c9;"
					@click="saveInfo"
				>
					저장
				</v-btn>
				<v-btn dark color="#5500ff" @click="changeActive">
					다음
				</v-btn>
			</div>
		</v-col>
		<v-col cols="3">
			<div>😊강의 정보 작성 Tip!</div>
		</v-col>
	</v-row>
</template>

<script>
import Editor from '@/components/component/editor.vue';
import { mapGetters } from 'vuex';

export default {
	components: {
		Editor,
	},
	data() {
		return {
			title: '',
			rules: [v => v.length > 4 || '5자이상 입력이 필요합니다.'],
			selectTags: ['C++'],
			tags: [
				// 'C',
				// 'C++',
				// 'Java',
				// 'Python',
				// 'CSS',
				// 'Vue',
				// 'React',
				// 'Angular',
				// 'Video',
				// 'Clean Code',
				// 'Spring',
				// 'TypeScript',
				// 'JavaScript',
			],
			preContent: `
						<h3>[강의 예시]</h3>
						<h2>
							'css 마스터' 코스에 오신걸 환영합니다.
						</h2>
						<p>
							해당 강의에서는 css의 기본을 다질 수 있습니다.
						</p>
						<pre><code>body { display: none; } \nheader { display: none; } \nfooter { display: none; } \naside { display: none; } \ndiv { display: none; }
						</code></pre>
						\n
						<ul>
							<li>
							선택자
							</li>
								<ol>
									<li>선택자 종류 </li>
									<li>선택자 조합 </li>
								</ol>
							<li>
							스타일링
							</li>
							<li>
							박스모델
							</li>
						</ul>
						<blockquote>
							모든 국민은 코딩을 배워야합니다. 코딩은 생각하는 방법을 가르쳐주기 때문입니다. 👏
							<br />
							– 스티브 잡스(Steve Jobs)
						</blockquote>
						`,
			content: null,
		};
	},
	methods: {
		changeActive() {
			this.$emit('changeActive');
		},
		saveInfo() {
			if (!this.title.length || this.title.length < 5) {
				this.$refs.title.focus();
				return;
			}
			this.content = this.$refs.editor.getHTML();
		},
	},
	watch: {
		title() {
			this.$emit('setTitle', this.title);
		},
	},
	computed: {
		...mapGetters(['course']),
	},
	created() {
		// this.$store
		// 	.dispatch('getCourse', this.$route.params.id)
		// 	.then(({ data }) => {
		// 		this.$store.commit('setCourse', data);
		// 		this.title =
		// 			this.course.cosTitle == null
		// 				? '[코스 이름 예시] CSS의 시작'
		// 				: this.course.cosTitle;
		// 		this.content =
		// 			this.course.cosContent == null
		// 				? this.preContent
		// 				: this.course.cosContent;
		// 	});
		// this.$store.dispatch('getTags').then(({ data }) => {
		// 	for (var tag of data) {
		// 		this.tags.push(tag.name);
		// 	}
		// });
		this.content = this.preContent;
	},
};
</script>

<style scoped>
.v-chip {
	border-radius: 4px !important;
}

.label {
	padding-bottom: 8px;
}

.item {
	padding-bottom: 24px;
}
</style>
