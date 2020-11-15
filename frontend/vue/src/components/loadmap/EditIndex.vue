<template>
	<v-row class="ml-3 height-100">
		<v-col cols="9">
			<h2>코스 순서</h2>
			<h4 class="pb-5 pl-3" style="color:gray;">
				"로드맵에 구성될 코스들을 순서에 맞게 배치해 주세요."
			</h4>
			<div class="mt-5">
				<div class="pa-6 mb-6 bg-light-gray border-radius-10">
					<div class="bold">
						코스 등록
					</div>
					<ul style="list-style-type: none;">
						<li>
							로드맵은 다양한 코스를 모아
							<span class="bold">하나의 커리큘럼</span>으로
							만듭니다.
						</li>
						<li>
							로드맵의 목표에 맞는
							<span class="bold">코스</span>를 선택해 주세요.
						</li>
						<li>
							여러개의 코스들을 통해 분야에 대한
							<span class="bold">학습흐름을 구성</span>하는 방식을
							권장합니다.
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
							<span style="font-size:15px;">코스 추가</span>
						</v-btn>
					</div>
					<div
						v-if="list.length == 0"
						style="text-align: center; font-weight: 600; font-size: 20px;"
					>
						로드맵을 처음 생성하셨군요.<br />
						코스를 추가해 보세요! 😊
					</div>
					<draggable
						:list="list"
						:disabled="!enabled"
						class="list-group"
						ghost-class="ghost"
						@start="dragging = true"
						@end="dragging = false"
					>
						<v-row
							class="list-group-item ma-1 pa-3 mb-5 index-box"
							v-for="(element, index) in list"
							:key="element.cosNo"
						>
							<v-col cols="10">
								<div class="list-icon mr-3">
									{{ index + 1 }}
								</div>
								<v-img
									class="ml-2 mr-4 border-radius-10"
									:src="element.cos_thumbnail"
									height="130"
									width="210"
									style="display: inline-block;"
								/>
								<div
									style="display: inline-block; vertical-align:top; word-break:break-all;"
								>
									<span class="bold" style="font-size: 20px;">
										{{ element.cos_title }}
									</span>
									<br />
									<span style="font-size: 17px;">
										By {{ element.mbr_nickname }}
									</span>
									<br />
									<v-row>
										<v-col cols="12" class="py-0">
											<span
												class="mr-1"
												v-for="(tag, i) in element.tags"
												:key="i"
												style="font-size:14px; color:#ababab; font-weight:500;"
											>
												#{{ tag }}
											</span>
										</v-col>
									</v-row>
								</div>
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
				<v-btn
					outlined
					class="mr-3"
					style="border: 1px solid #c9c9c9;"
					@click="save"
					:loading="loading"
				>
					저장
				</v-btn>
				<v-btn dark color="#5500ff" @click="changeActive">
					완료
				</v-btn>
			</div>
			<!-- 추가&수정 dialog -->
			<v-dialog v-model="isAdd" max-width="600">
				<v-card>
					<v-list-item class="pb-6">
						<v-list-item-title
							class="headline"
							style="font-weight: 600 !important"
						>
							순서 {{ isEdit ? editIdx + 1 : getOrder() }}
						</v-list-item-title>
						<v-list-item-action>
							<v-btn icon @click="resetCourse">
								<v-icon>
									mdi-close
								</v-icon>
							</v-btn>
						</v-list-item-action>
					</v-list-item>
					<v-card-text class="pb-0">
						<v-text-field
							v-model="search"
							ref="search"
							color="rgba(30, 30, 30, 0.5)"
							dense
							outlined
							hide-details
							placeholder="코스제목을 검색해주세요."
							maxlength="50"
							style="max-width: 250px; float:right;"
						>
							<v-icon slot="append" style="cursor: pointer;">
								mdi-magnify
							</v-icon>
						</v-text-field>
					</v-card-text>
					<div class="bold px-6 pb-2" style="clear:both;">
						코스목록
					</div>
					<v-card-text style="height:640px; overflow-y: scroll;">
						<template v-for="(element, index) in courseList">
							<div
								class="mb-2 border-radius-10"
								:key="element.cos_no"
								v-show="
									element.cos_title &&
										element.cos_title.includes(search)
								"
							>
								<index-course
									:course="element"
									:idx="index"
									:selectCourseNo="selectCourseNo"
									@selectCourse="selectCourse"
								/>
							</div>
						</template>
						<div
							v-if="courseList.length == 0"
							style="text-align: center; font-weight: 600; font-size: 16px; padding-top:210px;"
						>
							활동하신 기록이 없네요.
							<br />
							코스를 좋아요한 뒤 목록에 추가해보세요!😉
						</div>
						<!-- <v-pagination
							v-model="page"
							:length="30"
							total-visible="7"
							@input="next"
						></v-pagination> -->
					</v-card-text>
					<v-card-actions>
						<v-spacer></v-spacer>
						<v-btn color="error darken-1" text @click="resetCourse">
							<span class="bold">취소</span>
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
					<v-card-text class="bold">
						삭제된 코스목록은 복구되지 않으며 <br />
						그 동안 사용되었던 기록이 제거됩니다.
					</v-card-text>
					<v-card-actions>
						<v-spacer></v-spacer>
						<v-btn
							color="error darken-1"
							text
							@click="isDelete = false"
						>
							<span class="bold">취소</span>
						</v-btn>
						<v-btn color="darken-1" text @click="confirmDelete">
							<span class="bold">확인</span>
						</v-btn>
					</v-card-actions>
				</v-card>
			</v-dialog>
		</v-col>
		<v-col cols="3">
			<div>📑코스 설정 방법</div>
		</v-col>
	</v-row>
</template>
<script>
import draggable from 'vuedraggable';
import IndexCourse from '@/components/loadmap/IndexCourse.vue';
import { mapGetters } from 'vuex';

let order = 1;
export default {
	components: {
		draggable,
		IndexCourse,
	},
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
			courseList: [],
			selectCourseNo: -1,
			page: 1,
			loading: false,
			search: '',
		};
	},
	methods: {
		setAdd() {
			this.isAdd = true;
		},
		confirmAdd() {
			if (this.selectCourseNo == -1) {
				alert('코스를 선택해 주세요.');
				return;
			}
			let selected = this.courseList[this.selectCourseNo];
			selected.no = 0;
			selected.isEdit = false;
			this.list.push(selected);
			this.isAdd = false;
			this.resetCourse();
		},
		setDeleteIndex(idx) {
			this.isDelete = true;
			this.deleteIdx = idx;
		},
		setEditIndex(idx) {
			this.isEdit = true;
			this.isAdd = true;
			this.editIdx = idx;
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
			if (this.selectCourseNo == -1) {
				alert('코스를 선택해 주세요.');
				return;
			}
			const selected = this.courseList[this.selectCourseNo];
			selected.isEdit = true;
			if (this.list[this.editIdx].no == 0) {
				selected.no = 0;
			}
			this.list[this.editIdx] = selected;

			this.editIdx = -1;
			this.isEdit = false;
			this.isAdd = false;
			this.resetCourse();
		},
		getOrder() {
			return order;
		},
		selectCourse(idx) {
			this.selectCourseNo = idx;
		},
		resetCourse() {
			this.selectCourseNo = -1;
			this.isAdd = false;
		},
		changeActive() {
			this.$emit('changeActive');
		},
		next(page) {
			//페이징 미정
			this.page = page;
		},
		save() {
			let deleteList = [];
			let insertList = [];
			let updateList = [];
			for (let course of this.deleteList) {
				deleteList.push({
					no: course.no,
				});
			}
			for (let idx in this.list) {
				let course = this.list[idx];
				if (
					(Number(idx) + 1 != course.ldm_order || course.isEdit) &&
					course.no != 0
				) {
					updateList.push({
						no: course.no,
						ldm_order: Number(idx) + 1,
						cos_no: course.cos_no,
						ldm_no: Number(this.$route.params.id),
					});
				}
				if (course.no == 0) {
					insertList.push({
						ldm_order: Number(idx) + 1,
						cos_no: course.cos_no,
						ldm_no: Number(this.$route.params.id),
					});
				}
			}

			this.loading = true;
			this.$store
				.dispatch('setLoadmap', {
					delete: deleteList,
					insert: insertList,
					update: updateList,
				})
				.finally(() => {
					this.loading = false;
				});
		},
	},
	computed: {
		...mapGetters(['loadmap', 'loadmapCourse']),
	},
	mounted() {
		this.list = this.loadmapCourse;
		order = this.loadmapCourse.length + 1;
		this.$store.dispatch('getLikeCourses').then(({ data }) => {
			for (let course of data) {
				this.$store
					.dispatch('getUserByNo', course.mbr_no)
					.then(({ data }) => {
						course.mbr_nickname = data.nickname;
						course.mbr_profile = data.profile;
						this.$store
							.dispatch('getCourseTag', course.cos_no)
							.then(({ data }) => {
								course.tags = [];
								for (let tag of data) {
									course.tags.push(tag.tag_name);
								}
								this.courseList.push(course);
							});
					});
			}
		});
	},
};
</script>

<style scoped>
.add-btn {
	border: 1px solid #c9c9c9;
	font-weight: 600;
}

.index-box {
	border: 3px solid #dedede;
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

.list-icon {
	display: inline-block;
	border: 1px solid gray;
	width: 25px;
	height: 25px;
	text-align: center;
	font-size: 15px;
	border-radius: 6px;
	vertical-align: top;
}
</style>
