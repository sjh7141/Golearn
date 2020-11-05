<template>
	<v-row height="200">
		<v-col
			class="mx-3"
			@click="select"
			style="color:black; cursor: pointer;"
		>
			<v-img
				class="ml-2 mr-4 border-radius-10"
				src="@/assets/thumbnail_1.jpg"
				height="130"
				width="210"
				style="display: inline-block;"
			/>
			<v-avatar class="mr-2" style="vertical-align:top;">
				<img src="@/assets/default_profile.png" alt="프로필" />
			</v-avatar>
			<div
				style="display: inline-block; vertical-align:top; word-break:break-all; width:210px;"
			>
				<span class="bold" style="font-size: 20px;">
					{{ course.cosTitle }}
				</span>
				<br />
				{{ this.mbr.mbr_nickname }}
				<br />
				<v-row>
					<v-col cols="12" class="py-0">
						<v-chip
							class="mr-1 mb-1"
							v-for="(tag, i) in course.tags"
							:key="i"
							small
						>
							{{ tag }}
						</v-chip>
					</v-col>
				</v-row>
			</div>
		</v-col>
	</v-row>
</template>

<script>
export default {
	props: ['course', 'idx'],
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
			this.$emit('selectCourse', this.idx);
		},
	},
	mounted() {
		this.mbrNo = this.course.mbrNo;
	},
};
</script>

<style scoped></style>
