<template>
	<v-layout flat>
		<v-app-bar
			flat
			tile
			style="background-color:white; padding:0; height:100%"
		>
			<v-card flat tile style="background-color:transparent">
				<v-img src="@/assets/logo.png" max-width="60" contain />
			</v-card>
			<template v-for="(menu, idx) in menus">
				<div :key="`menu_${idx}`" class="nav px-5">
					{{ menu.title }}
				</div>
			</template>
			<v-spacer />
			<v-card
				dense
				flat
				height="100%"
				width="350"
				style="padding: 5px; margin-top:5px;"
			>
				<v-text-field
					class="basic"
					v-model="keyword"
					flat
					dense
					solo
					outlined
					@focus="isFocus = true"
					@blur="isFocus = false"
				>
					<v-icon
						:class="{ basic: isFocus }"
						slot="append"
						@click="keywordSearch()"
					>
						mdi-magnify
					</v-icon>
				</v-text-field>
			</v-card>
			<v-spacer />
			<div>
				<v-btn light depressed outlined @click="goToLogin">
					로그인
				</v-btn>
			</div>
		</v-app-bar>
	</v-layout>
</template>

<script>
export default {
	name: 'Header',

	data() {
		return {
			keyword: '',
			isFocus: false,
			menus: [
				{
					title: '로드맵',
				},
				{
					title: '코스',
				},
				{
					title: '커뮤니티',
				},
			],
		};
	},

	methods: {
		keywordSearch() {},
		goToLogin() {
			this.$router.push('/login');
		},
	},
	mounted() {},
};
</script>

<style scoped>
@import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);
.nav {
	font-family: 'Noto Sans KR', sans-serif !important;
	font-size: 18px;
	font-weight: 600;
}
.nav:hover {
	color: rgb(60, 65, 223);
	cursor: pointer;
}

.append-icon:focus {
	color: rgb(60, 65, 223) !important;
}
</style>
