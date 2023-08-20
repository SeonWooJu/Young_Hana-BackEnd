package com.example.young_hanabackend.fend.service;

import com.example.young_hanabackend.fend.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    TeamMapper teamMapper;

    @Autowired
    public TeamService(TeamMapper teamMapper) {
        this.teamMapper = teamMapper;
    }

}
