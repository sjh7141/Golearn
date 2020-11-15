<template>
	<v-layout style="position:fixed; top:0;left:0;z-index:100; width:100%;">
		<v-app-bar
			flat
			tile
			style="background-color:white; padding:0; height:100%"
		>
			<v-card
				flat
				tile
				style="background-color:transparent"
				class="ml-4 mr-2"
			>
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
				width="450"
				tile
				style="padding: 5px; margin-top:5px;"
			>
				<v-text-field
					class="basic"
					v-model="keyword"
					dense
					solo-inverted
					@focus="isFocus = true"
					@blur="isFocus = false"
					@keydown.enter="keywordSearch()"
					color="red"
					flat
					hide-details
					style="color:red;"
				>
					<div slot="append">
						<v-icon
							:class="{ basic: isFocus }"
							@click="keywordSearch()"
						>
							mdi-magnify
						</v-icon>
					</div>
				</v-text-field>
			</v-card>
			<v-spacer />
			<div style="width:300px; text-align:right;">
				<v-btn
					text
					v-if="isLogin === 0"
					light
					depressed
					@click="goToLogin"
					class="bold login"
				>
					로그인
				</v-btn>
				<template v-else>
					<v-btn
						width="40"
						height="40"
						icon
						class="mr-2"
						@click="video = true"
					>
						<v-avatar size="24">
							<v-icon size="24">
								mdi-video-plus
							</v-icon>
						</v-avatar>
					</v-btn>

					<v-menu left bottom offsetY>
						<template v-slot:activator="{ on, attrs }">
							<v-badge
								color="error"
								:content="
									totalNotice > 9 ? '9+' : `${totalNotice}`
								"
								offset-x="24"
								offset-y="24"
							>
								<v-btn
									icon
									v-bind="attrs"
									v-on="on"
									@click="getNoticeList"
								>
									<v-icon>
										mdi-bell-outline
									</v-icon>
								</v-btn>
							</v-badge>
						</template>
						<v-list class="notice_list" outlined dense>
							<v-list-item>
								<v-list-item-content>
									<v-list-item-title
										>전체
										{{ notices.length }}</v-list-item-title
									>
								</v-list-item-content>
								<v-list-item-action>
									<v-list-item-title
										style="font-size:12px; cursor:pointer;"
										@click.stop="removeAllNotice"
										>전체 삭제</v-list-item-title
									>
								</v-list-item-action>
							</v-list-item>
							<template v-for="(notice, i) in notices">
								<v-divider :key="`${i}_divider`" />
								<v-list-item
									:key="`${i}_notice`"
									link
									style="padding-left:0px;"
									@click="$router.push(notice.noti_path)"
								>
									<v-icon
										:color="
											notice.noti_read == '0'
												? 'primary'
												: 'transparent'
										"
										style="margin:7px;"
									>
										mdi-circle-medium
									</v-icon>
									<v-list-item-avatar
										style="margin-right:10px;"
									>
										<v-img :src="notice.profile"> </v-img>
									</v-list-item-avatar>
									<v-list-item-content>
										<v-list-item-subtitle>
											{{ notice.noti_msg }}
										</v-list-item-subtitle>
										<v-list-item-subtitle>
											<span style="font-size:12px;">{{
												notice.reg_dt | diffDate
											}}</span>
										</v-list-item-subtitle>
									</v-list-item-content>

									<v-list-item-action
										@click.stop="removeNotice(notice)"
									>
										<v-icon>
											mdi-close
										</v-icon>
									</v-list-item-action>
								</v-list-item>
							</template>
							<template v-if="!notices.length">
								<v-divider />
								<v-container fluid>
									<v-row>
										<v-col cols="12">
											<v-row align="end" justify="center">
												<v-icon
													style="font-size:120px;"
												>
													mdi-bell-off-outline
												</v-icon>
											</v-row>
										</v-col>
									</v-row>
									<v-row>
										<v-col cols="12">
											<v-row align="end" justify="center">
												<div style="font-size:20px">
													새로운 알림이 없습니다
												</div>
											</v-row>
										</v-col>
									</v-row>
									<v-row>
										<v-col cols="12">
											<v-row align="end" justify="center">
												<div style="font-size:12px">
													나의 활동 소식과 새 소식을
												</div>
											</v-row>
											<v-row align="end" justify="center">
												<div style="font-size:12px">
													한번에 받아 보세요.
												</div>
											</v-row>
										</v-col>
									</v-row>
								</v-container>
							</template>
						</v-list>
					</v-menu>

					<v-menu bottom min-width="200px" rounded offset-y>
						<template v-slot:activator="{ on }">
							<v-btn
								icon
								color="transparent"
								v-on="on"
								class="ml-1 mr-5"
							>
								<v-avatar size="36">
									<img
										:src="
											user.profile
												? user.profile
												: '@/assets/default_profile.png'
										"
										alt="프로필"
									/>
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
			<v-card color="#f9f9f9">
				<v-card-text class="pt-5">
					<v-row justify="center">
						<v-img
							src="@/assets/logo.png"
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
import moment from 'moment';
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
			totalNotice: 0,
			notices: [],
		};
	},

	methods: {
		...mapActions([
			'getNotiCount',
			'getNotification',
			'removeNotification',
			'removeNotifications',
		]),
		keywordSearch() {
			this.$router.push(`/video?search=${this.keyword}`);
		},
		goToLogin() {
			this.$store.commit('setPrevPage', this.$route.fullPath);
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
		getNoticeList() {
			this.getNotification().then(res => {
				this.notices = res.data;
			});
		},
		dateFormatter(date) {
			return moment(date).fromNow();
		},
		removeNotice(notice) {
			const self = this;
			this.removeNotification(notice.noti_no).then(function() {
				const index = self.notices.indexOf(notice);
				self.notices.splice(index, 1);
				self.totalNotice =
					self.totalNotice > 0 ? self.totalNotice - 1 : 0;
			});
		},
		removeAllNotice() {
			const self = this;
			this.removeNotifications().then(function() {
				self.notices = [];
				self.totalNotice = 0;
			});
		},
		getTotalNotice() {
			this.getNotiCount().then(res => {
				this.totalNotice = res.data.num_of_noti;
			});
		},
	},
	computed: {
		...mapGetters(['isLogin', 'user']),
	},
	mounted() {
		this.getTotalNotice();
	},
	filters: {
		diffDate(date) {
			return moment(date).fromNow();
		},
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
.v-menu__content {
	box-shadow: none;
	overflow-y: visible;
	overflow-x: visible;
	contain: none;
}
.login {
	font-family: 'BMJUA';
}
.notice_list {
	border-radius: 3px;
	width: 400px;
	min-height: 200px;
	max-height: 450px;
	overflow-y: auto;
	padding: 0px;
}
@font-face {
	font-family: 'BMJUA';
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/BMJUA.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}
</style>
<style>
.v-input--is-focused > .v-input__control > .v-input__slot {
	background-color: rgba(38, 38, 56, 0.13) !important;
}

.v-input--is-focused > .v-input__control > .v-input__slot input {
	color: black !important;
}
</style>
