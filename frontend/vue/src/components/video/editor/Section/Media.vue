<template>
	<div style="width:100%; height:100%;">
		<v-layout wrap>
			<div
				v-for="(item, i) in fileList"
				:key="`media-list_${i}`"
				class="mr-4 mb-4"
				style="width:133.3px; max-width:133.3px;"
			>
				<v-hover v-slot="{ hover }">
					<div
						style="max-width:133.3px; max-height:75px; width:133.3px; background-color:black; height:75px;display:flex; border-radius:3px; position:relative;"
					>
						<img
							:src="item.blob"
							v-if="item.type == 'image'"
							width="133"
							height="75"
							style="object-fit: contain;"
						/>
						<img
							:src="item.thumbnail"
							v-else-if="item.type == 'video'"
							width="133"
							height="75"
						/>
						<div
							style="width:133px; height:75px;"
							v-else-if="item.type == 'audio'"
						>
							<div
								style="display:block; margin:auto; width:60px;height:75px; padding-top:7.5px;"
							>
								<v-icon color="#D5D5DE" size="60">
									mdi-music-box-outline
								</v-icon>
							</div>
						</div>
						<v-btn
							dark
							icon
							absolute
							right
							bottom
							v-show="hover"
							style="right:0px; bottom:0px;"
							@click="addPlayer(item)"
						>
							<v-icon>
								mdi-plus
							</v-icon>
						</v-btn>
					</div>
				</v-hover>
				<v-list-item-subtitle
					dark
					style="color:#80818B; font-size:12px;"
				>
					{{ item.name }}
				</v-list-item-subtitle>
			</div>
		</v-layout>
	</div>
</template>

<script>
import EventBus from '@/util/EventBus.js';
export default {
	name: 'EditMedia',
	watch: {},
	data() {
		return {
			fileList: [],
			typeList: ['image', 'audio', 'video'],
		};
	},
	mounted() {
		EventBus.$on('addMedia', f => {
			var reader = new FileReader();
			let newFile = {
				name: f.name,
				fadeIn: 0,
				fadeOut: 0,
				thumbnail: '',
				blob: '',
			};
			this.typeList.forEach(type => {
				if (f.type.indexOf(type) != -1) newFile.type = type;
			});

			reader.onload = function(file) {
				newFile.blob = file.target.result;
				if (newFile.type == 'video' || newFile.type == 'audio') {
					const video = document.createElement('video');
					video.src = file.target.result;
					video.onloadeddata = () => {
						newFile.duration = video.duration;
						newFile.startTime = 0;
						newFile.volume = 100;
						if (newFile.type == 'video') {
							const canvas = document.createElement('canvas');
							video.currentTime = 1;

							video.addEventListener('timeupdate', function() {
								canvas.width = video.videoWidth;
								canvas.height = video.videoHeight;
								const ctx = canvas.getContext('2d');
								ctx.drawImage(
									video,
									0,
									0,
									canvas.width,
									canvas.height,
								);
								newFile.thumbnail = canvas.toDataURL();
							});
						}
					};
				} else {
					newFile.duration = 10;
				}
			};
			reader.readAsDataURL(f);

			this.fileList.push(newFile);
		});
	},
	methods: {
		addPlayer(item) {
			let copiedItem = {};
			for (let i in item) copiedItem[i] = item[i];
			EventBus.$emit('addPlayer', copiedItem);
		},
	},
};
</script>

<style></style>
