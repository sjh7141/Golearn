<template>
	<div>
		<div style="width:100%;">
			<v-tabs
				v-model="tabs"
				color="black"
				slider-color="rgb(60, 65, 223)"
				dense
				style="border-top:1px solid rgba(0,0,0,0.13);"
			>
				<v-tab>
					Open
				</v-tab>

				<v-tab>
					Merged
				</v-tab>

				<v-tab>
					Closed
				</v-tab>

				<v-tab>
					All
				</v-tab>

				<v-btn
					depressed
					style="position:absolute; right:0; margin-top:6px"
					color="success"
					@click="$emit('move', '/request', 2)"
				>
					<span style="font-size:14px; font-weight:400">
						New pull request
					</span>
				</v-btn>
			</v-tabs>
			<v-tabs-items v-model="tabs">
				<v-tab-item v-for="i in 4" :key="`item_${i}`">
					<v-list
						class="pa-0 ma-0"
						v-for="(item, j) in items"
						:key="`list_${j}`"
						style="background-color:#fafafa"
					>
						<v-divider />
						<v-list-item @click="$emit('move', '/request/1', -1)">
							<v-list-item-avatar>
								<v-img
									:src="
										`https://picsum.photos/500/300?image=${j *
											5 +
											10}`
									"
								/>
							</v-list-item-avatar>
							<v-list-item-content>
								<v-list-item-title>
									{{ item.title }}
								</v-list-item-title>
								<v-list-item-subtitle>
									{{ item.subtitle }}
								</v-list-item-subtitle>
							</v-list-item-content>
							<v-list-item-action>
								<v-icon v-if="item.flag == 1" color="success">
									mdi-check
								</v-icon>
								<v-icon
									v-else-if="item.flag == 2"
									color="error"
								>
									mdi-close
								</v-icon>
							</v-list-item-action>
						</v-list-item>
					</v-list>
				</v-tab-item>
			</v-tabs-items>
		</div>
	</div>
</template>

<script>
export default {
	name: 'RequestList',
	data() {
		return {
			tabs: 0,
			items: [
				{
					title: 'modify : course 불러오기',
					subtitle: 'opened 4 hours ago by 김현수',
					flag: 1,
				},
				{
					title: 'modify : 회원가입 수정',
					subtitle: 'opened 6 hours ago by 김현수',
					flag: 0,
				},
				{
					title: 'feat: 회원 계정 관련 구현 완료',
					subtitle: 'opened 22 hours ago by 김명석',
					flag: 0,
				},
				{
					title: 'modify : token 수정',
					subtitle: 'opened 2 days ago by 김현수',
					flag: 0,
				},
				{
					title: 'modify : course 불러오기',
					subtitle: 'opened 4 hours ago by 김현수',
					flag: 0,
				},
				{
					title: 'modify : 회원가입 수정',
					subtitle: 'opened 6 hours ago by 김현수',
					flag: 0,
				},
				{
					title: 'feat: 회원 계정 관련 구현 완료',
					subtitle: 'opened 22 hours ago by 김명석',
					flag: 0,
				},
				{
					title: 'modify : token 수정',
					subtitle: 'opened 2 days ago by 김현수',
					flag: 1,
				},
				{
					title: 'modify : course 불러오기',
					subtitle: 'opened 4 hours ago by 김현수',
					flag: 2,
				},
				{
					title: 'modify : 회원가입 수정',
					subtitle: 'opened 6 hours ago by 김현수',
					flag: 0,
				},
				{
					title: 'feat: 회원 계정 관련 구현 완료',
					subtitle: 'opened 22 hours ago by 김명석',
					flag: 0,
				},
				{
					title: 'modify : token 수정',
					subtitle: 'opened 2 days ago by 김현수',
					flag: 0,
				},
				{
					title: 'modify : course 불러오기',
					subtitle: 'opened 4 hours ago by 김현수',
					flag: 0,
				},
				{
					title: 'modify : 회원가입 수정',
					subtitle: 'opened 6 hours ago by 김현수',
					flag: 2,
				},
				{
					title: 'feat: 회원 계정 관련 구현 완료',
					subtitle: 'opened 22 hours ago by 김명석',
					flag: 1,
				},
				{
					title: 'modify : token 수정',
					subtitle: 'opened 2 days ago by 김현수',
					flag: 1,
				},
				{
					title: 'modify : course 불러오기',
					subtitle: 'opened 4 hours ago by 김현수',
					flag: 1,
				},
				{
					title: 'modify : 회원가입 수정',
					subtitle: 'opened 6 hours ago by 김현수',
					flag: 1,
				},
				{
					title: 'feat: 회원 계정 관련 구현 완료',
					subtitle: 'opened 22 hours ago by 김명석',
					flag: 1,
				},
				{
					title: 'modify : token 수정',
					subtitle: 'opened 2 days ago by 김현수',
					flag: 0,
				},
			],
		};
	},
};
</script>

<style scoped>
.v-tab,
.v-btn {
	text-transform: none;
}
</style>
