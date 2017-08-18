package com.ysn.moncy.library.svgloader;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;
import com.ysn.moncy.R;

import java.io.InputStream;

/**
 * Created by root on 02/08/17.
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
                .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                .decoder(new SvgDecoder())
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_error)
                .animate(android.R.anim.fade_in)
                .listener(new SvgSoftwareLayerSetter<Uri>());
        return requestBuilder;
    }
}
