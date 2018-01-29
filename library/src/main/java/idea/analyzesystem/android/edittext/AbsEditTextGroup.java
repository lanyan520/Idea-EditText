package idea.analyzesystem.android.edittext;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by idea on 2016/7/18.
 */
public abstract class AbsEditTextGroup extends LinearLayout implements TextWatcher {

    protected float sp16 = 16.0f;
    protected int dp4 = 4;
    protected ArrayList<AbsEditText> editTexts = new ArrayList<AbsEditText>();

    public AbsEditTextGroup(Context context) {
        this(context,null,0);
    }

    public AbsEditTextGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AbsEditTextGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addViews();
        buildListener();
    }


    protected void addViews() {
        for (int i = 0; i < getChildCount(); i++) {
            if (i%2==0) {
                AbsEditText absEditText= createAbsEditText();
                editTexts.add(absEditText);
                addView(absEditText);
            } else {
                addView(createSemicolonTextView());
            }
        }
    }

    protected AbsEditText createAbsEditText() {

        AbsEditText absEditText = getAbsEditText();
        LayoutParams params = new LayoutParams(0, LayoutParams.MATCH_PARENT);
        params.weight = 1;
        absEditText.setLayoutParams(params);
        absEditText.setTextSize(sp16);//sp
        absEditText.setTextColor(0xFF888888);
        absEditText.setGravity(Gravity.CENTER);
        absEditText.setPadding(dp4, dp4, dp4, dp4);
        absEditText.setSingleLine();
        absEditText.setFocusableInTouchMode(true);
        absEditText.setBackgroundDrawable(new ColorDrawable(0xFFFFFFFF));
        applyEditTextTheme(absEditText);
        return absEditText;
    }

    protected TextView createSemicolonTextView() {
        TextView textView = new TextView(getContext());
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(params);
        textView.setTextSize(sp16);//sp
        textView.setTextColor(0xFF444444);
        textView.setText(getSemicolomText());
        applySemicolonTextViewTheme(textView);
        return textView;
    }

    protected void buildListener() {
        for (int i = 0; i < editTexts.size(); i++) {
            editTexts.get(i).addTextChangedListener(this);
            if(i!=0){
                editTexts.get(i).setOnKeyListener(new OnDelKeyListener(editTexts.get(i-1), editTexts.get(i)));
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().length() == getDelMaxLength()) {
            for (int i=0; i< editTexts.size()-1; i++){
                if(editTexts.get(i).hasFocus()){ //hasFocus √ & isFocus ×
                    editTexts.get(i).clearFocus();
                    editTexts.get(i+1).requestFocus();
                    break;
                }
            }
        }
    }

    public boolean checkInputValue() {
        boolean result = true;
        for (int i = 0; i < editTexts.size(); i++) {
            if (!editTexts.get(i).checkInputValue()) {
                result = false;
                break;
            }
        }

        return result;
    }

    /**
     * IP网关子网掩码转byte4个字节
     * @return
     */
    public byte[] getBytesWithIP() {
        byte[] result = new byte[4];
        for (int i = 0; i < editTexts.size(); i++) {
            int number = Integer.valueOf(editTexts.get(i).getText().toString());
            result[i] = (byte)number;
        }
        return result;
    }

    /**
     * Mac地址转换6字节byte
     * @return
     */
    public byte[] getBytesWithMac() {
        byte[] result = new byte[6];
        for (int i = 0; i < editTexts.size(); i++) {
            String mac = editTexts.get(i).getText().toString();
            byte byteMac =Integer.valueOf(mac, 16).byteValue();
            result[i] = byteMac;
        }
        return result;
    }



    /**
     * 重置自布局的输入控件
     */
    public void resetText(){
        for(AbsEditText editText:editTexts){
            editText.setText("");
        }
    }

    class OnDelKeyListener implements OnKeyListener {

        private AbsEditText clearEditText;
        private AbsEditText requestEditText;

        public OnDelKeyListener(AbsEditText requestEditText, AbsEditText clearEditText){
            this.requestEditText = requestEditText;
            this.clearEditText = clearEditText;
        }
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_DEL
                    && event.getAction() == KeyEvent.ACTION_DOWN&&clearEditText.getSelectionStart()==0) {
                clearEditText.clearFocus();
                requestEditText.requestFocus();
                requestEditText.setSelection(requestEditText.getText().toString().trim().length());
                return true;
            }
            return false;
        }
    }

    public ArrayList<AbsEditText> getChildEditTextViews(){
        return editTexts;
    }
    public abstract int getChildCount();

    public abstract AbsEditText getAbsEditText();

    public abstract String getSemicolomText();

    public abstract int getDelMaxLength();

    public abstract void applySemicolonTextViewTheme(TextView semicolonTextView);

    public abstract void applyEditTextTheme(AbsEditText absEditText);



}