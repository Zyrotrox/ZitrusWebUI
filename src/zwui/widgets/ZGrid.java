package zwui.widgets;

import java.util.ArrayList;
import java.util.List;

public class ZGrid extends ZWidget {
    private List<ZGridChild> children;
    private List<ZGridRow> rows;

    private List<String> rowStyleClasses;

    private String gridColumnType;

    /**
     * draws a bootstrap grid, (12 columns responsive 'n shit
     */
    public ZGrid(){
        super();
        this.children = new ArrayList<>();
        this.rows = new ArrayList<>();
        this.gridColumnType = "col-md";
        this.rowStyleClasses = new ArrayList<>();
    }

    public void addChild(ZWidget child, int row, int column, int columnSpan) throws Exception{
        if(row < this.rows.size()){
            this.rows.get(row).addChild(child, column, columnSpan, gridColumnType);
        }else {
            ZGridRow r = initRow();
            r.addChild(child, column, columnSpan, gridColumnType);
            this.rows.add(r);
        }
    }

    public void addChild(ZWidget child, int columnSpan) throws Exception{
        if(rows.get(rows.size()-1).checkSpace(columnSpan)){
            rows.get(rows.size()-1).appendChild(child, columnSpan, this.gridColumnType);
        }else {
            ZGridRow r = initRow();
            r.addChild(child, 0, columnSpan, gridColumnType);
            this.rows.add(r);
        }
    }

    private ZGridRow initRow(){
        ZGridRow r = new ZGridRow();
        for(String styleClass : this.rowStyleClasses){
            r.addStyleClass(styleClass);
        }
        return r;
    }

    @Override
    public String print(){
        String res = "<div " + this.printTagOptions() + " >";
        for(ZGridRow r : this.rows){
            res += r.print();
        }
        res += "</div>";
        return res;
    }

    public void addRowStyleClass(String styleClass){
        this.rowStyleClasses.add(styleClass);
    }

    public String getGridColumnType() {
        return gridColumnType;
    }

    public void setGridColumnType(String gridColumnType) {
        this.gridColumnType = gridColumnType;
    }

    public List<String> getRowStyleClasses() {
        return rowStyleClasses;
    }

    public void setRowStyleClasses(List<String> rowStyleClasses) {
        this.rowStyleClasses = rowStyleClasses;
    }
}
