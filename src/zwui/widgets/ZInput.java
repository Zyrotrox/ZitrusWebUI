package zwui.widgets;

public class ZInput extends ZWidget {
    private String value;
    private String placeholder;
    private String type;

    private String onValueChangedJS;
    private String onInputJS;

    private boolean required;

    public ZInput(){
        super();
        this.addStyleClass("form-control");
        this.value = "";
        this.placeholder = "";
        this.type = "text";
        this.required = false;
    }

    public ZInput(String value){
        this.value = value;
        this.placeholder = "";
        this.type = "text";
        this.required = false;
    }

    @Override
    public String print(){
        String res = "<input type=\""+type+"\" ";
        if(this.placeholder != null && this.placeholder.length() > 0){
            res += "placeholder=\""+this.placeholder+"\" ";
        }
        if(this.value != null && this.value.length() > 0){
            res += "value=\""+this.value+"\" ";
        }
        if(this.onValueChangedJS != null && this.onValueChangedJS.length() > 0){
            res += "onchange=\""+this.onValueChangedJS+"\" ";
        }
        if(this.onInputJS != null && this.onInputJS.length() > 0){
            res += "oninput=\""+this.onInputJS+"\" ";
        }
        if(this.required){
            res += "required";
        }
        res += this.printTagOptions() + " />";

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

    public String getOnInputJS() {
        return onInputJS;
    }

    public void setOnInputJS(String onInputJS) {
        this.onInputJS = onInputJS;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
