package info.hccis.squash.controllers;

import info.hccis.util.ApiDetail;
import info.hccis.util.ApiProcessor;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Base controller which control general functionality in the app.
 *
 * @since 20220624
 * @author BJM
 */
@Controller
@RequestMapping("/callapi")
public class CallApiController {

    /**
     * Send the user to the view.
     *
     * @since 20231102
     * @author BJM
     */
    @RequestMapping("/pirate")
    public String translateToPirate(Model model) {
        model.addAttribute("apiDetail", new ApiDetail());
        return "callapi/pirate";
    }

    /**
     * Send the user to the view.
     *
     * @since 20231102
     * @author BJM
     */
    @RequestMapping("/pirate/submit")
    public String submit(Model model, HttpServletRequest request, @ModelAttribute("apiDetail") ApiDetail apiDetail){

        String pirate = ApiProcessor.callApi2(apiDetail.getEnglish());
        apiDetail.setPirate(pirate);
        
        model.addAttribute("apiDetail", apiDetail);
        return "callapi/pirate";
    }

    
}
