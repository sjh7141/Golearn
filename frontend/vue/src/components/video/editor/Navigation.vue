<template>
	<v-tabs
		:value="index"
		vertical
		icons-and-text
		background-color="#272731"
		slider-color="#9382d7"
		dark
		style="width:60px; height:100%;"
		:ripple="false"
	>
		<span
			style="width: 36px; height: 36px; margin:0 auto;"
			class="mt-4 mb-3"
			><svg viewBox="0 0 36 36" fill="none">
				<rect width="36" height="36" rx="4" fill="#665DC3"></rect>
				<path
					fill-rule="evenodd"
					clip-rule="evenodd"
					d="M18.056 26.1c-4.69 0-8.156-3.616-8.156-8.027v-.058c0-4.41 3.466-8.115 8.216-8.115 2.48 0 4.182.794 5.527 1.94.239.206.567.647.567 1.235 0 .912-.747 1.617-1.673 1.617-.448 0-.836-.176-1.075-.352-.926-.765-1.942-1.323-3.376-1.323-2.63 0-4.571 2.234-4.571 4.939v.059c0 2.763 1.942 4.968 4.72 4.968 1.434 0 2.54-.558 3.526-1.381.209-.177.567-.383.986-.383.866 0 1.553.706 1.553 1.559 0 .47-.18.852-.508 1.117-1.404 1.323-3.107 2.205-5.736 2.205z"
					fill="#fff"
				></path>
			</svg>
			<div class="sc-LzMEC gKVwXG" style="opacity: 0;">
				<svg viewBox="0 0 36 36" fill="none">
					<rect width="36" height="36" rx="4" fill="#665DC3"></rect>
					<rect
						x="8"
						y="10"
						width="20"
						height="2.824"
						rx="1.412"
						fill="#fff"
					></rect>
					<rect
						x="8"
						y="16.588"
						width="20"
						height="2.824"
						rx="1.412"
						fill="#fff"
					></rect>
					<rect
						x="8"
						y="23.177"
						width="20"
						height="2.824"
						rx="1.412"
						fill="#fff"
					></rect>
				</svg></div
		></span>
		<v-tab
			style="font-size:10px; min-width:60px;max-width:60px;height:60px;"
			class="pa-0"
			v-for="(tab, i) in tabs"
			:key="`tab_${i}`"
			@click="$emit('change', i)"
		>
			<span v-html="tab.title" />
			<v-icon class="ma-0">
				{{ tab.icon }}
			</v-icon>
		</v-tab>
	</v-tabs>
</template>

<script>
export default {
	name: 'EditNavigation',
	props: ['index'],
	data() {
		return {
			tabs: [
				{
					title: 'Add <br /> Media',
					icon: 'mdi-plus',
				},
				{
					title: 'Media',
					icon: 'mdi-file-outline',
				},
				{
					title: 'Caption',
					icon: 'mdi-format-text',
				},
				{
					title: 'Background',
					icon: 'mdi-vector-arrange-below',
				},
			],
		};
	},
};
</script>

<style>
.v-tab {
	text-transform: none;
	letter-spacing: normal;
	font-weight: 600;
}
.v-ripple__container {
	display: none !important;
}
</style>
