<template>
	<div></div>
</template>

<script>
import { mapActions } from 'vuex';
export default {
	mounted() {
		this.getLoadmap(this.$route.params.ldm_no).then(({ data }) => {
			if (data.course.length == 0) {
				alert('유효하지 않은 로드맵입니다.');
				this.$router.push('/roadmap');
			} else {
				console.log(data);
				this.$router.push(
					`/course/${data.course[0].cos_no}/intro?ldm_no=${data.loadmap.ldm_no}`,
				);
			}
		});
	},
	methods: {
		...mapActions(['getLoadmap']),
	},
};
</script>

<style></style>
