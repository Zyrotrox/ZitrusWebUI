package zwui.examples;

import zwui.widgets.ZButton;
import zwui.widgets.ZGlyphicon;

public class ZButtonExample {

    public void start(){
        //initialize a new ZButton, the type for the button
        //is either "button" or "submit" for forms
        ZButton btn = new ZButton("button");
        //set the text for the button
        btn.setText("Click Me!");
        //set a javascript function for the onclick of the button
        //you will have to declare the function somewhere
        btn.setOnClickJS("SomeJsFunction('some parameter');");
        //you can set a bootstrap glyphicon to be shown before the button text
        //simply give the constructor the name of the icon, the ZGlyphicon will
        //complete the classname glyphicon glyphicon-NAME automatically
        btn.setGlyphicon(new ZGlyphicon("floppy-disk"));
        //set the value of the button
        btn.setValue("value");
        //set the button tooltip (bootstrap)
        btn.setTooltip("pls click me!");

        //print the html!
        //tst
        System.out.println(btn.print());

        new HtmlPrinter().print(btn.print());
    }
}
