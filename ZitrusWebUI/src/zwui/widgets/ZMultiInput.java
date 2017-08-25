package zwui.widgets;

import zwui.widgets.types.ZLabelType;

import java.util.ArrayList;
import java.util.List;

public class ZMultiInput extends ZWidget {
    private List<ZLabel> options;
    private String inputNames;
    private String inputType;
    private boolean inline;

    private ZMultiInput(){
        super();
        this.options = new ArrayList<>();
        this.inputType = "checkbox";
        this.addStyleClass("MultiInputContainer");
    }

    public ZMultiInput(String inputNames){
        this();
        this.inputNames = inputNames;
    }

    @Override
    public String print(){
        String res = "<div " + this.printTagOptions() + " >";
        for(ZLabel option : options){
            res += option.print();
        }
        res += "</div>";
        return res;
    }

    public void addOption(String text, String value){
        ZInput option = new ZInput();
        ZLabel lbl = new ZLabel(ZLabelType.LBL);
        lbl.addCustomTag(new String[]{"for", option.getId()});
        lbl.setText(text);
        if(this.inline){
            lbl.addStyleClass(this.inputType + "-inline");
        }
        option.setType(inputType);
        option.setStyleClasses(new ArrayList<>());
        option.setValue(value);
        option.setName(this.inputNames);
        lbl.setChild(option);
        this.options.add(lbl);
    }


    public String getInputNames() {
        return inputNames;
    }

    public void setInputNames(String inputNames) {
        this.inputNames = inputNames;
    }

    public List<ZLabel> getOptions() {
        return options;
    }

    public void setOptions(List<ZLabel> options) {
        this.options = options;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public boolean isInline() {
        return inline;
    }

    public void setInline(boolean inline) {
        this.inline = inline;
    }
}
