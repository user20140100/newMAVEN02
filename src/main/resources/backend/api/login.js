function loginApi(data) {
  return $axios({
    'url': 'http://localhost:8081/user/login',
    'method': 'post',
    data
  })
}

function logoutApi(){
  return $axios({
    'url': 'http://localhost:8081/user/logout',
    'method': 'post',
  })
}
