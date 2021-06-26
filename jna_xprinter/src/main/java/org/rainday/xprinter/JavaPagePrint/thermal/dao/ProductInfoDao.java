package org.rainday.xprinter.JavaPagePrint.thermal.dao;

/**
 * @author mox
 * @date 2020/4/3 17:48
 */

public class ProductInfoDao {

    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品单价
     */

    private String unitPrice;
    /**
     * 商品数量
     */
    private String num;
    /**
     * 商品总价
     */
    private String sum;

    public ProductInfoDao(String name, String unitPrice, String num, String sum) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.num = num;
        this.sum = sum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }
}
