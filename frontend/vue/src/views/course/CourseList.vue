<template>
	<div ref="app">
		<div id="cl-banner" />
		<course-list> </course-list>
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

<style scoped>
#cl-banner {
	height: 200px;
	width: 100%;
	background-image: url('../../assets/course_banner.gif');
	background-repeat: no-repeat;
	background-size: cover;
}
</style>
