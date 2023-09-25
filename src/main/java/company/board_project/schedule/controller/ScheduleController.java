package company.board_project.schedule.controller;

import company.board_project.match.normalmatch.repository.MatchRepository;
import company.board_project.schedule.dto.ScheduleListDto;
import company.board_project.schedule.dto.SchedulePostDto;
import company.board_project.schedule.dto.ScheduleResponseDto;
import company.board_project.schedule.entity.Schedule;
import company.board_project.schedule.mapper.ScheduleMapper;
import company.board_project.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final ScheduleMapper scheduleMapper;
    private final MatchRepository matchRepository;

    @PostMapping
    public ResponseEntity postSchedule(@Validated @RequestBody SchedulePostDto requestBody) {

        Schedule schedule = scheduleService.createSchedule(scheduleMapper.schedulePostDtoToSchedule(requestBody));
        ScheduleResponseDto scheduleResponseDto = scheduleMapper.scheduleToScheduleResponse(schedule, matchRepository);

        return ResponseEntity.ok(scheduleResponseDto);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity getSchedules(@PathVariable("scheduleId") @Positive Long scheduleId) {
        List<Schedule> schedules = scheduleService.findSchedules(scheduleId);
        ScheduleListDto scheduleListDto = scheduleMapper.scheduleListDtoToScheduleResponse(schedules, matchRepository);

        return ResponseEntity.ok(scheduleListDto);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity deleteMatch(@PathVariable("scheduleId") Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
