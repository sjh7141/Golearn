<template>
	<v-row id="edit-cover" class="ml-3 height-100">
		<v-col cols="9">
			<h2>영상</h2>
			<h4 class="pb-5 pl-3" style="color:gray;">
				"동영상 파일과 영상에 사용될 썸네일을 등록해주세요."
			</h4>
			<div justify="center" class="mt-5">
				<div class="pa-6 mb-6 bg-light-gray border-radius-10">
					<div class="bold">
						주의사항
					</div>
					<ol>
						<li>
							동영상 파일의 경우
							<span class="bold">mp4</span>파일이 권장되며 이외의
							파일은 정상적으로 작동되지않을 수 있습니다.
						</li>
						<li>
							파일 용량은
							<span class="bold">최대 10GB 이하</span>, 영상의
							재생시간은
							<span class="bold">최대 8시간 이하</span>를
							권장드립니다.
						</li>
						<li>
							커버 이미지(썸네일)를 직접 제작하실 경우,
							<span class="bold">768 × 500(px)</span>에
							맞춰주세요. 가로형 이미지에 최적화되어있습니다.
						</li>
						<li>
							이미지가 규정에 맞지 않을 경우, 관리자 판단하에
							임의로 변경될 수 있습니다.
						</li>
						<li>
							이미지는
							<span class="bold">jpg, jpeg, png</span>파일만 사용
							가능합니다.
						</li>
					</ol>
				</div>
				<div>
					<div class="bold">영상</div>
					<div class="pb-8" style="text-align:end;">
						<v-btn
							outlined
							class="add-btn mr-3"
							@click="clickVideo"
						>
							<v-icon color="darken-3">
								mdi-plus
							</v-icon>
							<span style="font-size:15px;">업로드</span>
						</v-btn>
						<v-btn outlined class="add-btn" @click="isAdd = true">
							<v-icon color="darken-3">
								mdi-plus
							</v-icon>
							<span style="font-size:15px;">가져오기</span>
						</v-btn>
					</div>
					<v-row class="pb-5">
						<v-col md="8" offset="2">
							<video
								v-show="isVideo"
								controls
								style="width:100%; max-width: 1500px;"
								ref="video"
							>
								브라우저에서 지원되는 포맷이 아닙니다.
							</video>
							<div v-show="!isVideo" class="empty-box">
								영상업로드시 미리 보기가 제공됩니다.😊
							</div>
						</v-col>
					</v-row>
				</div>
				<div>
					<div class="bold">커버 이미지</div>
					<v-row>
						<v-col md="5">
							<v-btn
								v-show="!isImg"
								tile
								class="py-7"
								text
								color="gray"
								id="img-btn"
								@click="clickImg"
							>
								<v-icon class="upload-icon pt-10">
									mdi-image-plus
								</v-icon>
								<div class="pb-4">파일을 선택해주세요.</div>
							</v-btn>
							<v-hover v-slot="{ hover }">
								<v-card
									class="mx-auto"
									color="grey lighten-4"
									max-width="600"
								>
									<v-img
										v-show="isImg"
										ref="img"
										height="300"
									>
										<v-expand-transition>
											<v-row
												v-if="hover"
												class="d-flex transition-fast-in-fast-out darken-2 v-card--reveal display-3 white--text"
												style="height: 100%; background-color:rgba(0,0,0,0.3);"
												align="center"
												justify="center"
											>
												<v-btn
													fab
													large
													@click="deleteImg"
													color="rgba(255,255,255,0.8)"
												>
													<v-icon
														color="rgba(0,0,0,0.5)"
														large
													>
														mdi-trash-can
													</v-icon>
												</v-btn>
											</v-row>
										</v-expand-transition>
									</v-img>
								</v-card>
							</v-hover>
						</v-col>
						<v-col md="7">
							<div
								class="bg-light-gray border-radius-10 pa-6"
								style="height:100%;"
							>
								<div class="bold">
									어떤 이미지를 선택하실지 고민이신가요?
								</div>
								<br />
								영상의 첫인상을 책임지는 썸네일!
								<br />
								어떤 이미지가 좋을지
								<span>
									<a href="#">
										<span>썸네일 가이드</span>
									</a>
								</span>
								를 확인해 보세요!
								<v-row>
									<v-col cols="4">
										<v-img
											class="border-radius-10"
											src="https://4.bp.blogspot.com/-FKj4lxWCQrA/U2oVUsAZrVI/AAAAAAAAApA/D0oJ6QP_z0g/s1600/phpCode.png"
											height="115"
										></v-img>
									</v-col>
									<v-col cols="4">
										<v-img
											class="border-radius-10"
											src="@/assets/thumbnail_1.jpg"
											height="115"
										></v-img>
									</v-col>
									<v-col cols="4">
										<v-img
											class="border-radius-10"
											src="@/assets/thumbnail_2.jpg"
											height="115"
										></v-img>
									</v-col>
								</v-row>
							</div>
						</v-col>
					</v-row>
					<input
						ref="videoFile"
						type="file"
						id="videoFile"
						v-show="false"
						accept="video/*"
						@change="setVideo"
					/>
					<input
						ref="file"
						type="file"
						id="file"
						v-show="false"
						accept="image/png, image/jpeg, image/bmp"
						@change="setImg"
					/>
				</div>
			</div>
			<div class="mt-6" style="text-align:end;">
				<v-btn dark color="#5500ff" @click="save" :loading="loading">
					저장
				</v-btn>
			</div>
			<v-dialog v-model="isAdd" max-width="600">
				<v-card>
					<v-card-title class="headline pb-6">
						보관함 목록
					</v-card-title>
					<v-card-text>
						<template v-for="(element, index) in videoList">
							<div
								class="mb-2 border-radius-10"
								:key="index + '_vid'"
							>
								<index-video
									:video="element"
									:idx="index"
									:selectVideoNo="selectVideoNo"
									@selectVideo="selectVideo"
								/>
							</div>
						</template>
					</v-card-text>
					<v-card-actions>
						<v-spacer></v-spacer>
						<v-btn
							color="error darken-1"
							text
							@click="isAdd = false"
						>
							<span class="bold" @click="resetVideo">취소</span>
						</v-btn>
						<v-btn color="darken-1" text @click="confirm">
							<span class="bold">확인</span>
						</v-btn>
					</v-card-actions>
				</v-card>
			</v-dialog>
		</v-col>
		<v-col cols="3">
			<div>💕썸네일 고르는 Tip!</div>
		</v-col>
	</v-row>
</template>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
import IndexVideo from '@/components/course/IndexVideo.vue';
import { mapGetters } from 'vuex';
import getBlobDuration from 'get-blob-duration';

export default {
	components: { IndexVideo },
	data() {
		return {
			isImg: false,
			isNew: false,
			isVideo: false,
			isAdd: false,
			videoList: [],
			connectVideo: null,
			selectVideoNo: -1,
			loading: false,
		};
	},
	methods: {
		setImg() {
			var self = this;
			var file = document.getElementById('file').files[0];
			var reader = new FileReader();

			reader.onloadend = function() {
				self.isImg = true;
				self.$refs.img.src = reader.result;
			};

			if (file) {
				reader.readAsDataURL(file);
			}
		},
		setVideo() {
			var self = this;
			var file = document.getElementById('videoFile').files[0];
			var reader = new FileReader();

			reader.onloadend = function() {
				self.isVideo = true;
				self.isNew = true;
				self.$refs.video.src = reader.result;
				self.setTotalTime(reader.result);
			};

			if (file) {
				reader.readAsDataURL(file);
			}
		},
		async setTotalTime(src) {
			const duration = await getBlobDuration(src);
			this.$store.commit('setVideoLength', parseInt(duration));
		},
		clickImg() {
			this.$refs.file.value = null;
			$('#file').click();
		},
		clickVideo() {
			this.$refs.videoFile.value = null;
			$('#videoFile').click();
		},
		deleteImg() {
			this.isImg = false;
		},
		selectVideo(idx) {
			this.selectVideoNo = idx;
		},
		resetVideo() {
			this.selectVideoNo = -1;
			this.isAdd = false;
		},
		confirm() {
			if (this.selectVideoNo == -1) {
				alert('영상을 선택해주세요');
				return;
			}
			this.isVideo = true;
			this.$refs.video.src = this.videoList[this.selectVideoNo].vid_url;
			this.setTotalTime(this.videoList[this.selectVideoNo].vid_url);
			this.connectVideo = this.videoList[this.selectVideoNo];
			this.isNew = false;
			this.resetVideo();
		},
		async save() {
			if (!this.isVideo) {
				alert('영상을 업로드 또는 보관함에서 선택해주세요');
				return;
			}
			this.loading = true;
			var thumbnailURL = await this.saveThumbnail();
			var videoURL = this.isNew
				? await this.saveVideo()
				: this.connectVideo.vid_url;
			this.loading = false;
			if (thumbnailURL) {
				this.$store.commit('setThumbnailURL', thumbnailURL.data);
			}
			if (videoURL) {
				this.$store.commit('setVideoURL', videoURL.data);
			}
			this.$emit('changeActive', 0);
		},
		saveThumbnail() {
			let formData = new FormData();
			if (document.getElementById('file').files[0]) {
				formData.append(
					'file',
					document.getElementById('file').files[0],
				);
				return this.$store.dispatch('upload', {
					data: formData,
					target: 'video/thumbnail',
				});
			}
		},
		saveVideo() {
			let formData = new FormData();
			if (document.getElementById('videoFile').files[0]) {
				formData.append(
					'file',
					document.getElementById('videoFile').files[0],
				);
				return this.$store.dispatch('upload', {
					data: formData,
					target: 'video',
				});
			}
		},
	},
	mounted() {
		this.$store.dispatch('getSaveVideos').then(({ data }) => {
			for (let video of data) {
				this.$store
					.dispatch('getUserByNo', video.mbr_no)
					.then(({ data }) => {
						video.mbr_nick_name = data.nickname;
						video.mbr_profile = data.profile;
						this.videoList.push(video);
					});
			}
		});
	},
	computed: {
		...mapGetters(['uploadVideo']),
	},
};
</script>

<style scoped>
.label {
	padding-bottom: 8px;
}

.item {
	padding-bottom: 24px;
}

#background-img {
	display: none;
	cursor: pointer;
}

#img-btn {
	width: 100%;
	height: 300px;
	border: 1px solid #c9c9c9;
	border-radius: 10px;
	cursor: pointer;
	font-weight: 600;
	font-size: 14px;
	text-align: center;
}

#banner-btn {
	width: 100%;
	height: 100%;
	border: 1px solid #c9c9c9;
	border-radius: 10px;
	cursor: pointer;
	font-weight: 600;
	font-size: 14px;
	text-align: center;
}

.upload-icon {
	font-size: 30px;
	color: black;
}

a {
	text-decoration: none;
}

.add-btn {
	border: 1px solid #c9c9c9;
	font-weight: 600;
}

.empty-box {
	font-size: 30px;
	font-weight: 600;
	color: #b5b5b5;
	text-align: center;
	min-height: 100px;
}
</style>

<style>
#edit-cover .v-btn__content {
	display: inline-block !important;
	text-align: center;
	margin: 0 auto;
}
</style>
