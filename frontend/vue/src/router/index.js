import Vue from 'vue';
import VueRouter from 'vue-router';

import Index from '@/views/Index.vue';
import PlayVideo from '@/views/video/play.vue';
import Login from '@/views/Login.vue';

Vue.use(VueRouter);

const routes = [
	{
		path: '/',
		name: 'Index',
		component: Index,
	},
	{
		path: '/video/play',
		name: 'PlayVideo',
		component: PlayVideo,
	},
	{
		path: '/login',
		name: 'Login',
		component: Login,
	},
];

const router = new VueRouter({
	mode: 'history',
	// base: process.env.BASE_URL,
	routes,
});

export default router;
