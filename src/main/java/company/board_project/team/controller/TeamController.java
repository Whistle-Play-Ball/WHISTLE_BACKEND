package company.board_project.team.controller;

import company.board_project.common.resolver.AuthenticatedUser;
import company.board_project.response.MultiResponseDto;
import company.board_project.team.entity.Team;
import company.board_project.team.mapper.TeamMapper;
import company.board_project.team.service.TeamService;
import company.board_project.team.dto.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/teams")
public class TeamController {
    private final TeamService teamService;
    private final TeamMapper teamMapper;

    @Transactional
    @PostMapping
    public ResponseEntity postTeam(@Valid @RequestBody TeamPostDto requestBody, @AuthenticatedUser String email) {
        log.info("email : {}", email);
        Team team = teamService.createTeam(teamMapper.teamPostDtoToTeam(requestBody), email);
        TeamResponseDto teamResponseDto = teamMapper.teamToTeamResponseDto(team);
        log.info("teamResponseDto : {}", teamResponseDto.toString());
        log.info("email : {}", email);

        TeamMemberListPostDto teamMemberListPostDto = new TeamMemberListPostDto();
        teamMemberListPostDto.setName(teamResponseDto.getManagerName());
        teamMemberListPostDto.setTeamMemberRole("MANAGER");
        teamService.createTeamMemberList(teamMapper.teamMemberListPostDtoToTeam(teamMemberListPostDto),teamResponseDto.getUserId(),teamResponseDto.getTeamId());
        log.info("teamMemberListPostDto[{}]", teamMemberListPostDto.toString());

        return ResponseEntity.ok(teamResponseDto);
    }

    @PatchMapping("/{teamId}")
    public ResponseEntity patchTeam(@Valid @RequestBody TeamPatchDto requestBody,
                                    @PathVariable("teamId") @Positive Long teamId) {
        Team team = teamService.updateTeam(
                teamMapper.teamPatchDtoToTeam(requestBody),
                teamId);

        team.setTeamId(teamId);
        TeamResponseDto userResponseDto = teamMapper.teamToTeamResponseDto(team);

        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/{teamId}")
    public ResponseEntity getTeam(@PathVariable("teamId") @Positive Long teamId) {
        Team team = teamService.findTeam(teamId);
        TeamResponseDto teamResponse = teamMapper.teamToTeamResponseDto(team);
        log.info("팀 리스 폰스 {}", teamResponse);

        return ResponseEntity.ok(teamResponse);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity getTeamByUserId(@PathVariable("userId") @Positive Long userId) {
        Team team = teamService.findTeamByUserId(userId);
        TeamResponseDto teamResponse = teamMapper.teamToTeamResponseDto(team);

        return ResponseEntity.ok(teamResponse);
    }


    @GetMapping("/league/{leagueId}")
    public ResponseEntity getAllTeamsByLeagueId(@PathVariable("leagueId") @Positive Long leagueId) {
        List<Team> teams = teamService.findAllTeamByLeagueId(leagueId);
        TeamListDto teamListDto = teamMapper.teamListDtoToTeamResponse(teams);

        return new ResponseEntity<>(teamListDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getTeams(@Positive @RequestParam("page") int page,
                                   @Positive @RequestParam("size") int size) {
        Page<Team> pageTeams = teamService.findTeams(page - 1, size);
        List<Team> teams = pageTeams.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(teamMapper.teamsToTeamResponse(teams),
                        pageTeams),
                HttpStatus.OK);
    }

    @GetMapping("/honor/high")
    public ResponseEntity getTeamsByHighestHonorScore() {
        List<Team> teams = teamService.findByHighestHonorScore();
        List<TeamResponseDto> teamResponseDtos = teamMapper.teamsToTeamResponse(teams);

        return ResponseEntity.ok(teamResponseDtos);
    }

    @GetMapping("/honor/low")
    public ResponseEntity getTeamsByLowestHonorScore() {
        List<Team> teams = teamService.findByLowestHonorScore();
        List<TeamResponseDto> teamResponseDtos = teamMapper.teamsToTeamResponse(teams);

        return ResponseEntity.ok(teamResponseDtos);
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity deleteTeam(@PathVariable("teamId") @Positive Long teamId) {
        teamService.deleteTeam(teamId);

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}

