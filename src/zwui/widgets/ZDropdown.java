package zwui.widgets;

import java.util.ArrayList;
import java.util.List;

public class ZDropdown<E> extends ZWidget {
    private List<E> options;
    private E type;

    private List<String> displayNames;
    private List<String> vales;

    private String active;

    public ZDropdown(){
        super();
        this.displayNames = new ArrayList<>();
        this.vales = new ArrayList<>();
        this.options = new ArrayList<>();

        initDropdown();
    }

    private void initDropdown(){
        this.displayNames = initDisplayNames(this.options);
        this.vales = initValues(this.options);
    }

    @Override
    public String print(){
        String res = "<select " + printTagOptions() + " >";
        if(this.active != null){
            String selected;
            for(int i = 0; i < displayNames.size(); i++){
                if(this.active.equals(vales.get(i))){
                    selected = "selected=\"selected\"";
                }else {
                    selected = "";
                }
                res += "<option value=\""+this.vales.get(i)+"\" "+selected+">" +
                        this.displayNames.get(i) + "</option>";
            }
        }else {
            for(int i = 0; i < displayNames.size(); i++){
                res += "<option value=\""+this.vales.get(i)+"\">" +
                        this.displayNames.get(i) + "</option>";
            }
        }
        res += "</select>";
        return res;
    }

    /**
     * initializes the display values for the dropdown
     * @param rows
     * @override for custom dropdown
     * @return
     */
    protected List<String> initDisplayNames(List<E> rows){
        List<String> res = new ArrayList<>();
        for(E r : rows){
            res.add(r.toString());
        }
        return res;
    }

    /**
     * initializes the values for the dropdown
     * @param rows
     * @override for custom values
     * @return
     */
    protected List<String> initValues(List<E> rows){
        List<String> res = new ArrayList<>();
        for(int i = 0; i < rows.size(); i++){
            res.add(Integer.toString(i));
        }
        return res;
    }

    public List<E> getOptions() {
        return options;
    }

    public void setOptions(List<E> options) {
        this.options = options;
        initDropdown();
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
