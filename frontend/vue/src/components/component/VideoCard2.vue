<template>
	<v-card
		class="content-bg card mx-auto"
		width="250"
		height="240"
		flat
		tile
		router
		:to="`/channel/play/${video.vid_no}`"
	>
		<div class="image-wrapper">
			<v-img
				:src="`${video.vid_thumbnail}`"
				height="150"
				width="250"
				centered
			></v-img>
		</div>
		<v-row no-gutters>
			<v-col class="pl-1">
				<v-card-title
					class="pl-0 pt-3 pb-1 title"
					style="line-height: 1.2rem"
				>
					{{ video.vid_title }}
				</v-card-title>
				<v-card-text
					@click.prevent
					@click="$router.push(`/channel/${video.mbr_no}`)"
					class="nickname pa-0"
				>
					{{ video.mbr_nickname }}
				</v-card-text>
				<v-card-subtitle class="pl-0 pt-0 others">
					<v-icon small>mdi-play</v-icon>
					{{ (video.vid_view * 13) | vf }}
					<v-icon>mdi-circle-small</v-icon
					>{{ dateFormatter(video.reg_dt) }}
				</v-card-subtitle>
			</v-col>
		</v-row>
	</v-card>
</template>

<script>
import { mapGetters, mapMutations } from 'vuex';
import moment from 'moment';
export default {
	name: 'VideoCard',
	props: ['video', 'hide', 'clear'],
	data() {
		return {
			url: 'k3a402.p.ssafy.io',
			check: false,
		};
	},
	computed: {
		...mapGetters(['checked']),
	},
	methods: {
		...mapMutations(['setChecked', 'removeChecked']),
		dateFormatter(date) {
			return moment(date).fromNow();
		},
		updateSelectedTenants(value) {
			if (value !== null) {
				this.setChecked(this.video.vid_no);
				this.check = this.video.vid_no;
			} else {
				this.removeChecked(this.video.vid_no);
				this.check = false;
			}
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
		vf(value) {
			return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
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
.v-card {
	font-family: 'BMJUA' !important;
}
.image-wrapper {
	overflow: hidden;
}
.pause {
	animation-play-state: paused;
}
.remove-card {
	position: absolute;
	width: 100%;
	height: 100%;
	top: 0px;
}
.nickname:hover {
	color: purple;
	cursor: pointer;
}

.v-card .v-image {
	overflow: hidden;
	transition: background-color 0.2s, transform 0.2s ease-in-out;
}
.v-card .v-image:hover {
	transform: scale(1.2);
}
.title {
	font-weight: bold;
	font-size: 0.85rem !important;
	font-family: 'BMJUA' !important;
}
.nickname {
	font-size: 0.8rem;
}
.others {
	font-size: 0.8rem;
	opacity: 0.8;
}
.v-card .title {
	white-space: nowrap;
	word-break: normal;
	overflow: hidden;
	display: block !important;
	text-overflow: ellipsis;
}
</style>
