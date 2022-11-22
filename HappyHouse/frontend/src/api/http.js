import axios from "axios";
const instance = axios.create({
  baseURL: "http://localhost:8080/",
  headers: {
    "Content-Type": "application/json",
  },
});

instance.interceptors.request.use(
  (config) => {
    config.headers["access-token"] = sessionStorage.getItem("access-token");
    console.log(config);
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default instance;
