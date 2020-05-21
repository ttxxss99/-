<template>
	<div>
		<div class="crumbs">
			<el-breadcrumb separator="/">
				<el-breadcrumb-item>
					<i class="el-icon-lx-cascades"></i> 员工管理
				</el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div class="container">
			<div class="handle-box">
				<el-button type="danger" icon="el-icon-delete" class="handle-del mr10" @click="delAllSelection">批量删除</el-button>
				<el-button type="primary" icon="el-icon-plus" class="handle-del mr10" @click="showModel('add')">增加</el-button>
				<el-input v-model="query.name" placeholder="用户名" class="handle-input mr10"></el-input>
				<el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
			</div>
			<el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header"
			 @selection-change="handleSelectionChange">
				<el-table-column type="selection" width="55" align="center"></el-table-column>
				<!-- <el-table-column prop="id" label="ID" width="55" align="center"></el-table-column> -->
				<el-table-column prop="name" label="姓名"></el-table-column>
				<el-table-column label="工龄">
					<template slot-scope="scope">{{scope.row.workAge}}</template>
				</el-table-column>
				<el-table-column prop="tel" label="电话"></el-table-column>
				<el-table-column prop="time" label="创建时间"></el-table-column>
				<el-table-column prop="mark" label="说明"></el-table-column>
				<el-table-column label="操作" width="180" align="center">
					<template slot-scope="scope">
						<el-button type="text" icon="el-icon-edit" @click="showModel('update', scope.row)">编辑</el-button>
						<el-button type="text" icon="el-icon-delete" class="red" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
					</template>
				</el-table-column>
			</el-table>
			<div class="pagination">
				<el-pagination background layout="total, prev, pager, next" :current-page="query.pageIndex" :page-size="query.pageSize"
				 :total="pageTotal" @current-change="handlePageChange"></el-pagination>
			</div>
		</div>

		<!-- 编辑弹出框 -->
		<el-dialog :title="modelTtile" :visible.sync="personModelShow">
			<el-form :model="form">
				<el-form-item label="姓名" label-width="80px">
					<el-input v-model="form.name"></el-input>
				</el-form-item>
				<el-form-item label="电话" label-width="80px">
					<el-input v-model="form.tel"></el-input>
				</el-form-item>
				<el-form-item label="备注" label-width="80px">
					<el-input v-model="form.mark"></el-input>
				</el-form-item>
				<el-form-item label="工龄" label-width="80px">
					<el-select v-model="form.workAge" placeholder="工龄" class="handle-select mr10">
						<el-option key="1" label="1" value="1"></el-option>
						<el-option key="2" label="2" value="2"></el-option>
						<el-option key="3" label="3" value="3"></el-option>
						<el-option key="4" label="4" value="4"></el-option>
						<el-option key="5" label="5" value="5"></el-option>
					</el-select>
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
		name: 'employee',
		data() {
			return {
				query: {
					// address: '',
					name: '',
					pageIndex: 1,
					pageSize: 10
				},
				personModelShow: false,
				modelTtile: '',
				isEdit: false,
				tableData: [],
				multipleSelection: [],
				delList: [],
				pageTotal: 0,
				form: {
					name: '',
					mark: '',
					tel: '',
					workAge: ''
				},
				idx: -1,
				id: -1
			};
		},
		created() {
			this.getData();
		},
		methods: {
			// 获取 easy-mock 的模拟数据
			getData() {
					console.log(this.form)
					this.$axios
						.post(
							'/employee/selectByName?currentPage=' +
							this.query.pageIndex +
							'&pageSize=' +
							this.query.pageSize,this.form
						)
						.then(res => {
							console.log(res);
							this.tableData = res.data.data.items;
							this.pageTotal = res.data.data.totalNum || 50;
							// this.isSerch = false;
						})
						.catch(err => {
							console.log(err)
						})
				// }

			},
			// 触发搜索按钮
			handleSearch() {
				// this.$set(this.query, 'pageIndex', 1);
				// this.isSerch = true;
				this.getData();
			},
			// 删除操作
			handleDelete(index, row) {
				const that = this;
				// 二次确认删除
				this.$confirm('确定要删除吗？', '提示', {
						type: 'warning'
					})
					.then(() => {
						this.$axios
							.post('/employee/delete', [row.id])
							.then(res => {
								if (res.data) {
									this.$message.success('删除成功');
									this.tableData.splice(index, 1);
									this.getData()
								}
							})
							.catch(err => {
								console.log(err)
							})
					})
					.catch(() => {});
			},
			// 多选操作
			handleSelectionChange(val) {
				this.multipleSelection = val;
			},
			delAllSelection() {
				const length = this.multipleSelection.length;
				for (let i = 0; i < length; i++) {
					this.delList = this.delList.concat(this.multipleSelection[i].id);
				}
				this.$axios
					.post('/employee/delete', this.delList)
					.then(res => {
						if (res.data) {
							this.$message.success('删除成功');
							this.getData()
						}
					})
					.catch(err => {
						console.log(err)
					})
				this.multipleSelection = [];
				this.delList = [];
			},
			//添加用户
			showModel(type, row) {
				this.personModelShow = true
				if (type === 'add') {
					this.clearData()
					this.modelTtile = '增加员工'
				} else if (type === 'update') {
					this.modelTtile = '修改员工信息'
					this.isEdit = true
					for (var key in this.form) {
						this.form[key] = row[key]
					}
					this.form.id = row.id
				}
			},
			// 编辑操作
			// handleEdit(index, row) {
			// 	this.idx = index;
			// 	this.form = row;
			// 	this.editVisible = true;
			// },
			// 保存编辑
			submit() {
				if (this.isEdit) {
					this.$axios
						.post('/employee/update', this.form)
						.then(res => {
							if (res.data) {
								this.$message.success(`修改第 ${this.idx + 1} 行成功`);
								this.$set(this.tableData, this.idx, this.form);
								this.getData()
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
						.post('/employee/insert', this.form)
						.then(res => {
							if (res.data) {
								this.$message.info('添加成功')
								this.getData()
								this.clearData()
								this.personModelShow = false
							}
						})
						.catch(err => {
							console.log(err)
						})
				}
			},
			//清除数据
			clearData() {
				this.form = {
					name: '',
					tel: '',
					mark: '',
					workAge: ''
				}
			},
			// 分页导航
			handlePageChange(val) {
				this.$set(this.query, 'pageIndex', val);
				this.getData();
			}
		}
	};
</script>

<style scoped>
	.handle-box {
		margin-bottom: 20px;
	}

	.handle-select {
		width: 120px;
	}

	.handle-input {
		width: 300px;
		display: inline-block;
	}

	.table {
		width: 100%;
		font-size: 14px;
	}

	.red {
		color: #ff0000;
	}

	.mr10 {
		margin-right: 10px;
	}

	.table-td-thumb {
		display: block;
		margin: auto;
		width: 40px;
		height: 40px;
	}
</style>
