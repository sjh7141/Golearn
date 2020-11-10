<template>
	<div ref="app">
		<v-card elevation="0" class="mx-auto my-12" width="350" height="400">
			<v-img width="350" height="200" :src="course.cos_thumbnail"></v-img>

			<v-card-title style="font-size:0.9rem; font-weight:bold">{{
				course.cos_title
			}}</v-card-title>

			<v-card-text>
				<v-container>
					<v-row style="height: 18px;" class="my-2 content">
						{{ course.cos_content }}
					</v-row>

					<v-row class="content">
						{{ course.mbr_nickname }}
					</v-row>
					<v-row>
						<span
							style="font-size:0.8rem"
							class="mx-1"
							v-for="(i, index) in course.tags"
							:key="index"
							>#{{ i.tag_name }}</span
						>
					</v-row>
					<v-row>
						<v-icon color="#d4d4d4" small>mdi-account</v-icon>
						123
						<v-icon color="#d4d4d4" small>mdi-heart</v-icon>
						123
						<v-spacer></v-spacer>
						<v-icon x-small :color="difficult(course.tags)"
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

	mounted() {
		console.log(this.course);
	},
	methods: {
		difficult(dif) {
			if (dif.length >= 3) {
				return 'red';
			}
		},
	},
};
</script>

<style scoped>
.content {
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
</style>
