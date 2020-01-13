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
			<el-button type="primary" size="mini" icon="el-icon-plus" @click="showModel('add')">添加用户</el-button>
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
							<el-button size="mini" icon="el-icon-edit" @click="showModel('update', scope.row)">编辑</el-button>
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
			<div class="pagination" v-show="tableData.length !==0">
				<el-pagination
					:page-size="pageSize"
					layout="prev, pager, next"
					:current-page="pageIndex"
					:current-change="handlePage"
					:total="pageTotal"
				></el-pagination>
			</div>
		</div>

		<el-dialog :title="modelTtile" :visible.sync="personModelShow">
			<el-form :model="personObj">
				<el-form-item label="姓名" label-width="80px">
					<el-input v-model="personObj.name"></el-input>
				</el-form-item>
				<el-form-item label="电话" label-width="80px">
					<el-input v-model="personObj.tel"></el-input>
				</el-form-item>
				<el-form-item label="备注" label-width="80px">
					<el-input v-model="personObj.mark"></el-input>
				</el-form-item>
				<el-form-item label="工龄" label-width="80px">
					<el-input v-model="personObj.workAge"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="personModelShow = false">取 消</el-button>
				<el-button type="primary" @click="submit">确 定</el-button>
			</div>
		</el-dialog>
	</div>
</template>

<script>
export default {
	name: 'articleList',
	data() {
		return {
			personModelShow: false,
			modelTtile: '',
			isEdit: false,
			pageSize: 10,
			pageTotal: 0,
			personObj: {
				name: '',
				tel: '',
				mark: '',
				workAge: ''
			},
			pageSize: 10, //每页显示9条数据
			pageIndex: 0,
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
			tableData: []
		}
	},
	created() {
		this.tableDataAjax()
	},
	methods: {
		//获取表格数据
		tableDataAjax() {
			this.$axios
				.get(
					'/employee/selectAll?currentPage=' +
						this.pageIndex +
						'&pageSize=' +
						this.pageSize
				)
				.then(res => {
					console.log(res)
					this.tableData = res.data.data.items
					this.totalPage = res.data.data.totalNum
				})
				.catch(err => {
					console.log(err)
				})
		},
		//添加用户
		showModel(type, row) {
			this.personModelShow = true
			if (type === 'add') {
				this.modelTtile = '增加员工'
			} else if (type === 'update') {
				this.modelTtile = '修改员工信息'
				this.isEdit = true
				for (var key in this.personObj) {
					this.personObj[key] = row[key]
				}
				this.personObj.id = row.id
			}
		},
		//提交
		submit() {
			console.log(this.personObj)
			if (isEdit) {
				this.$axios
					.post('/employee/update', this.personObj)
					.then(res => {
						if (res.data) {
							this.$message.info('修改成功')
							this.tableDataAjax()
							this.clearData()
							this.isEdit = false
							this.personModelShow = false
						}
					})
					.catch(err => {
						console.log(err)
					})
			} else {
				this.$axios
					.post('/employee/insert', this.personObj)
					.then(res => {
						if (res.data) {
							this.$message.info('添加成功')
							this.tableDataAjax()
							this.clearData()
							this.personModelShow = false
						}
					})
					.catch(err => {
						console.log(err)
					})
			}
		},
		handlePage(val) {
			this.pageIndex = val
			this.tableDataAjax()
		},
		//删除
		handleDelete(index, row) {
			this.$axios
				.post('/employee/delete', [row.id])
				.then(res => {
					if (res.data) {
						this.$message.info('删除成功')
						this.tableDataAjax()
					}
				})
				.catch(err => {
					console.log(err)
				})
		},
		//清除数据
		clearData() {
			this.personObj = {
				name: '',
				tel: '',
				mark: '',
				workAge: ''
			}
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
