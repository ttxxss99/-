//显示所有文章列表组件
<template>
	<div class="article-list">
		<p>文章列表（共12篇文章）</p>
		<div class="article-table-wrap">
			<div class="table">
				<el-table :data="tableData" border stripe style="width: 100%" size="mini">
					<el-table-column v-for="item in tableHeader" :label="item.label" :prop="item.prop" show-overflow-tooltip width="auto">
						
					</el-table-column>
					<!-- <el-table-column label="状态">
						<template slot-scope="scope">
							<el-tag :type="true ? 'primary' : 'success'" size="mini" disable-transitions>已使用</el-tag>
						</template>
					</el-table-column> -->
					<el-table-column label="操作" width="200">
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
			<div class="pagination">
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
			tableHeader:[
				{
					label:'1',
					prop:'day'
				},
				{
					label:'2',
					prop:'daySalary'
				},
				{
					label:'3',
					prop:'pname'
				},
				{
					label:'4',
					prop:'ename'
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
.article-list {
	background-color: #fff;
	width: 100%;
	height: 100%;

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
	padding: 50px 0 40px 0;
	width: 100%;
	box-sizing: border-box;
	animation: show 0.8s;
	position: relative;
	height: 100%;

	.table {
        width: 100%;
        height: 100%;
        position: relative;
        overflow: auto;
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
</style>
