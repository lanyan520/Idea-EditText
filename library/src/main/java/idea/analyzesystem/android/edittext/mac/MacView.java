package idea.analyzesystem.android.edittext.mac;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import idea.analyzesystem.android.edittext.AbsEditText;
import idea.analyzesystem.android.edittext.AbsEditTextGroup;

/**
 * Created by idea on 2016/7/15.
 */
public class MacView extends AbsEditTextGroup {
    public MacView(Context context) {
        this(context,null,0);
    }

    public MacView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MacView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getChildCount() {
        return 11;
    }

    @Override
    public AbsEditText getAbsEditText() {
        return new MacEditText(getContext());
    }

    @Override
    public String getSemicolomText() {
        return ":";
    }

    @Override
    public int getDelMaxLength() {
        return 2;
    }

    @Override
    public void applySemicolonTextViewTheme(TextView semicolonTextView) {
        semicolonTextView.setGravity(Gravity.CENTER);
        semicolonTextView.setPadding(dp4, dp4, dp4, dp4);
    }

    @Override
    public void applyEditTextTheme(AbsEditText absEditText) {

    }

    @Override
    public boolean checkInputValue() {
        return super.checkInputValue();
    }

    public void setMacText(String[] macValues){
        for(int i=0; i<editTexts.size(); i++){
            editTexts.get(i).setText(macValues[i]);
        }
    }

    public String getMacAddress(){
        String result = "";
        for(int i=0; i<editTexts.size()-1; i++){
            result+= editTexts.get(i).getText().toString().trim()+":";
        }
        result+= editTexts.get(5).getText().toString().trim();

        return result;
    }

    /**
     * setAllFocuse false 禁用编辑器
     * @param focuse
     */
    public void setAllFocuse(boolean focuse){
        for(int i=0; i<editTexts.size(); i++){
            editTexts.get(i).setFocusable(focuse);
            editTexts.get(i).setFocusableInTouchMode(focuse);
        }
    }

    public void setChildIndexText(int index,String text){
        editTexts.get(index).setText(text);
    }
}
