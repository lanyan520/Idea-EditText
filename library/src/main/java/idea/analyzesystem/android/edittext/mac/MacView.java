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

import idea.analyzesystem.edittext.R;

/**
 * Created by idea on 2016/7/15.
 */
public class MacView extends LinearLayout implements TextWatcher {

    private float sp16 = 16.0f;
    private int dp4 = 4;
    private ArrayList<MacEditText> macEditTexts = new ArrayList<MacEditText>();

    public MacView(Context context) {
        this(context, null, 0);
    }

    public MacView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MacView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addViews();
        initMacView();
    }

    protected void addViews() {
        for (int i = 0; i < 11; i++) {
            if (i == 0 || i == 2 || i == 4 || i == 6 || i == 8 || i == 10) {
                MacEditText macEditText = createMacEditText();
                macEditTexts.add(macEditText);
                addView(macEditText);
            } else {
                addView(createSemicolonTextView());
            }
        }
    }

    protected MacEditText createMacEditText() {

        MacEditText macEditText = new MacEditText(getContext());
        LayoutParams params = new LayoutParams(0, LayoutParams.MATCH_PARENT);
        params.weight = 1;
        macEditText.setLayoutParams(params);
        macEditText.setTextSize(sp16);//sp
        macEditText.setTextColor(0xFF888888);
        macEditText.setGravity(Gravity.CENTER);
        macEditText.setPadding(dp4, dp4, dp4, dp4);
        macEditText.setSingleLine();
        macEditText.setFocusable(false);
        macEditText.setFocusableInTouchMode(true);
        macEditText.setBackgroundColor(0xFFFFFFFF);
        return macEditText;
    }

    protected TextView createSemicolonTextView() {

        TextView textView = new TextView(getContext());
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(params);
        textView.setTextSize(sp16);//sp
        textView.setTextColor(0xFF444444);
        textView.setText(":");
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(dp4, dp4, dp4, dp4);
        return textView;
    }

    @Deprecated
    public void remeasureChildWeight() {
        for (int i = 0; i < 11; i++) {
            if (i == 0 || i == 2 || i == 4 || i == 6 || i == 8 || i == 10) {
                LayoutParams params = new LayoutParams(0, LayoutParams.MATCH_PARENT);
                params.weight = 1;
                getChildAt(i).setLayoutParams(params);
            } else {
                LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
                getChildAt(i).setLayoutParams(params);
            }
        }
    }

    protected void initMacView() {
        for (int i = 0; i < macEditTexts.size(); i++) {
            macEditTexts.get(i).addTextChangedListener(this);
            if(i!=0){
                macEditTexts.get(i).setOnKeyListener(new OnMacKeyListener(macEditTexts.get(i-1),macEditTexts.get(i)));
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
        if (s.toString().length() == 2) {
            if (macEditTexts.get(0).isFocused()) {
                macEditTexts.get(0).clearFocus();
                macEditTexts.get(1).requestFocus();
            } else if (macEditTexts.get(1).isFocused()) {
                macEditTexts.get(1).clearFocus();
                macEditTexts.get(2).requestFocus();
            } else if (macEditTexts.get(2).isFocused()) {
                macEditTexts.get(2).clearFocus();
                macEditTexts.get(3).requestFocus();
            } else if (macEditTexts.get(3).isFocused()) {
                macEditTexts.get(3).clearFocus();
                macEditTexts.get(4).requestFocus();
            } else if (macEditTexts.get(4).isFocused()) {
                macEditTexts.get(4).clearFocus();
                macEditTexts.get(5).requestFocus();
            }
        }
    }

    public boolean checkInputValue(MacEditText... params) {

        boolean result = true;
        for (int i = 0; i < params.length - 1; i++) {
            if (!params[i].checkInputValue()) {
                result = false;
                break;
            }
        }

        return result;
    }

    public void setMacText(String macAddress) {
        String[] mac = macAddress.split(":");
        for (int i = 0; i < macEditTexts.size(); i++) {
            macEditTexts.get(i).setText(mac[i]);
        }
    }

    public String getMacValues() {
        String result = "";
        for (int i = 0; i < macEditTexts.size(); i++) {
            result += macEditTexts.get(i).getText().toString();
        }
        return result;
    }


    class OnMacKeyListener implements OnKeyListener{

        private MacEditText clearEditText;
        private MacEditText requestEditText;

        public OnMacKeyListener(MacEditText requestEditText,MacEditText clearEditText){
            this.requestEditText = requestEditText;
            this.clearEditText = clearEditText;
        }
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_DEL
                    && event.getAction() == KeyEvent.ACTION_DOWN&&clearEditText.getSelectionStart()==0) {
                clearEditText.clearFocus();
                requestEditText.requestFocus();
                return true;
            }
            return false;
        }
    }
}
