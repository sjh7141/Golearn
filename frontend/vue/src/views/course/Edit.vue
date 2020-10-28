<template>
	<div>
		<v-progress-linear :value="(select + 1) * 25"></v-progress-linear>
		<v-container fluid class="course-wrapper py-0 ">
			<v-row class="height-100">
				<v-col md="2">
					<v-row class="mx-2">
						<v-col
							v-for="(list, index) in iconList"
							cols="12"
							class="pa-3 my-2 list-wrapper d-flex align-center"
							:class="{ 'list-active': isActive(index) }"
							:key="list"
							@click="select = index"
						>
							<div class="list-icon mr-3">{{ index + 1 }}</div>
							<span>{{ list }}</span>
						</v-col>
					</v-row>
				</v-col>
				<v-col md="10" style="border-left: 1px solid #f2f2f2">
					<edit-info v-show="select == 0" />
					<edit-cover v-show="select == 1" />
					<edit-index v-show="select == 2" />
					<edit-manager v-show="select == 3" />
				</v-col>
			</v-row>
		</v-container>
	</div>
</template>

<script>
import EditInfo from '@/components/course/EditInfo.vue';
import EditCover from '@/components/course/EditCover.vue';
import EditIndex from '@/components/course/EditIndex.vue';
import EditManager from '@/components/course/EditManager.vue';

export default {
	components: {
		EditInfo,
		EditCover,
		EditIndex,
		EditManager,
	},
	data() {
		return {
			id: this.$route.params.id,
			iconList: ['강의정보', '커버 이미지', '목차', '관리자'],
			select: 0,
		};
	},
	methods: {
		isActive(idx) {
			if (idx == this.select) {
				return true;
			}
			return false;
		},
	},
};
</script>

<style scoped>
.course-wrapper {
	min-height: 869px;
}

.list-icon {
	display: inline-block;
	border: 1px solid gray;
	width: 23px;
	height: 23px;
	text-align: center;
	font-size: 13px;
	border-radius: 6px;
}

.list-wrapper {
	border-radius: 6px;
	cursor: pointer;
}

.list-active {
	background-color: #ededed;
}

.list-wrapper:hover {
	background-color: #e3e3e3;
}
</style>
