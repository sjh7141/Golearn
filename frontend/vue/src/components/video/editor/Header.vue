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
				<v-slide-y-reverse-transition hide-on-leave>
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
						v-show="showText"
					>
						<v-icon left color="#C0C0CD">
							mdi-format-text
						</v-icon>
						<span style="color:#C0C0CD;font-size:14px;">
							자막
						</span>
					</v-btn>
				</v-slide-y-reverse-transition>
			</template>

			<v-card color="#2C2C38" float class="pa-5" v-if="showText">
				자막
				<v-textarea
					v-model="selectedItem.name"
					class="mt-2"
					outlined
					hide-details
					style="z-index:9999"
					autofocus
					auto-grow
					:rows="3"
					@change="isChange = true"
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
				<v-slide-y-reverse-transition hide-on-leave>
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
						v-show="showTransform"
					>
						<v-icon left color="#C0C0CD">
							mdi-picture-in-picture-bottom-right mdi-flip-h
						</v-icon>
						<span style="color:#C0C0CD;font-size:14px;">
							크기
						</span>
					</v-btn>
				</v-slide-y-reverse-transition>
			</template>
			<v-card color="#2C2C38" float class="pa-5" v-if="showTransform">
				위치
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
							:color="
								selectedItem.position == i
									? '#9382D7'
									: '#666673'
							"
							style="margin:1px;"
							:key="`position_${i}`"
							@click.stop="
								selectedItem.position = i;
								isChange = true;
							"
							:style="{
								'background-color':
									selectedItem.position == i
										? '#665DC3'
										: '#1C1C26',
							}"
						/>
						<br v-show="i % 3 == 0" :key="`br_${i}`" />
					</template>
				</div>

				크기
				<div>
					<v-slider
						v-model="selectedItem.size"
						loader-height="2"
						color="#9382D7"
						:ripple="false"
						hide-details
						@change="isChange = true"
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
				<v-slide-y-reverse-transition hide-on-leave>
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
						v-show="showColor"
					>
						<v-icon left color="#C0C0CD">
							mdi-palette-outline
						</v-icon>
						<span style="color:#C0C0CD;font-size:14px;">
							색상
						</span>
					</v-btn>
				</v-slide-y-reverse-transition>
			</template>
			<v-card color="#2C2C38" float class="pa-5" v-if="showColor">
				색상
				<v-color-picker
					v-model="selectedItem.color"
					class="ma-2"
					hide-mode-switch
					mode="hexa"
					flat
					style="background-color:transparent"
					@update:color="isChange = true"
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
				<v-slide-y-reverse-transition hide-on-leave>
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
						v-show="showAudio"
					>
						<v-icon left color="#C0C0CD">
							mdi-volume-high
						</v-icon>
						<span style="color:#C0C0CD;font-size:14px;">
							볼륨
						</span>
					</v-btn>
				</v-slide-y-reverse-transition>
			</template>
			<v-card color="#2C2C38" float class="pa-5" v-if="showAudio">
				<v-layout>
					볼륨 <v-spacer /> {{ selectedItem.volume }}%
				</v-layout>
				<div>
					<v-slider
						class="ma-0"
						v-model="selectedItem.volume"
						loader-height="2"
						color="#9382D7"
						:ripple="false"
						hide-details
						@change="isChange = true"
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
				<v-slide-y-reverse-transition hide-on-leave>
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
						v-show="showFade"
					>
						<v-icon left color="#C0C0CD">
							mdi-box-shadow
						</v-icon>
						<span style="color:#C0C0CD;font-size:14px;">
							효과
						</span>
					</v-btn>
				</v-slide-y-reverse-transition>
			</template>
			<v-card
				color="#2C2C38"
				float
				class="pa-5"
				v-if="showFade"
				style="text-transform: none"
			>
				<v-layout>
					Fade In <v-spacer />
					{{ parseFloat(selectedItem.fadeIn / 10).toFixed(1) }}
					<span>s</span>
				</v-layout>
				<div>
					<v-slider
						class="ma-0"
						v-model="selectedItem.fadeIn"
						loader-height="2"
						color="#9382D7"
						:ripple="false"
						hide-details
						:min="0"
						:max="20"
						@change="isChange = true"
					>
					</v-slider>
				</div>
				<v-layout>
					Fade Out <v-spacer />
					{{ parseFloat(selectedItem.fadeOut / 10).toFixed(1) }}
					<span>s</span>
				</v-layout>
				<div>
					<v-slider
						class="ma-0"
						v-model="selectedItem.fadeOut"
						loader-height="2"
						color="#9382D7"
						:ripple="false"
						hide-details
						:min="0"
						:max="20"
						@change="isChange = true"
					>
					</v-slider>
				</div>
			</v-card>
		</v-tooltip>
		<v-spacer />
		<v-btn color="#665DC3" @click="exportVideo">
			영상 업로드
		</v-btn>
	</v-app-bar>
</template>

<script>
import EventBus from '@/util/EventBus.js';
export default {
	name: 'EditHeader',

	data() {
		return {
			properties: 0,

			showText: false,
			showTransform: false,
			showColor: false,
			showAudio: false,
			showFade: false,
		};
	},
	computed: {
		selectedItem: {
			get() {
				return this.$store.getters.selectedItem;
			},
			set(val) {
				this.$store.commit('setSelectedItem', val);
			},
		},
		isChange: {
			get() {
				return this.$store.getters.isChange;
			},
			set(val) {
				this.$store.commit('setIsChange', val);
			},
		},
	},
	watch: {
		selectedItem() {
			this.properties = 0;
			this.showText =
				this.selectedItem && this.selectedItem.type == 'caption';
			this.showTransform =
				this.selectedItem && this.selectedItem.type == 'caption';
			this.showColor =
				this.selectedItem &&
				(this.selectedItem.type == 'caption' ||
					this.selectedItem.type == 'background');
			this.showAudio =
				this.selectedItem &&
				(this.selectedItem.type == 'video' ||
					this.selectedItem.type == 'audio');
			this.showFade =
				this.selectedItem && this.selectedItem.type != 'audio';
		},
	},
	mounted() {
		EventBus.$on('focusout', () => {
			this.properties = 0;
		});
	},
	methods: {
		exportVideo() {
			this.$emit('exportVideo');
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
