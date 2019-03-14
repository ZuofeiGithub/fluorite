package cn.stylefeng.guns.modular.companymanager.service;

import cn.stylefeng.guns.modular.system.model.TblCompany;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-19
 */
public interface ITblCompanyService extends IService<TblCompany> {

    /**
     * 根据公司ID获取公司
     * @param name
     * @return
     */
    TblCompany getByCompanyName(String name);
}
