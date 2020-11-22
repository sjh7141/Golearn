<template>
	<div id="wrapper">
		<v-row class="pt-15" justify="center">
			<v-col
				cols="6"
				class="tap"
				:class="{ select: select == 2 }"
				@click="goToPrivacy"
			>
				개인정보처리방침
			</v-col>
			<v-col
				cols="6"
				class="tap"
				:class="{ select: select == 1 }"
				@click="goToTerms"
			>
				이용약관
			</v-col>
		</v-row>
		<div id="content" class="pb-15">
			<div style="text-align:center;">
				<span class="title-text">
					고런고런 {{ select == 1 ? '이용약관' : '개인정보처리방침' }}
				</span>
			</div>
			<v-divider class="my-15" />
			<div>
				<private-policy v-if="select == 1" />
				<terms v-else />
			</div>
		</div>
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
			select: 2,
		};
	},
	mounted() {
		if (this.$route.name == 'Terms') {
			this.select = 1;
		}
	},
	methods: {
		goToTerms() {
			if (this.select != 1) {
				this.$router.push('/terms');
			}
		},
		goToPrivacy() {
			if (this.select != 2) {
				this.$router.push('/privacy');
			}
		},
	},
};
</script>

<style scoped>
#wrapper {
	max-width: 1020px;
	min-height: 800px;
	margin: 0 auto;
}
#content {
	margin-top: 100px;
}
.tap {
	display: inline-block;
	text-align: center;
	cursor: pointer;
}
.select {
	background-color: #000000;
	color: white;
}
.title-text {
	font-size: 1.8rem;
	font-weight: 600;
}
</style>
