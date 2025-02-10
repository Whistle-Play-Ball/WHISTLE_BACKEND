package company.board_project.match.match.mapper;

import company.board_project.match.match.dto.LeagueMatchResponseDto;
import company.board_project.match.match.dto.MatchPostDto;
import company.board_project.match.match.entity.Match;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LeagueMatchMapper {
    Match leagueMatchPostDtoToLeagueMatch(MatchPostDto requestBody);
    LeagueMatchResponseDto leagueMatchToLeagueMatchResponseDto(Match requestBody);
}
