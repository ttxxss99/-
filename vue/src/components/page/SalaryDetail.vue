<template>
	<div>
		<div class="crumbs">
			<el-breadcrumb separator="/">
				<el-breadcrumb-item>
					<i class="el-icon-lx-cascades"></i> 工资详情
				</el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div class="container">
			<div class="handle-box">
				<el-button type="primary" icon="el-icon-delete" class="handle-del mr10" @click="delAllSelection">批量删除</el-button>
				<el-button type="primary" icon="el-icon-plus" class="handle-del mr10" @click="showModel('add')">增加</el-button>
				<el-input v-model="query.eName" placeholder="用户名" class="handle-input mr10"></el-input>
				<el-input v-model="query.pName" placeholder="地点" class="handle-input mr10"></el-input>
				<div class="block" style="display: initial;">
					<el-date-picker v-model="query.time" format="yyyy-MM-dd HH:mm:ss" value-format="yyyy-MM-dd HH:mm:ss"
					type="daterange" align="right" unlink-panels range-separator="至"
					 start-placeholder="开始日期" end-placeholder="结束日期" :picker-options="pickerOptions">
					</el-date-picker>
				</div>
				<el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
			</div>
			<el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header"
			 @selection-change="handleSelectionChange">
				<el-table-column type="selection" width="55" align="center"></el-table-column>
				<el-table-column prop="id" label="ID" width="55" align="center"></el-table-column>
				<el-table-column prop="eName" label="用户名"></el-table-column>
				<el-table-column prop="pName" label="地点"></el-table-column>
				<el-table-column prop="day" label="天数"></el-table-column>
				<el-table-column prop="time" label="时间"></el-table-column>
				<el-table-column prop="fine" label="借款"></el-table-column>
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
		<el-dialog :title="modelTtile" :visible.sync="editVisible" width="30%">
			<el-form :model="form">
				<el-form-item label="姓名" label-width="80px">
					<div class="block">
						<el-cascader v-model="form.ename" :options="options" :props="{ expandTrigger: 'hover' }"></el-cascader><!-- @change="handleChange" -->
					</div>
				</el-form-item>
				<el-form-item label="地点" label-width="80px">
					<div class="block">
						<el-cascader v-model="form.pname" :options="options" :props="{ expandTrigger: 'hover' }"></el-cascader>
					</div>
				</el-form-item>
				<el-form-item label="天数" label-width="80px">
					<!-- <el-input v-model="form.workAge"></el-input> -->
					<el-select v-model="form.day" placeholder="天数" class="handle-select mr10">
						<el-option key="1" label="1" value="1"></el-option>
						<el-option key="2" label="0.5" value="0.5"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="借款" label-width="80px">
					<el-input v-model="form.fine"></el-input>
				</el-form-item>
			</el-form>
			<span slot="footer" class="dialog-footer">
				<el-button @click="editVisible = false">取 消</el-button>
				<el-button type="primary" @click="saveEdit">确 定</el-button>
			</span>
		</el-dialog>
	</div>
</template>

<script>
	// import {
	// 	fetchData
	// } from '../../api/index';

	// import axios from 'axios';


	export default {
		name: 'salarydetail',
		data() {
			return {
				query: {
					// address: '',
					// name: '',
					time:'',
					pageIndex: 1,
					pageSize: 10
				},
				tableData: [],
				multipleSelection: [],
				delList: [],
				modelTtile: '',
				editVisible: false,
				isEdit: false,
				pageTotal: 0,
				form: {},
				idx: -1,
				id: -1,
				pickerOptions: {
					shortcuts: [{
						text: '最近一周',
						onClick(picker) {
							const end = new Date();
							const start = new Date();
							start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
							picker.$emit('pick', [start, end]);
						}
					}, {
						text: '最近一个月',
						onClick(picker) {
							const end = new Date();
							const start = new Date();
							start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
							picker.$emit('pick', [start, end]);
						}
					}, {
						text: '最近三个月',
						onClick(picker) {
							const end = new Date();
							const start = new Date();
							start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
							picker.$emit('pick', [start, end]);
						}
					}]
				},
				object: {
					id: this.$route.params.id
				},

				// value: [],
				options: [{
					value: 'employees',
					label: '员工',
					children: []
				}, {
					value: 'place',
					label: '地点',
					children: []
				}]
			};
		},
		created() {
			this.query.eId=this.$route.query.eId
			this.getData();
		},
		methods: {
			// 获取 easy-mock 的模拟数据
			getData() {
				console.log(this.query)
				this.$axios
					.post(
						'/salaryDetail/selectByName?currentPage=' +
						this.query.pageIndex +
						'&pageSize=' +
						this.query.pageSize,
						this.query //参数
					)
					.then(res => {
						console.log(res);
						for (let i = 0; i < res.data.employees.length; i++) {
							let obj = {}
							obj.value = res.data.employees[i].id,
								obj.label = res.data.employees[i].name
							this.options[0].children[i] = obj
						}
						for (let i = 0; i < res.data.posts.length; i++) {
							let obj = {}
							obj.value = res.data.posts[i].id,
								obj.label = res.data.posts[i].name
							this.options[1].children[i] = obj
						}
						this.tableData = res.data.data.items;
						this.pageTotal = res.data.data.totalNum || 50;
					})
					.catch(err => {
						console.log(err)
					})
			},
			// 触发搜索按钮
			handleSearch() {
				this.$set(this.query, 'pageIndex', 1);
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
							.post('/salaryDetail/delete', [row.id])
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
					.post('/salaryDetail/delete', this.delList)
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
			// 编辑操作
			showModel(type, row) {
				this.editVisible = true;
				if (type === 'add') {
					this.clearData()
					this.modelTtile = '增加'
				} else if (type === 'update') {
					this.modelTtile = '修改信息'
					this.isEdit = true
					
					for (var key in this.form) {
						this.form[key] = row[key]
					}
					this.form = row
					this.form.id = row.id
				}
			},
			// 保存编辑
			saveEdit() {
				if (this.isEdit) {
					this.$axios
						.post('/salaryDetail/update', this.form)
						.then(res => {
							if (res.data) {
								this.$message.success(`修改第 ${this.idx + 1} 行成功`);
								this.$set(this.tableData, this.idx, this.form);
								this.getData()
								this.clearData()
								this.isEdit = false
								this.editVisible = false
							}
						})
						.catch(err => {
							console.log(err)
						})
				} else {
					this.$axios
						.post('/salaryDetail/insert', this.form)
						.then(res => {
							if (res.data) {
								this.$message.info('添加成功')
								this.getData()
								this.clearData()
								this.editVisible = false
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
					ename: '',
					pname: '',
					day: '',
					fine: ''
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
