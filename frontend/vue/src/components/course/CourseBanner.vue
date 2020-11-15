<template>
	<div>
		<v-img width="100%" :src="src" height="230">
			<v-layout
				style="background-color:rgba(0,0,0,0.6); width:100%;height:85%;"
			>
				<div
					style="width:1080px;color:white;"
					class="mx-auto mt-auto"
					:class="{ 'mb-5': ldm_no }"
				>
					<h1 style="font-weight:400" class="mb-10">
						{{ course.cos_title }}
					</h1>
					<v-layout>
						<v-avatar size="24">
							<v-img
								:src="member.profile"
								v-if="member"
								style="cursor:pointer;"
								@click="$router.push(`/channel/${member.no}`)"
							/>
						</v-avatar>
						<span
							style="font-size:12px;cursor:pointer;"
							class="ml-2 my-auto"
							@click="$router.push(`/channel/${member.no}`)"
						>
							{{ member ? member.nickname : '' }}
						</span>
						<v-spacer />
						<span style="font-size:12px;" class="my-auto">
							<span style="opacity:0.6">
								좋아요
							</span>
							<span>
								{{ course.mbr_no }}
							</span>
							<span style="font-size:8px" class="my-auto mx-2"
								>|</span
							>
							<span style="opacity:0.6" class="my-auto">
								수강자
							</span>
							<span> {{ course.cos_no }} </span>
						</span>

						<v-icon
							class="ml-5 pr-10"
							dark
							size="24"
							@click="
								$router.push(
									`/course/management/${course.cos_no}`,
								)
							"
							v-if="isManager"
							>mdi-cog</v-icon
						>
						<div class="ml-10" v-else></div>
					</v-layout>
				</div>
			</v-layout>
			<v-layout
				style="background-color:rgba(0,0,0,0.6); width:100%;height:15%; overflow-x:hidden;"
				class="px-5"
			>
				<div
					class="mx-auto"
					style="color:white; font-family: 'BMJUA', sans-serif !important; font-weight:400;"
				>
					<span
						v-for="(item, i) in roadmap"
						:key="`courseTitle_${i}`"
						class="mx-5"
						style="cursor:pointer"
						@click="
							$router.push(
								`/course/${item.cos_no}/intro?ldm_no=${ldm_no}`,
							)
						"
					>
						{{ item.cos_title }}
					</span>
				</div>
			</v-layout>
		</v-img>
	</div>
</template>

<script>
import { mapActions } from 'vuex';
export default {
	props: ['course', 'src'],
	data() {
		return {
			member: null,
			isManager: false,
			// roadmap: [
			// 	{ cos_title: 'C언어' },
			// 	{ cos_title: 'JAVA' },
			// 	{ cos_title: 'Python' },
			// 	{ cos_title: 'HTML' },
			// ],
			roadmap: null,
			ldm_no: 0,
		};
	},
	watch: {
		course() {
			this.getMember(this.course.mbr_no).then(({ data }) => {
				this.member = data;
			});
			this.checkCourseManager(this.course.cos_no).then(({ data }) => {
				this.isManager = data;
			});
		},
		ldm_no() {
			if (this.idm_no) {
				this.getLoadmap(this.ldm_no).then(({ data }) => {
					this.roadmap = data;
				});
			}
		},
	},
	mounted() {
		if (!this.member && !this.course) {
			this.getMember(this.course.mbr_no).then(({ data }) => {
				this.member = data;
			});
			this.checkCourseManager(this.course.cos_no).then(({ data }) => {
				console.log(data);
				this.isManager = data;
			});
		}
		if (this.idm_no) {
			this.getLoadmap(this.ldm_no).then(({ data }) => {
				this.roadmap = data;
			});
		}
	},
	methods: {
		...mapActions(['getMember', 'checkCourseManager', 'getLoadmap']),
	},
};
</script>

<style></style>
