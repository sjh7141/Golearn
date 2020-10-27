<template>
	<v-card
		ref="app"
		:width="width"
		:height="height"
		outlined
		flat
		tile
		class="pa-2 mr-6"
		style="overflow-y: auto; position:fixed; right:0;"
		:style="{ top: top }"
	>
		<div style="font-size:16px; font-weight:400;">
			코린아, 코딩하자! with 파이썬
		</div>
		<v-row class="pl-3 my-4" v-for="i in 10" :key="`test_${i}`">
			<v-col class="pa-0" :cols="3">
				<v-img
					:src="`https://picsum.photos/500/300?image=${i * 10 + 10}`"
				></v-img>
			</v-col>
			<v-col class="pa-0" :cols="9">
				제목
			</v-col>
		</v-row>
	</v-card>
</template>

<script>
export default {
	name: 'Chapter',
	data() {
		return {
			width: 0,
			height: 0,
			top: 64,
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
				this.width = 418;
				this.height =
					window.innerHeight - this.$refs.app.$el.offsetTop - 20;
			}
			console.log(this.height);
		},
	},
};
</script>

<style scoped></style>
