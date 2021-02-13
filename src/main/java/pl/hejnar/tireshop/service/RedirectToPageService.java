package pl.hejnar.tireshop.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class RedirectToPageService {

    public String goToPage(HttpSession ses){
        if(ses.getAttribute("currentPage") != null){
            String page = (String) ses.getAttribute("currentPage");
            if(page.equals("tire")){
                return "redirect:/shop/tire";
            }else if(page.equals("wheelRim")){
                return "redirect:/shop/wheel-rim";
            }else {
                return "redirect:/";
            }
        }else {
            return "redirect:/";
        }
    }
}
