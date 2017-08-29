package zwui.widgets.types;

public enum ZLabelType {
    H1, H2, H3, H4, H5, LBL, FONT;

    public String getStartTag(String options){
        switch (this){
            case H1:
                return "<h1 " + options + ">";
            case H2:
                return "<h2 " + options + ">";
            case H3:
                return "<h3 " + options + ">";
            case H4:
                return "<h4 " + options + ">";
            case H5:
                return "<h5 " + options + ">";
            case LBL:
                return "<label " + options + ">";
            case FONT:
                return "<font " + options + ">";
            default:
                return "Error";
        }
    }

    public String getEndTag(){
        switch (this){
            case H1:
                return "</h1>";
            case H2:
                return "</h2>";
            case H3:
                return "</h3>";
            case H4:
                return "</h4>";
            case H5:
                return "</h5>";
            case LBL:
                return "</label>";
            case FONT:
                return "</font>";
            default:
                return "Error";
        }
    }
}
