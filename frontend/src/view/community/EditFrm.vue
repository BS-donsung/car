<template>
  <div id="wrap">
    <div class="container notice_container">
      <!-- <Sidebar /> -->
      <div class="notice_write">
        <div class="wright_wrap">
          <h3 class="box_title">
            글 수정
          </h3>
          <form
            class="form_box"
            @submit.prevent="fetchEdit">
            <div class="in_title">
              <h3 class="sub_title">
                제목
              </h3>
              <textarea
                v-model="state.form.title"
                class="title"
                maxlength="100"></textarea>
            </div>
            <div class="in_title contents">
              <h3 class="sub_title">
                내용
              </h3>
              <QuillEditor
                v-model:content="state.form.content"
                content-type="html"
                :options="state.form.editorOption"
                style="height:400px;"
                theme="snow" />
            </div>
            <div class="button_wrap">
              <button
                class="writing_btn back_btn"
                @click="backload()">
                목록
              </button>
              <button
                class="writing_btn writing_done"
                type="submit"
                @click="editfrm">
                수정 완료
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { URL } from '@/components/global'
import axios from 'axios'
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css'


const route = useRoute()
const router = useRouter()
const bno = route.params.bno
const state = reactive({
  form: {
    title: '',
    content: '',
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
  },
})

const backload = () => {
  router.push({
    name: 'community',
  })
}

const fetchEdit = async () => {
  const postdata = {
    title: state.form.title,
    content: state.form.content,
  }
  if (state.form.title == '') {
    alert('제목이 비었습니다.\n제목을 입력바랍니다.')
    return false
  } else if (state.form.content == '') {
    alert('내용이 비었습니다.\n내용 입력바랍니다.')
    return false
  }

  try {
    if (confirm('수정하시겠습니까?')) {
      await axios.put(URL + `/board/modify/${bno}`, postdata, {
        withCredentials: true,
      })
        .then((res) => {
          console.log(res.data)
          alert('수정이 완료되었습니다.')
          console.log('수정성공')
        })
      router.push({ name: 'detailnotice' })
    }
  } catch (error) {
    alert('수정에 실패했습니다.')
    console.log(error)
  }
}


</script>

<style scoped>
@charset "UTF-8";
@import "@/assets/notice.css";
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&family=Open+Sans:ital,wght@0,300;0,600;1,300;1,600&family=Poor+Story&family=Poppins:wght@300;400;500;600;700&display=swap');

#wrap {
  display: flex;
  flex-direction: column;
  background: #F0F1F8;
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
</style>
