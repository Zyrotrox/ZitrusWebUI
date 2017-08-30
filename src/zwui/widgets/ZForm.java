package zwui.widgets;

import java.util.List;

public class ZForm extends ZWidget {
    private ZGrid grd_form;
    private String method;
    private String action;
    private ZButton btn_submit;
    private ZButton btn_clear;
    private ZLink lnk_back;

    public ZForm(){
        super();
        this.grd_form = new ZGrid();
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
        String res = "<form action=\""+this.action+"\" method=\""+this.method+"\" "
                + printTagOptions() + " >";
        res += grd_form.print();
        res += btn_submit.print();
        if(btn_clear != null){
            res += btn_clear.print();
        }
        if(lnk_back != null){
            res += lnk_back.print();
        }
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

    public void setSubmitButtonText(String text){
        this.btn_submit.setText(text);
    }

    public ZLink getLnk_back() {
        return lnk_back;
    }

    public void setLnk_back(ZLink lnk_back) {
        this.lnk_back = lnk_back;
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

    public ZButton getBtn_clear() {
        return btn_clear;
    }

    public void setBtn_clear(ZButton btn_clear) {
        this.btn_clear = btn_clear;
    }
}
