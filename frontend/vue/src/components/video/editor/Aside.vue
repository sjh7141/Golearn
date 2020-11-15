<template>
	<div id="edit-aside" ref="editAside" style="width:100%; height:100%;">
		<canvas
			id="preview"
			:width="width"
			:height="height"
			style="background-color:black; margin-left: auto; margin-right: auto; display:block;"
		>
		</canvas>

		<v-row justify="center" class="mt-4">
			<v-icon
				color="#B3B3B6"
				size="20"
				class="px-3"
				@click="setCurrentTime(0)"
			>
				mdi-skip-previous
			</v-icon>

			<v-icon
				color="#B3B3B6"
				size="20"
				class="px-3"
				@click="moveCurrentTime(-10)"
			>
				mdi-fast-forward mdi-rotate-180
			</v-icon>
			<v-icon
				color="#B3B3B6"
				size="38"
				@click="isPlay ? pause() : play()"
				class="px-3"
				v-if="!loading"
			>
				{{ isPlay ? 'mdi-pause' : 'mdi-play' }}
			</v-icon>
			<v-btn loading icon color="#B3B3B6" v-show="loading" />
			<v-icon
				color="#B3B3B6"
				size="20"
				class="px-3"
				@click="moveCurrentTime(10)"
			>
				mdi-fast-forward
			</v-icon>
			<v-icon
				color="#B3B3B6"
				size="20"
				class="px-3"
				@click="setCurrentTime(duration)"
			>
				mdi-skip-next
			</v-icon>
		</v-row>
		<v-dialog v-model="dialog" max-width="700" persistent>
			<v-card color="#1C1C26" dark>
				<v-card-title>
					{{ fileName ? fileName : 'Untitled' }}
				</v-card-title>

				<v-card-text>
					<v-layout class="mt-2 mb-1">
						{{ isSuccess ? '업로드 중...' : '변환 중...' }}

						<v-spacer />
						{{ remainingTime }}분 남음
					</v-layout>
					<v-row>
						<v-col :cols="11" class="py-0">
							<div style="height:100%;">
								<v-progress-linear
									:value="progressRate"
									height="12"
									color="#9382d7"
									width="80%"
									rounded
									style="margin: 6px 0px;"
								></v-progress-linear>
							</div>
						</v-col>
						<v-col
							:cols="1"
							class="py-0 pl-0"
							style="font-size:14px;"
						>
							{{ progressRate }}%
						</v-col>
					</v-row>
				</v-card-text>
				<canvas
					id="exportPreview"
					width="652"
					height="366.75"
					style="background-color:black; margin-left: auto; margin-right: auto; display:block;"
				/>
				<div style="height:16px;" />
			</v-card>
		</v-dialog>
	</div>
</template>

<script src="" />
<script>
import { mapActions } from 'vuex';
import EventBus from '@/util/EventBus.js';

export default {
	name: 'EditAside',
	props: ['doExport'],
	data() {
		return {
			width: 0,
			height: 0,
			isPlay: false,
			movie: null,
			video: null,
			loading: false,

			sumVideo: 0,
			sumAudio: 0,
			sumCaption: 0,

			dialog: false,

			fileName: '',
			vid: null,

			remainingTime: 0,
			progressRate: 0,
			isSuccess: false,
		};
	},
	computed: {
		currentTime: {
			get() {
				return this.$store.getters.currentTime;
			},
			set(val) {
				this.$store.commit('setCurrentTime', val);
			},
		},
		duration: {
			get() {
				return this.$store.getters.duration;
			},
			set(val) {
				this.$store.commit('setDuration', val);
			},
		},
		mediaList: {
			get() {
				return this.$store.getters.mediaList;
			},
			set(val) {
				this.$store.commit('setMediaList', val);
			},
		},
		isChange: {
			get() {
				return this.$store.getters.isChange;
			},
			set(val) {
				this.$store.commit('setIsChange', val);
			},
		},
	},
	watch: {
		mediaList() {
			this.isChange = true;
		},
		isChange() {
			if (this.isChange) this.pause();
		},
		doExport() {
			this.export();
		},
	},
	mounted() {
		this.canvasResize();
		window.addEventListener('resize', this.canvasResize);

		EventBus.$on('exportVideo', this.export);
		EventBus.$on('pause', () => {
			this.pause();
		});
	},
	beforeDestroy() {
		window.removeEventListener('resize', this.canvasResize);
		this.movie.stop();
		this.currentTime = 0;
		this.duration = 0;
	},
	methods: {
		...mapActions(['upload', 'uploadVideo', 'saveVideo']),
		canvasResize() {
			const { clientWidth, clientHeight } = this.$refs.editAside;
			if (clientWidth > ((clientHeight - 50) * 16) / 9) {
				this.height = clientHeight - 50;
				this.width = (this.height * 16) / 9;
			} else {
				this.width = clientWidth;
				this.height = (this.width * 9) / 16;
			}
		},

		setCurrentTime(val) {
			this.pause();
			this.currentTime = val;
		},

		moveCurrentTime(val) {
			this.pause();
			this.currentTime += val;
		},

		async play() {
			if (this.loading || !this.mediaList.length) return;
			this.isPlay = true;

			if (this.isChange) {
				delete this.movie;
				this.movie = new vd.Movie(document.getElementById('preview'));

				vd.event.subscribe(this.movie, 'movie.timeupdate', () => {
					this.currentTime = this.movie.currentTime;
				});

				this.sumVideo = 0;
				this.sumAudio = 0;
				this.sumCaption = 0;
				this.loading = true;

				const { clientWidth, clientHeight } = document.getElementById(
					'preview',
				);
				this.addMedias(
					this.movie,
					this.mediaList,
					0,
					clientWidth,
					clientHeight,
					false,
				).finally(() => {
					this.isChange = false;
					this.loading = false;
					this.movie.setCurrentTime(this.currentTime);
					this.movie.play();
				});
			} else {
				this.movie.setCurrentTime(this.currentTime);
				this.movie.play();
			}
		},
		pause() {
			if (this.isPlay) {
				this.isPlay = false;
				this.movie.pause();
			}
		},

		export() {
			this.pause();
			this.dialog = true;

			this.fileName = '';
			this.vid = null;

			const canvas = document.createElement('canvas');

			canvas.width = 1920;
			canvas.height = 1080;
			const movie = new vd.Movie(canvas);

			this.sumVideo = 0;
			this.sumAudio = 0;
			this.sumCaption = 0;
			vd.event.subscribe(movie, 'movie.timeupdate', () => {
				if (movie.currentTime == 0) {
					this.progressRate = 100;
					return;
				}
				this.remainingTime = parseInt(
					(this.duration - movie.currentTime) / 60,
				);
				if (this.remainingTime < 1) this.remainingTime = 1;

				this.progressRate = (
					(movie.currentTime / this.duration) *
					100
				).toFixed(1);
			});

			this.addMedias(movie, this.mediaList, 0, 1920, 1080, false).finally(
				() => {
					const canvas = document.getElementById('exportPreview');
					const tmpMovie = new vd.Movie(canvas);

					this.sumVideo = 0;
					this.sumAudio = 0;
					this.sumCaption = 0;
					this.addMedias(
						tmpMovie,
						this.mediaList,
						0,
						canvas.width,
						canvas.height,
						true,
					).finally(() => {
						tmpMovie.play();
					});

					this.movieToBlob(movie);
				},
			);
		},

		movieToBlob(movie) {
			movie.record(25).then(res => {
				let formData = new FormData();
				formData.append('file', res);
				// console.dir(URL.createObjectURL(res));
				this.upload({
					data: formData,
					target: 'video',
				})
					.then(({ data }) => {
						this.isSuccess = true;
						this.$store.commit('setEditURL', data);
						this.$router.push('/video/upload');
					})
					.catch(() => {
						alert('업로드 실패!');
						this.dialog = false;
					});
			});
		},

		async addMedias(movie, mediaList, i, width, height, muted) {
			return new Promise((resolve, reject) => {
				if (i >= mediaList.length) resolve();
				else {
					this.addMedia(
						movie,
						mediaList[i],
						width,
						height,
						muted,
					).then(() => {
						resolve(
							this.addMedias(
								movie,
								mediaList,
								i + 1,
								width,
								height,
								muted,
							),
						);
					});
				}
			});
		},

		async addMedia(movie, media, width, height, muted) {
			return new Promise(resolve => {
				if (media.type == 'video') {
					if (this.fileName == '') {
						this.fileName = media.name;
						this.vid = media.vid_no;
					}
					let video = document.createElement('video');
					video.src = media.blob;

					let opacity = {};

					opacity[0] = 0;
					opacity[media.fadeIn / 10] = 1;
					opacity[media.duration] = 0;
					opacity[media.duration - media.fadeOut / 10] = 1;

					video.onloadeddata = () => {
						movie.addLayer(
							new vd.layer.Video(this.sumVideo, video, {
								duration: media.duration,
								mediaStartTime: media.startTime,
								width: width,
								height: height,
								volume: media.volume / 100,
								opacity,
								muted,
							}),
						);
						this.sumVideo += media.duration;
						resolve();
					};
				} else if (media.type == 'image') {
					let opacity = {};

					opacity[0] = 0;
					opacity[media.fadeIn / 10] = 1;
					opacity[media.duration] = 0;
					opacity[media.duration - media.fadeOut / 10] = 1;

					let image = document.createElement('img');
					image.src = media.blob;
					image.onload = () => {
						movie.addLayer(
							new vd.layer.Image(
								this.sumVideo,
								media.duration,
								image,
								{
									width: width,
									height: height,
									opacity,
								},
							),
						);
						this.sumVideo += media.duration;
						resolve();
					};
				} else if (media.type == 'background') {
					let opacity = {};

					opacity[0] = 0;
					opacity[media.fadeIn / 10] = 1;
					opacity[media.duration] = 0;
					opacity[media.duration - media.fadeOut / 10] = 1;

					movie.addLayer(
						new vd.layer.Visual(this.sumVideo, media.duration, {
							background: media.color,
							width: width,
							height: height,
							opacity,
						}),
					);
					this.sumVideo += media.duration;
					resolve();
				} else if (media.type == 'audio') {
					let audio = document.createElement('audio');
					audio.src = media.blob;

					audio.onloadeddata = () => {
						movie.addLayer(
							new vd.layer.Audio(this.sumAudio, audio, {
								duration: media.duration,
								mediaStartTime: media.startTime,
								volume: media.volume / 100,
								muted,
							}),
						);
						this.sumAudio += media.duration;
						resolve();
					};
				} else if (media.type == 'caption') {
					let opacity = {};

					opacity[0] = 0;
					opacity[media.fadeIn / 10] = 1;
					opacity[media.duration] = 0;
					opacity[media.duration - media.fadeOut / 10] = 1;

					movie.addLayer(
						new vd.layer.Text(
							this.sumCaption,
							media.duration,
							media.name,
							{
								font: `${media.size}px sans-serif`,
								x: (width / 3) * ((media.position - 1) % 3),
								y:
									(height / 3) *
									parseInt((media.position - 1) / 3),
								color: media.color,
								opacity,
							},
						),
					);
					this.sumCaption += media.duration;
					resolve();
				}
			});
		},
	},
};
</script>

<style>
#edit-aside .v-icon:hover:before {
	color: white !important;
	cursor: pointer;
}
#edit-aside .v-icon::after {
	display: none !important;
}
</style>
