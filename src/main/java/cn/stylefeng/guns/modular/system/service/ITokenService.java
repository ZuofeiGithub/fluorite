package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.model.Token;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zuofei
 * @since 2019-03-27
 */
public interface ITokenService extends IService<Token> {
    Token selectTokenById(Integer id);
}
