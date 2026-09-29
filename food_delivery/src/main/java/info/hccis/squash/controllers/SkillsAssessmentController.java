package info.hccis.squash.controllers;

import info.hccis.squash.bo.SkillsAssessmentBO;
import info.hccis.squash.bo.SkillsAssessmentValidationBO;
import info.hccis.squash.jpa.entity.SkillsAssessmentSquashTechnical;
import info.hccis.squash.repositories.SkillsAssessmentSquashTechnicalRepository;
import info.hccis.squash.util.CisUtility;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to administer ticket orders. Note that the code was taken from
 * Fred Campos' project from 2021 which also had modifications from Ferhad in
 * 2022.
 *
 * @since 20220616
 * @author BJM
 */
@Controller
@RequestMapping("/skillsassessment")
public class SkillsAssessmentController {

    private final SkillsAssessmentSquashTechnicalRepository _sastr;

    @Autowired
    public SkillsAssessmentController(SkillsAssessmentSquashTechnicalRepository sastr) {
        _sastr = sastr;
    }

    @Autowired
    private MessageSource messageSource;

    private static final Logger logger = LoggerFactory.getLogger(SkillsAssessmentController.class);

    @RequestMapping("")
    public String home(Model model, HttpSession session) {

        System.out.println("request mapping matched for skillsassessment");
        Iterable<SkillsAssessmentSquashTechnical> theList = _sastr.findAll();
        model.addAttribute("assessments", theList);
        model.addAttribute("assessment", new SkillsAssessmentSquashTechnical());

        //Build an ArrayList of all the names in the assessments found
        ArrayList<String> theNames = new ArrayList();
        for (SkillsAssessmentSquashTechnical current : theList) {
            theNames.add(current.getAthleteName());
        }

        //Now could add theNames to the model so that it can be used to load a drop down
        //on the list page.  
        session.setAttribute("names", theNames);

        return "skillsassessment/list";
    }

    /**
     * Page to add new item. Taken from tutor app from 2022 (which was also
     * derived from class samples)
     *
     * @param model
     * @return add
     * @since 2023-10-20
     * @author BJM
     */
    @RequestMapping("/add")
    public String add(Model model) {

        System.out.println("request mapping matched for add");
        SkillsAssessmentSquashTechnical assessment = new SkillsAssessmentSquashTechnical();
        assessment.setCreatedDateTime(CisUtility.getCurrentDate("yyyy-MM-dd hh:mm:ss"));
        model.addAttribute("assessment", assessment);

        //Send the user to a view to allow them to add a new skills assessment
        return "skillsassessment/add";
    }

    /**
     * Submit method that processes add and edit and any form submission
     *
     * @param model
     * @param request
     * @param assessment being added or modified
     * @param bindingResult Result of SQL
     * @return add with errors or assessment
     * @since 20231023
     * @author BJM
     */
    @RequestMapping("/submit")
    public String submit(Model model, HttpServletRequest request, @Valid @ModelAttribute("assessment") SkillsAssessmentSquashTechnical assessment, BindingResult bindingResult) {

        SkillsAssessmentValidationBO savbo = new SkillsAssessmentValidationBO();
        ArrayList<String> validationErrors = savbo.validate(assessment);
        boolean businessValidationIssues = !validationErrors.isEmpty();
        
        
        if (bindingResult.hasErrors() || businessValidationIssues) {
//            System.out.println("--------------------------------------------");
//            System.out.println("Validation error - BJM");
//            for (ObjectError error : bindingResult.getAllErrors()) {
//                System.out.println(error.getObjectName() + "-" + error.toString() + "-" + error.getDefaultMessage());
//            }
//            System.out.println("--------------------------------------------");
//
            model.addAttribute("businessValidationErrors", validationErrors);

            return "skillsassessment/add";
        }
        
        
        

        assessment.setTechnicalScore(SkillsAssessmentBO.calculateScore(assessment));
        _sastr.save(assessment);
        return "redirect:/skillsassessment";
    }

    /**
     * Page to edit
     *
     * @param id ID
     * @param model
     * @return
     * @since 20220616
     * @author Fred Campos
     */
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {

        System.out.println("request mapping matched for edit");

        Optional assessmentOptional = _sastr.findById(id);
        if (assessmentOptional.isPresent()) {
            SkillsAssessmentSquashTechnical assessment = (SkillsAssessmentSquashTechnical) assessmentOptional.get();
            assessment.setCreatedDateTime(CisUtility.getCurrentDate("yyyy-MM-dd hh:mm:ss"));
            model.addAttribute("assessment", assessment);
            return "skillsassessment/add";
        }
        //possibly not found for edit id.  todo set a message to the user.  
        return "redirect:/skillsassessment";

    }

    /**
     * Page to delete an order
     *
     * @param id ID
     * @return directions to the list page
     * @since 20231023
     * @author BJM
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        _sastr.deleteById(id);
        return "redirect:/skillsassessment";
    }

    /**
     * Search for assessments of a given athlete
     *
     * @param model
     * @param assessment
     * @return view for list
     * @since 2022-06-20
     * @author BJM
     */
    @RequestMapping("/search")
    public String search(Model model, @ModelAttribute("assessment") SkillsAssessmentSquashTechnical sast) {

        HashSet<SkillsAssessmentSquashTechnical> theSet = SkillsAssessmentBO.loadAssessmentsForAthleteAssessor(_sastr, sast);
        model.addAttribute("assessments", theSet);

        return "skillsassessment/list";
    }

}
