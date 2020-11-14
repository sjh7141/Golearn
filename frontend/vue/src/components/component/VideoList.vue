<template>
	<div ref="app">
		<v-card class="transparent" flat v-if="videos.length !== 0">
			<v-card-title
				><div>동영상</div>
				<v-spacer></v-spacer>
				<template v-if="isLogin">
					<v-slide-x-transition mode="out-in">
						<v-btn
							v-if="hide"
							class="mx-1"
							@click="remove"
							outlined
							color="purple"
							>삭제</v-btn
						>
					</v-slide-x-transition>
					<v-slide-x-transition mode="out-in">
						<v-btn
							v-if="hide"
							class="mx-1"
							@click="cancle"
							outlined
							color="purple"
							>취소</v-btn
						>
					</v-slide-x-transition>
					<v-slide-x-reverse-transition mode="out-in">
						<v-btn
							color="gray"
							icon
							large
							v-if="!hide && channel.no == user.no"
							class="mx-1"
							@click="hide = true"
						>
							<v-icon>mdi-cog</v-icon>
						</v-btn>
					</v-slide-x-reverse-transition>
				</template>
			</v-card-title>
			<v-row>
				<v-col
					cols="12"
					sm="6"
					md="3"
					lg="2"
					v-for="(video, i) in loading ? 10 : videos"
					:key="i"
					class="mx-xs-auto"
				>
					<v-skeleton-loader type="card-avatar" :loading="loading">
						<video-card
							:card="{ maxWidth: 250 }"
							:video="video"
							:hide="hide"
							:clear="clear"
						></video-card>
					</v-skeleton-loader>
				</v-col>
			</v-row>
		</v-card>
	</div>
</template>

<script>
import { mapGetters, mapActions, mapMutations } from 'vuex';
import VideoCard from './VideoCard';
export default {
	props: ['channel', 'videos'],
	data: () => ({
		loading: false,
		clear: false,
		hide: false,
	}),
	computed: {
		...mapGetters(['user', 'isLogin', 'checked']),
	},
	components: {
		VideoCard,
	},
	methods: {
		...mapActions(['getChannelVideos', 'removeVideos']),
		...mapMutations(['clearChecked']),
		async remove() {
			const self = this;
			this.removeVideos(this.checked).then(function() {
				self.$emit('getChannel', self.$route.params.id);
				self.checked = [];
			});
			self.hide = false;
		},
		cancle() {
			this.hide = false;
			this.clear = !this.clear;
			this.clearChecked();
		},
	},
	watch: {},
};
</script>

<style scoped>
.card {
	background: #f9f9f9 !important;
}
</style>
