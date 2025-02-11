package company.board_project.match.match.controller;

import company.board_project.common.resolver.AuthenticatedUser;
import company.board_project.constant.MatchType;
import company.board_project.match.match.dto.*;
import company.board_project.match.match.entity.Match;
import company.board_project.match.match.mapper.MatchMapper;
import company.board_project.match.match.service.MatchService;
import company.board_project.response.MultiResponseDto;
import company.board_project.response.SingleResponseDto;
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
@RequestMapping("/api/matches")
public class MatchController {
    private final MatchService matchService;
    private final MatchMapper matchMapper;

    @PostMapping
    public ResponseEntity postMatch(@Validated @RequestBody MatchPostDto requestBody, @AuthenticatedUser String email) {

        Match match = new Match();
        if (requestBody.getMatchType().equals(MatchType.NORMAL)) {
            match = matchService.createMatch(email, requestBody, matchMapper.matchPostDtoToMatch(requestBody));
            CreateNormalMatchResponseDto createNormalMatchResponseDto = matchMapper.matchToCreateNormalMatchResponseDto(match);
            return new ResponseEntity<>(new SingleResponseDto<>(createNormalMatchResponseDto), HttpStatus.CREATED);

        } else if (requestBody.getMatchType().equals(MatchType.LEAGUE)) {
            match = matchService.createLeagueMatch(email, requestBody);
            CreateLeagueMatchResponseDto createLeagueMatchResponseDto = matchMapper.matchToCreateLeagueMatchResponseDto(match);
            return new ResponseEntity<>(new SingleResponseDto<>(createLeagueMatchResponseDto), HttpStatus.CREATED);

        } else {
            match = matchService.createTournamentMatch(email, requestBody);
            CreateTournamentMatchResponseDto createTournamentMatchResponseDto = matchMapper.matchToCreateTournamentMatchResponseDto(match);
            return new ResponseEntity<>(new SingleResponseDto<>(createTournamentMatchResponseDto), HttpStatus.CREATED);
        }
    }

    @GetMapping("/{matchId}")
    public ResponseEntity getMatch(@PathVariable("matchId") Long matchId) {
        Match match = matchService.findMatch(matchId);
        MatchResponseDto matchResponseDto = matchMapper.matchToMatchResponse(match);

        return ResponseEntity.ok(matchResponseDto);
    }

    @GetMapping
    public ResponseEntity getMatches(@Positive @RequestParam(value = "page", defaultValue = "1") int page,
                                     @Positive @RequestParam(value = "size", defaultValue = "40") int size) {

        Page<Match> pageMatches = matchService.findMatches(page - 1, size);
        List<Match> matches = pageMatches.getContent();
        log.info("전체 요청 :" + matches);
        return new ResponseEntity<>(
                new MultiResponseDto<>(matchMapper.matchesToMatchesResponse(matches),
                        pageMatches),
                HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity getSearch(@RequestParam(value = "keyword", required = false) String keyword) {
        List<Match> matches = matchService.findAllSearch(keyword);
        MatchListDto matchListDto = matchMapper.matchListDtoToMatchResponse(matches);

        return ResponseEntity.ok(matchListDto);
    }

    @GetMapping("/search/username")
    public ResponseEntity getSearchByUserName(@RequestParam(value = "name", required = false) String name) {
        List<Match> matches = matchService.findAllSearchByUserName(name);
        MatchListDto matchListDto = matchMapper.matchListDtoToMatchResponse(matches);

        return ResponseEntity.ok(matchListDto);
    }

    @GetMapping("/newest")
    public ResponseEntity getMatchesNewest() {
        List<Match> matches = matchService.findMatchesNewest();
        List<MatchResponseDto> matchResponseDto = matchMapper.matchesToMatchesResponse(matches);

        return ResponseEntity.ok(matchResponseDto);
    }

    @GetMapping("/latest")
    public ResponseEntity getMatchesLatest() {
        List<Match> matches = matchService.findMatchesLatest();
        List<MatchResponseDto> matchResponseDto = matchMapper.matchesToMatchesResponse(matches);

        return ResponseEntity.ok(matchResponseDto);
    }

    @PatchMapping("/{matchId}")
    public ResponseEntity patchMatch(@RequestBody MatchPatchDto requestBody,
                                     @PathVariable("matchId") Long matchId) {
        requestBody.updateId(matchId);
        Match match = matchService.updateMatch(
                matchMapper.matchPatchDtoToMatch(requestBody));

        MatchResponseDto matchResponse = matchMapper.matchToMatchResponse(match);

        return ResponseEntity.ok(matchResponse);
    }

    @DeleteMapping("/{matchId}")
    public ResponseEntity deleteMatch(@PathVariable("matchId") Long matchId) {
        matchService.deleteMatch(matchId);

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}