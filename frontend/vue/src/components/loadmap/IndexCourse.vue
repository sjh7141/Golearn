<template>
	<v-row height="200">
		<v-col
			class="mr-3 pb-0"
			@click="select"
			style="color:black; cursor: pointer;"
		>
			<v-img
				class="border-radius-10"
				:src="course.cos_thumbnail"
				width="550"
				height="300"
				style="display: inline-block;"
				:style="{
					border:
						selectCourseNo == idx
							? '3px solid #6952bf'
							: '1px solid rgba(0,0,0,0.12)',
				}"
			/>
			<!-- <v-fab-transition>
				<v-icon
					v-if="selectCourseNo == idx"
					x-large
					icon
					color="pink"
					style="position:absolute; right:40px;"
				>
					mdi-check-circle
				</v-icon>
			</v-fab-transition> -->
		</v-col>
		<v-col
			class="mx-3 pt-0"
			cols="12"
			@click="select"
			style="cursor: pointer;"
		>
			<div
				class="pl-2"
				style="display: inline-block; vertical-align:top; word-break:break-all;"
			>
				<span class="bold" style="font-size: 20px; color:#303030;">
					{{ course.cos_title }}
				</span>
				<br />
				By {{ course.mbr_nickname }}
				<v-row>
					<v-col cols="12" class="py-0">
						<span
							class="mr-1"
							v-for="(tag, i) in course.tags"
							:key="i"
							style="font-size:14px; color:#ababab; font-weight:500;"
						>
							#{{ tag }}
						</span>
					</v-col>
				</v-row>
			</div>
		</v-col>
	</v-row>
</template>

<script>
export default {
	props: ['course', 'idx', 'selectCourseNo'],
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
	data() {
		return {};
	},
	methods: {
		select() {
			this.$emit('selectCourse', this.idx);
		},
	},
	mounted() {},
};
</script>

<style scoped></style>
