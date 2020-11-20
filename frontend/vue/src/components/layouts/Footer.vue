<template>
	<v-container id="footer" class="justify-center wrap pt-9" fluid fill-height>
		<v-row fill-height>
			<v-col cols="1" />
			<v-col cols="1" align="center">
				<v-img src="@/assets/logo.png" width="70" height="70" />
			</v-col>
			<v-col cols="6">
				<v-row>
					<ul class="footLink">
						<li class="point br1" style="padding-left: 0;">
							<router-link to="/privacy">
								개인정보처리방침
							</router-link>
						</li>
						<li class="br1">
							<router-link to="/terms">
								이용약관
							</router-link>
						</li>
						<li class="br1">
							<router-link to="/community/faq/1">
								Help
							</router-link>
						</li>
						<li class="br1">
							<router-link to="/community/notice/1">
								Contact
							</router-link>
						</li>
						<li>
							<router-link to="/community/notice/1">
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
							href="https://play.google.com/store/apps/details?id=kr.co.golearn"
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
export default {
	data() {
		return {
			policy: false,
			terms: false,
		};
	},
};
</script>

<style scoped>
.headline {
	font-weight: 600;
	font-size: 30px;
}
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
