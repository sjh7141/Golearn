<template>
	<div id="edit-aside" ref="editAside" style="width:100%; height:100%;">
		<canvas
			id="preview"
			:width="width"
			:height="height"
			style="background-color:black; margin-left: auto; margin-right: auto; display:block;"
		/>
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
			<v-icon color="#B3B3B6" size="38" @click="togglePlay" class="px-3">
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
	mounted() {
		this.canvasResize();
		window.addEventListener('resize', this.canvasResize);

		this.movie = new vd.Movie(document.getElementById('preview'));
		vd.event.subscribe(this.movie, 'movie.timeupdate', () => {
			// console.log(this.movie.currentTime);
		});

		EventBus.$on('changePlayer', mediaList => {
			mediaList.forEach(media => {
				if (media.type == 'video') {
					let video = document.createElement('video');
					video.src = media.blob;
					video.onloadeddata = () => {
						console.dir(video);
						const canvasWidth = document.getElementById('preview')
							.clientWidth;
						const canvasHeight = document.getElementById('preview')
							.clientHeight;
						const videoHeight = canvasHeight;
						const videoWidth =
							(canvasHeight * video.videoWidth) /
							video.videoHeight;
						this.movie.addLayer(
							new vd.layer.Video(0, video, {
								width: videoWidth,
								height: videoHeight,
								mediaY: (canvasHeight - videoHeight) / 2,
							}),
						);
					};
				}
			});
		});
	},
	beforeDestroy() {
		window.removeEventListener('resize', this.canvasResize);
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

		togglePlay() {
			this.isPlay = !this.isPlay;
			if (this.isPlay) {
				this.movie.play();
			} else {
				this.movie.pause();
			}
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
