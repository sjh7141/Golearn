<template>
	<div ref="app">
		<v-card class="transparent" flat v-if="courses.length !== 0">
			<v-card-title class="px-0">
				<v-row align="center" class="pb-5">
					<div class="modify-text">
						{{ this.title[this.type] }}
					</div>
					<v-spacer />
					<div
						v-if="flag"
						@click="goToLikeCourse"
						style="cursor: pointer;"
					>
						<span class="bold" style="font-size: 20px;">
							더보기
						</span>
						<v-icon large color="blue-grey">
							mdi-chevron-right
						</v-icon>
					</div>
				</v-row>
			</v-card-title>
			<v-row>
				<v-col
					cols="12"
					sm="6"
					md="4"
					lg="3"
					v-for="(course, i) in courses"
					:key="i"
					class="mx-xs-auto"
				>
					<v-skeleton-loader type="card-avatar">
						<course-card
							:card="{ maxWidth: 250 }"
							:course="course"
						></course-card>
					</v-skeleton-loader>
				</v-col>
			</v-row>
		</v-card>
	</div>
</template>

<script>
import CourseCard from '@/components/component/CourseCard';
export default {
	props: ['courses', 'flag', 'type'],
	data() {
		return {
			title: ['좋아요한 코스', '수강중인 코스'],
		};
	},
	computed: {},
	components: {
		CourseCard,
	},
	mounted() {},
	methods: {
		goToLikeCourse() {
			this.$emit('setNo', 3 + this.type);
		},
	},
};
</script>

<style scoped>
.card {
	background: #f9f9f9 !important;
}
.modify-text {
	font-size: 30px;
	font-weight: 600;
}
</style>
