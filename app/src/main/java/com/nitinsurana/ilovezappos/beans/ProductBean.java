package com.nitinsurana.ilovezappos.beans;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Created by coding_idiot on 06/02/17.
 */

public class ProductBean implements Serializable {
    //    {"brandName":"Globe","thumbnailImageUrl":"http:\/\/www.zappos.com\/images\/z\/1\/1\/8\/6\/5\/3\/1186538-t-THUMBNAIL.jpg",
//            "productId":"7174012","originalPrice":"$50.00","styleId":"1186538",
//            "colorId":"38264","price":"$50.00","percentOff":"0%","productUrl":"http:\/\/www.zappos.com\/product\/7174012\/color\/38264","productName":"Castro"},
    private String brandName, thumbnailImageUrl, productId, originalPrice, styleId;
    private String colorId, price, percentOff, productUrl, productName;
    private float f_price, f_originalPrice;

    public float getF_price() {
        return f_price;
    }

    public void setF_price(float f_price) {
        this.f_price = f_price;
    }

    public float getF_originalPrice() {
        return f_originalPrice;
    }

    public void setF_originalPrice(float f_originalPrice) {
        this.f_originalPrice = f_originalPrice;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    public void setThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
        if (StringUtils.isNotBlank(originalPrice) && StringUtils.isNotBlank(originalPrice.replace("$", ""))) {
            this.setF_originalPrice(Float.parseFloat(originalPrice.replace("$", "")));
        } else {
            this.setF_originalPrice(0.0f);
        }
    }

    public String getStyleId() {
        return styleId;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
        if (StringUtils.isNotBlank(price) && StringUtils.isNotBlank(price.replace("$", ""))) {
            this.setF_price(Float.parseFloat(price.replace("$", "")));
        } else {
            this.setF_price(0.0f);
        }
    }

    public String getPercentOff() {
        return percentOff;
    }

    public void setPercentOff(String percentOff) {
        this.percentOff = percentOff;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
