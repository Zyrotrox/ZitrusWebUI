package zwui.widgets;

import zwui.widgets.types.ZLabelType;

import java.util.ArrayList;
import java.util.List;

public class ZList<E> extends ZWidget {
    private List<E> rows;

    private List<String> displayNames;
    private List<String> values;

    private String title;
    private String rowNames;
    private String rowOnClickJS;
    private boolean searchable;
    private String searchPlaceholder;


    public ZList(){
        super();
        this.addStyleClass("list-group");
        this.rows = new ArrayList<>();
        this.displayNames = new ArrayList<>();
        this.values = new ArrayList<>();
        this.rowNames = "inp_ListRow";
    }

    public ZList(List<E> rows){
        this();
        this.rows = rows;
        if(rows != null && rows.size() > 0){
            initList();
        }
    }

    @Override
    public String print() {
        String res = "";
        if(this.title != null){
            ZLabel lbl_title = new ZLabel(ZLabelType.H4);
            lbl_title.setText(title);
            res += lbl_title.print();
        }
        if(isSearchable()){
            ZInput inp_search = new ZInput();
            inp_search.setPlaceholder(searchPlaceholder);
            inp_search.setOnInputJS("searchZList($('#"+this.getId()+"'), $(this));");
            res += inp_search.print();
        }
        res += "<div " + printTagOptions() + " >";
        ZLabel lbl_row;
        ZInput inp_checked;
        for(int i = 0; i < this.displayNames.size(); i++){
            lbl_row = new ZLabel(ZLabelType.LBL);
            inp_checked = new ZInput(values.get(i));
            inp_checked.setType("checkbox");
            inp_checked.setName(rowNames);
            inp_checked.addCss("visibility: hidden");
            //inp_checked.addCss("display: none");
            lbl_row.addStyleClass("list-group-item");
            lbl_row.addCustomTag(new String[]{"for", inp_checked.getId()});
            lbl_row.setChild(inp_checked);
            lbl_row.setText(displayNames.get(i));
            if(rowOnClickJS != null){
                lbl_row.setOnClickJS(rowOnClickJS);
            }
            res += lbl_row.print();
        }
        res += "</div>";
        return res;
    }

    private void initList(){
        this.displayNames = initDisplayNames(rows);
        this.values = initValues(rows);
    }

    protected List<String> initDisplayNames(List<E> rows){
        List<String> res = new ArrayList<>();
        for(E r : rows){
            res.add(r.toString());
        }
        return res;
    }

    protected List<String> initValues(List<E> rows){
        List<String> res = new ArrayList<>();
        for(int i = 0; i < rows.size(); i++){
            res.add(Integer.toString(i));
        }
        return res;
    }

    public List<E> getRows() {
        return rows;
    }

    public void setRows(List<E> rows) {
        this.rows = rows;
        if(rows != null && rows.size() > 0){
            initList();
        }
    }

    public String getRowNames() {
        return rowNames;
    }

    public void setRowNames(String rowNames) {
        this.rowNames = rowNames;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRowOnClickJS() {
        return rowOnClickJS;
    }

    public void setRowOnClickJS(String rowOnClickJS) {
        this.rowOnClickJS = rowOnClickJS;
    }

    public boolean isSearchable() {
        return searchable;
    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
        this.searchPlaceholder = "search list ...";
    }

    public void setSearchable(boolean searchable, String placeholder) {
        this.searchable = searchable;
        this.searchPlaceholder = placeholder;
    }
}
