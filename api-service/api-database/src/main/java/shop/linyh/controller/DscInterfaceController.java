package shop.linyh.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.linyh.helper.DscInterfaceHelper;
import xyz.linyh.ducommon.common.BaseResponse;
import xyz.linyh.ducommon.common.ResultUtils;

import javax.servlet.http.HttpServletRequest;


/**
 * @author linzz
 */
@RestController
public class DscInterfaceController {

    private DscInterfaceHelper dscInterfaceHelper;

    /**
     * 处理数据源接口
     * @param request
     * @return
     */
    @PostMapping("/yhapiBatabase")
    public BaseResponse<String> processDatasourceInterface(HttpServletRequest request){
        String result = dscInterfaceHelper.queryData(request);
        return ResultUtils.success(result);
    }
}
