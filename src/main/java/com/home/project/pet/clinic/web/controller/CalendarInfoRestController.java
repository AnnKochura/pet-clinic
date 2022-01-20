package com.home.project.pet.clinic.web.controller;

import com.home.project.pet.clinic.repository.calender.CalendarInfoRepository;
import com.home.project.pet.clinic.entity.calender.ScheduleCalendar;
import com.home.project.pet.clinic.repository.calender.ScheduleCalendarRepository;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("restcalender")
public class CalendarInfoRestController {

    final CalendarInfoRepository cInfo;
    final ScheduleCalendarRepository sRepo;

    public CalendarInfoRestController(CalendarInfoRepository cInfo, ScheduleCalendarRepository sRepo) {
        this.cInfo = cInfo;
        this.sRepo = sRepo;
    }

    @GetMapping("/calendarInfo")
    public Map<String, Object> calendarInfo() {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("calendarInfos", cInfo.findAll());
        return hm;
    }

    @PostMapping("/addSchedule")
    public Map<String, Object> calendarInfo(@RequestBody ScheduleCalendar scheduleCalendar) {
        Map<String, Object> hm = new LinkedHashMap<>();
        Optional<ScheduleCalendar> isThere = sRepo.findScheduleId(scheduleCalendar.getId());
        isThere.ifPresent(calendar -> scheduleCalendar.setSid(calendar.getSid()));
        ScheduleCalendar s = sRepo.saveAndFlush(scheduleCalendar);
        hm.put("scheduleCalendar", s);
        return hm;
    }

    @GetMapping("/listSchedule/{calendarId}")
    public Map<String, Object> listSchedule(@PathVariable String calendarId) {
        Map<String, Object> hm = new LinkedHashMap<>();
        List<ScheduleCalendar> s = sRepo.findByCalendarIdEquals(calendarId);
        hm.put("listSchedule", s);
        return hm;
    }


    @DeleteMapping("/deleteSchedule/{stSid}")
    public Boolean deleteSchedule(@PathVariable String stSid) {
        Optional<ScheduleCalendar> isThere = sRepo.findScheduleId(stSid);
        if (isThere.isPresent()) {
            sRepo.deleteById(isThere.get().getSid());
            return true;
        } else {
            return false;
        }
    }
}