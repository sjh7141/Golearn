<template>
	<div style="width:100%; height:100%;">
		<v-layout wrap>
			<div
				v-for="(item, i) in fonts"
				:key="`font_${i}`"
				class="mr-4 mb-4"
				style="width:133.3px; max-width:133.3px;"
			>
				<v-hover v-slot="{ hover }">
					<div
						style="max-width:133.3px; max-height:75px; background-color:black; width:133.3px; height:75px;display:flex; border-radius:3px; position:relative;"
					>
						<span
							style="color:#FFFFFF; margin:auto;"
							:style="{ 'font-family': item.family }"
						>
							{{ item.name }}
						</span>
						<v-btn
							dark
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
					{{ item.name }}
				</v-list-item-subtitle>
			</div>
		</v-layout>
	</div>
</template>

<script>
import EventBus from '@/util/EventBus.js';
export default {
	name: 'EditCaption',
	data() {
		return {
			fonts: [
				{
					name: '기본',
					family: 'BMJUA',
				},
				{
					name: '굴림체',
					family: '굴림체',
				},
				{
					name: '궁서체',
					family: '궁서체',
				},
				{
					name: '돋움체',
					family: '돋움체',
				},
				{
					name: '바탕체',
					family: '바탕체',
				},
				{
					name: '휴먼엽서체',
					family: '휴먼엽서체',
				},
			],
		};
	},
	methods: {
		addPlayer(item) {
			let copiedItem = {
				name: item.name,
				family: item.family,
				duration: 10,
				type: 'caption',
				src: '',
				size: 24,
				position: 8,
				color: '#FFFFFF',
				fadeIn: 0,
				fadeOut: 0,
				startTime: 0,
			};

			EventBus.$emit('addPlayer', copiedItem);
		},
	},
};
</script>

<style></style>
