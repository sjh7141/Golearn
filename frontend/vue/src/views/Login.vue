<template>
	<v-container fluid class="login-wrapper">
		<v-row align="center" justify="center" style="height:100%;">
			<div class="login-box-wrap pa-15 ">
				<h1 class="login-text">
					Go learn Go run
				</h1>
				<div class="form">
					<v-text-field
						v-model="id"
						outlined
						dark
						label="아이디"
						hide-details
						color="primary"
					/>
					<v-text-field
						class="mt-5"
						ref="id"
						v-model="password"
						outlined
						dark
						label="비밀번호"
						type="password"
						color="primary"
						@keydown.enter="login"
						:error-messages="error ? error_text : ''"
					/>
					<v-list-item style="padding:0;">
						<v-list-item-content style="padding:0">
							<v-checkbox
								label="아이디 저장"
								dense
								dark
								color="primary"
								style="margin:0px"
							/>
						</v-list-item-content>
					</v-list-item>
					<v-row align="center" justify="center">
						<v-btn
							color="#2e6afd"
							x-large
							dark
							style="margin-top:10px; width:95%;"
							@click="login"
						>
							<span style="font-size:20px">로그인</span>
						</v-btn>
						<div class="mt-7 mb-4" style="font-weight:800;">
							<a href="#"> 회원가입</a>
							<span class="bar" aria-hidden="true">|</span>
							<a href="#">아이디 찾기</a>
							<span class="bar" aria-hidden="true">|</span>
							<a href="#">비밀번호 찾기</a>
						</div>
					</v-row>
					<v-divider class="mb-4" dark></v-divider>
					<!-- <v-row>
						<v-col cols="12">
							<span style="color:white">
								회원이 아닌가요? 지금 가입하세요.
							</span>
						</v-col>
					</v-row> -->
				</div>
			</div>
		</v-row>
		<v-dialog v-model="success" max-width="400">
			<v-card>
				<v-card-title class="headline">
					<v-container class="justify-center wrap" fluid fill-height>
						<v-row justify="center"> </v-row>
						<v-row justify="center" class="mt-3">
							<span style="font-weight:700;">
								회원가입을 축하드립니다
							</span>
						</v-row>
					</v-container>
				</v-card-title>
				<v-card-text>
					Go learn, Go run 에서 다양한 활동을 즐겨보세요!
				</v-card-text>
				<v-card-actions style="text-align:center">
					<v-spacer></v-spacer>
					<v-btn
						color="primary darken-1"
						text
						@click="success = false"
					>
						확인
					</v-btn>
				</v-card-actions>
			</v-card>
		</v-dialog>
	</v-container>
</template>

<script>
export default {
	data() {
		return {
			id: '',
			password: '',
			error: false,
			error_text: '',
			success: false,
		};
	},
	mounted() {
		this.success = this.$route.query.success;
		history.pushState({ data: '' }, '', '/login');
	},
	methods: {
		getSession() {
			this.$store.dispatch('getUser').then(({ data }) => {
				this.$store.commit('setUser', data);
			});
			this.$router.push('/');
		},
		login() {
			this.$store
				.dispatch('login', {
					username: this.id,
					password: this.password,
				})
				.then(({ data }) => {
					this.$store.commit('setIsLogin', 1);
					this.$store.commit('setToken', data.token);
					this.getSession();
				})
				.catch(() => {
					this.error = true;
					this.error_text =
						'아이디 또는 비밀번호가 일치하지 않습니다.';
					this.$refs.id.focus();
				});
		},
	},
};
</script>

<style scoped>
a {
	text-decoration: none;
}

.login-wrapper {
	height: 100vh;
	/* background-color: #ededed; */
	background-image: url('../assets/login_banner.jpg');
	background-repeat: no-repeat;
	background-size: cover;
}

.login-text {
	text-align: center;
	padding-bottom: 20px;
	color: white;
}

.login {
	display: flex;
	min-width: 1200px;
}

.login-box {
	display: flex;
	flex-direction: column;
	background-color: rgb(255, 255, 255);
	width: 100%;
	min-height: 100vh;
}

.login-box-wrap {
	width: 480px;
	margin: auto;
	background-color: rgba(0, 0, 0, 0.75);
}

.bar {
	display: inline-block;
	width: 1px;
	height: 18px;
	margin: 2px 5px;
	text-indent: -999em;
	background: #e4e4e5;
	vertical-align: bottom;
}
</style>
