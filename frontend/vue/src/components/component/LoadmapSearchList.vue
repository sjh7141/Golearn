<template>
	<div id="csl" ref="app">
		<v-img height="200" src="@/assets/roadmap_banner.jpg">
			<v-row class="center" style="margin-left:0px">
				<div class="text-center" style="width:100%;">
					<v-row>
						<v-col cols="10">
							훌륭한 강의들로 구성된
							<span class="bold">로드맵</span>을 따라
						</v-col>
					</v-row>
					<v-row>
						<v-spacer></v-spacer>
						<v-col cols="9">
							한걸음 <span class="bold">더</span> 성장할 수
							있습니다.
						</v-col>
					</v-row>
				</div>
			</v-row>
		</v-img>
		<v-container class="asd content-component">
			<v-card class="transparent" flat v-if="loadmaps.length !== 0">
				<v-row>
					<v-spacer></v-spacer>
					<v-col cols="4" class="text-right">
						<v-text-field
							class="basic"
							v-model="search"
							flat
							dense
							outlined
							append-icon="mdi-magnify"
							@click:append="searchRoadmap"
							@keydown.enter="searchRoadmap"
							placeholder="로드맵 검색"
						>
						</v-text-field>
						<!-- <v-btn
							outlined
							class="bold"
							style="border: 1px solid #c9c9c9;"
							@click="make"
						>
							<v-icon color="darken-3">
								mdi-plus
							</v-icon>
							<span style="color:black">로드맵 등록</span>
						</v-btn> -->
						<v-btn
							dark
							depressed
							color="#6523e8"
							class="bold"
							@click="make"
						>
							로드맵 등록
						</v-btn>
					</v-col>
				</v-row>
				<v-row>
					<v-col>
						<v-row>
							<v-col
								cols="12"
								sm="6"
								md="4"
								lg="4"
								v-for="(loadmap, i) in loadmaps"
								:key="i"
								class="mx-xs-auto pt-0 pb-6"
							>
								<v-skeleton-loader type="card-avatar">
									<loadmap-card
										:card="{ maxWidth: 400 }"
										:loadmap="loadmap"
									></loadmap-card>
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
import LoadmapCard from './LoadmapCard2';
import InfiniteLoading from 'vue-infinite-loading';
export default {
	data: () => ({
		loading: false,
		selectedTags: [],
		tags: [],
		search: '',
		pageNo: 1,
		loadmaps: [],
	}),
	computed: {},
	components: {
		InfiniteLoading,
		LoadmapCard,
	},
	methods: {
		...mapActions(['getTagList', 'getSearchResult', 'makeLoadmap']),
		setTags() {
			this.getTagList().then(res => {
				this.tags = res.data;
			});
		},
		infiniteHandler($state) {
			const payload = {
				search: this.search ? this.search : '',
				page_no: this.pageNo,
				type: 'loadmap',
			};
			this.getSearchResult(payload).then(res => {
				if (res.data.loadmap.length > 0) {
					this.loadmaps.push(...res.data.loadmap);
					$state.loaded();
					this.pageNo += 1;
				} else {
					$state.complete();
				}
			});
		},
		searchRoadmap() {
			this.pageNo = 1;
			this.loadmaps = [];
			const payload = {
				search: this.search ? this.search : '',
				page_no: this.pageNo,
				type: 'loadmap',
			};
			this.getSearchResult(payload).then(res => {
				this.loadmaps.push(...res.data.loadmap);
				this.pageNo += 1;
			});
		},
		make() {
			this.makeLoadmap().then(({ data }) => {
				this.$router.push(`/roadmap/management/${data}`);
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
@import url(//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSans-kr.css);

.content-component {
	/* background-color: #fafafa; */
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
	background-color: rgba(31, 179, 215, 0.12);
}
.center {
	display: flex;
	justify-content: center;
	align-items: center;
	font-family: 'Lato', 'Spoqa Han Sans';
	height: 100%;
	font-size: 2rem;
	font-weight: 500;
	color: #f9f9f9;
	width: 100%;
	/* background-color: #f9f9f977; */
	background: linear-gradient(
		to right,
		rgba(20, 20, 20, 0) 10%,
		rgba(20, 20, 20, 0.25) 25%,
		rgba(20, 20, 20, 0.5) 50%,
		rgba(20, 20, 20, 0.75) 75%,
		rgba(20, 20, 20, 1) 100%
	);
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
