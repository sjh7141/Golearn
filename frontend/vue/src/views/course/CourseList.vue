<template>
	<div ref="app">
		<v-container>
			<course-list :courses="courses"> </course-list>
		</v-container>
	</div>
</template>

<script>
import { mapActions } from 'vuex';
import CourseList from '@/components/component/CourseSearchList';
export default {
	components: { CourseList },

	data() {
		return {
			search: '',
			pageNo: 1,
			courses: [],
		};
	},
	methods: {
		//getSearchResult(context, search, tags, page_no, type) {
		...mapActions(['getSearchResult']),
		getCourse(search) {
			console.log(search);
			const payload = {
				search: search ? search : '',
				page_no: 1,
				type: 'course',
			};
			this.getSearchResult(payload).then(res => {
				console.log(res);
				this.courses = res.data.course;
			});
		},
	},
	mounted() {
		this.getCourse(this.$route.query.search);
	},
};
</script>

<style></style>
