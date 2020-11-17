<template>
	<v-tabs
		:value="index"
		vertical
		background-color="#272731"
		slider-color="#9382d7"
		dark
		style="width:60px; height:100%;"
		:ripple="false"
	>
		<div class="mt-3">
			<v-img
				src="@/assets/edit_logo2.png"
				width="40"
				height="40"
				@click="$router.push('/')"
				style="cursor:pointer; border-radius:5px;"
				class="mx-auto mb-1"
			/>
		</div>
		<v-tab
			style="font-size:10px; min-width:60px;max-width:60px;height:60px;"
			class="pa-0"
			v-for="(tab, i) in tabs"
			:key="`tab_${i}`"
			@click="$emit('change', i)"
		>
			<div style="margin-top:auto; margin-bottom:auto;">
				<div class="mb-1">
					<v-icon class="ma-0">
						{{ tab.icon }}
					</v-icon>
				</div>

				<span v-html="tab.title" class="mt-10" />
			</div>
		</v-tab>
		<v-spacer />
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
					title: '추가',
					icon: 'mdi-plus',
				},
				{
					title: '미디어',
					icon: 'mdi-file-outline',
				},
				{
					title: '자막',
					icon: 'mdi-format-text',
				},
				{
					title: '배경',
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
