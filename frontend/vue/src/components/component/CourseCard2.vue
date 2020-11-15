<template>
	<div ref="app">
		<v-card
			tile
			class="mx-auto  clickable asdf"
			width="300"
			height="330"
			style="border: 1px solid #e5e5e5;"
			:to="`/course/${course.cos_no}/intro`"
		>
			<v-img width="300" height="168.75" :src="course.cos_thumbnail">
				<!-- <v-progress-linear
					style="position:absolute;bottom:0px;width:100%"
					value="30"
					buffer-value="100"
					color="#c3aed6"
				></v-progress-linear> -->
			</v-img>

			<v-card-title
				style="font-size:0.9rem; font-weight:bold;  padding-bottom:10px; padding-top:10px"
				><v-list-item-title
					style="font-size:0.9rem; font-weight:bold; "
					>{{ course.cos_title }}</v-list-item-title
				></v-card-title
			>

			<v-card-text>
				<v-container>
					<v-row
						class="content name"
						@click.prevent
						@click="$router.push(`/channel/${course.mbr_no}`)"
					>
						By {{ course.mbr_nickname }}
					</v-row>
					<v-row class="hashtag">
						<span
							style="font-size:0.8rem;"
							class="mr-1 "
							v-for="(i, index) in course.tags"
							:key="`${course.cos_no}_tag_${index}`"
							>#{{ i.tag_name }}</span
						>
					</v-row>
					<v-row class="others">
						<span class="mr-2">
							<v-icon color="#d4d4d4" small>mdi-account</v-icon>
							<span>{{ course.viewer_count }}</span>
						</span>
						<span class="mr-2">
							<v-icon class="mr-1" color="#d4d4d4" small
								>mdi-heart</v-icon
							>
							<span>{{ course.like_count }}</span>
						</span>
						<v-spacer></v-spacer>
						<span class="mr-1">
							{{ difficultContent(course.tags) }}
						</span>
						<v-icon x-small :color="difficultColor(course.tags)"
							>mdi-circle</v-icon
						>
					</v-row>
				</v-container>
			</v-card-text>
		</v-card>
	</div>
</template>

<script>
export default {
	props: ['course'],

	mounted() {},
	methods: {
		difficultContent(dif) {
			if (dif.length >= 3) {
				return '심화';
			} else if (dif.length >= 2) {
				return '일반';
			} else {
				return '기초';
			}
		},
		difficultColor(dif) {
			if (dif.length >= 3) {
				return 'red';
			} else if (dif.length >= 2) {
				return 'yellow';
			} else {
				return 'green';
			}
		},
	},
};
</script>

<style scoped>
.v-card {
	font-family: 'BMJUA';
}
.content {
	font-family: 'BMJUA';
	color: black;
	font-size: 0.8rem;
	word-break: break-all;
	display: -webkit-box;
	margin-bottom: 0.5rem;
	line-height: 1.2em;
	text-overflow: ellipsis;
	overflow: hidden;
	-webkit-line-clamp: 1;
	-webkit-box-orient: vertical;
}
.hashtag {
	word-break: break-all;
	overflow: hidden;
	-webkit-line-clamp: 1;
	text-overflow: ellipsis;
	height: 30px;
}
.name:hover {
	color: purple;
	cursor: pointer;
}
.others {
	font-size: 0.8rem;
}
.clickable {
	cursor: pointer;
}
.asdf {
	box-shadow: none !important;
}
.asdf:hover {
	box-shadow: 0 0 0 0.2rem #c3aed644 !important;
}
.v-card .title {
	white-space: nowrap;
	word-break: normal;
	overflow: hidden;
	display: block !important;
	text-overflow: ellipsis;
}
</style>
