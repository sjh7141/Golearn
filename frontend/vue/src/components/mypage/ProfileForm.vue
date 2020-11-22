<template>
	<div style="">
		<v-card-title class="px-0">
			<span class="modify-text" style="font-family: 'BMJUA';">
				프로필 수정
			</span>
		</v-card-title>
		<div class="form pt-10 ml-15 modify-box-wrap">
			<div class="py-8 px-10 wrapper">
				<!-- 프로필 사진 변경 -->
				<v-row justify="center" style="margin-top:20px;">
					<input
						v-show="false"
						id="file"
						ref="file"
						type="file"
						accept="image/png, image/jpeg, image/bmp"
						@change="changeImg"
					/>

					<v-badge
						color="transparent"
						bottom
						offset-x="50"
						offset-y="50"
					>
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
							<v-img ref="img" :src="imgURL" />
						</v-avatar>
					</v-badge>
				</v-row>
				<!-- 정보 수정 -->
				<div class="py-4 bold" style="font-size: 22px;">필수정보</div>
				<v-row>
					<v-col md="6">
						<div class="pb-1 bold upper-text">
							닉네임
						</div>
						<v-text-field
							v-model="nickname"
							ref="nickname"
							outlined
							color="#8fbaff"
							:success-messages="nickSuccessMessage"
							:error-messages="nickErrorMessage"
							@blur="duplicateNickname()"
						/>
					</v-col>
					<v-col md="6">
						<div class="pb-1 bold upper-text">
							이메일
							<span style="color:#ed4a4a;">
								(변경요청시 1:1문의)
							</span>
						</div>
						<v-text-field
							v-model="email"
							ref="email"
							outlined
							color="#8fbaff"
							disabled
						/>
					</v-col>
				</v-row>
				<v-row align="center" justify="center">
					<v-btn
						class="mr-3"
						color="#8059d4"
						depressed
						large
						:loading="loading"
						style="margin-top:10px;"
						@click="validInfo"
					>
						<span style="font-size:15px; color:white">
							수정하기
						</span>
					</v-btn>
					<v-btn
						color="#cccccc"
						depressed
						large
						style="margin-top:10px;"
					>
						<span style="font-size:15px; color:white">
							취소
						</span>
					</v-btn>
				</v-row>
			</div>
			<!-- 비밀번호 변경 -->
			<div class="py-8 px-10 wrapper">
				<div class="py-4 bold" style="font-size: 22px;">비밀번호</div>
				<div class="pb-1 bold upper-text">
					새 비밀번호
				</div>
				<v-text-field
					v-model="pwd"
					ref="pwd"
					placeholder="새로운 비밀번호를 입력해 주세요."
					outlined
					color="#8fbaff"
					type="password"
					:success-messages="pwdSuccessMessage"
					:error-messages="pwdErrorMessage"
					@blur="validPassword()"
				/>
				<div class="pb-1 bold upper-text">
					새 비밀번호 확인
				</div>
				<v-text-field
					v-model="rePwd"
					ref="rePwd"
					placeholder="비밀번호를 다시 한 번 입력해 주세요."
					outlined
					color="#8fbaff"
					type="password"
					:success-messages="rePwdSuccessMessage"
					:error-messages="rePwdErrorMessage"
					@input="validRePassword()"
				/>
				<v-row align="center" justify="center">
					<v-btn
						class="mr-3"
						color="#8059d4"
						depressed
						large
						:loading="loading"
						style="margin-top:10px;"
						@click="modifyPassword"
					>
						<span style="font-size:15px; color:white">
							수정하기
						</span>
					</v-btn>
					<v-btn
						color="#cccccc"
						depressed
						large
						style="margin-top:10px;"
					>
						<span style="font-size:15px; color:white">
							취소
						</span>
					</v-btn>
				</v-row>
			</div>
			<!-- 이메일 인증 -->
			<div class="py-8 px-10 wrapper">
				<div class="py-4 bold" style="font-size: 22px;">
					이메일 인증
				</div>
				<v-row v-if="checkEmail" justify="center" align="center">
					<span class="bold" style="color:#5de364">
						이메일인증이 완료되었습니다. 좋은 활동 부탁드립니다.😁
					</span>
				</v-row>
				<v-row v-else justify="center">
					<span class="bold" style="color:#ff5e5e">
						이메일인증이 필요합니다.
					</span>
					<v-btn
						color="#cccccc"
						depressed
						small
						class="ml-4"
						:loading="loading"
						@click="certi = true"
					>
						<span style="color:white">
							인증하기
						</span>
						<v-icon color="white">
							mdi-chevron-right
						</v-icon>
					</v-btn>
				</v-row>
			</div>
			<v-dialog v-model="certi" max-width="760">
				<v-card class="pt-12">
					<div class="title px-10 pb-6">
						이메일 인증
					</div>
					<v-card-text class="bold py-0 px-10">
						이메일 인증 안내
					</v-card-text>

					<div
						class="mx-12 pa-6 mb-6"
						style="background-color:#f9fafb; font-size:16px;"
					>
						<ul style="color:#999999">
							<li>
								이메일 인증을 진행한 경우, 등록된 이메일로
								인증번호가 발송됩니다.
							</li>
							<li>
								인증이 완료되면 고런고런의 제한된 컨텐츠를 모두
								이용하실 수 있습니다.
							</li>
						</ul>
					</div>
					<v-card-text class="bold py-0 px-10">
						현재 등록된 이메일
					</v-card-text>
					<v-text-field
						class="py-0 px-10"
						v-model="email"
						outlined
						hide-details
						color="#8fbaff"
						disabled
					/>
					<div style="width:130px; margin: 10px auto;">
						<v-btn
							color="#8059d4"
							depressed
							large
							:loading="loading"
							@click="sendEmail"
						>
							<span style="font-size:15px; color:white">
								인증번호 전송
							</span>
						</v-btn>
					</div>
					<v-card-text class="bold py-0 px-10">
						이메일 인증번호
					</v-card-text>
					<v-text-field
						class="py-0 px-10"
						v-model="emailCode"
						outlined
						placeholder="전송된 인증번호를 입력해주세요."
						color="#8fbaff"
						:error-messages="emailCodeErrorMessage"
					/>
					<v-card-actions class="pa-0 pt-8">
						<v-btn
							text
							@click="checkCode"
							style="background-color:#8059d4; width:50%;border-radius:0px; height:70px; font-size:20px; color:white;"
						>
							인증하기
						</v-btn>
						<v-btn
							class="ma-0"
							text
							@click="certi = false"
							style="background-color:#333333; width:50%;border-radius:0px; height:70px; font-size:20px; color:white;"
						>
							취소
						</v-btn>
					</v-card-actions>
				</v-card>
			</v-dialog>
			<!-- 탈퇴 -->
			<div class="py-8 px-10 wrapper">
				<v-row align="center">
					<v-col>
						<div class="my-4 bold" style="font-size: 22px;">
							탈퇴
						</div>
					</v-col>
					<v-spacer></v-spacer>
					<v-col style="text-align:right;">
						<v-btn v-if="fold" icon color="indigo" @click="unFold">
							<v-icon large color="blue-grey">
								mdi-chevron-right
							</v-icon>
						</v-btn>
						<v-btn v-else icon color="indigo" @click="unFold">
							<v-icon large color="blue-grey">
								mdi-chevron-down
							</v-icon>
						</v-btn>
					</v-col>
					<v-scroll-y-transition>
						<v-col cols="12" v-if="!fold" style="font-size: 15px;">
							<div class="bold mx-10">탈퇴 전 확인해주세요</div>
							<div class="mx-10">
								고런고런을 탈퇴하시겠습니까? 탈퇴하기 전 아래
								유의사항을 꼭 읽어주시고 결정해주세요!<br />
								더 노력하는 고런고런이 되겠습니다.😌<br />
								<ol style="line-height:25px;">
									<li>
										작성한 콘텐츠(동영상, 게시물, 댓글 등)는
										삭제되지 않으며, 만일 삭제를 원하시면
										탈퇴 이전에 삭제를 진행하여 주세요.
									</li>
									<li>
										탈퇴 후 동일한 메일로 재가입은
										불가능합니다.
									</li>
									<li>
										현재 비밀번호를 입력하고 탈퇴하기를
										누르시면 위 내용에 동의하는 것으로
										간주됩니다.
									</li>
								</ol>
							</div>
							<v-text-field
								class="mx-10 mt-5"
								v-model="deletePwd"
								placeholder="현재 비밀번호를 입력해주세요"
								outlined
								color="#8fbaff"
								type="password"
								:success-messages="deleteSuccessMessage"
								:error-messages="deleteErrorMessage"
								@blur="validDeletePwd()"
							/>
							<v-row align="center" justify="center">
								<v-btn
									class="mr-3"
									color="error"
									depressed
									large
									:loading="loading"
									style="margin-top:10px;"
									@click="deleteUser"
								>
									<span style="font-size:15px; color:white">
										탈퇴
									</span>
								</v-btn>
							</v-row>
						</v-col>
					</v-scroll-y-transition>
				</v-row>
			</div>
		</div>
	</div>
</template>

<script>
import PV from 'password-validator';
import { mapGetters } from 'vuex';

export default {
	components: {},
	data() {
		return {
			profile: '',
			checkEmail: false,
			fold: true,
			certi: false,
			passwordSchema: new PV(),
			pwd: '',
			rePwd: '',
			deletePwd: '',
			nickname: '',
			email: '',
			emailCode: '',
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
			deleteError: false,
			deleteSuccessMessage: '',
			deleteErrorMessage: '',
			emailCodeErrorMessage: '',
			loading: false,
			imgURL: '',
		};
	},
	computed: {
		...mapGetters(['user', 'isLogin']),
	},
	methods: {
		clickImg() {
			this.$refs.file.value = null;
			this.$refs.file.click();
		},
		changeImg() {
			var file = document.getElementById('file').files[0];

			if (file) {
				this.imgURL = URL.createObjectURL(file);
			}
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
		validDeletePwd() {
			if (
				this.deletePwd != '' &&
				this.passwordSchema.validate(this.deletePwd)
			) {
				this.$store
					.dispatch('pwdCheck', { password: this.deletePwd })
					.then(({ data }) => {
						if (data) {
							this.deleteError = false;
							this.deleteSuccessMessage =
								'비밀번호가 확인되었습니다.';
							this.deleteErrorMessage = '';
						} else {
							this.deleteError = true;
							this.deleteSuccessMessage = '';
							this.deleteErrorMessage =
								'입력된 비밀번호가 현재 비밀번호와 다릅니다.';
						}
					});
			} else {
				this.deleteError = true;
				this.deleteSuccessMessage = '';
				this.deleteErrorMessage =
					'영문,숫자 포함 5 자리이상이어야 합니다.';
			}
		},
		duplicateNickname() {
			if (this.nickname == this.user.nickname) {
				return;
			}
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
		validInfo() {
			if (this.nickname == '') {
				this.setNicknameError();
				return;
			}

			if (!this.nickError) {
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
							this.modifyInfo(data);
						})
						.catch(() => {
							this.loading - false;
							alert('업로드 에러');
						});
				} else {
					this.modifyInfo(null);
				}
			}
		},
		setPwdError() {
			this.pwdError = true;
			this.pwdSuccessMessage = '';
			this.pwdErrorMessage = '영문,숫자 포함 5 자리이상이어야 합니다.';
		},
		setNicknameError() {
			this.nickError = true;
			this.nickSuccessMessage = '';
			this.nickErrorMessage = '사용할 수 없는 닉네임입니다.';
		},
		modifyInfo(file) {
			let data = {
				// email: this.email,
				nickname: this.nickname,
				profile: this.profile,
			};
			if (file != null) {
				data.profile = file;
			}
			this.$store
				.dispatch('modifyUser', data)
				.then(() => {
					this.loading = false;
					this.$store.dispatch('getUser').then(({ data }) => {
						this.$store.commit('setUser', data);
						this.$router.go();
					});
				})
				.catch(() => {
					this.loading = false;
					alert(
						'회원정보수정시 오류가 발생하였습니다. 잠시 후 다시 시도해주세요.',
					);
				});
		},
		modifyPassword() {
			if (!this.pwdError && !this.rePwdError) {
				let data = {
					password: this.pwd,
				};
				this.$store
					.dispatch('modifyPassword', data)
					.then(() => {
						this.loading = false;
						alert(
							'다음 로그인부터 새로운 비밀번호로 접속하여 주세요.',
						);
						this.$router.go();
					})
					.catch(() => {
						this.loading = false;
						alert(
							'비밀번호수정시 오류가 발생하였습니다. 잠시 후 다시 시도해주세요.',
						);
					});
			}
		},
		deleteUser() {
			if (!this.deleteError && confirm('정말 탈퇴 하시겠습니까?')) {
				this.$store
					.dispatch('deleteUser')
					.then(() => {
						this.loading = false;
						alert('정상적으로 탈퇴되었습니다.');
						this.$router.push('/');
					})
					.catch(() => {
						this.loading = false;
						alert(
							'회원탈퇴시도 중 오류가 발생하였습니다. 잠시 후 다시 시도해주세요.',
						);
					});
			}
		},
		unFold() {
			this.fold = !this.fold;
		},
		sendEmail() {
			this.$store
				.dispatch('sendEmail', { email: this.email })
				.then(() => {
					this.loading = false;
					alert('정상적으로 전송되었습니다.');
				})
				.catch(() => {
					this.loading = false;
				});
		},
		checkCode() {
			this.$store
				.dispatch('checkCode', this.emailCode)
				.then(({ data }) => {
					if (data) {
						this.loading = false;
						this.certi = false;
						this.emailCodeErrorMessage = '';
						this.$store.dispatch('getUser').then(({ data }) => {
							this.$store.commit('setUser', data);
							this.$router.go();
						});
					} else {
						this.loading = false;
						this.emailCodeErrorMessage = '잘못된 인증번호입니다.';
					}
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
		if (this.isLogin != 1) {
			this.$router.push('/login');
		}
		this.profile = this.user.profile;
		this.imgURL = this.profile;
		this.id = this.user.username;
		this.email = this.user.email;
		this.nickname = this.user.nickname;
		this.checkEmail = this.user.check_email;
	},
};
</script>

<style scoped>
a {
	text-decoration: none;
}

.modify-wrapper {
	height: 100vh;
	background-image: url('/src/assets/modify_banner.jpg');
	background-repeat: no-repeat;
	background-size: cover;
}

.modify-text {
	padding-bottom: 20px;
	font-size: 40px;
	font-weight: 600;
}

.modify-box-wrap {
	width: 786px;
	/* margin: 0 auto; */
	/* background-color: rgba(0, 0, 0, 0.75); */
}

.wrapper {
	border: 1px solid #e3e3e3;
	margin-bottom: 30px;
}

.upper-text {
	color: #999999;
	font-size: 15px;
}

.title {
	font-weight: 600;
	font-size: 30px !important;
}
</style>
