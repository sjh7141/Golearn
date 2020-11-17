<template>
	<div>
		<div style="font-size:18px;" class="mb-5">
			<span> 댓글 </span>
			<span class="basic" style="font-weight:600">
				{{ numCmt }}
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
						<v-img :src="item.mbr_profile" />
					</v-avatar>
					<div class="my-5">
						<div>
							<span style="font-size:13px; font-weight:700;">
								{{ item.mbr_nickname }}
								<span
									style="font-size:12px; font-weight:400; color:#979797;"
								>
									&middot;
									{{
										item.reg_date
											| moment('YYYY-MM-DD hh:mm:ss')
									}}
								</span>
								<span
									class="ml-2"
									v-if="isLogin && item.mbr_no == user.no"
									style="font-size:12px; font-weight:400; color:#979797; cursor: pointer;"
									@click="deleteBoardComment(item)"
								>
									삭제
								</span>
							</span>
						</div>
						<div>
							<span style="font-size:13px; font-weight:400;">
								{{ item.comment }}
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
								@click="getBoardSubComments(item)"
							>
								<span class="black--text">
									답글
									<span
										class="basic"
										style="font-size:14px;"
										v-show="item.child_count"
									>
										{{ item.child_count }}
									</span>
								</span>
								<span
									v-show="!item.child_count"
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
									<v-img :src="child.mbr_profile" />
								</v-avatar>
								<div style="float:left;" class="my-5">
									<div>
										<span
											style="font-size:13px; font-weight:700;"
										>
											{{ child.mbr_nickname }}
											<span
												style="font-size:12px; font-weight:400; color:#979797;"
											>
												&middot;
												{{
													child.reg_date
														| moment(
															'YYYY-MM-DD hh:mm:ss',
														)
												}}
											</span>
											<span
												class="ml-2"
												v-if="
													isLogin &&
														child.mbr_no == user.no
												"
												style="font-size:12px; font-weight:400; color:#979797; cursor: pointer;"
												@click="
													deleteBoardComment(child)
												"
											>
												삭제
											</span>
										</span>
									</div>
									<div>
										<span
											style="font-size:13px; font-weight:400;"
										>
											{{ child.comment }}
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
			<infinite-loading @infinite="infiniteHandler">
				<div slot="no-more"></div>
				<div slot="no-results"></div>
			</infinite-loading>
		</v-card>
	</div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
import InfiniteLoading from 'vue-infinite-loading';

export default {
	name: 'BoardComment',
	props: ['no', 'numCmt'],
	components: {
		InfiniteLoading,
	},
	watch: {
		no() {
			this.getBoardComments();
		},
	},
	computed: {
		...mapGetters(['isLogin']),
	},
	data() {
		return {
			replyText: '',
			reply: [],
			user: {},
			page: 1,
		};
	},
	mounted() {
		this.getBoardComments();
		this.user = this.$store.getters.user;
	},
	methods: {
		...mapActions([
			'_getBoardComments',
			'_getBoardSubComments',
			'_writeBoardComment',
			'_deleteBoardComment',
		]),

		getBoardComments() {
			this._getBoardComments({
				brd_no: this.no,
				page_no: this.page,
			}).then(({ data }) => {
				this.reply = [];
				for (let i in data.comment) {
					data.comment[i].replyText = '';
					data.comment[i].active = false;
					data.comment[i].child = [];
					data.comment[i].mbr_nickname = '';
					data.comment[i].mbr_profile = '';
					let user = data.comment[i];
					this.$store
						.dispatch('getUserByNo', user.mbr_no)
						.then(({ data }) => {
							user.mbr_nickname = data.nickname;
							user.mbr_profile = data.profile;
						});
				}
				this.reply = data.comment;
			});
		},

		writeComment() {
			this._writeBoardComment({
				brd_no: this.no,
				comment: this.replyText,
				parent_no: 0,
			}).then(() => {
				this.getBoardComments();
				this.replyText = '';
			});
		},

		getBoardSubComments(item) {
			item.active = !item.active;
			if (item.active) {
				this._getBoardSubComments({
					brd_no: this.no,
					brd_cmt_pno: item.cmt_no,
				}).then(({ data }) => {
					for (let i in data.comment) {
						data.comment[i].mbr_nickname = '';
						data.comment[i].mbr_profile = '';
						let user = data.comment[i];
						this.$store
							.dispatch('getUserByNo', user.mbr_no)
							.then(({ data }) => {
								user.mbr_nickname = data.nickname;
								user.mbr_profile = data.profile;
							});
					}
					item.child = data.comment;
				});
			} else {
				item.child = [];
			}
		},

		writeSubComment(item) {
			this._writeBoardComment({
				brd_no: this.no,
				comment: item.replyText,
				parent_no: item.cmt_no,
			}).then(() => {
				this.getBoardComments(item);
			});
		},

		deleteBoardComment(item) {
			this._deleteBoardComment({ cmt_no: item.cmt_no }).then(() => {
				this.getBoardComments();
			});
		},

		disableFocus() {
			const qs = document.querySelector('.v-input--is-focused');
			if (qs) {
				qs.classList.toggle('v-input--is-focused');
			}
		},

		infiniteHandler($state) {
			this._getBoardComments({
				brd_no: this.no,
				page_no: this.page + 1,
			}).then(({ data }) => {
				if (data.comment.length > 0) {
					for (let i in data.comment) {
						data.comment[i].replyText = '';
						data.comment[i].active = false;
						data.comment[i].child = [];
						data.comment[i].mbr_nickname = '';
						data.comment[i].mbr_profile = '';
						let user = data.comment[i];
						this.$store
							.dispatch('getUserByNo', user.mbr_no)
							.then(({ data }) => {
								user.mbr_nickname = data.nickname;
								user.mbr_profile = data.profile;
							});
					}
					for (let comment of data.comment) {
						this.reply.push(comment);
					}
					$state.loaded();
					this.page += 1;
				} else {
					$state.complete();
				}
			});
		},
	},
};
</script>

<style></style>
