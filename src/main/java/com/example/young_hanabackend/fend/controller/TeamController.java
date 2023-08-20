package com.example.young_hanabackend.fend.controller;

import com.example.young_hanabackend.entity.Team;
import com.example.young_hanabackend.fend.service.TeamService;
import com.example.young_hanabackend.security.logic.JwtToken;
import com.example.young_hanabackend.security.model.FendResponseObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/admin/team")
public class TeamController {

//    TeamService teamService;
//    JwtToken jwtToken;
//
//    @Autowired
//    public TeamController(TeamService teamService, JwtToken jwtToken) {
//        this.teamService = teamService;
//        this.jwtToken = jwtToken;
//    }
//
//    @PostMapping("/leader")
//    public ResponseEntity<FendResponseObject<List<Team>>> postTeamLeader(
//            HttpServletRequest req,
//            HttpServletResponse res,
//            @RequestBody
//    ) {
//        String token = jwtToken.resolveToken(req);
//        FendResponseObject<List<Team>> ro = new FendResponseObject<>("Success");
//        ro.setMessage("리더 지정");
//
//        if (!jwtToken.validateToken(token) || !jwtToken.checkAdminRole(token))
//            return new ResponseEntity<>(ro, HttpStatus.FORBIDDEN);
//
//        ro.setData(teamService.postTeamLeader());
//
//        return new ResponseEntity<>(ro, HttpStatus.OK);
//    }
}
