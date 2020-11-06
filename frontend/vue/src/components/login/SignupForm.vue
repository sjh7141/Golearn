<template>
	<div class="signup-box-wrap pa-15 ">
		<span class="signup-text">
			회원가입
		</span>
		<v-row justify="center" style="margin-top:20px;">
			<input
				v-show="false"
				id="file"
				ref="file"
				type="file"
				accept="image/png, image/jpeg, image/bmp"
				@change="changeImg"
			/>

			<v-badge color="transparent" bottom offset-x="50" offset-y="50">
				<template v-slot:badge>
					<v-btn
						fab
						depressed
						color="#e4e4e4"
						outlined
						small
						style="background-color:white;"
						@click="clickImg()"
					>
						<v-icon color="#2e2e2e">
							mdi-pencil
						</v-icon>
					</v-btn>
				</template>
				<v-avatar color="#f5f5f5" width="130" height="130">
					<v-img ref="img" src="@/assets/user.png" />
				</v-avatar>
			</v-badge>
		</v-row>
		<div class="form pt-10">
			<v-text-field
				v-model="id"
				outlined
				dark
				label="아이디"
				placeholder="아이디를 입력해 주세요."
				color="#8fbaff"
				:success-messages="id_text"
				:error="iderror && id_text !== ''"
				@blur="validID()"
			/>
			<v-text-field
				class="pt-4"
				v-model="password"
				outlined
				dark
				label="비밀번호"
				placeholder="비밀번호를 입력해 주세요."
				color="#8fbaff"
				type="password"
				:success-messages="pw_text"
				:error="pwerror && pw_text !== ''"
				@blur="validPassword()"
			/>
			<v-text-field
				v-model="re_password"
				outlined
				dark
				label="비밀번호 확인"
				placeholder="비밀번호를 다시 한번 입력해 주세요."
				color="#8fbaff"
				type="password"
				:success-messages="repw_text"
				:error="repwerror && repw_text !== ''"
				@input="validRePassword()"
			/>
			<v-text-field
				class="pt-4"
				v-model="nickname"
				outlined
				dark
				label="닉네임"
				placeholder="닉네임을 입력해 주세요."
				color="#8fbaff"
				:success-messages="nick_text"
				:error="nickerror && nick_text !== ''"
				@blur="validNickname()"
			/>
			<v-text-field
				class="pt-4"
				v-model="email"
				outlined
				dark
				label="이메일"
				placeholder="이메일을 입력해 주세요."
				color="#8fbaff"
				:success-messages="email_text"
				:error="emailerror && email_text !== ''"
				@blur="validEmail()"
			/>
			<v-list-item style="padding:0;">
				<v-list-item-content style="padding:0;">
					<v-checkbox
						label="이벤트 및 소식 알림 동의 (선택)"
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
					@click="signup"
				>
					<span style="font-size:20px">가입하기</span>
				</v-btn>
			</v-row>
			<v-row justify="center">
				<div class="pa-2" style="font-size: 13px; color:#a6a6a6;">
					Run & Go 의
					<a
						href="#"
						style="font-size: 13px; text-decoration:none;"
						@click="terms = true"
					>
						이용약관 </a
					>,
					<a
						href="#"
						style="font-size: 13px; text-decoration:none;"
						@click="policy = true"
					>
						개인정보취급방침</a
					>을 확인하였고 이에 동의합니다.
				</div>
			</v-row>
			<v-row>
				<v-divider class="mt-7 mb-5" dark></v-divider>
			</v-row>
			<v-row>
				<v-col cols="12" class="py-0" style="font-size:16px;">
					<div @click="goToSignup" style="cursor: pointer;">
						<v-icon color="blue-grey">
							mdi-chevron-left
						</v-icon>
						<span style="color:white; font-size: 14px;">
							로그인
						</span>
					</div>
				</v-col>
			</v-row>
		</div>
		<!-- 개인정보 처리방침 dialog -->
		<v-dialog v-model="policy" width="600px">
			<v-card>
				<v-card-title class="d-flex">
					<v-row>
						<v-col cols="12" align="end">
							<v-btn
								class="mx-2"
								fab
								x-small
								:outlined="false"
								:depressed="true"
								color="rgba(0, 153, 204, 0)"
								@click="policy = false"
							>
								<v-icon dark>
									mdi-close
								</v-icon>
							</v-btn>
						</v-col>
						<v-col cols="12">
							<span class="headline">
								Run&GO 개인정보 처리방침
							</span>
						</v-col>
					</v-row>
				</v-card-title>
				<v-card-text style="word-break:keep-all">
					<private-policy />
				</v-card-text>
			</v-card>
		</v-dialog>
		<v-dialog v-model="terms" width="600px">
			<v-card>
				<v-card-title class="d-flex">
					<v-row>
						<v-col cols="12" align="end">
							<v-btn
								class="mx-2"
								fab
								x-small
								:outlined="false"
								:depressed="true"
								color="rgba(0, 153, 204, 0)"
								@click="terms = false"
							>
								<v-icon dark>
									mdi-close
								</v-icon>
							</v-btn>
						</v-col>
						<v-col cols="12">
							<span class="headline">Run&Go 이용약관</span>
						</v-col>
					</v-row>
				</v-card-title>
				<v-card-text style="word-break:keep-all">
					<terms />
				</v-card-text>
			</v-card>
		</v-dialog>
	</div>
</template>

<script>
import PrivatePolicy from '@/components/component/Policy.vue';
import Terms from '@/components/component/Terms.vue';
export default {
	components: {
		PrivatePolicy,
		Terms,
	},
	data() {
		return {
			policy: false,
			terms: false,
		};
	},
	methods: {
		clickImg() {
			this.$refs.file.click();
		},
		changeImg() {
			var self = this;
			var file = document.getElementById('file').files[0];
			var reader = new FileReader();

			reader.onloadend = function() {
				self.$refs.img.src = reader.result;
			};

			if (file) {
				reader.readAsDataURL(file);
			}
		},
		goToSignup() {
			this.$emit('goToSignup', false);
		},
	},
};
</script>

<style scoped>
a {
	text-decoration: none;
}

.signup-wrapper {
	height: 100vh;
	/* background-color: #ededed; */
	background-image: url('/src/assets/signup_banner.jpg');
	background-repeat: no-repeat;
	background-size: cover;
}

.signup-text {
	padding-bottom: 20px;
	color: white;
	font-size: 30px;
	font-weight: 600;
}

.signup {
	display: flex;
	min-width: 1200px;
}

.signup-box {
	display: flex;
	flex-direction: column;
	background-color: rgb(255, 255, 255);
	width: 100%;
	min-height: 100vh;
}

.signup-box-wrap {
	width: 580px;
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
