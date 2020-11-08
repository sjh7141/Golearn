<template>
	<v-row>
		<v-col cols="12">
			<video-list :videos="videos" :flag="false"> </video-list>
		</v-col>
	</v-row>
</template>

<script>
import VideoList from '@/components/mypage/VideoList';

export default {
	components: { VideoList },
	props: ['videos'],
	data() {
		return {};
	},
	computed: {},
	methods: {},
	mounted() {},
};
</script>

<style></style>
