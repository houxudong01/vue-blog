<template>
  <div>
    <Header></Header>

    <div class="m-content">

      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="标题" prop="title">
          <el-input v-model="ruleForm.title"></el-input>
        </el-form-item>

        <el-form-item label="摘要" prop="description">
          <el-input type="textarea" v-model="ruleForm.description"></el-input>
        </el-form-item>

        <el-form-item label="内容" prop="content">
          <mavon-editor v-model="ruleForm.content"></mavon-editor>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">保存</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>

    </div>



  </div>
</template>

<script>
  import Header from "../components/Header";
  export default {
    name: "BlogEdit.vue",
    components: {Header},
    data() {
      return {
        ruleForm: {
          id: '',
          title: '',
          description: '',
          content: ''
        },
        rules: {
          title: [
            { required: true, message: '请输入标题', trigger: 'blur' },
            { min: 3, max: 25, message: '长度在 3 到 25 个字符', trigger: 'blur' }
          ],
          description: [
            { required: true, message: '请输入摘要', trigger: 'blur' }
          ],
          content: [
            { trequired: true, message: '请输入内容', trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            const _this = this

            var params = new URLSearchParams();
            params.append('id', this.ruleForm.id);
            params.append('title', this.ruleForm.title);
            params.append('description', this.ruleForm.description);
            params.append('content', this.ruleForm.content);
            this.$axios.post('/blog/edit', params, {
              headers: {
                "Authorization": localStorage.getItem("token")
              }
            }).then(res => {
              console.log("编辑")
              console.log(res)
              _this.$router.push("/blog/"+res.data.data.id)

            })

          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    },
    created() {
      const blogId = this.$route.params.blogId
      console.log(blogId)
      const _this = this
      if(blogId) {
        this.$axios.get('/blog/' + blogId).then(res => {
          const blog = res.data.data
          _this.ruleForm.id = blog.id
          _this.ruleForm.title = blog.title
          _this.ruleForm.description = blog.description
          _this.ruleForm.content = blog.content
        })
      }

    }
  }
</script>

<style scoped>
  .m-content {
    text-align: center;
  }
</style>