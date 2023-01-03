package com.app.vple.controller;

import com.app.vple.domain.dto.AddMateDto;
import com.app.vple.domain.dto.MateInfoDto;
import com.app.vple.service.MateService;
import com.app.vple.service.model.SessionLoginUser;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/mate")
public class MateController {

    private final MateService mateService;

    private final HttpSession httpSession;

    @ApiOperation(value = "메이트 기능 활성화")
    @PostMapping
    public ResponseEntity<?> addMate(@RequestBody AddMateDto mateInfo) {
        try {
            SessionLoginUser loginUser = (SessionLoginUser) httpSession.getAttribute("user");
            mateService.addMate(loginUser.getEmail(), mateInfo.getLatitude(), mateInfo.getLongitude());
            return new ResponseEntity<>(loginUser.getName() + "님 mate 활성화 성공!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "메이트 기능 비활성화")
    @DeleteMapping
    public ResponseEntity<?> deleteMate() {
        try {
            SessionLoginUser loginUser = (SessionLoginUser) httpSession.getAttribute("user");
            mateService.deleteMate(loginUser.getEmail());
            return new ResponseEntity<>(loginUser.getName() + "님 mate 비활성화 성공!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "메이트 찾기 반경 500미터")
    @GetMapping
    public ResponseEntity<?> findMate() {
        try {
            SessionLoginUser loginUser = (SessionLoginUser) httpSession.getAttribute("user");
            List<MateInfoDto> mate = mateService.findMate(loginUser.getEmail());
            return new ResponseEntity<>(mate, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
