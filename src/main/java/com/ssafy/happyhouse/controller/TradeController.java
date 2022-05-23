package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.dto.HouseDetailResponse;
import com.ssafy.happyhouse.dto.HouseListResponse;
import com.ssafy.happyhouse.model.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    public ResponseEntity<List<HouseListResponse>> searchByDong(@RequestParam Map<String, String> address) {
        return new ResponseEntity<>(tradeService.searchByDong(address), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<HouseListResponse>> search(@RequestParam String keyword) {
        return new ResponseEntity<>(tradeService.search(keyword), HttpStatus.OK);
    }

    @GetMapping("/{aptCode}")
    public ResponseEntity<HouseDetailResponse> getDetail(@PathVariable String aptCode) throws Exception {
        return new ResponseEntity<>(tradeService.getDetail(new BigDecimal(aptCode)), HttpStatus.OK);
    }
}
