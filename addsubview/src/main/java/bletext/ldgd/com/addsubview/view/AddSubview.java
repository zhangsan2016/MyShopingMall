package bletext.ldgd.com.addsubview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import bletext.ldgd.com.addsubview.LogUtil;
import bletext.ldgd.com.addsubview.R;


/**
 * Created by ldgd on 2017/8/9.
 */

public class AddSubview extends LinearLayout implements View.OnClickListener {
    private ImageView iv_sub;
    private ImageView iv_add;
    private TextView count;

    private int value = 1;
    private int minValue = 1;
    private int maxValue = 10;

    private Context mContext;


    public AddSubview(Context context) {
        this(context, null);

    }

    public AddSubview(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddSubview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.mContext = context;

        value = getValue();

        //把布局和当前类形成整体
        View.inflate(context, R.layout.number_add_sub_layout, this);
        iv_sub = (ImageView) this.findViewById(R.id.iv_sub);
        iv_add = (ImageView) this.findViewById(R.id.iv_add);
        count = (TextView) this.findViewById(R.id.tv_count);

        //设置点击事件
        iv_sub.setOnClickListener(this);
        iv_add.setOnClickListener(this);


    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        count.setText(value + "");
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_sub:
                LogUtil.e("iv_sub");
                value--;
                setValue(value);
                if (onNumberChangeListener != null) {
                    onNumberChangeListener.onNumberChange(value);
                }

                break;
            case R.id.iv_add:
                LogUtil.e("iv_add");
                value++;
                setValue(value);
                if (onNumberChangeListener != null) {
                    onNumberChangeListener.onNumberChange(value);
                }

                break;

        }

    }


    /**
     * 当数量发生变化的时候回调
     */
    public interface OnNumberChangeListener {
        /**
         * 当数据发生变化的时候回调
         *
         * @param value
         */
        public void onNumberChange(int value);
    }

    private OnNumberChangeListener onNumberChangeListener;

    public void setOnNumberChangeListener(OnNumberChangeListener onNumberChangeListener) {
        this.onNumberChangeListener = onNumberChangeListener;
    }

}
