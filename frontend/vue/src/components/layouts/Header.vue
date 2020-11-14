<template>
	<v-layout style="position:fixed; top:0;left:0;z-index:100; width:100%;">
		<v-app-bar
			flat
			tile
			style="background-color:#fff; padding:0; height:100%"
		>
			<v-card flat tile style="background-color:transparent">
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
				>
					로그인
				</v-btn>
				<v-menu v-else bottom min-width="200px" rounded offset-y>
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
						<v-list-item-content class="justify-center">
							<div class="mx-auto text-center">
								<v-row class="mx-2">
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
										<v-row justify="start">
											<v-col cols="12" class="pa-0">
												<h3>{{ user.nickname }}</h3>
											</v-col>
											<v-col cols="12" class="pa-0">
												<p class="caption mt-1">
													{{ user.email }}
												</p>
											</v-col>
										</v-row>
									</div>
								</v-row>
								<v-divider class="my-3"></v-divider>
								<v-btn
									depressed
									rounded
									text
									@click="goToMypage"
									class="bold"
								>
									회원정보
								</v-btn>
								<v-divider class="my-3"></v-divider>
								<v-btn
									depressed
									rounded
									text
									@click="goToChannel"
									class="bold"
								>
									내 채널
								</v-btn>
								<v-divider class="my-3"></v-divider>
								<v-btn
									depressed
									rounded
									text
									@click="logout"
									class="bold"
								>
									로그아웃
								</v-btn>
							</div>
						</v-list-item-content>
					</v-card>
				</v-menu>
			</div>
		</v-app-bar>
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
@import url(https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/BMJUA.woff);
.nav {
	font-family: 'BM JUA', sans-serif !important;
	font-size: 18px;
}
.nav:hover {
	color: rgb(60, 65, 223);
	cursor: pointer;
}

.append-icon:focus {
	color: rgb(60, 65, 223) !important;
}
</style>
