package info.hccis.squash.entity;

import info.hccis.squash.jpa.entity.SkillsAssessmentSquashTechnical;
import java.util.ArrayList;

/**
 * Entity class to hold the attributes of the assessment related reports.
 * @author bjmaclean
 * @since 20231005
 */
public class ReportAssessment {
    private int scoreMin = 100;
    private int scoreMax = 1000;
    private String playerName;
    private ArrayList<SkillsAssessmentSquashTechnical> assessments;

    public int getScoreMin() {
        return scoreMin;
    }

    public void setScoreMin(int scoreMin) {
        this.scoreMin = scoreMin;
    }

    public int getScoreMax() {
        return scoreMax;
    }

    public void setScoreMax(int scoreMax) {
        this.scoreMax = scoreMax;
    }
    
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public ArrayList<SkillsAssessmentSquashTechnical> getAssessments() {
        return assessments;
    }

    public void setAssessments(ArrayList<SkillsAssessmentSquashTechnical> assessments) {
        this.assessments = assessments;
    }
}
