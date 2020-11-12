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
							disabled
							placeholder="코스 검색"
						>
							<v-icon slot="append" @click="keywordSearch()">
								mdi-magnify
							</v-icon>
						</v-text-field>
					</v-col>
				</v-row>
				<v-row>
					<v-slide-group
						class="my-5 px-5"
						show-arrows
						style="height: 50px;"
					>
						<v-slide-item v-for="tag in categories" :key="tag">
							<v-btn
								class="px-1 mx-2"
								active-class="white--text"
								text
								@click="goToSearch(tag)"
								elevation="0"
								style="text-transform:none; color:rgb(31,179,215);font-size:13px"
								:class="{
									selected: selectedTags.includes(tag),
								}"
							>
								<!-- <v-icon color="green accent-4" left>
									mdi-magnify
								</v-icon> -->
								{{ tag }}
							</v-btn>
						</v-slide-item>
					</v-slide-group>
				</v-row>
				<v-row>
					<!-- <v-col cols="2">
						<v-card tile flat outlined class="side-bar">
							<v-card-title>
								카테고리
							</v-card-title>
							<v-navigation-drawer style="border:0px" permanent>
								<v-list class="pb-0">
									<v-btn-toggle
										v-model="selectedTags"
										multiple
										group
									>
										<template
											v-for="(category,
											index) in categories"
										>
											<v-divider
												:key="`divider_${index}`"
											></v-divider>
											<v-btn
												rounded
												style="width:100%; border:0px"
												:key="`tag_${index}`"
												:value="index"
												text
												class="tag-button pa-0 ma-0 text-left"
											>
												{{ category }}
											</v-btn>
										</template>
									</v-btn-toggle>
								</v-list>
							</v-navigation-drawer>
						</v-card>
					</v-col> -->
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
		tags: [],
		search: '',
		pageNo: 1,
		courses: [],
		categories: [
			'운영체제',
			'네트워크',
			'게임개발',
			'보안',
			'프로그래밍언어',
			'알고리즘',
			'데이터베이스',
			'블록체인',
			'자동화',
			'웹 개발',
			'서버 개발',
		],
		search2: [
			'C',
			'C++',
			'JAVA',
			'Python',
			'Javascript',
			'CSS',
			'HTML',
			'Spring',
			'Django',
			'Algorithm',
			'MySQL',
			'NoSQL',
			'C1',
			'C++1',
			'JAVA1',
			'Python1',
			'Javascript1',
			'CSS1',
			'HTML1',
			'Spring1',
			'Django1',
			'Algorithm1',
			'MySQL1',
			'NoSQL1',
		],
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

		goToSearch(value) {
			if (this.selectedTags.includes(value)) {
				const index = this.selectedTags.indexOf(value);
				this.selectedTags.splice(index, 1);
			} else {
				this.selectedTags.push(value);
			}
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
	background-color: #fff;
}
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
.selected {
	background-color: rgba(31, 179, 215, 0.12);
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
