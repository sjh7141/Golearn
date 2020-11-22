<template>
	<div style="width:100%; height:100%;">
		<v-layout wrap>
			<div
				v-for="(item, i) in images"
				:key="`media-list_${i}`"
				class="mr-4 mb-4"
				style="width:133.3px; max-width:133.3px;"
			>
				<v-hover v-slot="{ hover }">
					<v-img
						:src="item.src"
						style="max-width:133.3px; max-height:75px; width:133.3px; height:75px;display:flex; border-radius:3px; position:relative;"
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
					</v-img>
				</v-hover>
				<v-list-item-subtitle
					dark
					style="color:#80818B; font-size:12px;"
				>
					{{ item.text }}
				</v-list-item-subtitle>
			</div>
			<div class="mr-4 mb-4" style="width:133.3px; max-width:133.3px;">
				<v-hover v-slot="{ hover }">
					<div
						style="max-width:133.3px; max-height:75px; background-color:black; width:133.3px; height:75px;display:flex; border-radius:3px; position:relative;"
						:style="{ 'background-color': color.color }"
					>
						<v-btn
							:ripple="false"
							:dark="color.text != 'white'"
							icon
							absolute
							right
							bottom
							v-show="hover"
							style="right:0px; bottom:0px;"
							@click="addPlayer(color)"
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
					{{ color.text }}
				</v-list-item-subtitle>
			</div>
		</v-layout>
	</div>
</template>

<script src="" />
<script>
import EventBus from '@/util/EventBus.js';
export default {
	name: 'EditBackground',
	watch: {},
	mounted() {
		for (let image of this.images) {
			let xhr = new XMLHttpRequest();
			xhr.open('GET', image.src, true);
			xhr.responseType = 'blob';
			xhr.onload = function(e) {
				var reader = new FileReader();
				reader.onload = function(event) {
					image.blob = event.target.result;
				};
				reader.readAsDataURL(this.response);
			};
			xhr.send();
		}
	},
	data() {
		return {
			images: [
				{
					name: 'leaf',
					duration: 10,
					type: 'image',
					text: 'leaf',
					src: require('@/assets/sample_image1.png'),
					fadeIn: 0,
					fadeOut: 0,
					blob: '',
				},
				{
					name: 'flowerpot',
					duration: 10,
					type: 'image',
					text: 'flowerpot',
					src: require('@/assets/sample_image2.jpg'),
					fadeIn: 0,
					fadeOut: 0,
					blob: '',
				},
				{
					name: 'ocean',
					duration: 10,
					type: 'image',
					text: 'ocean',
					src: require('@/assets/sample_image3.jpg'),
					fadeIn: 0,
					fadeOut: 0,
					blob: '',
				},
				{
					name: 'rose',
					duration: 10,
					type: 'image',
					text: 'rose',
					src: require('@/assets/sample_image4.jpg'),
					fadeIn: 0,
					fadeOut: 0,
					blob: '',
				},
				{
					name: 'lemon',
					duration: 10,
					type: 'image',
					text: 'lemon',
					src: require('@/assets/sample_image5.jpg'),
					fadeIn: 0,
					fadeOut: 0,
					blob: '',
				},
				{
					name: 'snow',
					duration: 10,
					type: 'image',
					text: 'snow',
					src: require('@/assets/sample_image6.jpg'),
					fadeIn: 0,
					fadeOut: 0,
					blob: '',
				},
				{
					name: 'coffee',
					duration: 10,
					type: 'image',
					text: 'coffee',
					src: require('@/assets/sample_image7.png'),
					fadeIn: 0,
					fadeOut: 0,
					blob: '',
				},
			],

			color: {
				name: 'color',
				duration: 10,
				type: 'background',
				color: '#FFFFFFFF',
				text: 'color',
				src: '',
				fadeIn: 0,
				fadeOut: 0,
			},
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
