package idea.analyzesystem.android.edittext.port;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;

import idea.analyzesystem.android.edittext.ip.IPEditText;
import idea.analyzesystem.android.edittext.ip.IPView;

/**
 * Created by idea on 2016/7/15.
 */
public class PortView extends IPEditText {
    public PortView(Context context) {
        this(context, null, 0);
    }

    public PortView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PortView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFocusableInTouchMode(true);
        setGravity(Gravity.CENTER);
        setPadding(4,4,4,4);
    }

    @Override
    public int getMaxLength() {
        return 5;
    }
}
