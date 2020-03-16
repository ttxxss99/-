//左边菜单组件
<template>
	<div class="left-menu" :style="{width:collapseMenu?'64px':'240px'}">
		<div class="wrap">
			<div class="avatar">
				<img src="static/images/avatar.jpg" alt="作者头像" />
			</div>
			<el-menu
				default-active="/admin"
				router
				:collapse-transition="collapseMenu"
				:style="{paddingLeft:collapseMenu?'0':'20px'}"
				:collapse="collapseMenu"
				class="menu"
				:unique-opened="true"
			>
				<el-menu-item index="/admin">
					<i class="iconfont icon-home"></i>
					<span slot="title">首页</span>
				</el-menu-item>
				<el-submenu index="2">
					<template slot="title">
						<i class="iconfont icon-article"></i>
						<span>文章管理</span>
					</template>
					<el-menu-item index="/article">
						<i class="iconfont icon-article-edit"></i>写文章
					</el-menu-item>
					<el-menu-item index="/post">
						<i class="iconfont icon-article-manage"></i>岗位
					</el-menu-item>
					<el-menu-item index="/admin/article/draft">
						<i class="iconfont icon-drafts"></i>草稿箱
					</el-menu-item>
					<el-menu-item index="/admin/article/deleted">
						<i class="iconfont icon-deleted"></i>回收站
					</el-menu-item>
				</el-submenu>
				<el-menu-item index="/admin/category">
					<i class="iconfont icon-tag"></i>
					<span slot="title">分类/标签</span>
				</el-menu-item>
				<el-menu-item index="/admin/comment">
					<i class="iconfont icon-comments"></i>
					<span slot="title">评论</span>
				</el-menu-item>
				<el-submenu index="4">
					<template slot="title">
						<i class="iconfont icon-config"></i>
						<span slot="title">工资管理</span>
					</template>
					<el-menu-item index="/salaryDetail">
						<i class="iconfont icon-base-config"></i>工资明细
					</el-menu-item>
					<el-menu-item index="/payStructure">
						<i class="iconfont icon-about"></i>工资结构
					</el-menu-item>
					<el-menu-item index="/salarySheet">
						<i class="iconfont icon-resume"></i>工资单
					</el-menu-item>
					<el-menu-item index="/admin/webConfig/friends">
						<i class="iconfont icon-friends-link"></i>友链管理
					</el-menu-item>
				</el-submenu>
				<el-submenu index="5">
					<template slot="title">
						<i class="iconfont icon-other"></i>
						<span slot="title">其他</span>
					</template>
					<el-menu-item :route="$route.path" index="0">
						<i class="iconfont icon-signout"></i>退出
					</el-menu-item>
				</el-submenu>
			</el-menu>
			<div
				class="collapse-wrap"
				@click="toggleCollapse"
				@mouseover="setLineData"
				@mouseout="setLineData"
			>
				<span
					class="collapse-line"
					v-for="(line,index) in toggleLineData"
					:key="index"
					:style="{width:line.width,top:line.top,transform:line.transform,opacity:line.opacity}"
				></span>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	name: 'leftMenu',
	data() {
		return {
			collapseMenu: false, //菜单栏合起来
			lineStyle: {
				normalLineData: [
					{
						width: '100%',
						top: '0px',
						transform: 'rotateZ(0deg)',
						opacity: '1'
					},
					{
						width: '100%',
						top: '0px',
						transform: 'rotateZ(0deg)',
						opacity: '1'
					},
					{
						width: '100%',
						top: '0px',
						transform: 'rotateZ(0deg)',
						opacity: '1'
					}
				],
				arrowLeftLineData: [
					{
						width: '50%',
						top: '3px',
						transform: 'rotateZ(-40deg)',
						opacity: '1'
					},
					{
						width: '100%',
						top: '0px',
						transform: 'rotateZ(0deg)',
						opacity: '1'
					},
					{
						width: '50%',
						top: '-3px',
						transform: 'rotateZ(45deg)',
						opacity: '1'
					}
				],
				arrowRightLineData: [
					{
						width: '50%',
						top: '3px',
						transform: 'translateX(10px) rotateZ(45deg)',
						opacity: '1'
					},
					{
						width: '100%',
						top: '0px',
						transform: 'rotateZ(0deg)',
						opacity: '1'
					},
					{
						width: '50%',
						top: '-3px',
						transform: 'translateX(10px) rotateZ(-45deg)',
						opacity: '1'
					}
				]
			},
			toggleLineData: []
		}
	},
	created() {
		this.toggleLineData = this.lineStyle.normalLineData
	},
	methods: {
		toggleCollapse() {
			this.collapseMenu = !this.collapseMenu
			this.collapseMenu
				? (this.toggleLineData = this.lineStyle.arrowRightLineData)
				: (this.toggleLineData = this.lineStyle.arrowLeftLineData)
		},
		setLineData(e) {
			if (e.type == 'mouseover') {
				this.toggleLineData = this.collapseMenu
					? this.lineStyle.arrowRightLineData
					: this.lineStyle.arrowLeftLineData
			} else {
				this.toggleLineData = this.lineStyle.normalLineData
			}
		}
	},
	components: {}
}
</script>

<style lang="less" scoped>
.left-menu {
	width: 64px;
	height: 100%;
	transition: all 0.5s;
    position: relative;
    overflow: hidden;

	.wrap {
		width: 100%;
		height: 100%;

		.avatar {
			width: 100%;
			height: 80px;
			background-color: hsla(0, 0%, 100%, 0.2);
			overflow: hidden;

			& > img {
				display: block;
				width: 60px;
				height: 60px;
				border-radius: 60px;
				margin: 10px auto;
			}
		}

		.menu {
			height: 100%;
			position: relative;
			border: none;
			transition: all 0.5s;

			.el-submenu__title i,
			.el-menu-item i {
				margin-right: 5px;
			}

			.el-menu-item.is-active {
				border-right: 2px solid #409eff;
				color: #409eff;
			}
		}

		.collapse-wrap {
			width: 24px;
			height: 24px;
			position: absolute;
			right: 20px;
			bottom: 20px;
			padding: 5px;
			z-index: 1050;
			cursor: pointer;
			line-height: 0;

			.collapse-line {
				display: inline-block;
				width: 100%;
				height: 2px;
				margin-top: 4px;
				transition: all 0.3s;
				background-color: #909399;
				position: relative;

				&:first-child {
					margin-top: 0;
				}
			}
		}
	}
}
</style>
