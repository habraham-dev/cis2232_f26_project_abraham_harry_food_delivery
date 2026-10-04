package info.hccis.squash.soap;

import info.hccis.squash.jpa.entity.SkillsAssessmentSquashTechnical;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface AssessmentService {
    @WebMethod
    SkillsAssessmentSquashTechnical getAssessment(int id);
    @WebMethod
    List<SkillsAssessmentSquashTechnical> getAssessments(String name);
    @WebMethod
    int getCount();
    
}