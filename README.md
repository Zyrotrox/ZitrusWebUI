# ZitrusWebUI
With the help of this simple java api, you can easily build _HTML_ webpages in no time.

# How to get it?
Its pretty simple, just fork the project, clone it to your file system and execute it using any java compiler.
You can also find an already compiled .jar file in out/artifacts/ZitrusWebUI_jar/.

# How to use?
Once you have the compiled .jar file you can import it as a library for your own project!
The generated HTML code heavily relies on Bootstrap, you can get it here http://getbootstrap.com/
If you want to use JS functions you will also need JQuery, you can get it here https://jquery.com/

**ZLabel**
```java
ZLabel lbl = new ZLabel(ZLabelType.LBL);
lbl.setText("Hello");

String html = lbl.print();
```

**ZButton**
```java
ZButton btn = new ZButton("button");
btn.setGlyphicon("floppy-disk");
btn.setText("save");
btn.addStyleClasses(new String[]{"btn", "btn-success"});
btn.setOnClickJS = "someJSFunctionName";

String html = btn.print();
```

**ZTable**
```java
List<YourClass> someList = getYourList();
ZTable<YourClass> tbl = new ZTable<YourClass>(someList);
//The table reads the class and writes all Properties w/ a getter in the table,
//you can sort it if you want
tbl.orderColumns(new String[]{"Property 1", "Property 3", "Property 2"});
//A js function to execute when a table row is pressed, 
//the function needs to take a jquery argument for the html row
tbl.setRowOnClick("someJSFunction");

String html = tbl.print();
```

**ZGrid**
```java
ZGrid grd = new ZGrid();
//Add a child row 0, column 0, column span 4 (bootstrap grid, 12 columns)
grd.addChild(lbl, 0, 0, 6);
grd.addChild(btn, 0, 1, 6);
//You can also just append a child, it gets either added in the last existing row if it fits
//or at the beginning of a new row if its too large)
grd.addChild(tbl, 7);
grd.addChild(btn, 6);
//the button will be added below the table since the column span sum is above 12

String html = grd.print();
```
