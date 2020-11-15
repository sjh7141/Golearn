<template>
	<v-hover v-slot="{ hover }">
		<v-card
			class="content-bg card mx-auto"
			width="250"
			height="220"
			flat
			tile
			router
			:to="`/channel/play/${video.vid_no}`"
		>
			<v-img
				:src="`${video.vid_thumbnail}`"
				height="150"
				width="250"
				centered
				:class="hover ? 'over' : 'out'"
				><v-fade-transition>
					<div
						v-if="hover && !hide"
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
						class="pl-2 pt-3 subtitle-1 title font-weight-bold"
						style="line-height: 1.2rem;"
					>
						{{ video.vid_title }}
					</v-card-title>

					<v-card-subtitle class="pl-2 pt-0" style="font-size:13px">
						조회수 {{ video.vid_view | viewFormatter }} 회<v-icon
							>mdi-circle-small</v-icon
						>{{ dateFormatter(video.reg_dt) }}
					</v-card-subtitle>
				</v-col>
			</v-row>
			<v-fade-transition>
				<div
					@click.prevent
					@click="
						check
							? updateSelectedTenants(null)
							: updateSelectedTenants(check)
					"
					v-if="hide"
					class="d-flex transition-fast-in-fast-out darken-2 v-card--reveal display-3 white--text remove-card"
				>
					<div style="position: absolute; top:5%;left:5%">
						<v-checkbox
							class="ma-0"
							color="red"
							:value="video.vid_no"
							v-model="check"
							hide-details
							@click.stop.prevent
							@click="updateSelectedTenants(check)"
						>
						</v-checkbox>
					</div>
				</div>
			</v-fade-transition>
			<!-- <div
				v-if="hide"
				@click.prevent
				style="position: absolute; width:100%;height: 100%;background-color:rgba(144,144,144,0.3); top:0px;z-index: 100;"
			>
				
			</div> -->
		</v-card>
	</v-hover>
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
	},
	watch: {
		clear() {
			this.check = false;
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
.remove-card {
	position: absolute;
	width: 100%;
	height: 100%;
	top: 0px;
}
.v-card .title {
	white-space: nowrap;
	word-break: normal;
	overflow: hidden;
	display: block !important;
	text-overflow: ellipsis;
}
</style>
