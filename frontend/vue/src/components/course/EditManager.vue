<template>
	<v-row class="ml-3 height-100">
		<v-col cols="9">
			<h2>관리자</h2>
			<h4 class="pb-5 pl-3" style="color:gray;">
				"코스를 관리할 관리자를 설정할 수 있습니다."
			</h4>
			<div class="mt-5">
				<div class="pa-6 mb-6 bg-light-gray border-radius-10">
					<div class="bold">
						주의사항
					</div>
					<ul style="list-style-type: none;">
						<li>
							관리자는 해당 코스에 대한
							<span class="bold">관리권한</span>과
							<span class="bold">알림</span>을 수신할 수 있습니다.
						</li>
						<li class="pb-4">
							충분한 상의를 거친 뒤
							<span class="bold">인증된 사용자</span>에게 관리자
							권한을 부여해 주세요.
						</li>
						<li>
							고런고런에서의 활동으로 당신만의
							<span class="bold">브랜드</span>를 만들어가세요. :)
						</li>
					</ul>
				</div>
				<v-divider class="pb-8"></v-divider>
				<div>
					<div class="pb-8" style="text-align:end;">
						<v-btn outlined class="add-btn" @click="isAdd = true">
							<v-icon color="darken-3">
								mdi-plus
							</v-icon>
							<span style="font-size:15px;">관리자 추가</span>
						</v-btn>
					</div>
					<v-row>
						<v-col cols="12">
							<v-simple-table>
								<template v-slot:default>
									<thead>
										<tr>
											<th class="text-left">
												이름
											</th>
											<th class="text-left">
												이메일
											</th>
											<th class="text-left">
												권한
											</th>
											<th class="text-left">
												관리자 설정일
											</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<tr
											v-for="(item, index) in manager"
											:key="item.mbr_no"
											style="height:70px;"
										>
											<td class="bold">
												<v-avatar
													class="mr-5"
													style="vertical-align:middle;"
												>
													<img
														src="@/assets/default_profile.png"
														alt="프로필"
													/>
												</v-avatar>
												{{ item.mbr_nickname }}
												(@{{ item.mbr_id }})
											</td>
											<td>{{ item.mbr_email }}</td>
											<td
												class="bold"
												:style="{
													color:
														item.authority ==
														'마스터'
															? '#2991ff'
															: '',
												}"
											>
												{{ item.authority }}
											</td>
											<td>{{ item.regist_date }}</td>
											<td>
												<v-icon
													v-if="
														item.authority !=
															'마스터'
													"
													class="pointer"
													color="darken-2"
													@click="
														setDeleteIndex(index)
													"
												>
													mdi-trash-can-outline
												</v-icon>
											</td>
										</tr>
									</tbody>
								</template>
							</v-simple-table>
						</v-col>
					</v-row>
				</div>
			</div>
			<div class="mt-6" style="text-align:end;">
				<v-btn
					outlined
					class="mr-3"
					style="border: 1px solid #c9c9c9;"
					@click="saveManager"
				>
					저장
				</v-btn>
				<v-btn dark color="#5500ff" @click="changeActive">
					다음
				</v-btn>
			</div>
			<!-- 추가 dialog -->
			<v-dialog v-model="isAdd" max-width="600">
				<v-card>
					<v-card-title class="headline pb-6">
						<span class="bold">
							관리자 추가
						</span>
					</v-card-title>
					<div class="bold px-6 pb-2">사용자목록</div>
					<v-autocomplete
						v-model="candidate"
						:items="people"
						filled
						chips
						multiple
						color="blue-grey lighten-2"
						item-text="mbr_nickname"
						return-object
						class="mx-3"
					>
						<template v-slot:selection="data">
							<v-chip
								v-bind="data.attrs"
								:input-value="data.selected"
								close
								@click="data.select"
								@click:close="remove(data.item)"
							>
								<v-avatar left>
									<v-img src="@/assets/default_profile.png" />
									<!-- <v-img :src="data.item.avatar"></v-img> -->
								</v-avatar>
								{{ data.item.mbr_nickname }}
							</v-chip>
						</template>
						<template v-slot:item="data">
							<template v-if="typeof data.item !== 'object'">
								<v-list-item-content
									v-text="data.item"
								></v-list-item-content>
							</template>
							<template v-else>
								<v-list-item-avatar>
									<!-- <img :src="data.item.avatar" /> -->
									<img src="@/assets/default_profile.png" />
								</v-list-item-avatar>
								<v-list-item-content>
									<v-list-item-title
										v-html="
											`${data.item.mbr_nickname} (@${data.item.mbr_id})`
										"
									></v-list-item-title>
									<v-list-item-subtitle
										v-html="data.item.mbr_email"
									></v-list-item-subtitle>
								</v-list-item-content>
							</template>
						</template>
					</v-autocomplete>
					<v-card-actions>
						<v-spacer></v-spacer>
						<v-btn
							color="error darken-1"
							text
							@click="isAdd = false"
						>
							취소
						</v-btn>
						<v-btn color="darken-1" text @click="addManager()">
							확인
						</v-btn>
					</v-card-actions>
				</v-card>
			</v-dialog>
			<!-- 삭제 dialog -->
			<v-dialog v-model="isDelete" max-width="400">
				<v-card>
					<v-card-title class="headline">
						<span class="bold">정말 삭제하시겠습니까?</span>
					</v-card-title>
					<v-card-text>
						해당 관리자는 관리자로서의 권한을 잃게됩니다.
					</v-card-text>
					<v-card-actions>
						<v-spacer></v-spacer>
						<v-btn
							color="error darken-1"
							text
							@click="isDelete = false"
						>
							취소
						</v-btn>
						<v-btn color="darken-1" text @click="confirmDelete">
							확인
						</v-btn>
					</v-card-actions>
				</v-card>
			</v-dialog>
		</v-col>
		<v-col cols="3">
			<div></div>
		</v-col>
	</v-row>
</template>

<script>
export default {
	data() {
		return {
			manager: [
				{
					mbr_nickname: '김길동',
					mbr_id: 'go1234',
					mbr_no: 122,
					mbr_profile: 1,
					mbr_email: 'gildong@gmail.com',
					authority: '마스터',
					ck_email: 1,
					regist_date: '19/01/24 14:30',
				},
				{
					mbr_nickname: '코런코런코런코런코런',
					mbr_id: 'go12345',
					mbr_no: 232,
					mbr_profile: 2,
					mbr_email: 'gildong@gmail.com',
					authority: '매니저',
					ck_email: 1,
					regist_date: '19/01/24 14:30',
				},
				{
					mbr_nickname: '김길동1',
					mbr_id: 'go123456',
					mbr_no: 123,
					mbr_profile: 1,
					mbr_email: 'gildong@gmail.com',
					authority: '매니저',
					ck_email: 1,
					regist_date: '19/01/24 14:30',
				},
				{
					mbr_nickname: '코런코런1',
					mbr_id: 'la1234',
					mbr_no: 234,
					mbr_profile: 2,
					mbr_email: 'gildong@gmail.com',
					authority: '매니저',
					ck_email: 1,
					regist_date: '19/01/24 14:30',
				},
				{
					mbr_nickname: '김길동5',
					mbr_id: 'la12345',
					mbr_no: 125,
					mbr_profile: 1,
					mbr_email: 'gildong@gmail.com',
					authority: '매니저',
					ck_email: 1,
					regist_date: '19/01/24 14:30',
				},
				{
					mbr_nickname: '코런코런123',
					mbr_id: 'la123456',
					mbr_no: 2321,
					mbr_profile: 2,
					mbr_email: 'gildong@gmail.com',
					authority: '매니저',
					ck_email: 1,
					regist_date: '19/01/24 14:30',
				},
				{
					mbr_nickname: '김길동6',
					mbr_id: 'da1234',
					mbr_no: 1226,
					mbr_profile: 1,
					mbr_email: 'gildong@gmail.com',
					authority: '매니저',
					ck_email: 1,
					regist_date: '19/01/24 14:30',
				},
				{
					mbr_nickname: '코런코런6',
					mbr_id: 'da12345',
					mbr_no: 23216,
					mbr_profile: 2,
					mbr_email: 'gildong@gmail.com',
					authority: '매니저',
					ck_email: 1,
					regist_date: '19/01/24 14:30',
				},
			],
			isDelete: false,
			deleteIdx: -1,
			isAdd: false,
			people: [
				{
					mbr_nickname: '마이',
					mbr_id: 'pa1234',
					mbr_no: 1221111111,
					mbr_profile: 1,
					mbr_email: 'gildong@gmail.com',
					authority: '매니저',
					ck_email: 1,
					regist_date: '19/01/24 14:30',
				},
				{
					mbr_nickname: '구미',
					mbr_id: 'pa12345',
					mbr_no: 232222222222222,
					mbr_profile: 2,
					mbr_email: 'gildong@gmail.com',
					authority: '매니저',
					ck_email: 1,
					regist_date: '19/01/24 14:30',
				},
				{
					mbr_nickname: '복숭',
					mbr_id: 'gpa123456',
					mbr_no: 12333333333,
					mbr_profile: 1,
					mbr_email: 'gildong@gmail.com',
					authority: '매니저',
					ck_email: 1,
					regist_date: '19/01/24 14:30',
				},
				{
					mbr_nickname: '아맛',
					mbr_id: 'lapa1234',
					mbr_no: 234444444444,
					mbr_profile: 2,
					mbr_email: 'gildong@gmail.com',
					authority: '매니저',
					ck_email: 1,
					regist_date: '19/01/24 14:30',
				},
				{
					mbr_nickname: '딸기맛',
					mbr_id: 'lapa12345',
					mbr_no: 12555555555555,
					mbr_profile: 1,
					mbr_email: 'gildong@gmail.com',
					authority: '매니저',
					ck_email: 1,
					regist_date: '19/01/24 14:30',
				},
				{
					mbr_nickname: '왕꿈틀이',
					mbr_id: 'lpa123456',
					mbr_no: 23211111111,
					mbr_profile: 2,
					mbr_email: 'gildong@gmail.com',
					authority: '매니저',
					ck_email: 1,
					regist_date: '19/01/24 14:30',
				},
				{
					mbr_nickname: '젤리',
					mbr_id: 'dpa1234',
					mbr_no: 12266666666666,
					mbr_profile: 1,
					mbr_email: 'gildong@gmail.com',
					authority: '매니저',
					ck_email: 1,
					regist_date: '19/01/24 14:30',
				},
				{
					mbr_nickname: '대왕고래',
					mbr_id: 'dpa12345',
					mbr_no: 232162222222222,
					mbr_profile: 2,
					mbr_email: 'gildong@gmail.com',
					authority: '매니저',
					ck_email: 1,
					regist_date: '19/01/24 14:30',
				},
			],
			candidate: [],
		};
	},
	methods: {
		setAdd() {},
		setDeleteIndex(idx) {
			this.isDelete = true;
			this.deleteIdx = idx;
		},
		confirmDelete() {
			this.manager.splice(this.deleteIdx, 1);
			this.deleteIdx = -1;
			this.isDelete = false;
		},
		remove(item) {
			const index = this.candidate.indexOf(item.mbr_nickname);
			if (index >= 0) this.candidate.splice(index, 1);
		},
		changeActive() {
			this.$emit('changeActive');
		},
		addManager() {
			for (var people of this.candidate) {
				this.manager.push(people);
			}
			this.isAdd = false;
		},
		saveManager() {},
	},
};
</script>

<style scoped>
.manager-box {
	border: 1px solid #8c94ff;
	border-radius: 10px;
}

.add-btn {
	border: 1px solid #c9c9c9;
	font-weight: 600;
}
</style>
