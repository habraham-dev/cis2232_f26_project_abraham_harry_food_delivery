package info.hccis.squash.soap;

import info.hccis.squash.dao.SquashSkillsDAO;
import info.hccis.squash.jpa.entity.SkillsAssessmentSquashTechnical;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;

@WebService(endpointInterface = "info.hccis.squash.soap.AssessmentService")
public class AssessmentServiceImpl implements AssessmentService {

    public SkillsAssessmentSquashTechnical getAssessment(int id) {

        SquashSkillsDAO squashSkillsDAO = new SquashSkillsDAO();
        SkillsAssessmentSquashTechnical sast = squashSkillsDAO.selectSkillsAssessment(id);
        return sast;

    }

    @Override
    public int getCount() {
        SquashSkillsDAO squashSkillsDAO = new SquashSkillsDAO();
        ArrayList<SkillsAssessmentSquashTechnical> sasts = squashSkillsDAO.selectSkillsAssessments("");
        return sasts.size();
    }

    @Override
    public List<SkillsAssessmentSquashTechnical> getAssessments(String name) {

        SquashSkillsDAO squashSkillsDAO = new SquashSkillsDAO();
        ArrayList<SkillsAssessmentSquashTechnical> sasts = squashSkillsDAO.selectSkillsAssessments(name);
        return sasts;


    }

}
