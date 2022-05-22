package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.domain.HouseInfo;
import com.ssafy.happyhouse.domain.RoadBasedAddress;
import com.ssafy.happyhouse.dto.HouseListRequest;
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
    public ResponseEntity<List<String>> getDongList(@RequestParam Map<String, String> siAndGu) {
        return new ResponseEntity<>(tradeService.getDongList(siAndGu), HttpStatus.OK);
    }

    @GetMapping("/search/dong")
    public ResponseEntity<List<HouseListRequest>> searchByDong(@RequestParam Map<String, String> address) {
        return new ResponseEntity<>(tradeService.searchByDong(address), HttpStatus.OK);
    }

    @GetMapping("/search/apt")
    public ResponseEntity<List<HouseListRequest>> searchByApt(@RequestParam String aptName) {
        return new ResponseEntity<>(tradeService.searchByApt(aptName), HttpStatus.OK);
    }
}
