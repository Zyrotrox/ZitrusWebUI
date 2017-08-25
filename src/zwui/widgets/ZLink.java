package zwui.widgets;

import java.util.ArrayList;
import java.util.List;

public class ZLink extends ZWidget{
    private String href;
    private String text;
    private List<String[]> parameters;

    private ZGlyphicon glyphicon;
    private String tooltip;
    private String tooltiphtml;

    public ZLink(){
        super();
        this.parameters = new ArrayList<>();
        this.href = "#";
    }

    public ZLink(String href){
        super();
        this.parameters = new ArrayList<>();
        this.href = href;
    }

    @Override
    public String print(){
        String res = "<a href=\""+this.href+ getParameterString() +"\" " + tooltiphtml + this.printTagOptions() + " >";
        if(this.glyphicon != null){
            res += this.glyphicon.print() + " ";
        }
        res += this.text;
        res += "</a>";
        return res;
    }

    private String getParameterString(){
        String res = "";
        if(this.parameters.size() > 0){
            res += "?";
            for(String[] para : parameters){
                res += para[0] + "=" + para[1] + "&";
            }
            res = res.substring(0, res.length()-1);
        }
        return res;
    }

    public void addParameter(String[] parameter){
        this.parameters.add(parameter);
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
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

    public List<String[]> getParameters() {
        return parameters;
    }

    public void setParameters(List<String[]> parameters) {
        this.parameters = parameters;
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

