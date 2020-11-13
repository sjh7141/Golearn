<template>
	<div>
		<div style="font-size:18px;" class="mb-5">
			<span> 댓글 </span>
			<span class="basic" style="font-weight:600"> 3 </span>
		</div>

		<v-avatar size="48" style="float:left;" class="mr-5">
			<v-img src="@/assets/default_profile.png" />
		</v-avatar>

		<v-textarea
			v-model="replyText"
			no-resize
			outlined
			auto-grow
			flat
			rows="5"
			color="#8e8e8e"
			@click="disableFocus"
			placeholder="내용을 입력해주세요."
			style="border-radius:1px;"
		>
			<template slot="append">
				<v-btn
					absolute
					right
					bottom
					depressed
					outlined
					tile
					:color="replyText.length ? '#9382D7' : '#e4e4e4'"
					style="font-size:14px;"
				>
					등록
				</v-btn>
			</template>
		</v-textarea>

		<v-card shaped outlined tile color="rgb(249,249,255)" class="mb-10">
			<div
				v-for="(item, i) in reply"
				:key="`reply_${i}`"
				style="clear: both;"
			>
				<div style="width:100%;">
					<v-avatar size="48" style="float:left;" class="mx-5">
						<v-img src="@/assets/default_profile.png" />
					</v-avatar>
					<div class="my-5">
						<div>
							<span style="font-size:13px; font-weight:700;">
								{{ item.nickname }}
								<span
									style="font-size:12px; font-weight:400; color:#979797;"
								>
									&middot; {{ item.date }}
								</span>
							</span>
						</div>
						<div>
							<span style="font-size:13px; font-weight:400;">
								{{ item.content }}
							</span>
						</div>
						<div class="mt-5" style="margin-left:88px;">
							<v-btn
								outlined
								depressed
								color="#e4e4e4"
								tile
								class="px-3"
								style="font-size:13px;"
								@click="item.active = !item.active"
							>
								<span class="black--text">
									답글
									<span
										class="basic"
										style="font-size:14px;"
										v-show="item.count"
									>
										{{ item.count }}
									</span>
								</span>
								<span v-show="!item.count" class="black--text">
									쓰기
								</span>
								<v-icon class="ma-0 pa-0" color="black">
									{{
										item.active
											? 'mdi-chevron-up'
											: 'mdi-chevron-down'
									}}
								</v-icon>
							</v-btn>
						</div>
						<div v-show="item.active">
							<div
								v-for="(child, j) in item.child"
								:key="`child_${j}`"
								class="mt-5 pl-16 pr-6"
								style="clear: both;"
							>
								<v-divider class="mt-5" style="clear: both;" />
								<v-avatar
									size="48"
									style="float:left;"
									class="mx-5 my-5"
								>
									<v-img src="@/assets/default_profile.png" />
								</v-avatar>
								<div style="float:left;" class="my-5">
									<div>
										<span
											style="font-size:13px; font-weight:700;"
										>
											{{ child.nickname }}
											<span
												style="font-size:12px; font-weight:400; color:#979797;"
											>
												&middot; {{ child.date }}
											</span>
										</span>
									</div>
									<div>
										<span
											style="font-size:13px; font-weight:400;"
										>
											{{ child.content }}
										</span>
									</div>
								</div>
							</div>

							<div class="pl-16 pr-6">
								<v-divider class="my-5" style="clear: both;" />
								<v-avatar
									size="48"
									style="float:left;"
									class="mx-5"
								>
									<v-img src="@/assets/default_profile.png" />
								</v-avatar>

								<v-textarea
									v-model="item.replyText"
									no-resize
									outlined
									auto-grow
									tile
									flat
									rows="5"
									color="#8e8e8e"
									placeholder="내용을 입력해주세요."
									@click="disableFocus"
									style="border-radius:1px;"
								>
									<template slot="append">
										<v-btn
											absolute
											right
											bottom
											depressed
											outlined
											tile
											:color="
												item.replyText.length
													? '#9382D7'
													: '#e4e4e4'
											"
											style="font-size:14px;"
										>
											등록
										</v-btn>
									</template>
								</v-textarea>
							</div>
						</div>
					</div>
				</div>
				<v-divider style="clear: both;" />
			</div>
		</v-card>
	</div>
</template>

<script>
export default {
	name: 'VideoComment',
	data() {
		return {
			replyText: '',
			reply: [
				{
					profile: '@/assets/default_profile.png',
					nickname: '미용쓰기',
					date: '2020-10-29 10:16:06',
					content: '좋은 정보 감사합니다.',
					count: 3,
					child: [
						{
							profile: '@/assets/default_profile.png',
							nickname: '미용쓰기',
							date: '2020-10-29 10:16:06',
							content: '좋은 정보 감사합니다.',
						},
						{
							profile: '@/assets/default_profile.png',
							nickname: 'asm9677',
							date: '2020-10-29 11:52:32',
							content: '좋아요',
						},
						{
							profile: '@/assets/default_profile.png',
							nickname: 'tlagyqls7',
							content: '굿',
							date: '2020-10-29 13:44:08 ',
						},
					],
					replyText: '',
					active: false,
				},
				{
					profile: '@/assets/default_profile.png',
					nickname: 'asm9677',
					date: '2020-10-29 11:52:32',
					content: '좋아요',
					count: 0,
					replyText: '',
					active: false,
				},
				{
					profile: '@/assets/default_profile.png',
					nickname: 'tlagyqls7',
					content: '굿',
					date: '2020-10-29 13:44:08 ',
					count: 0,
					replyText: '',
					active: false,
				},
			],
		};
	},
	mounted() {},
	methods: {
		disableFocus() {
			const qs = document.querySelector('.v-input--is-focused');
			if (qs) {
				qs.classList.toggle('v-input--is-focused');
			}
		},
	},
};
</script>

<style>
.v-input--is-focused {
	/* border: 1px solid grey !important; */
}
</style>
