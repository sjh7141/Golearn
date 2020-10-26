<template>
	<v-card
		ref="app"
		:width="width"
		:height="height"
		outlined
		flat
		tile
		class="pa-1"
	>
		<div style="padding:4px; font-size:16px; font-weight:400;">
			다음 동영상
		</div>
		<v-list>
			<v-list-item class="pa-1">
				<v-list-avatar>
					<v-img
						:width="100"
						src="https://picsum.photos/500/300?image=20"
						aspect-ratio="1.7"
					></v-img>
				</v-list-avatar>
				<v-list-content>
					<v-list-item-title>제목</v-list-item-title>
				</v-list-content>
			</v-list-item>
		</v-list>
	</v-card>
</template>

<script>
export default {
	name: 'Chapter',
	data() {
		return {
			width: 0,
			height: 0,
		};
	},
	mounted() {
		this.resizeChapter();
		window.addEventListener('resize', this.resizeChapter);
	},
	beforeDestroy() {
		window.removeEventListener('resize', this.resizeChapter);
	},
	methods: {
		resizeChapter() {
			if (window.innerWidth < 960) {
				this.width = '100%';
				this.height = 500;
			} else {
				this.width = 442;
				this.height = window.innerHeight - this.$refs.app.$el.offsetTop;
			}
			console.log(this.height);
		},
	},
};
</script>

<style scoped></style>
