<template>
	<div ref="app">
		<v-card class="transparent" flat v-if="courses.data.length !== 0">
			<v-card-title>코스</v-card-title>
			<v-row>
				<v-col
					cols="12"
					sm="6"
					md="4"
					lg="3"
					v-for="(course, i) in loading ? 10 : courses.data"
					:key="i"
					class="mx-xs-auto"
				>
					<v-skeleton-loader type="card-avatar" :loading="loading">
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
import { mapGetters, mapActions } from 'vuex';
import CourseCard from './CourseCard';
export default {
	data: () => ({
		loading: false,
		errored: false,
		showSubBtn: true,
		items: [
			{ name: '홈', link: '/home' },
			{ name: '동영상', link: '/videos' },
		],
		courses: {},
		channel: {
			mbr_nickname: 'asm9677',
			_id: 'asdf',
		},
		currentUser: {
			_id: 'asdf',
		},
		signinDialog: false,
		details: {},
	}),
	computed: {
		...mapGetters(['isAuthenticated']),
	},
	components: {
		CourseCard,
	},
	methods: {
		...mapActions(['getChannelCourses']),
		async getChannel(id) {
			this.loading = true;
			this.errored = false;

			this.getChannelCourses(id).then(res => {
				this.courses = res;
			});

			this.loading = false;
		},
		subscribe() {},
	},
	mounted() {
		this.getChannel(this.$route.params.id);
	},
	beforeRouteUpdate(to, from, next) {
		this.getChannel(to.params.id);
		next();
	},
};
</script>

<style scoped>
.card {
	background: #f9f9f9 !important;
}
</style>
