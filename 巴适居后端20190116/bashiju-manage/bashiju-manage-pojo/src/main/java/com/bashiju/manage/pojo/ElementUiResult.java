package com.bashiju.manage.pojo;
import java.util.ArrayList;
import java.util.List;
public class ElementUiResult
{
    private String value;

    private String label;

    private List<ElementUiResult> elementUiResult;

    public void setValue(String value){
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
    public void setLabel(String label){
        this.label = label;
    }
    public String getLabel(){
        return this.label;
    }
    public void setChildren(List<ElementUiResult> children){
        this.elementUiResult = children;
    }
    public List<ElementUiResult> getChildren(){
        return this.elementUiResult;
    }
}

