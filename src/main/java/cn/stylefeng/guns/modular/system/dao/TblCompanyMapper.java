package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.TblCompany;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-19
 */
public interface TblCompanyMapper extends BaseMapper<TblCompany> {
    TblCompany getByCompanyName(@Param("name") String name);
}
