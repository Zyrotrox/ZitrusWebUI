package zwui.widgets;

public class ZInput extends ZWidget {
    private String value;
    private String placeholder;
    private String type;

    private String onValueChangedJS;

    public ZInput(){
        super();
        this.addStyleClass("form-control");
        this.value = "";
        this.placeholder = "";
        this.type = "text";
    }

    public ZInput(String value){
        this.value = value;
        this.placeholder = "";
        this.type = "text";
    }

    @Override
    public String print(){
        String res = "<input type=\""+type+"\" placeholder=\""+this.placeholder+"\" " +
                "value=\""+this.value+"\" onchange=\""+this.onValueChangedJS+"($(this));\" " + this.printTagOptions() + " />";
        return res;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getOnValueChangedJS() {
        return onValueChangedJS;
    }

    public void setOnValueChangedJS(String onValueChangedJS) {
        this.onValueChangedJS = onValueChangedJS;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
