package xyz.linyh.pay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.linyh.pay.mapper.CreditProductsMapper;
import xyz.linyh.pay.service.CreditProductsService;
import xyz.linyh.model.pay.dto.CreditProductDto;
import xyz.linyh.model.pay.eneity.CreditProducts;

/**
* @author lin
* @description 针对表【creditproducts(积分商品表)】的数据库操作Service实现
* @createDate 2023-12-26 13:51:22
*/
@Service
public class CreditProductsServiceImpl extends ServiceImpl<CreditProductsMapper, CreditProducts>
    implements CreditProductsService {

    @Override
    public boolean addCreditProduct(CreditProductDto dto) {
        CreditProducts creditProducts = new CreditProducts();
        creditProducts.setDescription(dto.getDescription());
        creditProducts.setPrice(dto.getPrice());
        creditProducts.setIntegral(dto.getIntegral());
        creditProducts.setPicture(dto.getPicture());
        creditProducts.setDiscountPrice(dto.getDiscountPrice());

        return this.save(creditProducts);
    }
}




