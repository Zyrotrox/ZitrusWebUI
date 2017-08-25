package zwui.widgets;

import java.util.ArrayList;
import java.util.List;

public class ZContainer extends ZWidget {
    private List<ZWidget> children;

    public ZContainer(){
        super();
        this.addStyleClass("zcontainer");
        this.children = new ArrayList<>();
    }

    @Override
    public String print() {
        String res = "<div " + this.printTagOptions() + " >";
        for(ZWidget w : this.children){
            res += w.print();
        }
        res += "</div>";
        return res;
    }

    public void addChild(ZWidget child){
        this.children.add(child);
    }

    public List<ZWidget> getChildren() {
        return children;
    }

    public void setChildren(List<ZWidget> children) {
        this.children = children;
    }
}
