<template>
	<div style="width:100%; height:100%;">
		<v-layout wrap>
			<div
				v-for="(item, i) in colorList"
				:key="`media-list_${i}`"
				class="mr-4 mb-4"
				style="width:133.3px; max-width:133.3px;"
			>
				<v-hover v-slot="{ hover }">
					<div
						style="max-width:133.3px; max-height:75px; background-color:black; width:133.3px; height:75px;display:flex; border-radius:3px; position:relative;"
						:style="{ 'background-color': item.color }"
					>
						<v-btn
							:ripple="false"
							:dark="item.text != 'white'"
							icon
							absolute
							right
							bottom
							v-show="hover"
							style="right:0px; bottom:0px;"
							@click="addPlayer(item)"
						>
							<v-icon>
								mdi-plus
							</v-icon>
						</v-btn>
					</div>
				</v-hover>
				<v-list-item-subtitle
					dark
					style="color:#80818B; font-size:12px;"
				>
					{{ item.text }}
				</v-list-item-subtitle>
			</div>
		</v-layout>
	</div>
</template>

<script>
import EventBus from '@/util/EventBus.js';
export default {
	name: 'EditBackground',
	watch: {},
	data() {
		return {
			colorList: [
				{
					name: 'background',
					duration: 10,
					type: 'background',
					color: '#FFFFFFFF',
					text: 'white',
					src: '',
				},
				{
					name: 'background',
					duration: 10,
					type: 'background',
					color: '#000000FF',
					text: 'black',
					src: '',
				},
				{
					name: 'background',
					duration: 10,
					type: 'background',
					color: '#FF0000FF',
					text: 'red',
					src: '',
				},
			],
		};
	},
	methods: {
		addPlayer(item) {
			let copiedItem = {};
			for (let i in item) copiedItem[i] = item[i];
			EventBus.$emit('addPlayer', copiedItem);
		},
	},
};
</script>

<style>
.v-icon::after {
	display: none !important;
}
.v-btn:hover::before {
	opacity: 0 !important;
}
</style>
