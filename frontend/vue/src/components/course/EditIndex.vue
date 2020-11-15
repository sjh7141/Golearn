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
						<li>
							추가된 목차는 <span class="bold">드래그</span>를
							통해 순서 변경이 가능합니다.
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
					<div
						v-if="list.length == 0"
						style="text-align: center; font-weight: 600; font-size: 20px;"
					>
						코스를 처음 생성하셨군요.<br />
						강의를 추가해 보세요! 😊
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
							:key="index + '_index'"
						>
							<v-col cols="10">
								<div class="list-icon mr-3">
									{{ index + 1 }}
								</div>
								<div
									style="display: inline-block; vertical-align:top; word-break:break-all;"
								>
									{{ element.idx_title }}
								</div>
							</v-col>
							<v-col cols="2" align="end">
								<v-icon
									class="pr-2 pointer"
									color="darken-2"
									@click="setEditIndex(index)"
									style="vertical-align:center;"
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
							<v-col cols="12">
								<v-row height="200">
									<v-col cols="1" />
									<v-col cols="3">
										<v-img
											v-if="element"
											class="border-radius-10"
											:src="
												element.map.video.vid_thumbnail
											"
											width="100%"
											style="display: inline-block;"
										/>
									</v-col>
									<v-col cols="8">
										<div
											v-if="element"
											style="margin: 0 auto;"
										>
											<div
												style="display: inline-block; vertical-align:top; word-break:break-all;"
											>
												<span
													class="bold"
													style="font-size: 20px; color:#303030;"
												>
													{{
														element.map.video
															.vid_title
													}}
												</span>
												<br />
												<span
													style="font-size: 15.75px; color:#5e5e5e; font-weight: 500;"
												>
													{{
														element.map.author
															.mbr_nick_name
													}}
													• 조회수
													{{
														element.map.video
															.vid_view
													}}회 •
													{{
														element.map.video
															.reg_dt | diffDate
													}}
												</span>
											</div>
										</div>
									</v-col>
								</v-row>
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
					다음
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
							목차 {{ isEdit ? editIdx + 1 : getOrder() }}
						</v-list-item-title>
						<v-list-item-action>
							<v-btn icon @click="resetVideo">
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
							placeholder="영상제목을 검색해주세요."
							maxlength="50"
							style="max-width: 250px; float:right;"
						>
							<v-icon slot="append" style="cursor: pointer;">
								mdi-magnify
							</v-icon>
						</v-text-field>
					</v-card-text>
					<div class="bold px-6 pb-2" style="clear:both;">제목</div>
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
					<v-card-text style="height:640px; overflow-y: scroll;">
						<template v-for="(element, index) in videoList">
							<div
								class="mb-2 border-radius-10"
								:key="index + '_vid'"
								v-show="
									element.vid_title &&
										element.vid_title.includes(search)
								"
							>
								<index-video
									:video="element"
									:idx="index"
									:selectVideoNo="selectVideoNo"
									@selectVideo="selectVideo"
								/>
							</div>
						</template>
						<div
							v-if="videoList.length == 0"
							style="text-align: center; font-weight: 600; font-size: 16px; padding-top:210px;"
						>
							활동하신 기록이 없네요.
							<br />
							영상을 업로드해보세요!😉
						</div>
					</v-card-text>
					<v-card-actions>
						<v-spacer></v-spacer>
						<v-btn
							color="error darken-1"
							text
							@click="resetVideo"
							class="bold"
						>
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
					<v-card-text class="bold">
						삭제된 목차는 복구되지 않으며 <br />
						그 동안 사용되었던 기록이 제거됩니다.
					</v-card-text>
					<v-card-actions>
						<v-spacer></v-spacer>
						<v-btn
							color="error darken-1"
							text
							@click="isDelete = false"
							class="bold"
						>
							취소
						</v-btn>
						<v-btn
							color="darken-1"
							text
							@click="confirmDelete"
							class="bold"
						>
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

let order = 1;
export default {
	components: {
		draggable,
		IndexVideo,
	},
	data() {
		return {
			enabled: true,
			list: [],
			deleteList: [],
			dragging: false,
			rules: [v => v.length > 4 || '5자이상 입력이 필요합니다.'],
			errorMessage: '',
			isDelete: false,
			deleteIdx: -1,
			isAdd: false,
			isEdit: false,
			editIdx: -1,
			editTitle: '',
			videoList: [],
			videoLikeList: [],
			loading: false,
			selectVideoNo: -1,
			search: '',
		};
	},
	methods: {
		setAdd() {
			this.isAdd = true;
			this.editTitle = '';
		},
		confirmAdd() {
			if (this.editTitle.length < 5) {
				this.errorMessage = '5자이상 입력이 필요합니다.';
				this.$refs.title.focus();
				return;
			} else if (this.selectVideoNo == -1) {
				alert('영상을 선택해 주세요.');
				return;
			} else {
				this.errorMessage = '';
			}

			var newIndex = {
				cos_no: this.$route.params.id,
				idx_no: 0,
				idx_title: this.editTitle,
				idx_order: order++,
				map: {
					video: this.videoList[this.selectVideoNo],
					author: {
						mbr_nick_name: this.videoList[this.selectVideoNo]
							.mbr_nick_name,
						mbr_profile: this.videoList[this.selectVideoNo]
							.mbr_profile,
					},
				},
				vid_no: this.videoList[this.selectVideoNo].vid_no,
				isEdit: false,
			};
			this.list.push(newIndex);
			this.editTitle = '';
			this.isAdd = false;
			this.resetVideo();
		},
		setDeleteIndex(idx) {
			this.isDelete = true;
			this.deleteIdx = idx;
		},
		setEditIndex(idx) {
			this.isEdit = true;
			this.isAdd = true;
			this.editIdx = idx;
			this.editTitle = this.list[idx].idx_title;
		},
		confirmDelete() {
			const temp = this.list[this.deleteIdx];
			this.list.splice(this.deleteIdx, 1);
			if (temp.idx_no != 0) {
				this.deleteList.push(temp);
			}
			order--;
			this.deleteIdx = -1;
			this.isDelete = false;
		},
		confirmEdit() {
			if (this.selectVideoNo == -1) {
				alert('영상을 선택해 주세요.');
				return;
			}
			this.list[this.editIdx].idx_title = this.editTitle;
			this.list[this.editIdx].isEdit = true;
			if (this.selectVideoNo != -1) {
				this.list[this.editIdx].vid_no = this.videoList[
					this.selectVideoNo
				].vid_no;
				this.list[this.editIdx].map.video = this.videoList[
					this.selectVideoNo
				];
				this.list[this.editIdx].map.author = {
					mbr_nick_name: this.videoList[this.selectVideoNo]
						.mbr_nick_name,
					mbr_profile: this.videoList[this.selectVideoNo].mbr_profile,
				};
			}
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
		save() {
			let deleteList = [];
			let insertList = [];
			let updateList = [];
			for (let index of this.deleteList) {
				deleteList.push({
					idx_no: index.idx_no,
				});
			}
			for (let idx in this.list) {
				let index = this.list[idx];
				if (
					(Number(idx) + 1 != index.idx_order || index.isEdit) &&
					index.idx_no != 0
				) {
					updateList.push({
						cos_no: index.cos_no,
						idx_order: Number(idx) + 1,
						idx_title: index.idx_title,
						vid_no: index.vid_no,
						idx_no: index.idx_no,
					});
				}
				if (index.idx_no == 0) {
					insertList.push({
						cos_no: index.cos_no,
						idx_order: Number(idx) + 1,
						idx_title: index.idx_title,
						vid_no: index.vid_no,
					});
				}
			}
			this.loading = true;
			this.$store
				.dispatch('setIndex', {
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
		...mapGetters(['course', 'user']),
	},
	mounted() {
		this.$store
			.dispatch('getIndex', this.$route.params.id)
			.then(({ data }) => {
				for (let index of data) {
					index.isEdit = false;
				}
				this.list = data;
				order = data.length + 1;
			});
		this.$store.dispatch('getVideos').then(({ data }) => {
			for (let video of data) {
				video.mbr_nick_name = this.user.nickname;
				video.mbr_profile = this.user.profile;
			}
			this.videoList = data;
		});
		this.$store.dispatch('getLikeVideos').then(({ data }) => {
			this.videoLikeList = data;
			for (let video of data) {
				this.$store
					.dispatch('getUserByNo', video.mbr_no)
					.then(({ data }) => {
						video.mbr_nick_name = data.nickname;
						video.mbr_profile = data.profile;
						this.videoList.push(video);
					});
			}
		});
	},
	filters: {
		diffDate(val) {
			let diff = (new Date() - new Date(val)) / 1000;
			if (diff < 60) {
				return '방금 전';
			}
			diff /= 60;
			if (diff < 60) {
				return parseInt(diff) + '분 전';
			}

			diff /= 60;
			if (diff < 24) {
				return parseInt(diff) + '시간 전';
			}

			diff /= 24;
			if (diff < 7) {
				return parseInt(diff) + '일 전';
			}
			if (diff < 30) {
				return parseInt(diff / 7) + '주 전';
			}
			if (diff < 365) {
				return parseInt(diff / 30) + '달 전';
			}
			return parseInt(diff / 365) + '년 전';
		},
	},
};
</script>

<style scoped>
.add-btn {
	border: 1px solid #c9c9c9;
	font-weight: 600;
}

.index-box {
	border: 1px solid #e3e3e3;
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
