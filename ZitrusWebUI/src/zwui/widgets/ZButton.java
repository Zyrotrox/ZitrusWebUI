package zwui.widgets;

public class ZButton extends ZWidget{

    /**
     * @var type of the button (submit | button)
     */
    private String btnType;

    private String text;

    private String value;

    /**
     * @var bootstrap glyphicon to be shown before the button text
     */
    private ZGlyphicon glyphicon;

    private String tooltip;

    private String tooltiphtml;

    public ZButton(String type){
        super();
        this.btnType = type;
    }

    @Override
    public String print(){
        String res;
        if(this.glyphicon != null){
            res = "<button type=\"" + this.btnType + "\" value=\""+this.value+"\" " + tooltiphtml + this.printTagOptions() + ">" + this.glyphicon.print()
                    + " " + this.text;
        }else{
            res = "<button type='" + this.btnType + "' "  + this.printTagOptions() + ">"
                    + " " + this.text;
        }
        return res + "</button>";
    }

    public String getBtnType() {
        return btnType;
    }

    public void setBtnType(String btnType) {
        this.btnType = btnType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ZGlyphicon getGlyphicon() {
        return glyphicon;
    }

    public void setGlyphicon(ZGlyphicon glyphicon) {
        this.glyphicon = glyphicon;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
        if(tooltip != null && tooltip.length() > 0){
            this.tooltiphtml = "data-toggle=\"tooltip\" title=\""+tooltip+"\" ";
        }else {
            this.tooltiphtml = "";
        }

    }
}
