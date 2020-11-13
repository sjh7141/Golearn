<template>
	<v-app>
		<div id="app">
			<Header ref="header" v-if="showHeader" />
			<Navigation v-if="showHeader" />
			<div :style="{ 'margin-top': space + 'px' }" />
			<Content class="content-component" />
			<Footer v-if="showFooter" />
		</div>
	</v-app>
</template>

<script>
import Header from '@/components/layouts/Header.vue';
import Navigation from '@/components/layouts/Navigation.vue';
import Content from '@/components/layouts/Content.vue';
import Footer from '@/components/layouts/Footer.vue';

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
