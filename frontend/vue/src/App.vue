<template>
	<v-app>
		<div id="app">
			<Header ref="header" :totalNotice="totalNotice" v-if="showHeader" />
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
import { mapActions, mapGetters } from 'vuex';

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
			totalNotice: 0,
		};
	},

	computed: {
		...mapGetters({
			sb: 'snackbar',
			SBMessage: 'SBMessage',
			isLogin: 'isLogin',
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
			if (
				this.$route.path.indexOf('/login') > -1 ||
				this.$route.path.indexOf('/404') > -1
			) {
				render = false;
				this.setSpace(0);
			} else {
				this.setSpace(64);
			}
			return render;
		},
		showFooter() {
			let render = true;
			if (
				this.$route.path.indexOf('/login') > -1 ||
				this.$route.path.indexOf('/404') > -1
			) {
				render = false;
			}
			return render;
		},
	},

	mounted() {
		this.space = this.$refs.header.$el.clientHeight;
		if (this.snackbar) {
			var self = this;
			setTimeout(function() {
				self.$store.commit('setSBMessage', '');
				self.$store.commit('setSnackbar', false);
			}, 3000);
		}
	},

	methods: {
		...mapActions(['getNotiCount']),
		setSpace(value) {
			// this.space = this.$refs.header.$el.clientHeight;
			this.space = value;
		},
	},
	watch: {
		$route(val, prev) {
			this.$store.commit('setPrevPage', prev.fullPath);
			if (this.isLogin) {
				this.$refs.header.getTotalNotice();
			}
			window.scrollTo(0, 0);
		},
	},
};
</script>

<style src="@/assets/css/style.css" />
