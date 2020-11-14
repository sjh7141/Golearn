<template>
	<div>
		<h3>코스 소개</h3>
		<v-divider class="my-5" />
		<div v-html="info.cos_content"></div>
		<v-layout class="mt-5 mb-1">
			<v-spacer />
			<v-tooltip top>
				<template v-slot:activator="{ on }">
					<v-btn icon v-on="on" @click="toggleSubscribe">
						<v-icon
							size="25"
							:color="
								subscribed || isMine
									? '#9382D7'
									: 'rgba(0, 0, 0, 0.54)'
							"
							>{{
								subscribed || isMine
									? 'mdi-bell'
									: 'mdi-bell-outline'
							}}</v-icon
						>
					</v-btn>
				</template>
				<span>
					구독하기
				</span>
			</v-tooltip>
			<v-tooltip top>
				<template v-slot:activator="{ on }">
					<v-btn icon v-on="on" @click="toggleLike">
						<v-icon
							size="25"
							:color="liked ? '#9382D7' : 'rgba(0, 0, 0, 0.54)'"
							>{{
								liked ? 'mdi-heart' : 'mdi-heart-outline'
							}}</v-icon
						>
					</v-btn>
				</template>
				<span>
					좋아요
				</span>
			</v-tooltip>
			<v-tooltip top>
				<template v-slot:activator="{ on }">
					<v-btn icon v-on="on">
						<v-icon size="25">mdi-source-merge</v-icon>
					</v-btn>
				</template>
				<span>
					기여하기
				</span>
			</v-tooltip>
		</v-layout>
		<v-divider class="mb-5" />
	</div>
</template>

<script>
import { mapActions } from 'vuex';
export default {
	props: ['info'],
	watch: {
		info() {
			this.isLike(this.info.mbr_no).then(({ data }) => {
				this.subscribed = data;
			});
			this.getLikeCourse(this.info.cos_no).then(({ data }) => {
				this.liked = data;
			});
			this.isMine = this.$store.getters.user.no == this.info.mbr_no;
		},
	},
	mounted() {
		if (this.info.mbr_no) {
			this.isLike(this.info.mbr_no).then(({ data }) => {
				this.subscribed = data;
			});
			this.isMine = this.$store.getters.user.no == this.info.mbr_no;
		}
		if (this.info.cos_no) {
			this.getLikeCourse(this.info.cos_no).then(({ data }) => {
				this.liked = data;
			});
		}
	},
	data() {
		return {
			isMine: false,
			subscribed: false,
			liked: false,
		};
	},
	methods: {
		...mapActions([
			'isLike',
			'like',
			'unlike',
			'setLikeCourse',
			'removeLikeCourse',
			'getLikeCourse',
		]),
		toggleSubscribe() {
			if (this.isMine) return;

			if (this.subscribed) {
				this.unlike(this.info.mbr_no).then(() => {
					this.subscribed = false;
				});
			} else {
				this.like(this.info.mbr_no).then(() => {
					this.subscribed = true;
				});
			}
		},

		toggleLike() {
			if (this.liked) {
				this.removeLikeCourse(this.info.cos_no).then(() => {
					this.liked = false;
				});
			} else {
				this.setLikeCourse(this.info.cos_no).then(() => {
					this.liked = true;
				});
			}
		},
	},
};
</script>

<style></style>
