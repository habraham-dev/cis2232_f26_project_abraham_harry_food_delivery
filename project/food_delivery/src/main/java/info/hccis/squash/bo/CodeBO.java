package info.hccis.squash.bo;

import info.hccis.squash.jpa.entity.CodeType;
import info.hccis.squash.jpa.entity.CodeValue;
import info.hccis.squash.repositories.CodeTypeRepository;
import info.hccis.squash.repositories.CodeValueRepository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class CodeBO {

    private static CodeTypeRepository _ctr;
    private static CodeValueRepository _cvr;
    private static HttpSession _session;

    public static void setup(HttpSession session, CodeTypeRepository ctr, CodeValueRepository cvr) {

        _ctr = ctr;
        _cvr = cvr;
        _session = session;

        Iterable<CodeType> codeTypeIterable = ctr.findAll();
        Iterable<CodeValue> codeValueList = cvr.findAll();

        ArrayList<CodeType> codeTypeList = new ArrayList<>();
        for (CodeType codeType : codeTypeIterable) {
            codeTypeList.add(codeType);
        }

        Iterable<CodeValue> organizationCodes = cvr.findByCodeTypeId(3);

        session.setAttribute("organizationCodes", organizationCodes);
        session.setAttribute("codeTypeList", codeTypeList);
        session.setAttribute("codeValueList", codeValueList);

        System.out.println("Size of code types: "+codeTypeList.size());

        }


    public static ArrayList<CodeValue> loadCurrentSkills(int codeTypeId) {
        //***************************************************************
        //Next want to setup the skills for the organizations.  The code
        //type will be 100*the organization's code value sequence.
        //***************************************************************
        int organizationCodeForSkills = codeTypeId*100;

        Iterable<CodeValue> organizationsSkillCodeValues = _cvr.findByCodeTypeId(organizationCodeForSkills);
        _session.setAttribute("currentSkills", organizationsSkillCodeValues);
        ArrayList<CodeValue> organizationsSkillCodeValuesList = new ArrayList<>();
        for (CodeValue codeValue : organizationsSkillCodeValues) {
            organizationsSkillCodeValuesList.add(codeValue);
        }
        System.out.println("BJM Loaded organizationsSkillCodeValues for: "+organizationCodeForSkills+"-->"+organizationsSkillCodeValuesList.size());

        return organizationsSkillCodeValuesList;
    }
}
