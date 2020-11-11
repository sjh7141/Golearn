<template>
	<v-row>
		<v-col cols="12">
			<course-list :courses="courses" :flag="false" :type="1" />
		</v-col>
	</v-row>
</template>

<script>
import CourseList from '@/components/mypage/CourseList';

export default {
	components: { CourseList },
	props: ['courses'],
	data() {
		return {};
	},
	computed: {},
	methods: {},
	mounted() {},
};
</script>

<style></style>
