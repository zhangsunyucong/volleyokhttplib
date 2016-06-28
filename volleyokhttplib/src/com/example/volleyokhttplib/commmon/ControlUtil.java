package com.example.volleyokhttplib.commmon;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.volleyokhttplib.MyApplication;
import com.example.volleyokhttplib.R;

public class ControlUtil {
	private static String TAG = "ControlUtil";

	public static void updateTextView(View view, int textViewId,
			int textStringId) {
		if (view != null) {
			TextView textView = (TextView) view.findViewById(textViewId);
			if (textView != null) {
				textView.setText(textStringId);
			}
		}

	}

	public static void updateTextView(View view, int textViewId,
			String textString) {
		if (view != null) {
			TextView textView = (TextView) view.findViewById(textViewId);
			if (textView != null) {
				textView.setText(textString);
			}
		}

	}
	
	public static void updateTextView(View view, int textViewId,
			SpannableStringBuilder spannableStringBuilder) {
		if (view != null) {
			TextView textView = (TextView) view.findViewById(textViewId);
			if (textView != null) {
				textView.setText(spannableStringBuilder);
			}
		}

	}

	public static void updateTextView(Activity activity, int textViewId,
			int textStringId) {
		if (activity != null) {
			TextView textView = (TextView) activity.findViewById(textViewId);
			if (textView != null) {
				textView.setText(textStringId);
			}
		}

	}

	public static void updateTextView(Activity activity, int textViewId,
			String textString) {
		if (activity != null) {
			TextView textView = (TextView) activity.findViewById(textViewId);
			if (textView != null) {
				textView.setText(textString);
			}
		}

	}

	public static void updateEditText(Activity activity, int ediTextId,
			String textString) {
		if (activity != null) {
			EditText editText = (EditText) activity.findViewById(ediTextId);
			if (editText != null) {
				editText.setText(textString);
			}
		}
	}
	public static void updateEditText(View view, int ediTextId,
			String textString) {
		if (view != null) {
			EditText editText = (EditText) view.findViewById(ediTextId);
			if (editText != null) {
				editText.setText(textString);
			}
		}
	}
	
	public static void updateButtonText(Activity activity, int btnId,
			String textString){
		if(activity != null){
			Button btn = (Button) activity.findViewById(btnId);
			if (btn != null) {
				btn.setText(textString);
			}
		}
	}

	public static void setViewVisibility(View view, int visibility) {

		if (view != null) {
			view.setVisibility(visibility);
		}

	}

	public static void setViewVisibility(View dstView, int dstId, int visibility) {
		if (dstView != null) {
			View view = dstView.findViewById(dstId);

			if (view != null) {
				view.setVisibility(visibility);
			}
		}

	}
	
	public static int getViewVisibility(View dstView,  int dstId){
		if (dstView != null) {
			View view = dstView.findViewById(dstId);

			if (view != null) {
				return view.getVisibility();
			}
		}
		return -1;
	}
	
	public static int getViewVisibility(Activity activity,  int dstId){
		if (activity != null) {
			View view = activity.findViewById(dstId);

			if (view != null) {
				return view.getVisibility();
			}
		}
		return -1;
	}
	
	public static void setViewVisibility(Activity activity, int dstViewId, int dstId, int visibility){
		View parentView = activity.findViewById(dstViewId);

		if (parentView != null) {
			View view = parentView.findViewById(dstId);
			if(view != null){
				view.setVisibility(visibility);
			}
		}
	}

	public static void setViewVisibility(Activity activity, int dstId,
			int visibility) {
		if (activity != null) {
			View view = activity.findViewById(dstId);

			if (view != null) {
				view.setVisibility(visibility);
			}
		}

	}

	public static void updateTextViewColor(View view, int viewId, int colorId) {
		if (view != null) {
			TextView textViewId = (TextView) view.findViewById(viewId);
			if (textViewId != null) {
				textViewId.setTextColor(view.getResources().getColor(colorId));
			}
		}

	}

	public static String getEditTextString(Activity activity, int dstId) {
		if (activity != null) {
			EditText editText = (EditText) activity.findViewById(dstId);
			if (editText != null) {
				return editText.getText().toString();
			}
		}
		return null;
	}

	public static String getEditTextString(View view, int dstId) {
		if (view != null) {
			EditText editText = (EditText) view.findViewById(dstId);
			if (editText != null) {
				return editText.getText().toString();
			}
		}
		return null;
	}

	public static String getTextViewString(View view, int dstId) {
		if (view != null) {
			TextView textView = (TextView) view.findViewById(dstId);
			if (textView != null) {
				return textView.getText().toString();
			}
		}
		return null;

	}

	public static String getTextViewString(Activity activity, int dstId) {
		if (activity != null) {
			TextView textView = (TextView) activity.findViewById(dstId);
			if (textView != null) {
				return textView.getText().toString();
			}
		}
		return null;

	}

	public static void showToastText(Context context, String text) {
		if ((context != null) && (text != null)) {
			Toast toast = new Toast(context);
			LayoutInflater inflater = LayoutInflater.from(context);
			View view = inflater.inflate(R.layout.layout_toast_message, null);
			updateTextView(view, R.id.id_textView_toastMessage, text);			
			toast.setView(view);
			toast.show();
		} else {
			Log.d(TAG,"showToastText --(context != null) || (text != null) ");
		}

	}
	
	public static void setBackgroundColor(Activity activity,int childViewId,int color){
		if (activity != null) {
			View childView = activity.findViewById(childViewId);
			if (childView != null) {
				childView.setBackgroundColor(color);
			}
		}
	}
	
	public static void setBackgroundColor(View view,int childViewId,int color){
		if (view != null) {
			View childView = view.findViewById(childViewId);
			if (childView != null) {
				childView.setBackgroundColor(color);
			}
		}
	}

	public static void setBackgroundDrawable(View view, int childViewId,
			int drawableId) {

		if (view != null) {
			View childView = view.findViewById(childViewId);
			if (childView != null) {
				childView.setBackgroundResource(drawableId);
			}
		}
	}

	public static void setBackgroundDrawable(Activity activity,
			int childViewId, int drawableId) {

		if (activity != null) {
			View childView = activity.findViewById(childViewId);

			if (childView != null) {
				childView.setBackgroundResource(drawableId);
			}
		}
	}

	public static void setImageViewDrawable(Activity activity, int childViewId,
			int drawableId) {

		if (activity != null) {
			ImageView childView = (ImageView) activity
					.findViewById(childViewId);
			if (childView != null) {
				childView.setImageResource(drawableId);
			}
		}
	}
	
	
	public static void setImageViewDrawable(View view, int childViewId,
			int drawableId) {

		if (view != null) {
			ImageView childView = (ImageView) view
					.findViewById(childViewId);
			if (childView != null) {
				childView.setImageResource(drawableId);
			}
		}
	}
	
	public static void clearImageMemory(View view){
		if (view!=null) {
			Drawable drawable = view.getBackground();
			if (drawable!=null && (drawable instanceof BitmapDrawable)) {
				Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
				if (bitmap!=null) {
					bitmap.recycle();
				}
				bitmap = null;
			}
			
			if (drawable!=null) {
				drawable.setCallback(null);
			}
		}
	}

	public static void setTextColor(Activity activity, int viewId, int colorId) {
		if (activity != null) {
			TextView textViewId = (TextView) activity.findViewById(viewId);
			if (textViewId != null) {
				textViewId.setTextColor(activity.getResources().getColor(
						colorId));
			}
		}

	}
	
	public static void setTextColor(View view, int viewId, int colorId) {
		if (view != null) {
			TextView textViewId = (TextView) view.findViewById(viewId);
			if (textViewId != null) {
				textViewId.setTextColor(MyApplication.getApplication().getResources().getColor(
						colorId));
			}
		}

	}

	public static void setBtnTextColor(Activity activity, int viewId,
			int colorId) {
		if (activity != null) {
			Button button = (Button) activity.findViewById(viewId);
			if (button != null) {
				button.setTextColor(activity.getResources().getColor(colorId));
			}
		}
	}

	public static void setImageViewImageResource(View view, int imageViewId,
			int resourceId) {
		if (view != null) {
			ImageView dstView = (ImageView) view.findViewById(imageViewId);
			dstView.setImageResource(resourceId);
		}
	}
	
	public static void setImageViewImageResource(Activity activity, int imageViewId,
			int resourceId) {
		if (activity != null) {
			ImageView dstView = (ImageView) activity.findViewById(imageViewId);
			dstView.setImageResource(resourceId);
		}
	}
	
	public static void setImageButtonImageResource(View view,int imageButtonId,int resourceId){
		if (view!=null) {
			ImageButton imageButton = (ImageButton)view.findViewById(imageButtonId);
			
			if (imageButton!=null) {
				imageButton.setImageResource(resourceId);
			}
		}
	}
	public static void setButtonClickable(Activity activity,int viewId,boolean clickable){
		Button btn = (Button) activity.findViewById(viewId);
		btn.setClickable(clickable);
	}
	
	public static void setButtonClickable(View view,int viewId,boolean clickable){
		Button btn = (Button) view.findViewById(viewId);
		btn.setClickable(clickable);
	}

	public static void updateTextViewWithTimeFormat(TextView view, int second) {
		if (view != null) {
			int hh = second / 3600;
			int mm = second % 3600 / 60;
			int ss = second % 60;
			String strTemp = null;
			if (0 != hh) {
				strTemp = String.format("%02d:%02d:%02d", hh, mm, ss);
			} else {
				strTemp = String.format("%02d:%02d", mm, ss);
			}
			view.setText(strTemp);
		}
	}
}
