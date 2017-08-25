package zwui.widgets;

public class ZGridChild extends ZWidget{
    private static final int MAX_COLUMNS = 12;

    private ZWidget child;
    private int column;
    private int columnSpan;
    private String columnType;

    public ZGridChild(){
        super();
    }

    public ZGridChild(ZWidget child, int column, String columntype) throws Exception{
        this();
        if (!checkColumns(column, 1)){
            throw new Exception("ZGRID COLUMN EXCEPTION: a row cannot have more than " + MAX_COLUMNS + " columns!");
        }
        this.child = child;
        this.column = column;
        this.columnSpan = 1;
        this.columnType = columntype;
        this.initChild();
    }

    public ZGridChild(ZWidget child, int column, int columnSpan, String columntype) throws Exception{
        this();
        if (!checkColumns(column, columnSpan)){
            throw new Exception("ZGRID COLUMN EXCEPTION: a row cannot have more than " + MAX_COLUMNS + " columns!");
        }
        this.child = child;
        this.column = column;
        this.columnSpan = columnSpan;
        this.columnType = columntype;
        this.initChild();
    }

    /**
     * initializes child object
     */
    private void initChild(){
        this.styleClasses.add(this.columnType + "-" + this.columnSpan);
    }

    @Override
    public String print(){
        String res = "<div " + this.printTagOptions() + " >";
        res += this.child.print();
        res += "</div>";
        return res;
    }

    /**
     * checks if the child is out of grid bounds
     * @param col start column
     * @param span column span
     * @return
     */
    private boolean checkColumns(int col, int span){
        if(col > this.MAX_COLUMNS || col + span > this.MAX_COLUMNS){
            return false;
        }
        else{
            return true;
        }
    }

    public ZWidget getChild() {
        return child;
    }

    public void setChild(ZWidget child) {
        this.child = child;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getColumnSpan() {
        return columnSpan;
    }

    public void setColumnSpan(int columnSpan) {
        this.columnSpan = columnSpan;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }
}
