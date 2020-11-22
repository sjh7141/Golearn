<template>
	<v-row>
		<v-col cols="12">
			<video-list
				class="pb-8"
				:videos="videos.slice(0, lengthVideo)"
				@setNo="setNo"
				:flag="true"
			>
			</video-list>
			<course-list
				class="pb-8"
				:courses="courses.slice(0, 4)"
				@setNo="setNo"
				:flag="true"
				:type="0"
			>
			</course-list>
			<course-list
				:courses="courses.slice(0, 4)"
				@setNo="setNo"
				:flag="true"
				:type="1"
			>
			</course-list>
		</v-col>
	</v-row>
</template>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
import { mapActions, mapGetters } from 'vuex';
import VideoList from '@/components/mypage/VideoList';
import CourseList from '@/components/mypage/CourseList';

export default {
	components: { VideoList, CourseList },
	props: ['videos', 'courses'],
	data() {
		return {
			lengthVideo: 6,
		};
	},
	computed: {
		...mapGetters(['user']),
	},
	methods: {
		...mapActions(['getMember', 'isLike', 'upload', 'setBanner']),
		setNo(idx) {
			this.$emit('setNo', idx);
		},
	},
	mounted() {
		window.innerWidth <= 1264
			? (this.lengthVideo = 4)
			: (this.lengthVideo = 6);

		var self = this;
		$(window).resize(function() {
			var width_size = window.innerWidth;
			if (width_size <= 1264) {
				//sm md
				self.lengthVideo = 4;
			} else {
				//lg
				self.lengthVideo = 6;
			}
		});
	},
};
</script>

<style></style>
