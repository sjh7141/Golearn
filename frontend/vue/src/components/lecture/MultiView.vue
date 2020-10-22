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
	>
		<div :style="{ height: totalHeight + 'px', width: videoWidth + 'px' }">
			<video
				class="video-js vjs-default-skin vjs-big-play-centered"
				id="video-player"
				controls
				data-setup="{}"
				poster="@/assets/logo.png"
				style="height:100%; width:100%"
			>
				<source src="@/assets/sample.mp4" />
			</video>
		</div>
		<div
			style="cursor:e-resize; background-color:grey"
			:style="{ height: totalHeight + 'px', width: BORDER_SIZE + 'px' }"
			@mousedown="isHorizontalDrag = true"
		></div>
		<div :style="{ height: totalHeight + 'px', width: editorWidth + 'px' }">
			<div
				id="editor"
				:style="{
					height: editorHeight + 'px',
					width: editorWidth + 'px',
				}"
			></div>
			<div
				style="cursor:n-resize; background-color:grey"
				:style="{
					height: BORDER_SIZE + 'px',
					width: editorWidth + 'px',
				}"
				@mousedown="isVerticalDrag = true"
			></div>
			<div
				id="result"
				style="background-color:rgb(28, 28, 28)"
				:style="{
					height: resultHeight + 'px',
					width: editorWidth + 'px',
				}"
			>
				<div style="width:100%; height:100%; padding:3px 0px 0px 10px;">
					<div>
						출력
					</div>
					<div style="margin-top:10px;">
						Value: 2.2250738585072014E-308
					</div>
				</div>
			</div>
		</div>
	</v-layout>
</template>

<script src="" />
<script>
export default {
	name: 'Video',
	data() {
		return {
			BORDER_SIZE: 4,

			totalHeight: 0,
			videoWidth: 0,
			editorWidth: 0,

			editorHeight: 0,
			resultHeight: 0,

			source_code: `import java.util.ArrayList;
    import java.util.Vector;

    public class InfiniteLoop {

        /*
         * This will cause the program to hang...
         *
         * Taken from:
         * http://www.exploringbinary.com/java-hangs-when-converting-2-2250738585072012e-308/
         */
        public static void main(String[] args) {
            double d = Double.parseDouble("2.2250738585072012e-308");

            // unreachable code
            System.out.println("Value: " + d);
        }
    }`,
			isHorizontalDrag: false,
			isVerticalDrag: false,
		};
	},
	mounted() {
		this.createChapterButton();
		this.initCodeEditer();

		window.addEventListener('resize', this.resizeHandler);
		this.videoWidth = window.innerWidth * 0.5;
		this.totalHeight = window.innerHeight - this.$refs.app.offsetTop;
		this.editorHeight = this.totalHeight * 0.66 - this.BORDER_SIZE;

		this.resizeHandler();
	},
	beforeDestroy() {
		window.removeEventListener('resize', this.resizeHandler);
	},
	methods: {
		dragStart(e) {},
		dragEnd(e) {},
		resizeWidth(e) {
			if (this.isHorizontalDrag) {
				this.videoWidth = e.clientX - this.BORDER_SIZE;
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
		resizeHandler() {
			this.totalHeight = window.innerHeight - this.$refs.app.offsetTop;

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
			var Button = videojs.getComponent('Button');
			var MyButton = videojs.extend(Button, {
				constructor: function() {
					Button.apply(this, arguments);
					this.addClass('vjs-chapters-button');
				},
				handleClick: function() {
					// todo
				},
			});
			videojs.registerComponent('MyButton', MyButton);
			var player = videojs('video-player');
			player.getChild('controlBar').addChild('myButton', {});
		},
		initCodeEditer() {
			var editor = ace.edit('editor');
			editor.session.setMode('ace/mode/java');
			editor.setValue(this.source_code);
			editor.setTheme('ace/theme/merbivore_soft');
			document.getElementById('editor').style.fontSize = '16px';
		},
	},
};
</script>

<style>
.ace_scrollbar-h,
.ace_scrollbar-v {
	overflow-x: hidden;
	overflow-y: hidden;
}

#result {
	color: rgb(230, 225, 220);
	font-weight: 500;
	font-size: 14px;
	font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen,
		Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
}
</style>
