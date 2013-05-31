package com.zgy.piechartview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

/**
 * QQ群：88130145
 * @author ZGY
 *
 */
public class MainActivity extends Activity {

	private String[] colors = { "#000000", "#ff0000", "#ff6666", "#ff80FF", "#ffFF00", "#ffE685" };
	private float[] items = { (float) 21.0, (float) 20.0, (float) 10.0, (float) 10.0, (float) 10.0, (float) 10.0, (float) 10.0, (float) 10.0, (float) 10.0, (float) 10.0 };
	// private float[] items = { (float) 20.0, (float) 20.0, (float) 10.0 };
	private int total = 150;
	private int radius = 100;
	private int strokeWidth = 0;
	private String strokeColor = "#000000";
	private float animSpeed = (float) 2;

	private PieChartView pieChart;

	private TextView textInfo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		textInfo = (TextView) findViewById(R.id.text_item_info);
		pieChart = (PieChartView) findViewById(R.id.parbar_view);
		// pb.setShowItem(5, true);//设置显示的块
		// pb.setAnimEnabled(false);//是否开启动画
		pieChart.setItemsSizes(items);// 设置各个块的值
		// pieChart.setTotal(total);//设置整体的值, 默认为和
		// pieChart.setItemsColors(colors);//设置各个块的颜色
		pieChart.setRotateSpeed(animSpeed);// 设置旋转速度
		pieChart.setRaduis(radius);// 设置饼状图半径，不包含边缘的圆环
		pieChart.setStrokeWidth(strokeWidth);// 设置边缘的圆环粗度
		pieChart.setStrokeColor(strokeColor);// 设置边缘的圆环颜色
		pieChart.setRotateWhere(PieChartView.TO_RIGHT);//设置选中的item停靠的位置，默认在右侧
		pieChart.setSeparateDistence(20);// 设置旋转的item分离的距离
		// 也可以不使用xml布局，更多细节请看DOC

		pieChart.setOnItemSelectedListener(new OnPieChartItemSelectedLinstener() {

			public void onPieChartItemSelected(PieChartView view, int position, String colorRgb, float size, float rate, boolean isFreePart, float rotateTime) {
				// TODO Auto-generated method stub
				Log.e("Main", "onClicked item : " + position);
				if (isFreePart) {
					textInfo.setText("多余的部分" + position + "\r\nitem size: " + size + "\r\nitem color: " + colorRgb + "\r\nitem rate: " + rate + "\r\nrotateTime : " + rotateTime);
				} else {
					textInfo.setText("item position: " + position + "\r\nitem size: " + size + "\r\nitem color: " + colorRgb + "\r\nitem rate: " + rate + "\r\nrotateTime : " + rotateTime);
				}
				textInfo.setVisibility(View.VISIBLE);
				Animation myAnimation_Alpha = new AlphaAnimation(0.1f, 1.0f);
				myAnimation_Alpha.setDuration((int) (3 * rotateTime));
				textInfo.startAnimation(myAnimation_Alpha);
			}

		});
	}
}
