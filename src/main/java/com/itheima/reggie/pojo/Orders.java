package com.itheima.reggie.pojo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author panajenemo
 * @since 2023-01-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Orders对象", description="订单表")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "订单号")
    private String number;

    @ApiModelProperty(value = "订单状态 1待付款，2待派送，3已派送，4已完成，5已取消")
    private Integer status;

    @ApiModelProperty(value = "下单用户")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "地址id")
    @TableField("address_book_id")
    private Long addressBookId;

    @ApiModelProperty(value = "下单时间")
    @TableField("order_time")
    private LocalDateTime orderTime;

    @ApiModelProperty(value = "结账时间")
    @TableField("checkout_time")
    private LocalDateTime checkoutTime;

    @ApiModelProperty(value = "支付方式 1微信,2支付宝")
    @TableField("pay_method")
    private Integer payMethod;

    @ApiModelProperty(value = "实收金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "备注")
    private String remark;

    private String phone;

    private String address;

    @TableField("user_name")
    private String userName;

    private String consignee;


}
