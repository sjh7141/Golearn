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
					<v-img ref="img" />
				</v-avatar>
			</v-badge>
		</v-row>
		<div class="form pt-10">
			<v-text-field
				v-model="id"
				ref="id"
				outlined
				dark
				label="아이디"
				placeholder="아이디를 입력해 주세요."
				color="#8fbaff"
				:success-messages="idSuccessMessage"
				:error-messages="idErrorMessage"
				:error="idError"
				@blur="duplicateId()"
			/>
			<v-text-field
				class="pt-4"
				v-model="pwd"
				ref="pwd"
				outlined
				dark
				label="비밀번호"
				placeholder="비밀번호를 입력해 주세요."
				color="#8fbaff"
				type="password"
				:success-messages="pwdSuccessMessage"
				:error-messages="pwdErrorMessage"
				@blur="validPassword()"
			/>
			<v-text-field
				v-model="rePwd"
				ref="rePwd"
				outlined
				dark
				label="비밀번호 확인"
				placeholder="비밀번호를 다시 한 번 입력해 주세요."
				color="#8fbaff"
				type="password"
				:success-messages="rePwdSuccessMessage"
				:error-messages="rePwdErrorMessage"
				@input="validRePassword()"
			/>
			<v-text-field
				class="pt-4"
				v-model="nickname"
				ref="nickname"
				outlined
				dark
				label="닉네임"
				placeholder="닉네임을 입력해 주세요."
				color="#8fbaff"
				:success-messages="nickSuccessMessage"
				:error-messages="nickErrorMessage"
				@blur="duplicateNickname()"
			/>
			<v-text-field
				class="pt-4"
				v-model="email"
				ref="email"
				outlined
				dark
				label="이메일"
				placeholder="이메일을 입력해 주세요."
				color="#8fbaff"
				:success-messages="emailSuccessMessage"
				:error-messages="emailErrorMessage"
				@blur="duplicateEmail()"
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
					color="#633cb6"
					x-large
					dark
					:loading="loading"
					style="margin-top:10px; width:95%;"
					@click="validTotal"
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
					<div @click="goToSignup(1)" style="cursor: pointer;">
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
import PV from 'password-validator';
import * as EmailValidator from 'email-validator';

export default {
	components: {
		PrivatePolicy,
		Terms,
	},
	data() {
		return {
			policy: false,
			terms: false,
			passwordSchema: new PV(),
			id: '',
			pwd: '',
			rePwd: '',
			nickname: '',
			email: '',
			idError: false,
			idSuccessMessage: '',
			idErrorMessage: '',
			pwdError: false,
			pwdSuccessMessage: '',
			pwdErrorMessage: '',
			rePwdError: false,
			rePwdErrorMessage: '',
			rePwdSuccessMessage: '',
			emailError: false,
			emailErrorMessage: '',
			emailSuccessMessage: '',
			nickError: false,
			nickSuccessMessage: '',
			nickErrorMessage: '',
			loading: false,
		};
	},
	methods: {
		clickImg() {
			// this.$refs.file.value = null;
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
		goToSignup(state) {
			this.$emit('goToSignup', state);
		},
		validCheck() {
			return (
				this.idError ||
				this.pwdError ||
				this.rePwdError ||
				this.nickError ||
				this.emailError
			);
		},
		validParameter(param) {
			if (param == '') {
				return false;
			} else {
				return true;
			}
		},
		duplicateId() {
			if (!this.validParameter(this.id)) {
				this.setIdError();
			} else {
				this.$store.dispatch('idCheck', this.id).then(({ data }) => {
					if (data) {
						this.idError = false;
						this.idSuccessMessage = '사용할 수 있는 아이디입니다.';
						this.idErrorMessage = '';
					} else {
						this.setIdError();
					}
				});
			}
		},
		validPassword() {
			if (this.pwd != '' && this.passwordSchema.validate(this.pwd)) {
				this.pwdError = false;
				this.pwdSuccessMessage = '올바른 형태의 비밀번호입니다.';
				this.pwdErrorMessage = '';
			} else {
				this.setPwdError();
			}
		},
		validRePassword() {
			if (this.pwd == this.rePwd) {
				this.rePwdError = false;
				this.rePwdSuccessMessage = '비밀번호가 일치합니다.';
				this.rePwdErrorMessage = '';
			} else {
				this.rePwdError = true;
				this.rePwdSuccessMessage = '';
				this.rePwdErrorMessage = '비밀번호가 일치하지 않습니다.';
			}
		},
		duplicateNickname() {
			if (!this.validParameter(this.nickname)) {
				this.setNicknameError();
			} else {
				this.$store
					.dispatch('nicknameCheck', this.nickname)
					.then(({ data }) => {
						if (data) {
							this.nickError = false;
							this.nickSuccessMessage =
								'사용할 수 있는 닉네임입니다.';
							this.nickErrorMessage = '';
						} else {
							this.setNicknameError();
						}
					});
			}
		},
		validEmail() {
			if (this.email != '' && EmailValidator.validate(this.email)) {
				return true;
			} else {
				this.setEmailError();
				return false;
			}
		},
		duplicateEmail() {
			if (this.validEmail()) {
				this.$store
					.dispatch('emailCheck', this.email)
					.then(({ data }) => {
						if (data) {
							this.emailError = false;
							this.emailSuccessMessage =
								'사용할 수 있는 이메일입니다.';
							this.emailErrorMessage = '';
						} else {
							this.emailError = true;
							this.emailSuccessMessage = '';
							this.emailErrorMessage =
								'사용할 수 없는 이메일입니다.';
						}
					});
			}
		},
		validTotal() {
			let terminate = true;
			if (this.id == '') {
				this.setIdError();
			} else if (this.pwd == '') {
				this.setPwdError();
			} else if (this.nickname == '') {
				this.setNicknameError();
			} else if (this.email == '') {
				this.setEmailError();
			} else {
				terminate = false;
			}

			if (terminate) return;

			if (
				!this.idError &&
				!this.pwdError &&
				!this.rePwdError &&
				!this.nickError &&
				!this.emailError
			) {
				this.loading = true;
				let formData = new FormData();
				if (document.getElementById('file').files[0]) {
					formData.append(
						'file',
						document.getElementById('file').files[0],
					);
					this.$store
						.dispatch('upload', {
							data: formData,
							target: 'member/profile',
						})
						.then(({ data }) => {
							this.signup(data);
						})
						.catch(() => {
							this.loading - false;
							alert('업로드 에러');
						});
				} else {
					this.signup(null);
				}
			}
		},
		setIdError() {
			this.idError = true;
			this.idSuccessMessage = '';
			this.idErrorMessage = '사용할 수 없는 아이디입니다.';
		},
		setPwdError() {
			this.pwdError = true;
			this.pwdSuccessMessage = '';
			this.pwdErrorMessage = '영문,숫자 포함 5 자리이상이어야 합니다.';
		},
		setEmailError() {
			this.emailError = true;
			this.emailSuccessMessage = '';
			this.emailErrorMessage = '올바르지 않은 이메일 형식입니다.';
		},
		setNicknameError() {
			this.nickError = true;
			this.nickSuccessMessage = '';
			this.nickErrorMessage = '사용할 수 없는 닉네임입니다.';
		},
		signup(file) {
			let data = {
				username: this.id,
				password: this.pwd,
				email: this.email,
				nickname: this.nickname,
			};
			if (file != null) {
				data.profile = file;
			}
			this.$store
				.dispatch('signup', data)
				.then(() => {
					this.loading = false;
					this.$emit('goToSignup', 2);
				})
				.catch(() => {
					this.loading = false;
					alert(
						'회원가입에 오류가 발생하였습니다. 잠시 후 다시 시도해주세요.',
					);
				});
		},
	},
	created() {
		this.passwordSchema
			.is()
			.min(5)
			.is()
			.max(50)
			.has()
			.digits()
			.has()
			.letters();
	},
	mounted() {
		this.$refs.img.src = '/user.png';
	},
};
</script>

<style scoped>
a {
	text-decoration: none;
}

.signup-wrapper {
	height: 100vh;
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

.signup-box-wrap {
	width: 580px;
	margin: auto;
	background-color: rgba(0, 0, 0, 0.75);
	color: #a8a8a8;
}
</style>
