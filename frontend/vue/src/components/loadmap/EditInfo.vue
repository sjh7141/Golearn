<template>
	<v-row class="ml-3 height-100">
		<v-col cols="9">
			<h2>로드맵 정보</h2>
			<h4 class="pb-5 pl-3" style="color:gray;">
				"로드맵 전체 구성에 대한 정보가 잘 드러나는 제목과 내용을
				작성해주세요."
			</h4>
			<div class="item">
				<div class="label">
					<h4>제목</h4>
				</div>
				<v-text-field
					v-model="title"
					:rules="rules"
					ref="title"
					filled
					placeholder="[로드맵 이름 예시] Web 단기 완성"
					maxlength="30"
				></v-text-field>
			</div>
			<div id="editor-wrapper" class="item">
				<div class="label">
					<h4>내용</h4>
				</div>
				<editor
					v-if="content != null"
					ref="editor"
					:preContent="content"
				></editor>
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
			<div>😊로드맵 정보 작성 Tip!</div>
		</v-col>
	</v-row>
</template>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
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
			isImg: false,
		};
	},
	methods: {
		saveInfo() {
			if (!this.title.length || this.title.length < 5) {
				this.$refs.title.focus();
				return;
			}
			this.content = this.$refs.editor.getHTML();
			this.$store.commit('setLoadmapTitle', this.title);
			this.$store.commit('setLoadmapContent', this.content);
			this.$store.dispatch('setLoadmap', {
				insert: [],
				delete: [],
				update: [],
			});
		},
		changeActive() {
			this.$emit('changeActive');
		},
	},
	watch: {
		title() {
			this.$emit('setTitle', this.title);
		},
	},
	computed: {
		...mapGetters(['loadmap']),
	},
	created() {
		this.$store
			.dispatch('getLoadmap', this.$route.params.id)
			.then(({ data }) => {
				console.log(data);
				if (data.course.length != 0) {
					this.$store.commit(
						'setLoadmapBanner',
						data.course[0].cos_banner,
					);
				} else {
					this.$store.commit('setLoadmapBanner', null);
				}
				this.$store.commit('setLoadmap', data.loadmap);
				let self = data.course;
				for (let course of data.course) {
					course.isEdit = false;
					this.$store
						.dispatch('getUserByNo', course.mbr_no)
						.then(({ data }) => {
							course.mbr_nickname = data.nickname;
							course.mbr_profile = data.profile;
							this.$store.commit('setLoadmapCourse', self);
						});
					this.$store
						.dispatch('getCourseTag', course.cos_no)
						.then(({ data }) => {
							console.log(data, course.cos_no);
							course.tags = [];
							for (let tag of data) {
								course.tags.push(tag.tag_name);
							}
							this.$store.commit('setLoadmapCourse', self);
						});
				}
				this.title =
					this.loadmap.ldm_title == null
						? '[로드맵 이름 예시] Web 단기 완성'
						: this.loadmap.ldm_title;
				this.content =
					this.loadmap.ldm_content == null
						? this.preContent
						: this.loadmap.ldm_content;
			});
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
