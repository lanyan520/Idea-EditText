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

import idea.analyzesystem.android.edittext.AbsEditText;
import idea.analyzesystem.android.edittext.AbsEditTextGroup;
import idea.analyzesystem.android.edittext.mac.MacEditText;

/**
 * Created by idea on 2016/7/15.
 */
public class IPView extends AbsEditTextGroup {
    public IPView(Context context) {
        super(context);
    }

    public IPView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IPView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getChildCount() {
        return 7;
    }

    @Override
    public AbsEditText getAbsEditText() {
        return new IPEditText(getContext());
    }

    @Override
    public String getSemicolomText() {
        return ".";
    }

    @Override
    public int getDelMaxLength() {
        return 3;
    }

    @Override
    public void applySemicolonTextViewTheme(TextView semicolonTextView) {
        semicolonTextView.setPadding(0,0,0,5);
        semicolonTextView.getPaint().setFakeBoldText(true);
        semicolonTextView.setBackgroundColor(0xFFFFFFFF);
        semicolonTextView.setGravity(Gravity.BOTTOM);
    }

    @Override
    public void applyEditTextTheme(AbsEditText absEditText) {

    }
}
