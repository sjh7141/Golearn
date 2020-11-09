<template>
	<div
		id="edit-article"
		style="background-color:#17171E; width:100%; height:100%;"
		@mouseup="resizeEnd"
		@mousemove.capture="resizeLayer($event)"
	>
		<v-layout class="px-8 py-1">
			<v-btn
				depressed
				color="#1C1C26"
				dark
				v-for="btn in cmdBtns"
				:key="`cmdBtn_${btn.title}`"
				class="mr-1"
			>
				<v-icon left color="#80818B"> {{ btn.icon }} </v-icon>
				<span style="color:#80818B; font-size:14px;">{{
					btn.title
				}}</span>
			</v-btn>
			<v-spacer />
			<div
				style="font-size:13px; font-weight:500; margin-top:auto; margin-bottom:auto;"
			>
				<span>
					<span class="time-hm">
						{{ currentTime | convertM }}:{{ currentTime | convertS
						}}<span class="time-s"
							>.{{ currentTime | convertMS }}
						</span>
					</span>
				</span>
				<span style="color:#666673"> / </span>
				<span>
					<span class="time-hm">
						{{ totalTime | convertM }}:{{ totalTime | convertS
						}}<span class="time-s"
							>.{{ totalTime | convertMS }}
						</span>
					</span>
				</span>
			</div>
			<v-spacer />
			<v-btn
				depressed
				color="#1C1C26"
				dark
				v-for="btn in sizeBtns"
				:key="`cmdBtn_${btn.title}`"
				class="mr-1"
				@click="btn.action"
			>
				<v-icon left color="#80818B"> {{ btn.icon }} </v-icon>
				<span style="color:#80818B; font-size:14px;">{{
					btn.title
				}}</span>
			</v-btn>
		</v-layout>
		<div
			style="overflow-x:scroll; height:calc(100% - 56px); width:auto; position: relative;"
			ref="box"
			@mousedown="
				setTimerDrag = true;
				setCurrentTime;
			"
			@mousemove="setCurrentTime"
			@mouseup="setTimerDrag = false"
		>
			<div
				style="position: absolute; border-left:2px solid white; height:100%; margin-left:50px; z-index:1; margin-top:1px;"
				:style="{ left: caretPosition + 'px' }"
			></div>

			<v-layout ref="timeline">
				<div style="min-width:50px; border-top:1px solid #30303A;" />

				<div
					style="border-top:1px solid #30303A; border-left:1px solid #30303A; font-size:14px; color:#4B4B50; font-weight:500; min-width:calc((100% - 50px) / 20);  position:relative;"
					v-for="(item, i) in timeline"
					:key="`timeline_${i}`"
				>
					<span class="pl-1 pt-1 " style="float:left;">
						{{ item }}
					</span>
					<div
						style="width:50%; height:16px; border-left:1px solid #30303A;position: absolute; right:0px;"
					/>
				</div>
			</v-layout>
			<v-row
				align="center"
				class="ma-0"
				style="height:calc(100% - 27px);"
				:style="{ width: timeWidth + 'px' }"
			>
				<div>
					<v-row align="center" class="ma-0" style="height:50px;">
						<v-icon color="#80818B" style="width:50px;">
							mdi-movie-outline</v-icon
						>

						<div
							v-for="(item, i) in videoList"
							:key="`video_${i}`"
							style="position:relative;"
						>
							<v-btn
								small
								class="px-0"
								depressed
								style="background-color:#766af6;
								color:white; font-weight:300;
								font-size:14px; display:flex; min-width: 0px;
								overflow: hidden;"
								:style="{
									width:
										durationToWidth(item.duration) + 'px',
								}"
								:class="{
									'selected-item': selectedItem == item,
								}"
								:ripple="false"
								@mousedown.stop="1"
								@click.stop="selectedItem = item"
							>
								<span class="px-2"> {{ item.name }}</span>
							</v-btn>
							<div
								v-if="
									item.type == 'image' ||
										item.type == 'background'
								"
								style="position: absolute; top:0px; right:-5px; width:10px; height:28px; cursor:col-resize;"
								class="resize"
								@mousedown.stop="resizeStart($event, item, 0)"
								@click.stop="1"
							></div>
						</div>
					</v-row>
					<v-row align="center" class="ma-0" style="height:50px;">
						<v-icon color="#80818B" style="width:50px;">
							mdi-music-note</v-icon
						>
						<div
							v-for="(item, i) in audioList"
							:key="`audio_${i}`"
							style="position:relative;"
						>
							<v-btn
								small
								class="px-0"
								depressed
								style="background-color:#4980F1;
								color:white; font-weight:300;
								font-size:14px; display:flex; min-width: 0px;
								overflow: hidden;"
								:style="{
									width:
										durationToWidth(item.duration) + 'px',
								}"
								:class="{
									'selected-item': selectedItem == item,
								}"
								:ripple="false"
								@mousedown.stop="1"
								@click.stop="selectedItem = item"
							>
								<span class="px-2"> {{ item.name }}</span>
							</v-btn>
						</div>
					</v-row>
					<v-row align="center" class="ma-0" style="height:50px;">
						<v-icon color="#80818B" style="width:50px;">
							mdi-format-text</v-icon
						>
						<div
							v-for="(item, i) in captionList"
							:key="`caption_${i}`"
							style="position:relative;"
						>
							<v-btn
								small
								class="px-0"
								depressed
								style="background-color:#766af6;
								color:white; font-weight:300;
								font-size:14px; display:flex; min-width: 0px;
								overflow: hidden;"
								:style="{
									width:
										durationToWidth(item.duration) + 'px',
								}"
								:class="{
									'selected-item': selectedItem == item,
								}"
								:ripple="false"
								@mousedown.stop="1"
								@click.stop="selectedItem = item"
							>
								<span class="px-2"> {{ item.name }}</span>
							</v-btn>
							<div
								v-if="item.type == 'caption'"
								style="position: absolute; top:0px; right:-5px; width:10px; height:28px; cursor:col-resize;"
								class="resize"
								@mousedown.stop="resizeStart($event, item, 1)"
								@click.stop="1"
							></div>
						</div>
					</v-row>
				</div>
			</v-row>
		</div>
	</div>
</template>

<script>
import EventBus from '@/util/EventBus.js';
export default {
	name: 'EditArticle',
	filters: {
		convertM(t) {
			let ret = parseInt(t / 60);
			if (ret < 10) ret = '0' + ret;
			return ret;
		},
		convertS(t) {
			let ret = parseInt(t) % 60;
			if (ret < 10) ret = '0' + ret;
			return ret;
		},
		convertMS(t) {
			let ret = parseInt((t - parseInt(t)) * 100);
			if (ret < 10) ret = '0' + ret;
			return ret;
		},
	},
	watch: {
		videoDuration() {
			this.calcTotalTime();
		},
		audioDuration() {
			this.calcTotalTime();
		},
		captionDuration() {
			this.calcTotalTime();
		},
		interval() {
			this.setTimeline();
		},
		totalTime() {
			if (this.totalTime > this.saveTime) {
				this.setTimeline();
			}
			if (this.currentTime > this.totalTime) {
				this.currentTime = this.totalTime;
				this.caretPosition = this.durationToWidth(this.currentTime);
			}
		},
		currentTime() {
			this.caretPosition = this.durationToWidth(this.currentTime);
			if (this.currentTime > this.totalTime) {
				this.currentTime = this.totalTime;
			}
		},
	},
	data() {
		return {
			intervalList: [1, 2, 5, 10, 30, 60, 120, 300, 600, 1800, 3600],
			interval: 1,
			currentTime: 0,
			totalTime: 0,
			cmdBtns: [
				{
					title: 'Copy',
					icon: 'mdi-content-copy',
				},
				{
					title: 'Paste',
					icon: 'mdi-content-duplicate',
				},
				{
					title: 'Delete',
					icon: 'mdi-trash-can-outline',
				},
				{
					title: 'Cut',
					icon: 'mdi-content-cut',
				},
			],
			sizeBtns: [
				{
					title: 'Zoom In',
					icon: 'mdi-plus',
					action: this.zoomIn,
				},
				{
					title: 'Zoom Out',
					icon: 'mdi-minus',
					action: this.zoomOut,
				},
				{
					title: 'Fit To Screen',
					icon: 'mdi-arrow-collapse mdi-rotate-45',
					action: this.fitScreen,
				},
			],

			videoList: [],
			audioList: [],
			captionList: [],

			videoDuration: 0,
			audioDuration: 0,
			captionDuration: 0,
			timeWidth: 0,

			resizeType: -1,
			resizeItem: -1,
			resizeElement: null,

			timeline: [],
			saveTime: 0,

			selectedItem: null,
			caretPosition: 0,

			setTimerDrag: false,
		};
	},
	mounted() {
		EventBus.$on('addPlayer', item => {
			if (
				item.type == 'video' ||
				item.type == 'image' ||
				item.type == 'background'
			) {
				this.videoList.push(item);
				if (this.videoList.length == 2) {
					console.dir(this.videoList[0]);
					console.dir(this.videoList[1]);
					console.log(this.videoList[0] === this.videoList[1]);
				}
				this.videoDuration += item.duration;
			} else if (item.type == 'audio') {
				this.audioList.push(item);
				this.audioDuration += item.duration;
			} else if (item.type == 'caption') {
				this.captionList.push(item);
				this.captionDuration += item.duration;
			}
			// if (this.totalTime == 0) this.fitScreen();
			EventBus.$emit('changePlayer', [
				...this.videoList,
				...this.audioList,
				...this.captionList,
			]);
		});

		window.addEventListener('resize', this.handleTimeLine);
		this.setTimeline();
	},
	updated() {
		this.$nextTick(function() {
			this.handleTimeLine();
		});
	},
	beforeDestroy() {
		window.removeEventListener('resize', this.handleTimeLine);
	},
	methods: {
		handleTimeLine() {
			this.timeWidth = this.$refs.timeline.scrollWidth;
		},
		zoomIn() {
			if (this.interval <= 1) {
				return;
			}
			for (let i in this.intervalList) {
				if (this.interval <= this.intervalList[i]) {
					this.interval = this.intervalList[i - 1];
					return;
				}
			}
			this.handleTimeLine();
		},
		zoomOut() {
			for (let i in this.intervalList) {
				i = parseInt(i);
				if (this.interval <= this.intervalList[i]) {
					this.interval = this.intervalList[i + 1];
					break;
				}
			}
			if (this.interval * 20 > this.totalTime) {
				this.fitScreen();
			}
			this.handleTimeLine();
		},
		fitScreen() {
			this.interval = this.totalTime / 19;
			if (this.interval < 1) this.interval = 1;
			this.handleTimeLine();
		},
		durationToWidth(val) {
			const wps =
				(this.$refs.timeline.clientWidth - 50) / 20 / this.interval;
			return val * wps;
		},

		resizeLayer(e) {
			// if (e.target.className == 'resize') return;
			switch (this.resizeType) {
				case 0:
					this.resizeImage(e);
					break;
				case 1:
					this.resizeCaption(e);
			}
		},
		resizeImage(e) {
			let curX = e.x - 60 + this.$refs.box.scrollLeft;
			let width = curX - this.resizeElement.offsetLeft;
			if (width < 1) width = 1;
			this.videoDuration -= this.resizeItem.duration;
			this.resizeItem.duration =
				width *
				(this.interval / ((this.$refs.timeline.clientWidth - 50) / 20));
			this.videoDuration += this.resizeItem.duration;
			let tmpArr = [];
			for (let i in this.videoList) {
				tmpArr.push(this.videoList[i]);
			}
			this.videoList = tmpArr;
		},
		resizeCaption(e) {
			let curX = e.x - 60 + this.$refs.box.scrollLeft;
			let width = curX - this.resizeElement.offsetLeft;
			if (width < 1) width = 1;
			this.captionDuration -= this.resizeItem.duration;
			this.resizeItem.duration =
				width *
				(this.interval / ((this.$refs.timeline.clientWidth - 50) / 20));
			this.captionDuration += this.resizeItem.duration;
			let tmpArr = [];
			for (let i in this.captionList) {
				tmpArr.push(this.captionList[i]);
			}
			this.captionList = tmpArr;
		},

		resizeStart(e, item, type) {
			console.log(e);
			this.resizeType = type;
			this.resizeItem = item;
			this.resizeElement = e.target.parentElement;
		},

		resizeEnd() {
			this.resizeType = -1;
			this.resizeItem = null;
			this.resizeElement = null;
		},

		calcTotalTime() {
			this.totalTime = Math.max(
				this.videoDuration,
				this.audioDuration,
				this.captionDuration,
			);
		},

		setTimeline() {
			let data = [];
			let tmpTime = this.totalTime;
			if (tmpTime < 19) {
				tmpTime = 19;
			}
			for (let i = 0; parseInt(i) <= tmpTime; i += this.interval) {
				data.push(
					this.$moment
						.utc(i * 1000)
						.format(i >= 3600 ? 'h:mm:ss' : 'm:ss'),
				);
			}

			this.timeline = data;
			this.saveTime = this.totalTime;
			this.caretPosition = this.durationToWidth(this.currentTime);
		},

		setCurrentTime(e) {
			if (this.setTimerDrag) {
				let curX = e.x - 110 + this.$refs.box.scrollLeft;
				this.currentTime =
					curX *
					(this.interval /
						((this.$refs.timeline.clientWidth - 50) / 20));

				if (this.currentTime < 0) this.currentTime = 0;
				if (this.currentTime > this.totalTime)
					this.currentTime = this.totalTime;
			}
		},
	},
};
</script>

<style>
#edit-article .v-btn:hover {
	opacity: 0.7;
}

#edit-article .time-hm {
	color: #ffffff;
}
#edit-article .time-s {
	color: #8d8d9c;
}

#edit-article .selected-item {
	border: 2px solid #00d7ff;
}
#edit-article span {
	-ms-user-select: none;
	-moz-user-select: -moz-none;
	-khtml-user-select: none;
	-webkit-user-select: none;
	user-select: none;
}
</style>

<style scoped>
::-webkit-scrollbar {
	height: 10px;
}
::-webkit-scrollbar-thumb:horizontal {
	background-color: #333344;
	border-radius: 4px;
}
</style>
