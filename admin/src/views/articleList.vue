//显示所有文章列表组件
<template>
	<div class="article-list">
		<div class="btn-items flex">
			<div class="search">
				<label for="userName">用户名称：</label>
				<el-input size="mini" type="text" id="userName" name="userName" placeholder="请输入用户名"></el-input>
			</div>
			<el-button type="primary" size="mini" icon="el-icon-search">搜索</el-button>
			<el-button size="mini">重置</el-button>
			<el-button size="mini" icon="el-icon-arrow-down">展开</el-button>
		</div>
		<div class="btn-items">
			<el-button type="primary" size="mini" icon="el-icon-plus">添加用户</el-button>
			<el-button size="mini" icon="el-icon-delete">批量删除</el-button>
		</div>
		<div class="article-table-wrap">
			<div class="table">
				<el-table :data="tableData" show-summary border stripe style="width: 100%" size="mini">
					<el-table-column type="selection" width="55"></el-table-column>
					<el-table-column
						v-for="item in tableHeader"
						:label="item.label"
						:prop="item.prop"
						align="center"
						show-overflow-tooltip
						width="auto"
					></el-table-column>
					<!-- <el-table-column label="状态">
						<template slot-scope="scope">
							<el-tag :type="true ? 'primary' : 'success'" size="mini" disable-transitions>已使用</el-tag>
						</template>
					</el-table-column>-->
					<el-table-column label="操作" width="200" align="center">
						<template slot-scope="scope">
							<el-button size="mini" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
							<el-button
								size="mini"
								icon="el-icon-delete"
								type="danger"
								@click="handleDelete(scope.$index, scope.row)"
							>删除</el-button>
						</template>
					</el-table-column>
				</el-table>
			</div>
			<!-- 分页 -->
			<div class="pagination" v-show='tableData.length !==0'>
				<el-pagination
					:page-sizes="[100, 200, 300, 400]"
					:page-size="100"
					layout="prev, pager, next"
					:total="400"
				></el-pagination>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	name: 'articleList',
	data() {
		return {
			pageSize: 10, //每页显示9条数据
			tableHeader: [
				{
					label: '姓名',
					prop: 'eName'
				},
				{
					label: '天数',
					prop: 'eDay'
				},
				{
					label: '说明',
					prop: 'mark'
				},
				{
					label: '逻辑删除',
					prop: 'logicDel'
				}
			],
			tableData: [
			]
		}
	},
	created() {
		this.tableDataAjax()
	},
	methods: {
		//获取表格数据
		tableDataAjax() {
			this.$axios
				.get('/place/selectAll')
				.then(res => {
					console.log(res)
					this.tableData = res.data.data
				})
				.catch(err => {
					console.log(err)
				})
		}
	},
	components: {}
}
</script>

<style lang='less' scoped>
label {
	white-space: nowrap;
}
.btn-items {
	margin: 10px !important;
}
.search {
	width: 20%;
	display: flex;
	align-items: center;
	margin-right: 10px;
}
.article-list {
	background-color: #fff;
	width: 100%;
	overflow: hidden;
	height: 100%;
	display: flex;
	flex-direction: column;

	& > p {
		padding: 10px;
		position: absolute;
		font-size: 16px;
		font-weight: bold;
		margin-bottom: 10px;
		border-bottom: 1px solid rgba(38, 42, 48, 0.1);
	}
}
.article-table-wrap {
	width: 100%;
	box-sizing: border-box;
	animation: show 0.8s;
	position: relative;
	height: 100%;

	.table {
		width: 100%;
		height: 100%;
		position: absolute;
		overflow: auto;
		box-sizing: border-box;
		padding-bottom: 40px;
	}
}

.article-title {
	& > span {
		cursor: pointer;

		&:hover {
			color: #29b6f6;
		}
	}
}
.pagination {
	position: absolute;
	bottom: 0;
}
</style>
