package company.board_project.team.mapper;

import company.board_project.apply.entity.Apply;
import company.board_project.team.entity.Team;
import company.board_project.team.entity.TeamMemberInfo;
import company.board_project.user.entity.User;
import company.board_project.team.dto.*;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    Team teamPostDtoToTeam(TeamPostDto requestBody);

    Team teamPatchDtoToTeam(TeamPatchDto requestBody);

    default TeamResponseDto teamToTeamResponseDto(Team team){

        User user = team.getUser();
        List<Apply> applies = new ArrayList<>();

        return TeamResponseDto.builder()
                .userId(user.getUserId())
                .teamId(team.getTeamId())
                .championCount(team.getChampionCount())
                .memberCount(team.getMemberCount())
                .teamName(team.getTeamName())
                .applies(applies)
                .managerName(user.getName())
                .sportsType(String.valueOf(team.getSportsType()))
                .ageType(String.valueOf(team.getAgeType()))
                .locationType(String.valueOf(team.getLocationType()))
                .levelType(String.valueOf(team.getLevelType()))
                .formation(String.valueOf(team.getFormation()))
                .uniformType(String.valueOf(team.getUniformType()))
                .introduction(team.getIntroduction())
                .frequency(String.valueOf(team.getFrequency()))
                .createdAt(team.getCreatedAt())
                .modifiedAt(team.getModifiedAt())
                .build();
    }

    TeamMemberInfo teamMemberInfoPostDtoToTeam(TeamMemberInfoPostDto requestBody);

    default TeamListDto teamListDtoToTeamResponse(List<Team> teams){

        return TeamListDto.builder()
                .teamResponseDtoList(teamsToTeamResponse(teams))
                .build();
    }

    List<TeamResponseDto> teamsToTeamResponse(List<Team> teams);
}
