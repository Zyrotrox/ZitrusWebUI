package zwui.widgets;

public class ZGlyphicon extends ZWidget{

    private String iconText;

    public ZGlyphicon(String icon){
        super();
        this.iconText = "<span class=\"glyphicon glyphicon-" + icon + "\"></span>";
    }

    @Override
    public String print(){
        return this.iconText;
    }
}
