<template>
	<v-row class="ml-3 height-100">
		<v-col cols="9">
			<h2>목차</h2>
			<h4 class="pb-5 pl-3" style="color:gray;">
				"코스에 구성될 강의들을 순서에 맞게 작성해 주세요."
			</h4>
			<div class="mt-5">
				<div class="pa-6 mb-6 bg-light-gray border-radius-10">
					<div class="bold">
						목차 등록
					</div>
					<ul style="list-style-type: none;">
						<li>
							목차는 코스의 커리큘럼을 나타냅니다.
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
				<div>
					<div class="pb-8" style="text-align:end;">
						<v-btn outlined class="add-btn" @click="setAdd">
							<v-icon color="darken-3">
								mdi-plus
							</v-icon>
							<span style="font-size:15px;">강의 추가</span>
						</v-btn>
					</div>
					<draggable
						:list="list"
						:disabled="!enabled"
						class="list-group"
						ghost-class="ghost"
						:move="checkMove"
						@start="dragging = true"
						@end="dragging = false"
					>
						<v-row
							class="list-group-item ma-1 pa-3 mb-5 index-box"
							v-for="(element, index) in list"
							:key="element.index"
						>
							<v-col cols="10">
								목차{{ index + 1 }}: {{ element.name }}
							</v-col>
							<v-col cols="2" align="end">
								<v-icon
									class="pr-2 pointer"
									color="darken-2"
									@click="setEditIndex(index)"
								>
									mdi-file-edit-outline
								</v-icon>
								<v-icon
									class="pointer"
									color="darken-2"
									@click="setDeleteIndex(index)"
								>
									mdi-trash-can-outline
								</v-icon>
							</v-col>
						</v-row>
					</draggable>
				</div>
			</div>
			<div class="mt-6" style="text-align:end;">
				<v-btn outlined class="mr-3" style="border: 1px solid #c9c9c9;">
					저장
				</v-btn>
				<v-btn dark color="#5500ff" @click="changeActive">
					다음
				</v-btn>
			</div>
			<!-- 추가&수정 dialog -->
			<v-dialog v-model="isAdd" max-width="600">
				<v-card>
					<v-card-title class="headline pb-6">
						<span class="bold">
							목차 {{ isEdit ? editIdx + 1 : getOrder() }}
						</span>
					</v-card-title>
					<div class="bold px-6 pb-2">제목</div>
					<v-card-text class="pb-0">
						<v-text-field
							v-model="editTitle"
							:rules="rules"
							ref="title"
							filled
							placeholder="제목입력 후 강의 영상을 선택해 주세요."
							maxlength="30"
						></v-text-field>
					</v-card-text>
					<div class="bold px-6 pb-2">영상목록</div>
					<v-card-text>
						<template v-for="(element, index) in videoList">
							<div
								class="mb-2 border-radius-10"
								:class="{
									selectBorder: index == selectVideoNo,
								}"
								:key="element.vidno"
							>
								<index-video
									:video="element"
									:idx="index"
									@selectVideo="selectVideo"
								/>
							</div>
						</template>
					</v-card-text>
					<v-card-actions>
						<v-spacer></v-spacer>
						<v-btn color="error darken-1" text @click="resetVideo">
							취소
						</v-btn>
						<v-btn
							color="darken-1"
							text
							@click="isEdit ? confirmEdit() : confirmAdd()"
						>
							<span class="bold">확인</span>
						</v-btn>
					</v-card-actions>
				</v-card>
			</v-dialog>
			<!-- 삭제 dialog -->
			<v-dialog v-model="isDelete" max-width="350">
				<v-card>
					<v-card-title class="headline">
						<span class="bold">정말 삭제하시겠습니까?</span>
					</v-card-title>
					<v-card-text>
						삭제된 목차는 복구되지 않으며 <br />
						그 동안 사용되었던 기록이 제거됩니다.
					</v-card-text>
					<v-card-actions>
						<v-spacer></v-spacer>
						<v-btn
							color="error darken-1"
							text
							@click="isDelete = false"
						>
							취소
						</v-btn>
						<v-btn color="darken-1" text @click="confirmDelete">
							확인
						</v-btn>
					</v-card-actions>
				</v-card>
			</v-dialog>
		</v-col>
		<v-col cols="3">
			<div>📑목차 작성 방법</div>
		</v-col>
	</v-row>
</template>
<script>
import draggable from 'vuedraggable';
import IndexVideo from '@/components/course/IndexVideo.vue';
import { mapGetters } from 'vuex';

let order = 3;
const nameTemplate = '제목입력과 강의영상을 선택해 주세요.';
export default {
	components: {
		draggable,
		IndexVideo,
	},
	data() {
		return {
			enabled: true,
			list: [
				{ name: nameTemplate, no: 122, order: 1, vid_no: 1 },
				{ name: nameTemplate, no: 232, order: 2, vid_no: 2 },
			],
			deleteList: [],
			dragging: false,
			rules: [v => v.length > 4 || '5자이상 입력이 필요합니다.'],
			isDelete: false,
			deleteIdx: -1,
			isAdd: false,
			isEdit: false,
			editIdx: -1,
			editTitle: '',
			videoList: [
				{
					vidNo: 3,
					mbrNo: 2,
					vidPno: 0,
					vidTitle: '테스트 영상',
					vidContent: null,
					vidUrl: null,
					vidView: 18,
					regDt: '2020-10-27T11:26:14.000+00:00',
					vidHide: true,
					vidThumbnail: 'video_default_thumbnail.png',
					vidLength: 0,
				},
				{
					vidNo: 3,
					mbrNo: 2,
					vidPno: 0,
					vidTitle: '테스트 영상',
					vidContent: null,
					vidUrl: null,
					vidView: 18,
					regDt: '2020-10-27T11:26:14.000+00:00',
					vidHide: true,
					vidThumbnail: 'video_default_thumbnail.png',
					vidLength: 0,
				},
				{
					vidNo: 3,
					mbrNo: 2,
					vidPno: 0,
					vidTitle: '테스트 영상',
					vidContent: null,
					vidUrl: null,
					vidView: 18,
					regDt: '2020-10-27T11:26:14.000+00:00',
					vidHide: true,
					vidThumbnail: 'video_default_thumbnail.png',
					vidLength: 0,
				},
				{
					vidNo: 3,
					mbrNo: 2,
					vidPno: 0,
					vidTitle: '테스트 영상 테스트 영상 테스트 영상',
					vidContent: null,
					vidUrl: null,
					vidView: 18,
					regDt: '2020-10-27T11:26:14.000+00:00',
					vidHide: true,
					vidThumbnail: 'video_default_thumbnail.png',
					vidLength: 0,
				},
				{
					vidNo: 3,
					mbrNo: 2,
					vidPno: 0,
					vidTitle: '테스트 영상',
					vidContent: null,
					vidUrl: null,
					vidView: 18,
					regDt: '2020-10-27T11:26:14.000+00:00',
					vidHide: true,
					vidThumbnail: 'video_default_thumbnail.png',
					vidLength: 0,
				},
				{
					vidNo: 3,
					mbrNo: 2,
					vidPno: 0,
					vidTitle: '테스트 영상',
					vidContent: null,
					vidUrl: null,
					vidView: 18,
					regDt: '2020-10-27T11:26:14.000+00:00',
					vidHide: true,
					vidThumbnail: 'video_default_thumbnail.png',
					vidLength: 0,
				},
				{
					vidNo: 3,
					mbrNo: 2,
					vidPno: 0,
					vidTitle: '테스트 영상',
					vidContent: null,
					vidUrl: null,
					vidView: 18,
					regDt: '2020-10-27T11:26:14.000+00:00',
					vidHide: true,
					vidThumbnail: 'video_default_thumbnail.png',
					vidLength: 0,
				},
			],
			selectVideoNo: -1,
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
</style>
