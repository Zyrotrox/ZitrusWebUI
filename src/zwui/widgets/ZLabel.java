package zwui.widgets;

import zwui.widgets.types.ZLabelType;

public class ZLabel extends ZWidget {
    private ZLabelType labelType;

    private String text;
    private String targetId;

    public ZLabel(ZLabelType zlt){
        super();
        this.labelType = zlt;
        this.id = "lbl_" + this.generateId();
    }

    @Override
    public String print(){
        String options = this.printTagOptions();
        if(this.targetId != null){
            this.addCustomTag("for", targetId);
        }
        if(this.child != null){
            return this.labelType.getStartTag(options) + this.child.print() + this.text + this.labelType.getEndTag();
        }else {
            return this.labelType.getStartTag(options) +this.text + this.labelType.getEndTag();
        }
    }

    public ZLabelType getLabelType() {
        return labelType;
    }

    public void setLabelType(ZLabelType labelType) {
        this.labelType = labelType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }
}
