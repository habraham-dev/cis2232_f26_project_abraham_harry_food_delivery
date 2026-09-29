package info.hccis.squash.controllers;

import info.hccis.squash.bo.CodeBO;
import info.hccis.squash.jpa.entity.CodeValue;
import info.hccis.squash.repositories.CodeTypeRepository;
import info.hccis.squash.repositories.CodeValueRepository;
import info.hccis.squash.util.CisUtility;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Base controller which control general functionality in the app.
 *
 * @since 20220624
 * @author BJM
 */
@Controller
public class BaseController {

    private final CodeValueRepository _cvr;
    private final CodeTypeRepository _ctr;

    @Autowired
    public BaseController(CodeValueRepository cvr, CodeTypeRepository ctr) {
        _cvr = cvr;
        _ctr = ctr;
    }

    /**
     * Send the user to the welcome view
     *
     * @since 20220624
     * @author BJM
     */
    @RequestMapping("/")
    public String home(HttpSession session) {


        //BJM 20200602 Issue#1 Set the current date in the session
        String currentDate = CisUtility.getCurrentDate("yyyy-MM-dd");
        session.setAttribute("currentDate", currentDate);

        CodeBO.setup(session, _ctr, _cvr);
        return "index";
    }

    @RequestMapping("/start/{id}")
    public String start(@PathVariable int id,HttpSession session) {
        System.out.println("organization code value id: " + id);

        Optional<CodeValue> organizationCodeValueOptional = _cvr.findById(id);
        CodeValue organizationCodeValue = null;
        if (organizationCodeValueOptional.isPresent()) {
             organizationCodeValue = organizationCodeValueOptional.get();
        }
        session.setAttribute("organization id", id);
        session.setAttribute("organizationCodeValue",organizationCodeValue);

        System.out.println("organization name: " + organizationCodeValue.getEnglishDescription());
        session.setAttribute("organizationName",organizationCodeValue.getEnglishDescription());
        System.out.println("loading skills for id:"+organizationCodeValue.getCodeValueSequence());
        ArrayList<CodeValue> currentSkills = CodeBO.loadCurrentSkills(organizationCodeValue.getCodeValueSequence());

        return "other/welcome";
    }


    /**
     * Send the user to the about view.
     *
     * @since 20220624
     * @author BJM
     */
    @RequestMapping("/about")
    public String about() {
        System.out.println("The user chose to go to the about view.  Controller sending the user there");
        System.out.println("will send the user to this view --> templates.other/about.html");
        return "other/about";
    }

    
}
