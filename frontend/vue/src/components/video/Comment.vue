<template>
	<div>
		<div style="font-size:18px;" class="mb-5">
			<span> 댓글 </span>
			<span class="basic" style="font-weight:600">
				{{ reply.length }}
			</span>
		</div>
		<div v-if="$store.getters.isLogin">
			<v-avatar size="48" style="float:left;" class="mr-5">
				<v-img
					:src="
						user
							? user.profile
							: 'https://go-learn.s3.ap-northeast-2.amazonaws.com/member/profile/profile_default1.png'
					"
				/>
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
						@click="replyText.length ? writeComment() : null"
					>
						등록
					</v-btn>
				</template>
			</v-textarea>
		</div>

		<v-card shaped outlined tile color="rgb(249,249,255)" class="mb-10">
			<div
				v-for="(item, i) in reply"
				:key="`reply_${i}`"
				style="clear: both;"
			>
				<div style="width:100%;">
					<v-avatar size="48" style="float:left;" class="mx-5">
						<v-img :src="item.member.mbr_profile" />
					</v-avatar>
					<div class="my-5">
						<div>
							<span style="font-size:13px; font-weight:700;">
								{{ item.member.mbr_nickname }}
								<span
									style="font-size:12px; font-weight:400; color:#979797;"
								>
									&middot;
									{{
										item.reg_dt
											| moment('YYYY-MM-DD hh:mm:ss')
									}}
								</span>
							</span>
						</div>
						<div>
							<span style="font-size:13px; font-weight:400;">
								{{ item.vid_comment }}
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
								@click="getVideoSubComments(item)"
							>
								<span class="black--text">
									답글
									<span
										class="basic"
										style="font-size:14px;"
										v-show="item.num_of_reply"
									>
										{{ item.num_of_reply }}
									</span>
								</span>
								<span
									v-show="!item.num_of_reply"
									class="black--text"
								>
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
						<div v-if="item.active">
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
									<v-img :src="child.member.mbr_profile" />
								</v-avatar>
								<div style="float:left;" class="my-5">
									<div>
										<span
											style="font-size:13px; font-weight:700;"
										>
											{{ child.member.mbr_nickname }}
											<span
												style="font-size:12px; font-weight:400; color:#979797;"
											>
												&middot;
												{{
													child.reg_dt
														| moment(
															'YYYY-MM-DD hh:mm:ss',
														)
												}}
											</span>
										</span>
									</div>
									<div>
										<span
											style="font-size:13px; font-weight:400;"
										>
											{{ child.vid_comment }}
										</span>
									</div>
								</div>
							</div>

							<div
								class="pl-16 pr-6"
								v-if="$store.getters.isLogin"
							>
								<v-divider class="my-5" style="clear: both;" />
								<v-avatar
									size="48"
									style="float:left;"
									class="mx-5"
								>
									<v-img
										:src="
											user
												? user.profile
												: 'https://go-learn.s3.ap-northeast-2.amazonaws.com/member/profile/profile_default1.png'
										"
									/>
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
											@click="writeSubComment(item)"
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
import { mapActions } from 'vuex';
export default {
	name: 'VideoComment',
	props: ['no'],
	watch: {
		no() {
			this.getVideoComments();
		},
	},
	data() {
		return {
			replyText: '',
			reply: [],
			user: {},
		};
	},
	mounted() {
		this.getVideoComments();
		this.user = this.$store.getters.user;
	},
	methods: {
		...mapActions([
			'_getVideoComments',
			'_getVideoSubComments',
			'_writeComment',
			'_writeSubComment',
		]),

		getVideoComments() {
			this._getVideoComments(this.no).then(({ data }) => {
				for (let i in data) {
					data[i].replyText = '';
					data[i].active = false;
					data[i].child = [];
				}
				this.reply = data;
			});
		},

		writeComment() {
			this._writeComment({
				vid_no: this.no,
				vid_comment: this.replyText,
			}).then(() => {
				this.getVideoComments();
				this.replyText = '';
			});
		},

		getVideoSubComments(item) {
			item.active = !item.active;

			this._getVideoSubComments({
				vid_no: this.no,
				vid_cmt_pno: item.vid_cmt_no,
			}).then(({ data }) => {
				item.child = data;
			});
		},

		writeSubComment(item) {
			this._writeSubComment({
				vid_no: this.no,
				vid_cmt_pno: item.vid_cmt_no,
				vid_comment: item.replyText,
			}).then(() => {
				this.getVideoComments(item);
			});
		},

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
