<template>
	<div ref="app">
		<v-card class="transparent" flat v-if="courses.length !== 0">
			<v-card-title
				>코스 <v-spacer></v-spacer
				><v-text-field name="asdf">asdf</v-text-field></v-card-title
			>
			<v-row>
				<v-col
					cols="12"
					sm="6"
					md="4"
					lg="3"
					v-for="(course, i) in loading ? 10 : courses"
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
import CourseCard from './CourseCard2';
export default {
	props: ['courses'],
	data: () => ({
		loading: false,
		errored: false,
		showSubBtn: true,

		signinDialog: false,
		details: {},
	}),
	computed: {},
	components: {
		CourseCard,
	},
};
</script>

<style scoped>
.card {
	background: #f9f9f9 !important;
}
</style>
