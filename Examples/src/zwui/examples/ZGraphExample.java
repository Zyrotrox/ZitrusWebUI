package zwui.examples;

import zwui.widgets.ZGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ZGraphExample {
    public static final String in = "201709010001130.009246\n"+
            "201709010002130.009921\n"+
            "201709010003130.010271\n"+
            "201709010004130.009200\n"+
            "201709010005130.009036\n"+
            "201709010006130.009054\n"+
            "201709010007130.009164\n"+
            "201709010008130.009554\n"+
            "201709010009130.010643\n"+
            "201709010010130.009143\n"+
            "201709010011130.007100\n"+
            "201709010012130.006979\n"+
            "201709010013130.007107\n"+
            "201709010014130.008092\n"+
            "201709010015130.008364\n"+
            "201709010016130.009093\n"+
            "201709010017130.010507\n"+
            "201709010018130.009454\n"+
            "201709010019130.010707\n"+
            "201709010020130.010979\n"+
            "201709010021130.009031\n"+
            "201709010022130.007193\n"+
            "201709010023130.006829\n"+
            "201709010024130.007008\n"+
            "201709010025130.008014\n"+
            "201709010026130.007657\n"+
            "201709010027130.007500\n"+
            "201709010028130.008243\n"+
            "201709010029130.008193\n"+
            "201709010030130.008508\n"+
            "201709010031130.008893\n"+
            "201709010032130.007921";

    public void start(){
        String[] lines = in.split("\n");
        int x = 0;
        ZGraph graph = new ZGraph(0, 32, 0, 0.02);
        for(String line : lines){
            double y = Double.parseDouble(line.substring(14));
            graph.addPoint(x, y);
            if(x == 0 || x % 10 == 0){
                graph.addXLabel(line.substring(8,10) + ":" + line.substring(10,12));
            }
            x++;
        }
        graph.setyLabelInterval(0.005);
        graph.setxLabelInterval(10);
        graph.readyGraph();




        /*ZGraph graph = new ZGraph(-10, 10, -10, 10);
        graph.setxLabelInterval(2);
        graph.setyLabelInterval(2);
        graph.setWidth(700);
        graph.setHeight(700);
        graph.addCss("height: 780px");
        graph.addCss("width: 780px");

        graph.addPoint(-10, -10);
        graph.addPoint(-6, 6);
        graph.addPoint(-2, -8);
        graph.addPoint(4, 6);
        graph.addPoint(8, 2);


        graph.readyGraph();
        graph.setXLabel(2, "tst");

       /* Random r = new Random();
        double ran;
        for(int i = -10; i < 100; i++){
            ran = (double)r.nextInt(20);
            if(ran < 10){
                ran *= -1;
            }else {
                ran -= 10;
            }
            points.add(new Double[]{(double)i, ran});
        }*/
       /* points.add(1.0d);
        points.add(5.6d);
        points.add(50.0d);
        points.add(66.0d);
        points.add(40d);
        points.add(20d);
        points.add(99d);
        points.add(77d);

        graph.setHeight(100);
        graph.setWidth(200);
        */
        System.out.println(graph.print());
        new HtmlPrinter().print(graph.print());
    }


}
