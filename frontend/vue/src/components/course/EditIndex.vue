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
						<v-btn outlined class="add-btn" @click="add">
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
						<transition-group type="transition" name="flip-list">
							<v-row
								class="list-group-item ma-1 pa-3 mb-5 index-box"
								v-for="(element, index) in list"
								:key="element.name"
							>
								<v-col cols="10">
									{{ element.name }}
								</v-col>
								<v-col cols="2" align="end">
									<v-icon
										class="pr-2 pointer"
										color="darken-2"
									>
										mdi-file-edit-outline
									</v-icon>
									<v-icon
										class="pointer"
										color="darken-2"
										@click="deleteIndex(index)"
									>
										mdi-trash-can-outline
									</v-icon>
								</v-col>
							</v-row>
						</transition-group>
					</draggable>
				</div>
			</div>
			<div class="mt-6" style="text-align:end;">
				<v-btn outlined class="mr-3" style="border: 1px solid #c9c9c9;">
					저장
				</v-btn>
				<v-btn dark color="#5500ff">
					다음
				</v-btn>
			</div>
		</v-col>
		<v-col cols="3">
			<div>📑목차 작성 방법</div>
		</v-col>
	</v-row>
</template>
<script>
import draggable from 'vuedraggable';
let id = 2;
export default {
	components: {
		draggable,
	},
	data() {
		return {
			enabled: true,
			list: [{ name: '목차의 제목을 입력해 주세요.', id: 1 }],
			dragging: false,
		};
	},
	methods: {
		add() {
			this.list.push({
				name: '목차의 제목을 입력해 주세요.' + id,
				id: id++,
			});
		},
		replace() {
			this.list = [{ name: 'Edgard', id: id++ }];
		},
		checkMove(e) {
			window.console.log('Future index: ' + e.draggedContext.futureIndex);
		},
		deleteIndex(idx) {
			console.log(idx);
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
</style>
