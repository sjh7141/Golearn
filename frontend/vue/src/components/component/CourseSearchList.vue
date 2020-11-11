<template>
	<div ref="app">
		<v-container class="asd">
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
						>
							<v-icon slot="append" @click="keywordSearch()">
								mdi-magnify
							</v-icon>
						</v-text-field>
					</v-col>
				</v-row>
				<v-row>
					<v-col cols="2">
						<v-card tile class="side-bar">
							<v-card-title>
								카테고리
							</v-card-title>
							<v-navigation-drawer permanent>
								<v-list>
									<v-btn-toggle
										v-model="selectedTags"
										multiple
										group
									>
										<v-btn
											rounded
											style="width:100%"
											v-for="(tag, index) in tags"
											:key="`tag_${index}`"
											:value="tag.tag_no"
											text
											class="tag-button pa-0 ma-0"
										>
											{{ tag.tag_name }}
										</v-btn>
									</v-btn-toggle>
								</v-list>
							</v-navigation-drawer>
						</v-card>
					</v-col>
					<v-col>
						<v-row>
							<v-col
								cols="10"
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
		tags: [],
		search: '',
		pageNo: 1,
		courses: [],
	}),
	computed: {},
	components: {
		InfiniteLoading,
		CourseCard,
	},
	methods: {
		...mapActions(['getTags', 'getSearchResult']),
		setTags() {
			this.getTags().then(res => {
				this.tags = res.data;
			});
		},
		infiniteHandler($state) {
			const payload = {
				search: this.search ? this.search : '',
				page_no: this.pageNo,
				type: 'course',
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
.card {
	background: #f9f9f9 !important;
}
.asd {
	max-width: 1440px;
}
.v-btn-toggle {
	flex-direction: column;
	display: block !important;
}
.side-bar {
	position: fixed;
	width: 10.7%;
}
</style>
