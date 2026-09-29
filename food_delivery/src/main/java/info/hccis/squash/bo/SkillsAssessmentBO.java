package info.hccis.squash.bo;

import info.hccis.squash.jpa.entity.SkillsAssessmentSquashTechnical;
import info.hccis.squash.repositories.SkillsAssessmentSquashTechnicalRepository;
import info.hccis.squash.util.CisUtility;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Business logic for my assessments
 *
 * @author bjmaclean
 * @since 20231026
 */
public class SkillsAssessmentBO {

    /**
     * calculate and set the technical score in the sast passed in
     *
     * @param sast
     * @return technical score
     * @since 20231026
     * @author BJM
     */
    public static int calculateScore(SkillsAssessmentSquashTechnical sast) {

        final int VALUE_DRIVE = 15;
        final int VALUE_MAX = 8;
        final int VALUE_SUM = 5;

                
        int score = (sast.getBackhandDrives()+sast.getForehandDrives())*VALUE_DRIVE
                +(sast.getBackhandVolleyMax()+sast.getForehandVolleyMax())*VALUE_MAX
                +(sast.getBackhandVolleySum()+sast.getForehandVolleySum())*VALUE_SUM;
        
        return score;
    }

    /**
     * Load a set with the assessments for an athlete and an assessor
     *
     * @param sastr
     * @param sast
     * @return A set with the appropriate assessments
     * @since 20231027
     * @author BJM
     */
    public static HashSet<SkillsAssessmentSquashTechnical> loadAssessmentsForAthleteAssessor(SkillsAssessmentSquashTechnicalRepository sastr, SkillsAssessmentSquashTechnical sast) {

        List<SkillsAssessmentSquashTechnical> theListAthleteName = new ArrayList();
        List<SkillsAssessmentSquashTechnical> theListAssessorName = new ArrayList();

        //**********************************************************************
        //Use repository method created to find any objects which contain 
        //the name entered on the list page.
        //**********************************************************************
        theListAthleteName = sastr.findByAthleteNameContaining(sast.getAthleteName());
        theListAssessorName = sastr.findByAssessorNameContaining(sast.getAssessorName());

        HashSet<SkillsAssessmentSquashTechnical> theSet = new HashSet();

        for (SkillsAssessmentSquashTechnical skillsAssessmentSquashTechnical : theListAthleteName) {
            if (theListAssessorName.contains(skillsAssessmentSquashTechnical)) {
                theSet.add(skillsAssessmentSquashTechnical);
            }
        }

        return theSet;

    }

}
