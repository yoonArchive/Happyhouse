import axios from "axios";
import {API_BASE_URL} from "@/config";

// axios 객체 생성
function apiInstance() {
    const instance = axios.create({
        baseURL: API_BASE_URL,
        headers: {
            "Content-type": "application/json",
        },
    });
    instance.interceptors.request.use((config) => {
        console.log(config);
        config.headers["access-token"] = sessionStorage.getItem("access-token");
        return config
    }, (error) => {
        return Promise.reject(error);
    })
    return instance;
}

export {apiInstance};
