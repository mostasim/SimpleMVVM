
package tech.mbsoft.simplemvvm.repository.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Currency {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("name")
    @Expose
    private Object name;
    @SerializedName("symbol")
    @Expose
    private Object symbol;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getSymbol() {
        return symbol;
    }

    public void setSymbol(Object symbol) {
        this.symbol = symbol;
    }

}
