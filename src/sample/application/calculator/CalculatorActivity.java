/*メモから戻るときのcount再セット
 * 横にしたときの処理
 * テスト
 * チェックにて、＝の後に演算子がないかチェック
 */



package sample.application.calculator;

//import sample.application.memopad.R;
import java.util.ArrayList;
import java.util.Iterator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.text.Selection;

public class CalculatorActivity extends Activity {
	
	Integer count    = 0;
	Double  d1       = 0.0;
	Double  d2       = 0.0;
	String  enzan    = " ";
/*	ArrayList arrayList1 = new ArrayList();
	ArrayList arrayList2 = new ArrayList();
	ArrayList arrayList3 = new ArrayList(); */
	Double  dbk[]    = new Double[100];
	String  ebk[]    = new String[100];
	boolean iflg     = true;
	boolean rflg     = true;

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
//		return true;
		return super.onCreateOptionsMenu(menu);
    }
    
    public void numKeyOnClick(View v){
    	Button   button = (Button)v;
    	TextView tv     = (TextView) this.findViewById(R.id.displayPanel);
    	String   str    = button.getText().toString();
    	String   strtv  = tv.getText().toString();
//  	if("0".equals(strtv)){
    	if(".".equals(str)){
	     	if(iflg == true){
	     		tv.setText("0" + str);
	     	}else{
	     		if(strtv.indexOf(".") == -1){
	         		tv.setText(strtv + str);
    	     	}
    		}
    	}else{
	     	if(iflg == true){
	     		tv.setText(str);
	     	}else{
	     		tv.setText(strtv + str);
	     	}
    	}
    	iflg = false;
    }
    public void addKeyOnClick(View v){
     	TextView tv = (TextView) this.findViewById(R.id.displayPanel);
		d2 = Double.parseDouble(tv.getText().toString());
     	d1 = calculato(enzan,d1,d2);
    	enzan = "+";
//    	arrayList1.add(d1);
//    	arrayList2.add(enzan);
    	if(rflg == true){
        	dbk[count] = d2;
        	ebk[count] = enzan;
        	count++;
    	}else{
        	ebk[count - 1] = enzan;
        	rflg = true;
    	}
    	tv.setText(d1.toString());
    	iflg  = true;
    }
    public void subKeyOnClick(View v){
     	TextView tv = (TextView) this.findViewById(R.id.displayPanel);
		d2 = Double.parseDouble(tv.getText().toString());
     	d1 = calculato(enzan,d1,d2);
    	enzan = "-";
    	if(rflg == true){
        	dbk[count] = d2;
        	ebk[count] = enzan;
        	count++;
    	}else{
        	ebk[count - 1] = enzan;
        	rflg = true;
    	}
    	tv.setText(d1.toString());
    	iflg  = true;
    }
    public void mulKeyOnClick(View v){
     	TextView tv = (TextView) this.findViewById(R.id.displayPanel);
		d2 = Double.parseDouble(tv.getText().toString());
     	d1 = calculato(enzan,d1,d2);
    	enzan = "*";
    	if(rflg == true){
        	dbk[count] = d2;
        	ebk[count] = enzan;
        	count++;
    	}else{
        	ebk[count - 1] = enzan;
        	rflg = true;
    	}
    	tv.setText(d1.toString());
    	iflg  = true;
    }
    public void divKeyOnClick(View v){
     	TextView tv = (TextView) this.findViewById(R.id.displayPanel);
		d2 = Double.parseDouble(tv.getText().toString());
     	d1 = calculato(enzan,d1,d2);
    	enzan = "/";
    	if(rflg == true){
        	dbk[count] = d2;
        	ebk[count] = enzan;
        	count++;
    	}else{
        	ebk[count - 1] = enzan;
        	rflg = true;
    	}
    	tv.setText(d1.toString());
    	iflg  = true;
    }
    public void sumKeyOnClick(View v){
    	if(iflg == false){
	     	TextView tv = (TextView) this.findViewById(R.id.displayPanel);
			d2 = Double.parseDouble(tv.getText().toString());
	     	d1 = calculato(enzan,d1,d2);
	    	enzan = "=";
	    	tv.setText(d1.toString());
	    	dbk[count] = d2;
//	    	ebk[count] = enzan;
//	    	count++;
//	    	dbk[count] = d1;
	    	ebk[count] = "";
	    	count++;
    	}else{
	    	enzan = "=";
	    	dbk[count] = d2;
	    	ebk[count] = enzan;
    	}
/*    	for(int i = 0; i < count; i++){
         	Log.d("[dbkのtext]",dbk[i].toString());
         	Log.d("[ebkのtext]",ebk[i]);
    	}*/
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
    		if(d2 == 0.0){
    			d1 = 0.0;
    		}else{
        		d1 = d1 / d2;
    		}
    	}
    	return d1;
    }
    public void memoOnClick(View v){
    	String str = "";
    	Double d   = 0.0;
        setContentView(R.layout.memo);
     	EditText et = (EditText) this.findViewById(R.id.editText1);
    	for(int i = 0; i < count; i++){
    		str = str + dbk[i].toString() + "\n" + ebk[i].toString();
    	}
    	for(int ii = 0; ii < count; ii++){
    		if(ii == 0){
    			if("+".equals(ebk[ii])){
    				d = dbk[ii] + dbk[ii + 1];
    			}
    			if("-".equals(ebk[ii])){
    				d = dbk[ii] - dbk[ii + 1];
    			}
    			if("*".equals(ebk[ii])){
    				d = dbk[ii] * dbk[ii + 1];
    			}
    			if("/".equals(ebk[ii])){
    				d = dbk[ii] / dbk[ii + 1];
    			}
    		}else{
    			if("+".equals(ebk[ii])){
    				d = d + dbk[ii + 1];
    			}
    			if("-".equals(ebk[ii])){
    				d = d - dbk[ii + 1];
    			}
    			if("*".equals(ebk[ii])){
    				d = d * dbk[ii + 1];
    			}
    			if("/".equals(ebk[ii])){
    				d = d / dbk[ii + 1];
    			}
    		}
    	}
    	str = str + "=" + d.toString();
//    	SharedPreferences pref = this.getSharedPreferences("MemoPrefs", MODE_PRIVATE);
    	et.setText(str);
//    	et.setSelection(pref.getInt("cursor" ,0));
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
//     	EditText et = (EditText) this.findViewById(R.id.editText1);
		switch(item.getItemId()){
		case R.id.menu_return:
			reCalc();
			break;
		case R.id.menu_cancel:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
    public void reCalc(){
     	EditText et    = (EditText) this.findViewById(R.id.editText1);
		String   memo  = et.getText().toString();
		boolean  memoc = this.memoCheck(memo);
		if(memoc == true){
			Integer  i     = 0;
			Integer  j     = 1;
	    	Integer  e     = 0;
	    	Double   d     = 0.0;
	    	String   nbk[] = new String[100];
	    	String   kbk[] = new String[100];
	    	while(!"=".equals(memo.substring(i,j))) {
				if(("+".equals(memo.substring(i,j)))
				 ||("-".equals(memo.substring(i,j)))
				 ||("*".equals(memo.substring(i,j)))
				 ||("/".equals(memo.substring(i,j)))){
					kbk[e] = memo.substring(i,j);
					e++;
				}else{
		         	Log.d("[ccccc]",memo.substring(i,j));
					if(!"\n".equals(memo.substring(i,j))){
						if(nbk[e] == null){
							nbk[e] = memo.substring(i,j);
						}else{
							nbk[e] = nbk[e] + memo.substring(i,j);
						}
					}
				}
				i++;
				j++;
			}
	    	for(int ii = 0; ii < e; ii++){
	    		if(ii == 0){
	    			if("+".equals(kbk[ii])){
	    				d = Double.parseDouble(nbk[ii]) + Double.parseDouble(nbk[ii + 1]);
	    			}
	    			if("-".equals(kbk[ii])){
	    				d = Double.parseDouble(nbk[ii]) - Double.parseDouble(nbk[ii + 1]);
	    			}
	    			if("*".equals(kbk[ii])){
	    				d = Double.parseDouble(nbk[ii]) * Double.parseDouble(nbk[ii + 1]);
	    			}
	    			if("/".equals(kbk[ii])){
	    				d = Double.parseDouble(nbk[ii]) / Double.parseDouble(nbk[ii + 1]);
	    			}
	    		}else{
	    			if("+".equals(kbk[ii])){
	    				d = d + Double.parseDouble(nbk[ii + 1]);
	    			}
	    			if("-".equals(kbk[ii])){
	    				d = d - Double.parseDouble(nbk[ii + 1]);
	    			}
	    			if("*".equals(kbk[ii])){
	    				d = d * Double.parseDouble(nbk[ii + 1]);
	    			}
	    			if("/".equals(kbk[ii])){
	    				d = d / Double.parseDouble(nbk[ii + 1]);
	    			}
	    		}
	    	}
	    	kbk[e] = "";
	    	count = ++e;
	    	for (int ii = 0; ii < dbk.length; ii++) {
	    		dbk[ii] = null;
	    	}
	    	for (int ii = 0; ii < nbk.length; ii++) {
	    		if(nbk[ii] != null){
	    			dbk[ii] = Double.parseDouble(nbk[ii]);
	    		}else{
	    			break;
	    		}
    	    }
	    	ebk  = kbk;
	    	rflg = false;
	        setContentView(R.layout.activity_calculator);
	     	TextView tv = (TextView) this.findViewById(R.id.displayPanel);
	    	tv.setText(d.toString());
		}
    }
    
    public boolean memoCheck(String memo){
		Integer  i     = 0;
		Integer  j     = 1;
		Integer  count = 0;
		boolean  memoc = true;
		boolean  eqKey = false;
//"=" が二個あってはいけない。
//"=" の後に演算子があってはいけない。
     	Log.d("[check]","1" + memoc);
//    	while(!"".equals(memo.substring(i,j))) {
// StringIndexOutOfBoundsException の例外処理を使用してみては？
     	try{
	     	while(memo.substring(i,j) != null) {
	         	Log.d("[check]","1-1" + memoc);
	    		if("=".equals(memo.substring(i,j))){
	    			count = count + 1;
	    			eqKey = true;
	    		}
	    		if(("+".equals(memo.substring(i,j)))
	    		 ||("-".equals(memo.substring(i,j)))
	    		 ||("*".equals(memo.substring(i,j)))
	    		 ||("/".equals(memo.substring(i,j)))){
		    		if(eqKey == true){
						memoc = false;
						break;
		    		}
	    		}
	    		if(count >= 2){
					memoc = false;
					break;
	    		}
				i++;
				j++;
	    	}
     	}catch(StringIndexOutOfBoundsException e){
     		
     	}
//"="は一個なけらばならない
       	if(count == 0){
       		memoc = false;
       	}
//入力に不正があってはいけない。
     	Log.d("[check]","2" + memoc);
    	if(memoc == true){
    		i     = 0;
    		j     = 1;
	    	while(!"=".equals(memo.substring(i,j))) {
				if(("+".equals(memo.substring(i,j)))
				 ||("-".equals(memo.substring(i,j)))
				 ||("*".equals(memo.substring(i,j)))
				 ||("/".equals(memo.substring(i,j)))
				 ||("0".equals(memo.substring(i,j)))
				 ||("1".equals(memo.substring(i,j)))
				 ||("2".equals(memo.substring(i,j)))
				 ||("3".equals(memo.substring(i,j)))
				 ||("4".equals(memo.substring(i,j)))
				 ||("5".equals(memo.substring(i,j)))
				 ||("6".equals(memo.substring(i,j)))
				 ||("7".equals(memo.substring(i,j)))
				 ||("8".equals(memo.substring(i,j)))
				 ||("9".equals(memo.substring(i,j)))
				 ||(".".equals(memo.substring(i,j)))
				 ||("\n".equals(memo.substring(i,j)))){
					i++;
					j++;
				}else{
					memoc = false;
					break;
				}
			}
    	}
//演算子もしくわ改行、"."が二個続いてはいけない。
     	Log.d("[check]","3" + memoc);
    	if(memoc == true){
    		i     = 0;
    		j     = 1;
        	count = 0;
	    	while(!"=".equals(memo.substring(i,j))) {
				if(("+".equals(memo.substring(i,j)))
				 ||("-".equals(memo.substring(i,j)))
				 ||("*".equals(memo.substring(i,j)))
				 ||("/".equals(memo.substring(i,j)))
				 ||(".".equals(memo.substring(i,j)))
				 ||("\n".equals(memo.substring(i,j)))){
					count = count + 1;
				}else{
					count = 0;
				}
	    		if(count >= 3){
					memoc = false;
					break;
	    		}
				i++;
				j++;
			}
    	}
//一個の数値に "." が二個あってはいけない。
     	Log.d("[check]","4" + memoc);
    	if(memoc == true){
    		i     = 0;
    		j     = 1;
        	count = 0;
	    	while(!"=".equals(memo.substring(i,j))) {
				if(("+".equals(memo.substring(i,j)))
				 ||("-".equals(memo.substring(i,j)))
				 ||("*".equals(memo.substring(i,j)))
				 ||("/".equals(memo.substring(i,j)))){
					count = 0;
				}else{
					if(".".equals(memo.substring(i,j))){
						count = count + 1;
					}
				}
	    		if(count >= 2){
					memoc = false;
					break;
	    		}
				i++;
				j++;
			}
    	}
     	Log.d("[check]","5" + memoc);
    	return memoc;
    }
    public void functionKeyOnClick(View v){
    	TextView tv     = (TextView) this.findViewById(R.id.displayPanel);
    	String   strtv  = tv.getText().toString();
		tv.setText("0");
    	switch(v.getId()){
    	case R.id.keypadAC:
    		count    = 0;
    		d1       = 0.0;
    		d2       = 0.0;
    		enzan    = " ";
    		iflg     = true;
    		rflg     = true;
	    	for (int ix = 0; ix < dbk.length; ix++) {
	    		dbk[ix] = null;
	    	}
	    	for (int ix = 0; ix < ebk.length; ix++) {
	    		ebk[ix] = null;
	    	}
    		tv.setText("0");
    		break;
    	case R.id.keypadC:
    		tv.setText("0");
    		break;
    	case R.id.keypadBS:
    		if(strtv.length() <= 1){
    			strtv = "0";
    		}else{
    			strtv = strtv.substring(0,strtv.length() - 1);
    		}
    		tv.setText(strtv);
    		break;
    	}
    }
/*    public void writePrefrences(){
    	String str = null;
    	SharedPreferences prefs = getSharedPreferences("CalcPrefs", MODE_PRIVATE);
    	SharedPreferences.Editor editor =  prefs.edit();
    	str = count.toString();
    	editor.putString("count", str);
    	str = d1.toString();
    	editor.putString("count", str);
    	str = d2.toString();
    	editor.putString("count", str);
    	editor.putString("count", this.enzan);
    	editor.put
    }
*/
}
