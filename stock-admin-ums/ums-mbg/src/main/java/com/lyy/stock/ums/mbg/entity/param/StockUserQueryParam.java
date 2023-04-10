package com.lyy.stock.ums.mbg.entity.param;

import com.lyy.stock.ums.mbg.entity.po.StockUser;
import lombok.Data;

/**
 * @Author:
 * @createTime: 2023/04/10 15:08:03
 * @version:
 * @Description:
 */
@Data
public class StockUserQueryParam extends StockUser {

    protected String orderByClause;

    protected boolean distinct;
}
