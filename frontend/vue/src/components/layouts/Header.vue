<template>
	<v-layout style="position:fixed; top:0;left:0;z-index:100; width:100%;">
		<v-app-bar
			flat
			tile
			style="background-color:white; padding:0; height:100%"
			class="px-10"
		>
			<v-card flat tile style="background-color:transparent" class="mr-6">
				<router-link to="/">
					<v-img src="@/assets/logo.png" max-width="40" contain />
				</router-link>
			</v-card>
			<template v-for="(menu, idx) in menus">
				<div
					:key="`menu_${idx}`"
					class="nav px-5"
					@click="$router.push(menu.link)"
				>
					{{ menu.title }}
				</div>
			</template>
			<v-spacer />
			<v-card
				dense
				flat
				height="100%"
				width="350"
				style="padding: 5px; margin-top:5px;"
			>
				<v-text-field
					class="basic"
					v-model="keyword"
					flat
					dense
					solo
					outlined
					@focus="isFocus = true"
					@blur="isFocus = false"
					@keydown.enter="keywordSearch()"
				>
					<v-icon
						:class="{ basic: isFocus }"
						slot="append"
						@click="keywordSearch()"
					>
						mdi-magnify
					</v-icon>
				</v-text-field>
			</v-card>
			<v-spacer />
			<div>
				<v-btn
					v-if="isLogin === 0"
					light
					depressed
					outlined
					@click="goToLogin"
					class="bold"
				>
					로그인
				</v-btn>
				<template v-else>
					<v-btn icon large class="mr-5" @click="video = true">
						<v-avatar>
							<v-icon large>
								mdi-video-plus
							</v-icon>
						</v-avatar>
					</v-btn>

					<v-menu bottom min-width="200px" rounded offset-y>
						<template v-slot:activator="{ on }">
							<v-btn icon large v-on="on" class="mr-5">
								<v-avatar>
									<v-icon>
										mdi-bell
									</v-icon>
								</v-avatar>
							</v-btn>
						</template>
						<v-card>
							<v-list-item-content class="justify-center">
								<div class="mx-auto"></div>
							</v-list-item-content>
						</v-card>
					</v-menu>
					<v-menu bottom min-width="200px" rounded offset-y>
						<template v-slot:activator="{ on }">
							<v-btn icon large v-on="on">
								<v-avatar>
									<img
										:src="
											user.profile
												? user.profile
												: '@/assets/default_profile.png'
										"
										alt="프로필"
									/>
									<!-- <span class="white--text headline">{{
									user.initials
								}}</span> -->
								</v-avatar>
							</v-btn>
						</template>
						<v-card>
							<v-list-item-content class="justify-center pb-0">
								<div class="mx-auto">
									<v-row class="mx-4">
										<v-avatar color="brown">
											<img
												:src="
													user.profile
														? user.profile
														: '@/assets/default_profile.png'
												"
												alt="프로필"
											/>
										</v-avatar>
										<div>
											<v-col cols="12" class="py-0">
												<h3>{{ user.nickname }}</h3>
											</v-col>
											<v-col cols="12" class="py-0">
												<p class="caption mt-1">
													{{ user.email }}
												</p>
											</v-col>
										</div>
									</v-row>
									<v-divider class="mt-3"></v-divider>
									<v-btn
										depressed
										text
										@click="goToMypage"
										class="bold menu-start"
									>
										<v-icon class="pr-3" color="#737373">
											mdi-badge-account-horizontal
										</v-icon>
										회원정보
									</v-btn>
									<v-divider></v-divider>
									<v-btn
										depressed
										text
										@click="goToChannel"
										class="bold menu-start"
									>
										<v-icon class="pr-3" color="#737373">
											mdi-account-box
										</v-icon>
										내 채널
									</v-btn>
									<v-divider></v-divider>
									<v-btn
										depressed
										text
										@click="logout"
										class="bold menu-start"
									>
										<v-icon class="pr-3" color="#737373">
											mdi-logout
										</v-icon>
										로그아웃
									</v-btn>
								</div>
							</v-list-item-content>
						</v-card>
					</v-menu>
				</template>
			</div>
		</v-app-bar>
		<v-dialog v-model="video" max-width="700">
			<v-card>
				<v-card-text class="pt-5">
					<v-row justify="center">
						<v-img
							src="@/assets/logo3.png"
							max-width="100"
							contain
						/>
					</v-row>
					<v-row justify="center">
						<h1 style="font-size:40px;">
							<span style="color:#633cb6;font-weight:800;">
								동영상
							</span>
							관리
						</h1>
					</v-row>
					<v-row>
						<v-col
							v-for="(tap, idx) in videoModal"
							:key="idx"
							:cols="6"
						>
							<v-row>
								<v-card
									class="select-card"
									tile
									hover
									style="box-shadow:none; width:100%;"
									:ripple="false"
									@click="goToTap(tap.path)"
								>
									<v-row justify="center">
										<div class="card-img">
											<v-icon x-large>
												{{ tap.icon }}
											</v-icon>
										</div>
									</v-row>
									<v-row justify="center">
										<h3 v-text="tap.title" />
									</v-row>
									<v-row justify="start">
										<div
											style="font-size:14px; margin-top:10px;"
											v-html="tap.contents"
										/>
									</v-row>
									<v-row justify="center">
										<v-btn
											color="#633cb6"
											rounded
											large
											depressed
											dark
											style="margin-top:30px; width:60%;"
										>
											<span style="font-size:16px"
												><b>선택하기</b>
											</span>
										</v-btn>
									</v-row>
								</v-card>
							</v-row>
						</v-col>
					</v-row>
				</v-card-text>
			</v-card>
		</v-dialog>
	</v-layout>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';

export default {
	name: 'Header',

	data() {
		return {
			keyword: '',
			isFocus: false,
			menus: [
				{
					title: '로드맵',
					link: '/roadmap',
				},
				{
					title: '코스',
					link: '/course',
				},
				{
					title: '커뮤니티',
					link: '/course',
				},
			],
			video: false,
			videoModal: [
				{
					path: 'upload',
					icon: 'mdi-file-upload',
					title: '동영상 업로드',
					contents:
						'새로운 영상을 업로드 하고 고런고런의 컨텐츠에 사용할 수 있습니다.',
				},
				{
					path: 'edit',
					icon: 'mdi-content-cut',
					title: '동영상 편집',
					contents:
						'고런고런의 편집기를 사용하여 영상을 직접 편집할 수 있습니다.',
				},
			],
			notiCount: 0,
			notifications: [],
		};
	},

	methods: {
		...mapActions(['getNotiCount', 'getNotification']),
		keywordSearch() {
			this.$router.push(`/video?search=${this.keyword}`);
		},
		goToLogin() {
			this.$router.push('/login');
		},
		goToMypage() {
			this.$router.push('/mypage');
		},
		goToChannel() {
			this.$router.push(`/channel/${this.user.no}`);
		},
		logout() {
			this.$store.commit('setIsLogin', 0);
			this.$store.commit('setUser', null);
			this.$store.commit('setToken', null);
			this.$router.push('/');
		},
		goToTap(path) {
			this.video = false;
			this.$router.push(`/video/${path}`);
		},
		getNoti() {
			this.getNotification().then(res => {
				this.notifications = res.data;
			});
		},
	},
	computed: {
		...mapGetters(['isLogin', 'user']),
	},
	mounted() {
		this.getNotiCount().then(res => {
			this.notiCount = res.data.num_of_noti;
		});
	},
};
</script>

<style scoped>
@import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);
.nav {
	font-family: 'BMJUA', sans-serif !important;
	font-size: 18px;
	font-weight: 600;
	color: #4a4a4a;
}
.nav:hover {
	color: rgb(60, 65, 223);
	cursor: pointer;
}

.append-icon:focus {
	color: rgb(60, 65, 223) !important;
}
.menu-start {
	justify-content: start !important;
	width: 100%;
	height: 60px !important;
}
.select-card:hover {
	box-shadow: 0 4px 4px 0 rgba(0, 0, 0, 0.2) !important;
}
.select-signup {
	background-color: #f9f9f9;
	height: 100vh;
	width: 100%;
	margin: 0;
}

.select-card {
	margin: 0px 20px;
	width: 282px;
	padding: 36px;
}

.card-img {
	margin: 24px 0 42px 0;
	vertical-align: top;
	text-align: center;
	font-size: 30px;
}

@font-face {
	font-family: 'BMJUA';
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/BMJUA.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}
</style>
