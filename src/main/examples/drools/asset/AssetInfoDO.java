package examples.drools.asset;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;

/**
 * Created by leslie on 2017/10/24.
 */
public class AssetInfoDO {

    // 首付比例
    private BigDecimal downPayment;

    // 贷款利率
    private BigDecimal loanInterest;

    public BigDecimal getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(BigDecimal downPayment) {
        this.downPayment = downPayment;
    }

    public BigDecimal getLoanInterest() {
        return loanInterest;
    }

    public void setLoanInterest(BigDecimal loanInterest) {
        this.loanInterest = loanInterest;
    }
}
