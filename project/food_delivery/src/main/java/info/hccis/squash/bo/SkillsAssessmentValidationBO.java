package info.hccis.squash.bo;

import info.hccis.squash.jpa.entity.SkillsAssessmentSquashTechnical;
import java.util.ArrayList;

/**
 * Custom business validation
 *
 * @author bjmaclean
 * @since 20231130
 */
public class SkillsAssessmentValidationBO {

    public ArrayList<String> validate(SkillsAssessmentSquashTechnical sast) {

        ArrayList<String> errors = new ArrayList();

        //Validate the skills numbers
        if (sast.getForehandDrives() < 0
                || sast.getBackhandDrives() < 0 
                || sast.getForehandVolleyMax() < 0
                || sast.getBackhandVolleyMax() < 0
                || sast.getForehandVolleySum() < 0
                || sast.getBackhandVolleySum() < 0) {

            errors.add("Skill counts can not be negative");
        }
        return errors;
    }

}
