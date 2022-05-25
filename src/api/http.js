import axios from "axios";
const instance = axios.create({
  baseURL: "http://localhost:8080/",
  headers: {
    "Content-Type": "application/json",
  },
});

instance.interceptors.request.use((config)=>{
  console.log(config);
  config.headers["access-token"] = sessionStorage.getItem("access-token");
  return config
},(error) => {
  return Promise.reject(error);
})

export default instance;