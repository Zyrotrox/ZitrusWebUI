package zwui.widgets;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ZWidget {
    /**
     * @var html id for the widget
     */
    protected String id;
    /**
     * @var css style classes for the widget
     */
    protected List<String> styleClasses;
    /**
     * @var name for the widget
     */
    protected String name;
    /**
     * @var custom tags, List of Array(key, value)
     */
    protected List<String[]> customTags;
    /**
     * @var css style attributes
     */
    protected List<String> css;
    /**
     * @var html code for custom widgets
     */
    private String htmlCode;
    /**
     * @var html tag for custom widgets
     */
    private String tagName;

    protected ZWidget child;

    /** @var JS FUNCTION NAMES **/
    protected String onClickJS;

    public ZWidget(){
        this.styleClasses = new ArrayList<>();
        this.customTags = new ArrayList<>();
        this.css = new ArrayList<>();
        this.id = "widget_" + generateId();
    }

    /**
     * generates a id for the html widget
     * @return id
     */
    protected int generateId(){
        int id = new Random().nextInt();
        if(id < 0){
            id *= -1;
        }
        return id;
    }

    public String print(){
        String res = "<" + this.tagName + " " + this.printTagOptions() + " >";
        if(this.htmlCode != null){
            res += this.htmlCode;
        }
        if(this.child != null){
            res += " " + this.child.print();
        }
        res += "</" + this.tagName + ">";

        return res;
    }

    /**
     * returns a string w/ the 'class' property of an html element
     * @return html code
     */
    public String printClasses(){
        String res = "class=\"";
        for(String c : this.styleClasses){
            res += c + " ";
        }
        res += "\"";
        return res;
    }

    /**
     * returns a string w/ the defined custom tags
     * @return html code
     */
    public String printCustomTags(){
        String res = "";
        if(this.customTags.size() > 0){
            for(String[] tag : this.customTags){
                res += tag[0] + "=\"" + tag[1] + "\" ";
            }
        }
        return res;
    }

    public String printOnClickJS(){
        if(this.onClickJS != null && this.onClickJS.length() > 0){
            return "onclick=\"" + this.onClickJS + "\" ";
        }else {
            return "";
        }
    }

    public String printCssStyle(){
        String res = "style=\"";
        for(String css : this.css){
            res += css + "; ";
        }
        res += "\"";
        return res;
    }

    public String printTagOptions(){
        // name=\"" +this.name +"\""  + this.printClasses() + " " + this.printCssStyle() + " " + this.printCustomTags() + " " + this.printOnClickJS();
        String res = "id=\"" + this.id + "\" ";
        if(this.name != null){
            res += "name=\"" + this.name + "\" ";
        }
        if(this.styleClasses.size() > 0){
            res += this.printClasses();
        }
        if(this.css.size() > 0){
            res += this.printCssStyle();
        }
        if(this.customTags.size() > 0){
            res += this.printCustomTags();
        }
        if(this.onClickJS != null){
            res += this.printOnClickJS();
        }
        return res;
    }

    public void addStyleClass(String className){
        this.styleClasses.add(className);
    }
    public void addStyleClasses(String[] classes){
        for(String c : classes){
            this.styleClasses.add(c);
        }
    }
    public void addCustomTag(String[] tag){
        this.customTags.add(tag);
    }
    public void addCustomTags(String[][] tags){
        for(String[] c : tags){
            this.customTags.add(c);
        }
    }
    public void addCss(String css){
        this.css.add(css);
    }
    /** GETTERS AND SETTERS **/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getStyleClasses() {
        return styleClasses;
    }

    public void setStyleClasses(List<String> styleClasses) {
        this.styleClasses = styleClasses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String[]> getCustomTags() {
        return customTags;
    }

    public void setCustomTags(List<String[]> customTags) {
        this.customTags = customTags;
    }

    public String getOnClickJS() {
        return onClickJS;
    }

    public void setOnClickJS(String onClickJS) {
        this.onClickJS = onClickJS;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }

    public String getHtmlCode() {
        return htmlCode;
    }

    public void setHtmlCode(String htmlCode) {
        this.htmlCode = htmlCode;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public ZWidget getChild() {
        return child;
    }

    public void setChild(ZWidget child) {
        this.child = child;
    }
}
