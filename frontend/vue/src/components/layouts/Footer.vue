<template>
	<v-container id="footer" class="justify-center wrap pt-9" fluid fill-height>
		<v-row fill-height>
			<v-col cols="1" />
			<v-col cols="1" align="center">
				<v-img src="@/assets/logo3.png" />
			</v-col>
			<v-col cols="6">
				<v-row>
					<ul class="footLink">
						<li class="point br1" style="padding-left: 0;">
							<v-dialog v-model="policy" width="600px">
								<template v-slot:activator="{ on, attrs }">
									<span
										v-bind="attrs"
										@click="policy = !policy"
										v-on="on"
									>
										개인정보 처리방침
									</span>
								</template>
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
												<span class="headline"
													>개인정보 처리방침</span
												>
											</v-col>
										</v-row>
									</v-card-title>
									<v-card-text style="word-break:keep-all">
										<private-policy />
									</v-card-text>
								</v-card>
							</v-dialog>
						</li>
						<li class="br1">
							<v-dialog v-model="terms" width="600px">
								<template v-slot:activator="{ on, attrs }">
									<span
										v-bind="attrs"
										@click="terms = !terms"
										v-on="on"
									>
										이용약관
									</span>
								</template>
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
												<span class="headline">
													이용약관</span
												>
											</v-col>
										</v-row>
									</v-card-title>
									<v-card-text style="word-break:keep-all">
										<terms />
									</v-card-text>
								</v-card>
							</v-dialog>
						</li>
						<li class="br1">
							<router-link to="#">
								Help
							</router-link>
						</li>
						<li class="br1">
							<router-link to="#">
								Contact
							</router-link>
						</li>
						<li>
							<router-link to="#">
								About
							</router-link>
						</li>
					</ul>
				</v-row>
				<v-row>
					서울특별시 강남구 테헤란로 212 (역삼동 718-5번지) FAX.
					0501-070-0000 고객상담센터 020-737-2020
				</v-row>
				<v-row>
					Copyright © 2020 고런고런 Inc. All rights reserved.
				</v-row>
			</v-col>
			<v-col cols="3" class="d-flex justify-center">
				<ul class="sns" style="margin-top:15px;">
					<li class="icon">
						<a href="https://www.facebook.com/">
							<v-icon class="mdi-36px">mdi-facebook</v-icon>
						</a>
					</li>
					<li class="icon">
						<a
							href="https://section.blog.naver.com/BlogHome.nhn?directoryNo=0&currentPage=1&groupId=0"
						>
							<v-icon class="mdi-36px">mdi-blogger</v-icon>
						</a>
					</li>
					<li class="icon">
						<a href="https://www.instagram.com/?hl=ko">
							<v-icon class="mdi-36px">mdi-instagram</v-icon>
						</a>
					</li>
					<li class="icon">
						<a href="https://www.youtube.com/">
							<v-icon class="mdi-36px">mdi-youtube</v-icon>
						</a>
					</li>
					<li class="icon">
						<a
							href="https://go-learn.s3.ap-northeast-2.amazonaws.com/app-release.apk"
						>
							<v-icon class="mdi-36px">mdi-android</v-icon>
						</a>
					</li>
				</ul>
			</v-col>
			<v-col cols="1" />
		</v-row>
	</v-container>
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
};
</script>

<style scoped>
.wrap {
	height: 180px;
	/* background-color:#333; */
	font-weight: 600;
	color: #999;
	font-size: 1vw;
	border-top: thin solid #d9d9d9;
}
ul {
	list-style: none;
	display: block;
	padding: 0 !important;
}
li {
	display: list-item;
	float: left;
	padding: 0 20px;
	margin-bottom: 15px;
}
.br1 {
	border-right: 1px solid #999;
}
a {
	color: #999 !important;
	text-decoration: none;
}
.icon {
	display: list-item;
	font-size: 14px;
	float: left;
	padding: 0 7px;
}
#footer {
	height: 200px;
	background-color: #243238;
}
.mdi-36px {
	font-size: 36px;
	color: white;
}
</style>
