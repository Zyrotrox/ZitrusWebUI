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
    private double viewportTop;
    private double viewportBottom;
    private double viewportStart;
    private double viewportEnd;
    private double viewportWidth;
    private double viewportHeight;
    private double xFactor;
    private double yFactor;
    private int xOffset;
    private int yOffset;
    private List<Double[]> viewportPoints;
    private List<Integer[]> points;
    private List<String> xLabels;
    private List<String> yLabels;

    private String stroke;
    private String strokeWidth;
    private String fill;
    private double xLabelInterval;
    private double yLabelInterval;

    public ZGraph(){
        super();
        this.viewportPoints = new ArrayList<>();
        this.points = new ArrayList<>();
        width = 800;
        height = 600;
        xOffset = 50;
        yOffset = 50;
        xLabelInterval = 10;
        yLabelInterval = 50;
        xLabels = new ArrayList<>();
        yLabels = new ArrayList<>();
        stroke = "#ff0000";
        strokeWidth = "2";
        fill = "none";
        initContainer();
    }

    public ZGraph(double viewportStart, double viewportEnd, double viewportBottom, double viewportTop){
        this();
        this.viewportStart = viewportStart;
        this.viewportEnd = viewportEnd;
        this.viewportBottom = viewportBottom;
        this.viewportTop = viewportTop;
    }

    private void initContainer(){
        this.addCss("overflow: scroll");
        this.addCss("width: 880px");
        this.addCss("height: 640px");
    }

    public void setGraphData(List<Double[]> data){
        this.viewportPoints = data;
        calculatePoints();
    }

    public void setXLabel(int x, String lbl){
        this.xLabels.set(x, lbl);
    }

    public void addXLabel(String lbl){
        this.xLabels.add(lbl);
    }

    public void setyLabels(int y, String lbl){
        this.yLabels.set(y, lbl);
    }

    public void addPoint(double x, double y){
        this.viewportPoints.add(new Double[]{x,y});
    }

    public void readyGraph(){
        calculatePoints();
    }

    private void calculatePoints(){
        viewportWidth = viewportEnd - viewportStart;
        viewportHeight = viewportTop - viewportBottom;
        xFactor = width / viewportWidth;
        yFactor = height / viewportHeight;

        int x;
        int y;
        for(Double[] vp : viewportPoints){
            x = getXPoint(vp[0]);
            y = getYPoint(vp[1]);
            points.add(new Integer[]{x,y});
        }
        initLabels();
    }

    private void initLabels(){
        for(double i = viewportBottom; i <= viewportTop; i+=yLabelInterval){
            yLabels.add(Double.toString(i));
        }
        for(double i = viewportStart; i <= viewportEnd; i+=xLabelInterval){
            xLabels.add(Double.toString(i));
        }
    }

    private int getXPoint(double x){
        double f;
        if(viewportStart < 0){
            f = viewportStart * -1;
        }else{
            f = viewportStart;
        }
        return (int)Math.round((x + f) * xFactor) + xOffset;
    }

    private int getYPoint(double y){
        double f;
        if(viewportBottom < 0){
            f = viewportBottom * -1;
        }else{
            f = viewportBottom;
        }
        return (height - (int)Math.round((y + f) * yFactor)) - yOffset;
    }


    @Override
    public String print(){
        String res = "<div " + printTagOptions() + ">" +
                "<svg height=\""+(height+ yOffset)+"\" width=\""+(width + xOffset)+"\">" +
                printZeroLines() +
                printPlot() +
                printLabels() +
                "</svg>";
        return res;
    }

    private String printPlot(){
        String res = "<path stroke=\""+stroke+"\" stroke-width=\""+strokeWidth+"\" fill=\""+fill+"\"";
        StringBuilder builder = new StringBuilder();
        if(points.size() > 0){
            builder.append("d=\"M" + points.get(0)[0] + "," + points.get(0)[1] + " L");
            for(Integer[] p : points){
                builder.append(p[0] + "," + p[1] + " ");
            }
            builder.append("\" />");
        }
        return res + builder.toString();
    }

    private String printLabels(){
        String res = "<g>";
        StringBuilder builder = new StringBuilder();

        int y;
        int x;
        int l = 0;
        for(double i = viewportBottom; i <= viewportTop; i+= yLabelInterval){
            y = getYPoint(i);
            builder.append("<text x=\"0\" y=\""+y+"\">" + yLabels.get(l) + "</text>");
            builder.append("<rect x=\""+xOffset+"\" y=\""+y+"\" width=\"4\" height=\"2\" />");
            l++;
        }
        l = 0;
        for(double i = viewportStart; i <= viewportEnd; i+= xLabelInterval){
            x = getXPoint(i);
            builder.append("<text x=\""+x+"\" y=\""+(height - 10)+"\">" + xLabels.get(l) + "</text>");
            builder.append("<rect x=\""+x+"\" y=\""+(height -yOffset) +"\" width=\"2\" height=\"4\" />");
            l++;
        }
        return res + builder.toString() + "</g>";
    }

    private String printOutlinesLines(){
        return "<path stroke=\"#000000\" stroke-width=\"5\" fill=\"none\" " +
                "d=\"M"+xOffset+",0 " +
                "L"+xOffset+","+(height-yOffset)+" " +
                width+","+(height-yOffset)+"\" />";
    }

    private String printZeroLines(){
        String yLine = "<path stroke=\"#000000\" stroke-width=\"3\" fill=\"none\" " +
                "d=\"M"+getXPoint(0) + ","+getYPoint(viewportTop)+" " +
                "L"+getXPoint(0)+","+getYPoint(viewportBottom)+"\" />";
        String xLine = "<path stroke=\"#000000\" stroke-width=\"3\" fill=\"none\" " +
                "d=\"M"+getXPoint(viewportStart)+","+getYPoint(0)+ " " +
                "L"+getXPoint(viewportEnd)+","+getYPoint(0)+"\" />";

        return yLine + xLine;
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

    public double getViewportTop() {
        return viewportTop;
    }

    public void setViewportTop(double viewportTop) {
        this.viewportTop = viewportTop;
    }

    public double getViewportBottom() {
        return viewportBottom;
    }

    public void setViewportBottom(double viewportBottom) {
        this.viewportBottom = viewportBottom;
    }

    public double getViewportStart() {
        return viewportStart;
    }

    public void setViewportStart(double viewportStart) {
        this.viewportStart = viewportStart;
    }

    public double getViewportEnd() {
        return viewportEnd;
    }

    public void setViewportEnd(double viewportEnd) {
        this.viewportEnd = viewportEnd;
    }

    public double getxLabelInterval() {
        return xLabelInterval;
    }

    public void setxLabelInterval(double xLabelInterval) {
        this.xLabelInterval = xLabelInterval;
    }

    public double getyLabelInterval() {
        return yLabelInterval;
    }

    public void setyLabelInterval(double yLabelInterval) {
        this.yLabelInterval = yLabelInterval;
    }

    public double getViewportWidth() {
        return viewportWidth;
    }

    public void setViewportWidth(double viewportWidth) {
        this.viewportWidth = viewportWidth;
    }

    public double getViewportHeight() {
        return viewportHeight;
    }

    public void setViewportHeight(double viewportHeight) {
        this.viewportHeight = viewportHeight;
    }

    public int getxOffset() {
        return xOffset;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public void setyOffset(int yOffset) {
        this.yOffset = yOffset;
    }

    public String getStroke() {
        return stroke;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    public String getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(String strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    /*private int width;
    private int height;
    private double viewHeight;
    private double viewWidth;
    private int scale;
    private int type;
    private int yLabelIncrement;
    private int xLabelIncrement;
    private int xOffset;
    private List<Double[]> viewPoints;
    private List<Double[]> points;

    public ZGraph(){
        super();
        this.scale = 10;
        this.xLabelIncrement = 10;
        this.yLabelIncrement = 10;
        this.xOffset = 20;
        this.type = LINE;
        this.viewPoints = new ArrayList<>();
    }

    public void setYData(List<Double> points){
        double highest = 0.0d;
        for(int i = 0; i < points.size(); i++){
            viewPoints.add(new Double[]{((double)i * scale), points.get(i)});
            if(points.get(i) > highest)
                highest = points.get(i);
        }
        width = (int)Math.round(viewPoints.get(viewPoints.size()-1)[0]);
        height = (int)highest + 10;
    }

    private void calculatePoints(){
        double x;
        double y;
        for(Double[] point : viewPoints){
            x = point[0];
            y = point[1];
            x += xOffset;
            y = height - y;
            points.add(new Double[]{x, y});
        }
    }

    @Override
    public String print(){
        String res = "<div " + this.printTagOptions() + ">";
        res += "<svg width=\"" + this.width + "\" height=\""+this.height+"\">" +
                "<path stroke=\"#000000\" stroke-width=\"5\" fill=\"none\" d=\"" +
                "M"+xOffset+",0 L"+xOffset+"," +this.height + " L" + width + "," + height + "\" />" +
                "<path stroke=\"#ff0000\" stroke-width=\"3\" fill=\"none\" d=\"";


        calculatePoints();
        if(points.size() > 0){
            StringBuilder builder = new StringBuilder();
            //double y = height - viewPoints.get(0)[1];
            double y = points.get(0)[1];
            double x = points.get(0)[0];
            builder.append("M" + x + "," + y + " L");
            for(int i = 0; i < points.size(); i++){
                y = points.get(i)[1];
                x = points.get(i)[0];
                builder.append(x + "," + y + " ");
            }
            res += builder.toString();
        }

        res += "\" /></svg></div>";
        return res;
    }

    private String getLabels(){
        String res = "<g font-size=\"20\" fill=\"black\" stroke=\"none\" text-anchor=\"middle\">";

        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < height; i += yLabelIncrement){
            builder.append("<text x=\"" + 0 + "\" y=\""+i+"\">" + i + "</text>");
        }

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
    }*/
}
