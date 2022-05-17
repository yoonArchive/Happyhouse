package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.dto.TradeInfo;
import com.ssafy.happyhouse.model.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/trade")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @GetMapping("/si")
    public ResponseEntity<List<String>> getSiList() {
        return new ResponseEntity<>(tradeService.getSiList(), HttpStatus.OK);
    }

    @GetMapping("/gu")
    public ResponseEntity<List<String>> getGuList(@RequestParam String si) {
        return new ResponseEntity<>(tradeService.getGuList(si), HttpStatus.OK);
    }

    @GetMapping("/dong")
    public ResponseEntity<List<String>> getDongList(@RequestParam String gu) {
        return new ResponseEntity<>(tradeService.getDongList(gu), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<TradeInfo>> getList(@RequestParam Map<String, String> address) {
        return new ResponseEntity<>(tradeService.getList(address), HttpStatus.OK);
    }

    @GetMapping("/search/apt")
    public ResponseEntity<List<TradeInfo>> searchByApt(@RequestParam String aptName) {
        return new ResponseEntity<>(tradeService.searchByApt(aptName), HttpStatus.OK);
    }

    @GetMapping("/search/dong")
    public ResponseEntity<List<TradeInfo>> searchByDong(@RequestParam String dongName) {
        return new ResponseEntity<>(tradeService.searchByDong(dongName), HttpStatus.OK);
    }
}
