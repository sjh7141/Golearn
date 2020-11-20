<template>
	<div id="edit-add-media" style="width:100%; height:100%;">
		<div
			style="width:90%; height:80%; border:1px dashed rgba(229,230,241,0.25); border-radius:5px;"
			@drop.prevent="dragFiles"
			@dragover.prevent
		>
			<v-row align="end" justify="center" style="height:65%; width:100%;">
				<!-- <i class="fas fa-photo-video"></i> -->
				<v-icon size="148" color="#D5D5DE">
					fas fa-photo-video
				</v-icon>
				<!-- <v-img
					src="@/assets/test.png"
					max-width="25%"
					style=""
					contain
				/> -->
			</v-row>
			<v-row
				justify="center"
				style="color:#D5D5DE;font-size:17px;"
				class="mt-10"
			>
				영상, 오디오 또는 이미지 파일을 드래그해보세요!
			</v-row>
		</div>
		<div style="width:90%; height:calc(20% - 16px);" class="mt-4">
			<input
				type="file"
				v-show="false"
				@change="addFiles"
				id="file"
				ref="file"
				multiple
			/>
			<v-hover v-slot="{ hover }">
				<v-btn
					width="31%"
					depressed
					:ripple="false"
					outlined
					:style="{
						'background-color': hover ? '#2B2947' : '#2C2C38',
						'border-color': hover ? '#9382D7' : '#4a4a56',
					}"
					style="height:70%;margin-right:3%"
					dark
					@click="$refs.file.click()"
				>
					<v-icon left size="20"> mdi-file-upload-outline </v-icon>
					<span style="font-size:14px; font-weight:400; ">
						내 컴퓨터
					</span>
				</v-btn>
			</v-hover>
			<v-hover v-slot="{ hover }">
				<v-btn
					width="32%"
					depressed
					:ripple="false"
					outlined
					:style="{
						'background-color': hover ? '#2B2947' : '#2C2C38',
						'border-color': hover ? '#9382D7' : '#4a4a56',
					}"
					style="height:70%;margin-right:3%;opacity: 1 !important;"
					dark
					@click="storage = true"
				>
					<v-icon left size="20" class="mr-3">
						mdi-play-box-multiple-outline
					</v-icon>
					<span style="font-size:14px; font-weight:400;">
						보관함
					</span>
				</v-btn>
			</v-hover>
			<v-hover v-slot="{ hover }">
				<v-btn
					width="31%"
					depressed
					:ripple="false"
					outlined
					:style="{
						'background-color': hover ? '#2B2947' : '#2C2C38',
						'border-color': hover ? '#9382D7' : '#4a4a56',
					}"
					style="height:70%;"
					dark
					@click="startRecordingScreen"
				>
					<v-icon left size="20"> mdi-video-outline </v-icon>
					<span style="font-size:14px; font-weight:400; ">
						화면 녹화
					</span>
				</v-btn>
			</v-hover>
		</div>
		<v-dialog
			v-model="storage"
			hide-overlay
			max-width="1052"
			overlay-opacity="1"
			overlay-color="white"
		>
			<v-card tile outlined>
				<v-list class="pa-0 ma-0">
					<v-list-item>
						<v-list-item-title>
							보관함
						</v-list-item-title>
						<v-list-item-action>
							<v-btn icon @click="storage = false">
								<v-icon>
									mdi-close
								</v-icon>
							</v-btn>
						</v-list-item-action>
					</v-list-item>
					<v-divider />
					<v-layout wrap style="height:462px; overflow-y: scroll;">
						<v-flex
							md3
							lg3
							xl3
							class="pa-5"
							v-for="(item, i) in storageList"
							:key="`storage_${i}`"
						>
							<v-card
								height="190"
								tile
								flat
								outlined
								link
								class="edit-card"
								@click.stop="selected = i"
								:style="{
									border:
										selected == i
											? '3px solid #9382d7'
											: '1px solid rgba(0,0,0,0.12)',
								}"
							>
								<v-img :src="item.vid_thumbnail" />
								<v-list-item-title
									style="font-size:14px;"
									class="pa-3"
								>
									{{
										item.vid_title
											? item.vid_title
											: 'Untitled'
									}}
								</v-list-item-title>
							</v-card>
						</v-flex>
						<v-flex
							v-if="storageList.length == 0"
							class="pa-3"
							style="font-size:16px;"
						>
							No data
						</v-flex>
					</v-layout>
					<v-divider />
					<v-list-item>
						<v-spacer />
						<v-btn
							depressed
							:color="selected >= 0 ? '#9382d7' : '#d4d4d4'"
							dark
							class="my-3"
							tile
							@click="1"
							:loading="loading"
						>
							<span
								style="font-size:14px;font-weight:400"
								@click="
									selected >= 0
										? loadVideo(storageList[selected])
										: 1
								"
							>
								불러오기
							</span>
						</v-btn>
					</v-list-item>
				</v-list>
			</v-card>
		</v-dialog>
		<v-dialog
			v-model="screenDialog"
			fullscreen
			hide-overlay
			transition="dialog-bottom-transition"
			eager
		>
			<v-card class="preview-card" style="width:100%; height:100%;" tile>
				<div style="width:100%; height:10%; background-color:#26282A" />

				<video
					id="screen-video-preview"
					playsinline
					autoplay
					muted
					style="height:80%; display:block;"
					class="mx-auto"
				></video>
				<v-row
					align="center"
					justify="center"
					class="ma-0"
					style="height:10%; background-color:#1C1E1F"
				>
					<v-btn fab class="mr-3" @click="toggleMic">
						<v-icon
							size="24"
							:color="playMicrophone ? 'success' : 'error'"
						>
							{{
								playMicrophone
									? 'mdi-microphone'
									: 'mdi-microphone-off'
							}}
						</v-icon>
					</v-btn>
					<v-btn fab class="mr-3" @click="toggleCamcorder">
						<v-icon
							size="24"
							:color="playCamcorder ? 'success' : 'error'"
						>
							{{
								playCamcorder
									? 'mdi-video'
									: 'mdi-video-off-outline'
							}}
						</v-icon>
					</v-btn>
					<v-btn fab>
						<v-icon
							size="24"
							color="error"
							@click="previewMediaRecorder.stop()"
						>
							mdi-stop
						</v-icon>
					</v-btn>
				</v-row>
			</v-card>
		</v-dialog>
	</div>
</template>

<script src=""></script>

<script>
import { mapActions } from 'vuex';
import EventBus from '@/util/EventBus.js';
import axios from 'axios';
export default {
	name: 'EditAddMedia',
	watch: {
		storage() {
			if (this.storage) {
				this.loadStorage();
			} else {
				this.selected = -1;
			}
		},
	},
	data() {
		return {
			whiteList: [
				'image/jpg',
				'image/jpeg',
				'image/png',
				'image/bmp',
				'audio/mpeg',
				'audio/wav',
				'video/mp4',
				'video/webm',
			],
			storageList: [],
			storage: false,
			selected: -1,
			loading: false,

			recordedChunks: [],
			screenDialog: false,

			previewMediaRecorder: null,
			camcorder: null,

			playCamcorder: false,
			playMicrophone: false,
		};
	},
	mounted() {},
	methods: {
		...mapActions(['getStorageList']),
		startRecordingScreen() {
			const screen = document.querySelector('#screen-video-preview');
			this.previewMediaRecorder = null;
			this.camcorder = null;

			this.playCamcorder = false;
			this.playMicrophone = false;

			navigator.mediaDevices
				.getDisplayMedia({ video: true, audio: false })
				.then(stream => {
					this.screenDialog = true;
					screen.srcObject = stream;

					navigator.mediaDevices
						.getUserMedia({ video: true })
						.then(stream => {
							const camcorder = document.createElement('video');
							this.camcorder = camcorder;

							camcorder.srcObject = stream;
							camcorder.muted = true;
							camcorder.play();
						});

					var options = { mimeType: 'video/webm; codecs=vp9' };
					const mediaRecorder = new MediaRecorder(stream, options);
					this.previewMediaRecorder = mediaRecorder;

					mediaRecorder.ondataavailable = this.handleDataAvailable;
					mediaRecorder.start();

					mediaRecorder.onstop = () => {
						this.screenDialog = false;
						if (this.camcorder) {
							document.exitPictureInPicture();
							this.camcorder.pause();
						}

						const mergedBlob = new Blob(this.recordedChunks, {
							type: 'video/webm; codecs=vp9',
						});

						this.addRecordedScreen(mergedBlob);
					};
				});
		},
		handleDataAvailable(event) {
			if (event.data.size > 0) {
				this.recordedChunks.push(event.data);
			} else {
				// ...
			}
		},
		toggleMic() {
			if (this.playMicrophone) {
			} else {
			}
			this.playMicrophone = !this.playMicrophone;
		},
		toggleCamcorder() {
			if (this.playCamcorder) {
				document.exitPictureInPicture();
			} else {
				if (this.camcorder) {
					this.camcorder.requestPictureInPicture();
				} else {
					alert('웹캠이 올바르게 연결되었는지 확인해주십시오.');
					return;
				}
			}
			this.playCamcorder = !this.playCamcorder;
		},
		dragFiles(e) {
			let flag = false;
			let droppedFiles = e.dataTransfer.files;
			if (!droppedFiles) return;
			[...droppedFiles].forEach(f => {
				flag |= this.uploadFile(f);
			});
			if (flag) {
				this.$emit('uploadFile', 1);
			}
		},
		addFiles() {
			let flag = false;
			let addedFiles = document.getElementById('file').files;
			if (!addedFiles) return;
			[...addedFiles].forEach(f => {
				flag |= this.uploadFile(f);
			});
			if (flag) {
				this.$emit('uploadFile', 1);
			}
		},
		uploadFile(f) {
			if (this.whiteList.indexOf(f.type) == -1) {
				alert(f.name + '은 지원하지 않는 파일 형식입니다.');
				return false;
			}
			EventBus.$emit('addMedia', f);
			return true;
		},
		loadStorage() {
			this.getStorageList().then(({ data }) => {
				this.storageList = data;
			});
		},
		blobToString(b) {
			var u, x;
			u = URL.createObjectURL(b);
			x = new XMLHttpRequest();
			x.open('GET', u, false); // although sync, you're not fetching over internet
			x.send();
			URL.revokeObjectURL(u);
			return x.responseText;
		},

		loadVideo(video) {
			const self = this;
			this.loading = true;
			axios
				.get(video.vid_url, { responseType: 'blob' })
				.then(({ data }) => {
					var reader = new FileReader();
					reader.onload = function(file) {
						video.blob = file.target.result;
						video.type = data.type;
						EventBus.$emit('loadVideo', video);
						self.$emit('uploadFile', 1);
						self.storage = false;
					};
					reader.readAsDataURL(data);
				})
				.finally(() => {
					this.loading = false;
				});
		},

		addRecordedScreen(blob) {
			const self = this;
			var reader = new FileReader();
			reader.onload = function(file) {
				const video = {
					vid_title: '녹화 화면',
				};

				video.blob = file.target.result;
				video.type = blob.type;
				EventBus.$emit('loadVideo', video);
				self.$emit('uploadFile', 1);
			};
			reader.readAsDataURL(blob);
		},
	},
};
</script>

<style>
.v-dialog {
	border-radius: 0px !important;
	box-shadow: none !important;
}

.edit-card:focus::before {
	opacity: 0 !important;
}

.preview-card {
	background-color: #222222 !important;
}

.preview-tab .v-tabs-bar {
	height: 100% !important;
}
</style>
