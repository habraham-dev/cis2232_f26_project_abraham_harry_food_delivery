/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info.hccis.squash.jpa.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author bjmaclean
 */
@Entity
@Table(name = "SkillsAssessmentSquashTechnical")
public class SkillsAssessmentSquashTechnical implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "assessmentDate")
    private String assessmentDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "createdDateTime")
    private String createdDateTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "athleteName")
    private String athleteName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "assessorName")
    private String assessorName;
    @Column(name = "forehandDrives")
    private Integer forehandDrives;
    @Column(name = "backhandDrives")
    private Integer backhandDrives;
    @Column(name = "forehandVolleyMax")
    private Integer forehandVolleyMax;
    @Column(name = "forehandVolleySum")
    private Integer forehandVolleySum;
    @Column(name = "backhandVolleyMax")
    private Integer backhandVolleyMax;
    @Column(name = "backhandVolleySum")
    private Integer backhandVolleySum;
    @Column(name = "technicalScore")
    private Integer technicalScore;

    public SkillsAssessmentSquashTechnical() {
        backhandDrives = 0;
        forehandDrives = 0;
        forehandVolleyMax = 0;
        forehandVolleySum = 0;
        backhandVolleyMax = 0;
        backhandVolleySum = 0;
    }

    public SkillsAssessmentSquashTechnical(Integer id) {
        this.id = id;
    }

    public SkillsAssessmentSquashTechnical(Integer id, String assessmentDate, String createdDateTime, String athleteName, String assessorName) {
        this.id = id;
        this.assessmentDate = assessmentDate;
        this.createdDateTime = createdDateTime;
        this.athleteName = athleteName;
        this.assessorName = assessorName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAssessmentDate() {
        return assessmentDate;
    }

    public void setAssessmentDate(String assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getAthleteName() {
        return athleteName;
    }

    public void setAthleteName(String athleteName) {
        this.athleteName = athleteName;
    }

    public String getAssessorName() {
        return assessorName;
    }

    public void setAssessorName(String assessorName) {
        this.assessorName = assessorName;
    }

    public Integer getForehandDrives() {
        return forehandDrives;
    }

    public void setForehandDrives(Integer forehandDrives) {
        this.forehandDrives = forehandDrives;
    }

    public Integer getBackhandDrives() {
        return backhandDrives;
    }

    public void setBackhandDrives(Integer backhandDrives) {
        this.backhandDrives = backhandDrives;
    }

    public Integer getForehandVolleyMax() {
        return forehandVolleyMax;
    }

    public void setForehandVolleyMax(Integer forehandVolleyMax) {
        this.forehandVolleyMax = forehandVolleyMax;
    }

    public Integer getForehandVolleySum() {
        return forehandVolleySum;
    }

    public void setForehandVolleySum(Integer forehandVolleySum) {
        this.forehandVolleySum = forehandVolleySum;
    }

    public Integer getBackhandVolleyMax() {
        return backhandVolleyMax;
    }

    public void setBackhandVolleyMax(Integer backhandVolleyMax) {
        this.backhandVolleyMax = backhandVolleyMax;
    }

    public Integer getBackhandVolleySum() {
        return backhandVolleySum;
    }

    public void setBackhandVolleySum(Integer backhandVolleySum) {
        this.backhandVolleySum = backhandVolleySum;
    }

    public Integer getTechnicalScore() {
        return technicalScore;
    }

    public void setTechnicalScore(Integer technicalScore) {
        this.technicalScore = technicalScore;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SkillsAssessmentSquashTechnical)) {
            return false;
        }
        SkillsAssessmentSquashTechnical other = (SkillsAssessmentSquashTechnical) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    //Keep in mind this will disappear if I regenerate the class
    @Override
    public String toString() {
        String output = "Assessment Details: Athlete: " + getAthleteName() + " Date: " + getAssessmentDate() + " Score: " + getTechnicalScore();
        return output;
    }
    
}
