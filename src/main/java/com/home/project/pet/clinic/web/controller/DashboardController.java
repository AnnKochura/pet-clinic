package com.home.project.pet.clinic.web.controller;

import com.home.project.pet.clinic.entity.projections.DashboardInfo;
import com.home.project.pet.clinic.entity.projections.DiaryToDashboard;
import com.home.project.pet.clinic.entity.UserFollowIn;
import com.home.project.pet.clinic.repository.DashboardRepository;
import com.home.project.pet.clinic.repository.UserFollowInRepository;
import com.home.project.pet.clinic.repository.UserRepository;
import com.home.project.pet.clinic.utils.Util;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.*;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private static final Logger LOG = Logger.getLogger(DashboardController.class);

    final UserFollowInRepository userFollowInRepository;
    final UserRepository userRepository;
    final DashboardRepository dashboardRepository;

    public DashboardController(UserFollowInRepository userFollowInRepository, UserRepository userRepository, DashboardRepository dashboardRepository) {
        this.userFollowInRepository = userFollowInRepository;
        this.userRepository = userRepository;
        this.dashboardRepository = dashboardRepository;
    }

    @GetMapping("")
    public String dashboard(Model model) {
        controlUserFollowIn();
        Optional<DashboardInfo> optionalDashboardInfo = dashboardRepository.getViewDashboard();
        if (optionalDashboardInfo.isPresent()) {
            float rateDiary = 0.0f;
            float rateCustomer = 0.0f;
            float rateCiro = 0.0f;
            int diaryToday = 0;
            int diaryYesterday = 0;
            int registercustomerToday = 0;
            int registercustomerYesterday = 0;
            int todayCiro = 0;
            int yesterdayCiro = 0;
            int stockValue = 0;
            try {
                diaryToday = Integer.parseInt(optionalDashboardInfo.get().getDiaryToday());
                diaryYesterday = Integer.parseInt(optionalDashboardInfo.get().getDiaryYesterday());
                registercustomerToday = Integer.parseInt(optionalDashboardInfo.get().getRegisterCustomerToday());
                registercustomerYesterday = Integer.parseInt(optionalDashboardInfo.get().getRegisterCustomerYesterday());
                todayCiro = Integer.parseInt(optionalDashboardInfo.get().getTodayCiro());
                yesterdayCiro = Integer.parseInt(optionalDashboardInfo.get().getYesterdayCiro());
                stockValue = Integer.parseInt(optionalDashboardInfo.get().getStockValue());
            } catch (Exception e) {
                Util.log("DashboardController Error : " + e, this.getClass());
            }
            if (diaryYesterday != 0) {
                rateDiary = ((diaryToday - diaryYesterday) / (float) diaryYesterday);
                rateDiary = rateDiary * 100;
            }
            if (registercustomerYesterday != 0) {
                rateCustomer = ((registercustomerToday - registercustomerYesterday) / (float) registercustomerYesterday);
                rateCustomer = rateCustomer * 100;
            }
            if (yesterdayCiro != 0) {
                rateCiro = ((todayCiro - yesterdayCiro) / (float) yesterdayCiro);
                rateCiro = rateCiro * 100;
            }

            String key = " % Compared to the previous day";

            model.addAttribute("rateDiary", "" + rateDiary + key);
            model.addAttribute("rateCustomer", "" + rateCustomer + key);
            model.addAttribute("rateCiro", "" + rateCiro + key);
            model.addAttribute("diaryToday", diaryToday);
            model.addAttribute("customerToday", registercustomerToday);
            model.addAttribute("todayCiro", todayCiro);
            model.addAttribute("stockValue", stockValue);

            // Appointments with today's date
            List<DiaryToDashboard> dashExpiredDiaryList = dashboardRepository.getExpiredDiary();
            model.addAttribute("dashExpiredDiaryList",dashExpiredDiaryList);
            // Upcoming appointments today
            List<DiaryToDashboard> dashUpcomingDiaryList = dashboardRepository.getUpcomingDiary();
            model.addAttribute("dashUpcomingDiaryList",dashUpcomingDiaryList);

        }
        return "dashboard";
    }

    private void controlUserFollowIn() {
        Authentication aut = SecurityContextHolder.getContext().getAuthentication();
        LOG.info("Authentication: " + aut);

        String email = aut.getName(); // username
        LOG.info("Authentication email: " + email);
        if (email != null) {
            //Has this user logged in in the last half hour?
            Optional<UserFollowIn> optUserFollowIn = userFollowInRepository.findLastLoginProcesses(email);
            if (!optUserFollowIn.isPresent()) {
                //Written if this user has not logged in in the last half hour.
                UserFollowIn userFollowIn = new UserFollowIn();
                userFollowInRepository.save(userFollowIn);
            }
        }

    }

    @GetMapping("/dayOfWeeks")
    @ResponseBody
    public List<String> getCustomerRegisterDayOfWeeks(){
        List<String> ls = new ArrayList<>();
        Object[] registerCustomerDaysOfWeek = dashboardRepository.registerCustomerDaysOfWeek();
        for (int i = 0; i<7; i++){
            ls.add(String.valueOf(((BigInteger)(((Object[]) registerCustomerDaysOfWeek[0])[i])).intValue()));
        }
        return ls;
    }

    @GetMapping("/petTypeCount")
    @ResponseBody
    public Map<String,Double> getPetTypeCount(){
        Map<String,Double> hm = new LinkedHashMap<>();
        try {
            List<Object[]> petTypeNumber = dashboardRepository.petTypeCount();
            double sum = 0;
            for (Object[] objects : petTypeNumber) {
                sum += ((BigInteger) (((Object[]) objects)[1])).doubleValue();
            }
            for (Object[] objects : petTypeNumber) {
                double temp = ((BigInteger) (((Object[]) objects)[1])).doubleValue();
                Double temp2 = (temp * 100) / sum;
                Formatter formatter = new Formatter();
                formatter.format("%.2f", temp2);
                String temp3 = formatter.toString().replace(',', '.');
                Double newValue = Double.parseDouble(temp3);

                hm.put(String.valueOf(((((Object[]) objects)[0]))), newValue);
            }
        } catch (Exception ex) {
            System.err.println("getPetTypeCount Error = " + ex);
        }
        return hm;
    }

}
