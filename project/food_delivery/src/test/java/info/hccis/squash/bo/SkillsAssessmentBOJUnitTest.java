package info.hccis.squash.bo;

import info.hccis.squash.jpa.entity.SkillsAssessmentSquashTechnical;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author bjmaclean
 */
public class SkillsAssessmentBOJUnitTest {

    public SkillsAssessmentBOJUnitTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testCalculateScore_foreHandDrives3() {
        SkillsAssessmentSquashTechnical sast = new SkillsAssessmentSquashTechnical();
        sast.setForehandDrives(3);
        int score = SkillsAssessmentBO.calculateScore(sast);
        Assertions.assertEquals(45,score);
    }

    @Test
    public void testCalculateScore_foreHandDrives3_backhandDrives3() {
        SkillsAssessmentSquashTechnical sast = new SkillsAssessmentSquashTechnical();
        sast.setForehandDrives(3);
        sast.setBackhandDrives(3);
        int score = SkillsAssessmentBO.calculateScore(sast);
        boolean areEqual = score == 90;
        Assertions.assertTrue(areEqual);
    }

}
