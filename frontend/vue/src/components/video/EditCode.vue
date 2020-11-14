<template>
	<v-row class="ml-3 height-100">
		<v-col cols="9">
			<h2>코드</h2>
			<h4 class="pb-5 pl-3" style="color:gray;">
				"강의와 함께 실습할 수 있는 코드를 작성해 주세요."
			</h4>
			<div class="mt-5">
				<div class="pa-6 mb-6 bg-light-gray border-radius-10">
					<div class="bold">
						코드 등록
					</div>
					<ul style="list-style-type: none;">
						<li>
							고런고런의 컴파일러는
							<span class="bold">C, C++, Java, Python</span>을
							지원합니다.
						</li>
						<li>
							<span class="bold">제목</span>을 입력하시고
							<span class="bold">강의영상을</span> 선택해주세요.
						</li>
						<li>
							본인이 <span class="bold">등록</span>한 영상과
							<span class="bold">좋아요</span>한 영상을 등록할 수
							있습니다.
						</li>
					</ul>
				</div>
				<v-divider class="pb-8"></v-divider>
				<v-row justify="end">
					<v-combobox
						v-model="selectType"
						:items="outerType"
						label="언어"
						outlined
						dense
						class="mx-3"
						style="max-width: 150px;"
						@change="changeType"
					></v-combobox>
				</v-row>
				<div
					:style="{
						height: totalHeight + 'px',
						width: '100%',
					}"
				>
					<div
						id="editor"
						:style="{
							height: totalHeight + 'px',
							width: '100%',
						}"
					></div>
				</div>
			</div>
			<div class="mt-6" style="text-align:end;">
				<v-btn dark color="#5500ff" :loading="loading" @click="save">
					완료
				</v-btn>
			</div>
		</v-col>
		<v-col cols="3">
			<div>📑코드 작성 Tip!</div>
		</v-col>
	</v-row>
</template>
<script src="" />
<script>
import { mapGetters } from 'vuex';

export default {
	components: {},
	data() {
		return {
			enabled: true,
			loading: false,
			totalHeight: 0,
			videoWidth: 0,
			editorWidth: 0,
			editorHeight: 0,
			resultHeight: 0,
			editor: null,
			innerType: {
				C: 'c_cpp',
				'C++': 'c_cpp',
				Java: 'java',
				Python: 'python',
			},
			outerType: ['C', 'C++', 'Java', 'Python'],
			selectType: 'Java',
			source_code: `import java.util.ArrayList;
import java.util.Vector;

public class InfiniteLoop {

	/*
    * This will cause the program to hang...
    *
    * Taken from:
    * http://www.exploringbinary.com/java-hangs-when-converting-2-2250738585072012e-308/
    */
	public static void main(String[] args) {
        double d = Double.parseDouble("2.2250738585072012e-308");

        // unreachable code
        System.out.println("Value: " + d);
    }
}`,
			isHorizontalDrag: false,
			isVerticalDrag: false,
		};
	},
	methods: {
		changeActive(idx) {
			this.$emit('changeActive', idx);
		},
		initCodeEditer() {
			this.editor = ace.edit('editor');
			this.editor.session.setMode('ace/mode/java');
			this.editor.setValue(this.source_code);
			this.editor.setTheme('ace/theme/chrome');
			document.getElementById('editor').style.fontSize = '16px';
		},
		save() {
			this.$store.commit('setVideoCode', this.editor.getValue());
			if (this.uploadVideo.vid_url == '') {
				alert('영상을 등록해주세요.');
				this.changeActive(2);
			} else if (this.uploadVideo.vid_title.length < 5) {
				alert('강의 제목을 5자 이상 입력해주세요.');
				this.changeActive(0);
			} else if (this.uploadVideo.vid_content == '') {
				alert('강의 내용을 입력해주세요.');
				this.changeActive(0);
			} else if (this.uploadVideo.tags.length == 0) {
				alert('태그를 1개이상 선택해주세요.');
				this.changeActive(0);
			} else {
				this.$store.dispatch('setVideo').then(({ data }) => {
					this.changeActive(3);
				});
			}
		},
		changeType() {
			this.editor.session.setMode(
				`ace/mode/${this.innerType[this.selectType]}`,
			);
			this.$store.commit(
				'setVideoCodeType',
				this.innerType[this.selectType],
			);
		},
	},
	mounted() {
		this.initCodeEditer();
		this.totalHeight = 500;
		// console.log(this.editor.getValue());
	},
	computed: {
		...mapGetters(['uploadVideo']),
	},
};
</script>

<style scoped>
.add-btn {
	border: 1px solid #c9c9c9;
	font-weight: 600;
}

.index-box {
	border: 1px solid #8c94ff;
	font-weight: 600;
	font-size: 20px;
	cursor: move;
}

.pointer {
	cursor: pointer;
}

.ghost {
	opacity: 0.5;
	background: #abb1ff;
}

.selectBorder {
	border: 3px solid #30dcff;
}

#result {
	color: rgb(230, 225, 220);
	font-weight: 500;
	font-size: 14px;
	font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen,
		Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
	overflow-y: auto;
}

#editor {
	overflow-x: auto;
}
</style>
