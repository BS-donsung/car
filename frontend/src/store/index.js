import { defineStore } from 'pinia'

export const useCompStore = defineStore('compar', () => {
 let CarInfomation = ['', '']

// 홈 화면에서 시작할때
  function getCarInfo(clickbtn) {
    if (CarInfomation[0] == '') {
      this.CarInfomation[0] = clickbtn
    } else {
      this.CarInfomation[1] = clickbtn
    }
  }

  let user = ''
  // let role = ''
  function setUser(user){
    this.user = user
    // this.role = 
  }
  function getUser(){
    return this.user
  }

  return {
    CarInfomation, getCarInfo, 
    user,
    setUser, getUser
  }
})
