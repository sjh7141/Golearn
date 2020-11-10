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
				<div
					:style="{
						height: totalHeight + 'px',
						width: '100%',
					}"
					v-show="editMode"
				>
					<div
						id="editor"
						:style="{
							height: editorHeight + 'px',
							width: '100%',
						}"
					></div>
					<div
						style="cursor:n-resize; background-color:grey"
						:style="{
							height: BORDER_SIZE + 'px',
							width: '100%',
						}"
						@mousedown="isVerticalDrag = true"
					></div>
					<div
						id="result"
						style="background-color:rgb(28, 28, 28)"
						:style="{
							height: resultHeight + 'px',
							width: '100%',
						}"
					>
						<div
							style="width:100%; height:100%; padding:3px 0px 0px 10px;"
						>
							<div>
								출력
							</div>
							<div style="margin-top:10px;">
								Value: 2.2250738585072014E-308
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="mt-6" style="text-align:end;">
				<v-btn outlined class="mr-3" style="border: 1px solid #c9c9c9;">
					저장
				</v-btn>
				<v-btn dark color="#5500ff" @click="changeActive">
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

let order = 1;
// const nameTemplate = '제목입력과 강의영상을 선택해 주세요.';
export default {
	components: {},
	data() {
		return {
			enabled: true,
			list: [],
			deleteList: [],
			dragging: false,
			rules: [v => v.length > 4 || '5자이상 입력이 필요합니다.'],
			isDelete: false,
			deleteIdx: -1,
			isAdd: false,
			isEdit: false,
			editIdx: -1,
			editTitle: '',
			BORDER_SIZE: 4,
			height: 800,
			width: 800,
			totalHeight: 0,
			videoWidth: 0,
			editorWidth: 0,

			editorHeight: 0,
			resultHeight: 0,

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

			editMode: true,
		};
	},
	methods: {
		setAdd() {
			this.isAdd = true;
			this.editTitle = '';
		},
		confirmAdd() {
			if (this.editTitle.length < 5) {
				this.$refs.title.focus();
				return;
			} else if (this.selectVideoNo == -1) {
				alert('영상을 선택해 주세요.');
				return;
			}
			this.list.push({
				name: this.editTitle,
				no: 0,
				order: order++,
				vid_no: this.selectVideoNo,
			});
			this.editTitle = '';
			this.isAdd = false;
			this.resetVideo();
		},
		checkMove() {
			//e) {
			// window.console.log('Future index: ' + e.draggedContext.futureIndex);
		},
		setDeleteIndex(idx) {
			this.isDelete = true;
			this.deleteIdx = idx;
		},
		setEditIndex(idx) {
			this.isEdit = true;
			this.isAdd = true;
			this.editIdx = idx;
			this.editTitle = this.list[idx].name;
		},
		confirmDelete() {
			const temp = this.list[this.deleteIdx];
			this.list.splice(this.deleteIdx, 1);
			if (temp.no != 0) {
				this.deleteList.push(temp);
			}
			order--;
			this.deleteIdx = -1;
			this.isDelete = false;
		},
		confirmEdit() {
			this.list[this.editIdx].name = this.editTitle;
			this.editIdx = -1;
			this.editTitle = '';
			this.isEdit = false;
			this.isAdd = false;
			this.resetVideo();
		},
		getOrder() {
			return order;
		},
		selectVideo(idx) {
			this.selectVideoNo = idx;
		},
		resetVideo() {
			this.selectVideoNo = -1;
			this.isAdd = false;
		},
		changeActive() {
			this.$emit('changeActive');
		},
		dragStart(e) {},
		dragEnd(e) {},
		resizeWidth(e) {
			if (this.isHorizontalDrag) {
				this.videoWidth = e.clientX - this.BORDER_SIZE;
				this.editorWidth =
					window.innerWidth - this.videoWidth - this.BORDER_SIZE;
			}
		},
		resizeHeight(e) {
			if (this.isVerticalDrag && e.clientY < window.innerHeight) {
				this.editorHeight =
					e.clientY - this.$refs.app.offsetTop - this.BORDER_SIZE;
				this.resultHeight =
					this.totalHeight - this.editorHeight - this.BORDER_SIZE;
			}
		},
		resizeVideo() {
			this.totalHeight = window.innerHeight - 64;

			if (this.videoWidth > window.innerWidth)
				this.videoWidth = window.innerWidth - this.BORDER_SIZE;
			if (this.editorHeight > this.totalHeight)
				this.editorHeight = this.totalHeight - this.BORDER_SIZE;

			this.editorWidth =
				window.innerWidth - this.videoWidth - this.BORDER_SIZE;
			this.resultHeight =
				this.totalHeight - this.editorHeight - this.BORDER_SIZE;
		},
		createChapterButton() {
			const self = this;

			var Button = videojs.getComponent('Button');
			var MyButton = videojs.extend(Button, {
				constructor: function() {
					Button.apply(this, arguments);
					this.addClass('vjs-chapters-button');
				},
				handleClick: function() {
					self.editMode = !self.editMode;
				},
			});
			videojs.registerComponent('MyButton', MyButton);
			var player = videojs('video-player');
			player.getChild('controlBar').addChild('myButton', {});
		},
		initCodeEditer() {
			var editor = ace.edit('editor');
			editor.session.setMode('ace/mode/java');
			editor.setValue(this.source_code);
			editor.setTheme('ace/theme/merbivore_soft');
			document.getElementById('editor').style.fontSize = '16px';
		},
	},
	mounted() {
		// this.createChapterButton();
		this.initCodeEditer();

		window.addEventListener('resize', this.resizeVideo);
		this.videoWidth = window.innerWidth * 0.6;
		this.totalHeight = window.innerHeight - 64;
		this.editorHeight = this.totalHeight * 0.66 - this.BORDER_SIZE;

		this.resizeVideo();
	},
	computed: {
		...mapGetters(['course']),
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
</style>
