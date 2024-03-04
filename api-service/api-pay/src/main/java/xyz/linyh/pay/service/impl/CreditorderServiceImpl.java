package xyz.linyh.pay.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.linyh.ducommon.common.ErrorCode;
import xyz.linyh.ducommon.constant.PayConstant;
import xyz.linyh.ducommon.exception.BusinessException;
import xyz.linyh.model.pay.dto.CreateCreditOrderDto;
import xyz.linyh.model.pay.eneity.CreditOrder;
import xyz.linyh.model.pay.eneity.CreditProducts;
import xyz.linyh.pay.mapper.CreditOrderMapper;
import xyz.linyh.pay.service.CreditOrderService;
import xyz.linyh.pay.service.CreditProductsService;

import java.time.LocalDateTime;


/**
 * @author lin
 * @description 针对表【creditOrder(积分订单表)】的数据库操作Service实现
 * @createDate 2024-03-04 20:47:55
 */
@Service
public class CreditorderServiceImpl extends ServiceImpl<CreditOrderMapper, CreditOrder>
        implements CreditOrderService {

    @Autowired
    private CreditProductsService creditProductsService;


    @Override
    public CreditOrder createCreditOrder(CreateCreditOrderDto dto, Long userId) {
//        0. 参数校验
        if (dto == null || dto.getProductId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "创建订单需要携带产品id等等参数");
        }
        if (dto.getNum() < 1 || dto.getNum() >= 999) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "创建订单需要携带数量,并且需要合法");
        }

//        1. 获取商品判断商品是否存在
        CreditProducts creditProduct = creditProductsService.getById(dto.getProductId());
        if (creditProduct == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "商品不存在");
        }

//        2. 根据请求参数创建订单数据
        CreditOrder creditOrder = new CreditOrder();
        creditOrder.setOrderNo(getOrderId());
        creditOrder.setUserId(userId);
        creditOrder.setProductId(0L);
        creditOrder.setOrderName(creditProduct.getDescription());
        creditOrder.setTotal(dto.getNum() * creditProduct.getPrice());
        creditOrder.setStatus(PayConstant.ORDER_STATIC_UNPAID);
        creditOrder.setPayType(dto.getPayType().toString());
        creditOrder.setProductInfo(creditProduct.getDescription());
        creditOrder.setAddPoints(creditOrder.getAddPoints());
        creditOrder.setExpirationTime(LocalDateTime.now().plusDays(1L).toEpochSecond(java.time.ZoneOffset.of("+8")));
        creditOrder.setCreateTime(LocalDateTime.now().toEpochSecond(java.time.ZoneOffset.of("+8")));
        creditOrder.setUpdateTime(LocalDateTime.now().toEpochSecond(java.time.ZoneOffset.of("+8")));
        boolean saveResult = this.save(creditOrder);
        if (!saveResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "创建订单失败");
        }
        return creditOrder;
    }

    /**
     * 根据雪花算法生成订单id
     *
     * @return
     */
    private String getOrderId() {
        Snowflake snowflake = IdUtil.getSnowflake();
        return snowflake.nextIdStr();
    }
}




