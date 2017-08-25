package zwui.widgets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ZGridRow extends ZWidget {
    private int rowIndex;
    private List<ZGridChild> children;

    public ZGridRow(){
        super();
        this.children = new ArrayList<>();
        this.addStyleClass("row");
    }

    public void addChild(ZWidget child, int column, int columnSpan, String colType) throws Exception {
        this.children.add(new ZGridChild(child, column, columnSpan, colType));
    }

    @Override
    public String print(){
        String res = "<div " + this.printTagOptions() + " >";
        this.sortChildren();
        for(ZGridChild c : this.children){
            res += c.print();
        }
        res += "</div>";
        return res;
    }

    /**
     * sorts the children in the row in case they weren't added in the right order
     */
    private void sortChildren(){
        Collections.sort(this.children, new Comparator<ZGridChild>() {
            @Override
            public int compare(ZGridChild o1, ZGridChild o2) {
                if(o1.getColumn() > o2.getColumn()) return 1;
                if(o1.getColumn() < o2.getColumn()) return -1;
                return 0;
            }
        });
    }

    public boolean checkSpace(int span){
        int r = 0;
        for(ZGridChild c : this.children){
            r += c.getColumnSpan();
        }
        r += span;
        if(r > 12){
            return false;
        }else{
            return true;
        }
    }

    public void appendChild(ZWidget child, int span, String colType) throws Exception{
        this.sortChildren();
        int col = this.children.get(this.children.size()-1).getColumn() + this.children.get(this.children.size()-1).getColumnSpan();
        this.addChild(child, col, span, colType);
    }
}
