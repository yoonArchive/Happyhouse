import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/about",
    name: "about",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/AboutView.vue"),
  },
  // 회원 관리
  {
    path: "/user",
    name: "User",
    component: () => import("@/views/UserView.vue"),
    children: [
      // 회원가입
      {
        path: "signup",
        name: "SignUp",
        component: () => import("@/components/user/SignUp.vue"),
      },
      // 로그인
      {
        path: "login",
        name: "LogIn",
        component: () => import("@/components/user/LogIn.vue"),
      },
      // 아이디 찾기
      {
        path: "findid",
        name: "FindId",
        component: () => import("@/components/user/IdFind.vue"),
      },
      // 비밀번호 찾기
      {
        path: "findpw",
        name: "FindPw",
        component: () => import("@/components/user/PasswordFind.vue"),
      },
      // 회원정보 수정
      {
        path: "modify/:userId",
        name: "UserModify",
        component: () => import("@/components/user/infoUpdate.vue"),
      },
    ],
  },
  // 공지사항
  {
    path: "/notice",
    name: "Notice",
    component: () => import("@/views/NoticeView.vue"),
    children: [
      // 공지사항 목록
      {
        path: "",
        name: "NoticeList",
        component: () => import("@/components/notice/NoticeList.vue"),
      },
      // 공지사항 등록
      {
        path: "create",
        name: "NoticeCreate",
        component: () => import("@/components/notice/NoticeCreate.vue"),
      },
      // 공지사항 상세보기
      {
        path: ":noticeId",
        name: "NoticeDetail",
        component: () => import("@/components/notice/NoticeDetail.vue"),
      },
      // 공지사항 수정
      {
        path: "modify/:noticeId",
        name: "NoticeModify",
        component: () => import("@/components/notice/NoticeModify.vue"),
      },
    ],
  },
  // Q&A
  {
    path: "/qna",
    name: "QnA",
    component: () => import("@/views/QnAView.vue"),
    children: [
      // 질문 목록
      {
        path: "",
        name: "QuestionList",
        component: () => import("@/components/qna/QuestionList.vue"),
      },
      // 질문 등록
      {
        path: "create",
        name: "QuestionCreate",
        component: () => import("@/components/qna/QuestionCreate.vue"),
      },
      // 질문 상세보기
      {
        path: ":questionId",
        name: "QuestionDetail",
        component: () => import("@/components/qna/QuestionDetail.vue"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
