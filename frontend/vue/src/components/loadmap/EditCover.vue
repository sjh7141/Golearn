<template>
	<v-row id="loadmap-cover" class="ml-3 height-100">
		<v-col cols="9">
			<h2>이미지</h2>
			<h4 class="pb-5 pl-3" style="color:gray;">
				"커버 이미지(썸네일)와 배너를 등록 하실 수 있습니다."
			</h4>
			<div justify="center" class="mt-5">
				<div class="label">
					<h4>이미지 업로드</h4>
				</div>
				<div class="pa-6 mb-6 bg-light-gray border-radius-10">
					<div class="bold">
						주의사항
					</div>
					<ol>
						<li>
							커버 이미지(썸네일)를 직접 제작하실 경우,
							<span class="bold">768 × 500(px)</span>에, <br />
							배너 이미지는
							<span class="bold">1920 x 720(px)</span>에
							맞춰주세요. 가로형 이미지에 최적화되어있습니다.
						</li>
						<li>
							이미지가 규정에 맞지 않을 경우, 관리자 판단하에
							임의로 변경될 수 있습니다.
						</li>
						<li>
							이미지는
							<span class="bold">jpg, jpeg, png</span>파일만 사용
							가능합니다.
						</li>
					</ol>
				</div>
				<div>
					<div class="bold">커버 이미지</div>
					<v-row>
						<v-col md="5">
							<v-btn
								v-show="!isImg"
								tile
								class="py-7"
								text
								color="gray"
								id="img-btn"
								@click="clickImg"
							>
								<v-icon class="upload-icon">
									mdi-image-plus
								</v-icon>
								<div class="pb-4">파일을 선택해주세요</div>
							</v-btn>
							<v-hover v-slot="{ hover }">
								<v-card
									class="mx-auto"
									color="grey lighten-4"
									max-width="600"
								>
									<v-img
										v-show="isImg"
										ref="img"
										height="300"
										:src="imgURL"
									>
										<v-expand-transition>
											<v-row
												v-if="hover"
												class="d-flex transition-fast-in-fast-out darken-2 v-card--reveal display-3 white--text"
												style="height: 100%; background-color:rgba(0,0,0,0.3);"
												align="center"
												justify="center"
											>
												<v-btn
													fab
													large
													@click="deleteImg"
													color="rgba(255,255,255,0.8)"
												>
													<v-icon
														color="rgba(0,0,0,0.5)"
														large
													>
														mdi-trash-can
													</v-icon>
												</v-btn>
											</v-row>
										</v-expand-transition>
									</v-img>
								</v-card>
							</v-hover>
						</v-col>
						<v-col md="7">
							<div
								class="bg-light-gray border-radius-10 pa-6"
								style="height:100%;"
							>
								<div class="bold">
									어떤 이미지를 선택하실지 고민이신가요?
								</div>
								<br />
								첫인상을 책임지는 커버 이미지!
								<br />
								어떤 이미지가 좋을지
								<span>
									<a href="#">
										<span>커버 이미지 가이드</span>
									</a>
								</span>
								를 확인해 보세요!
								<v-row>
									<v-col cols="4">
										<v-img
											class="border-radius-10"
											src="https://4.bp.blogspot.com/-FKj4lxWCQrA/U2oVUsAZrVI/AAAAAAAAApA/D0oJ6QP_z0g/s1600/phpCode.png"
											height="115"
										></v-img>
									</v-col>
									<v-col cols="4">
										<v-img
											class="border-radius-10"
											src="@/assets/thumbnail_1.jpg"
											height="115"
										></v-img>
									</v-col>
									<v-col cols="4">
										<v-img
											class="border-radius-10"
											src="@/assets/thumbnail_2.jpg"
											height="115"
										></v-img>
									</v-col>
								</v-row>
							</div>
						</v-col>
					</v-row>
					<input
						ref="file"
						type="file"
						id="file"
						v-show="false"
						accept="image/png, image/jpeg, image/bmp"
						@change="setImg"
					/>
				</div>
			</div>
			<div class="mt-6" style="text-align:end;">
				<v-btn
					outlined
					class="mr-3"
					style="border: 1px solid #c9c9c9;"
					@click="saveCover"
				>
					저장
				</v-btn>
				<v-btn dark color="#5500ff" @click="changeActive">
					다음
				</v-btn>
			</div>
		</v-col>
		<v-col cols="3">
			<div>💕썸네일 고르는 Tip!</div>
		</v-col>
	</v-row>
</template>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
import { mapGetters } from 'vuex';

export default {
	components: {},
	data() {
		return {
			isImg: false,
			imgURL: '',
		};
	},
	methods: {
		setImg() {
			var file = document.getElementById('file').files[0];

			if (file) {
				this.isImg = true;
				this.imgURL = URL.createObjectURL(file);
			}
		},
		clickImg() {
			this.$refs.file.value = null;
			$('#file').click();
		},
		deleteImg() {
			this.isImg = false;
		},
		changeActive() {
			this.$emit('changeActive');
		},
		async saveCover() {
			var thumbnailURL = await this.saveThumbnail();
			if (thumbnailURL) {
				this.$store.commit('setLoadmapThumbnail', thumbnailURL.data);
			}
			this.$store
				.dispatch('setLoadmap', { insert: [], delete: [], update: [] })
				.then(() => {});
		},
		saveThumbnail() {
			let formData = new FormData();
			if (document.getElementById('file').files[0]) {
				formData.append(
					'file',
					document.getElementById('file').files[0],
				);
				return this.$store.dispatch('upload', {
					data: formData,
					target: 'loadmap/thumbnail',
				});
			}
		},
	},
	computed: {
		...mapGetters(['loadmap']),
	},
	mounted() {
		this.$refs.img.src = this.loadmap.ldm_thumbnail;
		this.isImg = true;
	},
};
</script>

<style scoped>
.label {
	padding-bottom: 8px;
}

.item {
	padding-bottom: 24px;
}

#background-img {
	display: none;
	cursor: pointer;
}

#img-btn {
	width: 100%;
	height: 300px;
	border: 1px solid #c9c9c9;
	border-radius: 10px;
	font-weight: 600;
}

.upload-icon {
	font-size: 30px;
}

a {
	text-decoration: none;
}
</style>
<style>
#loadmap-cover .v-btn__content {
	display: inline-block !important;
	text-align: center;
	margin: 0 auto;
}
</style>
