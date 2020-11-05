<template>
	<div id="request">
		<v-img width="100%" src="@/assets/bg_skin1.jpg" height="230">
			<v-layout
				style="background-color:rgba(0,0,0,0.4); width:100%;height:100%;"
			>
			</v-layout>
		</v-img>

		<div class="py-5" style="width:1080px; margin:0 auto;" ref="contain">
			<h3 style="">New Merge Request</h3>
			<v-layout class="mt-5">
				<v-flex class="pr-5" md6 lg6 xl6>
					<v-card flat outlined class="mb-4">
						<div
							style="background-color:#fafafa;padding:5px 10px;font-size:14px;font-weight:600"
						>
							Source branch
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
											{{ channels[channelIndex] }}
											<v-icon
												size="18"
												style="position: absolute; right: 0px;"
											>
												mdi-chevron-down
											</v-icon>
										</v-btn>
									</template>
									<v-card outlined flat>
										<div
											class="pa-2"
											style="text-align:center;width:100%;color:#212121;font-size:13px;font-weight:600;"
										>
											Select source project
										</div>
										<v-divider class="pa-2" />
										<div class="dropdown-input mx-2">
											<input
												class="dropdown-input-field"
												type="search"
												placeholder="Search projects"
												autocomplete="off"
												@click.stop=""
											/>
											<v-icon
												style="position: absolute; right:12px; top:60px;"
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
												v-model="channelIndex"
											>
												<v-list-item
													dense
													v-for="(item,
													i) in channels"
													:key="`channels_${i}`"
												>
													<v-icon
														small
														class="mr-2"
														v-if="i == channelIndex"
													>
														mdi-check-bold
													</v-icon>
													<v-icon
														v-else
														class="pa-2 mr-2"
													>
													</v-icon>
													{{ item }}
												</v-list-item>
											</v-list-item-group>
										</v-list>
									</v-card>
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
											{{ videos[videoIndex] }}
											<v-icon
												size="18"
												style="position: absolute; right: 0px;"
											>
												mdi-chevron-down
											</v-icon>
										</v-btn>
									</template>
									<v-card outlined flat>
										<div
											class="pa-2"
											style="text-align:center;width:100%;color:#212121;font-size:13px;font-weight:600;"
										>
											Select source project
										</div>
										<v-divider class="pa-2" />
										<div class="dropdown-input mx-2">
											<input
												class="dropdown-input-field"
												type="search"
												placeholder="Search projects"
												autocomplete="off"
												@click.stop=""
											/>
											<v-icon
												style="position: absolute; right:12px; top:60px;"
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
													{{ item }}
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
									<MultiView width="498" height="280" />
								</v-list-item>
								<v-list-item dense class="pa-0 mt-7 mb-3">
									<v-list-item-content>
										<v-list-item-title
											class="mb-2"
											style="font-size:14px;font-weight:600;"
										>
											강의 제목
										</v-list-item-title>
										<v-list-item-title>
											<v-text-field
												:ripple="false"
												outlined
												disabled
												dense
												filled
												readonly
												:value="`HTML & Internet`"
												style="font-size:14px;"
												hide-details
											/>
										</v-list-item-title>
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
										>
											<span>
												CPU는 계속해서 다음에 무엇을
												할지 물어 봅니다.
												<p />
												이것에 대한 답변은 사람이 하며,
												답변들의 목록이나 순차적으로
												진행되어야 할 사항들을 파이썬
												언어로 작성한 뒤 보조 기억장치에
												저장합니다.
												<p />
												CPU의 질문에 답변을 해야 하는
												순간이 오면 메인 메모리에 해당
												내용을 로드 합니다. 이 과정에서
												2진수로 만들어져 있는 기계어로
												마법처럼 변환이 되며 이는
												컴퓨터가 이해할 수 있는 언어로
												전달 되게 됩니다.
												<p />
												그 후 내부적으로는 1초에
												수십억번(CPU에 성능에 따라
												달라집니다)에 걸쳐 질문과 답변이
												이루어 지게 됩니다.
											</span>
										</div>
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
							Target branch
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
											{{ courses[courseIndex] }}
											<v-icon
												size="18"
												style="position: absolute; right: 0px;"
											>
												mdi-chevron-down
											</v-icon>
										</v-btn>
									</template>
									<v-card outlined flat>
										<div
											class="pa-2"
											style="text-align:center;width:100%;color:#212121;font-size:13px;font-weight:600;"
										>
											Select source project
										</div>
										<v-divider class="pa-2" />
										<div class="dropdown-input mx-2">
											<input
												class="dropdown-input-field"
												type="search"
												placeholder="Search projects"
												autocomplete="off"
												@click.stop=""
											/>
											<v-icon
												style="position: absolute; right:12px; top:60px;"
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
												v-model="courseIndex"
											>
												<v-list-item
													dense
													v-for="(item, i) in courses"
													:key="`courses_${i}`"
												>
													<v-icon
														small
														class="mr-2"
														v-if="i == courseIndex"
													>
														mdi-check-bold
													</v-icon>
													<v-icon
														v-else
														class="pa-2 mr-2"
													>
													</v-icon>
													{{ item }}
												</v-list-item>
											</v-list-item-group>
										</v-list>
									</v-card>
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
											{{ chapters[chapterIndex] }}
											<v-icon
												size="18"
												style="position: absolute; right: 0px;"
											>
												mdi-chevron-down
											</v-icon>
										</v-btn>
									</template>
									<v-card outlined flat>
										<div
											class="pa-2"
											style="text-align:center;width:100%;color:#212121;font-size:13px;font-weight:600;"
										>
											Select source project
										</div>
										<v-divider class="pa-2" />
										<div class="dropdown-input mx-2">
											<input
												class="dropdown-input-field"
												type="search"
												placeholder="Search projects"
												autocomplete="off"
												@click.stop=""
											/>
											<v-icon
												style="position: absolute; right:12px; top:60px;"
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
													{{ item }}
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
									<MultiView width="498" height="280" />
								</v-list-item>
								<v-list-item dense class="pa-0 mt-7 mb-3">
									<v-list-item-content>
										<v-list-item-title
											class="mb-2"
											style="font-size:14px;font-weight:600;"
										>
											강의 제목
										</v-list-item-title>
										<v-list-item-title>
											<v-text-field
												:ripple="false"
												outlined
												disabled
												dense
												filled
												readonly
												:value="`HTML & Internet`"
												style="font-size:14px;"
												hide-details
											/>
										</v-list-item-title>
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
										>
											<span>
												CPU는 계속해서 다음에 무엇을
												할지 물어 봅니다.
												<p />
												이것에 대한 답변은 사람이 하며,
												답변들의 목록이나 순차적으로
												진행되어야 할 사항들을 파이썬
												언어로 작성한 뒤 보조 기억장치에
												저장합니다.
												<p />
												CPU의 질문에 답변을 해야 하는
												순간이 오면 메인 메모리에 해당
												내용을 로드 합니다. 이 과정에서
												2진수로 만들어져 있는 기계어로
												마법처럼 변환이 되며 이는
												컴퓨터가 이해할 수 있는 언어로
												전달 되게 됩니다.
												<p />
												그 후 내부적으로는 1초에
												수십억번(CPU에 성능에 따라
												달라집니다)에 걸쳐 질문과 답변이
												이루어 지게 됩니다.
											</span>
										</div>
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
				<v-img src="@/assets/default_profile.png" />
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
						:color="message.length ? 'success' : '#e4e4e4'"
					>
						Compare branches and continue
					</v-btn>
				</template>
			</v-textarea>
		</div>
	</div>
</template>

<script>
import MultiView from '@/components/video/MultiView.vue';
export default {
	name: 'Request',
	components: {
		MultiView,
	},

	data() {
		return {
			channels: [
				's03-final/s03p31a402',
				's03-final/s03p31a403',
				's03-final/s03p31a404',
				's03-final/s03p31a402',
				's03-final/s03p31a403',
				's03-final/s03p31a404',
			],
			channelIndex: 0,

			videos: [
				'cicd',
				'course-service',
				'develop',
				'feature/account-service',
				'feature/auth-service',
				'feature/board-service',
			],
			videoIndex: 0,

			courses: [
				's03-final/s03p31a402',
				's03-final/s03p31a403',
				's03-final/s03p31a404',
				's03-final/s03p31a402',
				's03-final/s03p31a403',
				's03-final/s03p31a404',
			],
			courseIndex: 0,

			chapters: [
				'cicd',
				'course-service',
				'develop',
				'feature/account-service',
				'feature/auth-service',
				'feature/board-service',
			],
			chapterIndex: 0,

			message: '',
		};
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
