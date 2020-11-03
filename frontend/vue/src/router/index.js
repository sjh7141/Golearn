import Vue from 'vue';
import VueRouter from 'vue-router';

import Index from '@/views/Index.vue';
import PlayChannel from '@/views/channel/play.vue';
import Login from '@/views/Login.vue';
import EditCourse from '@/views/course/Edit.vue';
import PlayCource from '@/views/course/play.vue';
import EditLoadMap from '@/views/loadmap/Edit.vue';

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
		path: '/course/management/:id',
		name: 'EditCourse',
		component: EditCourse,
	},
	{
		path: '/course/play',
		name: 'PlayCourse',
		component: PlayCource,
	},
	{
		path: '/loadmap/management/:id',
		name: 'EditLoadMap',
		component: EditLoadMap,
	},
];

const router = new VueRouter({
	mode: 'history',
	// base: process.env.BASE_URL,
	routes,
});

export default router;
