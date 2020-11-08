<template>
	<div id="edit-aside" ref="editAside" style="width:100%; height:100%;">
		<canvas
			:width="width"
			:height="height"
			style="background-color:black; margin-left: auto; margin-right: auto; display:block;"
		/>
		<v-row justify="center" class="mt-4">
			<v-icon color="#B3B3B6" size="20" class="px-3">
				mdi-skip-previous
			</v-icon>

			<v-icon color="#B3B3B6" size="20" class="px-3">
				mdi-fast-forward mdi-rotate-180
			</v-icon>
			<v-icon color="#B3B3B6" size="38" @click="1" class="px-3">
				{{ isPlay ? 'mdi-pause' : 'mdi-play' }}
			</v-icon>
			<v-icon color="#B3B3B6" size="20" class="px-3">
				mdi-fast-forward
			</v-icon>
			<v-icon color="#B3B3B6" size="20" class="px-3">
				mdi-skip-next
			</v-icon>
		</v-row>
	</div>
</template>

<script>
export default {
	name: 'EditAside',
	data() {
		return {
			width: 0,
			height: 0,
			isPlay: false,
		};
	},
	mounted() {
		this.canvasResize();
		window.addEventListener('resize', this.canvasResize);
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
