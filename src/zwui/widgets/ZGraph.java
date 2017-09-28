package zwui.widgets;

import zwui.widgets.ZContainer;
import zwui.widgets.ZWidget;

import java.util.ArrayList;
import java.util.List;

public class ZGraph extends ZWidget {
    public static final int LINE = 0;
    public static final int AREA = 1;

    private int width;
    private int height;
    private int scale;
    private int type;
    private List<Double> xData;
    private List<Double> yData;
    private List<Double[]> data;

    public ZGraph(){
        super();
        this.scale = 10;
        this.xData = new ArrayList<>();
        this.yData = new ArrayList<>();
        this.type = LINE;
        this.data = new ArrayList<>();
    }

    public void setyData(List<Double> points){
        double highest = 0.0d;
        for(int i = 0; i < points.size(); i++){
            //xData.add((double)i * scale);
            data.add(new Double[]{((double)i * scale), points.get(i)});
            if(points.get(i) > highest)
                highest = points.get(i);
        }
        width = (int)Math.round(xData.get(xData.size()-1));
        height = (int)highest + 10;
        //yData = points;
    }

    @Override
    public String print(){
        String res = "<div " + this.printTagOptions() + ">";
        res += "<svg width=\"" + this.width + "\" height=\""+this.height+"\">" +
                "<path d=\"M0,0 L";
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < data.size(); i++){
            builder.append(data.get(i)[0] + "," + data.get(i)[1] + " ");
        }
        res += builder.toString();
        res += "\" /></svg></div>";
        return res;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
