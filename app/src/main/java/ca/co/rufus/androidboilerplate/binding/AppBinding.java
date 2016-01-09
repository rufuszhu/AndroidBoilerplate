package ca.co.rufus.androidboilerplate.binding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import ca.co.rufus.androidboilerplate.R;
import ca.co.rufus.androidboilerplate.injection.module.ApiModule;


/**
 * Created by rufus on 2015-12-10.
 */
public class AppBinding {
    @BindingAdapter({"app:imageUrl"})
    public static void setImageUrl(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(ApiModule.PRODUCTION_API_URL + url)
                .error(R.mipmap.ic_launcher)
                .centerCrop()
                .crossFade()
                .into(view);
    }
}
