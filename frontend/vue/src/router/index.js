import Vue from 'vue';
import VueRouter from 'vue-router';

import Index from '@/views/Index.vue';
import PlayChannel from '@/views/channel/play.vue';
import Login from '@/views/Login.vue';
import Course from '@/views/course/index.vue';
import Request from '@/views/course/Request.vue';
import RequestDetail from '@/views/course/RequestDetail.vue';
import EditCourse from '@/views/course/Edit.vue';
import EditLoadMap from '@/views/loadmap/Edit.vue';
import Community from '@/views/community/Community.vue';
import Post from '@/views/community/Post.vue';

import EditVideo from '@/views/channel/EditVideo.vue';

import Channel from '@/views/channel/Channel.vue';
import Mypage from '@/views/Mypage.vue';
import VideoUpload from '@/views/video/Upload.vue';
import CourseList from '@/views/course/CourseList.vue';
import LoadmapList from '@/views/loadmap/LoadmapList.vue';
import Loadmap from '@/views/loadmap/Index.vue';
import VideoList from '@/views/video/VideoList.vue';
Vue.use(VueRouter);

const routes = [
	{
		path: '/',
		name: 'Index',
		component: Index,
	},
	{
		path: '/channel/play/:no',
		name: 'PlayChannel',
		component: PlayChannel,
	},
	{
		path: '/video/edit',
		name: 'EditVideo',
		component: EditVideo,
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
		path: '/course/:no/:id',
		name: 'Course',
		component: Course,
	},
	{
		path: '/course',
		name: 'CourseList',
		component: CourseList,
	},
	{
		path: '/roadmap',
		name: 'LoadmapList',
		component: LoadmapList,
	},
	{
		path: '/roadmap/:ldm_no',
		name: 'LoadmapDetail',
		component: Loadmap,
	},
	{
		path: '/video',
		name: 'VideoList',
		component: VideoList,
	},
	{
		path: '/request',
		name: 'Request',
		component: Request,
	},
	{
		path: '/request/:id',
		name: 'RequestDetail',
		component: RequestDetail,
	},
	{
		path: '/roadmap/management/:id',
		name: 'EditLoadMap',
		component: EditLoadMap,
	},
	{
		path: '/channel/:id',
		name: 'Channel',
		component: Channel,
	},
	{
		path: '/mypage',
		name: 'Mypage',
		component: Mypage,
	},
	{
		path: '/video/upload',
		name: 'VideoUpload',
		component: VideoUpload,
	},
	{
		path: '/post/:type/:id',
		component: Post,
	},
	{
		path: '/community/:type/:page',
		name: 'Community',
		component: Community,
	},
];

const router = new VueRouter({
	mode: 'history',
	// base: process.env.BASE_URL,
	routes,
});

export default router;
