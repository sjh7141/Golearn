<template>
	<v-row height="200">
		<v-col
			class="mx-3 pb-0"
			@click="select"
			style="color:black; cursor: pointer; text-align:center;"
		>
			<v-img
				class="border-radius-10"
				:src="video.vid_thumbnail"
				width="100%"
				height="300px;"
				style="display: inline-block;"
			/>
			<v-fab-transition>
				<v-icon
					v-if="selectVideoNo == idx"
					x-large
					icon
					color="pink"
					style="position:absolute; right:40px;"
				>
					mdi-check-circle
				</v-icon>
			</v-fab-transition>
		</v-col>
		<v-col
			class="mx-3 pt-0"
			cols="12"
			@click="select"
			style="cursor: pointer;"
		>
			<div style="margin: 0 auto;">
				<v-avatar class="mr-2" style="vertical-align:top;">
					<img :src="video.mbr_profile" alt="프로필" />
				</v-avatar>
				<div
					style="display: inline-block; vertical-align:top; word-break:break-all;"
				>
					<span class="bold" style="color:#303030;">
						{{ video.vid_title }}
					</span>
					<br />
					<span style="color:#5e5e5e;">
						{{ video.mbr_nick_name }} • 조회수
						{{ video.vid_view }}회 •
						{{ video.reg_dt | diffDate }}
					</span>
				</div>
			</div>
		</v-col>
	</v-row>
</template>

<script>
export default {
	props: ['video', 'idx', 'selectVideoNo'],
	filters: {
		diffDate(val) {
			let diff = (new Date() - new Date(val)) / 1000;
			if (diff < 60) {
				return '방금 전';
			}
			diff /= 60;
			if (diff < 60) {
				return parseInt(diff) + '분 전';
			}

			diff /= 60;
			if (diff < 24) {
				return parseInt(diff) + '시간 전';
			}

			diff /= 24;
			if (diff < 7) {
				return parseInt(diff) + '일 전';
			}
			if (diff < 30) {
				return parseInt(diff / 7) + '주 전';
			}
			if (diff < 365) {
				return parseInt(diff / 30) + '달 전';
			}
			return parseInt(diff / 365) + '년 전';
		},
	},
	data() {
		return {};
	},
	methods: {
		select() {
			this.$emit('selectVideo', this.idx);
		},
	},
	mounted() {},
};
</script>

<style scoped></style>
