import Vue from 'vue';
import VueRouter from 'vue-router';

import Index from '@/views/Index.vue';
import PlayChannel from '@/views/channel/play.vue';
import Login from '@/views/Login.vue';
import Course from '@/views/course/index.vue';
import Request from '@/views/course/Request.vue';

Vue.use(VueRouter);

const routes = [
	{
		path: '/',
		name: 'Index',
		component: Index,
	},
	{
		path: '/channel/play',
		name: 'PlayChannel',
		component: PlayChannel,
	},
	{
		path: '/login',
		name: 'Login',
		component: Login,
	},
	{
		path: '/course/:id',
		name: 'Course',
		component: Course,
	},
	{
		path: '/request',
		name: 'Request',
		component: Request,
	},
];

const router = new VueRouter({
	mode: 'history',
	// base: process.env.BASE_URL,
	routes,
});

export default router;
