<template>
	<v-app>
		<div id="app">
			<Header ref="header" v-if="showHeader" />
			<Navigation v-if="showHeader" />
			<div :style="{ 'margin-top': space + 'px' }" />
			<Content class="content-component" />
			<Footer v-if="showFooter" />
			<v-snackbar
				v-model="snackbar"
				:multi-line="true"
				:timeout="3000"
				color="#1e2224"
			>
				<span style="color:#ebebeb; font-family: 'BMJUA';">
					{{ SBMessage }}
				</span>
			</v-snackbar>
		</div>
	</v-app>
</template>

<script>
import Header from '@/components/layouts/Header.vue';
import Navigation from '@/components/layouts/Navigation.vue';
import Content from '@/components/layouts/Content.vue';
import Footer from '@/components/layouts/Footer.vue';
import { mapGetters } from 'vuex';

export default {
	name: 'App',

	components: {
		Header,
		Navigation,
		Content,
		Footer,
	},

	data() {
		return {
			space: 0,
		};
	},

	computed: {
		...mapGetters({
			sb: 'snackbar',
			SBMessage: 'SBMessage',
		}),
		snackbar: {
			get() {
				return this.sb;
			},
			set(flag) {
				return flag;
			},
		},
		showHeader() {
			let render = true;
			if (this.$route.path.indexOf('/login') > -1) {
				render = false;
				this.setSpace(0);
			} else {
				this.setSpace(64);
			}
			return render;
		},
		showFooter() {
			let render = true;
			if (this.$route.path.indexOf('/login') > -1) {
				render = false;
			}
			return render;
		},
	},

	mounted() {
		this.space = this.$refs.header.$el.clientHeight;
		console.log(this.snackbar);
		if (this.snackbar) {
			var self = this;
			setTimeout(function() {
				self.$store.commit('setSBMessage', '');
				self.$store.commit('setSnackbar', false);
			}, 3000);
		}
	},

	methods: {
		setSpace(value) {
			// this.space = this.$refs.header.$el.clientHeight;
			this.space = value;
		},
	},
};
</script>

<style src="@/assets/css/style.css" />
