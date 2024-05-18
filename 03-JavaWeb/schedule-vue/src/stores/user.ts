import { reactive } from 'vue'
import { defineStore } from 'pinia'

export const useUserStore = defineStore('user', () => {
  let sysUser = reactive({
    uid: 0,
    username: ''
  })

  return { sysUser }
})
