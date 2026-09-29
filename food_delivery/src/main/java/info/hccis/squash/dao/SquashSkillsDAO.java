package info.hccis.squash.dao;

import info.hccis.squash.jpa.entity.SkillsAssessmentSquashTechnical;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DAO class to access skills
 *
 * @author bjmaclean
 * @since 20220621
 */
public class SquashSkillsDAO {

    private static ResultSet rs;
    private static Connection conn = null;
    private static final Logger logger = LoggerFactory.getLogger(SquashSkillsDAO.class);

    public SquashSkillsDAO() {

        String propFileName = "application";
        ResourceBundle rb = ResourceBundle.getBundle(propFileName);
        String connectionString = rb.getString("spring.datasource.url");
        String userName = rb.getString("spring.datasource.username");
        String password = rb.getString("spring.datasource.password");

        try {
            conn = DriverManager.getConnection(connectionString, userName, password);
        } catch (SQLException e) {
            logger.error(e.toString());
        }
    }

    /**
     * Select an assessment by idd
     *
     * @since 20231106
     * @author BJM
     */
    public SkillsAssessmentSquashTechnical selectSkillsAssessment(int id) {

        SkillsAssessmentSquashTechnical sast = new SkillsAssessmentSquashTechnical();

        PreparedStatement stmt;
        ArrayList<SkillsAssessmentSquashTechnical> skillsAssessments = new ArrayList();

        //https://stackoverflow.com/questions/2857164/cannot-use-a-like-query-in-a-jdbc-preparedstatement
        //Bitbucket Issue#5
        try {
            String query = "SELECT * FROM skillsassessmentsquashtechnical sast WHERE sast.id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while (rs.next()) {

                sast.setId(rs.getInt("id"));
                sast.setAssessmentDate(rs.getString("assessmentDate"));
                sast.setAthleteName(rs.getString("athleteName"));
                sast.setAssessorName(rs.getString("assessorName"));
                sast.setAssessorName(rs.getString("assessorName"));
                sast.setCreatedDateTime(rs.getString("createdDateTime"));
                sast.setForehandDrives(rs.getInt("forehandDrives"));
                sast.setBackhandDrives(rs.getInt("backhandDrives"));
                sast.setForehandVolleyMax(rs.getInt("forehandVolleyMax"));
                sast.setForehandVolleySum(rs.getInt("forehandVolleySum"));
                sast.setBackhandVolleyMax(rs.getInt("backhandVolleyMax"));
                sast.setBackhandVolleySum(rs.getInt("backhandVolleySum"));
                sast.setTechnicalScore(rs.getInt("technicalScore"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("Found assessment:  " + sast.toString());
        return sast;

    }

    /**
     * Select skills assessments by athlete name
     *
     * @since 20231012
     * @author BJM
     */
    public ArrayList<SkillsAssessmentSquashTechnical> selectSkillsAssessments(String athleteName) {
        PreparedStatement stmt;
        ArrayList<SkillsAssessmentSquashTechnical> skillsAssessments = new ArrayList();

        //https://stackoverflow.com/questions/2857164/cannot-use-a-like-query-in-a-jdbc-preparedstatement
        //Bitbucket Issue#5
        String athleteNameLike = "%" + athleteName + "%";
        try {
            String query = "SELECT * FROM skillsassessmentsquashtechnical sast WHERE sast.athleteName LIKE ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, athleteNameLike);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while (rs.next()) {
                SkillsAssessmentSquashTechnical sast = new SkillsAssessmentSquashTechnical();

                sast.setId(rs.getInt("id"));
                sast.setAssessmentDate(rs.getString("assessmentDate"));
                sast.setAthleteName(rs.getString("athleteName"));
                sast.setAssessorName(rs.getString("assessorName"));
                sast.setAssessorName(rs.getString("assessorName"));
                sast.setCreatedDateTime(rs.getString("createdDateTime"));
                sast.setForehandDrives(rs.getInt("forehandDrives"));
                sast.setBackhandDrives(rs.getInt("backhandDrives"));
                sast.setForehandVolleyMax(rs.getInt("forehandVolleyMax"));
                sast.setForehandVolleySum(rs.getInt("forehandVolleySum"));
                sast.setBackhandVolleyMax(rs.getInt("backhandVolleyMax"));
                sast.setBackhandVolleySum(rs.getInt("backhandVolleySum"));
                sast.setTechnicalScore(rs.getInt("technicalScore"));
                skillsAssessments.add(sast);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("Found assessments:  " + skillsAssessments.size());
        return skillsAssessments;
    }

    /**
     * Select skills assessments by min and max score
     *
     * @since 20231012
     * @author BJM
     */
    public ArrayList<SkillsAssessmentSquashTechnical> selectSkillsAssessments(int min, int max) {
        PreparedStatement stmt;
        ArrayList<SkillsAssessmentSquashTechnical> skillsAssessments = new ArrayList();

        try {
            String query = "SELECT * FROM skillsassessmentsquashtechnical sast WHERE sast.technicalScore >= ? && sast.technicalScore <= ?;";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, min);
            stmt.setInt(2, max);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while (rs.next()) {
                SkillsAssessmentSquashTechnical sast = new SkillsAssessmentSquashTechnical();

                sast.setId(rs.getInt("id"));
                sast.setAssessmentDate(rs.getString("assessmentDate"));
                sast.setAthleteName(rs.getString("athleteName"));
                sast.setAssessorName(rs.getString("assessorName"));
                sast.setAssessorName(rs.getString("assessorName"));
                sast.setCreatedDateTime(rs.getString("createdDateTime"));
                sast.setForehandDrives(rs.getInt("forehandDrives"));
                sast.setBackhandDrives(rs.getInt("backhandDrives"));
                sast.setForehandVolleyMax(rs.getInt("forehandVolleyMax"));
                sast.setForehandVolleySum(rs.getInt("forehandVolleySum"));
                sast.setBackhandVolleyMax(rs.getInt("backhandVolleyMax"));
                sast.setBackhandVolleySum(rs.getInt("backhandVolleySum"));
                sast.setTechnicalScore(rs.getInt("technicalScore"));
                skillsAssessments.add(sast);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("Found assessments:  " + skillsAssessments.size());
        return skillsAssessments;
    }
}
