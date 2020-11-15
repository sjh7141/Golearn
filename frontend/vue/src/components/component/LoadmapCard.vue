<template>
	<v-hover v-slot="{ hover }">
		<v-card
			class="content-bg card mx-auto"
			width="450"
			height="320"
			flat
			tile
			router
			:to="`/roadmap/${loadmap.ldm_no}`"
		>
			<v-img
				:src="`${loadmap.ldm_thumbnail}`"
				height="250"
				width="450"
				:class="hover ? 'over' : 'out'"
				><v-fade-transition>
					<div
						v-if="hover"
						class="d-flex transition-fast-in-fast-out darken-2 v-card--reveal display-3 white--text"
						style="height: 100%; background-color:rgba(240,240,240,0.7);"
					>
						<!-- <v-icon class="mx-auto" color="red" x-large
							>mdi-play</v-icon
						> -->
						<div class="mx-auto content-wrapper">
							<span
								class="mx-auto content"
								v-html="loadmap.ldm_content"
							>
							</span>
						</div>
					</div> </v-fade-transition
			></v-img>
			<v-row no-gutters>
				<v-col>
					<v-card-title
						class="pl-2 pt-3 subtitle-1 title font-weight-bold"
						style="line-height: 1.2rem"
					>
						{{ loadmap.ldm_title }}
					</v-card-title>

					<v-card-subtitle class="pl-2 pt-0" style="font-size:13px">
						{{ dateFormatter(loadmap.reg_dt) }}
					</v-card-subtitle>
				</v-col>
			</v-row>
		</v-card>
	</v-hover>
</template>

<script>
import moment from 'moment';
export default {
	name: 'CoruseCard',
	props: {
		loadmap: {
			type: Object,
			required: true,
		},
	},
	data() {
		return {};
	},
	methods: {
		dateFormatter(date) {
			return moment(date).fromNow();
		},
	},
	created() {},
};
</script>

<style scoped>
.content-wrapper {
	right: 0;
	left: 0;
	margin: auto;
	padding: 0;
	width: 100%;
	height: 100%;
	padding: 0.75rem;
}
.content {
	color: black;
	font-weight: bold;
	font-size: 0.85rem;
	word-break: break-all;
	display: -webkit-box;
	margin-bottom: 0.5rem;
	line-height: 1.2em;
	text-overflow: ellipsis;
	overflow: hidden;
	-webkit-line-clamp: 7;
	-webkit-box-orient: vertical;
}
.v-card .title {
	white-space: nowrap;
	word-break: normal;
	overflow: hidden;
	display: block !important;
	text-overflow: ellipsis;
}
</style>
