<template>
  <Nav />
  <div id="wrap">
    <div class="container notice_container">
      <div class="notice_write">
        <div class="wright_wrap">
          <h3 class="box_title">
            커뮤니티 글쓰기
          </h3>

          <form class="form_box">
            <div class="in_title">
              <h3 class="sub_title">
                제목
              </h3>
              <textarea
                v-model="notice_data.title"
                maxlength="100"></textarea>
            </div>

            <div class="in_title contents">
              <h3 class="sub_title">
                내용
              </h3>
              <!-- <textarea v-model="form.description"></textarea> -->
              <QuillEditor
                v-model:content="notice_data.content"
                content-type="html"
                :options="form.editorOption"
                style="height:400px;"
                theme="snow" />
            </div>

            <div class="button_wrap">
              <button
                class="writing_btn back_btn"
                @click="backComFrm">
                목록
              </button>
              <button
                type="reset"
                class="writing_btn del_btn"
                @click="backComFrm">
                취소
              </button>
              <button
                class="writing_btn writing_done"
                @click.prevent="completeBtn">
                작성 완료
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import Nav from '@/view/ToNav.vue'
import router from '@/router'
import { reactive, ref } from 'vue'
import { URL } from '@/components/global'
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css'

const form = ref({
  title: null, //제목
  content: null,
  editorOption: {
    modules: {
      toolbar: [
        ['bold', 'italic', 'underline', 'strike'],
        ['blockquote', 'code-block'],
        [{ header: [1, 2, 3, 4, 5, 6, false] }],
        [{ list: 'ordered' }, { list: 'bullet' }],
        //  [{script:'sub'},{script:'super'}],
        [{ indent: '-1' }, { indent: '+1' }],
        [{ color: [] }, { background: [] }]
      ]
    },
  }, //내용
})

const notice_data = reactive({
  title: '',
    writerDto: {
      username:'username1',
      nickname:'유저1',
    },
    content: '',
})

const completeBtn = async () => {
  console.log(notice_data)
  const requestOptions = {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(notice_data),
  }
  try {
    const res = await fetch(URL + '/board/post', requestOptions)
    console.log(res.json())
    console.log('작성완료')
    router.push({
      path: '/community',
    })
  } catch (error) {
    console.log('게시글 등록에 실패했습니다', error)
  }
}
  const backComFrm = () => {
    router.push({
      path: '/community',
    })
  }
</script>

<style scoped>
@charset "UTF-8";
@import "@/assets/notice.css";
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&family=Open+Sans:ital,wght@0,300;0,600;1,300;1,600&family=Poor+Story&family=Poppins:wght@300;400;500;600;700&display=swap');

* {
  box-sizing: border-box;
  font-family: 'Poppins', sans-serif;
}

#wrap {
  display: flex;
  min-height: 100vh;
  flex-direction: column;
}

.container.notice_container {
  height: 100%;
  padding-bottom: 80px;
  background: white;
  max-width: 1060px;
  margin: 0 auto;
  padding-top: 80px;
  flex: 1;
  margin-top: 80px;
}

.notice_write {
  background: white;
  width: 100%;
  position: relative;
}

.wright_wrap {
  padding: 20px 20px 40px;
  background: white;
  box-shadow: 0 0 10px -3px #d0d0d0;
}


.wright_wrap .form_box {
  display: grid;
  gap: 20px;
  position: relative;
}

.wright_wrap .form_box .span1 {
  grid-column: span 2;
}

.wright_wrap {
  padding-bottom: 80px;
}

.box_title {
  font-size: 22px;
}

.box_title {
  font-size: 22px;
  font-weight: bold;
}

/* form tage css */
.in_title {
  display: grid;
  position: relative;
}

.sub_title {
  margin-bottom: 5px;
  font-size: 14px;
}

textarea {
  resize: none;
  border: 1px solid #dbdbdb;
  padding: 0px 10px 0 10px;
  font-size: 13px;
  height: 40px;
  line-height: 38px;
  color: #333;
  font-family: 'Poppins', sans-serif;
}

textarea:focus {
  border: 1px solid #dbdbdb;
  box-shadow: 0 0 10px -5px #bebebe;
}

.sub_title {
  margin-bottom: 5px;
  font-size: 14px;
}

.notice_sub_title {
  font-size: 14px;
  color: #999999;
}

.button_wrap {
  display: flex;
  gap: 15px;
  justify-self: end;
}

.writing_btn {
  border: none;
  background: #1B1B1E;
  color: white;
  padding: 8px 15px;
  font-size: 14px;
  cursor: pointer;
  transition: all 0.3s;
  border: 1px solid #1B1B1E;
}
</style>
