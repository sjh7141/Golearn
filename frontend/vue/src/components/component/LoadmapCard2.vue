<template>
	<div ref="app">
		<v-card
			class="mx-auto  clickable asdf"
			width="370"
			height="300"
			style="border: 1px solid #e5e5e5;"
			:to="`/roadmap/${loadmap.ldm_no}`"
		>
			<v-img
				style="border-bottom:1px solid #e4e4e499"
				width="370"
				height="208.125"
				:src="loadmap.ldm_thumbnail"
			>
			</v-img>

			<v-card-title
				style="font-size:0.9rem; font-weight:bold; padding-bottom:0px; padding-top:10px"
				>{{ loadmap.ldm_title }}</v-card-title
			>

			<v-card-text>
				<v-container class="mt-0">
					<v-row
						class="content name"
						@click.prevent
						@click="$router.push(`/channel/${loadmap.mbr_no}`)"
					>
						By {{ loadmap.mbr_nickname }}
					</v-row>
					<v-row>
						<span
							style="font-size:0.8rem;"
							class="mr-1"
							v-for="(i, index) in loadmap.tags"
							:key="`${loadmap.cos_no}_tag_${index}`"
							>#{{ i.tag_name }}</span
						>
					</v-row>
				</v-container>
			</v-card-text>
		</v-card>
	</div>
</template>

<script>
export default {
	props: ['loadmap'],

	mounted() {},
	methods: {},
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
</style>
