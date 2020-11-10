<template>
	<div id="edit-aside" ref="editAside" style="width:100%; height:100%;">
		<canvas
			id="preview"
			:width="width"
			:height="height"
			style="background-color:black; margin-left: auto; margin-right: auto; display:block;"
		/>
		<!-- <v-overlay>
			</ -->
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
			>
				{{ isPlay ? 'mdi-pause' : 'mdi-play' }}
			</v-icon>
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
	</div>
</template>

<script src="" />
<script>
import EventBus from '@/util/EventBus.js';
export default {
	name: 'EditAside',
	data() {
		return {
			width: 0,
			height: 0,
			isPlay: false,
			movie: null,
			video: null,
			mediaList: [],
			isChange: false,
			count: 0,
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
	},
	watch: {
		currentTime() {
			// this.movie.setCurrentTime(this.currentTime);
		},
		count() {
			if (!this.count) {
				this.movie.setCurrentTime(this.currentTime);
				this.movie.play();
			}
		},
	},
	mounted() {
		this.canvasResize();
		window.addEventListener('resize', this.canvasResize);

		EventBus.$on('exportVideo', this.export);
		EventBus.$on('pause', () => {
			this.pause();
		});

		EventBus.$on('changePlayer', mediaList => {
			console.dir('changePlayer');
			this.mediaList = mediaList;
			this.isChange = true;
		});
	},
	beforeDestroy() {
		window.removeEventListener('resize', this.canvasResize);
		this.movie.stop();
		this.currentTime = 0;
		this.duration = 0;
	},
	methods: {
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
			this.currentTime = val;
		},

		moveCurrentTime(val) {
			this.currentTime += val;
		},

		play() {
			this.isPlay = true;
			if (this.isChange) {
				delete this.movie;

				this.movie = new vd.Movie(document.getElementById('preview'));

				vd.event.subscribe(this.movie, 'movie.timeupdate', () => {
					this.currentTime = this.movie.currentTime;
				});
				let sumVideo = 0;
				let sumAudio = 0;
				let sumCaption = 0;
				this.count = this.mediaList.length;
				this.mediaList.forEach(media => {
					if (media.type == 'video') {
						let video = document.createElement('video');
						video.src = media.blob;

						video.onloadeddata = () => {
							const {
								clientWidth,
								clientHeight,
							} = document.getElementById('preview');

							this.movie.addLayer(
								new vd.layer.Video(sumVideo, video, {
									duration: media.duration,
									mediaStartTime: media.startTime,
									width: clientWidth,
									height: clientHeight,
								}),
							);
							this.count -= 1;
							sumVideo += media.duration;
						};
					}
				});
				this.isChange = false;
			}
		},
		pause() {
			this.isPlay = false;
			this.movie.pause();
		},

		export() {
			this.movie.record(25).then(res => {
				console.dir(URL.createObjectURL(res));
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
