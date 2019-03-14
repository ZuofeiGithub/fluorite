package cn.stylefeng.guns.modular.companymanager.service.impl;

import cn.stylefeng.guns.modular.system.model.TblCompany;
import cn.stylefeng.guns.modular.system.dao.TblCompanyMapper;
import cn.stylefeng.guns.modular.companymanager.service.ITblCompanyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-19
 */
@Service
public class TblCompanyServiceImpl extends ServiceImpl<TblCompanyMapper, TblCompany> implements ITblCompanyService {

    @Override
    public TblCompany getByCompanyName(String name) {
        return this.baseMapper.getByCompanyName(name);
    }
}
