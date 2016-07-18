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
import idea.analyzesystem.edittext.R;

/**
 * Created by idea on 2016/7/15.
 */
public class MacView extends AbsEditTextGroup {
    public MacView(Context context) {
        super(context);
    }

    public MacView(Context context, AttributeSet attrs) {
        super(context, attrs);
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
}
