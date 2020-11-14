<template>
	<v-card
		class="content-bg card mx-auto"
		width="250"
		height="220"
		flat
		tile
		router
		:to="`/channel/${channel.no}`"
	>
		<v-card-title>
			<v-avatar size="120" class="mx-auto">
				<v-img :src="`${channel.profile}`"></v-img>
			</v-avatar>
		</v-card-title>
		<v-row no-gutters justify="center">
			<v-col>
				<v-card-title
					class="pl-2 pt-2 subtitle-1 center font-weight-bold"
					style="line-height: 1.2rem;"
				>
					{{ channel.nickname }}
				</v-card-title>

				<v-card-subtitle class="center pl-2 pt-0">
					구독자 {{ channel.like_count | viewFormatter }} 명
				</v-card-subtitle>
			</v-col>
		</v-row>
	</v-card>
</template>

<script>
import moment from 'moment';
export default {
	name: 'ChannelCard',
	props: ['channel'],
	data() {
		return {};
	},
	computed: {},
	methods: {
		dateFormatter(date) {
			return moment(date).fromNow();
		},
	},
	created() {},
	filters: {
		viewFormatter(value) {
			if (value >= 1000000) {
				return '' + parseInt(value / 1000000) + '백만';
			} else if (value >= 10000) {
				return '' + parseInt(value / 10000) + '만';
			} else if (value >= 1000) {
				return '' + parseInt(value / 1000) + '천';
			} else return value;
		},
	},
	watch: {
		clear() {
			this.check = false;
		},
	},
};
</script>

<style scoped>
.pause {
	animation-play-state: paused;
}
.remove-card {
	position: absolute;
	width: 100%;
	height: 100%;
	top: 0px;
}
.center {
	text-align: center;
	justify-content: center;
}
</style>
