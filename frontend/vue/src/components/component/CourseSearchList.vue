<template>
	<div id="csl" ref="app">
		<v-container class="asd content-component">
			<v-card class="transparent" flat v-if="courses.length !== 0">
				<v-row>
					<v-spacer></v-spacer>
					<v-col cols="4" class="text-right">
						<v-text-field
							class="basic"
							v-model="search"
							flat
							dense
							outlined
							placeholder="코스 검색"
							@keydown.enter="searchCourse"
							@click:append="searchCourse"
							append-icon="mdi-magnify"
							hide-details
						>
						</v-text-field>
					</v-col>
				</v-row>
				<v-row>
					<v-col>
						<v-row>
							<v-col
								cols="12"
								sm="6"
								md="4"
								lg="3"
								v-for="(course, i) in courses"
								:key="i"
								class="mx-xs-auto pt-0 pb-6"
							>
								<v-skeleton-loader type="card-avatar">
									<course-card
										:card="{ maxWidth: 250 }"
										:course="course"
									></course-card>
								</v-skeleton-loader>
							</v-col>
						</v-row>
					</v-col>
				</v-row>
			</v-card>
		</v-container>
		<infinite-loading @infinite="infiniteHandler"></infinite-loading>
	</div>
</template>

<script>
import { mapActions } from 'vuex';
import CourseCard from './CourseCard2';
import InfiniteLoading from 'vue-infinite-loading';
export default {
	data: () => ({
		loading: false,
		selectedTags: [],
		tags: '',
		search: '',
		pageNo: 1,
		courses: [],
		categories: [],
	}),
	computed: {},
	components: {
		InfiniteLoading,
		CourseCard,
	},
	methods: {
		...mapActions(['getTagList', 'getSearchResult', 'makeCourse']),
		setTags() {
			this.getTagList().then(res => {
				this.categories = res.data;
			});
		},
		infiniteHandler($state) {
			const payload = {
				search: this.search ? this.search : '',
				page_no: this.pageNo,
				type: 'course',
				tag_no: this.tags,
			};
			this.getSearchResult(payload).then(res => {
				if (res.data.course.length > 0) {
					this.courses.push(...res.data.course);
					$state.loaded();
					this.pageNo += 1;
				} else {
					$state.complete();
				}
			});
		},
		searchCourse() {
			this.pageNo = 1;
			this.courses = [];
			const payload = {
				search: this.search ? this.search : '',
				page_no: this.pageNo,
				type: 'course',
				tag_no: this.tags,
			};
			this.getSearchResult(payload).then(res => {
				this.courses.push(...res.data.course);
				this.pageNo += 1;
			});
		},
		goToSearch(value) {
			this.tags = value.tag_no;
			this.searchCourse();
		},
		make() {
			this.makeCourse().then(({ data }) => {
				this.$router.push(`/course/management/${data.cos_no}`);
			});
		},
	},
	created() {
		this.setTags();
	},

	mounted() {
		this.search = this.$route.query.search;
	},
	watch: {},
};
</script>

<style scoped>
.content-component {
	background-color: transparent;
}
.card {
	/* background: #f9f9f9 !important; */
}
.asd {
	max-width: 1220px;
}
.v-btn-toggle {
	flex-direction: column;
	display: block !important;
}
.side-bar {
	position: fixed;
	width: 10.7%;
}
.selected {
	background-color: #c3aed633;
}
</style>

<style>
#csl .v-navigation-drawer__border {
	display: none !important;
	background-color: rgba(0, 0, 0, 0) !important;
}
#csl .v-btn__content {
	justify-content: center;
}
</style>
