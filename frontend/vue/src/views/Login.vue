<template>
	<v-container fluid class="login-wrapper">
		<v-row>
			<v-img
				src="@/assets/logo2.png"
				max-width="120"
				contain
				class="ml-15 mt-10"
				style="cursor: pointer;"
			/>
		</v-row>
		<v-row align="center" justify="center">
			<v-col cols="12">
				<login-form
					v-if="!isSignup"
					@goToSignup="goToSignup"
					style="margin-top:100px;"
				/>
				<signup-form v-else @goToSignup="goToSignup" />
			</v-col>
			<v-dialog v-model="success" max-width="400">
				<v-card>
					<v-card-title class="headline">
						<v-container
							class="justify-center wrap"
							fluid
							fill-height
						>
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
		</v-row>
	</v-container>
</template>

<script>
import LoginForm from '@/components/login/LoginForm.vue';
import SignupForm from '@/components/login/SignupForm.vue';

export default {
	components: {
		LoginForm,
		SignupForm,
	},
	data() {
		return {
			isSignup: false,
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
		goToSignup(flag) {
			this.isSignup = flag;
		},
	},
};
</script>

<style scoped>
a {
	text-decoration: none;
}

.login-wrapper {
	min-height: 100vh;
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
