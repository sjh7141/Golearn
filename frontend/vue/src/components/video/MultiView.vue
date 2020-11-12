<template>
	<v-layout
		ref="app"
		@mouseup="
			isHorizontalDrag = false;
			isVerticalDrag = false;
		"
		@mousemove="
			resizeWidth($event);
			resizeHeight($event);
		"
		:style="{
			top: 0,
			position: editMode ? 'fixed' : 'static',
			left: 0,
			'z-index': editMode ? 9999 : 1,
		}"
		wrap
	>
		<v-row
			class="mx-0 px-4"
			v-show="editMode"
			style="height:56px;font-size:15px; font-weight:600; "
			:style="{
				width: videoWidth + BORDER_SIZE + editorWidth + 'px',
				'background-color': dark ? '#272731' : '#F4F4F7',
				'border-bottom': dark ? '1px solid #111' : '1px solid #D7D7EB',
			}"
		>
			<span
				style="margin-top:auto; margin-bottom:auto; "
				:style="{
					color: dark ? '#f4f4f7' : '#262647',
				}"
			>
				구름IDE에서 파이썬 코딩 시작하기
			</span>
			<v-spacer />
			<v-btn
				light
				class="my-auto px-0"
				depressed
				outlined
				small
				@click="dark = true"
				:ripple="false"
				color="#4C4C5C"
				:style="{
					'background-color': dark ? '#4C4C5C' : 'transparent',
				}"
				style="border-top-right-radius: 0px;border-bottom-right-radius: 0px;"
			>
				<span
					style="font-size:12px; font-weight:500"
					:style="{ color: dark ? '#f4f4f7' : '#000' }"
				>
					dark
				</span>
			</v-btn>
			<v-btn
				light
				class="my-auto px-0 mr-5"
				depressed
				outlined
				small
				@click="dark = false"
				:ripple="false"
				color="#4C4C5C"
				:style="{
					'background-color': !dark ? '#4C4C5C' : 'transparent',
				}"
				style="border-top-left-radius: 0px;border-bottom-left-radius: 0px;"
			>
				<span style="font-size:12px; font-weight:500; color: #f4f4f7">
					light
				</span>
			</v-btn>
			<v-menu offset-y>
				<template v-slot:activator="{ on }">
					<v-btn
						light
						class="my-auto px-0"
						depressed
						small
						v-on="on"
						:ripple="false"
						color="#4C4C5C"
						min-width="65"
					>
						<div
							style="color:#f4f4f7; font-size:12px; font-weight:500; display:flex"
						>
							<span class="my-auto ml-1" v-if="language != -1">
								{{ languages[language].title }}
							</span>
							<v-icon size="16">
								mdi-menu-down
							</v-icon>
						</div>
					</v-btn>
				</template>
				<v-list class="pa-0" style="font-size:15px;">
					<v-list-item
						link
						style="min-height:30px;"
						v-for="(lang, i) in languages"
						:key="`language_${i}`"
						@click="changeMode(i, language)"
					>
						{{ lang.title }}
					</v-list-item>
				</v-list>
			</v-menu>
		</v-row>

		<div
			:style="{
				width: (editMode ? videoWidth : width) + 'px',
				height: (editMode ? totalHeight : height) + 'px',
			}"
		>
			<video
				class="video-js vjs-default-skin vjs-big-play-centered"
				id="video-player"
				controls
				data-setup="{}"
				poster="https://cdn.inflearn.com/wp-content/uploads/ktw_algorithm3.jpg"
				style="height:100%; width:100%;background-color:white; padding-left:10px;"
				:style="{ 'background-color': dark ? '#1c1c1c' : '#fff' }"
			>
				<source src="@/assets/sample.mp4" />
				<div
					style="position: absolute; right:0; height:100%; background-color:red; width:4px;"
				/>
			</video>
		</div>
		<div
			style="cursor:e-resize;"
			:style="{
				height: totalHeight + 'px',
				width: BORDER_SIZE + 'px',
				'background-color': dark ? '#1c1c1c' : '#fff',
				'border-right': dark ? '1px solid #111' : '1px solid #D7D7EB',
			}"
			@mousedown="isHorizontalDrag = true"
			v-show="editMode"
		></div>
		<div
			:style="{ height: totalHeight + 'px', width: editorWidth + 'px' }"
			v-show="editMode"
		>
			<div
				id="editor"
				:style="{
					height: editorHeight + 'px',
					width: editorWidth + 'px',
				}"
			></div>
			<div
				style="cursor:n-resize;"
				:style="{
					height: BORDER_SIZE + 'px',
					width: editorWidth + 'px',
					'background-color': dark ? '#212126' : '#FBFBFD',
					'border-top': dark ? '1px solid #111' : '1px solid #D7D7EB',
				}"
				@mousedown="isVerticalDrag = true"
			></div>
			<div
				id="result"
				style="background-color:#FBFBFD"
				:style="{
					height: resultHeight + 'px',
					width: editorWidth + 'px',
					'background-color': dark ? '#212126' : '#FBFBFD',
					color: dark ? '#e1e1e7' : '#000',
				}"
			>
				<div style="width:100%; height:100%;">
					<div class="mt-2 mb-4" style="padding-left:10px;">
						실행 결과

						<v-progress-circular
							class="ml-2"
							indeterminate
							size="20"
							:color="dark ? '#e1e1e7' : '#000'"
							v-show="loading"
						></v-progress-circular>
					</div>
					<v-divider />
					<div
						style="margin-top:10px; padding-left:10px; font-size:13px;font-weight:400;"
						:style="{
							color: dark ? '#f4f4f7' : '#262647',
						}"
						v-html="result.replace('\n', '<br />')"
					/>
				</div>
			</div>
		</div>
		<v-row
			class="mx-0 px-4"
			v-show="editMode"
			style="height:56px; font-size:15px; font-weight:600;"
			:style="{
				width: videoWidth + BORDER_SIZE + editorWidth + 'px',
				'background-color': dark ? '#272731' : '#F4F4F7',
				'border-top': dark ? '1px solid #111' : '1px solid #D7D7EB',
			}"
		>
			<v-btn
				class="my-auto mr-3"
				depressed
				color="#4C4C5C"
				@click="editMode = false"
			>
				<span style="color:#fafafa;font-size:16px; font-weight:500">
					닫기
				</span>
			</v-btn>
			<v-spacer />
			<v-btn
				class="my-auto mr-3"
				depressed
				color="#4C4C5C"
				@click="initCode"
			>
				<span style="color:#fafafa;font-size:16px; font-weight:500">
					초기화
				</span>
			</v-btn>
			<v-btn
				class="my-auto"
				depressed
				color="#4C4C5C"
				@click="executeScript"
			>
				<span style="color:#fafafa;font-size:16px; font-weight:600">
					실행
				</span>
			</v-btn>
		</v-row>
	</v-layout>
</template>

<script src="" />
<script>
import { mapActions } from 'vuex';
export default {
	name: 'MultiView',
	props: ['width', 'height', 'source', 'type'],
	watch: {
		dark() {
			var editor = ace.edit('editor');
			if (this.dark) editor.setTheme('ace/theme/merbivore_soft');
			else editor.setTheme('ace/theme/sqlserver');
		},
		source() {
			this.languages[this.type].script = this.source;
			this.changeMode(this.type, this.language);
		},
	},
	data() {
		return {
			dark: false,
			BORDER_SIZE: 10,

			totalHeight: 0,
			videoWidth: 0,
			editorWidth: 0,

			editorHeight: 0,
			resultHeight: 0,
			isHorizontalDrag: false,
			isVerticalDrag: false,

			editMode: true,
			languages: [
				{
					title: 'C++',
					value: 'cpp17',
					versionIndex: '0',
					mode: 'ace/mode/c_cpp',
					script: '',
				},
				{
					title: 'Java',
					value: 'java',
					versionIndex: '3',
					mode: 'ace/mode/java',
					script: '',
				},
				{
					title: 'Python',
					value: 'python3',
					versionIndex: '3',
					mode: 'ace/mode/python',
					script: '',
				},
			],
			language: -1,

			result: '',
			loading: false,
		};
	},
	mounted() {
		this.createChapterButton();
		this.initCodeEditer();

		window.addEventListener('resize', this.resizeVideo);
		this.videoWidth = window.innerWidth * 0.6;
		this.totalHeight = window.innerHeight - 112;
		this.editorHeight = this.totalHeight * 0.66 - this.BORDER_SIZE;

		this.resizeVideo();
	},
	beforeDestroy() {
		window.removeEventListener('resize', this.resizeVideo);
	},
	methods: {
		...mapActions(['compile']),
		executeScript() {
			var editor = ace.edit('editor');
			const script = ace.edit('editor').getValue();
			this.loading = true;

			this.compile({
				script,
				language: this.languages[this.language].value,
				versionIndex: this.languages[this.language].versionIndex,
			})
				.then(({ data }) => {
					this.result = data.output;
				})
				.finally(() => {
					this.loading = false;
				});
		},
		dragStart(e) {},
		dragEnd(e) {},
		resizeWidth(e) {
			if (this.isHorizontalDrag) {
				this.videoWidth = e.clientX - this.BORDER_SIZE;
				if (this.videoWidth < 300) this.videoWidth = 300;
				this.editorWidth =
					window.innerWidth - this.videoWidth - this.BORDER_SIZE;
			}
		},
		resizeHeight(e) {
			if (this.isVerticalDrag && e.clientY < window.innerHeight) {
				this.editorHeight =
					e.clientY - this.$refs.app.offsetTop - this.BORDER_SIZE;
				this.resultHeight =
					this.totalHeight - this.editorHeight - this.BORDER_SIZE;
			}
		},
		resizeVideo() {
			this.totalHeight = window.innerHeight - 112;

			if (this.videoWidth > window.innerWidth)
				this.videoWidth = window.innerWidth - this.BORDER_SIZE;
			if (this.editorHeight > this.totalHeight)
				this.editorHeight = this.totalHeight - this.BORDER_SIZE;

			this.editorWidth =
				window.innerWidth - this.videoWidth - this.BORDER_SIZE;
			this.resultHeight =
				this.totalHeight - this.editorHeight - this.BORDER_SIZE;
		},
		createChapterButton() {
			const self = this;

			var Button = videojs.getComponent('Button');
			var MyButton = videojs.extend(Button, {
				constructor: function() {
					Button.apply(this, arguments);
					this.addClass('vjs-chapters-button');
				},
				handleClick: function() {
					self.editMode = !self.editMode;

					document
						.querySelector('.vjs-control-bar')
						.classList.toggle('edit-mode');
				},
			});
			videojs.registerComponent('MyButton', MyButton);
			var player = videojs('video-player');
			player.getChild('controlBar').addChild('myButton', {});
		},
		initCodeEditer() {
			var editor = ace.edit('editor');
			editor.session.setMode('ace/mode/c_cpp');
			editor.setTheme('ace/theme/sqlserver');
			document.getElementById('editor').style.fontSize = '16px';
		},
		initCode() {
			var editor = ace.edit('editor');

			this.languages[this.type].script = this.source;
			this.changeMode(this.type, this.language);
		},
		setCode(idx) {
			var editor = ace.edit('editor');
			editor.setValue(this.languages[idx].script);
			editor.session.setMode(this.languages[idx].mode);
		},
		changeMode(cur, prev) {
			var editor = ace.edit('editor');

			this.language = cur;
			if (prev != -1 && cur != prev)
				this.languages[prev].script = editor.getValue();

			this.setCode(cur);
		},
	},
};
</script>

<style>
.edit-mode {
	left: 10px !important;
	width: calc(100% - 10px) !important;
}
.ace_gutter-cell {
	color: #98a8b9;
	font-weight: 200;
}
.ace-sqlserver .ace_gutter-active-line {
	background-color: inherit;
}

.ace-sqlserver .ace_gutter {
	background: white;
}

.ace-merbivore-soft .ace_gutter-active-line {
	background-color: inherit;
}

.ace-merbivore-soft .ace_gutter {
	background: #1c1c1c;
}
</style>
<style scoped>
#result {
	color: rgb(0, 0, 0);
	font-weight: 700;
	font-size: 14px;
	font-family: 'Noto Sans KR', sans-serif !important;
	/* font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen,
		Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif; */
	overflow-y: auto;
}
</style>
