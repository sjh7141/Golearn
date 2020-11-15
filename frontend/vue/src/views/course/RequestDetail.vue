<template>
	<div id="request">
		<course-banner
			:course="course"
			:src="course.cos_banner"
			:ldm_no="ldm_no"
		/>

		<div class="py-5" style="width:1080px; margin:0 auto;" ref="contain">
			<h3 style="">{{ req ? req.vid_req_comment : '' }}</h3>
			<v-layout class="mt-5">
				<v-flex class="pr-5" md6 lg6 xl6>
					<v-card flat outlined class="mb-4">
						<div
							style="background-color:#fafafa;padding:5px 10px;font-size:14px;font-weight:600"
						>
							영상
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
											{{
												video.author
													? video.author.mbr_nick_name
													: ''
											}}
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
											{{
												video.video
													? video.video.vid_title
													: ''
											}}
										</v-btn>
									</template>
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
										:src="video.video.vid_url"
										v-if="video.video"
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
													video.video
														? video.video.vid_title
														: ''
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
												video.video
													? video.video.vid_content
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
											{{ course ? course.cos_title : '' }}
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
											{{
												chapter ? chapter.idx_title : ''
											}}
										</v-btn>
									</template>
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
										:src="chapter.map.video.vid_url"
										v-if="chapter.map"
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
													chapter &&
													chapter.map &&
													chapter.map.video
														? chapter.map.video
																.vid_title
														: ''
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
												chapter &&
												chapter.map &&
												chapter.map.video
													? chapter.map.video
															.vid_content
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
				<span> 응답 메세지 </span>
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
						style="font-size:14px;right:90px;"
						@click="message.length ? responseVideo(1) : null"
					>
						수락
					</v-btn>
					<v-btn
						absolute
						right
						bottom
						depressed
						:outlined="!message.length"
						tile
						dark
						:color="message.length ? 'warning' : '#e4e4e4'"
						style="font-size:14px;"
						@click="message.length ? responseVideo(2) : null"
					>
						거절
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
			message: '',
			user: {},
			req_no: -1,
			cos_no: -1,
			vid_no: -1,
			idx_no: -1,

			course: {},
			video: {},
			chapter: {},
			req: {},
			ldm_no: 0,
		};
	},
	mounted() {
		this.user = this.$store.getters.user;
		this.req_no = this.$route.params.id;
		this.ldm_no = this.$route.query.ldm_no;

		this.getRequestDetail(this.req_no).then(({ data }) => {
			this.req = data;
			const { cos_no, vid_no, idx_no } = data;
			this.cos_no = cos_no;
			this.vid_no = vid_no;
			this.idx_no = idx_no;

			this.getCourse(cos_no).then(({ data }) => {
				this.course = data;
			});
			this.getVideoDetail(vid_no).then(({ data }) => {
				this.video = data;
			});
			this.getChapterDetail(idx_no).then(({ data }) => {
				const tmpData = data;
				this.getVideoDetail(data.vid_no).then(({ data }) => {
					tmpData.map = data;
					this.chapter = tmpData;
				});
			});
		});
	},
	methods: {
		...mapActions([
			'getRequestDetail',
			'getCourse',
			'getChapterDetail',
			'getVideoDetail',
			'returnResponse',
		]),

		responseVideo(yn) {
			this.returnResponse({
				idx_no: this.idx_no,
				vid_no: this.vid_no,
				cos_no: this.cos_no,
				vid_req_accept_yn: yn,
				vid_req_no: parseInt(this.req_no),
				vid_res_comment: this.message,
			}).then(() => {
				alert('처리되었습니다.');
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
