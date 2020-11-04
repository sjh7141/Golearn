<template>
	<v-card
		class="content-bg card mx-auto"
		:max-width="card.maxWidth"
		flat
		tile
		router
		:to="`/watch/${video.vid_no}`"
	>
		<v-img :src="`${video.vid_thumbnail}`"></v-img>
		<v-row no-gutters>
			<v-col cols="2" v-if="card.type != 'noAvatar'">
				<v-list-item
					class="pl-0 pt-3"
					router
					:to="`/channels/${channel._id}`"
				>
					<v-list-item-avatar>
						<v-img
							v-if="channel.photoUrl !== 'no-photo.jpg'"
							class="elevation-6"
							:src="`${url}/uploads/avatars/${channel.photoUrl}`"
						></v-img>
						<v-avatar v-else color="red">
							<span class="white--text headline ">
								{{
									channel.mbr_nickname
										.split('')[0]
										.toUpperCase()
								}}</span
							>
						</v-avatar>
					</v-list-item-avatar>
				</v-list-item>
			</v-col>
			<v-col>
				<v-card-title
					class="pl-2 pt-3 subtitle-1 font-weight-bold"
					style="line-height: 1.2rem"
				>
					{{ video.vid_title }}
				</v-card-title>

				<v-card-subtitle class="pl-2 pb-0">
					{{ channel.mbr_nickname }}
				</v-card-subtitle>
				<v-card-subtitle class="pl-2 pt-0">
					{{ video.vid_view }} views<v-icon>mdi-circle-small</v-icon
					>{{ dateFormatter(video.reg_dt) }}
				</v-card-subtitle>
			</v-col>
		</v-row>
	</v-card>
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
		channel: {
			type: Object,
			required: true,
		},
		card: Object,
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
	created() {
		console.log(this.video);
	},
};
</script>

<style></style>
