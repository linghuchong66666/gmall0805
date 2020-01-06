package com.atguigu.gmall.pms.vo;

import com.atguigu.gmall.pms.entity.ProductAttrValueEntity;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Data
public class BaseAttrValueVO extends ProductAttrValueEntity {

    private List<String> valueSelected;

    public void SetValueSelected(List<String> valueSelected){
        if (!CollectionUtils.isEmpty(valueSelected)){
            this.setAttrValue(StringUtils.join(valueSelected,","));
        }else {
            this.setAttrValue(null);
        }
    }
}
