<template>
	<div class="admin-wrap">
		<!-- 左边菜单栏 -->
		<leftMenu />
		<div class="view-wrap">
			<router-view></router-view>
		</div>
	</div>
</template>

<script>
import leftMenu from '@/components/leftMenu'
export default {
	name: 'Home',
	data() {
		return {}
	},
	components: {
        leftMenu
    }
}
</script>

<style lang='less' scoped>
.admin-wrap {
    width: 100%;
    height: 100%;
	display: flex;
	position: relative;
	.view-wrap {
		flex: 1;
		min-height: 311px;
        height: calc(100vh);
        box-sizing: border-box;
		background-color: #f0f2f5;
		padding: 14px;
	}
}
</style>
