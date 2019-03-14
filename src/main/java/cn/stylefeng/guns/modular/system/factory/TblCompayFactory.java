package cn.stylefeng.guns.modular.system.factory;

import cn.stylefeng.guns.modular.companymanager.transfer.TblCompanyDto;
import cn.stylefeng.guns.modular.system.model.TblCompany;
import cn.stylefeng.guns.modular.system.model.User;
import cn.stylefeng.guns.modular.system.transfer.UserDto;
import cn.stylefeng.roses.core.util.ToolUtil;
import org.springframework.beans.BeanUtils;

public class TblCompayFactory {

    public static TblCompany createCompany(TblCompanyDto tblCompanyDto) {
        if (tblCompanyDto == null) {
            return null;
        } else {
            TblCompany company = new TblCompany();
            BeanUtils.copyProperties(tblCompanyDto, company);
            return company;
        }
    }

    public static TblCompany editCompany(TblCompanyDto newCompany, TblCompany oldCompany) {
        if (newCompany == null || oldCompany == null) {
            return oldCompany;
        } else {
            if (ToolUtil.isNotEmpty(newCompany.getName())) {
                oldCompany.setName(newCompany.getName());
            }
            if(ToolUtil.isNotEmpty(newCompany.getAddress())){
                oldCompany.setAddress(newCompany.getAddress());
            }
            if(ToolUtil.isNotEmpty(newCompany.getAccount())){
                oldCompany.setAccount(newCompany.getAccount());
            }
            if(ToolUtil.isNotEmpty(newCompany.getLegalPerson())){
                oldCompany.setLegalPerson(newCompany.getLegalPerson());
            }
            if(ToolUtil.isNotEmpty(newCompany.getLogo())){
                oldCompany.setLogo(newCompany.getLogo());
            }
            if(ToolUtil.isNotEmpty(newCompany.getComment())){
                oldCompany.setComment(newCompany.getComment());
            }
            if(ToolUtil.isNotEmpty(newCompany.getLiveday())){
                oldCompany.setLiveday(newCompany.getLiveday());
            }
            if(ToolUtil.isNotEmpty(newCompany.getPassword())){
                oldCompany.setPassword(newCompany.getPassword());
            }
            if(ToolUtil.isNotEmpty(newCompany.getPersonLimit())){
                oldCompany.setPersonLimit(newCompany.getPersonLimit());
            }
            if(ToolUtil.isNotEmpty(newCompany.getTelphone())){
                oldCompany.setTelphone(newCompany.getTelphone());
            }
            if(ToolUtil.isNotEmpty(newCompany.getSort())){
                oldCompany.setSort(newCompany.getSort());
            }
            return oldCompany;
        }
    }
}
