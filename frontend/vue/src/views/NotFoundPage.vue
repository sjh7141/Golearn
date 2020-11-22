<template>
	<v-container class="wrapper">
		<v-img class="asm" src="@/assets/logo.png" style="z-index: 0;"> </v-img>
		<v-row style="z-index: 99;">
			<v-row class="hunit">
				<v-col cols="12" style="z-index: 99;">
					<h1>해당 페이지를 찾지 못했습니다.</h1>
				</v-col>
			</v-row>
			<v-row class="hunit">
				<v-col style="z-index: 99;">
					<h3>주소가 잘못되었거나 사라진 페이지 입니다.</h3>
				</v-col>
			</v-row>
			<v-row class="hunit">
				<v-col style="z-index: 99;">
					<h3 class="link" @click="$router.push('/')">
						메인페이지로 이동하기
					</h3>
				</v-col>
			</v-row>
		</v-row>
		<!-- <v-row justify-lg="center" class="content">
			<v-row>
				<v-img
					class="logo"
					src="@/assets/logo.png"
					@click="$router.push('/')"
					width="100px"
				/>
				<h1>404 Page Not Found</h1>
			</v-row>
		</v-row> -->
	</v-container>
</template>

<script>
export default {};
</script>

<style scoped>
.wrapper {
	min-height: 700px;
	padding: 100px;
}
.hunit {
	width: 100%;
}
h1 {
	font-size: 50px;
}
h3 {
	font-weight: 100;
	font-size: 30px;
}
.link {
	color: #8675a9;
	cursor: pointer;
	text-decoration: underline;
}
.asm {
	position: absolute;
	right: 10%;
	bottom: 10%;
	width: 25%;
}
@keyframes click {
	0% {
		rotate: 20deg;
		-webkit-transform: rotate(20deg);
		transform: rotate(20deg);
	}

	50% {
		-webkit-transform: rotate(0deg);
		transform: rotate(0deg);
	}

	100% {
		-webkit-transform: rotate(-20deg);
		transform: rotate(-20deg);
	}
}
.logo {
	width: 30px;
	animation-name: click;
	animation-timing-function: steps(1);
	animation-duration: 1.5s;
	animation-iteration-count: infinite;
	animation-delay: 1s;
	cursor: pointer;
}
.a {
	width: 100%;
	background-color: white;
	margin-left: 0;
}
</style>
