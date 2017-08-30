package zwui.examples;

import zwui.widgets.ZGlyphicon;
import zwui.widgets.ZWidget;

public class ZWidgetExample {

    public void start(){
        //Create a new Widget
        ZWidget widget = new ZWidget();
        //set the html tag name for your widget
        widget.setTagName("p");
        //add a style class to your widget
        widget.addStyleClass("someCssClass");
        //add css directly to your widget
        widget.addCss("color: #ff0000");
        //set the content of your widget
        widget.setHtmlCode("this is a test paragraph!");

        //you can also add 1 child to a ZWidget (this does not work w/ extended classes)
        widget.setChild(new ZGlyphicon("plus"));

        //the widget.print() function gives you the html for your widget, you can use it to
        //create a html file or if you are using java ee web u can give the widget as an argument
        //to the .jsp file and print it there
        System.out.println(widget.print());
        new HtmlPrinter().print(widget.print());
    }
}
