package zwui.widgets;

import java.util.ArrayList;
import java.util.List;

public class ZForm extends ZWidget {
    private ZGrid grd_form;
    private String method;
    private String action;
    private List<ZWidget> formButtons;
    private ZButton btn_submit;
    private List<ZInput> hiddenFields;
    private List<String[]> urlParameters;

    public ZForm(){
        super();
        this.grd_form = new ZGrid();
        this.hiddenFields = new ArrayList<>();
        formButtons = new ArrayList<>();
        initForm();
    }

    private void initForm(){
        grd_form.addStyleClass("form-horizontal");
        grd_form.addRowStyleClass("form-group");
        btn_submit = new ZButton("submit");
        btn_submit.setGlyphicon(new ZGlyphicon("floppy-disk"));
        btn_submit.setText("save");
        btn_submit.addStyleClasses(new String[]{"btn", "btn-success"});
    }

    @Override
    public String print(){
        if(this.urlParameters != null && this.urlParameters.size() > 0){
            action += "?";
            for(String[] para : this.urlParameters)
                action += para[0] + "=" + para[1] + "&";
            action = action.substring(0, action.length()-1);
        }
        String res = "<form action=\""+this.action+"\" method=\""+this.method+"\" "
                + printTagOptions() + " >";
        if(this.hiddenFields.size() > 0){
            for(ZInput inp : hiddenFields){
                res += inp.print();
            }
        }
        res += grd_form.print();
        ZContainer btns = new ZContainer();
        btns.addChild(btn_submit);
        for(ZWidget w : this.formButtons){
            btns.addChild(w);
        }
        res += btns.print();

        res += "</form>";
        return res;
    }

    public void addStep(ZWidget label, ZWidget input){
        try {
            grd_form.addChild(label, 3);
            grd_form.addChild(input, 9);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addStep(ZWidget label, ZWidget input, boolean required){
        if(required){
            try {
                input.addCustomTag(new String[]{"required", "required"});
                grd_form.addChild(label, 3);
                grd_form.addChild(input, 9);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            addStep(label, input);
        }
    }

    public void addHiddenField(String name, String value){
        ZInput inp = new ZInput(value);
        inp.setName(name);
        inp.setType("hidden");
        this.hiddenFields.add(inp);
    }

    public void addUrlParameter(String name, String value){
        this.urlParameters.add(new String[]{name, value});
    }

    public void setSubmitButtonText(String text){
        this.btn_submit.setText(text);
    }

    public void addFormButton(ZWidget widget){
        this.formButtons.add(widget);
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ZButton getBtn_submit() {
        return btn_submit;
    }

    public void setBtn_submit(ZButton btn_submit) {
        this.btn_submit = btn_submit;
    }

    public List<ZInput> getHiddenFields() {
        return hiddenFields;
    }

    public void setHiddenFields(List<ZInput> hiddenFields) {
        this.hiddenFields = hiddenFields;
    }

    public List<String[]> getUrlParameters() {
        return urlParameters;
    }

    public void setUrlParameters(List<String[]> urlParameters) {
        this.urlParameters = urlParameters;
    }
}
