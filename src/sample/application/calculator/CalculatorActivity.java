package sample.application.calculator;

//import sample.application.memopad.R;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.text.Selection;

public class CalculatorActivity extends Activity {
	
	Integer count    = 0;
	Double d1        = 0.0;
	Double d2        = 0.0;
//	String taihi     = null;
	String enzan     = " ";
/*	ArrayList arrayList1 = new ArrayList();
	ArrayList arrayList2 = new ArrayList();
	ArrayList arrayList3 = new ArrayList(); */
	Double dbk[]     = new Double[100];
	String ebk[]     = new String[100];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_calculator, menu);
		MenuInflater mi = this.getMenuInflater();
		mi.inflate(R.menu.menu,menu);
        return true;
//		return super.onCreateOptionsMenu(menu);
    }
    
    public void numKeyOnClick(View v){
    	Button button = (Button)v;
     	Log.d("[buttonのtext]",button.getText().toString());
     	TextView tv = (TextView) this.findViewById(R.id.displayPanel);
     	String str = button.getText().toString();
     	String strtv = tv.getText().toString();
     	Log.d("[tvのtext]",tv.getText().toString());
     	if(strtv.equals("0")){
     		if(str.equals(".")){
         		tv.setText(strtv + str);
    		}else{
    			tv.setText(str);
    		}
     	}else{
     		tv.setText(strtv + str);
     	}
    }
    public void addKeyOnClick(View v){
     	TextView tv = (TextView) this.findViewById(R.id.displayPanel);
		d2 = Double.parseDouble(tv.getText().toString());
     	d1 = calculato(enzan,d1,d2);
    	enzan = "+";
    	tv.setText("0");
//    	arrayList1.add(d1);
//    	arrayList2.add(enzan);
    	dbk[count] = d2;
    	ebk[count] = enzan;
    	count++;
    }
    public void subKeyOnClick(View v){
     	TextView tv = (TextView) this.findViewById(R.id.displayPanel);
		d2 = Double.parseDouble(tv.getText().toString());
     	d1 = calculato(enzan,d1,d2);
    	enzan = "-";
    	tv.setText("0");
    	dbk[count] = d2;
    	ebk[count] = enzan;
    	count++;
    }
    public void mulKeyOnClick(View v){
     	TextView tv = (TextView) this.findViewById(R.id.displayPanel);
		d2 = Double.parseDouble(tv.getText().toString());
     	d1 = calculato(enzan,d1,d2);
    	enzan = "*";
    	tv.setText("0");
    	dbk[count] = d2;
    	ebk[count] = enzan;
    	count++;
    }
    public void divKeyOnClick(View v){
     	TextView tv = (TextView) this.findViewById(R.id.displayPanel);
		d2 = Double.parseDouble(tv.getText().toString());
     	d1 = calculato(enzan,d1,d2);
    	enzan = "/";
    	tv.setText("0");
    	dbk[count] = d2;
    	ebk[count] = enzan;
    	count++;
    }
    public void sumKeyOnClick(View v){
     	TextView tv = (TextView) this.findViewById(R.id.displayPanel);
		d2 = Double.parseDouble(tv.getText().toString());
     	d1 = calculato(enzan,d1,d2);
/*    	if(enzan.equals(" ")){
    		d1 = Double.parseDouble(tv.getText().toString());
    	}
    	if(enzan.equals("+")){
    		d2 = Double.parseDouble(tv.getText().toString());
    		d1 = d1 + d2;
    	}
    	if(enzan.equals("-")){
    		d2 = Double.parseDouble(tv.getText().toString());
    		d1 = d1 - d2;
    	}
    	if(enzan.equals("*")){
    		d2 = Double.parseDouble(tv.getText().toString());
    		d1 = d1 * d2;
    	}
    	if(enzan.equals("/")){
    		d2 = Double.parseDouble(tv.getText().toString());
    		d1 = d1 / d2;
    	}
*/
    	enzan = "=";
    	tv.setText(d1.toString());
    	dbk[count] = d2;
    	ebk[count] = enzan;
    	count++;
    	dbk[count] = d1;
    	ebk[count] = "";
    	count++;
//    	for(int i = 0; i < dbk.length; i++){
    	for(int i = 0; i < count; i++){
         	Log.d("[dbkのtext]",dbk[i].toString());
         	Log.d("[ebkのtext]",ebk[i]);
    	}
    }
    public double calculato(String enzan,Double d1,Double d2){
    	if(enzan.equals(" ")){
    		d1 = d2;
    	}
    	if(enzan.equals("+")){
    		d1 = d1 + d2;
    	}
    	if(enzan.equals("-")){
    		d1 = d1 - d2;
    	}
    	if(enzan.equals("*")){
    		d1 = d1 * d2;
    	}
    	if(enzan.equals("/")){
    		d1 = d1 / d2;
    	}
    	return d1;
    }
    public void memoOnClick(View v){
    	String str = "";
        setContentView(R.layout.memo);
     	EditText et = (EditText) this.findViewById(R.id.editText1);
    	for(int i = 0; i < count; i++){
    		str = str + dbk[i].toString() + "\n" + ebk[i].toString();
//    		str = str + dbk[i].toString() + "¥n" + ebk[i].toString();
    	}
    	et.setText(str);
    }

}
