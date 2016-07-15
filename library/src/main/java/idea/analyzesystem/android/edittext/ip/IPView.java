package idea.analyzesystem.android.edittext.ip;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import idea.analyzesystem.android.edittext.mac.MacEditText;

/**
 * Created by idea on 2016/7/15.
 */
public class IPView extends LinearLayout implements TextWatcher {

    private float sp16 = 16.0f;
    private int dp4 = 4;
    private ArrayList<IPEditText> ipEditTexts = new ArrayList<IPEditText>();

    public IPView(Context context) {
        this(context, null, 0);
    }

    public IPView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IPView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addViews();
        initIPView();
    }

    protected void addViews() {
        for (int i = 0; i < 7; i++) {
            if (i == 0 || i == 2 || i == 4 || i == 6 ) {
                IPEditText ipEditText = createIPEditText();
                ipEditTexts.add(ipEditText);
                addView(ipEditText);
            } else {
                addView(createSemicolonTextView());
            }
        }
    }

    protected IPEditText createIPEditText() {

        IPEditText ipEditText = new IPEditText(getContext());
        LayoutParams params = new LayoutParams(0, LayoutParams.MATCH_PARENT);
        params.weight = 1;
        ipEditText.setLayoutParams(params);
        ipEditText.setTextSize(sp16);//sp
        ipEditText.setTextColor(0xFF888888);
        ipEditText.setGravity(Gravity.CENTER);
        ipEditText.setPadding(dp4, dp4, dp4, dp4);
        ipEditText.setSingleLine();
        ipEditText.setFocusable(false);
        ipEditText.setFocusableInTouchMode(true);
        ipEditText.setBackgroundColor(0xFFFFFFFF);
        return ipEditText;
    }

    protected TextView createSemicolonTextView() {

        TextView textView = new TextView(getContext());
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(params);
        textView.setTextSize(sp16);//sp
        textView.setTextColor(0xFF444444);
        textView.setText(".");
        textView.setPadding(0,0,0,5);
        textView.getPaint().setFakeBoldText(true);
        textView.setBackgroundColor(0xFFFFFFFF);
        textView.setGravity(Gravity.BOTTOM);
        return textView;
    }

    @Deprecated
    public void remeasureChildWeight() {
        for (int i = 0; i < 6; i++) {
            if (i == 0 || i == 2 || i == 4 || i == 6 ) {
                LayoutParams params = new LayoutParams(0, LayoutParams.MATCH_PARENT);
                params.weight = 1;
                getChildAt(i).setLayoutParams(params);
            } else {
                LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
                getChildAt(i).setLayoutParams(params);
            }
        }
    }

    protected void initIPView() {
        for (int i = 0; i < ipEditTexts.size(); i++) {
            ipEditTexts.get(i).addTextChangedListener(this);
            if(i!=0){
                ipEditTexts.get(i).setOnKeyListener(new OnMacKeyListener(ipEditTexts.get(i-1),ipEditTexts.get(i)));
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
        if (s.toString().length() == 3) {
            if (ipEditTexts.get(0).isFocused()) {
                ipEditTexts.get(0).clearFocus();
                ipEditTexts.get(1).requestFocus();
            } else if (ipEditTexts.get(1).isFocused()) {
                ipEditTexts.get(1).clearFocus();
                ipEditTexts.get(2).requestFocus();
            } else if (ipEditTexts.get(2).isFocused()) {
                ipEditTexts.get(2).clearFocus();
                ipEditTexts.get(3).requestFocus();
            }
        }
    }

    public boolean checkInputValue(IPEditText... params) {

        boolean result = true;
        for (int i = 0; i < params.length - 1; i++) {
            if (!params[i].checkInputValue()) {
                result = false;
                break;
            }
        }

        return result;
    }

    public void setIPText(String ip) {
        String[] ips = ip.split(":");
        for (int i = 0; i < ipEditTexts.size(); i++) {
            ipEditTexts.get(i).setText(ips[i]);
        }
    }

    public String getIPValues() {
        String result = "";
        for (int i = 0; i < ipEditTexts.size(); i++) {
            result += ipEditTexts.get(i).getText().toString();
        }
        return result;
    }


    class OnMacKeyListener implements OnKeyListener{

        private IPEditText clearEditText;
        private IPEditText requestEditText;

        public OnMacKeyListener(IPEditText requestEditText,IPEditText clearEditText){
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
