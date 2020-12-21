package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.di.component.DaggerCurrencyConverterComponent;
import com.pine.populay_options.mvp.model.mvp.contract.CurrencyConverterContract;
import com.pine.populay_options.mvp.model.mvp.contract.MarginCalculatorContract;
import com.pine.populay_options.mvp.model.mvp.presenter.CurrencyConverterPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.MarginCalculatorPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class CurrencyConverterActivity extends BaseActivity<CurrencyConverterPresenter> implements CurrencyConverterContract.View {
    @BindView(R.id.toolbar_back)
    RelativeLayout mToolbarBack;
    @BindView(R.id.img_unders)
    ImageView imgUnders;
    @BindView(R.id.line_calculator_)
    LinearLayout lineCalculators;
    @BindView(R.id.line_calculator)
    LinearLayout lineCalculator;
    @BindView(R.id.LinearLayout)
    LinearLayout mLinearLayout;
    @BindView(R.id.radio)
    RelativeLayout radio;
    @BindView(R.id.txt_equal)
    TextView txt_equal;
    @BindView(R.id.txt_national_main)
    TextView txt_national_main;
    int width1;
    int height1;
    private String lastOperators = ""; //记录上一次进行计算操作的结果
    private double firstNumber = 0D; //定义并初始化第一次的取值
    private double sencondNumber = 0D; //定义并初始化第二次的取值
    private double dou = 0;  //接收结果
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCurrencyConverterComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.study_currency_converter;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.customer_service);
        mToolbarBack.setVisibility(View.VISIBLE);
        WindowManager wm1 = this.getWindowManager();
        width1 = wm1.getDefaultDisplay().getWidth();
        height1 = wm1.getDefaultDisplay().getHeight();

    }

    float formerValue;
    @OnClick({R.id.img_unders, R.id.img_under, R.id.txt_study_currency_converter_market, R.id.txt_study_currency_converter_0, R.id.txt_study_currency_converter_1, R.id.txt_study_currency_converter_2, R.id.txt_study_currency_converter_3, R.id.txt_study_currency_converter_4,
            R.id.txt_study_currency_converter_5, R.id.txt_study_currency_converter_6, R.id.txt_study_currency_converter_7, R.id.txt_study_currency_converter_8, R.id.txt_study_currency_converter_9, R.id.txt_study_currency_converter_multiply, R.id.txt_study_currency_converter_,
            R.id.txt_study_currency_converter_less, R.id.txt_study_currency_converter_add, R.id.txt_study_currency_converter_equal, R.id.txt_study_currency_converter_home, R.id.txt_study_currency_converter_tools, R.id.txt_study_currency_converter_me,R.id.txt_study_currency_converter_ac,
            R.id.txt_study_currency_converter_markets, R.id.txt_study_currency_converter_mes, R.id.txt_study_currency_converter_toolss, R.id.txt_study_currency_converter_homes,R.id.txt_study_currency_converter_point,R.id.txt_study_currency_converter_divide_by,R.id.txt_study_currency_converter_percent
    })
    public void onClick(View view) {
        ObjectAnimator anim;
        String string;
        switch (view.getId()) {
            case R.id.img_unders:
                lineCalculators.setVisibility(View.GONE);
                lineCalculator.setVisibility(View.VISIBLE);
                break;
            case R.id.img_under:
                lineCalculators.setVisibility(View.VISIBLE);
                lineCalculator.setVisibility(View.GONE);
                break;
            case R.id.txt_study_currency_converter_c:

                break;
            case R.id.txt_study_currency_converter_:

                break;
            case R.id.txt_study_currency_converter_ac:
                lastOperators="";
                txt_equal.setText("");
                break;
            case R.id.txt_study_currency_converter_divide_by:

                string = txt_equal.getText().toString();
                if (opratorCalc(string)){
                    getResult();
                    string = txt_equal.getText().toString();
                }
                txt_equal.setText(string + " "+"÷"+" ");
                break;
            case R.id.txt_study_currency_converter_multiply:
                string = txt_equal.getText().toString();
                if (opratorCalc(string)){
                    getResult();
                    string = txt_equal.getText().toString();
                }
                string = txt_equal.getText().toString();
                txt_equal.setText(string + " "+"×"+" ");
                break;
            case R.id.txt_study_currency_converter_less:
                string = txt_equal.getText().toString();
                if (opratorCalc(string)){
                    getResult();
                    string = txt_equal.getText().toString();
                }

                txt_equal.setText(string + " "+"－"+" ");
                break;
            case R.id.txt_study_currency_converter_add:
                string = txt_equal.getText().toString();
                if (opratorCalc(string)){
                    getResult();
                    string = txt_equal.getText().toString();
                }

                txt_equal.setText(string + " "+"＋"+" ");
                break;
            case R.id.txt_study_currency_converter_equal:
                getResult();
                string = txt_equal.getText().toString();
                txt_national_main.setText(string);
                break;
            case R.id.txt_study_currency_converter_0:
                string = txt_equal.getText().toString();
                txt_equal.setText(string + "0");
                break;
            case R.id.txt_study_currency_converter_1:
                string = txt_equal.getText().toString();
                txt_equal.setText(string + "1");
                break;
            case R.id.txt_study_currency_converter_2:
                string = txt_equal.getText().toString();
                txt_equal.setText(string + "2");
                break;
            case R.id.txt_study_currency_converter_3:
                string = txt_equal.getText().toString();
                txt_equal.setText(string + "3");
                break;
            case R.id.txt_study_currency_converter_4:
                string = txt_equal.getText().toString();
                txt_equal.setText(string + "4");
                break;
            case R.id.txt_study_currency_converter_5:
                string = txt_equal.getText().toString();
                txt_equal.setText(string + "5");
                break;
            case R.id.txt_study_currency_converter_6:
                string = txt_equal.getText().toString();
                txt_equal.setText(string + "6");
                break;
            case R.id.txt_study_currency_converter_7:
                string = txt_equal.getText().toString();
                txt_equal.setText(string + "7");
                break;
            case R.id.txt_study_currency_converter_8:
                string = txt_equal.getText().toString();
                txt_equal.setText(string + "8");
                break;
            case R.id.txt_study_currency_converter_9:
                string = txt_equal.getText().toString();
                txt_equal.setText(string + "9");
                break;
             case R.id.txt_study_currency_converter_point:
                string = txt_equal.getText().toString();
                txt_equal.setText(string + ".");
                break;
            case R.id.  txt_study_currency_converter_percent:
                string = txt_equal.getText().toString();

                if (opratorCalc(string)){
                    String str3 = string.substring(0,string.indexOf(" ") + 3);
                    String str2 = string.substring(string.indexOf(" ") + 3);
                    Double po=   Double.parseDouble(str2)/100D;
                    txt_equal.setText(str3+po);
                }else {
                    Double po=   Double.parseDouble(string)/100D;
                    txt_equal.setText(po+"");
                }
                break;
            case R.id.txt_study_currency_converter_home:
            case R.id.txt_study_currency_converter_homes:
                string = txt_equal.getText().toString();
                try {
                 double a=   Double.parseDouble(string);
                 int d= (int) a;
                  txt_equal.setText(d+"");
                }catch (Exception e){

                }
                break;
            case R.id.txt_study_currency_converter_market:
            case R.id.txt_study_currency_converter_markets:

                break;
            case R.id.txt_study_currency_converter_tools:
            case R.id.txt_study_currency_converter_toolss:

                break;
            case R.id.txt_study_currency_converter_me:
            case R.id.txt_study_currency_converter_mes:
                 txt_equal.setText("0.00");
                txt_national_main.setText("0.00");

                break;
        }
    }


    //按当前计算结果进行下一次的数据输入及计算
    public boolean opratorCalc(String  string)
    {

        return  string.indexOf("＋")!=-1||string.indexOf("－")!=-1||string.indexOf("×")!=-1||string.indexOf("÷")!=-1;
    }
    private void getResult() {
        //首先取一下你输入完后现在输入框的内容
        String result = txt_equal.getText().toString();
        //当我们的输入框是null或者""时我们不进行操作
        if (result == null || result.equals(" ")) {
            return;
        }
        //我们要计算结果，必须知道输入框是否有操作符，判断条件就是前后是否在空格，否则不进行操作
        if (!result.contains(" ")) {
            //没有运算符，所以不用运算
            return;
        }
        //当我点击等号的时候，清空标识设置为true,但是如果之前有的话，设置成false

        //如果有空格，我们就截取前后段再获取运算符进行计算
        String str1 = result.substring(0, result.indexOf(" "));  //运算符前面字段
        String op = result.substring(result.indexOf(" ") + 1, result.indexOf(" ") + 2);  //截取运算符
        String str2 = result.substring(result.indexOf(" ") + 3);  //运算符后面字段

        //这里我们还是要判断str1和str2都不是空才
        if (!str1.equals("") && !str2.equals("")) {  //第一种情况：都不是空
            //强转
            double d1 = Double.parseDouble(str1);
            double d2 = Double.parseDouble(str2);
            //开始计算，定义一个double变量接收结果，定义为全局
            if (op.equals("＋")) {  //加
                dou = d1 + d2;
            } else if (op.equals("－")) { //减
                dou = d1 - d2;
            } else if (op.equals("×")) { //乘
                dou = d1 * d2;
            } else if (op.equals("÷")) {  //除
                //除数为0不计算
                if (d2 == 0) {
                    dou = 0;
                } else {
                    dou = d1 / d2;
                }
            }
            //我们之前把他强转为double类型了，但是如果没有小数点，我们就是int类型
            if (!str1.contains(".") && !str2.contains(".") && !op.equals("÷")) {
                int i = (int) dou;
                txt_equal.setText(i + "");
            } else {  //如果有小数点
                txt_equal.setText(dou + "");
            }
        } else if (!str1.equals("") && str2.equals("")) {  //第二种情况:str2是空
            //这种情况就不运算了
            txt_equal.setText(result);
        } else if (str1.equals("") && !str2.equals("")) {  //第三种情况:str1是空
            //这种情况我们就需要判断了，因为此时str1 = 0;
            double d3 = Double.parseDouble(str2);
            if (op.equals("+")) {  //加
                dou = 0 + d3;
            } else if (op.equals("-")) { //减
                dou = 0 - d3;
            } else if (op.equals("×")) { //乘
                dou = 0;
            } else if (op.equals("÷")) {  //除
                //除数为0不计算
                dou = 0;
            }
            //我们之前把他强转为double类型了，但是如果没有小数点，我们就是int类型
            if (!str2.contains(".")) {
                int i = (int) dou;
                txt_equal.setText(i + "");
            } else {  //如果有小数点
                txt_equal.setText(dou + "");
            }
        } else {  //最后一种情况，他们两个都是空
            //我们清空掉
            txt_equal.setText("");
        }
    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
