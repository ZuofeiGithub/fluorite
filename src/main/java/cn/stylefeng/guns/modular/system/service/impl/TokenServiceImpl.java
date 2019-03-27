package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.model.Token;
import cn.stylefeng.guns.modular.system.dao.TokenMapper;
import cn.stylefeng.guns.modular.system.service.ITokenService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zuofei
 * @since 2019-03-27
 */
@Service
public class TokenServiceImpl extends ServiceImpl<TokenMapper, Token> implements ITokenService {
    @Resource
    TokenMapper tokenMapper;
    @Override
    public Token selectTokenById(Integer id) {
        return tokenMapper.selectById(id);
    }
}
