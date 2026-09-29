package info.hccis.squash.jpa.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Entity
@Table(name = "CodeValue", schema = "bjmac_squash_skills")
public class CodeValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "codeTypeId", nullable = false)
    private Integer codeTypeId;

    @NotNull
    @Column(name = "codeValueSequence", nullable = false)
    private Integer codeValueSequence;

    @Size(max = 100)
    @NotNull
    @Column(name = "englishDescription", nullable = false, length = 100)
    private String englishDescription;

    @Size(max = 20)
    @NotNull
    @Column(name = "englishDescriptionShort", nullable = false, length = 20)
    private String englishDescriptionShort;

    @Size(max = 100)
    @Column(name = "frenchDescription", length = 100)
    private String frenchDescription;

    @Size(max = 20)
    @Column(name = "frenchDescriptionShort", length = 20)
    private String frenchDescriptionShort;

    @Column(name = "sortOrder")
    private Integer sortOrder;

    @Column(name = "createdDateTime")
    private Instant createdDateTime;

    @Size(max = 20)
    @Column(name = "createdUserId", length = 20)
    private String createdUserId;

    @Column(name = "updatedDateTime")
    private Instant updatedDateTime;

    @Size(max = 20)
    @Column(name = "updatedUserId", length = 20)
    private String updatedUserId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodeTypeId() {
        return codeTypeId;
    }

    public void setCodeTypeId(Integer codeTypeId) {
        this.codeTypeId = codeTypeId;
    }

    public Integer getCodeValueSequence() {
        return codeValueSequence;
    }

    public void setCodeValueSequence(Integer codeValueSequence) {
        this.codeValueSequence = codeValueSequence;
    }

    public String getEnglishDescription() {
        return englishDescription;
    }

    public void setEnglishDescription(String englishDescription) {
        this.englishDescription = englishDescription;
    }

    public String getEnglishDescriptionShort() {
        return englishDescriptionShort;
    }

    public void setEnglishDescriptionShort(String englishDescriptionShort) {
        this.englishDescriptionShort = englishDescriptionShort;
    }

    public String getFrenchDescription() {
        return frenchDescription;
    }

    public void setFrenchDescription(String frenchDescription) {
        this.frenchDescription = frenchDescription;
    }

    public String getFrenchDescriptionShort() {
        return frenchDescriptionShort;
    }

    public void setFrenchDescriptionShort(String frenchDescriptionShort) {
        this.frenchDescriptionShort = frenchDescriptionShort;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Instant getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Instant createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId;
    }

    public Instant getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(Instant updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    public String getUpdatedUserId() {
        return updatedUserId;
    }

    public void setUpdatedUserId(String updatedUserId) {
        this.updatedUserId = updatedUserId;
    }

}