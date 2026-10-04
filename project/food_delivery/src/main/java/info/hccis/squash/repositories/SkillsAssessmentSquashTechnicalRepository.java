package info.hccis.squash.repositories;

import info.hccis.squash.jpa.entity.SkillsAssessmentSquashTechnical;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsAssessmentSquashTechnicalRepository extends CrudRepository<SkillsAssessmentSquashTechnical, Integer> {
    //https://www.baeldung.com/spring-jpa-like-queries
    List<SkillsAssessmentSquashTechnical> findByAthleteNameContaining(String name);
    List<SkillsAssessmentSquashTechnical> findByAssessorNameContaining(String name);

}