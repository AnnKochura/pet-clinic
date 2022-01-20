package com.home.project.pet.clinic.web.controller;

import com.home.project.pet.clinic.entity.projections.DiaryInfoToIstaticticPageCustomer;
import com.home.project.pet.clinic.entity.projections.DiaryInfoToIstatisticPage;
import com.home.project.pet.clinic.repository.DiaryRepository;
import com.home.project.pet.clinic.repository.UserFollowInRepository;
import com.home.project.pet.clinic.utils.Util;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@RequestMapping("/statistic")
@Controller
public class StatisticController {

    final DiaryRepository diaryRepository;
    final UserFollowInRepository userFollowInRepository;

    public StatisticController(DiaryRepository diaryRepository, UserFollowInRepository userFollowInRepository) {
        this.diaryRepository = diaryRepository;
        this.userFollowInRepository = userFollowInRepository;
    }

    @GetMapping
    public String statistic(Model model) {
        Optional<DiaryInfoToIstatisticPage> opt = diaryRepository.findBusiestDayOfWeek();
        Optional<DiaryInfoToIstaticticPageCustomer> opt2 = diaryRepository.findBusiestCustomer();
        String date = "";
        int randevuNumber = 0;
        if (opt.isPresent()) {
            date = castingDayName(opt.get().getDaysOfWeek());
            try {
                randevuNumber = Integer.parseInt(opt.get().getDiaryNumber());
            } catch (Exception e) {
                Util.log("StatisticController Error" + e, this.getClass());
            }
        }
        int randevuNumber2 = 0;
        String cuname = "";
        if (opt2.isPresent()) {
            try {
                randevuNumber2 = Integer.parseInt(opt2.get().getCount_diary());
                cuname = opt2.get().getCu_name();
            } catch (Exception e) {
                Util.log("StatisticController Error" + e, this.getClass());
            }
        }
        Optional<String> optional = userFollowInRepository.getNumber();
        int ziyaretsayisi = 0;
        if (optional.isPresent()) {
            try {
                ziyaretsayisi = Integer.parseInt(optional.get());
            } catch (Exception e) {
                Util.log("StatisticController Error : " + e, this.getClass());
            }
        }

        List<Object[]> customerList = userFollowInRepository.ls();//How Much Which Customer Earned This Week
        List<Object[]> saleTypeList = userFollowInRepository.ls2();//Earnings by Payment Type

        saleTypeList.forEach(item -> {
            if (item[0].equals(1)) {
                item[0] = "Cash";
            } else if (item[0].equals(2)) {
                item[0] = "Card";
            } else {
                item[0] = "Transfer";
            }
        });

        model.addAttribute("enyogungun", "" + "Day: " + date);
        model.addAttribute("randevusayisi", "Number of Appointments: " + randevuNumber);
        model.addAttribute("customername", "Customer name: " + cuname);
        model.addAttribute("count_diary", "Number of Appointments: " + randevuNumber2);
        model.addAttribute("ziyaretsayisi", "+" + ziyaretsayisi);
        model.addAttribute("customerList", customerList);
        model.addAttribute("saleTypeList", saleTypeList);
        return "statistic";
    }

    private String castingDayName(String i) {
        if (i.equals("1")) {
            return "Monday";
        } else if (i.equals("2")) {
            return "Tuesday";
        } else if (i.equals("3")) {
            return "Wednesday";
        } else if (i.equals("4")) {
            return "Thursday";
        } else if (i.equals("5")) {
            return "Friday";
        } else if (i.equals("6")) {
            return "Saturday";
        } else {
            return "Sunday";
        }
    }
}


