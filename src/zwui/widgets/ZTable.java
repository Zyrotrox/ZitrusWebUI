package zwui.widgets;

import zwui.widgets.types.ZLabelType;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ZTable<E> extends ZWidget {
    private Class<E> type;

    /**
     * @var source list of objects for the table
     */
    protected List<E> rows;

    /**
     * @var list of Strings that the table displays in the header row
     */
    protected List<String> displayHeaders;

    /**
     * @var 2 dimensional list of Strings for the table rows that get displayed
     */
    protected List<List<String>> displayRows;


    /** TABLE PREFERENCES **/
    private String header;          //Header to be shown above the table
    private boolean hideable = false;        //if Table is hideable
    private ZButton hideBtn;
    private int[] displayColumns;            //columns to display when printing table, show all when null or empty

    //JS FUNCTIONS
    private String hideFunction;
    /**
     * @var name of the JS function to execute when row is clicked, a onclick function must
     * take one parameter for the jquery element object for the clicked row
     */
    private String rowOnClick;


    public ZTable(){
        super();
        this.id = "table_" + generateId();
        this.displayHeaders = new ArrayList<>();
        this.displayRows = new ArrayList<>();
        this.hideFunction = "toggleWidgetVisibility";
        this.addStyleClass("table");
        this.addCustomTag(new String[] {"border", "1"});
    }

    /**
     * initilizes table w/ given type (draws header w/out rows)
     * @param type
     */
    public ZTable(Class type){
        this();
        this.type = type;
        initTable(type);
    }

    /**
     * initializes a new ZTable w/ given rows
     * @param rows
     */
    public ZTable(List<E> rows){
        this();
        this.rows = rows;
        if(rows.size() > 0){
            initTable(rows.get(0).getClass());
        }
    }

    /**
     * initializes a new ZTable z/ given rows, sets the type (if rows r empty)
     * @param type
     * @param rows
     */
    public ZTable(Class type, List<E> rows){
        this();
        this.rows = rows;
        this.type = type;
        initTable(type);
    }

    /**
     * prints the table
     * @return html code
     */
    @Override
    public String print() {
        if(this.displayColumns != null && this.displayColumns.length > 0) {
            return print(this.displayColumns);
        }
        String res = printHeader();
        res += "<table id='"+this.id+"' class='table'>"
                + "<tr>";

        for(String dh : this.displayHeaders) {
            res += "<th>"+ dh + "</th>";
        }
        res += "</tr>";

        for(List<String> row : this.displayRows) {
            res += "<tr onClick=\""+ this.rowOnClick +"($(this));\">";
            for(String col : row) {
                res += "<td>"+ col + "</td>";
            }
            res += "</tr>";
        }
        res += "</table>";

        return res;
    }


    /**
     * prints the table w/ the specified columns to display
     * @param displayCols columns to display
     * @return html code
     */
    public String print(int[] displayCols) {
        String res = printHeader();
        res += "<table "+this.printTagOptions()+">"
                + "<tr>";

        int i = 0;
        for(String dh : this.displayHeaders) {
            if(contains(displayCols, i)) {
                res += "<th>"+ dh + "</th>";
                System.out.println("TABLE COLUMN: " + dh);
            }
            i++;
        }
        res += "</tr>";

        for(List<String> row : this.displayRows) {
            res += "<tr onClick=\""+ this.rowOnClick +"($(this));\">";
            i = 0;
            for(String col : row) {
                if(contains(displayCols, i)) {
                    res += "<td>"+ col + "</td>";
                }
                i++;
            }
            res += "</tr>";
        }
        res += "</table>";

        return res;
    }

    /**
     * gets the header for the table
     * @return html code
     */
    private String printHeader() {
        String res = "";
        if(this.header != null) {
            ZLabel header = new ZLabel(ZLabelType.H2);
            if(this.hideable) {
                String btn = " <button type='button' class='btn btn-table' "
                        + "onclick=\""+this.hideFunction+"('"+this.id+"', $(this));\">"
                        + "<span class='glyphicon glyphicon-chevron-up'></span>"
                        + "</button>";

                header.setText(this.header + " " + this.hideBtn.print());
                res = header.print();
            }else{
                header.setText(this.header);
                res = header.print();
            }
        }
        return res;
    }

    /**
     * checks if an int array contains a key
     * @param array
     * @param key
     * @return
     */
    private boolean contains(int[] array, int key) {
        for(int i : array) {
            if(i == key) {
                return true;
            }
        }
        return false;
    }

    /**
     * sorts the table columns
     * @param orderedColumnNames specified order
     */
    public void orderColumns(String[] orderedColumnNames) throws Exception {
        List<String> orderedHeader = new ArrayList<>();
        List<List<String>> orderedRows = new ArrayList<>();
        List<Integer> orderKeys = new ArrayList<>();
        int key;
        for(String nCol : orderedColumnNames){
            key = this.displayHeaders.indexOf(nCol);
            orderKeys.add(key);
            orderedHeader.add(this.displayHeaders.get(key));
        }
        for(List<String> row : this.displayRows){
            orderedRows.add(orderList(orderKeys, row));
        }
        this.displayHeaders = orderedHeader;
        this.displayRows = orderedRows;
    }

    /**
     * orders a List after the given keys
     * @param keys new order
     * @param source list to sort
     * @return sorted list
     * @throws IndexOutOfBoundsException when keys are out of bound
     */
    private List<String> orderList(List<Integer> keys, List<String> source) throws IndexOutOfBoundsException{
        List<String> ordered = new ArrayList<>();
        for(int k : keys){
            ordered.add(source.get(k));
        }
        return ordered;
    }

    /**
     * initializes the display rows for the table
     * @param type
     */
    protected void initTable(Class type){
        this.type = type;

        if(type == String[].class){
            initStringTable();
        }//TODO standard types
        else{
            this.displayHeaders = initObjectTableHeaders();
            this.displayRows = initObjectTable();
        }
    }

    /**
     * initializes a Table of String arrays, the first row is the header row
     */
    protected void initStringTable(){
        List<String[]> rows = (List<String[]>)this.rows;
        this.displayHeaders = Arrays.asList(rows.get(0));
        for(int i = 1; i < rows.size(); i++){
            this.displayRows.add(Arrays.asList(rows.get(i)));
        }
    }

    /**
     * initializes a Table of unknown objects, override for custom tables
     * @return 2 dimensional String list for the table rows
     * @override for custom tables
     */
    protected List<List<String>> initObjectTable(){
        List<List<String>> res = new ArrayList<>();
        try {
            List<String> row;
            for(E r : this.rows) {           //read all rows
                row = new ArrayList<>();
                final Object o = r;
                for (Method m : o.getClass().getMethods()) {        //get properties of single row and add them to display row
                    if (m.getName().startsWith("get") && m.getParameterTypes().length == 0 && !m.getName().contains("Class")) {
                        final Object gr = m.invoke(o);
                        System.out.println(gr.toString());
                        row.add(gr.toString());
                    }
                }
                res.add(row);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ERROR Cannot read class getters");
        }
        return res;
    }

    /**
     * initializes the header row for the table, override for custom headers
     * @return String list for the table header
     * @override for custom header
     */
    protected List<String> initObjectTableHeaders(){
        return getGetterMethods(this.type);
    }


    /**
     * gets the names of the object parameters
     * @param c object of class to read
     * @return List<String> of parameter names
     */
    private List<String> getGetterMethods(Class<?> c){
        List<String> names = new ArrayList<>();
        Method[] methods = c.getMethods();
        for (Method method: methods){
            String name=method.getName();
            if(name.startsWith("get") && !name.contains("Class")){
                names.add(name.substring(3));
            }
        }
        return names;
    }

    private void makeHideButton(){
        ZButton btn = new ZButton("button");
        btn.setOnClickJS(this.hideFunction+"('"+this.id+"', $(this));");
        btn.addStyleClass("btn");
        btn.addStyleClass("btn-table");
        btn.setText("");
        btn.setGlyphicon(new ZGlyphicon("chevron-up"));
        btn.setOnClickJS(this.hideFunction + "('" + this.id + "', $(this));");
        this.hideBtn = btn;
    }


    public Class<E> getType() {
        return type;
    }

    public void setType(Class<E> type) {
        this.type = type;
    }

    public List<E> getRows() {
        return rows;
    }

    public void setRows(List<E> rows) {
        this.rows = rows;
        if(rows.size() > 0){
            initTable(rows.get(0).getClass());
        }
    }

    public List<String> getDisplayHeaders() {
        return displayHeaders;
    }

    public void setDisplayHeaders(List<String> displayHeaders) {
        this.displayHeaders = displayHeaders;
    }

    public List<List<String>> getDisplayRows() {
        return displayRows;
    }

    public void setDisplayRows(List<List<String>> displayRows) {
        this.displayRows = displayRows;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        if(this.hideable){
            makeHideButton();
        }
        this.header = header;
    }

    public boolean isHideable() {
        return hideable;
    }

    public void setHideable(boolean hideable) {
        if(hideable){
            makeHideButton();
        }else {
            this.hideBtn = null;
        }
        this.hideable = hideable;
    }

    public int[] getDisplayColumns() {
        return displayColumns;
    }

    public void setDisplayColumns(int[] displayColumns) {
        this.displayColumns = displayColumns;
    }

    public String getHideFunction() {
        return hideFunction;
    }

    public void setHideFunction(String hideFunction) {
        this.hideFunction = hideFunction;
    }

    public String getRowOnClick() {
        return rowOnClick;
    }

    public void setRowOnClick(String rowOnClick) {
        this.rowOnClick = rowOnClick;
    }
}
