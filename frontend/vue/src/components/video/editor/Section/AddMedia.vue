<template>
	<div style="width:100%; height:100%;">
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
					width="49%"
					depressed
					:ripple="false"
					outlined
					:style="{
						'background-color': hover ? '#2B2947' : '#2C2C38',
						'border-color': hover ? '#9382D7' : '#4a4a56',
					}"
					style="height:70%;"
					dark
					@click="$refs.file.click()"
				>
					<v-icon left size="20"> mdi-file-upload-outline </v-icon>
					<span style="font-size:14px; font-weight:400; ">
						내 PC
					</span>
				</v-btn>
			</v-hover>
			<v-hover v-slot="{ hover }">
				<v-btn
					width="49%"
					depressed
					:ripple="false"
					outlined
					:style="{
						'background-color': hover ? '#2B2947' : '#2C2C38',
						'border-color': hover ? '#9382D7' : '#4a4a56',
					}"
					style="height:70%;margin-left:2%;opacity: 1 !important;"
					dark
				>
					<v-icon left size="20" class="mr-3">
						mdi-play-box-multiple-outline
					</v-icon>
					<span style="font-size:14px; font-weight:400;">
						보관함
					</span>
				</v-btn>
			</v-hover>
		</div>
	</div>
</template>

<script>
import EventBus from '@/util/EventBus.js';
export default {
	name: 'EditAddMedia',
	data() {
		return {
			whiteList: ['image/png', 'audio/mpeg', 'video/mp4', 'video/gif'],
		};
	},
	methods: {
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
	},
};
</script>

<style></style>
