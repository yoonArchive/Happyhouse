import { apiInstance } from "./index.js";

const api = apiInstance();

function sidoList(success, fail) {
  api.get(`/trade/si`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/trade/gu`, { params: params }).then(success).catch(fail);
}

function dongList(params, success, fail) {
  api.get(`/trade/dong`, { params: params }).then(success).catch(fail);
}

async function houseList(params, success, fail) {
  await api.get(`/trade/list`, { params: params }).then(success).catch(fail);
}

export { sidoList, gugunList, dongList, houseList };
