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
						style="max-width:133.3px; max-height:75px; background-color:black; width:133.3px; height:75px;display:flex; border-radius:3px; position:relative;"
					>
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
