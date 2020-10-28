<template>
	<v-app>
		<div id="app">
			<Header v-if="showHeader" />
			<Navigation v-if="showHeader" />
			<Content />
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
		return {};
	},

	computed: {
		showHeader() {
			let render = true;
			if (this.$route.path.indexOf('/login') > -1) {
				render = false;
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
};
</script>

<style src="@/assets/css/style.css" />
