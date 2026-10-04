package info.hccis.squash.controllers;

import info.hccis.squash.dao.SquashSkillsDAO;
import info.hccis.squash.entity.ReportAssessment;
import info.hccis.squash.jpa.entity.SkillsAssessmentSquashTechnical;
import info.hccis.util.FileUtil;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to administer reports of the project.
 *
 * @since 20220616
 * @author BJM
 */
@Controller
@RequestMapping("/report")
public class ReportController {

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    /**
     * Send the user to list of reports view.
     *
     * @param model
     * @param session
     * @return To the appropriate view
     * @since 20220624
     * @author BJM
     */
    @RequestMapping("")
    public String home(Model model, HttpSession session) {

        //BJM 20200602 Issue#1 Set the current date in the session
        logger.info("Running the reports controller base method");
        return "report/list";
    }
    
    /**
     * Method to send user to the order date report.
     *
     * @param model
     * @return view for list
     * @since 2022-06-20
     * @author BJM
     */
    @RequestMapping("/assessment/player")
    public String assessmentByPlayer(Model model) {

        //**********************************************************************
        // Send the user to the player report view.  Adding a ReportAssessment object
        // to the model with the handle reportInput.  This is to allow this object 
        // to be used on the html page (see Thymeleaf tags.
        //**********************************************************************
        model.addAttribute("reportInput", new ReportAssessment());
        System.out.println("BJM - reportcontroller - sending the user to a different view");
        return "report/reportAssessmentsByPlayer";
    }

    /**
     * Method to send user to the order date report.
     *
     * @param model
     * @return view for list
     * @since 2022-06-20
     * @author BJM
     */
    @RequestMapping("/assessment/player/submit")
    public String assessmentByPlayerSubmit(Model model, @ModelAttribute("reportInput") ReportAssessment reportAssessment) {

        System.out.println("The name entered by the user is: "+reportAssessment.getPlayerName());
        
        //todo 1 use dao class to get the assessments for that player
        SquashSkillsDAO squashSkillsDAO = new SquashSkillsDAO();
        ArrayList<SkillsAssessmentSquashTechnical> skillsAssessments = squashSkillsDAO.selectSkillsAssessments(reportAssessment.getPlayerName());
        
        //if not rows found, add a message to the model
        if(skillsAssessments.isEmpty()){
            model.addAttribute("message", "No data foound for <strong>"+reportAssessment.getPlayerName()+"</strong>");
        }
        
        FileUtil.writeToFile("Player Report", skillsAssessments);
        
        //add them to the model
        reportAssessment.setAssessments(skillsAssessments);
        model.addAttribute("reportInput", reportAssessment);
        
        //model.addAttribute("reportInput", new ReportOrder());
        System.out.println("BJM - reportcontroller - assessment player was submitted");
        return "report/reportAssessmentsByPlayer";
    }

    
    
     /**
     * Method to send user to the tech score report
     *
     * @param model
     * @return view for list
     * @since 2022-06-20
     * @author BJM
     */
    @RequestMapping("/assessment/score")
    public String assessmentByScore(Model model) {

        model.addAttribute("reportInput", new ReportAssessment());
        System.out.println("BJM - reportcontroller - sending the user to a different view");
        return "report/reportAssessmentsByScore";
    }
    
    /**
     * Method to send user to the order date report.
     *
     * @param model
     * @return view for list
     * @since 2022-06-20
     * @author BJM
     */
    @RequestMapping("/assessment/score/submit")
    public String assessmentByScoreSubmit(Model model, @ModelAttribute("reportInput") ReportAssessment reportAssessment) {

        System.out.println("The max entered by the user is: "+reportAssessment.getScoreMax());
        
        SquashSkillsDAO squashSkillsDAO = new SquashSkillsDAO();
        int min = reportAssessment.getScoreMin();
        int max = reportAssessment.getScoreMax();
        ArrayList<SkillsAssessmentSquashTechnical> skillsAssessments = squashSkillsDAO.selectSkillsAssessments(min, max);
        
        FileUtil.writeToFile("Report Score Min Max", skillsAssessments);

        //if not rows found, add a message to the model
        if(skillsAssessments.isEmpty()){
            model.addAttribute("message", "No data found");
        }
        
        //add them to the model
        reportAssessment.setAssessments(skillsAssessments);
        model.addAttribute("reportInput", reportAssessment);

        System.out.println("BJM - reportcontroller - assessment player was submitted");
        return "report/reportAssessmentsByScore";
    }

}
