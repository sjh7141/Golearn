<template>
	<div style="width:100%; height:100%;">
		<AddMedia
			v-show="index == 0 || (isEmpty && index == 1)"
			@uploadFile="
				$emit('change', 1);
				isEmpty = false;
			"
		/>
		<Media v-show="index == 1 && !isEmpty" />
		<Caption v-show="index == 2" />
		<Background v-show="index == 3" />
	</div>
</template>

<script>
import AddMedia from '@/components/video/editor/Section/AddMedia.vue';
import Media from '@/components/video/editor/Section/Media.vue';
import Caption from '@/components/video/editor/Section/Caption.vue';
import Background from '@/components/video/editor/Section/Background.vue';
export default {
	name: 'EditSection',
	props: ['index'],
	components: {
		AddMedia,
		Media,
		Caption,
		Background,
	},
	data() {
		return {
			isEmpty: true,
		};
	},
	methods: {},
};
</script>

<style>
.theme--dark.v-btn:hover::before {
	opacity: 0 !important;
}
</style>
