package info.hccis.squash.repositories;

import info.hccis.squash.jpa.entity.CodeType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeTypeRepository extends CrudRepository<CodeType, Integer> {
       CodeType findByCodeTypeId(Integer codeTypeId);
}