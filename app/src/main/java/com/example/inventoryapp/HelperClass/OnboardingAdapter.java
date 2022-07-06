package com.example.inventoryapp.HelperClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.inventoryapp.R;

import org.w3c.dom.Text;

public class OnboardingAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public OnboardingAdapter(Context context) {
        this.context = context;
    }

    int images[]= {

            R.drawable.onboarding_icon,
            R.drawable.onboarding_icon2,
            R.drawable.onboarding_icon1
    };

    int headings[] = {

            R.string.onboardingtext2,
            R.string.onboardingtext3,
            R.string.onboardingtext4

    };

    int captions[] = {

            R.string.onboardingcaption1,
            R.string.onboardingcaption2,
            R.string.onboardingcaption3
    };

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.onboarding_layout,container, false);

        //Hooks
        ImageView imageView = view.findViewById(R.id.sliderimage);
        TextView heading = view.findViewById(R.id.tvonboarding1);
        TextView caption = view.findViewById(R.id.tvonboarding2);

        imageView.setImageResource(images[position]);
        heading.setText(headings[position]);
        caption.setText(captions[position]);

        container.addView(view);

        return  view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
