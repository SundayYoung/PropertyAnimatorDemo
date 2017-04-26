package com.felixliu.propertyanimatordemo.view;

import android.view.animation.LinearInterpolator;

public class MyInterpolator extends LinearInterpolator {
	private float factor;

	public MyInterpolator() {
		this.factor = 0.15f;
	}

	@Override
	public float getInterpolation(float input) {
		return (float) (Math.pow(2, -10 * input)
				* Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1);
	}
}
