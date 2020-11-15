<template>
	<div style="width:100%; height:100%;">
		<v-layout wrap>
			<div class="mr-4 mb-4" style="width:133.3px; max-width:133.3px;">
				<v-hover v-slot="{ hover }">
					<div
						style="max-width:133.3px; max-height:75px; background-color:black; width:133.3px; height:75px;display:flex; border-radius:3px; position:relative;"
					>
						<span style="color:#FFFFFF; margin:auto;">
							Sample
						</span>
						<v-btn
							dark
							icon
							absolute
							right
							bottom
							v-show="hover"
							style="right:0px; bottom:0px;"
							@click="addPlayer"
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
					Caption
				</v-list-item-subtitle>
			</div>
		</v-layout>
	</div>
</template>

<script>
import EventBus from '@/util/EventBus.js';
export default {
	name: 'EditCaption',
	methods: {
		addPlayer() {
			EventBus.$emit('addPlayer', {
				name: 'Sample caption',
				duration: 10,
				type: 'caption',
				src: '',
				size: 24,
				position: 8,
				color: '#FFFFFF',
				fadeIn: 0,
				fadeOut: 0,
			});
		},
	},
};
</script>

<style></style>
