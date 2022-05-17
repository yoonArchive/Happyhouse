package com.ssafy.happyhouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping
	public String goMain() {
		return "index";
	}

	@GetMapping("/signup")
	public String goSignup() {
		return "user/signup";
	}

	@GetMapping("/login")
	public String goLogin() {
		return "user/login";
	}

	@GetMapping("/find/pw")
	public String goFindPassword() {
		return "user/passwordFinding";
	}

	@GetMapping("/user/update")
	public String goUserUpdate() {
		return "user/infoUpdate";
	}

	@GetMapping("/notice/list")
	public String goNoticeList() {
		return "notice/noticeList";
	}

	@GetMapping("/notice/regist")
	public String goNoticeRegist() {
		return "notice/noticeSave";
	}

	@GetMapping("/trade/info")
	public String goTradeInfo() {
		return "tradeinfo/tradeinfo";
	}

	@GetMapping("/trade/search")
	public String goTradeSearch() {
		return "tradeinfo/tradeSearch";
	}
}
