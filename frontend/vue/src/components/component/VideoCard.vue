<template>
	<v-hover v-slot="{ hover }">
		<v-card
			class="content-bg card mx-auto"
			width="250"
			height="220"
			flat
			tile
			router
			:to="`/watch/${video.vid_no}`"
		>
			<v-img
				:src="`${video.vid_thumbnail}`"
				height="150"
				width="250"
				centered
				:class="hover ? 'over' : 'out'"
				><v-fade-transition>
					<div
						v-if="hover"
						class="d-flex transition-fast-in-fast-out darken-2 v-card--reveal display-3 white--text"
						style="height: 100%; background-color:rgba(240,240,240,0.7);"
					>
						<v-icon class="mx-auto" color="red" x-large
							>mdi-play</v-icon
						>
					</div>
				</v-fade-transition></v-img
			>
			<v-row no-gutters>
				<v-col>
					<v-card-title
						class="pl-2 pt-3 subtitle-1 font-weight-bold"
						style="line-height: 1.2rem"
					>
						{{ video.vid_title }}
					</v-card-title>

					<v-card-subtitle class="pl-2 pt-0">
						조회수 {{ video.vid_view | viewFormatter }} 회<v-icon
							>mdi-circle-small</v-icon
						>{{ dateFormatter(video.reg_dt) }}
					</v-card-subtitle>
				</v-col>
			</v-row>
		</v-card>
	</v-hover>
</template>

<script>
import moment from 'moment';
export default {
	name: 'VideoCard',
	props: {
		video: {
			type: Object,
			required: true,
		},
	},
	data() {
		return {
			url: 'k3a402.p.ssafy.io',
		};
	},
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
};
</script>

<style scoped>
.card {
	background: #f9f9f9 !important;
}
.pause {
	animation-play-state: paused;
}
</style>
