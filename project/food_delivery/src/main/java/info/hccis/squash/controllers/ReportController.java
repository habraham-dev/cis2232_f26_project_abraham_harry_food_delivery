package info.hccis.squash.controllers;

import info.hccis.squash.dao.FoodDeliveryOrderDAO;
import info.hccis.squash.dao.SquashSkillsDAO;
import info.hccis.squash.entity.ReportAssessment;
import info.hccis.squash.entity.ReportFoodDeliveryOrder;
import info.hccis.squash.jpa.entity.FoodDeliveryOrder;
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
     * Send the user to the Order Summary Report input view.
     *
     * An empty ReportFoodDeliveryOrder object is added to the model with the
     * handle reportInput. The form on the page fills in its orderId.
     *
     * @param model
     * @return the order summary report view
     * @since 20261101
     * @author Harry Abraham
     * Issue#5
     */
    @RequestMapping("/order/summary")
    public String orderSummary(Model model) {

        model.addAttribute("reportInput", new ReportFoodDeliveryOrder());
        logger.info("Sending the user to the order summary report view");
        return "report/reportOrderSummary";
    }

    /**
     * Process the Order Summary Report after the user submits an Order ID.
     *
     * 1. Use the DAO (JDBC) to find the order.
     * 2. Write the report to a file in c:/cis2232/.
     * 3. Send the user back to the view to see the results.
     *
     * @param model
     * @param reportInput holds the order id entered by the user
     * @return the order summary report view (now with results)
     * @since 20261101
     * @author Harry Abraham
     * Issue#5
     */
    @RequestMapping("/order/summary/submit")
    public String orderSummarySubmit(Model model, @ModelAttribute("reportInput") ReportFoodDeliveryOrder reportInput) {

        Integer orderId = reportInput.getOrderId();
        logger.info("Order summary report requested for order id: " + orderId);

        //If nothing was entered, ask the user for an order id.
        if (orderId == null) {
            model.addAttribute("message", "Please enter an Order ID.");
            model.addAttribute("reportInput", reportInput);
            return "report/reportOrderSummary";
        }

        //1. Use the DAO class (JDBC) to get the order for that id.
        FoodDeliveryOrderDAO foodDeliveryOrderDAO = new FoodDeliveryOrderDAO();
        ArrayList<FoodDeliveryOrder> orders = foodDeliveryOrderDAO.selectFoodDeliveryOrders(orderId);

        //If no rows were found, add a message to the model.
        if (orders.isEmpty()) {
            model.addAttribute("message", "No order found for Order ID " + orderId + ".");
        }

        //2. Write the report to a file (c:/cis2232/OrderSummaryReport<date>.txt).
        FileUtil.writeToFile("Order Summary Report", orders);

        //3. Add the results to the model and send the user to the view.
        reportInput.setOrders(orders);
        model.addAttribute("reportInput", reportInput);
        return "report/reportOrderSummary";
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