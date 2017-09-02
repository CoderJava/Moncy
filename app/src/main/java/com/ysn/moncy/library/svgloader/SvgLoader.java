package com.ysn.moncy.library.svgloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.caverock.androidsvg.SVG;
import com.ysn.moncy.R;

import java.io.InputStream;

/**
 * Created by root on 02/08/17.
 * Library SVG Loader
 */

public class SvgLoader {

    private final Context context;

    public SvgLoader(Context context) {
        this.context = context;
    }

    public GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> load() {
        GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder =
                Glide.with(context)
                .using(Glide.buildStreamModelLoader(Uri.class, context), InputStream.class)
                .from(Uri.class)
                .as(SVG.class)
                .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new FileToStreamDecoder<>(new SvgDecoder()))
                .decoder(new SvgDecoder())
                .placeholder(R.drawable.ic_image_black_24dp)
                .error(R.drawable.ic_broken_image_black_24dp)
                .animate(android.R.anim.fade_in)
                .listener(new SvgSoftwareLayerSetter<Uri>());
        return requestBuilder;
    }
}
