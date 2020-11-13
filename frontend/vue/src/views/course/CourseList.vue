<template>
	<div ref="app">
		<v-container>
			<course-list :courses="courses"> </course-list>
		</v-container>
	</div>
</template>

<script>
import CourseList from '@/components/component/CourseSearchList';
export default {
	components: { CourseList },

	data() {
		return {};
	},
	methods: {},
	mounted() {},
};
</script>

<style></style>
