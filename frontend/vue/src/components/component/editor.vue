<template>
	<div class="editor  ma-0">
		<editor-menu-bar
			id="menu-bar"
			:editor="editor"
			v-slot="{ commands, isActive }"
		>
			<div class="menubar">
				<button
					class="menubar__button ml-3"
					:class="{ 'is-active': isActive.bold() }"
					@click="commands.bold"
				>
					<img
						class="icon"
						src="@/assets/images/icons/bold.svg"
						alt=""
						height="18"
						width="18"
					/>
				</button>

				<button
					class="menubar__button"
					:class="{ 'is-active': isActive.italic() }"
					@click="commands.italic"
				>
					<img
						class="icon"
						src="@/assets/images/icons/italic.svg"
						alt=""
					/>
				</button>

				<button
					class="menubar__button"
					:class="{ 'is-active': isActive.strike() }"
					@click="commands.strike"
				>
					<img
						class="icon"
						src="@/assets/images/icons/strike.svg"
						alt=""
					/>
				</button>

				<button
					class="menubar__button"
					:class="{ 'is-active': isActive.underline() }"
					@click="commands.underline"
				>
					<img
						class="icon"
						src="@/assets/images/icons/underline.svg"
						alt=""
					/>
				</button>

				<span class="bar" aria-hidden="true">|</span>

				<button
					class="menubar__button"
					:class="{ 'is-active': isActive.paragraph() }"
					@click="commands.paragraph"
				>
					<img
						class="icon"
						src="@/assets/images/icons/paragraph.svg"
						alt=""
					/>
				</button>

				<button
					class="menubar__button"
					:class="{
						'is-active': isActive.heading({ level: 1 }),
					}"
					@click="commands.heading({ level: 1 })"
				>
					H1
				</button>

				<button
					class="menubar__button"
					:class="{
						'is-active': isActive.heading({ level: 2 }),
					}"
					@click="commands.heading({ level: 2 })"
				>
					H2
				</button>

				<button
					class="menubar__button"
					:class="{
						'is-active': isActive.heading({ level: 3 }),
					}"
					@click="commands.heading({ level: 3 })"
				>
					H3
				</button>

				<span class="bar" aria-hidden="true">|</span>

				<button
					class="menubar__button"
					:class="{ 'is-active': isActive.bullet_list() }"
					@click="commands.bullet_list"
				>
					<img
						class="icon"
						src="@/assets/images/icons/ul.svg"
						alt=""
					/>
				</button>

				<button
					class="menubar__button"
					:class="{
						'is-active': isActive.ordered_list(),
					}"
					@click="commands.ordered_list"
				>
					<img
						class="icon"
						src="@/assets/images/icons/ol.svg"
						alt=""
					/>
				</button>

				<button
					class="menubar__button"
					:class="{ 'is-active': isActive.blockquote() }"
					@click="commands.blockquote"
				>
					<img
						class="icon"
						src="@/assets/images/icons/quote.svg"
						alt=""
					/>
				</button>

				<button
					class="menubar__button"
					:class="{ 'is-active': isActive.code_block() }"
					@click="commands.code_block"
				>
					<img
						class="icon"
						src="@/assets/images/icons/code.svg"
						alt=""
					/>
				</button>

				<button
					class="menubar__button"
					@click="commands.horizontal_rule"
				>
					<img
						class="icon"
						src="@/assets/images/icons/hr.svg"
						alt=""
					/>
				</button>

				<button
					class="menubar__button"
					@click="clickImg(commands.image)"
				>
					<img
						class="icon"
						src="@/assets/images/icons/image.svg"
						alt=""
					/>
				</button>

				<span class="bar" aria-hidden="true">|</span>

				<button class="menubar__button" @click="commands.undo">
					<img
						class="icon"
						src="@/assets/images/icons/undo.svg"
						alt=""
					/>
				</button>

				<button class="menubar__button" @click="commands.redo">
					<img
						class="icon"
						src="@/assets/images/icons/redo.svg"
						alt=""
					/>
				</button>
			</div>
		</editor-menu-bar>
		<input
			ref="file"
			type="file"
			id="editor-file"
			v-show="false"
			accept="image/png, image/jpeg, image/bmp"
			@change="setImg"
		/>
		<editor-content class="editor__content pa-5" :editor="editor" />
		<div id="editor-footer">
			<div class="px-2 footer-btn">
				<h4 class="px-2">Markdown</h4>
			</div>
		</div>
	</div>
</template>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
import { Editor, EditorContent, EditorMenuBar } from 'tiptap';
import {
	Blockquote,
	CodeBlock,
	HardBreak,
	Heading,
	HorizontalRule,
	OrderedList,
	BulletList,
	ListItem,
	TodoItem,
	TodoList,
	Bold,
	Code,
	Italic,
	Image,
	Link,
	Strike,
	Underline,
	History,
} from 'tiptap-extensions';
export default {
	components: {
		EditorContent,
		EditorMenuBar,
	},
	props: ['preContent'],
	data() {
		return {
			editor: new Editor({
				extensions: [
					new Blockquote(),
					new BulletList(),
					new CodeBlock(),
					new HardBreak(),
					new Heading({ levels: [1, 2, 3] }),
					new HorizontalRule(),
					new ListItem(),
					new OrderedList(),
					new TodoItem(),
					new TodoList(),
					new Link(),
					new Bold(),
					new Code(),
					new Italic(),
					new Image(),
					new Strike(),
					new Underline(),
					new History(),
				],
				content: this.preContent,
			}),
			editorWidth: 0,
			callBack: null,
		};
	},
	methods: {
		showImagePrompt(command) {
			const src = prompt('사용하실 이미지 URL을 입력해 주세요.');
			if (src !== null) {
				command({ src });
			}
		},
		getHTML() {
			return this.editor.getHTML();
		},
		clickImg(command) {
			this.$refs.file.value = null;
			$('#editor-file').click();
			this.callBack = command;
		},
		setImg() {
			var file = document.getElementById('editor-file').files[0];
			if (file) {
				let formData = new FormData();
				if (file) {
					formData.append('file', file);
					this.$store
						.dispatch('upload', {
							data: formData,
							target: 'contents',
						})
						.then(({ data }) => {
							const src = data;
							this.callBack({ src });
						});
				}
				// const src = URL.createObjectURL(file);
			}
		},
	},
	beforeDestroy() {
		this.editor.destroy();
	},
};
</script>

<style lang="scss" scoped>
.icon {
	position: relative;
	display: inline-block;
	vertical-align: middle;
	width: 0.8rem;
	height: 0.8rem;
	margin: 0 0.3rem;
	top: -0.05rem;
	fill: currentColor;

	&__svg {
		display: inline-block;
		vertical-align: top;
		width: 100%;
		height: 100%;
	}

	&:first-child {
		margin-left: 0;
	}

	&:last-child {
		margin-right: 0;
	}
}

// svg sprite
body > svg,
.icon use > svg,
symbol {
	path,
	rect,
	circle,
	g {
		fill: currentColor;
		stroke: none;
	}

	*[d='M0 0h24v24H0z'] {
		display: none;
	}
}

#menu-bar {
	border: 1px solid #e3e3e3;
}

.editor__content {
	border-right: 1px solid #e3e3e3;
	border-left: 1px solid #e3e3e3;
	border-bottom: 1px solid #e3e3e3;
	height: 600px;
	overflow-y: scroll;
}

.bar {
	display: inline-block;
	width: 1px;
	height: 18px;
	margin: 2px 5px;
	text-indent: -999em;
	background: #e4e4e5;
	vertical-align: middle;
}

#editor-footer {
	border-right: 1px solid #e3e3e3;
	border-left: 1px solid #e3e3e3;
	border-bottom: 1px solid #e3e3e3;
	background-color: #ededed;
	text-align: end;
}

.footer-btn {
	display: inline-block;
	background-color: white;
	cursor: pointer;
}
</style>
