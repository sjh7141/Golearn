<template>
	<v-row height="200">
		<v-col
			class="mx-3 pb-0"
			@click="select"
			style="color:black; cursor: pointer; text-align:center;"
		>
			<v-img
				class="border-radius-10"
				src="@/assets/thumbnail_2.jpg"
				width="100%"
				height="300px;"
				style="display: inline-block;"
			>
			</v-img>
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
					<img src="@/assets/default_profile.png" alt="프로필" />
				</v-avatar>
				<div
					style="display: inline-block; vertical-align:top; word-break:break-all;"
				>
					<span class="bold" style="font-size: 20px; color:#303030;">
						{{ video.vidTitle }}
					</span>
					<br />
					<span style="color:#5e5e5e;">
						{{ this.mbr.mbr_nickname }} • 조회수
						{{ video.vidView }}회 •
						{{ video.regDt | diffDate }}
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
		return {
			mbrNo: 0,
			mbr: {
				mbr_nickname: '우리동네대장',
				mbr_profile: 'default_profile.png',
			},
		};
	},
	methods: {
		select() {
			this.$emit('selectVideo', this.idx);
		},
	},
	mounted() {
		this.mbrNo = this.video.mbrNo;
		console.log(this.selectVideoNo, this.idx);
	},
};
</script>

<style scoped></style>
