<template>
	<div ref="app">
		<div
			id="left-panel"
			:style="{ width: width + 'px', height: totalHeight + 'px' }"
			style="overflow-y:auto;"
		>
			<multi-view
				:width="width"
				:height="height"
				@toggleEditor="editor = !editor"
			/>
			zz<br />
			zz<br />
			zz<br />
			zz<br />
			zz<br />
			zz<br />
			zz<br />
			zz<br />
			zz<br />
			zz<br />
			zz<br />
			zz<br />
			zz<br />
			zz<br />
		</div>
		<div id="right-panel">
			<Chapter />
		</div>
	</div>
</template>

<script>
import Chapter from '@/components/video/Chapter.vue';
import MultiView from '@/components/video/MultiView.vue';
export default {
	name: 'VideoPlay',
	components: {
		MultiView,
		Chapter,
	},
	data() {
		return {
			totalHeight: 0,
			width: 0,
			height: 0,
		};
	},
	mounted() {
		window.addEventListener('resize', this.resizePlay);
		this.resizePlay();
	},
	beforeDestroy() {
		window.removeEventListener('resize', this.resizePlay);
	},
	methods: {
		resizePlay() {
			if (window.innerWidth < 960) {
				this.width = window.innerWidth;
			} else {
				this.width = window.innerWidth - 450;
			}
			this.height = (this.width * 9) / 16;
			this.totalHeight = window.innerHeight - this.$refs.app.offsetTop;
		},
	},
};
</script>

<style scoped>
#left-panel {
	float: left;
}
#right-panel {
	float: right;
}
</style>
