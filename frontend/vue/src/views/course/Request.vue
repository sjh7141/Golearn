<template>
	<div id="request">
		<course-banner
			:course="course"
			:src="course.cos_banner"
			:ldm_no="ldm_no"
		/>

		<div class="py-5" style="width:1080px; margin:0 auto;" ref="contain">
			<h3 style="">영상 기여하기</h3>
			<v-layout class="mt-5">
				<v-flex class="pr-5" md6 lg6 xl6>
					<v-card flat outlined class="mb-4">
						<div
							style="background-color:#fafafa;padding:5px 10px;font-size:14px;font-weight:600"
						>
							내 영상
						</div>
						<v-divider />
						<div style="padding:10px 5px;">
							<div
								style="width:50%;display: inline-block;padding:0 5px;"
							>
								<v-menu
									offset-y
									:nudge-bottom="3"
									z-index="200"
								>
									<template v-slot:activator="{ attrs }">
										<v-btn
											class="request-button"
											left
											depressed
											outlined
											dense
											width="100%"
											:ripple="false"
											style="border-color:#e5e5e5; color:2e2e2e; font-size:14px;"
											v-bind="attrs"
										>
											{{ user.nickname }}
										</v-btn>
									</template>
								</v-menu>
							</div>
							<div
								style="width:50%;display: inline-block;padding:0 5px;"
							>
								<v-menu
									offset-y
									:nudge-bottom="3"
									z-index="200"
								>
									<template v-slot:activator="{ on, attrs }">
										<v-btn
											class="request-button"
											left
											depressed
											outlined
											dense
											width="100%"
											:ripple="false"
											style="border-color:#e5e5e5; color:2e2e2e; font-size:14px;"
											v-bind="attrs"
											v-on="on"
										>
											{{
												videoIndex >= 0
													? videos[videoIndex]
															.vid_title
														? videos[videoIndex]
																.vid_title
														: 'Untitled'
													: 'Untitled'
											}}
											<v-icon
												size="18"
												style="position: absolute; right: 0px;"
											>
												mdi-chevron-down
											</v-icon>
										</v-btn>
									</template>
									<v-card outlined flat>
										<div class="dropdown-input mx-2 my-2">
											<input
												class="dropdown-input-field"
												type="search"
												placeholder="검색..."
												autocomplete="off"
												@click.stop=""
											/>
											<v-icon
												style="position: absolute; right:12px; top:15px;"
												size="18"
											>
												mdi-magnify</v-icon
											>
										</div>
										<v-list
											style="font-size: 14px; max-height:200px; overflow-y:auto;"
											class="pa-0 ma-0"
										>
											<v-list-item-group
												v-model="videoIndex"
											>
												<v-list-item
													dense
													v-for="(item, i) in videos"
													:key="`videos_${i}`"
												>
													<v-icon
														small
														class="mr-2"
														v-if="i == videoIndex"
													>
														mdi-check-bold
													</v-icon>
													<v-icon
														v-else
														class="pa-2 mr-2"
													>
													</v-icon>
													{{
														item.vid_title
															? item.vid_title
															: 'Untitled'
													}}
												</v-list-item>
											</v-list-item-group>
										</v-list>
									</v-card>
								</v-menu>
							</div>
						</div>
						<v-divider />
						<div
							style="background-color:#fafafa;padding:5px 10px;font-size:14px;font-weight:400"
						>
							<v-list style="background-color:transparent;">
								<v-list-item dense class="pa-0 ma-0">
									<video
										width="498"
										height="280"
										:src="videos[videoIndex].vid_url"
										v-if="videoIndex >= 0"
										controls
									/>
								</v-list-item>
								<v-list-item dense class="pa-0 mt-7 mb-3">
									<v-list-item-content>
										<v-list-item-title
											class="mb-2"
											style="font-size:14px;font-weight:600;"
										>
											강의 제목
										</v-list-item-title>
										<div
											class="mb-1 px-3 py-4"
											style="background-color:rgb(235,235,235); border-radius:4px; color:rgb(146,146,146)"
										>
											<span>
												{{
													videoIndex >= 0
														? videos[videoIndex]
																.vid_title
															? videos[videoIndex]
																	.vid_title
															: 'Untitled'
														: 'Untitled'
												}}
											</span>
										</div>
									</v-list-item-content>
								</v-list-item>
								<v-list-item dense class="pa-0" filled>
									<v-list-item-content>
										<v-list-item-title
											class="mb-2"
											style="font-size:14px;font-weight:600;"
										>
											강의 설명
										</v-list-item-title>

										<div
											class="mb-1 px-3 py-4"
											style="background-color:rgb(235,235,235); border-radius:4px; color:rgb(146,146,146)"
											v-html="
												videoIndex >= 0
													? videos[videoIndex]
															.vid_content
														? videos[videoIndex]
																.vid_content
														: ''
													: ''
											"
										></div>
									</v-list-item-content>
								</v-list-item>
							</v-list>
						</div>
					</v-card>
				</v-flex>
				<v-flex class="pl-5" md6 lg6 xl6>
					<v-card flat outlined>
						<div
							style="background-color:#fafafa;padding:5px 10px;font-size:14px;font-weight:600"
						>
							코스
						</div>
						<v-divider />
						<div style="padding:10px 5px;">
							<div
								style="width:50%;display: inline-block;padding:0 5px;"
							>
								<v-menu
									offset-y
									:nudge-bottom="3"
									z-index="200"
								>
									<template v-slot:activator="{ attrs }">
										<v-btn
											class="request-button"
											left
											depressed
											outlined
											dense
											width="100%"
											:ripple="false"
											style="border-color:#e5e5e5; color:2e2e2e; font-size:14px;"
											v-bind="attrs"
										>
											{{ course.cos_title }}
										</v-btn>
									</template>
								</v-menu>
							</div>
							<div
								style="width:50%;display: inline-block;padding:0 5px;"
							>
								<v-menu
									offset-y
									:nudge-bottom="3"
									z-index="200"
								>
									<template v-slot:activator="{ on, attrs }">
										<v-btn
											class="request-button"
											left
											depressed
											outlined
											dense
											width="100%"
											:ripple="false"
											style="border-color:#e5e5e5; color:2e2e2e; font-size:14px;"
											v-bind="attrs"
											v-on="on"
										>
											{{
												chapterIndex >= 0
													? chapters[chapterIndex]
															.idx_title
														? chapters[chapterIndex]
																.idx_title
														: 'Untitled'
													: 'Untitled'
											}}
											<v-icon
												size="18"
												style="position: absolute; right: 0px;"
											>
												mdi-chevron-down
											</v-icon>
										</v-btn>
									</template>
									<v-card outlined flat>
										<div class="dropdown-input mx-2 my-2">
											<input
												class="dropdown-input-field"
												type="search"
												placeholder="검색..."
												autocomplete="off"
												@click.stop=""
											/>
											<v-icon
												style="position: absolute; right:12px; top:15px;"
												size="18"
											>
												mdi-magnify</v-icon
											>
										</div>
										<v-list
											style="font-size: 14px; max-height:200px; overflow-y:auto;"
											class="pa-0 ma-0"
										>
											<v-list-item-group
												v-model="chapterIndex"
											>
												<v-list-item
													dense
													v-for="(item,
													i) in chapters"
													:key="`chapters_${i}`"
												>
													<v-icon
														small
														class="mr-2"
														v-if="i == chapterIndex"
													>
														mdi-check-bold
													</v-icon>
													<v-icon
														v-else
														class="pa-2 mr-2"
													>
													</v-icon>
													{{
														item.idx_title
															? item.idx_title
															: 'Untitled'
													}}
												</v-list-item>
											</v-list-item-group>
										</v-list>
									</v-card>
								</v-menu>
							</div>
						</div>
						<v-divider />
						<div
							style="background-color:#fafafa;padding:5px 10px;font-size:14px;font-weight:400"
						>
							<v-list style="background-color:transparent;">
								<v-list-item dense class="pa-0 ma-0">
									<video
										width="498"
										height="280"
										:src="chapters[chapterIndex].vid_url"
										v-if="chapterIndex >= 0"
										controls
									/>
								</v-list-item>
								<v-list-item dense class="pa-0 mt-7 mb-3">
									<v-list-item-content>
										<v-list-item-title
											class="mb-2"
											style="font-size:14px;font-weight:600;"
										>
											강의 제목
										</v-list-item-title>
										<div
											class="mb-1 px-3 py-4"
											style="background-color:rgb(235,235,235); border-radius:4px; color:rgb(146,146,146)"
										>
											<span>
												{{
													chapterIndex >= 0
														? chapters[chapterIndex]
																.map.video
																.vid_title
															? chapters[
																	chapterIndex
															  ].map.video
																	.vid_title
															: 'Untitled'
														: 'Untitled'
												}}
											</span>
										</div>
									</v-list-item-content>
								</v-list-item>
								<v-list-item dense class="pa-0" filled>
									<v-list-item-content>
										<v-list-item-title
											class="mb-2"
											style="font-size:14px;font-weight:600;"
										>
											강의 설명
										</v-list-item-title>

										<div
											class="mb-1 px-3 py-4"
											style="background-color:rgb(235,235,235); border-radius:4px; color:rgb(146,146,146)"
											v-html="
												chapterIndex >= 0
													? chapters[chapterIndex].map
															.video.vid_content
														? chapters[chapterIndex]
																.map.video
																.vid_content
														: ''
													: ''
											"
										/>
									</v-list-item-content>
								</v-list-item>
							</v-list>
						</div>
					</v-card>
				</v-flex>
			</v-layout>
			<div style="font-size:18px;" class="my-5">
				<span> 요청 메세지 </span>
			</div>

			<v-avatar size="48" style="float:left;" class="mr-5">
				<v-img :src="user.profile" />
			</v-avatar>

			<v-textarea
				v-model="message"
				no-resize
				outlined
				auto-grow
				tile
				flat
				rows="5"
				color="#8e8e8e"
				placeholder="내용을 입력해주세요."
			>
				<template slot="append">
					<v-btn
						absolute
						right
						bottom
						depressed
						:outlined="!message.length"
						tile
						dark
						:color="message.length ? 'success' : '#e4e4e4'"
						style="font-size:14px;"
						@click="message.length ? requestVideo() : null"
					>
						요청
					</v-btn>
				</template>
			</v-textarea>
		</div>
	</div>
</template>

<script>
import { mapActions } from 'vuex';
import CourseBanner from '@/components/course/CourseBanner.vue';

export default {
	name: 'Request',
	components: {
		CourseBanner,
	},

	data() {
		return {
			videos: [],
			videoIndex: -1,

			chapters: [],
			chapterIndex: -1,

			message: '',
			user: {},
			cos_no: -1,
			course: {},
			ldm_no: 0,
		};
	},
	mounted() {
		if (!this.$store.getters.isLogin) this.$router.push('/login');
		this.ldm_no = this.$route.query.ldm_no;

		this.user = this.$store.getters.user;
		this.cos_no = this.$route.query.cos_no;
		const self = this;
		this.getChannelVideos(this.user.no).then(({ data }) => {
			this.videos = data;
			if (data.length > 0) this.videoIndex = 0;
		});
		this.getCourseIndexs(this.cos_no).then(({ data }) => {
			data.forEach(res => {
				res.vid_url = '';
				self.getVideo(res.vid_no).then(result => {
					res.vid_url = result.data.video.vid_url;
				});
			});
			this.chapters = data;

			if (data.length > 0) this.chapterIndex = 0;
		});
		this.getCourse(this.cos_no).then(({ data }) => {
			this.course = data;
		});
	},
	methods: {
		...mapActions([
			'getChannelVideos',
			'getCourseIndexs',
			'sendRequest',
			'getCourse',
			'getVideo',
		]),

		requestVideo() {
			if (this.videoIndex < 0 || this.chapterIndex < 0) return;

			this.sendRequest({
				cos_no: this.cos_no,
				idx_no: this.chapters[this.chapterIndex].idx_no,
				vid_no: this.videos[this.videoIndex].vid_no,
				vid_req_comment: this.message,
			}).then(() => {
				alert('요청을 보냈습니다.');
				this.$router.push(
					`/course/${this.cos_no}/requestlist${
						this.ldm_no ? '?ldm_no=' + this.ldm_no : ''
					}`,
				);
			});
		},
	},
};
</script>

<style>
#request h3 {
	font-weight: 500;
}
.request-button {
	text-transform: none;
	font-weight: 400;
	font-family: default;
}

.request-button .v-btn__content {
	display: inline;
}

.v-menu__content {
	border: 1px solid #e5e5e5;
	border-radius: 3px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.dropdown-input {
	margin-bottom: 10px;
	font-size: 14px;
}

.dropdown-input-field {
	display: block;
	width: 100%;
	min-height: 30px;
	padding: 0 7px;
	color: #555555;
	line-height: 30px;
	border: 1px solid #73afea;
	border-radius: 2px;
	outline: 0;
	box-shadow: 0 0 4px rgba(115, 175, 234, 0.4);
}
</style>
