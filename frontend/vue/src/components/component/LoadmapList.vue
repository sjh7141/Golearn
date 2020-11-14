<template>
	<div ref="app">
		<v-card class="transparent" flat v-if="loadmaps.length !== 0">
			<v-card-title>로드맵</v-card-title>
			<v-row>
				<v-col
					cols="12"
					sm="6"
					md="4"
					lg="3"
					v-for="(loadmap, i) in loading ? 10 : loadmaps"
					:key="i"
					class="mx-xs-auto"
				>
					<v-skeleton-loader type="card-avatar" :loading="loading">
						<loadmap-card
							:card="{ maxWidth: 250 }"
							:loadmap="loadmap"
						></loadmap-card>
					</v-skeleton-loader>
				</v-col>
			</v-row>
		</v-card>
	</div>
</template>

<script>
import LoadmapCard from './LoadmapCard';
export default {
	props: ['loadmaps'],
	data: () => ({
		loading: false,
		errored: false,
		showSubBtn: true,
		items: [
			{ name: '홈', link: '/home' },
			{ name: '동영상', link: '/videos' },
		],
		channel: {
			mbr_nickname: 'asm9677',
			_id: 'asdf',
		},
		currentUser: {
			_id: 'asdf',
		},
		signinDialog: false,
		details: {},
	}),
	computed: {},
	components: {
		LoadmapCard,
	},
};
</script>

<style scoped>
.card {
	/* background: #f9f9f9 !important; */
}
</style>
