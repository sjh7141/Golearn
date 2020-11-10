<template>
	<v-app-bar
		class="ma-0 mt-3"
		color="transparent"
		flat
		dark
		id="edit-header"
		@click="properties = 0"
	>
		<v-spacer />

		<v-tooltip
			bottom
			:open-on-hover="false"
			:value="properties == 1"
			class="pa-0 ma-0"
			eager
			fixed
		>
			<template v-slot:activator="{}">
				<v-btn
					class="ml-2"
					depressed
					:ripple="false"
					@click.stop="properties = properties != 1 ? 1 : 0"
					outlined
					:color="properties == 1 ? '#9382D7' : '#2C2C38'"
					:style="{
						'background-color':
							properties == 1 ? '#2B2947' : '#2C2C38',
					}"
				>
					<v-icon left color="#C0C0CD">
						mdi-format-text
					</v-icon>
					<span style="color:#C0C0CD;font-size:14px;">
						Text
					</span>
				</v-btn>
			</template>
			<v-card color="#2C2C38" float class="pa-5">
				Text
				<v-textarea
					v-model="media.text"
					class="mt-2"
					outlined
					hide-details
					style="z-index:9999"
					autofocus
					auto-grow
					:rows="3"
				>
				</v-textarea>
			</v-card>
		</v-tooltip>

		<v-tooltip
			bottom
			:open-on-hover="false"
			:value="properties == 2"
			class="pa-0 ma-0"
			eager
			fixed
		>
			<template v-slot:activator="{}">
				<v-btn
					class="ml-2"
					depressed
					:ripple="false"
					@click.stop="properties = properties != 2 ? 2 : 0"
					outlined
					:color="properties == 2 ? '#9382D7' : '#2C2C38'"
					:style="{
						'background-color':
							properties == 2 ? '#2B2947' : '#2C2C38',
					}"
				>
					<v-icon left color="#C0C0CD">
						mdi-picture-in-picture-bottom-right mdi-flip-h
					</v-icon>
					<span style="color:#C0C0CD;font-size:14px;">
						Transform
					</span>
				</v-btn>
			</template>
			<v-card color="#2C2C38" float class="pa-5">
				Position
				<div class="mt-2 mb-4">
					<template v-for="i in 9">
						<v-btn
							min-width="20"
							min-height="20"
							height="20"
							class="pa-0"
							outlined
							depressed
							tile
							:color="media.position == i ? '#9382D7' : '#666673'"
							style="margin:1px;"
							:key="`position_${i}`"
							@click.stop="media.position = i"
							:style="{
								'background-color':
									media.position == i ? '#665DC3' : '#1C1C26',
							}"
						/>
						<br v-show="i % 3 == 0" :key="`br_${i}`" />
					</template>
				</div>

				Size
				<div>
					<v-slider
						v-model="media.size"
						loader-height="2"
						color="#9382D7"
						:ripple="false"
						hide-details
					>
					</v-slider>
				</div>
			</v-card>
		</v-tooltip>

		<v-tooltip
			bottom
			:open-on-hover="false"
			:value="properties == 3"
			class="pa-0 ma-0"
			eager
			fixed
		>
			<template v-slot:activator="{}">
				<v-btn
					class="ml-2"
					depressed
					:ripple="false"
					@click.stop="properties = properties != 3 ? 3 : 0"
					outlined
					:color="properties == 3 ? '#9382D7' : '#2C2C38'"
					:style="{
						'background-color':
							properties == 3 ? '#2B2947' : '#2C2C38',
					}"
				>
					<v-icon left color="#C0C0CD">
						mdi-palette-outline
					</v-icon>
					<span style="color:#C0C0CD;font-size:14px;">
						Color
					</span>
				</v-btn>
			</template>
			<v-card color="#2C2C38" float class="pa-5">
				Color
				<v-color-picker
					v-model="media.color"
					class="ma-2"
					hide-mode-switch
					mode="hexa"
					flat
					style="background-color:transparent"
				></v-color-picker>
			</v-card>
		</v-tooltip>

		<v-tooltip
			bottom
			:open-on-hover="false"
			:value="properties == 4"
			class="pa-0 ma-0"
			eager
			fixed
		>
			<template v-slot:activator="{}">
				<v-btn
					class="ml-2"
					depressed
					:ripple="false"
					@click.stop="properties = properties != 4 ? 4 : 0"
					outlined
					:color="properties == 4 ? '#9382D7' : '#2C2C38'"
					:style="{
						'background-color':
							properties == 4 ? '#2B2947' : '#2C2C38',
					}"
				>
					<v-icon left color="#C0C0CD">
						mdi-volume-high
					</v-icon>
					<span style="color:#C0C0CD;font-size:14px;">
						Audio
					</span>
				</v-btn>
			</template>
			<v-card color="#2C2C38" float class="pa-5">
				<v-layout> Volume <v-spacer /> {{ media.volume }}% </v-layout>
				<div>
					<v-slider
						class="ma-0"
						v-model="media.volume"
						loader-height="2"
						color="#9382D7"
						:ripple="false"
						hide-details
					>
					</v-slider>
				</div>
			</v-card>
		</v-tooltip>
		<v-tooltip
			bottom
			:open-on-hover="false"
			:value="properties == 5"
			class="pa-0 ma-0"
			eager
			fixed
		>
			<template v-slot:activator="{}">
				<v-btn
					class="ml-2"
					depressed
					:ripple="false"
					@click.stop="properties = properties != 5 ? 5 : 0"
					outlined
					:color="properties == 5 ? '#9382D7' : '#2C2C38'"
					:style="{
						'background-color':
							properties == 5 ? '#2B2947' : '#2C2C38',
					}"
				>
					<v-icon left color="#C0C0CD">
						mdi-box-shadow
					</v-icon>
					<span style="color:#C0C0CD;font-size:14px;">
						Fade
					</span>
				</v-btn>
			</template>
			<v-card color="#2C2C38" float class="pa-5">
				<v-layout>
					Fade In <v-spacer />
					{{ parseFloat(media.fadeIn / 10).toFixed(1) }}
					<span style="text-transform: none">s</span>
				</v-layout>
				<div>
					<v-slider
						class="ma-0"
						v-model="media.fadeIn"
						loader-height="2"
						color="#9382D7"
						:ripple="false"
						hide-details
						:min="0"
						:max="20"
					>
					</v-slider>
				</div>
				<v-layout>
					Fade Out <v-spacer />
					{{ parseFloat(media.fadeOut / 10).toFixed(1) }}
					<span style="text-transform: none">s</span>
				</v-layout>
				<div>
					<v-slider
						class="ma-0"
						v-model="media.fadeOut"
						loader-height="2"
						color="#9382D7"
						:ripple="false"
						hide-details
						:min="0"
						:max="20"
					>
					</v-slider>
				</div>
			</v-card>
		</v-tooltip>
		<v-spacer />
		<v-btn rounded color="#665DC3" @click="exportVideo">
			<v-icon left>
				mdi-export
			</v-icon>
			Export
		</v-btn>
	</v-app-bar>
</template>

<script>
import EventBus from '@/util/EventBus.js';
export default {
	name: 'EditHeader',
	// props: ['media'],

	data() {
		return {
			media: {
				type: 'video',
				text: 'Sample Text',
				color: '#FFFFFFFF',
				position: 5,
				size: 50,
				volume: 100,
				fadeIn: 0,
				fadeOut: 0,
			},

			properties: 0,
		};
	},
	mounted() {
		EventBus.$on('focusout', () => {
			this.properties = 0;
		});
	},
	methods: {
		test(e) {
			console.log(e);
		},
		exportVideo() {
			EventBus.$emit('exportVideo');
		},
	},
};
</script>

<style scoped>
.v-tooltip__content {
	padding: 0 !important;
	width: 300px !important;
	padding: 20px;
	pointer-events: all !important;
	text-transform: uppercase !important;
}

#edit-header .v-tooltip__content[class*='enter-active'] {
	transition-duration: 0ms !important;
}

#edit-header .v-color-picker__controls {
	padding-bottom: 0px !important;
}

#edit-header .v-slider__track-container {
	height: 4px !important;
}
#edit-header .v-slider__track-fill,
#edit-header .v-slider__track-background {
	border-radius: 2px !important;
}

#edit-header .v-slider__thumb::before {
	display: none;
}
#edit-header .v-slider--horizontal {
	margin: 0px !important;
}
</style>
