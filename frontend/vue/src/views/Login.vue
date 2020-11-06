<template>
	<v-container fluid class="login-wrapper">
		<v-row>
			<v-img
				src="@/assets/logo2.png"
				max-width="120"
				contain
				class="ml-15 mt-10"
				style="cursor: pointer;"
				@click="goToHome"
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
			<v-dialog v-model="success" max-width="450">
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
									🎊회원가입을 축하드립니다🎊
								</span>
							</v-row>
						</v-container>
					</v-card-title>
					<v-card-text style="text-align:center">
						Run & Go 에서 다양한 활동을 즐겨보세요!
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
			success: false,
		};
	},
	mounted() {},
	methods: {
		goToSignup(flag) {
			if (flag == 0) {
				this.isSignup = true;
			} else {
				this.isSignup = false;
			}
			if (flag == 2) {
				this.success = true;
			}
		},
		goToHome() {
			this.$router.push('/');
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
</style>
