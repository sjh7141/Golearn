<template>
	<div class="login-box-wrap pa-15 ">
		<h1 class="login-text">
			로그인
		</h1>
		<div class="form">
			<v-text-field
				v-model="id"
				outlined
				dark
				label="아이디"
				hide-details
				color="#8fbaff"
			/>
			<v-text-field
				class="mt-5"
				ref="id"
				v-model="password"
				outlined
				dark
				label="비밀번호"
				type="password"
				color="#8fbaff"
				@keydown.enter="login"
				:error-messages="error ? error_text : ''"
			/>
			<v-list-item style="padding:0;">
				<v-list-item-content style="padding:0;">
					<v-checkbox
						label="아이디 저장"
						dense
						dark
						color="primary"
						style="margin:0px"
					/>
				</v-list-item-content>
				<v-list-item-content style="padding:0;">
					<a
						href="#"
						style="font-size:16px;margin-bottom:16px;color:white;"
					>
						비밀번호를 잊으셨나요?
					</a>
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
				<!-- <div class="mt-7 mb-4" style="font-weight:800;">
					<router-link to="/signup"> 회원가입</router-link>
					<span class="bar" aria-hidden="true">|</span>
					<a href="#">아이디 찾기</a>
					<span class="bar" aria-hidden="true">|</span>
					<a href="#">비밀번호 찾기</a>
				</div> -->
			</v-row>
			<v-row>
				<v-divider class="mt-7 mb-5" dark></v-divider>
			</v-row>
			<v-row>
				<v-col cols="12" class="py-0" style="font-size:16px;">
					<span>
						회원이 아닌가요?
						<span
							@click="goToSignup"
							style="color:white;cursor: pointer;"
						>
							지금 가입하세요.
						</span>
					</span>
				</v-col>
			</v-row>
		</div>
	</div>
</template>

<script>
export default {
	data() {
		return {};
	},
	methods: {
		goToSignup() {
			this.$emit('goToSignup', true);
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
	background-image: url('/src/assets/login_banner.jpg');
	background-repeat: no-repeat;
	background-size: cover;
}

.login-text {
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
	color: #a8a8a8;
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
