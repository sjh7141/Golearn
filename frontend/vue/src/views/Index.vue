<template>
	<div>
		<v-app id="inspire">
			<v-carousel
				hide-delimiters
				hide-delimiter-background
				:show-arrows="false"
				:cycle="cycle"
				height="400"
			>
				<v-carousel-item
					v-for="(item, i) in items"
					:key="i"
					:src="item.src"
				>
					<v-jumbotron dark>
						<v-container fill-height>
							<v-layout align-top>
								<v-flex>
									<h3
										class="display-2"
										style="color:rgb(233,233,233);"
									>
										{{ item.title }}
									</h3>
								</v-flex>
							</v-layout>
						</v-container>
					</v-jumbotron>
				</v-carousel-item>
			</v-carousel>
			<div class="container">
				<v-container>
					<v-divider></v-divider>
					<h2 @click="$router.push('/channel/edit')">
						이런 강의 어떠세요?
					</h2>
					<v-row>
						<vcards :items="cards"> </vcards>
					</v-row>
					<v-divider></v-divider>
					<h2>금주의 인기강의</h2>
					<v-row>
						<vcards :items="hit"></vcards>
					</v-row>
				</v-container>
			</div>
		</v-app>
	</div>
</template>

<script>
import vcards from '@/components/component/cards';
export default {
	components: {
		vcards,
	},
	data() {
		return {
			cycle: true,
			items: [
				{
					title: 'go learn, go run',
					src:
						'https://www.udacity.com/www-proxy/contentful/assets/2y9b3o528xhq/4QTUWa3i4VL7CMc6MjXJe0/556a951cc5ff56725c4f82d0654fe9fc/HomepageBanner_tablet.jpg',
				},
			],
			cards: [
				{
					title: 'C++ 기초 강의',
					viewer: 50,
					like: 1000,
					thumbnail:
						'https://cdn.inflearn.com/wp-content/uploads/ktw_algorithm3.jpg',
					tags: ['C', 'C++'],
					nickname: '고런',
					id: 'golearn',
					profile:
						'https://www.creativefabrica.com/wp-content/uploads/2019/02/Monogram-GR-Logo-Design-by-Greenlines-Studios.jpg',
				},
				{
					title: 'Python으로 여자친구만들기',
					viewer: 50,
					like: 1000,
					thumbnail:
						'https://cdn.inflearn.com/wp-content/uploads/python-2.jpg',
					tags: ['Python', '연애'],
					nickname: '귀도 반 로썸',
					id: 'JSmaster',
					profile:
						'https://mblogthumb-phinf.pstatic.net/MjAxODEwMTRfMjMw/MDAxNTM5NTI0MDA4NTU1.lqwYXl0waT8to2HT3ZvKZf1MpOZJvtkDj64qC2cfI6sg.xbisRBnM7Q6Kdz-lwNzYiz_lQGPpnPrELpr82NaMIngg.JPEG.rhksdlr134/GuidoAvatar_400x400.jpg?type=w800',
				},
				{
					title: 'C를 마스터해보자',
					viewer: 50,
					like: 1000,
					thumbnail:
						'https://cdn.inflearn.com/wp-content/uploads/c002.jpg',
					tags: ['C'],
					nickname: '토발즈',
					id: 'windows',
					profile:
						'https://upload.wikimedia.org/wikipedia/commons/0/01/LinuxCon_Europe_Linus_Torvalds_03_%28cropped%29.jpg',
				},
				{
					title: '알고리즘 심화 강의',
					viewer: 50,
					like: 1000,
					thumbnail:
						'https://cdn.inflearn.com/public/course-325806-cover/0e0d07a7-840c-4d01-907c-626e9d6b97d5',
					tags: ['알고리즘', '수학', 'Python'],
					nickname: '안성민',
					id: 'algorithmBoy',
					profile:
						'https://post-phinf.pstatic.net/MjAxOTA5MDVfMTU4/MDAxNTY3Njc2NDkyNTUz.yxnxow6Ff5DXIRAHGcOgNUvkV2J_Nh6WttPIi0aEk_kg.rORnLOgHe8wp3_D_UsEGapDaUtlv3JrZvFA6CalCJPEg.JPEG/%EB%AF%B8%EC%B3%A4%EC%8A%B5%EB%8B%88%EA%B9%8C_%ED%9C%B4%EB%A8%BC.jpg?type=w1200',
				},
			],
			hit: [
				{
					title: 'Python으로 여자친구만들기',
					viewer: 50,
					like: 1000,
					thumbnail:
						'https://cdn.inflearn.com/wp-content/uploads/python-2.jpg',
					tags: ['Python', '연애'],
					nickname: '귀도 반 로썸',
					id: 'JSmaster',
					profile:
						'https://mblogthumb-phinf.pstatic.net/MjAxODEwMTRfMjMw/MDAxNTM5NTI0MDA4NTU1.lqwYXl0waT8to2HT3ZvKZf1MpOZJvtkDj64qC2cfI6sg.xbisRBnM7Q6Kdz-lwNzYiz_lQGPpnPrELpr82NaMIngg.JPEG.rhksdlr134/GuidoAvatar_400x400.jpg?type=w800',
				},
				{
					title: 'Python으로 여자친구만들기',
					viewer: 50,
					like: 1000,
					thumbnail:
						'https://cdn.inflearn.com/wp-content/uploads/python-2.jpg',
					tags: ['Python', '연애'],
					nickname: '귀도 반 로썸',
					id: 'JSmaster',
					profile:
						'https://mblogthumb-phinf.pstatic.net/MjAxODEwMTRfMjMw/MDAxNTM5NTI0MDA4NTU1.lqwYXl0waT8to2HT3ZvKZf1MpOZJvtkDj64qC2cfI6sg.xbisRBnM7Q6Kdz-lwNzYiz_lQGPpnPrELpr82NaMIngg.JPEG.rhksdlr134/GuidoAvatar_400x400.jpg?type=w800',
				},
				{
					title: 'Python으로 여자친구만들기',
					viewer: 50,
					like: 1000,
					thumbnail:
						'https://cdn.inflearn.com/wp-content/uploads/python-2.jpg',
					tags: ['Python', '연애'],
					nickname: '귀도 반 로썸',
					id: 'JSmaster',
					profile:
						'https://mblogthumb-phinf.pstatic.net/MjAxODEwMTRfMjMw/MDAxNTM5NTI0MDA4NTU1.lqwYXl0waT8to2HT3ZvKZf1MpOZJvtkDj64qC2cfI6sg.xbisRBnM7Q6Kdz-lwNzYiz_lQGPpnPrELpr82NaMIngg.JPEG.rhksdlr134/GuidoAvatar_400x400.jpg?type=w800',
				},
				{
					title: 'Python으로 여자친구만들기',
					viewer: 50,
					like: 1000,
					thumbnail:
						'https://cdn.inflearn.com/wp-content/uploads/python-2.jpg',
					tags: ['Python', '연애'],
					nickname: '귀도 반 로썸',
					id: 'JSmaster',
					profile:
						'https://mblogthumb-phinf.pstatic.net/MjAxODEwMTRfMjMw/MDAxNTM5NTI0MDA4NTU1.lqwYXl0waT8to2HT3ZvKZf1MpOZJvtkDj64qC2cfI6sg.xbisRBnM7Q6Kdz-lwNzYiz_lQGPpnPrELpr82NaMIngg.JPEG.rhksdlr134/GuidoAvatar_400x400.jpg?type=w800',
				},
			],
		};
	},
};
</script>

<style scoped>
.container {
	max-width: 1440px;
}

.card-col {
	padding: 2%;
}

.v-image:hover {
	cursor: pointer;
}

.link:hover {
	color: purple;
	cursor: pointer;
}
</style>
